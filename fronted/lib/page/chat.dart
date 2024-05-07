import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:web_socket_channel/io.dart';
import 'package:web_socket_channel/web_socket_channel.dart';

class ChatPage extends StatefulWidget {
  @override
  _ChatPageState createState() => _ChatPageState();
}

class _ChatPageState extends State<ChatPage> {
  List<String> messages = [];
  List<String> chatTargets = [];
  String? selectedChatTarget;
  TextEditingController _textEditingController = TextEditingController();
  WebSocketChannel? _channel=WebSocketChannel.connect(Uri.parse('ws://localhost:8081/chat/flutter'));

  @override
  void initState() {
    super.initState();
    fetchChatTargets();
    connectToWebSocket();
  }

  void fetchChatTargets() async {
    try {
      var response = await http.get(Uri.parse('YOUR_API_ENDPOINT'));
      if (response.statusCode == 200) {
        var data = jsonDecode(response.body);
        setState(() {
          chatTargets = List<String>.from(data['chatTargets']);
          selectedChatTarget = chatTargets.isNotEmpty ? chatTargets[0] : null;
        });
      } else {
        print('Failed to fetch chat targets. Error: ${response.statusCode}');
      }
    } catch (error) {
      print('Failed to fetch chat targets. Error: $error');
    }
  }

  void connectToWebSocket() {
    try {
      _channel?.stream.listen((message) {
        setState(() {
          messages.add(message);
        });
      });
    } catch (error) {
      print('Failed to connect to WebSocket. Error: $error');
    }
  }

  void _sendMessage(String message) {
    if (_channel != null && _channel?.sink != null) {
      _channel?.sink
          .add(jsonEncode({'toUserName': 'apifox', 'message': message}));
    } else {
      print('WebSocket connection is not open.');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Chat'),
      ),
      body: Column(
        children: <Widget>[
          Container(
            padding: EdgeInsets.all(10.0),
            child: DropdownButton<String>(
              value: selectedChatTarget,
              onChanged: (String? newValue) {
                setState(() {
                  selectedChatTarget = newValue;
                });
              },
              items: chatTargets.map((String target) {
                return DropdownMenuItem<String>(
                  value: target,
                  child: Text(target),
                );
              }).toList(),
            ),
          ),
          Expanded(
            child: ListView.builder(
              itemCount: messages.length,
              itemBuilder: (BuildContext context, int index) {
                return ListTile(
                  title: Text(messages[index]),
                );
              },
            ),
          ),
          Divider(),
          Container(
            padding: EdgeInsets.symmetric(horizontal: 10.0),
            child: Row(
              children: <Widget>[
                Expanded(
                  child: TextField(
                    controller: _textEditingController,
                    decoration: InputDecoration(
                      hintText: 'Type a message...',
                    ),
                  ),
                ),
                IconButton(
                  icon: Icon(Icons.send),
                  onPressed: () {
                    if (_textEditingController.text.isNotEmpty) {
                      _sendMessage(_textEditingController.text);
                      setState(() {
                        messages.add(_textEditingController.text);
                        _textEditingController.clear();
                      });
                    }
                  },
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  @override
  void dispose() {
    _channel?.sink.close();
    super.dispose();
  }
}

void main() {
  runApp(MaterialApp(
    title: 'Chat App',
    home: ChatPage(),
  ));
}
