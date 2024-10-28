<template>
<h2>Formularz Logowania</h2>
<form @submit.prevent="submitForm">
    <label for="login">Login</label>
    <input id="login" name="login" type="text">
    <label for="password">Password</label>
    <input id="password" name="password" type="text">
</form>
</template>

<script>
export default {
    name: 'LoginComponent'
};
</script>   