<template>
<div class="wyrownaj">
<h2>Formularz Logowania</h2>
<form @submit.prevent="performLogin">
    <div class="form-group">
        <label for="login">Login</label>
        <input id="login"  v-model="login" type="text" class="form-input">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input id="password" v-model="password" type="password" class="form-input">
    </div>
    <button type="submit" class="submit-button">zaloguj</button>
</form>
</div>
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

        try {
            const response = await axios.post('http://localhost:8080/auth/login', {
                email: this.login,
                password: this.password,
            });

            const token = response.data.token;
            sessionStorage.setItem('token', token);

            console.log('Zalogowano pomyślnie');
            console.log(token);
            this.$router.push('/schedule');
    } catch (error){
        console.log('Błąd logowania');
    }
},
},
};
</script>   