import 'package:fasttrip/style.dart';
import 'package:flutter/material.dart';
import 'package:fasttrip/pages/searchResult.dart';

class SearchPage extends StatelessWidget {
  const SearchPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        resizeToAvoidBottomInset: false,
        appBar: AppBar(
          elevation: 0, // Remove elevation to hide the bottom shadow
          backgroundColor: Colors.transparent, // Make the AppBar transparent
          shadowColor: Colors.transparent, // Set AppBar shadow color to transparent
          leading: IconButton(
            icon: Icon(Icons.arrow_back, color: Colors.black,),
            onPressed: () {
              Navigator.pop(context);
            },
          ),
        ),
        body: Center(
          child: SearchBar(),
        )
    );
  }
}


class SearchBar extends StatefulWidget {
  @override
  _SearchBarState createState() => _SearchBarState();
}

class _SearchBarState extends State<SearchBar> {
  TextEditingController _searchController = TextEditingController();
  // 최근 검색어 리스트
  final List<String> _recentSearchTerms = [];
  // 자동완성 리스트
  final List<String>autoComplete = [
    'Flights',
    'Hotels',
    'Car Rentals',
    'Vacation Packages',
    'Cruises',
    'Activities',
    'Travel Insurance',
    'Destinations',
    'Restaurants',
  ];



  //추천 검색어 위젯
  Widget _buildSuggestionButtons(BuildContext context) {
    List<String> suggestions = [
      '남산타워',
      '우도',
      '동문시장',
      '성산일출봉',
      '흑돼지',
      'Suggestion 6',
      'Suggestion 7',
      'Suggestion 8',
    ];

    return Container(
      margin: EdgeInsets.only(left: 20, right: 20),
      padding: EdgeInsets.symmetric(vertical: 50),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [

          Align(
            alignment: Alignment.centerLeft,
            child: Text(
              '추천 검색어',
              style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
            ),
          ),
          SizedBox(height: 20),
          Wrap(
            spacing: 10,
            runSpacing: 10,
            children: suggestions.map((suggestion) {
              return ElevatedButton(
                onPressed: () {
                  _handleSubmitted(context, suggestion);
                },
                style: ButtonStyle(
                  elevation: MaterialStateProperty.all<double>(0),
                  backgroundColor: MaterialStateProperty.all<Color>(Colors.transparent),
                  shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                    RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(20),
                      side: BorderSide(color: Colors.grey.shade500),
                    ),
                  ),
                ),
                child: Text(suggestion, style: lightText,),
              );
            }).toList(),
          ),
        ],
      ),
    );
  }

  @override
  void dispose() {
    _searchController.dispose();
    super.dispose();
  }

  void _handleSubmitted(BuildContext context, String value) {
    if (value.isNotEmpty) {
      setState(() {
        if (_recentSearchTerms.contains(value)) {
          _recentSearchTerms.remove(value);
        }
        _recentSearchTerms.add(value);
        if (_recentSearchTerms.length > 5) {
          _recentSearchTerms.removeAt(0);
        }
        _searchController.clear();
      });

      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => SearchResultsPage(searchTerm: value),
        ),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(
          margin: EdgeInsets.all(20.0),
          child: IgnorePointer(
            ignoring: false,
            child: Autocomplete<String>(
              optionsBuilder: (TextEditingValue textEditingValue) {
                if (textEditingValue.text.isEmpty) {
                  return const Iterable<String>.empty();
                }
                return autoComplete.where((String option) {
                  return option.toLowerCase().contains(textEditingValue.text.toLowerCase());
                });
              },
              displayStringForOption: (String option) => option,
              fieldViewBuilder: (BuildContext context, TextEditingController fieldTextEditingController, FocusNode fieldFocusNode, VoidCallback onFieldSubmitted) {
                _searchController = fieldTextEditingController;
                return TextField(
                  controller: fieldTextEditingController,
                  focusNode: fieldFocusNode,
                  decoration: InputDecoration(
                    border: UnderlineInputBorder(),
                    focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(color: Colors.black,),
                    ),
                    hintText: '검색어를 입력해주세요',
                    hintStyle: TextStyle(color: Colors.grey),
                  ),
                  onSubmitted: (String value) {
                    _handleSubmitted(context, value);
                    onFieldSubmitted();
                  },
                );
              },
              optionsViewBuilder: (BuildContext context, AutocompleteOnSelected<String> onSelected, Iterable<String> options) {
                return Align(
                  alignment: Alignment.topLeft,
                  child: Material(
                    elevation: 4.0,
                    child: Container(
                      height: 200.0,
                      child: ListView.builder(
                        padding: EdgeInsets.all(8.0),
                        itemCount: options.length,
                        itemBuilder: (BuildContext context, int index) {
                          final String option = options.elementAt(index);
                          return GestureDetector(
                            onTap: () {
                              onSelected(option);
                            },
                            child: ListTile(
                              title: Text(option),
                            ),
                          );
                        },
                      ),
                    ),
                  ),
                );
              },
            ),
          ),
        ),
        Align(
          alignment: Alignment.centerLeft,
          child: Padding(
            padding: const EdgeInsets.only(top: 50.0, left: 20, right:20),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      '최근 검색어',
                      style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                    ),
                    TextButton(onPressed: () {
                      setState(() {
                        _recentSearchTerms.clear();
                      });
                    }, child: Text('전체 삭제', style: lightText,)),
                  ],
                ),
                SizedBox(height: 20),
                ..._recentSearchTerms.reversed.expand((term) => [
                  Text(term, style: TextStyle(fontSize: 16)),
                  SizedBox(height: 5),
                ]).toList(),
              ],
            ),
          ),
        ),
        Expanded(
          child: Align(
            alignment: Alignment.bottomCenter,
            child: _buildSuggestionButtons(context),
          ),
        ),
      ],
    );
  }
}


