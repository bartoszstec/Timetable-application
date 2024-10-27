<template>
  <div id="page-wrapper">
  <HeaderComponent></HeaderComponent>
  <router-view></router-view>
  <FooterComponent></FooterComponent>
</div>
</template>

<script>
import FooterComponent from './components/FooterComponent.vue';
import HeaderComponent from './components/HeaderComponent.vue';


export default {
  name: 'App',
  components: {
    HeaderComponent,
    FooterComponent
  }
}
</script>
