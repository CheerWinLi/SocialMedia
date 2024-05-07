import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fronted/page/chat.dart';
import 'package:fronted/page/person.dart';
import 'package:fronted/page/video.dart';

class SidebarNavigation extends StatefulWidget {
  @override
  _SidebarNavigationState createState() => _SidebarNavigationState();
}

class _SidebarNavigationState extends State<SidebarNavigation> {
  int _selectedPageIndex = 0;

  List<Widget> _pages = [
    PersonPage(),
    ChatPage(),
    VideoPage(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('功能导航'),
      ),
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            UserAccountsDrawerHeader(
              accountName: Text('Your Name'),  // 替换为用户名称
              accountEmail: Text('yourname@example.com'),  // 替换为用户电子邮件地址
              currentAccountPicture: CircleAvatar(
                radius: 30,
                backgroundImage: AssetImage('assets/default_avatar.jpg'),  // 替换为头像路径
              ),
            ),
            ListTile(
              title: Text('个人信息'),
              onTap: () {
                setState(() {
                  _selectedPageIndex = 0;
                  Navigator.pop(context); // 关闭侧边栏
                });
              },
            ),
            ListTile(
              title: Text('聊天'),
              onTap: () {
                setState(() {
                  _selectedPageIndex = 1;
                  Navigator.pop(context); // 关闭侧边栏
                });
              },
            ),
            ListTile(
              title: Text('视频'),
              onTap: () {
                setState(() {
                  _selectedPageIndex = 2;
                  Navigator.pop(context); // 关闭侧边栏
                });
              },
            ),
          ],
        ),
      ),
      body: _pages[_selectedPageIndex],
    );
  }
}
void main() {
  runApp(MaterialApp(
    title: 'Chat App',
    home: SidebarNavigation(),
  ));
}