<template>
    <div id="login">
        <el-header style="text-align: center;font-size: 40px;font-weight: bold;padding-top: 100px;">
            牛魔酬宾
        </el-header>
        <el-container class="center" style="border: #666666 solid 1px">
            <el-main>
                <el-form :model="loginForm" ref="user" label-width="100px" class="demo-ruleForm" id="loginDiv">
                    <el-form-item>
                        <el-header
                                style="text-align: center;font-size: 25px;font-weight: bold;margin-left: -110px;margin-top: 30px">
                            欢迎牛魔
                        </el-header>
                    </el-form-item>
                    <el-form-item label="用户名:" prop="username">
                        <el-input v-model.trim="loginForm.username" style="width: 300px;height: 40px;"
                                  :plain="true" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model.trim="loginForm.password" autocomplete="off"
                                  style="width: 300px;"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button class="loginButton" @click="login" style="width: 100px;height: 40px;align: center;"
                                   type="primary">登录
                        </el-button>
                        <el-button class="registerButton" type="text" @click="dialogVisible = true">注册</el-button>

                        <el-dialog title="表单弹框" :visible.sync="dialogVisible" width="60%">
                            <el-form ref="form" :model="registerForm" label-width="80px">
                                <el-form-item label="账号">
                                    <el-input v-model="registerForm.username"></el-input>
                                </el-form-item>
                                <el-form-item label="密码">
                                    <el-input v-model="registerForm.password"></el-input>
                                </el-form-item>
                            </el-form>
                            <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false;register()">确 定</el-button>
      </span>
                        </el-dialog>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>
<script>

export default {
    data: function () {
        return {
            loginForm: {
                username: '',
                password: ''
            },
            registerForm: {
                username: '',
                password: ''
            },
            dialogVisible: false,
        }
    },

    methods: {
        login() {
            this.$http.post('/login', this.loginForm).then(response => {
                if (response.data.code === 200) {
                    this.$message({
                        message: '登陆成功',
                        type: 'success'
                    });
                    this.$store.commit('setToken', response.data.data.token);
                    this.$store.commit('setUserId', response.data.data.id);
                    this.$router.push('/home');
                    if (response.data.data.status === 1) {
                    } else {
                    }
                } else {
                    this.$message({
                        message: '登陆失败,密码错误或者账号有误',
                        type: 'error'
                    });
                }
            })
        },
        register() {
            this.$http.post('/register', this.registerForm).then(response => {
                if (response.data.code === 200) {
                    this.$message({
                        message: '注册成功',
                        type: 'success'
                    })
                    //注册完自己重新登录
                    this.$router.push('/login')
                } else {
                    this.$message({
                        message: '注册失败',
                        type: 'error'
                    })
                }
            })
        }
    }
}
</script>

<style scoped>
.center {
    width: 500px;
    height: 350px;
    background-color: #ffffff;
    border-radius: 5px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);


    .loginButton {
        position: relative;
        left: 100px;
        top: 10px;
    }

    .registerButton{
        position: relative;
        left: 100px;
        top: 10px;
    }
}

body {
    font-family: Verdana;
    width: 100%;
    text-transform: uppercase;
    background: #fff;
    background-attachment: fixed;
    background-size: cover;
    -webkit-background-size: cover;
    -o-background-size: cover;
    -moz-background-size: cover;
    -ms-background-size: cover;

    /*text-align: center;*/
}

/*.login {*/
/*// 假设你的图片路径为 'assets/image/bg.jpg'*/
/*    background-image: url('@/assets/image/zzzz.jpg');*/
/*    background-repeat: no-repeat;*/
/*    background-size: cover;*/
/*}*/
h1 {
    text-align: center;
    font-size: 33px;
    font-weight: bold;
    margin: 10px 0 35px 0;
    color: #130707;
    letter-spacing: 2px;
    word-spacing: 4px;
}


</style>
