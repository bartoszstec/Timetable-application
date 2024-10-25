// src/router.js
import { createRouter, createWebHistory } from 'vue-router';
import AnotherComponent from './components/AnotherComponent.vue';
import ScheduleComponent from './components/ScheduleComponent.vue';
import HelloWorld from './components/HelloWorld.vue';
import DataComponent from './components/DataComponent.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component:HelloWorld  // Komponent dla strony głównej
  },
  {
    path: '/another',
    name: 'another',
    component: AnotherComponent  // Komponent dla innej strony
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
  }
  
];

// Tworzymy instancję routera
const router = createRouter({
  history: createWebHistory(),  // Używa trybu history do nawigacji bez hasha #
  routes
});

export default router;