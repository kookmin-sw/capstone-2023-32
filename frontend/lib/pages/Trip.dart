import 'package:fasttrip/pages/MakePlan.dart';
import 'package:fasttrip/pages/PostDetailPage.dart';
import 'package:flutter/material.dart';
import 'package:fasttrip/style.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';
import '../token_model.dart';


// data fetch
// 받아올 데이터 정의
class Plan {
  String planId;
  String userId;
  String title;
  int like;
  List<String> tags;
  String comment;
  String imgUrl;
  DateTime lastModifiedDate;
  bool liked;

  Plan({
    required this.planId,
    required this.userId,
    required this.title,
    required this.like,
    required this.tags,
    required this.comment,
    required this.imgUrl,
    required this.lastModifiedDate,
    required this.liked,
  });

  factory Plan.fromJson(Map<String, dynamic> json){
    DateTime tempDate = json['lastModifiedDate'] != null ? DateTime.parse(json['lastModifiedDate']) : DateTime.now();
    DateTime dateWithoutMicroseconds = DateTime(tempDate.year, tempDate.month, tempDate.day, tempDate.hour, tempDate.minute, tempDate.second);

    return Plan(
      planId: (json['id'] ?? '').toString(),
      userId: (json['userId'] ?? '').toString(),
      title: json['title'] ?? '',
      like: json['like'] ?? 0,
      tags: List<String>.from(json['tags'] ?? [],),
      comment: json['comment'] ?? '',
      imgUrl: json['image'] ?? '',
      lastModifiedDate: dateWithoutMicroseconds,
      liked: json['liked'] ?? false,
    );
  }
}





Future<List<Plan>> fetchPlans(BuildContext context) async {
  final tokenModel = Provider.of<TokenModel>(context, listen: false);
  final token = tokenModel.token;

  final response = await http.get(
    Uri.parse('http://3.38.99.234:8080/api/plan/myList'),
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Auth': '$token',
    },
  );

  if (response.statusCode == 200) {
    String responseBody = utf8.decode(response.bodyBytes);
    List<dynamic> body = jsonDecode(responseBody);
    List<Plan> plans = body.map((dynamic item) => Plan.fromJson(item)).toList();
    return plans;
  } else {
    throw Exception('Failed to load plans');
  }
}




class TripPage extends StatefulWidget {
  const TripPage({Key? key}) : super(key: key);

  @override
  State<TripPage> createState() => _TripPageState();
}

class _TripPageState extends State<TripPage> {
  List<Plan> plans = [];

  @override
  void initState() {
    super.initState();
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    fetchPlans(context).then((loadedPlans) {
      setState(() {
        plans = loadedPlans;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Column(
          children: [
            Container(
              padding:
                  const EdgeInsets.only(top: 50.0, left: 20.0, right: 20.0),
              alignment: Alignment.centerLeft,
              child: Row(
                children: [
                  Icon(Icons.airplanemode_active, color: signatureColor),
                  const SizedBox(width: 6),
                  Text('내 여행', style: heading1, textAlign: TextAlign.left),
                  const Spacer(),
                  const MoreButton(),
                ],
              ),
            ),
            const SizedBox(height: 20),
            MyTravelList(showAll: false, plans: plans),
            const SizedBox(height: 15),
            Padding(
              padding: const EdgeInsets.only(left: 20.0, right: 20.0),
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(
                    backgroundColor: signatureColor,
                    fixedSize: const Size.fromHeight(50)),
                onPressed: () {
                  print('이거 어케하징');
                },
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Icon(Icons.smart_toy, size: 18),
                    SizedBox(width: 6),
                    Text(
                      'AI로 여행 만들기 (Beta)',
                      style: TextStyle(fontSize: 18, color: Colors.white),
                    ),
                  ],
                ),
              ),
            ),
            const SizedBox(height: 20),
            Container(
              padding: const EdgeInsets.only(
                top: 15.0,
                left: 20.0,
                right: 20.0,
                bottom: 20.0,
              ),
              alignment: Alignment.centerLeft,
              child: Row(
                children: [
                  Icon(Icons.airplanemode_active, color: signatureColor),
                  const SizedBox(width: 6),
                  Text('내가 찜한 여행', style: heading1, textAlign: TextAlign.left),
                ],
              ),
            ),
            HeartList(),
          ],
        ),
      ),
    );
  }
}

class MyTravelList extends StatelessWidget {
  final bool showAll;
  final List<Plan> plans;

