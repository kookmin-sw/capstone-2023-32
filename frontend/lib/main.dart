import 'package:flutter/material.dart';
import './style.dart' as theme;
import 'package:carousel_slider/carousel_slider.dart';

void main() {
  runApp(
      MaterialApp(
          theme: theme.mainTheme,
          home: MyApp()
      )
  );
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
        length: 4,
        child: Scaffold(
          resizeToAvoidBottomInset : false,
          body:TabBarView(
            children: [
              Home(),
              Text('222'),
              Text('피드'),
              Text('마이페이지'),
            ],
          ),
          bottomNavigationBar: BottomBar(),
        )
    );
  }
}

class BottomBar extends StatelessWidget {
  const BottomBar({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white, //색상
      child: Container(
        height: 70,
        padding: EdgeInsets.only(bottom: 10, top: 5),
        child: TabBar(
          indicatorSize: TabBarIndicatorSize.label,
          indicatorColor: Colors.lightBlue,
          indicatorWeight: 4,
          labelColor: Colors.black,
          unselectedLabelColor: Colors.black38,
          labelStyle: TextStyle(
            fontSize: 10,),

          tabs: [
            Tab(
              icon: Icon(
                Icons.home,
                size: 20,
              ),
              text: '홈',
            ),
            Tab(
              icon: Icon(Icons.airplanemode_active, size: 20),
              text: '여행',
            ),
            Tab(
              icon: Icon(
                Icons.library_books,
                size: 20,
              ),
              text: '피드',
            ),
            Tab(
              icon: Icon(
                Icons.person,
                size: 20,
              ),
              text: '마이페이지',
            )
          ],
        ),
      ),
    );
  }
}

class Home extends StatelessWidget {
  const Home({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ListView(
      children: [
        MainPage(),
        Slider(),
      ],
    );
  }
}

class MainPage extends StatelessWidget {
  const MainPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(bottom: 50),
      height: MediaQuery.of(context).size.height * 0.5, // 화면 크기의 절반
      decoration: BoxDecoration(
        image: DecorationImage(
          image: AssetImage('assets/flutter.png'), // 배경 이미지
          fit:BoxFit.cover,
        )
      ),
      child: Column(
        children: [
          Container(
            padding: EdgeInsets.all(20),
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Expanded (
                  child: Image.asset(   // 로고이미지
                    'assets/flutter.png',
                    width: 200,
                    height: 100,
                    alignment: Alignment.topLeft,
                  ),
                ),
                IconButton(
                  onPressed: (){},
                  icon: Icon(Icons.search),
                  alignment: Alignment.topRight
                ),
                IconButton(
                    onPressed: (){},
                    icon: Icon(Icons.notifications),
                    alignment: Alignment.topRight
                ),
              ],
            ),
          ),
          Container(
            height: 180,    // 반응형 디자인 적용 필요
            alignment: Alignment.bottomCenter,
            child: ElevatedButton(
                onPressed: (){},
                child: Text('새로운 Plan 시작하기')
            ),
          ),
        ],
      ),

    );
  }
}

class Slider extends StatefulWidget {
  const Slider({Key? key}) : super(key: key);


  @override
  State<Slider> createState() => _SliderState();
}

var heading1 = TextStyle(
  fontWeight: FontWeight.bold,
  fontSize: 20,
);

class _SliderState extends State<Slider> {

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(bottom: 50),
      child: Column(
        children: [
          Container(
            padding: EdgeInsets.only(left: 10),
            margin: EdgeInsets.only(bottom: 20),
            alignment: Alignment.topLeft,
            child: Text(
                '오늘의 Place',
              textAlign: TextAlign.left,
              style: heading1,
            ),
          ),
          CarouselSlider(
            options: CarouselOptions(
              height: 200.0,
              autoPlay: true,
            ),
            items: [1,2,3,4,5].map((i) {
              return Builder(
                builder: (BuildContext context) {
                  return Container(
                      width: MediaQuery.of(context).size.width,
                      margin: EdgeInsets.symmetric(horizontal: 5.0),
                      decoration: BoxDecoration(
                          border: Border.all(
                            width: 3,
                            color: Colors.grey,
                          ),
                        borderRadius: BorderRadius.circular(16.0),
                      ),
                      child: ClipRRect(
                        borderRadius: BorderRadius.circular(16.0),
                        child: Image.asset(
                          'assets/flutter.png',
                          fit: BoxFit.fill,
                        )
                      )
                  );
                },
              );
            }).toList(),
          ),
          Text('설명글'),
        ],
      ),
    );
  }
}



