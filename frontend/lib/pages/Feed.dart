import 'package:flutter/material.dart';
import 'package:fasttrip/style.dart';

class Post {
  final String imageUrl;
  final String title;
  final List<String> tags;

  Post({required this.imageUrl, required this.title, required this.tags});
}

class FeedPage extends StatefulWidget {
  @override
  _FeedPageState createState() => _FeedPageState();
}

class _FeedPageState extends State<FeedPage> {
  final List<Post> _data = [
    Post(
      imageUrl: 'https://images.unsplash.com/photo-1583265266785-aab9e443ee68?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1374&q=80',
      title: '파리 여행은 에펠탑부터',
      tags: ['계획', '프랑스', '유럽여행', '혼자'],
    ),
    Post(
      imageUrl: 'https://images.unsplash.com/photo-1553195029-754fbd369560?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1476&q=80',
      title: '휴양지하면 보라카이',
      tags: ['계획', '보라카이', '휴가', '가족'],
    ),
    Post(
      imageUrl: 'https://images.unsplash.com/photo-1590253230532-a67f6bc61c9e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1431&q=80',
      title: 'Chainsawman 님의 여행 계획',
      tags: ['모집', '일본', '오사카', '단체(혼성)'],
    ),

    // ...
  ];

  List<Post> _filteredData = [];
  String _searchText = '';

  List<String> _selectedFilters = [];

  @override
  void initState() {
    super.initState();
    _filteredData = _data;
  }

  void _applyFilter(String filter) {
    setState(() {
      if (_selectedFilters.contains(filter)) {
        _selectedFilters.remove(filter);
      } else {
        _selectedFilters.add(filter);
      }

      if (_selectedFilters.isEmpty) {
        _filteredData = _data;
      } else {
        _filteredData = _data
            .where((post) => _selectedFilters.every((tag) => post.tags.contains(tag)))
            .toList();
      }
    });
  }

