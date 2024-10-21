// src/router.js
import { createRouter, createWebHistory } from 'vue-router';
import HelloWorld from './components/HelloWorld.vue';
import AnotherComponent from './components/AnotherComponent.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HelloWorld  // Komponent dla strony głównej
  },
  {
    path: '/another',
    name: 'another',
    component: AnotherComponent  // Komponent dla innej strony
  }
];

// Tworzymy instancję routera
const router = createRouter({
  history: createWebHistory(),  // Używa trybu history do nawigacji bez hasha #
  routes
});

export default router;