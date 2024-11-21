// src/router.js
import { createRouter, createWebHistory } from 'vue-router';
import ScheduleComponent from './components/ScheduleComponent.vue';
import DataComponent from './components/DataComponent.vue';
import LoginComponent from './components/LoginComponent.vue';
import LogoutComponent from './components/LogoutComponent.vue';
import AdderComponent from './components/AdderComponent.vue';
import RegisterComponent from './components/RegisterComponent.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component:LoginComponent  // Komponent dla strony głównej (strona logowania)
  },
  {
    path: '/schedule',
    name: 'schedule',
    component: ScheduleComponent  // Komponent dla strony z harmonogramem zajęć
  },
  {
    path: '/showdata',
    name: 'showdata',
    component: DataComponent  // Komponent testowy wyświetlający dane z backendu
  },
  {
    path: '/login',
    name: 'login',
    component: LoginComponent  // Komponent dla strony logowania
  },
  {
    path: '/signup',
    name: 'signup',
    component: RegisterComponent  // Komponent dla strony rejestroania
  },
  {
    path: '/logout',
    name: 'logout',
    component: LogoutComponent  // Komponent dla wylogowania
  },
  {
    path: '/addLesson',
    name: 'addLesson',
    component: AdderComponent  // Komponent dla strony dodawania lekcji
  }
  
];

// Tworzymy instancję routera
const router = createRouter({
  history: createWebHistory(),  // Używa trybu history do nawigacji bez hasha #
  routes
});

export default router;
