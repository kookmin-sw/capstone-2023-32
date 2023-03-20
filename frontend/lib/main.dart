import 'package:fasttrip/pages/Home.dart';
import 'package:flutter/material.dart';
import './style.dart' as theme;


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


// 하단바 커스텀
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

