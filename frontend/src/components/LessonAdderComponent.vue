<template>
	<div class="wyrownaj">
		<form @submit.prevent="submitForm">
			<div id="header-wrapper">
				<div class="col-12">
					<header id="header">
						<nav id="nav">
							<router-link to="/addSemester">Dodaj semestr</router-link>
							<router-link to="/addGroup">Dodaj grupę</router-link>
						</nav>
					</header>
				</div>
			</div>
			<h2>Formularz dodawania zajęć</h2>
			<div v-if="infoMessage" class="info-message">
				{{ infoMessage }}
			</div>
			<div class="form-group">
				<label for="name">Nazwa zajęć</label>
				<input id="name" v-model="name" name="name" type="text" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="teacher">Nauczyciel</label>
				<select id="teacher" v-model="teacher" class="form-input">
				<option value="NULL" disabled>Wybierz grupę</option>
				<option v-for="teacher in teachers" :key="teacher.id" :value="teacher.id">
					{{ teacher.firstName }} {{ teacher.lastName }}
				</option>
				</select>
			</div>
			<div class="form-group">
				<label for="group">Grupa</label>
				<select id="group" v-model="group" class="form-input">
				<option value="NULL" disabled>Wybierz grupę</option>
				<option v-for="group in groups" :key="group.id" :value="group.id">
					{{ group.name }}
				</option>
				</select>
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
				<select id="dayOfTheWeek" v-model="dayOfTheWeek" class="form-input" required>
					<option value="" disabled>Wybierz dzień tygonia</option>
					<option value="Monday">Poniedzialek</option>
					<option value="Tuesday">Wtorek</option>
					<option value="Wednesday">Środa</option>
					<option value="Thursday">Czwartek</option>
					<option value="Friday">Piątek</option>
					<option value="Saturday">Sobota</option>
					<option value="Sunday">Niedziela</option>
				</select>
			</div>
			<div class="form-group">
				<label for="occurrence">Wystepowanie</label>
				<select id="occurrence" v-model="occurrence" class="form-input" required>
					<option value="even">Parzyste</option>
					<option value="odd">Nieparzyste</option>
					<option value="all">Wszystkie</option>
				</select>
			</div>
			<div class="form-group">
				<label for="semester">Semestr</label>
				<select id="semester" v-model="semester" class="form-input">
				<option value="NULL" disabled>Wybierz grupę</option>
				<option v-for="semester in semesters" :key="semester.id" :value="semester.id">
					{{ semester.name }}
				</option>
				</select>
			</div>
			<div v-if="errorMessage" class="error-message">
				{{ errorMessage }}
			</div>
			<button type="submit" class="submit-button">Zapisz</button>
		</form>

		<div class="section">
			<br><br><br><br><br><br>
				<h3>Lista zajęć</h3>
				<ul v-if="lessons.length">
					<li v-for="lesson in lessons" :key="lesson.id">
						<strong>{{ lesson.name }}</strong> pokój: {{ lesson.room }} - ({{ lesson.startTime }} - {{ lesson.endTime }} - {{ lesson.dayOfTheWeek }} - {{ lesson.semester.name }} - {{ lesson.occurrence }})
						<button @click="deleteLesson(lesson.id)">Usuń</button>
						<br><br>
					</li>
				</ul>
				<p v-else>Brak zajęć do wyświetlenia.</p>
		</div>
	</div>
</template>

<script>
import axios from 'axios';
export default {
	name: 'LessonAdderComponent',
	data() {
      return {
        name: '',
        group: '',
		groups: [],
        room: '',
        startTime: '',
        endTime: '',
        dayOfTheWeek: '',
		semester: '',
		semesters: [],
		teacher: '',
		teachers: [],
		occurrence: '',
		infoMessage: '',
		errorMessage: '',
		lessons: [],
      };
    },
    methods: {
		async fetchGroups() {
			try {
				const token = this.$store.state.token;
				const response = await axios.get('http://localhost:8080/api/groups', {
					headers: {
					'Authorization': `Bearer ${token}`
					}

				}); 
				console.log('Pobrane grupy:', response.data);
				this.groups = response.data;
			} catch (error) {
				console.error('Błąd podczas pobierania grup:', error);
				this.errorMessage = error.response.data.message;
			}
		},
		async fetchSemesters() {
			try {
				const token = this.$store.state.token;
				const response = await axios.get('http://localhost:8080/api/semesters', {
					headers: {
					'Authorization': `Bearer ${token}`
					}

				});
				console.log('Pobrane semstry:', response.data);
				this.semesters = response.data;
			} catch (error) {
				console.error('Błąd podczas pobierania semestrów:', error);
				this.errorMessage = error.response.data.message;
			}
		},
		async fetchTeachers() {
			try {
				const token = this.$store.state.token;
				const response = await axios.get('http://localhost:8080/api/teachers', {
					headers: {
					'Authorization': `Bearer ${token}`
					}

				});
				this.teachers = response.data;
			} catch (error) {
				console.error('Błąd podczas pobierania nauczycieli:', error);
				this.errorMessage = error.response.data.message;
			}
		},
		async fetchLessons() {
		const token = this.$store.state.token;
			try {
				const response = await axios.get('http://localhost:8080/api/lessons', {
				headers: { 'Authorization': `Bearer ${token}` }
				});
				this.lessons = response.data;
				console.log(response.data);
			} catch (err) {
				this.errorMessage = 'Błąd podczas ładowania zajęć';
				console.error(err);
			}
			},
		async submitForm() {
			this.infoMessage = '';
			this.errorMessage = '';
			const token = this.$store.state.token;
			console.log('Token:', token);
			try {
				const response = await axios.post('http://localhost:8080/api/lessons', {
				name: this.name,
				teacherId: this.teacher,
				studentGroupId: this.group,
				room: this.room,
				startTime: this.startTime,
				endTime: this.endTime,
				dayOfTheWeek: this.dayOfTheWeek,
				occurrence: this.occurrence,
				semesterId: this.semester,
			},{
				headers: {
					'Authorization': `Bearer ${token}`,
				},
			});
				console.log('Dodano :)',response.data);
				this.infoMessage = "Pomyślnie dodano lekcję";
				await this.fetchLessons();
			} catch (error) {
				console.log('Error :(', error.response?.data || error.message);
				this.errorMessage = error.response.data.message;
			}
		},
		async deleteLesson(id) {
		const token = this.$store.state.token;
			try {
				await axios.delete(`http://localhost:8080/api/lessons/${id}`, {
					headers: { 'Authorization': `Bearer ${token}` }
				});
				this.infoMessage = 'Zajęcia zostały pomyślnie usunięte.';
				await this.fetchLessons(); // Odśwież listę zajęć
			} catch (err) {
				this.errorMessage = 'Błąd podczas usuwania zajęć.';
			}
		},
    },
	mounted() {
		this.fetchGroups();
		this.fetchSemesters();
		this.fetchTeachers();
		this.fetchLessons();
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