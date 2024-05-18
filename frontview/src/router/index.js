import Student from '../components/student/Student.vue'
import Login from "../components/Login.vue";
import Course from "@/components/student/Course.vue";
import Score from "@/components/student/Score.vue";
import Details from "@/components/student/Details.vue";
import EducateMain from "@/components/educate/EducateMain.vue";
import EScore from "@/components/educate/EScore.vue";
import EDetails from "@/components/educate/EDetails.vue";
import ECourse from "@/components/educate/ECourse.vue";
import VueRouter from "vue-router";
import Vue from "vue";
import Test from "@/components/Test.vue";
import Menu from "@/components/menu/Menu.vue";

Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    routes:[
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/menu',
            component: Menu
        },
    ]
})
export default router;
