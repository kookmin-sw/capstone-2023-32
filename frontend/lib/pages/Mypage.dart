import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:fasttrip/style.dart';
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
        body: SingleChildScrollView(
          child: Column(
            children: [
              Container(
                padding: const EdgeInsets.only(
                  top: 50,
                  left: 20,
                ),
                alignment: Alignment.centerLeft,
                child: Text(
                  '마이페이지',
                  style: heading1,
                  textAlign: TextAlign.left,
                ),
              ),
              Padding(
                padding: const EdgeInsets.all(40),
                child: Column(
                  children: [
                    Row(
                      children: [
                        imageProfile(),
                        const SizedBox(
                          width: 30,
                        ),
                        Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Row(
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
                                fontWeight: FontWeight.normal,
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                    const SizedBox(
                      height: 65,
                    ),
                    mypageButton('내 정보'),
                    const SizedBox(
                      height: 22,
                    ),
                    mypageButton('내가 찜한 여행/스크랩'),
                    const SizedBox(
                      height: 22,
                    ),
                    mypageButton('쪽지함'),
                    const SizedBox(
                      height: 22,
                    ),
                    mypageButton('문의'),
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
              color: Color(0xFF9CC4FF),
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

  Widget mypageButton(String memuName) {
    return InkWell(
      onTap: () {},
      child: Container(
        padding: const EdgeInsets.symmetric(
          horizontal: 20,
          vertical: 13,
        ),
        width: 400,
        height: 56,
        decoration: BoxDecoration(
          border: Border.all(
            color: const Color(0xFF9CC4FF),
          ),
          borderRadius: BorderRadius.circular(15),
        ),
        child: Text(
          memuName,
          style: const TextStyle(
            fontSize: 20,
            color: Color(0xFF329EFF),
            fontWeight: FontWeight.normal,
          ),
          textAlign: TextAlign.start,
        ),
      ),
    );
  }
}
