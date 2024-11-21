<template>
    <h2>Formularz Rejestracji</h2>
    <form @submit.prevent="performRegistration">
      <label for="email">Email</label>
      <input id="email" v-model="email" type="email" required>
  
      <label for="password">Hasło</label>
      <input id="password" v-model="password" type="password" required>
  
      <label for="confirmPassword">Potwierdź hasło</label>
      <input id="confirmPassword" v-model="confirmPassword" type="password" required>
  
      <button type="submit">Zarejestruj się</button>
    </form>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'RegisterComponent',
    data() {
      return {
        email: '',
        password: '',
        confirmPassword: '',
        registrationError: false,
        errorMessage: '',
      };
    },
    methods: {
      async performRegistration() {
        if (this.password !== this.confirmPassword) {
          this.errorMessage = 'Hasła nie pasują do siebie!';
          console.log(this.errorMessage);
          return;
        }
  
        try {
            const response = await axios.post('http://localhost:8080/auth/signup', {
            email: this.email,
            password: this.password,
          });

          console.log('Zarejestrowano pomyślnie:', response.data);
          this.$router.push('/login');
        } catch (error) {
          this.registrationError = true;
          this.errorMessage = 'Błąd rejestracji. Spróbuj ponownie.';
          console.log(this.errorMessage);
        }
      },
    },
  };
  </script>
  