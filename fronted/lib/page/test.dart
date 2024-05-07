import 'dart:typed_data';
import 'package:file_picker/file_picker.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';


import 'package:http_parser/http_parser.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Upload File App',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: UploadPage(),
    );
  }
}

class UploadPage extends StatefulWidget {
  @override
  _UploadPageState createState() => _UploadPageState();
}

class _UploadPageState extends State<UploadPage> {
  Uint8List? fileBytes;
  String fileName = '';
  final TextEditingController _usernameController = TextEditingController();

  _getFile() async {
    FilePickerResult? result = await FilePicker.platform.pickFiles(type: FileType.any);

    if(result != null) {
      PlatformFile file = result.files.first;

      fileBytes = file.bytes;
      fileName = file.name;

      setState(() {}); // update the UI
    } else {
      // User canceled the picker
    }
  }

  _uploadFile() async {
    if (fileBytes != null) {
      var uri = Uri.parse("http://localhost:8080/feed/video/postVideo");
      var request = http.MultipartRequest("POST", uri)
        ..files.add(http.MultipartFile.fromString("username", _usernameController.text))
        ..files.add(http.MultipartFile.fromBytes('file', fileBytes!, filename: fileName ));

      var response = await request.send();
      if (response.statusCode == 200) {
        print('Upload successful!');
      } else {
        print('上传失败');
      }
    } else {
      print('No file selected.');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Upload Page')),
      body: Column(
        children: <Widget>[
          ElevatedButton(
            child: Text('Pick File'),
            onPressed: _getFile,
          ),
          Text(fileName),
          TextField(
            controller: _usernameController,
            decoration: InputDecoration(labelText: 'Enter your username'),
          ),
          ElevatedButton(
            child: Text('Upload File'),
            onPressed: _uploadFile,
          ),
        ],
      ),
    );
  }
}