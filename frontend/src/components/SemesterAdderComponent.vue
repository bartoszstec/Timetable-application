<template>
	<form @submit.prevent="submitForm">
		<div class="wyrownaj">
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
        startTime: '',
        endTime: '',
      };
    },
    methods: {
		async submitForm() {
		const token = sessionStorage.getItem('token');
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
        } catch (error) {
			console.log('Error :(', error.response?.data || error.message);
        }
      },
    },
};
</script>
