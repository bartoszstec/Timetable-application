<template>
    <div>
      Wylogowywanie...
    </div>
  </template>
  
  <script>
  export default {
    name: 'LogoutComponent',
    mounted() {
      this.logout();
    },
    methods: {
      logout() {
        this.$store.commit('clearUserData');
  
        // Przekierowanie użytkownika po wylogowaniu
        this.$router.push('/login');
        console.log("Wylogowano pomyślnie");
      }
    }
  };
  </script>