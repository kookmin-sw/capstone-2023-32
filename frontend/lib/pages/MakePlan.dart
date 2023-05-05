import 'package:fasttrip/pages/Search.dart';
import 'package:fasttrip/style.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

var subTitle = TextStyle(fontSize: 18, fontWeight: FontWeight.bold);

class MakePlan extends StatelessWidget {
  const MakePlan({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        elevation: 0,
        backgroundColor: Colors.transparent,
        shadowColor: Colors.transparent,
        leading: IconButton(
          icon: Icon(Icons.arrow_back, color: Colors.black,),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
      ),
      body: MakePage(),
    );
  }
}

class MakePage extends StatefulWidget {

  const MakePage({Key? key}) : super(key: key);

  @override
  State<MakePage> createState() => _MakePageState();
}

class _MakePageState extends State<MakePage> {
  final TextEditingController _controller = TextEditingController();
  List<String> tags = [];
  TextEditingController tagController = TextEditingController();
  DateTimeRange? _dateRange;
  String title = '';

  void fetchData() {
    setState(() {
      title = _controller.text;
    });
    print(title);
  }

  void _selectDateRange(BuildContext context) async {
    final DateTimeRange? picked = await showDateRangePicker(
      context: context,
      firstDate: DateTime.now().subtract(Duration(days: 365)),
      lastDate: DateTime.now().add(Duration(days: 365)),
      initialDateRange: _dateRange,
    );
    if (picked != null && picked != _dateRange) {
      setState(() {
        _dateRange = picked;
      });
    }
  }


  @override
  Widget build(BuildContext context) {
    return ListView(
      children: <Widget>[

        // ** 제목 **
        Column(
          children: [
            Padding(
              padding: const EdgeInsets.only(left: 20.0, right:20.0),
              child: Align(
                  alignment: Alignment.centerLeft,
                  child: Text('제목', style: subTitle,)
              ),
            ),
            Container(
              height: 50,
              margin: EdgeInsets.only(left: 20.0, right:20.0, top: 10),
              padding: EdgeInsets.all(10.0),
              decoration: BoxDecoration(
                border: Border.all(color: Colors.grey),
                borderRadius: BorderRadius.circular(10.0),
              ),
              child: TextField(
                controller: _controller,
                decoration: InputDecoration(
                  border: InputBorder.none,
                  hintText: '제목을 입력해주세요.',
                  hintStyle: TextStyle(color: Colors.grey),
                ),
                onChanged: (value) {
                },
              ),
            ),
          ],
        ),

        // ** 태그 추가 **
        Column(
          children: [
            Padding(
              padding: const EdgeInsets.only(left: 20.0, right:20.0, top: 20.0),
              child: Align(
                  alignment: Alignment.centerLeft,
                  child: Text('태그', style: subTitle,)
              ),
            ),
            Container(
              height: 50,
              margin: EdgeInsets.only(left: 20.0, right:20.0, top: 10.0, bottom: 20.0),
              padding: EdgeInsets.all(10.0),
              decoration: BoxDecoration(
                border: Border.all(color: Colors.grey),
                borderRadius: BorderRadius.circular(10.0),
              ),
              child: Row(
                children: [
                  Expanded(
                    child: TextField(
                      controller: tagController,
                      decoration: InputDecoration(
                        border: InputBorder.none,
                        hintText: '태그를 추가해주세요.',
                        hintStyle: TextStyle(color: Colors.grey),
                      ),
                    ),
                  ),
                  ElevatedButton(
                      style: ButtonStyle(
                        backgroundColor: MaterialStateProperty.all(signatureColor),
                        foregroundColor: MaterialStateProperty.all(Colors.white), // 텍스트 색상
                        shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                          RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(10),
                          ),
                        ),
                      ),
                      onPressed: () {
                        if (tags.isEmpty && tagController.text != '여행' && tagController.text != '모집')
                        {
                          showDialog(
                            context: context,
                            builder: (BuildContext context) {
                              return Dialog(
                                child: SpeechBubble(
                                  message: '첫 번째 태그는 여행 또는 모집으로 해주세요.',
                                ),
                              );
                            },
                          );
                        } else{
                          setState(() {
                            tags.add(tagController.text);
                            tagController.clear();
                          });
                        }
                      },
                      child: Text('태그 추가')
                  ),
                ],
              ),
            ),
            Container(
              margin:EdgeInsets.only(bottom:20.0),
              child: Wrap(
                alignment: WrapAlignment.start,
                spacing: 8.0,
                children: tags.map((tag) {
                  return Chip(
                    deleteIcon: Icon(
                      Icons.close,
                      size: 18.0,
                      color: Color(0xff6DA5FA),),
                    onDeleted: (){
                      setState(() {
                        tags.remove(tag);
                      });
                    },
                    label:Text(
                      tag,
                      style: const TextStyle(color: Color(0xff6DA5FA)),
                    ),
                    backgroundColor: Colors.transparent,
                    side: const BorderSide(color: Color(0xff6DA5FA), width: 1),
                  );
                }).toList(),
              ),
            ),
          ],
        ),


        // ** 계획 작성 **
        Container(
          margin: EdgeInsets.only(left:20.0, right:20.0, bottom:20.0),
          child:
          Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Align(
                alignment: Alignment.center,
                child: Text(
                  _dateRange == null
                      ? ''
                      : '${DateFormat('MM/dd/yyyy').format(_dateRange!.start)} - ${DateFormat('MM/dd/yyyy').format(_dateRange!.end)}',
                  style: TextStyle(fontSize: 16,),
                ),
              ),
              SizedBox(
                  height: 20
              ),
              Align(
                alignment: Alignment.center,
                child: OutlinedButton(
                  child: Text('일정 선택하기', style:TextStyle(color: Color(0xff6DA5FA))),
                  onPressed: () => _selectDateRange(context),
                ),
              ),
              SizedBox(
                  height: 20
              ),
              Text(
                  _dateRange == null
                      ? ''
                      : '${_dateRange!.end.difference(_dateRange!.start).inDays}박 ${_dateRange!.end.difference(_dateRange!.start).inDays + 1}일',
                  style: subTitle
              ),
              SizedBox(
                height: 20,
              ),

              // ** 1일차~n일차 **
              if (_dateRange != null)
                for (int i = 1; i <= _dateRange!.end.difference(_dateRange!.start).inDays + 1; i++)
                  Column(
                    children: [
                      Container(
                        child: Column(
                          children: [
                            Text('$i일차', style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold,)),
                            SizedBox(height:8),
                            TextButton(
                              onPressed: () {
                                Navigator.push(context, MaterialPageRoute(builder: (context) => SearchPage()));
                              },
                              child: Text('+', style: TextStyle(fontSize: 20, color: signatureColor),),
                            ),
                            SizedBox(
                              height: 15,
                            )
                          ],
                        ),
                      ),
                    ],
                  ),
              if (_dateRange != null)
                PostRequest(fetchData: fetchData),
            ],
          ),
        )
      ],
    );
  }
}


