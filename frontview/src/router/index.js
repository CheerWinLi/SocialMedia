import VueRouter from "vue-router";
import Vue from "vue";
import Login from "@/view/Login.vue";
import Home from "@/view/Home.vue";

Vue.use(VueRouter)
// 解决编程式路由往同一地址跳转时会报错的情况
const originalPush = VueRouter.prototype.push;
const originalReplace = VueRouter.prototype.replace;

// push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalPush.call(this, location, onResolve, onReject);
    return originalPush.call(this, location).catch(err => err);
};

//replace
VueRouter.prototype.replace = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalReplace.call(this, location, onResolve, onReject);
    return originalReplace.call(this, location).catch(err => err);
};
const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/home',
            component: Home,
            children: [
                {
                    name: "Chat",
                    path: "chat",
                    component: () => import("../components/chat/Chat.vue"),
                },
                {
                    name: "Video",
                    path: "video",
                    component: () =>  import("../components/video/Video.vue"),
                },
                {
                    name: "Friends",
                    path: "group",
                    component: () => import("../view/Group"),
                }
            ]
        }
    ]
})


export default router;