  const MyTravelList({Key? key, this.showAll = false, required this.plans})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: List.generate(plans.length < 3 ? plans.length + 1 : 3, (index) {
        if ((showAll || index < 3) && index < plans.length) {
          return Padding(
            padding:
            const EdgeInsets.only(left: 20.0, right: 20.0, bottom: 10.0),
            child: InkWell(
              child: Container(
                padding: const EdgeInsets.only(
                  left: 20,
                  right: 20,
                ),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(10),
                  border: Border.all(color: Colors.grey.shade300, width: 1),
                ),
                child: Padding(
                  padding: const EdgeInsets.all(8),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        plans[index].title,
                        style: const TextStyle(
                            fontSize: 18, fontWeight: FontWeight.bold),
                      ),
                      const SizedBox(height: 10),
                      Text(
                        '최근 수정일 : ${plans[index].lastModifiedDate}',
                        style: TextStyle(
                            fontSize: 14, color: Colors.grey.shade600),
                      ),
                    ],
                  ),
                ),
              ),
            ),
          );
        } else if (plans.length < 3) {
          return Padding(
            padding:
            const EdgeInsets.only(left: 20.0, right: 20.0, bottom: 10.0),
            child: InkWell(
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    settings: RouteSettings(name: 'make_plan'),
                    builder: (context) => const MakePlan(),
                  ),
                );
              },
              child: Container(
                height: 65,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(10),
                  border: Border.all(color: Colors.grey.shade300, width: 1),
                ),
                child: const Center(
                  child: Text(
                    '+',
                    style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
                  ),
                ),
              ),
            ),
          );
        } else {
          return const SizedBox.shrink();
        }
      }),
    );
  }
}

Future<List<Plan>> fetchPlans2(BuildContext context) async {
  final tokenModel = Provider.of<TokenModel>(context, listen: false);
  final token = tokenModel.token;

  final response = await http.get(
    Uri.parse('http://3.38.99.234:8080/api/plan/likedList'),
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Auth': '$token',
    },
  );

  if (response.statusCode == 200) {
    String responseBody = utf8.decode(response.bodyBytes);
    List<dynamic> body = jsonDecode(responseBody);
    List<Plan> plans = body.map((dynamic item) => Plan.fromJson(item)).toList();
    return plans;
  } else {
    throw Exception('Failed to load plans');
  }
}




class HeartList extends StatefulWidget {
  const HeartList({Key? key}) : super(key: key);

  @override
  _HeartListState createState() => _HeartListState();
}


class _HeartListState extends State<HeartList> {
  List<Plan> likedPlans = [];

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    fetchPlans2(context).then((loadedPlans) {
      setState(() {
        likedPlans = loadedPlans.where((Plan plan) => plan.liked).toList();
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: List.generate(likedPlans.length, (index) {
        return InkWell(
          onTap: () {
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => PostDetailPage(planId: likedPlans[index].planId),
              ),
            );
          },
          child: Padding(
            padding:
            const EdgeInsets.only(left: 20.0, right: 20.0, bottom: 20.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Stack(
                  children: [
                    ClipRRect(
                      borderRadius: BorderRadius.circular(10.0),
                      child: Image.network(
                        likedPlans[index].imgUrl,
                        width: double.infinity,
                        height: 200,
                        fit: BoxFit.cover,
                      ),
                    ),
                    Positioned(
                      top: 5,
                      right: 5,
                      child: IconButton(
                        onPressed: () {
                          setState(() {
                            likedPlans[index].liked = !likedPlans[index].liked;
                          });
                        },
                        padding: EdgeInsets.zero,
                        constraints: const BoxConstraints(),
                        icon: const Icon(
                          Icons.favorite,
                          color: Color(0xffFA6D6D),
                        ),
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 10.0),
                Text(
                  likedPlans[index].title,
                  style: const TextStyle(
                      fontSize: 16, fontWeight: FontWeight.bold),
                ),
                const SizedBox(height: 10.0),
                Wrap(
                  spacing: 8.0,
                  children: likedPlans[index]
                      .tags
                      .map((tag) => Chip(
                    label: Text(
                      tag,
                      style: const TextStyle(color: Color(0xff6DA5FA)),
                    ),
                    backgroundColor: Colors.transparent,
                    side: const BorderSide(
                        color: Color(0xff6DA5FA), width: 1),
                  ))
                      .toList(),
                ),
              ],
            ),
          ),
        );
      }),
    );
  }
}


class MoreButton extends StatelessWidget {
  const MoreButton({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return TextButton(
      onPressed: () {
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => const AllMyTravelList()),
        );
      },
      child: Text(
        '더보기',
        style: lightText,
      ),
    );
  }
}

class AllMyTravelList extends StatefulWidget {
  const AllMyTravelList({Key? key}) : super(key: key);

  @override
  State<AllMyTravelList> createState() => _AllMyTravelListState();
}

class _AllMyTravelListState extends State<AllMyTravelList> {
  List<Plan> plans = [];

  @override
  void initState() {
    super.initState();
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    fetchPlans(context).then((loadedPlans) {
      setState(() {
        plans = loadedPlans;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        elevation: 0,
        backgroundColor: Colors.transparent,
        shadowColor:
        Colors.transparent,
        leading: IconButton(
          icon: Icon(
            Icons.arrow_back,
            color: Colors.black,
          ),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
      ),
      body:Container(
        padding: const EdgeInsets.only(top:10.0),
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.only(left:20.0, bottom: 20.0),
              child: Row(
                children: [
                  Icon(Icons.airplanemode_active, color: signatureColor),
                  const SizedBox(width: 6),
                  Text('내 여행 목록', style: heading1, textAlign: TextAlign.left),
                ],
              ),
            ),
            MyTravelList(showAll: false, plans: plans),
          ],
      ),
      ),
    );
  }
}