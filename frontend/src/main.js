import { createApp } from 'vue';
import App from './App.vue';
import router from './router';  // Import routera
import store from './store';
import './assets/css/main.css';  // Globalny CSS

createApp(App)
  .use(store)
  .use(router)
  .mount('#app');