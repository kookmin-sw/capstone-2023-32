import 'package:flutter/material.dart';

class SignUp extends StatefulWidget {
  const SignUp({Key? key}) : super(key: key);

  @override
  State<SignUp> createState() => _SignUpState();
}

Widget TextFormFieldComponent(
    bool obsecureText,
    TextInputType keyboardType,
    TextInputAction textInputAction,
    String hintText,
    int maxSize,
    String errorMessage) {
  return Container(
    child: TextFormField(
      obscureText: obsecureText,
      keyboardType: keyboardType,
      textInputAction: textInputAction,
      decoration: InputDecoration(
        hintText: hintText,
      ),
      validator: (value) {
        if (value!.length < maxSize) {
          return errorMessage;
        }
        return null;
      },
    ),
  );
}

class _SignUpState extends State<SignUp> {
  final formkey = GlobalKey<FormState>(); //Textformfield에 입력된 값 저장

  Future<void> _submit() async {
    if (formkey.currentState!.validate() == false) {
      return;
    } else {
      formkey.currentState!.save();
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text("가입이 완료되었습니다. 로그인을 진행해 주세요."),
          duration: Duration(seconds: 1),
        ),
      );
      Navigator.of(context).popUntil((route) => route.isFirst);
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.white,
        body: Form(
          key: formkey,
          child: Padding(
            padding: const EdgeInsets.all(50),
            child: GestureDetector(
              onTap: () {
                FocusScope.of(context).unfocus();
              },
              child: SingleChildScrollView(
                child: Column(
                  children: [
                    const SizedBox(
                      height: 30,
                    ),
                    const Center(
                      child: Text(
                        '회원가입',
                        style: TextStyle(
                          fontSize: 30,
                          color: Colors.black,
                          fontWeight: FontWeight.w600,
                        ),
                      ),
                    ),
                    const SizedBox(
                      height: 60,
                    ),
                    const TextField(
                      decoration: InputDecoration(
                        labelText: '이름',
                      ),
                      keyboardType: TextInputType.text,
                    ),
                    const SizedBox(
                      height: 30,
                    ),
                    const TextField(
                      decoration: InputDecoration(
                        labelText: '아이디',
                      ),
                      keyboardType: TextInputType.text,
                    ),
                    const SizedBox(
                      height: 30,
                    ),
                    const TextField(
                      decoration: InputDecoration(
                        labelText: '비밀번호',
                      ),
                      keyboardType: TextInputType.text,
                      obscureText: true,
                    ),
                    const SizedBox(
                      height: 30,
                    ),
                    const TextField(
                      decoration: InputDecoration(
                        labelText: '비밀번호 재확인',
                      ),
                      keyboardType: TextInputType.text,
                      obscureText: true,
                    ),
                    const SizedBox(
                      height: 80,
                    ),
                    ElevatedButton(
                      onPressed: () {
                        _submit();
                      },
                      style: ElevatedButton.styleFrom(
                        backgroundColor: const Color(0xFFCAE6FF),
                        padding: const EdgeInsets.symmetric(
                          horizontal: 125,
                          vertical: 8,
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(20),
                        ),
                      ),
                      child: const Text(
                        '가입하기',
                        style: TextStyle(
                          color: Colors.black,
                          fontSize: 16,
                          fontWeight: FontWeight.w600,
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
