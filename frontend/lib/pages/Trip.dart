import 'package:fasttrip/Data/PostData.dart';
import 'package:fasttrip/pages/MakePlan.dart';
import 'package:fasttrip/pages/PostDetailPage.dart';
import 'package:flutter/material.dart';
import 'package:fasttrip/style.dart';

class TripPage extends StatefulWidget {
  const TripPage({Key? key}) : super(key: key);

  @override
  State<TripPage> createState() => _TripPageState();
}

class _TripPageState extends State<TripPage> {
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
            MyTravelList(),
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

class Travel {
  final String title;
  final DateTime lastModifiedDate;

  Travel({required this.title, required this.lastModifiedDate});
}

class MyTravelList extends StatelessWidget {
  final bool showAll;

  const MyTravelList({Key? key, this.showAll = false}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final List<Travel> travels = [
      Travel(title: 'Trip 1', lastModifiedDate: DateTime.now()),
      Travel(
          title: 'Trip 2',
          lastModifiedDate: DateTime.now().subtract(const Duration(days: 1))),
    ];

    return Column(
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children:
          List.generate(travels.length < 3 ? travels.length + 1 : 3, (index) {
        if ((showAll || index < 3) && index < travels.length) {
          return Padding(
            padding:
                const EdgeInsets.only(left: 20.0, right: 20.0, bottom: 10.0),
            child: InkWell(
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) =>
                        TravelDetailPage(travel: travels[index]),
                  ),
                );
              },
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
                        travels[index].title,
                        style: const TextStyle(
                            fontSize: 18, fontWeight: FontWeight.bold),
                      ),
                      const SizedBox(height: 10),
                      Text(
                        '최근수정일: ${travels[index].lastModifiedDate}',
                        style: TextStyle(
                            fontSize: 14, color: Colors.grey.shade600),
                      ),
                    ],
                  ),
                ),
              ),
            ),
          );
        } else if (travels.length < 3) {
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

class HeartList extends StatefulWidget {
  const HeartList({Key? key}) : super(key: key);

  @override
  State<HeartList> createState() => _HeartListState();
}

class _HeartListState extends State<HeartList> {
  @override
  Widget build(BuildContext context) {
    final filteredData = data.where((post) => post.heart).toList();

    return Column(
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: List.generate(filteredData.length, (index) {
        return InkWell(
          onTap: () {
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => PostDetailPage(planId: "",),
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
                        filteredData[index].imageUrl,
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
                            if (filteredData[index].heart) {
                              filteredData[index].heart = false;
                            } else {
                              filteredData[index].heart = true;
                            }
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
                  filteredData[index].title,
                  style: const TextStyle(
                      fontSize: 16, fontWeight: FontWeight.bold),
                ),
                const SizedBox(height: 10.0),
                Wrap(
                  spacing: 8.0,
                  children: filteredData[index]
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
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('All Travel Plans'),
      ),
      body: const MyTravelList(showAll: true),
    );
  }
}

// 게시물 상세페이지 (임시)
class TravelDetailPage extends StatelessWidget {
  final Travel travel;

  const TravelDetailPage({Key? key, required this.travel}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(travel.title),
      ),
      body: Center(
        child: Text('Travel details for ${travel.title}'),
      ),
    );
  }
}
