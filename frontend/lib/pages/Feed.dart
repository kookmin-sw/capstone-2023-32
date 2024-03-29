import 'package:fasttrip/pages/PostDetailPage.dart';
import 'package:flutter/material.dart';
import 'package:fasttrip/style.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';
import '../token_model.dart';

// data fetch
// 받아올 데이터 정의
class Plan {
  String planId;
  String userId;
  String title;
  int like;
  List<String> tags;
  String comment;
  String imgUrl;
  bool liked;

  Plan({
    required this.planId,
    required this.userId,
    required this.title,
    required this.like,
    required this.tags,
    required this.comment,
    required this.imgUrl,
    required this.liked
  });

  factory Plan.fromJson(Map<String, dynamic> json){
    return Plan(
      planId: (json['id'] ?? ''),
      userId: (json['userId'] ?? ''),
      title: json['title'] ?? '',
      like: json['like'] ?? 0,
      tags: List<String>.from(json['tags'] ?? [],),
      comment: json['comment'] ?? '',
      imgUrl: json['image'] ?? '',
      liked: json['liked'] ?? false,
    );
  }
}


class FeedPage extends StatefulWidget {
  const FeedPage({Key? key}) : super(key: key);

  @override
  _FeedPageState createState() => _FeedPageState();
}

class _FeedPageState extends State<FeedPage> {
  List<Plan> _filteredData = [];
  String _searchText = '';
  final List<String> _selectedFilters = [];

  List<Plan> plans = [];

  void fetchData() async {
    final tokenModel = Provider.of<TokenModel>(context, listen: false);
    final token = tokenModel.token;

    var url = Uri.parse('http://3.38.99.234:8080/api/plan/list');
    var requestBody = jsonEncode({
      "title": "",
      "tags": [],
    });

    var response = await http.post(
      url,
      body: requestBody,
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json",
        "Auth": '$token',
      },
    );

    if (response.statusCode == 200) {
      var data = jsonDecode(utf8.decode(response.bodyBytes)) as List;
      List<Plan> fetchedPlans = data.map((item) => Plan.fromJson(item)).toList();

      setState(() {
        plans = fetchedPlans;
        _filteredData = plans;
      });
      print('----------------------------------------------');
      for (Plan plan in plans) {
        print('Plan ID: ${plan.planId}');
        print('User ID: ${plan.userId}');
        print('Title: ${plan.title}');
        print('like: ${plan.like}');
        print('Tags: ${plan.tags.join(', ')}');
        print('comment: ${plan.comment}');
        print('imgUrl: ${plan.imgUrl}');
      }
    } else {
      print(response.statusCode);
      print('failed to load');
    }
  }




  @override
  void initState() {
    super.initState();
    fetchData();
  }

