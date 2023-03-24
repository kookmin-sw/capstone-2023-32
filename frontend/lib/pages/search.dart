import 'package:flutter/material.dart';


class Search extends SearchDelegate {
  @override
  List<Widget> buildActions(BuildContext context) {
    return <Widget>[
      IconButton(
        icon: Icon(Icons.close),
        onPressed: () {
          query = "";
        },
      ),
    ];
  }

  @override
    Widget buildLeading(BuildContext context){
    return IconButton(
      icon: Icon(Icons.arrow_back),
      onPressed:(){
        Navigator.pop(context);
      }
    );
  }

  String selectedResult = "";   // 여기다 검색 결과 받아오기

  @override
  Widget buildResults(BuildContext context) {
    return Container(
      child: Center(
        child: Text(selectedResult),
      ),
    );
  }

  final List<String> listExample;
  Search(this.listExample);

  List<String> recentList = ["Text3", "Text4"]; // shared preference 활용

  @override
  Widget buildSuggestions(BuildContext context) {
    List<String> suggestionList = [];
    query.isEmpty
      ? suggestionList = recentList
      : suggestionList.addAll(listExample.where(
        (element) => element.contains(query)
    ));
    return ListView.builder(
            itemCount: suggestionList.length,
            itemBuilder: (context, index){
              return ListTile(
                      title: Text(suggestionList[index],),
                      leading: query.isEmpty? Icon(Icons.access_time): SizedBox(),
                      onTap: (){
                        selectedResult = suggestionList[index];
                        showResults(context);
                      });
          },
    );

  }

}



class SearchPage extends StatelessWidget {
  const SearchPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final List<String> list = List.generate(10, (index) => "Text $index");
    return IconButton(
      onPressed: () {
        showSearch(
            context: context,
            delegate: Search(list)
        );
      },
      icon: const Icon(Icons.search),
      iconSize: 20,
      color: Colors.black,
    );
  }
}






