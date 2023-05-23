import 'package:flutter/foundation.dart';

class TokenModel extends ChangeNotifier {
  String? _token;

  String? get token => _token;

  set token(String? value) {
    _token = value;
    notifyListeners();
  }
}
