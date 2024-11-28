<template>
    <h1>403 - Brak dostępu</h1>
    <p>Nie masz uprawnień do przeglądania tej strony</p>
    <router-link to="/schedule">Wróć na stronę główną</router-link>
</template>


<script>
export default {
  name: 'ErrorPage403',
};
</script>