// 태그에 대한 필터만 적용
  void _applyFilter(String filter) {
    setState(() {
      if (_selectedFilters.contains(filter)) {
        _selectedFilters.remove(filter);
      } else {
        _selectedFilters.add(filter);
      }
    });
    // 현재 검색text를 호출하여 결과 확인
    _search(_searchText);
  }

  void _search(String searchText) {
    setState(() {
      _searchText = searchText;
      _filteredData = plans
          .where((post) =>
      // 검색 텍스트가 title, contents, tag에 있는지 확인
      (post.title.toLowerCase().contains(_searchText.toLowerCase()) ||
          post.comment.toLowerCase().contains(_searchText.toLowerCase()) ||
          post.tags.any((tag) => tag.toLowerCase().contains(_searchText.toLowerCase()))) &&
          // 게시물에 일치하는 태그가 있는지 확인
          (_selectedFilters.isEmpty || _selectedFilters.any((tag) => post.tags.contains(tag))))
          .toList();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(
          padding: const EdgeInsets.only(top: 50.0, left: 20.0),
          alignment: Alignment.centerLeft,
          child: Row(
          children: [
          Icon(Icons.library_books_rounded, color: signatureColor),
          const SizedBox(width: 6),
          Text('피드', style: heading1, textAlign: TextAlign.left),
          ],
          ),
        ),
        Container(
          padding: const EdgeInsets.only(left: 20.0, right: 20.0, bottom: 10.0, top: 20.0),
          height: MediaQuery.of(context).size.height * 0.1,
          child: TextField(
            onChanged: (value) {
              _search(value);
            },
            decoration: InputDecoration(
              hintText: '검색어를 입력해주세요',
              prefixIcon: const Icon(
                Icons.search,
                color: Colors.grey,
              ),
              border: const OutlineInputBorder(
                borderRadius: BorderRadius.all(
                  Radius.circular(25.0),
                ),
              ),
              enabledBorder: OutlineInputBorder(
                borderRadius: const BorderRadius.all(
                  Radius.circular(25.0),
                ),
                borderSide: BorderSide(color: Colors.grey.shade400),
              ),
              focusedBorder: OutlineInputBorder(
                borderRadius: const BorderRadius.all(
                  Radius.circular(25.0),
                ),
                borderSide: BorderSide(color: Colors.grey.shade400),
              ),
            ),
          ),
        ),
        Padding(
          padding: const EdgeInsets.only(
            left: 30,
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Container(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: [
                        Container(
                          width: MediaQuery.of(context).size.width * 0.21,
                          padding: const EdgeInsets.only(right: 40.0),
                          alignment: Alignment.center,
                          child: Text(
                            '누구와',
                            style: heading2,
                          ),
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
                          width: MediaQuery.of(context).size.width * 0.21,
                          padding: const EdgeInsets.only(right: 40.0),
                          alignment: Alignment.center,
                          child: Text(
                            '종류',
                            style: heading2,
                          ),
                        ),
                        FilterButton(
                          label: '여행',
                          selected: _selectedFilters.contains('여행'),
                          onPressed: () => _applyFilter('여행'),
                        ),
                        FilterButton(
                          label: '모집',
                          selected: _selectedFilters.contains('모집'),
                          onPressed: () => _applyFilter('모집'),
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
                      ],
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: [
                        Container(
                          width: MediaQuery.of(context).size.width * 0.21,
                          padding: const EdgeInsets.only(right: 40.0),
                          alignment: Alignment.center,
                          child: Text(
                            '기타',
                            style: heading2,
                          ),
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
                      builder: (context) =>
                          PostDetailPage(planId: _filteredData[index].planId),
                    ),
                  );
                },
                child: Padding(
                  padding: const EdgeInsets.only(
                      left: 20.0, right: 20.0, bottom: 20.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Stack(
                        children: [
                          ClipRRect(
                            borderRadius: BorderRadius.circular(10.0),
                            child: Image.network(_filteredData[index].imgUrl,
                                width: double.infinity,
                                height: 200,
                                fit: BoxFit.cover),
                          ),
                          Positioned(
                            top: 5,
                            right: 5,
                            child: IconButton(
                              onPressed: () {
                                setState(() {
                                  _filteredData[index].liked = !_filteredData[index].liked;
                                });
                              },
                              padding: EdgeInsets.zero,
                              constraints: const BoxConstraints(),
                              icon: Icon(
                                _filteredData[index].liked ? Icons.favorite : Icons.favorite_border,
                                color: _filteredData[index].liked ? Color(0xffFA6D6D) : Colors.white,
                              ),
                            ),
                          ),
                        ],
                      ),
                      const SizedBox(height: 10.0),
                      Text(
                        _filteredData[index].title,
                        style: const TextStyle(
                            fontSize: 16, fontWeight: FontWeight.bold),
                      ),
                      const SizedBox(height: 10.0),
                      Wrap(
                        spacing: 8.0,
                        children: _filteredData[index]
                            .tags
                            .map(
                              (tag) => Chip(
                                label: Text(
                                  tag,
                                  style: const TextStyle(color: Color(0xff6DA5FA)),
                                ),
                                backgroundColor: Colors.transparent,
                                side: const BorderSide(
                                    color: Color(0xff6DA5FA), width: 1),
                              ),
                            )
                            .toList(),
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

  const FilterButton({
    Key? key,
    required this.label,
    required this.selected,
    required this.onPressed,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return TextButton(
      onPressed: onPressed,
      style: TextButton.styleFrom(
        foregroundColor: selected ? Colors.blue : Colors.black,
        alignment: Alignment.centerLeft,
        backgroundColor: Colors.transparent,
        padding: const EdgeInsets.symmetric(horizontal: 10),
      ),
      child: Text(
        label,
        style: const TextStyle(fontSize: 13.0),
      ),
    );
  }
}
