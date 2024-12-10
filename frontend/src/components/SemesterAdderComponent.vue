<template>
	<div class="wyrownaj">
		<form @submit.prevent="submitForm">
		
			<div id="header-wrapper">
				<div class="col-12">
					<header id="header">
						<nav id="nav">
							<router-link to="/addLesson">Dodaj lekcję</router-link>
							<router-link to="/addGroup">Dodaj grupę</router-link>
						</nav>
					</header>
				</div>
			</div>
			<h2>Formularz dodawania semestrów</h2>
			<div v-if="infoMessage" class="info-message">
				{{ infoMessage }}
			</div>
			<div class="form-group">
				<label for="startTime">Czas rozpoczęcia zajęć</label>
				<input id="startTime" v-model="startTime" name="startTime" type="date" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="endTime">Czas zakończenia zajęć</label>
				<input id="endTime" v-model="endTime" name="endTime" type="date" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="name">Nazwa semestru</label>
				<input id="name" v-model="name" name="name" type="text" class="form-input" required>
			</div>
			<div v-if="errorMessage" class="error-message">
				{{ errorMessage }}
			</div>
			<button type="submit" class="submit-button">Zapisz</button>
		</form>

		<div class="section">
			<br><br><br><br><br><br>
				<h3>Lista semestrów</h3>
				<ul v-if="semesters.length">
					<li v-for="semester in semesters" :key="semester.id">
						<strong>{{ semester.name }}</strong> ({{ semester.startDate }} - {{ semester.endDate }})
						<button @click="deleteSemester(semester.id)">Usuń</button>
						<br><br>
					</li>
				</ul>
				<p v-else>Brak semestrów do wyświetlenia.</p>
		</div>
	</div>
</template>

<script>
import axios from 'axios';
export default {
	name: 'AdderComponent',
	data() {
      return {
        name: '',
        startTime: '',
        endTime: '',
		infoMessage: '',
		errorMessage: '',
		semesters: [],
      };
    },
    methods: {
		async submitForm() {
			this.infoMessage = '';
			this.errorMessage = '';
			const token = this.$store.state.token;
			console.log('Token:', token);
			try {
				const response = await axios.post('http://localhost:8080/api/semesters', {
				name: this.name,
				endDate: this.endTime,
				startDate: this.startTime,
			},{
				headers: {
					'Authorization': `Bearer ${token}`,
				},
			});
				console.log('Dodano :)',response.data);
				this.name = '';
				this.startTime = '';
				this.endTime = '';
				this.infoMessage = "Pomyślnie dodano semestr";

				await this.fetchSemesters();
			} catch (error) {
				console.log('Error :(', error.response?.data || error.message);
				this.errorMessage = error.response.data.message;
			}
		},
	async fetchSemesters() {
		const token = this.$store.state.token;
			try {
				const response = await axios.get('http://localhost:8080/api/semesters', {
				headers: { 'Authorization': `Bearer ${token}` }
				});
				this.semesters = response.data;
			} catch (err) {
				this.errorMessage = 'Błąd podczas ładowania semestrów';
				console.error(err);
			}
			},
	async deleteSemester(id) {
		const token = this.$store.state.token;
			try {
				await axios.delete(`http://localhost:8080/api/semesters/${id}`, {
					headers: { 'Authorization': `Bearer ${token}` }
				});
				this.infoMessage = 'Semestr został pomyślnie usunięty.';
				await this.fetchSemesters(); // Odśwież listę semestrów
			} catch (err) {
				this.errorMessage = 'Błąd podczas usuwania semestru.';
			}
		},
		
    },
	mounted() {
		this.fetchSemesters();
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
