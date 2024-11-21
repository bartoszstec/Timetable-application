<template>
<h2>Formularz Logowania</h2>
<form @submit.prevent="performLogin">
    <label for="login">Login</label>
    <input id="login"  v-model="login" type="text">
    <label for="password">Password</label>
    <input id="password" v-model="password" type="password">
    <button type="submit">zaloguj</button>
</form>
</template>

<script>
import axios from 'axios';

export default {
    name: 'LoginComponent',
data() {
    return {
        login: '',
        password: '',
        loginError: false,
    };
},
methods: {
    async performLogin() {
        //console.log(this.login + this.password);

        try {
            const response = await axios.post('http://localhost:8080/auth/login', {
                email: this.login,
                password: this.password,
            });

            const token = response.data.token;
            sessionStorage.setItem('token', token);

            console.log('Zalogowano pomyślnie');
            console.log(token);
            this.$router.push('/');
    } catch (error){
        console.log('Błąd logowania');
    }
},
},
};
</script>   