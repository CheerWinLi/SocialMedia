import VueRouter from "vue-router";
import Vue from "vue";
import Menu from "@/components/menu/Menu.vue";
import Login from "@/components/Login.vue";
import Chat from "@/components/chat/Chat.vue";
import Video from "@/components/video/Video.vue";

Vue.use(VueRouter)

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
            path: '/menu',
            component: Menu
        },
        {
            path: '/chat',
            component: Chat
        },{
            path: '/video',
            component: Video
        }
    ]
})
export default router;
