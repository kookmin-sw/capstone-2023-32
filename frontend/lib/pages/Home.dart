import 'package:fasttrip/pages/Search.dart';
import 'package:flutter/material.dart';
import 'package:fasttrip/pages/Login.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'dart:math';
import 'package:http/http.dart' as http;
import 'dart:convert';

class HomePage extends StatefulWidget {
  const HomePage({
    Key? key,
  }) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int num = -1;
  List<String> foodName = [];
  List<String> foodImage = [];
  List<String> foodCal = [];
  List<String> foodDishlist = [];

  String _nickname = '게스트';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Container(
          decoration: BoxDecoration(
            color: Colors.white,
            image: DecorationImage(
              image: AssetImage(
                'assets/images/background_home.jpg',
              ),
              fit: BoxFit.cover,
              opacity: 0.8,
            ),
          ),
          child: Padding(
            padding: const EdgeInsets.all(20),
            child: Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Image.asset(
                      'assets/icons/logo.png',
                      width: 50,
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.end,
                      children: [
                        IconButton(
                          onPressed: () {
                            Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) => const SearchPage()),
                            );
                          },
                          icon: const Icon(Icons.search),
                          iconSize: 20,
                          color: Colors.black,
                        ),
                      ],
                    ),
                  ],
                ),
                const SizedBox(
                  height: 140,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    SvgPicture.asset(
                      'assets/icons/plane_trip_icon.svg',
                    ),
                    const SizedBox(
                      width: 10,
                    ),
                    Text(
                      _nickname,
                      style: TextStyle(
                        fontSize: 22,
                        color: Colors.white,
                        fontWeight: FontWeight.w600,
                        shadows: <Shadow>[
                          Shadow(
                            color: Colors.black.withOpacity(0.8),
                            offset: Offset(0, 1),
                            blurRadius: 3,
                          )
                        ],
                      ),
                    ),
                    Text(
                      ' 님',
                      style: TextStyle(
                        fontSize: 17,
                        color: Colors.black,
                        fontWeight: FontWeight.w600,
                        shadows: <Shadow>[
                          Shadow(
                            color: Colors.white.withOpacity(0.6),
                            offset: Offset(0, 1),
                            blurRadius: 3,
                          )
                        ],
                      ),
                    ),
                    const SizedBox(
                      width: 3,
                    ),
                    IconButton(
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => const LogIn()),
                        );
                      },
                      icon: const Icon(Icons.login),
                      iconSize: 20,
                      color: Colors.black,
                    ),
                  ],
                ),
                const SizedBox(
                  height: 40,
                ),
                Row(
                  children: [
                    Text(
                      '오늘의',
                      style: TextStyle(
                        fontSize: 20,
                        color: Colors.black,
                        fontWeight: FontWeight.w600,
                        shadows: <Shadow>[
                          Shadow(
                            color: Colors.white.withOpacity(0.8),
                            offset: Offset(0, 1),
                            blurRadius: 3,
                          )
                        ],
                      ),
                    ),
                    SizedBox(
                      width: 7,
                    ),
                    Text(
                      'Food',
                      style: TextStyle(
                        fontSize: 25,
                        color: Colors.black,
                        fontWeight: FontWeight.w600,
                        shadows: <Shadow>[
                          Shadow(
                            color: Colors.white.withOpacity(0.8),
                            offset: Offset(0, 1),
                            blurRadius: 3,
                          )
                        ],
                      ),
                    ),
                  ],
                ),
                const SizedBox(
                  height: 10,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Container(
                      width: 170,
                      height: 240,
                      decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius: BorderRadius.only(
                            topLeft: Radius.circular(60),
                            topRight: Radius.circular(20),
                            bottomLeft: Radius.circular(20),
                            bottomRight: Radius.circular(20)),
                        boxShadow: [
                          BoxShadow(
                            color: Colors.grey.withOpacity(0.5),
                            spreadRadius: 0,
                            blurRadius: 8,
                            offset: Offset(0, 3),
                          ),
                        ],
                      ),
                      child: FutureBuilder(
                        future: foodRecom(1),
                        builder:
                            (BuildContext context, AsyncSnapshot snapshot) {
                          if (snapshot.hasData == false) {
                            return CircularProgressIndicator();
                          } else if (snapshot.hasError) {
                            return Padding(
                              padding: const EdgeInsets.all(8.0),
                              child: Text(
                                'Error: ${snapshot.error}',
                                style: TextStyle(fontSize: 15),
                              ),
                            );
                          } else {
                            return Padding(
                              padding: const EdgeInsets.only(
                                left: 5.0,
                                right: 5.0,
                                top: 7.0,
                              ),
                              child: Column(
                                children: [
                                  Image.network(
                                    foodImage[0],
                                    width: 120,
                                    height: 140,
                                    fit: BoxFit.none,
                                  ),
                                  const SizedBox(
                                    height: 3,
                                  ),
                                  Center(
                                    child: Text(
                                      foodName[0],
                                      style: TextStyle(
                                        fontSize: 17,
                                        fontWeight: FontWeight.w600,
                                        color: Color(0xFF329EFF),
                                      ),
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 8,
                                  ),
                                  Text(
                                    foodDishlist[0] + '...',
                                    overflow: TextOverflow.ellipsis,
                                    textAlign: TextAlign.right,
                                    style: TextStyle(
                                      fontSize: 10,
                                      fontWeight: FontWeight.w600,
                                      color: Color(0xFF6A91F5),
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 10,
                                  ),
                                  Text(
                                    foodCal[0],
                                    style: TextStyle(
                                      fontSize: 13,
                                      color: Color(0xFFB4B4B4),
                                      fontWeight: FontWeight.w600,
                                    ),
                                  ),
                                ],
                              ),
                            );
                          }
                        },
                      ),
                    ),
                    Container(
                      width: 170,
                      height: 240,
                      decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius: BorderRadius.only(
                            topLeft: Radius.circular(60),
                            topRight: Radius.circular(20),
                            bottomLeft: Radius.circular(20),
                            bottomRight: Radius.circular(20)),
                        boxShadow: [
                          BoxShadow(
                            color: Colors.grey.withOpacity(0.5),
                            spreadRadius: 0,
                            blurRadius: 8,
                            offset: Offset(0, 3),
                          ),
                        ],
                      ),
                      child: FutureBuilder(
                        future: foodRecom(2),
                        builder:
                            (BuildContext context, AsyncSnapshot snapshot) {
                          if (snapshot.hasData == false) {
                            return CircularProgressIndicator();
                          } else if (snapshot.hasError) {
                            return Padding(
                              padding: const EdgeInsets.all(8.0),
                              child: Text(
                                'Error: ${snapshot.error}',
                                style: TextStyle(fontSize: 15),
                              ),
                            );
                          } else {
                            return Padding(
                              padding: const EdgeInsets.only(
                                left: 5.0,
                                right: 5.0,
                                top: 7.0,
                              ),
                              child: Column(
                                children: [
                                  Image.network(
                                    foodImage[1],
                                    width: 120,
                                    height: 140,
                                    fit: BoxFit.none,
                                  ),
                                  const SizedBox(
                                    height: 3,
                                  ),
                                  Center(
                                    child: Text(
                                      foodName[1],
                                      style: TextStyle(
                                        fontSize: 17,
                                        fontWeight: FontWeight.w600,
                                        color: Color(0xFF329EFF),
                                      ),
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 8,
                                  ),
                                  Text(
                                    foodDishlist[1] + '...',
                                    overflow: TextOverflow.ellipsis,
                                    textAlign: TextAlign.right,
                                    style: TextStyle(
                                      fontSize: 10,
                                      fontWeight: FontWeight.w600,
                                      color: Color(0xFF6A91F5),
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 10,
                                  ),
                                  Text(
                                    foodCal[1],
                                    style: TextStyle(
                                      fontSize: 13,
                                      color: Color(0xFFB4B4B4),
                                      fontWeight: FontWeight.w600,
                                    ),
                                  ),
                                ],
                              ),
                            );
                          }
                        },
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Future foodRecom(int check) async {
    String url =
        "https://m.search.naver.com/p/csearch/content/qapirender.nhn?_callback=window.__jindo2_callback._&pkid=107&where=nexearch&q=제철";
    var response = await http.get(Uri.parse(url));
    var responseBody = response.body
        .replaceAll('window.__jindo2_callback._(', '')
        .replaceAll(');', '')
        .replaceAll('\\', '');
    Map<String, dynamic> jsonstr;

    while (true) {
      int foodLength = jsonDecode(responseBody)['data']['result']['itemList'][0]
              ['foodlist']
          .length;
      var rnd = Random().nextInt(foodLength);

      if (rnd != num) {
        num = rnd;

        jsonstr = jsonDecode(responseBody)['data']['result']['itemList'][0]
            ['tabfooddatalist'][num];
        foodName.add(jsonstr['thisfood']);
        foodImage.add(jsonstr['desc']['image_big_pc_url']);
        foodCal.add(
            '${jsonstr['desc']['calorie']}Kcal (${jsonstr['desc']['calorie_unit']}g)');
        foodDishlist.add(
            '${jsonstr['dishlist'][0]['dish_name']}, ${jsonstr['dishlist'][1]['dish_name']}');

        if (foodName.length == check &&
            foodImage.length == check &&
            foodCal.length == check &&
            foodDishlist.length == check) {
          break;
        }
      }
    }

    return jsonstr;
  }
}
