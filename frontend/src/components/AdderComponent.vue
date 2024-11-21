<template>
	<form @submit.prevent="submitForm">
		<div class="wyrownaj">
			<h2>Formularz dodawania zajęć</h2>
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
