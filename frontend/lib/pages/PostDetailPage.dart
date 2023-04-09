import 'package:fasttrip/Data/PostData.dart';
import 'package:flutter/material.dart';

// 게시물 상세 페이지
class PostDetailPage extends StatelessWidget {
  final Post post;

  PostDetailPage({required this.post});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('게시물 상세'),
      ),
      body: Center(
        child: Text('제목 : ${post.title}'),
      ),
    );
  }
}