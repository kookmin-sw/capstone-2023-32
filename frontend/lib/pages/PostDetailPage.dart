import 'package:flutter/material.dart';
import 'package:collection/collection.dart';
import 'package:share/share.dart';
import 'package:fasttrip/style.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'dart:convert' as convert;

class PostDetailPage extends StatefulWidget {
  final String planId;

  PostDetailPage({Key? key, required this.planId}) : super(key: key);

  @override
  State<PostDetailPage> createState() => _PostDetailPageState();
}


class _PostDetailPageState extends State<PostDetailPage> {
  bool isLiked = false;
  Map<String, dynamic>? planData;

  @override
  void initState() {
    super.initState();
    fetchPlanData();
  }

  Future<void> fetchPlanData() async {
    try {
      final data = await getPlanData(widget.planId);
      setState(() {
        planData = data;
      });
    } catch (e) {
      print('Failed to load plan: $e');
    }
  }

  Future<Map<String, dynamic>> getPlanData(String planId) async {
    final response = await http.get(
      Uri.parse('http://3.38.99.234:8080/api/plan?id=${planId}'),
    );

    if (response.statusCode == 200) {
      String body = convert.utf8.decode(response.bodyBytes);
      Map<String, dynamic> data = convert.jsonDecode(body);
      convert.JsonEncoder encoder = convert.JsonEncoder.withIndent('  ');
      String prettyprint = encoder.convert(data);
      print('Received data: $prettyprint');  // Output received data
      return data;
    } else {
      print(response.statusCode);
      throw Exception('Failed to load plan');
    }
  }


  Widget build(BuildContext context) {
    if (planData == null) {
      return Center(child: CircularProgressIndicator());
    } else {
      var plan = planData!['plan'];
      List<Map<String, dynamic>> pplan = List<Map<String, dynamic>>.from(
          planData!['pplan']);

      var pplanGroupedByDay = groupBy<Map<String, dynamic>, int>(
        pplan,
            (obj) => obj['day'] as int,
      );


      var days = pplanGroupedByDay.keys.length;
      var nights = days - 1;

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
                plan['title'] ?? '',
                style: TextStyle(
                  fontSize: 30,
                  fontWeight: FontWeight.w600,
                ),
              ),
              SizedBox(height: 12),
              Align(
                alignment: Alignment.centerLeft,
                child: Text(
                  '작성자: ${plan['userId'] ?? ''}',
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
                  '${nights.toString()}박 ${days.toString()}일',
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
                        '${plan['tags'][index] ?? ''}',
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
                        fontWeight: FontWeight.w400,
                      ),
                      textAlign: TextAlign.center,
                    ),
                  ),
                  Center(
                    child: Text(
                      '${item['p_post']}',
                      style: TextStyle(
                        fontSize: 15,
                        color: Color(0xffB4B4B4),
                      ),
                      textAlign: TextAlign.center,
                    ),
                  ),
                  SizedBox(height: 10),
                  if (item != pplanGroupedByDay[key]!.last) DottedLine(),
                ],
                SizedBox(height: 40),
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
                  plan['comment'] ?? '',
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
                            plan['like'] += 1;
                          } else {
                            plan['like'] -= 1;
                          }
                        });
                      },
                    ),
                    Text(plan['like'].toString()),
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

