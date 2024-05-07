import 'package:flutter/material.dart';
import 'package:fronted/page/route.dart';
import 'package:http/http.dart' as http; // 导入http包
import 'package:shared_preferences/shared_preferences.dart';
import 'dart:convert'; // 导入json
import 'register.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  // 控制器保存并操作文本字段的内容
  final usernameController = TextEditingController();
  final passwordController = TextEditingController();

  Future<void> _attemptLogin() async {
    try {
      final prefers = await SharedPreferences.getInstance();
      //日后别的page发请求的时候在进行获取token/添加到请求头
      final token = prefers.getString("Authorization");
      final response = await http.post(
        Uri.parse('http://localhost:8080/api/login'),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
          // 'Authorization': '$token'
        },
        body: jsonEncode(<String, String>{
          'username': usernameController.text,
          'password': passwordController.text,
        }),
      );
      if (response.statusCode == 200) {
        // If the server returns a 200 OK response, then parse the JSON.
        Map<String, dynamic> responseJson = jsonDecode(response.body);

        String token = responseJson['data']['token'];
        final prefs = await SharedPreferences.getInstance();
        await prefs.setString("token", token);
        // TODO: Handle the 'responseJson' as per your backend response, save tokens, navigate to home page etc.
        _navigateToHomePage(token);
      } else {
        // If the server returns an error response, then throw an exception.
        throw Exception('Failed to login. Please check your credentials.');
      }
    } catch (e) {
      print('Caught error: $e');
      throw e;
    }
  }

  void _navigateToHomePage(String token) {
    Navigator.push(context, MaterialPageRoute(builder: (context) =>SidebarNavigation()));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('登录'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(10),
        child: Column(
          children: <Widget>[
            TextField(
              controller: usernameController,
              decoration: const InputDecoration(
                  labelText: "用户名",
                  hintText: "用户名或邮箱",
                  prefixIcon: Icon(Icons.person)),
            ),
            TextField(
              controller: passwordController,
              decoration: const InputDecoration(
                  labelText: "密码",
                  hintText: "您的登录密码",
                  prefixIcon: Icon(Icons.lock)),
              obscureText: true,
            ),
            ElevatedButton(
              onPressed: _attemptLogin,
              child: const Text('登录'),
            ),
            ElevatedButton(
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => RegisterPage()),
                  );
                },
                child: const Text("注册"))
          ],
        ),
      ),
    );
  }
}

void main() {
  runApp(const MaterialApp(
    title: 'Navigation Basics',
    home: LoginPage()
  ));
}
