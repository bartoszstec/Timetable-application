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
        sessionStorage.removeItem('token');
  
        // Przekierowanie użytkownika po wylogowaniu
        this.$router.push('/login');
        console.log("Wylogowano pomyślnie");
        console.log("zawartość token:" + sessionStorage.getItem('token'));
      }
    }
  };
  </script>