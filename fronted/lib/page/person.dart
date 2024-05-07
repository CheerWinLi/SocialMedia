import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'dart:io';
import 'package:path_provider/path_provider.dart';
import 'package:http/http.dart' as http;

class PersonPage extends StatefulWidget {
  @override
  _PersonPageState createState() => _PersonPageState();
}

class _PersonPageState extends State<PersonPage> {
  String _nickname = '';
  String _avatarPath = '';

  @override
  void initState() {
    super.initState();
    // 从后端请求获取个人信息数据
    // _fetchPersonInfo();
  }

  // Future<void> _fetchPersonInfo() async {
  //   // 模拟从后端请求获取个人信息数据
  //   await Future.delayed(Duration(seconds: 1));
  //
  //   setState(() {
  //     // 从后端获取的昵称和头像路径
  //     _nickname = 'John Doe';
  //     _avatarPath = 'https://example.com/avatar.jpg';
  //   });
  // }

  Future<void> _pickImage(ImageSource source) async {
    final picker = ImagePicker();
    final pickedFile = await picker.getImage(source: source);

    if (pickedFile != null) {
      final fileName = 'avatar.jpg';
      final savedFile = File('assets/$fileName');

      // 将选择的图像复制到应用程序文档目录中
      await savedFile.writeAsBytes(await pickedFile.readAsBytes());

      // 上传头像至后端
      // await _uploadImage(savedFile);
    }
  }

  // Future<void> _uploadImage(File imageFile) async {
  //   // 模拟上传图像至后端
  //   await Future.delayed(Duration(seconds: 1));
  //
  //   // 获取上传后的头像路径
  //   final avatarUrl = 'https://example.com/uploaded_avatar.jpg';
  //
  //   setState(() {
  //     _avatarPath = avatarUrl;
  //   });
  // }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('个人信息'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            CircleAvatar(
              radius: 80,
              backgroundImage: _avatarPath.isNotEmpty
                  ? NetworkImage(_avatarPath) as ImageProvider<Object>
                  : AssetImage('assets/default_avatar.jpg') as ImageProvider<Object>,
            ),
            SizedBox(height: 16),
            Text(
              _nickname,
              style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 16),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                SizedBox(width: 16),
                ElevatedButton(
                  onPressed: () => _pickImage(ImageSource.gallery),
                  child: Text('上传照片'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
