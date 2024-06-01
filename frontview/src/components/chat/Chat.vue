<template>
    <div class="chat-container">
        <el-row>
            <!-- 侧边栏 -->
            <el-col :span="6">
                <el-card class="sidebar">
                    <el-list>
                        <el-list-item
                                v-for="(user, index) in users"
                                :key="index"
                                @click="selectUser(user)"
                                :class="{'active-user': user.userId === selectedUserId}"
                        >
                            {{ user.username }}
                        </el-list-item>
                    </el-list>
                </el-card>
            </el-col>

            <!-- 聊天框 -->
            <el-col :span="18">
                <el-card class="chat-box">
                    <!-- 聊天消息展示区域 -->
                    <div class="chat-messages" ref="chatMessages">
                        <div
                                v-for="(message, index) in currentMessages"
                                :key="index"
                                :class="{'self-message': message.sender === username, 'other-message': message.sender !== username}"
                        >
                            <p v-if="message.sender === username">{{ message.message }} : <strong>{{
                                message.sender
                                }}</strong></p>
                            <p v-else><strong>{{ message.sender }}</strong>: {{ message.message }}</p>
                        </div>
                    </div>
                    <!-- 聊天输入框 -->
                    <div class="chat-input">
                        <el-input v-model="newMessage" placeholder="请输入消息"></el-input>
                        <el-button @click="sendMessage" type="primary">发送</el-button>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            messages: {}, // 用于存储不同用户的消息记录
            newMessage: '',
            ws: null,
            username: this.$store.state.userId,
            users: [], // 聊天对象列表
            selectedUserId: null, // 当前选中的聊天对象ID
        };
    },
    computed: {
        currentMessages() {
            return this.messages[this.selectedUserId] || [];
        }
    },
    created() {
        this.initWebSocket();
        // this.fetchUsers();
        this.users = [
            {"userId": "user1", "username": "User 1"},
            {"userId": "user2", "username": "User 2"},
            {"userId": "user3", "username": "User 3"}
        ]
    },
    methods: {
        initWebSocket() {
            const userId = this.$store.state.userId; // Replace with actual userId
            this.ws = new WebSocket(`ws://localhost:8089/chat/${userId}`);

            this.ws.onopen = () => {
                console.log('WebSocket connection opened');
            };

            this.ws.onmessage = (event) => {
                console.log('收到消息:', event.data);
                const message = JSON.parse(event.data);
                console.log(message.sender, message.message);
                this.addMessage(message);
                this.$nextTick(() => {
                    const chatMessages = this.$refs.chatMessages;
                    chatMessages.scrollTop = chatMessages.scrollHeight;
                });
            };

            this.ws.onclose = () => {
                console.log('WebSocket connection closed');
            };

            this.ws.onerror = (error) => {
                console.error('WebSocket error:', error);
            };
        },
        sendMessage() {
            if (this.newMessage.trim() !== '' && this.selectedUserId) {
                const message = {
                    sender: this.username, // Replace with actual username
                    message: this.newMessage,
                    receiver: this.selectedUserId, // 使用选中的聊天对象ID
                    tag: "PERSONAL_MESSAGE"
                };
                this.ws.send(JSON.stringify(message));
                this.addMessage(message);
                this.newMessage = '';
            }
        },
        addMessage(message) {
            if (!this.messages[message.toUserName]) {
                this.messages[message.toUserName] = [];
            }
            if (!this.messages[message.sender]) {
                this.messages[message.sender] = [];
            }
            this.messages[message.toUserName].push(message);
            this.messages[message.sender].push(message);
        },
        fetchUsers() {
            axios.get('/api/chat-users')
                .then(response => {
                    this.users = response.data;
                })
                .catch(error => {
                    console.error('Error fetching users:', error);
                });
        },
        selectUser(user) {
            this.selectedUserId = user.userId;
            // 选中用户后，自动滚动到最新消息
            this.$nextTick(() => {
                const chatMessages = this.$refs.chatMessages;
                chatMessages.scrollTop = chatMessages.scrollHeight;
            });
        },
    },
    beforeDestroy() {
        if (this.ws) {
            this.ws.close();
        }
    }
};
</script>

<style scoped>
.chat-container {
    height: 100vh;
    background-color: #f5f5f5;
}

.sidebar {
    height: 80vh;
    overflow-y: auto;
}

.chat-box {
    height: 80vh;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.chat-messages {
    flex-grow: 1;
    overflow-y: auto;
    padding: 10px;
    background-color: #fff;
    border-bottom: 1px solid #ebeef5;
}

.chat-input {
    display: flex;
    padding: 10px;
    background-color: #fff;
}

.self-message {
    text-align: right;
    background-color: #d1e7dd; /* 绿色背景 */
    padding: 10px;
    border-radius: 5px;
    margin-bottom: 10px;
}

.other-message {
    text-align: left;
    background-color: #cce5ff; /* 蓝色背景 */
    padding: 10px;
    border-radius: 5px;
    margin-bottom: 10px;
}

.active-user {
    background-color: #e6f7ff;
}
</style>
