import 'package:flutter/material.dart';
import 'package:collection/collection.dart';
import 'package:share/share.dart';
import 'package:fasttrip/style.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;


class PostDetailPage extends StatefulWidget {
  final String planId;

  PostDetailPage({Key? key, required this.planId}) : super(key: key);

  @override
  State<PostDetailPage> createState() => _PostDetailPageState();
}


class _PostDetailPageState extends State<PostDetailPage> {
  bool isLiked = false;
  Map<String, dynamic>? planData;

  final Map<String, dynamic> dummyData = {
    "plan": {
      "p_id": "plan number",
      "p_title": "사랑과 낭만의 도시 Paris",
      "user": {"id": "Seulgi"},
      "tags": ["계획", "프랑스", "유럽여행", "혼자"],
      "p_comment": "최적의 동선으로 짠 코스입니다! 이 정도는 다녀야 어디가서 프랑스 가봤다고 할 수 있죠!",
      "p_likes": 0,
    },
    "pplan": [
      {
        "plan": {"p_id": "plan number"},
        "day": "1",
        "p_seq": "1",
        "p_name": "라파예트 백화점",
        "p_post": "address 1",
        "p_locate": "Location coordinates",
        "p_country": "France"
      },
      {
        "plan": {"p_id": "plan number"},
        "day": "1",
        "p_seq": "2",
        "p_name": "에투알 광장 개선문",
        "p_post": "address 1",
        "p_locate": "Location coordinates",
        "p_country": "France"
      },
      {
        "plan": {"p_id": "plan number"},
        "day": "1",
        "p_seq": "3",
        "p_name": "Angelina",
        "p_post": "address 1",
        "p_locate": "Location coordinates",
        "p_country": "France"
      },
      {
        "plan": {"p_id": "plan number"},
        "day": "1",
        "p_seq": "4",
        "p_name": "알렉산더 3세 다리",
        "p_post": "address 1",
        "p_locate": "Location coordinates",
        "p_country": "France"
      },
      {
        "plan": {"p_id": "plan number"},
        "day": "2",
        "p_seq": "1",
        "p_name": "La Main Noire",
        "p_post": "Location address 2",
        "p_locate": "Location coordinates",
        "p_country": "France"
      },
      {
        "plan": {"p_id": "plan number"},
        "day": "2",
        "p_seq": "2",
        "p_name": "샤크레퀘르 대성당",
        "p_post": "Location address 2",
        "p_locate": "Location coordinates",
        "p_country": "France"
      },
      {
        "plan": {"p_id": "plan number"},
        "day": "2",
        "p_seq": "3",
        "p_name": "마르스 광장 (에펠탑)",
        "p_post": "Location address 2",
        "p_locate": "Location coordinates",
        "p_country": "France"
      },
      {
        "plan": {"p_id": "plan number"},
        "day": "2",
        "p_seq": "4",
        "p_name": "오페라",
        "p_post": "Location address 2",
        "p_locate": "Location coordinates",
        "p_country": "France"
      },
      {
        "plan": {"p_id": "plan number"},
        "day": "3",
        "p_seq": "1",
        "p_name": "이랑",
        "p_post": "Location address 2",
        "p_locate": "Location coordinates",
        "p_country": "France"
      },
    ]
  };

  @override
  void initState() {
    super.initState();
    // fetchPlanData();
  }
  //
  // Future<void> fetchPlanData() async {
  //   try {
  //     final data = await getPlanData(widget.planId.toString());
  //     setState(() {
  //       planData = data;
  //     });
  //   } catch (e) {
  //     print('Failed to load plan: $e');
  //   }
  // }
  //
  // Future<Map<String, dynamic>> getPlanData(String planId) async {
  //   final response = await http.get(
  //     Uri.parse('http://3.38.99.234:8080/api/plan?id=${planId.toString()}'),
  //   );
  //
  //   if (response.statusCode == 200) {
  //     return jsonDecode(response.body);
  //   } else {
  //     print(response.statusCode);
  //     throw Exception('Failed to load plan');
  //   }
  // }


