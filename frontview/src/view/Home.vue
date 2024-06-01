<template>
    <div>
        <el-row>

            <el-col :span="3">
                <h2>Social  Media</h2>
                <el-menu @select="handleMenuSelect"  router>
                    <el-menu-item index="chat">聊天</el-menu-item>
                    <el-menu-item index="video">视频</el-menu-item>
                    <el-menu-item index="mall">商城</el-menu-item>
                </el-menu>
            </el-col>
            <el-col :span="20">
                <router-view></router-view>
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
    methods: {
        handleMenuSelect(index) {
            this.$router.push({ path: `/home/${index}` });
        }
    }
}
</script>

<style>
.el-menu {
    height: 100vh;
    border-right: 1px solid #ebeef5;
}
</style>
