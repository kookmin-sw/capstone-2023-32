import 'package:flutter/material.dart';


// 검색 결과 화면
class SearchResultsPage extends StatelessWidget {
  final String searchTerm;

  SearchResultsPage({required this.searchTerm});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Search Results'),
      ),
      body: Center(
        child: Text(
          '$searchTerm 검색 결과',
          style: TextStyle(fontSize: 18),
        ),
      ),
    );
  }
}