class SpeechBubble extends StatelessWidget {
  final String message;

  SpeechBubble({required this.message});

  @override
  Widget build(BuildContext context) {
    return Material(
      elevation: 2,
      color: Colors.red,
      child: Container(
        padding: EdgeInsets.all(10),
        child: Text(
          message,
          style: TextStyle(
            color: Colors.white,
          ),
        ),
      ),
    );
  }
}

// ** 데이터 보내기 (저장 및 공유 버튼)**
class PostRequest extends StatefulWidget {
  final VoidCallback fetchData;
  const PostRequest({Key? key, required this.fetchData}) : super(key: key);

  @override
  State<PostRequest> createState() => _PostRequestState();
}

Future<void> sendData() async {
  var url = 'http://3.38.99.234:8080/api/plan';

  var response = await http.post(
    Uri.parse(url),
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: jsonEncode(<String, dynamic>{
      'expense': 30,
      'days': 4,
      'purpose': [
        'activity',
        'tourism'
      ],
    }),
  );

  print(response.statusCode);

  if (response.statusCode == 200) {
    List<dynamic> responseBody = jsonDecode(response.body);
    responseBody.forEach((element) {
      print('Plan ID: ${element['planId']}');
      print('Star: ${element['star']}');
      print('User ID: ${element['userId']}');
      print('Purpose: ${element['purpose']}');
      print('------------------------');
    });
  } else {
    throw Exception('post error');
  }
}

// Future<void> fetchData() async {
//   var url = 'http://3.38.99.234:8080/api/plan';
//
//   var response = await http.get(
//     Uri.parse(url),
//   );
//
//   if (response.statusCode == 200) {
//     Map<String, dynamic> responseBody = jsonDecode(response.body);
//
//     print('Plan ID: ${responseBody['planId']}');
//     print('Star: ${responseBody['star']}');
//     print('User ID: ${responseBody['userId']}');
//     print('Purpose: ${responseBody['purpose']}');
//
//     List<dynamic> plans = responseBody['plan'];
//     plans.forEach((plan) {
//       print('Day: ${plan['day']}');
//       print('Destination: ${plan['destination']}');
//       print('Destination ID: ${plan['destinationId']}');
//       print('Time: ${plan['time']}');
//       print('Review: ${plan['review']}');
//       print('Coordinates: ${plan['cord']}');
//       print('Filter: ${plan['filter']}');
//       print('------------------------');
//     });
//   } else {
//     throw Exception('fetch error');
//   }
// }

class _PostRequestState extends State<PostRequest> {

  @override
  Widget build(BuildContext context) {
    return Align(
      alignment: Alignment.center,
      child: Container(
        margin: EdgeInsets.only(top: 20.0),
        width: MediaQuery.of(context).size.width * 0.9,
        child: ElevatedButton(
          style: ButtonStyle(
            backgroundColor: MaterialStateProperty.all(signatureColor),
            foregroundColor: MaterialStateProperty.all(Colors.white), // 텍스트 색상
            shape: MaterialStateProperty.all<RoundedRectangleBorder>(
              RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(10),
              ),
            ),
          ),
          onPressed: () {
            widget.fetchData();
            sendData();
          },
          child: SizedBox(
              width: double.infinity,
              height: 50,
              child: Align(
                  alignment: Alignment.center,
                  child: Text('저장 하기', style: TextStyle(fontSize: 15, fontWeight: FontWeight.bold), ))
          ),
        ),
      ),
    );
  }
}
