<template>
  <div id="page-wrapper">
  <HeaderComponent></HeaderComponent>

<!-- Globalny kontener alertów -->
<div v-for="(alert, index) in alerts" :key="index" :class="`alert alert-${alert.type}`">
  {{ alert.message }}
</div>
  
  <router-view></router-view>
  <FooterComponent></FooterComponent>
</div>
</template>

<script>
import FooterComponent from './components/FooterComponent.vue';
import HeaderComponent from './components/HeaderComponent.vue';
import { alertStore } from './alert.js';

export default {
  name: 'App',
  components: {
    HeaderComponent,
    FooterComponent
  },
  setup() {
    return {
      alerts: alertStore.alerts, // Reaktywny stan alertów
    };
  },
};
</script>
