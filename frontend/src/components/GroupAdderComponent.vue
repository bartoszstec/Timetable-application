<template>
	<form @submit.prevent="submitForm">
		<div class="wyrownaj">
			<div id="header-wrapper">
				<div class="col-12">
					<header id="header">
						<nav id="nav">
							<router-link to="/addSemester">Dodaj semestr</router-link>
							<router-link to="/addLesson">Dodaj lekcję</router-link>
						</nav>
					</header>
				</div>
			</div>
			<h2>Formularz dodawania grup</h2>
			<div v-if="infoMessage" class="info-message">
				{{ infoMessage }}
			</div>
			<div class="form-group">
				<label for="name">Nazwa grupy</label>
				<input id="name" v-model="name" name="name" type="text" class="form-input" required>
			</div>
			<div v-if="errorMessage" class="error-message">
				{{ errorMessage }}
			</div>
			<button type="submit" class="submit-button">Zapisz</button>
		</div>
	</form>
</template>

<script>
import axios from 'axios';
export default {
	name: 'AdderComponent',
	data() {
      return {
        name: '',
		infoMessage: '',
		errorMessage: '',
      };
    },
    methods: {
		async submitForm() {
			this.infoMessage = '';
			this.errorMessage = '';
			const token = this.$store.state.token;
			console.log('Token:', token);
			try {
				const response = await axios.post('http://localhost:8080/api/groups', {
					name: this.name,
				},{
					headers: {
						'Authorization': `Bearer ${token}`,
					},
				});
				console.log('Dodano :)',response.data);
				this.name = '';
				this.infoMessage = "Pomyślnie dodano grupę";
			} catch (error) {
				console.log('Error :(', error.response?.data || error.message);
				this.errorMessage = error.response.data.message;
			}
		},
    },
};
</script>

<style scoped>
.error-message {
  color: red;
  margin-top: 10px;
  font-weight: bold;
}
.info-message {
  color: green;
  margin-top: 10px;
  font-weight: bold;
}
</style>
