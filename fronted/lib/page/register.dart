import 'dart:async';
import 'dart:convert';
import 'package:http/http.dart' as http; // 导入http包

import 'package:english_words/english_words.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class RegisterPage extends StatefulWidget {
  const RegisterPage({super.key});

  @override
  State<StatefulWidget> createState() => _RegisterPageState();
}

class MyAppState extends ChangeNotifier {}

class _RegisterPageState extends State<RegisterPage> {
  final usernameController = TextEditingController();
  final passwordController = TextEditingController();
  final emailController = TextEditingController();

  Timer? _debounceTimer;

  _handleUsernameChanged(String text) {
    if (_debounceTimer?.isActive ?? false) _debounceTimer?.cancel();
    _debounceTimer = Timer(Duration(seconds: 1, milliseconds: 500), () {
      _checkUsernameExists(text);
    });
  }

  Future<void> _attemptRegister() async {
    try {
      final response = await http.post(
        Uri.parse('http://localhost:8080/api/register'),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: jsonEncode(<String, String>{
          'username': usernameController.text,
          'password':passwordController.text,
          'email':emailController.text
        }),
      );

      if (response.statusCode == 200) {
        final responseJson = jsonDecode(response.body);
        // TODO: 这取决于你的API如何返回结果，适当地调整代码
        // 假设API返回 {"exists": true} 或 {"exists": false}
        return responseJson['exists'];
      } else {
        // 打印错误到控制台并抛出异常
        print('Request failed with status: ${response.statusCode}.');
        throw Exception('注册失败');

      }
    } catch (e) {
      print('Caught error: $e');
      throw e;
    }
  }

// 类的其它部分代码同上，我们只对 _checkUsernameExists 函数进行修订
  Future<bool> _checkUsernameExists(String username) async {
    try {
      final response = await http.post(
        Uri.parse('http://localhost:8080/api/verifyUsername?username=$username'),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
      );

      if (response.statusCode == 200) {
        final responseJson = jsonDecode(response.body);
        // TODO: 这取决于你的API如何返回结果，适当地调整代码
        // 假设API返回 {"exists": true} 或 {"exists": false}
        return responseJson['data'];
      } else {
        // 打印错误到控制台并抛出异常
        print('Request failed with status: ${response.statusCode}.');
        throw Exception('用户名重复');
      }
    } catch (e) {
      print('Caught error: $e');
      throw e;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Register"),
      ),
      body: Column(
        children: <Widget>[
          Center(
            child: TextField(
              controller: usernameController,
              decoration: const InputDecoration(
                labelText: "请输入用户名",
                hintText: "注册的用户名",
                prefixIcon: Icon(Icons.person),
              ),
              onChanged: _handleUsernameChanged,
            ),
          ),
          Center(
            child: TextField(
              controller: passwordController,
              decoration: const InputDecoration(
                labelText: "请输入密码",
                hintText: "**************",
                prefixIcon: Icon(Icons.lock),
              ),
            ),
          ),
          Center(
            child: TextField(
              controller: emailController,
              decoration: const InputDecoration(
                labelText: "请输入邮箱",
                hintText: "邮箱",
                prefixIcon: Icon(Icons.email),
              ),
            ),
          ),
          ElevatedButton(
              onPressed: _attemptRegister,
              child: const Text("注册"))
        ],
      ),
    );
  }
}
