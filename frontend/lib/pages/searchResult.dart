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
          'Search results for: $searchTerm',
          style: TextStyle(fontSize: 18),
        ),
      ),
    );
  }
}