import 'package:flutter/foundation.dart';
import 'package:shared_preferences/shared_preferences.dart';

class TokenModel extends ChangeNotifier {
  String? _token;

  String? get token => _token;

  Future<void> setToken(String? value) async {
    _token = value;
    await _saveToken(value);
    notifyListeners();
  }

  Future<void> _saveToken(String? token) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString('jwt', token ?? '');
  }

  Future<void> loadToken() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('jwt');
    if (token != null && token.isNotEmpty) {
      _token = token;
      notifyListeners();
    }
  }
}