  Widget build(BuildContext context) {
    if (dummyData == null) {
      return Center(child: CircularProgressIndicator());
    } else {
      var plan = dummyData!['plan'];
      List<Map<String, dynamic>> pplan = List<Map<String, dynamic>>.from(
          dummyData!['pplan']);

      var pplanGroupedByDay = groupBy<Map<String, dynamic>, String>(
          pplan, (obj) => obj['day']);

      var days = pplanGroupedByDay.keys.length;
      var nights = days - 1;

      // var plan = dummyData['plan'];
      //List<Map<String, dynamic>> pplan = List<Map<String, dynamic>>.from(dummyData['pplan']);

      //var pplanGroupedByDay = groupBy<Map<String, dynamic>, String>(pplan, (obj) => obj['day']);

      // var days = pplanGroupedByDay.keys.length;
      // var nights = days - 1;

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
        body: Padding(
          padding: const EdgeInsets.only(
              top: 10.0, left: 20.0, right: 20.0, bottom: 10.0),
          child: ListView(
            children: [
              Text(
                plan['p_title'],
                style: TextStyle(
                  fontSize: 30,
                  fontWeight: FontWeight.w600,
                ),
              ),
              SizedBox(height: 12),
              Align(
                alignment: Alignment.centerLeft,
                child: Text(
                  '작성자: ${plan['user']['id']}',
                  style: TextStyle(
                      fontSize: 15,
                      fontWeight: FontWeight.w600
                  ),
                ),
              ),
              SizedBox(height: 5),
              Align(
                alignment: Alignment.centerLeft,
                child: Text(
                  '$nights박 $days일',
                  style: TextStyle(
                      fontSize: 15,
                      fontWeight: FontWeight.w600
                  ),
                ),
              ),
              SizedBox(height: 5),
              Wrap(
                spacing: 8.0,
                runSpacing: 4.0,
                children: List<Widget>.generate(
                  plan['tags'].length,
                      (int index) {
                    return Chip(
                      label: Text(
                        '${plan['tags'][index]}',
                        style: const TextStyle(color: Color(0xff6DA5FA)),
                      ),
                      backgroundColor: Colors.transparent,
                      side: const BorderSide(
                          color: Color(0xff6DA5FA), width: 1),
                    );
                  },
                ),
              ),
              SizedBox(height: 30),
              for (var key in pplanGroupedByDay.keys) ...[
                Center(
                  child: Text(
                    'Day $key',
                    style: TextStyle(
                      fontSize: 23,
                      fontWeight: FontWeight.w500,
                      color: Color(0xff4790FC),
                    ),
                  ),
                ),
                SizedBox(height: 30),
                for (var item in pplanGroupedByDay[key]!) ...[
                  Center(
                    child: Text(
                      '${item['p_name']}',
                      style: TextStyle(
                          fontSize: 20,
                          fontWeight: FontWeight.w400
                      ),
                      textAlign: TextAlign.center, // Center align text
                    ),
                  ),
                  Center(
                    child: Text(
                      '${item['p_post']}',
                      style: TextStyle(
                        fontSize: 15,
                        color: Color(0xffB4B4B4),
                      ),
                      textAlign: TextAlign.center, // Center align text
                    ),
                  ),
                  SizedBox(
                      height: 10
                  ),
                  if (item != pplanGroupedByDay[key]!.last) DottedLine(),
                  // Add a DottedLine if it's not the last item
                ],
                SizedBox(height: 40), // day 경계
              ],

              Text(
                  "Comments",
                  style: TextStyle(
                    fontSize: 25,
                    fontWeight: FontWeight.w400,
                  )
              ),
              SizedBox(height: 12),
              Container(
                padding: const EdgeInsets.all(8.0),
                // decoration: BoxDecoration(
                //   color: Color(0xff9CC4FF),
                //   borderRadius: BorderRadius.circular(8.0),
                // ),
                child: Text(
                  plan['p_comment'],
                  style: TextStyle(
                    fontSize: 14,
                    fontWeight: FontWeight.w500,
                    // color: Colors.white
                  ),
                ),
              ),
            ],
          ),
        ),
        bottomNavigationBar: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              Flexible(
                flex: 1,
                fit: FlexFit.tight,
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    IconButton(
                      icon: Icon(Icons.favorite,
                          color: isLiked ? Color(0xffFA6D6D) : Colors.grey),
                      onPressed: () {
                        setState(() {
                          isLiked = !isLiked;
                          if (isLiked) {
                            plan['p_likes'] += 1;
                          } else {
                            plan['p_likes'] -= 1;
                          }
                        });
                      },
                    ),
                    Text(plan['p_likes'].toString()),
                  ],
                ),
              ),
              Flexible(
                flex: 4,
                fit: FlexFit.tight,
                child: ElevatedButton.icon(
                  style: ElevatedButton.styleFrom(
                    backgroundColor: signatureColor,
                  ),
                  icon: Icon(Icons.share),
                  label: Text('Share'),
                  onPressed: () {
                    Share.share('https://www.url');
                  },
                ),
              ),
            ],
          ),
        ),

      );
    }
  }
}


class DottedLine extends StatelessWidget {
  final double dashWidth;
  final double dashHeight;
  final Color color;

  DottedLine({this.dashWidth = 3.0, this.dashHeight = 1.0, this.color = Colors.black});

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (BuildContext context, BoxConstraints constraints) {
        final boxWidth = constraints.constrainWidth();
        final dashCount = (boxWidth / (16 * dashWidth)).floor();
        return Flex(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          direction: Axis.vertical,
          children: List.generate(dashCount, (_) {
            return Padding(
              padding: const EdgeInsets.symmetric(vertical: 2.0), // add padding to increase spacing
              child: SizedBox(
                width: dashWidth,
                height: dashHeight,
                child: DecoratedBox(
                  decoration: BoxDecoration(color: color),
                ),
              ),
            );
          }),
        );
      },
    );
  }
}

