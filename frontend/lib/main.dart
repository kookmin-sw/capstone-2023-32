import 'package:fasttrip/pages/Feed.dart';
import 'package:fasttrip/pages/Home.dart';
import 'package:fasttrip/pages/Mypage.dart';
import 'package:fasttrip/pages/Trip.dart';
import 'package:flutter/material.dart';
import './style.dart' as theme;
import 'package:provider/provider.dart';
import './token_model.dart';

void main() {
  runApp(
    ChangeNotifierProvider(
      create: (context) => TokenModel(),
      child: MaterialApp(theme: theme.mainTheme, home: StartUpView()),
    ),
  );
}

class StartUpView extends StatefulWidget {
  @override
  _StartUpViewState createState() => _StartUpViewState();
}

class _StartUpViewState extends State<StartUpView> {
  @override
  void initState() {
    super.initState();
    loadData();
  }

  Future<void> loadData() async {
    await Provider.of<TokenModel>(context, listen: false).loadToken();
    Navigator.of(context).pushReplacement(
      MaterialPageRoute(
        builder: (context) => MyApp(),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: CircularProgressIndicator(), // Display a loading spinner
      ),
    );
  }
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> with WidgetsBindingObserver {
  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance?.addObserver(this);
  }

  @override
  void dispose() {
    WidgetsBinding.instance?.removeObserver(this);
    super.dispose();
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) async {
    super.didChangeAppLifecycleState(state);
    if (state == AppLifecycleState.resumed) {
      await Provider.of<TokenModel>(context, listen: false).loadToken();
    }
  }

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 4,
      child: Scaffold(
        resizeToAvoidBottomInset: false,
        body: TabBarView(
          children: [
            const HomePage(),
            const TripPage(),
            FeedPage(),
            const MyPage(),
          ],
        ),
        bottomNavigationBar: const BottomBar(),
      ),
    );
  }
}

// 하단 바 커스텀
class BottomBar extends StatelessWidget {
  const BottomBar({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white, //색상
      child: Container(
        height: 70,
        padding: const EdgeInsets.only(bottom: 10, top: 5),
        child: const TabBar(
          indicatorSize: TabBarIndicatorSize.label,
          indicatorColor: Color(0xff9CC4FF),
          indicatorWeight: 4,
          labelColor: Color(0xff9CC4FF),
          unselectedLabelColor: Colors.black38,
          labelStyle: TextStyle(
            fontSize: 10,
          ),
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
            ),
          ],
        ),
      ),
    );
  }
}
