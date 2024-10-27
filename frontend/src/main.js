import { createApp } from 'vue';
import App from './App.vue';
import router from './router';  // Import routera
import './assets/css/main.css';  // Globalny CSS

createApp(App)
  .use(router)
  .mount('#app');