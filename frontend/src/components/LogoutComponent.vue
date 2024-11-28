<template>
    <div>
      Wylogowywanie...
    </div>
  </template>
  
  <script>
  export default {
    name: 'LogoutComponent',
    mounted() {
      this.confirmLogout();
    },
    methods: {
      confirmLogout() {
      const confirmed = window.confirm('Czy na pewno chcesz się wylogować?');
      if (confirmed) {
        this.logout();
      } else {
        // Opcjonalne przekierowanie, jeśli użytkownik zrezygnuje z wylogowania
        this.$router.push('/schedule');
      }
    },
      logout() {
        this.$store.commit('clearUserData');
        // Przekierowanie użytkownika po wylogowaniu
        this.$router.push('/login');
        console.log("Wylogowano pomyślnie");
      }
    }
  };
  </script>