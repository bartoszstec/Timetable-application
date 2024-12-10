// src/router.js
import { createRouter, createWebHistory } from 'vue-router';
import store from './store';
import ScheduleComponent from './components/ScheduleComponent.vue';
import DataComponent from './components/DataComponent.vue';
import LoginComponent from './components/LoginComponent.vue';
import LogoutComponent from './components/LogoutComponent.vue';
import LessonAdderComponent from './components/LessonAdderComponent.vue';
import GroupAdderComponent from './components/GroupAdderComponent.vue';
import SemesterAdderComponent from './components/SemesterAdderComponent.vue';
import RegisterComponent from './components/RegisterComponent.vue';
import ErrorPage403 from './components/Error403Component.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component:LoginComponent  // Komponent dla strony głównej (strona logowania)
  },
  {
    path: '/login',
    name: 'login',
    component: LoginComponent
  },
  {
    path: '/logout',
    name: 'logout',
    component: LogoutComponent
  },
  {
    path: '/signup',
    name: 'signup',
    component: RegisterComponent
  },
  {
    path: '/schedule',
    name: 'schedule',
    component: ScheduleComponent,  // Komponent dla strony z harmonogramem zajęć
    meta: { requiresAuth: true,}
  },
  {
    path: '/showdata',
    name: 'showdata',
    component: DataComponent,  // Komponent dla strony z harmonogramem zajęć NAUCZYCIELI
    meta: { requiresAuth: true, role: ['TEACHER', 'OFFICE']  }
  },
  {
    path: '/addLesson',
    name: 'addLesson',
    component: LessonAdderComponent,  // Komponent dla strony dodawania lekcji
    meta: { requiresAuth: true, role: 'OFFICE' }
  },
  {
    path: '/addSemester',
    name: 'addSemester',
    component: SemesterAdderComponent,  // Komponent dla strony dodawania semestru
    meta: { requiresAuth: true, role: 'OFFICE' }
  },
  {
    path: '/addGroup',
    name: 'addGroup',
    component: GroupAdderComponent , // Komponent dla strony dodawania grupy
    meta: { requiresAuth: true, role: 'OFFICE' }
  },
  {
    path: '/403',
    name: '403',
    component: ErrorPage403, // Komponent wyświetlający szablon braku autoryzacji
  },
  {
    path: '/500',
    name: '500',
    component: ErrorPage403, // Komponent wyświetlający szablon błędu serwera
  },
];

//instancja routera
const router = createRouter({
  history: createWebHistory(),  // Używa trybu history do nawigacji bez hasha #
  routes,
});

router.beforeEach((to, from, next) => {
  const isAuthenticated = store.getters.isAuthenticated;
  const userRole = store.getters.userRole;

  // console.log('isAuthenticated:', isAuthenticated);
  // console.log('userRole:', userRole);
  // console.log('to.meta.role:', to.meta.role);

  if (to.meta.requiresAuth) {
    if (!isAuthenticated) {
      next('/login');
    } else if (to.meta.role && !to.meta.role.includes(userRole)) {
      next('/403'); // Przekierowanie użytkownika bez odpowiedniej roli
    } else {
      next(); // Użytkownik spełnia wymagania
    }
  } else {
    next();
  }
});


export default router;
