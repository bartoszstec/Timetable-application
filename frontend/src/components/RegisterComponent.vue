<template>
  <div class="wyrownaj">
    <h2>Formularz Rejestracji</h2>
    <form @submit.prevent="performRegistration">
      <div class="form-group">
      <label for="email">Email</label>
      <input id="email" v-model="email" type="email" class="form-input" required>
      </div>
      <div class="form-group">
      <label for="password">Hasło</label>
      <input id="password" v-model="password" type="password" class="form-input" required>
      </div>
      <div class="form-group">
      <label for="confirmPassword">Potwierdź hasło</label>
      <input id="confirmPassword" v-model="confirmPassword" type="password" class="form-input" required>
      </div>
      <div class="form-group">
        <label for="role">Rola</label>
        <select id="role" v-model="role" class="form-input" required>
          <option value="" disabled>Wybierz rolę</option>
          <option v-if="Array.isArray(groups) && groups.length > 0" value="STUDENT">Student</option>
          <option value="TEACHER">Wykładowca</option>
          <option value="OFFICE">Dziekanat</option>
        </select>
      </div>
      <div v-if="Array.isArray(groups) && this.role == 'STUDENT'" class="form-group">
        <label for="group">Grupa</label>
        <select id="group" v-model="group" class="form-input">
          <option value="" disabled>Wybierz grupę</option>
          <option v-for="group in groups" :key="group.id" :value="group.id">
            {{ group.name }}
          </option>
        </select>
      </div>
      <div v-if="errorMessage" class="error-message">
        {{ errorMessage }}
      </div>
      <button type="submit" class="submit-button">Zarejestruj się</button>
    </form>
  </div>
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
      role: '',
      group: '',
      groups: [],
      errorMessage: '',
    };
  },
  methods: {
    async fetchGroups() {
      try {
        const token = sessionStorage.getItem('token');
        const response = await axios.get('http://localhost:8080/api/groups', {
            headers: {
              'Authorization': `Bearer ${token}`
            }

        });
        this.groups = response.data;
      } catch (error) {
        console.error('Błąd podczas pobierania grup:', error);
      }
    },
    async performRegistration() {
      if (this.password !== this.confirmPassword) {
        this.errorMessage = 'Hasła nie pasują do siebie!';
        console.log(this.errorMessage);
        return;
      }

      if(this.role != 'STUDENT'){
        this.group = '';
      }

      try {
        const response = await axios.post('http://localhost:8080/auth/signup', {
          email: this.email,
          password: this.password,
          role: this.role,
          studentGroupId: this.group,
        });

        console.log('Zarejestrowano pomyślnie:', response.data);
        this.$router.push('/login');
      } catch (error) {
        this.errorMessage = 'Błąd rejestracji. Spróbuj ponownie.';
        console.log(this.errorMessage);
      }
    },
  },
  mounted() {
    this.fetchGroups();
  },
};
</script>

<style scoped>
.error-message {
  color: red;
  margin-top: 10px;
  font-weight: bold;
}
</style>

  