  void _search(String searchText) {
    setState(() {
      _searchText = searchText;
      _filteredData = _data
          .where((post) =>
      post.title.toLowerCase().contains(_searchText.toLowerCase()) &&
          (_selectedFilters.isEmpty || post.tags.any((tag) => _selectedFilters.contains(tag))))
          .toList();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(
          padding: const EdgeInsets.only(top:50.0, left:20.0),
          alignment: Alignment.centerLeft,
          child: Text('피드', style: heading1, textAlign: TextAlign.left),
        ),
        Padding(
          padding: const EdgeInsets.all(20.0),
          child: TextField(

            onChanged: (value) {
              _search(value);
            },
            decoration: InputDecoration(
              hintText: '검색어를 입력해주세요',
              prefixIcon: Icon(Icons.search, color: Colors.grey,),
              border: OutlineInputBorder(
                borderRadius: BorderRadius.all(
                  Radius.circular(25.0),
                ),
              ),
              enabledBorder: OutlineInputBorder(
                borderRadius: BorderRadius.all(
                  Radius.circular(25.0),
                ),
                borderSide: BorderSide(color:Colors.grey.shade400),
              ),
              focusedBorder: OutlineInputBorder(
                borderRadius: BorderRadius.all(
                  Radius.circular(25.0),
                ),
                borderSide: BorderSide(color: Colors.grey.shade400),
              ),
            ),
          ),
        ),
        Padding(
          padding: EdgeInsets.only(left: 30, bottom: 20,),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // Container(
              //   padding: EdgeInsets.only(left: 15,),
              //   child: Row(
              //     children: [
              //       Icon(Icons.filter_alt, size: 16,),
              //       Text(' 필터', style: TextStyle(fontSize: 15, fontWeight:FontWeight.bold),),
              //     ],
              //   ),
              // ),
              // SizedBox(height:8.0),
              Container(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: [
                        Container(
                          width: MediaQuery.of(context).size.width*0.21,
                          padding: const EdgeInsets.only(right:40.0),
                          alignment: Alignment.center,
                          child: Text('누구와', style: heading2, ),
                        ),
                        FilterButton(
                          label: '혼자',
                          selected: _selectedFilters.contains('혼자'),
                          onPressed: () => _applyFilter('혼자'),
                        ),
                        FilterButton(
                          label: '가족',
                          selected: _selectedFilters.contains('가족'),
                          onPressed: () => _applyFilter('가족'),
                        ),
                        FilterButton(
                          label: '애인',
                          selected: _selectedFilters.contains('애인'),
                          onPressed: () => _applyFilter('애인'),
                        ),
                        FilterButton(
                          label: '친구',
                          selected: _selectedFilters.contains('친구'),
                          onPressed: () => _applyFilter('친구'),
                        ),
                      ],
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: [
                        Container(
                          width: MediaQuery.of(context).size.width*0.2,
                          padding: const EdgeInsets.only(right:40.0),
                          alignment: Alignment.center,
                          child: Text('종류', style: heading2, ),
                        ),
                        FilterButton(
                          label: '단체(여자)',
                          selected: _selectedFilters.contains('단체(여자)'),
                          onPressed: () => _applyFilter('단체(여자)'),
                        ),
                        FilterButton(
                          label: '단체(남자)',
                          selected: _selectedFilters.contains('단체(남자)'),
                          onPressed: () => _applyFilter('단체(남자)'),
                        ),
                        FilterButton(
                          label: '단체(혼성)',
                          selected: _selectedFilters.contains('단체(혼성)'),
                          onPressed: () => _applyFilter('단체(혼성)'),
                        ),
                      ],
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: [
                        Container(
                          width: MediaQuery.of(context).size.width*0.2,
                          padding: const EdgeInsets.only(right:40.0),
                          alignment: Alignment.center,
                          child: Text('기타', style: heading2, ),
                        ),
                        FilterButton(
                          label: '최신',
                          selected: _selectedFilters.contains('최신'),
                          onPressed: () => _applyFilter('최신'),
                        ),
                        FilterButton(
                          label: '인기',
                          selected: _selectedFilters.contains('인기'),
                          onPressed: () => _applyFilter('인기'),
                        ),
                        FilterButton(
                          label: '계획',
                          selected: _selectedFilters.contains('계획'),
                          onPressed: () => _applyFilter('계획'),
                        ),
                        FilterButton(
                          label: '모집',
                          selected: _selectedFilters.contains('모집'),
                          onPressed: () => _applyFilter('모집'),
                        ),
                      ],
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
        Expanded(
          child: ListView.builder(
            itemCount: _filteredData.length,
            itemBuilder: (context, index) {
              return InkWell(
                onTap: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => PostDetailPage(post: _filteredData[index]),
                    ),
                  );
                },
                child: Padding(
                  padding: const EdgeInsets.only(left: 20.0, right: 20.0, bottom:20.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Image.network(
                          _filteredData[index].imageUrl,
                          width: double.infinity,
                          height: 200,
                          fit: BoxFit.cover),
                      SizedBox(height: 10.0),
                      Text(
                        _filteredData[index].title,
                        style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                      ),
                      SizedBox(height: 10.0),
                      Wrap(
                        spacing: 8.0,
                        children: _filteredData[index].tags
                            .map((tag) => Chip(
                          label: Text(
                            tag,
                            style: TextStyle(color: Colors.blue),
                          ),
                          backgroundColor: Colors.transparent,
                          side: BorderSide(color: Colors.blue, width: 1),
                        ),).toList(),
                      ),
                    ],
                  ),
                ),
              );
            },
          ),
        ),
      ],
    );
  }
}

class FilterButton extends StatelessWidget {
  final String label;
  final bool selected;
  final VoidCallback onPressed;

  FilterButton({
    required this.label,
    required this.selected,
    required this.onPressed,
  });

  @override
  Widget build(BuildContext context) {
    return TextButton(
      onPressed: onPressed,
      style: TextButton.styleFrom(
        alignment: Alignment.centerLeft,
        primary: selected ? Colors.blue : Colors.black,
        backgroundColor: Colors.transparent,
        padding: EdgeInsets.symmetric(horizontal: 10),
      ),
      child: Text(
        label,
        style: TextStyle(fontSize: 13.0),
      ),
    );
  }
}

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