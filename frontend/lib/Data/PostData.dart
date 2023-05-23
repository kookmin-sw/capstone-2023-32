class Post {
  final String imageUrl;
  final String title;
  final List<String> tags;
  final String contents;
  bool heart;

  Post(
      {required this.imageUrl,
      required this.title,
      required this.tags,
      required this.heart,
      required this.contents,
      });

}

final List<Post> data = [
  Post(
    imageUrl:
        'https://images.unsplash.com/photo-1583265266785-aab9e443ee68?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1374&q=80',
    title: '사랑과 낭만의 도시 Paris',
    tags: ['프랑스', '혼자', '아이스크림', '러시아'],
    heart: true,
    contents: "aaa",
  ),
  Post(
    imageUrl: 'https://images.unsplash.com/photo-1553195029-754fbd369560?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1476&q=80',
    title: '휴양지하면 보라카이',
    tags: ['계획', '보라카이', '휴가', '가족'],
    heart: false,
    contents: "bbb",
  ),
  Post(
    imageUrl:
        'https://images.unsplash.com/photo-1590253230532-a67f6bc61c9e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1431&q=80',
    title: '오사카 여행 모집합니다~!',
    tags: ['오사카', '일본', '단체', '모집'],
    heart: true,
    contents: "ccc",
  ),

  // ...
];
