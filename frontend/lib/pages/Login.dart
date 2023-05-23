import 'package:flutter/material.dart';
import 'package:fasttrip/pages/Signup.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:provider/provider.dart';
import '../token_model.dart';

Future<String> sendPostRequest(String id) async {
  final response = await http.post(
    Uri.parse('http://3.38.99.234:8080/api/user/testUser'),
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: jsonEncode(<String, String>{
      'id': id,
    }),
  );

  if (response.statusCode == 200) {
    return response.body ?? '';
  } else {
    print(response.statusCode);
    throw Exception('Failed to load post');
  }
}

class LogIn extends StatefulWidget {
  const LogIn({Key? key}) : super(key: key);

  @override
  State<LogIn> createState() => _LogInState();
}

class _LogInState extends State<LogIn> {

  final idController = TextEditingController();
  final passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.white,
        body: Padding(
          padding: const EdgeInsets.only(top:50.0, left:20.0, right:20.0, bottom: 30.0),
          child: GestureDetector(
            onTap: () {
              FocusScope.of(context).unfocus();
            },
            child: SingleChildScrollView(
              child: Column(
                children: [
                  const SizedBox(
                    height: 90,
                  ),
                  Align(
                    alignment: Alignment.centerLeft,
                    child: const Text(
                      '여행은 쉽고 빠르게,\n페스츄리에 어서오세요.',
                      style: TextStyle(
                        fontSize: 25,
                        color: Colors.black,
                        fontWeight: FontWeight.w500,
                      ),
                    ),
                  ),
                  const SizedBox(
                    height: 65,
                  ),
                  Align(
                    alignment: Alignment.centerLeft,
                    child: Text(
                      '아이디',
                      style: TextStyle(
                        fontSize: 16,
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  TextField(
                    controller: idController,
                    decoration: InputDecoration(
                      hintText: '아이디를 입력해주세요.',
                    ),
                    keyboardType: TextInputType.text,
                  ),
                  const SizedBox(
                    height: 30,
                  ),
                  Align(
                    alignment: Alignment.centerLeft,
                    child: Text(
                      '비밀번호',
                      style: TextStyle(
                        fontSize: 16,
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  TextField(
                    controller: passwordController,
                    decoration: InputDecoration(
                      hintText: '비밀번호를 입력해주세요.',
                    ),
                    keyboardType: TextInputType.text,
                    obscureText: true,
                  ),
                  const SizedBox(
                    height: 38,
                  ),
                  Padding(
                    padding: const EdgeInsets.only(left: 25.0, right: 45.0, top: 10.0, bottom:10.0),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        TextButton(
                          onPressed: () {},
                          style: TextButton.styleFrom(
                            minimumSize: Size.zero,
                            padding: EdgeInsets.zero,
                            tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                          ),
                          child: const Text(
                            '아이디 찾기',
                            style: TextStyle(
                              fontSize: 12,
                              color: Color(0xFFB4B4B4),
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                        TextButton(
                          onPressed: () {},
                          style: TextButton.styleFrom(
                            minimumSize: Size.zero,
                            padding: EdgeInsets.zero,
                            tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                          ),
                          child: const Text(
                            '비밀번호 찾기',
                            style: TextStyle(
                              fontSize: 12,
                              color: Color(0xFFB4B4B4),
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                        TextButton(
                          onPressed: () {
                            Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) => const SignUp()),
                            );
                          },
                          style: TextButton.styleFrom(
                            minimumSize: Size.zero,
                            padding: EdgeInsets.zero,
                            tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                          ),
                          child: const Text(
                            '회원가입',
                            style: TextStyle(
                              fontSize: 12,
                              color: Color(0xFFB4B4B4),
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 200),
                  Align(
                    alignment: Alignment.bottomCenter,
                    child: Container(
                      width: MediaQuery.of(context).size.width * 0.9,
                      child: ElevatedButton(
                        onPressed: () async {
                          final enteredId = idController.text;
                          String token = await sendPostRequest(enteredId);
                          print(enteredId);
                          print('Received token: $token');
                          Provider.of<TokenModel>(context, listen: false).token = token;
                        },
                        style: ElevatedButton.styleFrom(
                          elevation: 0,
                          backgroundColor: const Color(0xFF9CC4FF),
                          padding: const EdgeInsets.symmetric(
                            vertical: 15,
                          ),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(100),
                          ),
                        ),
                        child: const Text(
                          '로그인',
                          style: TextStyle(
                            color: Colors.white,
                            fontSize: 18,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      )
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}