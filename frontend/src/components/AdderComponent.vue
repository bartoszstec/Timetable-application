<template>
	<form @submit.prevent="submitForm" class="login-form">
		<div class="wyrownaj">
			<div class="form-group">
				<label for="name">Nazwa zajęć</label>
				<input id="name" v-model="name" name="name" type="text" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="teacher">Prowadzący</label>
				<input id="teacher" v-model="teacher" name="teacher" type="text" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="studentGroup">Grupa</label>
				<input id="studentGroup" v-model="studentGroup" name="studentGroup" type="text" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="room">Sala</label>
				<input id="room" v-model="room" name="room" type="text" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="startTime">Czas rozpoczęcia zajęć</label>
				<input id="startTime" v-model="startTime" name="startTime" type="time" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="endTime">Czas zakończenia zajęć</label>
				<input id="endTime" v-model="endTime" name="endTime" type="time" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="dayOfTheWeek">Dzień tygodnia</label>
				<input id="dayOfTheWeek" v-model="dayOfTheWeek" name="dayOfTheWeek" type="text" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="dayOfTheWeek">Semetr</label>
				<input id="semester" v-model="semester" name="semester" type="text" class="form-input" required>
			</div>
		</div>
		<button type="submit" class="submit-button">Zapisz</button>
	</form>
</template>

<script>
import axios from 'axios';
export default {
	name: 'AdderComponent',
	data() {
      return {
        name: '',
        teacher: '',
        studentGroup: '',
        room: '',
        startTime: '',
        endTime: '',
        dayOfTheWeek: '',
		semester: '',
      };
    },
    methods: {
		async submitForm() {
		const token = sessionStorage.getItem('token');
		console.log('Token:', token);
        try {
            const response = await axios.post('http://localhost:8080/api/lessons', {
			name: this.name,
			teacher: this.teacher,
			studentGroup: this.studentGroup,
			room: this.room,
			startTime: this.startTime,
			endTime: this.endTime,
			dayOfTheWeek: this.dayOfTheWeek,
			semesterId: 1,
        },{
			headers: {
				'Authorization': `Bearer ${token}`,
			},
        });

			console.log('Dodano :)',response.data);
			this.$router.push('/');
        } catch (error) {
			console.log('Error :(', error.response?.data || error.message);
        }
      },
    },
};
</script>

<style>
.wyrownaj {
	max-width: 400px;
	margin-left: auto;
	margin-right: auto;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 8px;
	background-color: #f9f9f9;
}

.form-group {
	margin-bottom: 15px;
}

label {
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
}

.form-input {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
	transition: border-color 0.3s;
}

.form-input:focus {
	border-color: #2196F3; /* Kolor obramowania przy focussie */
	outline: none; /* Usunięcie standardowej obramowania */
}

.submit-button {
	padding: 10px 15px;
	background-color: #077e25;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
	transition: background-color 0.3s;
	position: absolute;
	right: 30%; /* Ustawienie przycisku 30% od prawej krawędzi */
}

.submit-button:hover {
	background-color: #034213; /* Ciemniejszy kolor przy najechaniu */
}
</style>
