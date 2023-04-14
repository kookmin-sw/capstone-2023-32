import 'package:fasttrip/Data/PostData.dart';
import 'package:fasttrip/pages/PostDetailPage.dart';
import 'package:flutter/material.dart';
import 'package:fasttrip/style.dart';

class FeedPage extends StatefulWidget {
  const FeedPage({Key? key}) : super(key: key);

  @override
  _FeedPageState createState() => _FeedPageState();
}

class _FeedPageState extends State<FeedPage> {
  List<Post> _filteredData = [];
  String _searchText = '';

  final List<String> _selectedFilters = [];

  @override
  void initState() {
    super.initState();
    _filteredData = data;
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
      _filteredData = data
          .where((post) =>
      // 검색 텍스트가 title, contents, tag에 있는지 확인
      (post.title.toLowerCase().contains(_searchText.toLowerCase()) ||
          post.contents.toLowerCase().contains(_searchText.toLowerCase()) ||
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
            bottom: 20,
          ),
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
                          width: MediaQuery.of(context).size.width * 0.2,
                          padding: const EdgeInsets.only(right: 40.0),
                          alignment: Alignment.center,
                          child: Text(
                            '종류',
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
                    Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: [
                        Container(
                          width: MediaQuery.of(context).size.width * 0.2,
                          padding: const EdgeInsets.only(right: 40.0),
                          alignment: Alignment.center,
                          child: Text(
                            '기타',
                            style: heading2,
                          ),
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
                      builder: (context) =>
                          PostDetailPage(post: _filteredData[index]),
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
                          Image.network(_filteredData[index].imageUrl,
                              width: double.infinity,
                              height: 200,
                              fit: BoxFit.cover),
                          Positioned(
                            top: 5,
                            right: 5,
                            child: IconButton(
                              onPressed: () {
                                setState(() {
                                  if (_filteredData[index].heart) {
                                    _filteredData[index].heart = false;
                                  } else {
                                    _filteredData[index].heart = true;
                                  }
                                });
                              },
                              padding: EdgeInsets.zero,
                              constraints: const BoxConstraints(),
                              icon: Icon(
                                _filteredData[index].heart
                                    ? Icons.favorite
                                    : Icons.favorite_border,
                                color: _filteredData[index].heart
                                    ? Colors.red
                                    : Colors.white,
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
                                  style: const TextStyle(color: Colors.blue),
                                ),
                                backgroundColor: Colors.transparent,
                                side: const BorderSide(
                                    color: Colors.blue, width: 1),
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
