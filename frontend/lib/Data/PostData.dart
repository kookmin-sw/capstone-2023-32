class Post {
  final String imageUrl;
  final String title;
  final List<String> tags;
<<<<<<< Updated upstream
  bool heart;

  Post(
      {required this.imageUrl,
      required this.title,
      required this.tags,
      required this.heart});
=======
  final bool heart;
  final String contents;

  Post({required this.imageUrl, required this.title, required this.tags, required this.heart, required this.contents});
>>>>>>> Stashed changes
}

final List<Post> data = [
  Post(
    imageUrl:
        'https://images.unsplash.com/photo-1583265266785-aab9e443ee68?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1374&q=80',
    title: '사랑과 낭만의 도시 Paris',
    tags: ['계획', '프랑스', '유럽여행', '혼자'],
    heart: true,
    contents: "aaa",
  ),
  Post(
<<<<<<< Updated upstream
      imageUrl:
          'https://images.unsplash.com/photo-1553195029-754fbd369560?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1476&q=80',
      title: '휴양지하면 보라카이',
      tags: ['계획', '보라카이', '휴가', '가족'],
      heart: true),
=======
    imageUrl: 'https://images.unsplash.com/photo-1553195029-754fbd369560?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1476&q=80',
    title: '휴양지하면 보라카이',
    tags: ['계획', '보라카이', '휴가', '가족'],
    heart: true,
    contents: "bbb",
  ),
>>>>>>> Stashed changes
  Post(
    imageUrl:
        'https://images.unsplash.com/photo-1590253230532-a67f6bc61c9e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1431&q=80',
    title: 'Chainsawman 님의 여행 계획',
    tags: ['모집', '일본', '오사카', '단체(혼성)'],
    heart: false,
    contents: "ccc",
  ),

  // ...
];
