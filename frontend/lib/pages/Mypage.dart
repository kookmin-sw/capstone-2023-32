import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:flutter_email_sender/flutter_email_sender.dart';
import 'dart:io';

class MyPage extends StatefulWidget {
  const MyPage({Key? key}) : super(key: key);

  @override
  State<MyPage> createState() => _MyPageState();
}

class _MyPageState extends State<MyPage> {
  File? _imageFile;
  final _picker = ImagePicker();
  final String _nickname = '게스트';
  final int _age = 22;
  final String _gender = '남';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.white,
        body: Container(
          decoration: BoxDecoration(
            color: Colors.white,
            image: DecorationImage(
              image: AssetImage(
                'assets/images/background_mypage.jpg',
              ),
              fit: BoxFit.cover,
              opacity: 0.8,
            ),
          ),
          child: Column(
            children: [
              Container(
                padding: const EdgeInsets.only(
                  top: 50.0,
                  left: 20.0,
                  right: 20.0,
                ),
                alignment: Alignment.centerLeft,
                child: Text(
                  '마이페이지',
                  style: TextStyle(
                    fontSize: 25,
                    fontWeight: FontWeight.bold,
                    fontFamily: "SUITE",
                  ),
                  textAlign: TextAlign.left,
                ),
              ),
              SizedBox(
                height: 30,
              ),
              Padding(
                padding: const EdgeInsets.all(40),
                child: Column(
                  children: [
                    imageProfile(),
                    const SizedBox(
                      height: 20,
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text(
                          _nickname,
                          style: const TextStyle(
                            color: Colors.black,
                            fontSize: 25,
                            fontWeight: FontWeight.w900,
                          ),
                        ),
                        const SizedBox(
                          width: 10,
                        ),
                        IconButton(
                          onPressed: () {},
                          icon: const Icon(
                            Icons.edit,
                            color: Colors.grey,
                            size: 22,
                          ),
                          padding: EdgeInsets.zero,
                          constraints: const BoxConstraints(),
                        ),
                      ],
                    ),
                    Text(
                      '$_age / $_gender',
                      style: const TextStyle(
                        color: Colors.black,
                        fontSize: 18,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                    const SizedBox(
                      height: 110,
                    ),
                    InkWell(
                      onTap: () {},
                      child: Container(
                        padding: const EdgeInsets.symmetric(
                          horizontal: 20,
                          vertical: 11,
                        ),
                        width: 400,
                        height: 55,
                        decoration: BoxDecoration(
                          color: Colors.white,
                          border: Border.all(
                            color: const Color(0xFF9CC4FF),
                          ),
                          borderRadius: BorderRadius.circular(15),
                          boxShadow: [
                            BoxShadow(
                              color: Colors.grey.withOpacity(0.5),
                              spreadRadius: 0,
                              blurRadius: 3,
                              offset: Offset(0, 3),
                            ),
                          ],
                        ),
                        child: Row(
                          children: [
                            Icon(
                              Icons.manage_accounts,
                              color: Color(0xFF329EFF),
                            ),
                            SizedBox(
                              width: 10,
                            ),
                            Text(
                              '내 정보',
                              style: const TextStyle(
                                fontSize: 18,
                                color: Color(0xFF329EFF),
                                fontWeight: FontWeight.w600,
                              ),
                              textAlign: TextAlign.start,
                            ),
                          ],
                        ),
                      ),
                    ),
                    const SizedBox(
                      height: 15,
                    ),
                    InkWell(
                      onTap: () {
                        Inquiry(context);
                      },
                      child: Container(
                        padding: const EdgeInsets.symmetric(
                          horizontal: 20,
                          vertical: 11,
                        ),
                        width: 400,
                        height: 55,
                        decoration: BoxDecoration(
                          color: Colors.white,
                          border: Border.all(
                            color: const Color(0xFF9CC4FF),
                          ),
                          borderRadius: BorderRadius.circular(15),
                          boxShadow: [
                            BoxShadow(
                              color: Colors.grey.withOpacity(0.5),
                              spreadRadius: 0,
                              blurRadius: 3,
                              offset: Offset(0, 3),
                            ),
                          ],
                        ),
                        child: Row(
                          children: [
                            Icon(
                              Icons.question_answer,
                              color: Color(0xFF329EFF),
                            ),
                            SizedBox(
                              width: 10,
                            ),
                            Text(
                              '문의하기',
                              style: const TextStyle(
                                fontSize: 18,
                                color: Color(0xFF329EFF),
                                fontWeight: FontWeight.w600,
                              ),
                              textAlign: TextAlign.start,
                            ),
                          ],
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget imageProfile() {
    return Stack(
      children: [
        CircleAvatar(
          radius: 50,
          backgroundImage: _imageFile == null
              ? const AssetImage('assets/images/profile.png') as ImageProvider
              : FileImage(File(_imageFile!.path)),
        ),
        Positioned(
          bottom: 0,
          right: 0,
          child: InkWell(
            onTap: () {
              bottomSheet(context);
            },
            child: const Icon(
              Icons.camera_alt,
              color: Colors.white,
              size: 40,
              shadows: [
                Shadow(
                  color: Colors.black,
                  blurRadius: 5,
                )
              ],
            ),
          ),
        ),
      ],
    );
  }

  Future<dynamic> bottomSheet(BuildContext context) {
    return showDialog(
      context: context,
      builder: (BuildContext context) => AlertDialog(
        actions: <Widget>[
          Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              TextButton(
                onPressed: () {
                  getImage(ImageSource.gallery);
                  Navigator.of(context).pop();
                },
                child: const Text(
                  '앨범에서 사진 선택',
                  style: TextStyle(
                    fontSize: 20,
                    color: Color(0xFF329EFF),
                  ),
                ),
              ),
              const Divider(
                height: 1,
              ),
              TextButton(
                onPressed: () {
                  getImage(ImageSource.camera);
                  Navigator.of(context).pop();
                },
                child: const Text(
                  '카메라 불러오기',
                  style: TextStyle(
                    fontSize: 20,
                    color: Color(0xFF329EFF),
                  ),
                ),
              ),
              const Divider(
                height: 1,
              ),
              TextButton(
                onPressed: () {
                  setState(() {
                    _imageFile = null;
                  });
                  Navigator.of(context).pop();
                },
                child: const Text(
                  '사진 삭제',
                  style: TextStyle(
                    fontSize: 20,
                    color: Color(0xFF329EFF),
                  ),
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }

  Future getImage(ImageSource source) async {
    final pickedFile = await _picker.pickImage(source: source);

    if (pickedFile != null) {
      setState(() {
        _imageFile = File(pickedFile.path);
      });
    }
  }

  Future Inquiry(BuildContext context) async {
    final Email email = Email(
      body: '',
      subject: '[페스츄리(Fast Trip) 문의]',
      recipients: ['Fast_Trip@gmail.com'],
      cc: [],
      bcc: [],
      attachmentPaths: [],
      isHTML: false,
    );

    try {
      await FlutterEmailSender.send(email);
    } catch (error) {
      showDialog(
        context: context,
        barrierDismissible: false,
        builder: (BuildContext context) {
          return AlertDialog(
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(8.0)),
            title: const Text('문의하기'),
            content: const SingleChildScrollView(
              child: Text(
                  '기본 메일 앱을 사용할 수 없습니다.\n아래 이메일로 직접 문의해주세요!\n\nFast_Trip@gmail.com'),
            ),
            actions: <Widget>[
              Column(
                children: [
                  const Divider(
                    height: 1,
                  ),
                  Center(
                    child: TextButton(
                      child: const Text("확인"),
                      onPressed: () {
                        Navigator.pop(context);
                      },
                    ),
                  ),
                ],
              ),
            ],
          );
        },
      );
    }
  }
}
