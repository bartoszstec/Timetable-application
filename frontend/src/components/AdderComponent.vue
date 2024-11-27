<template>
	<form @submit.prevent="submitForm">
		<div class="wyrownaj">
			<div id="header-wrapper">
				<div class="col-12">
					<header id="header">
						<nav id="nav">
							<router-link to="/addSemester">Dodaj semstr</router-link>
							<router-link to="/addGroup">Dodaj grupę</router-link>
						</nav>
					</header>
				</div>
			</div>
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
        group: '',
		groups: [],
        room: '',
        startTime: '',
        endTime: '',
        dayOfTheWeek: '',
		semester: '',
		semesters: [],
		occurrence: '',
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
				console.log('Pobrane grupy:', response.data);
				this.groups = response.data;
			} catch (error) {
				console.error('Błąd podczas pobierania grup:', error);
			}
		},
		async fetchSemesters() {
			try {
				const token = sessionStorage.getItem('token');
				const response = await axios.get('http://localhost:8080/api/semesters', {
					headers: {
					'Authorization': `Bearer ${token}`
					}

				});
				console.log('Pobrane semstru:', response.data);
				this.semesters = response.data;
			} catch (error) {
				console.error('Błąd podczas pobierania semestrów:', error);
			}
		},
		async submitForm() {
		const token = sessionStorage.getItem('token');
		console.log('Token:', token);
        try {
            const response = await axios.post('http://localhost:8080/api/lessons', {
			name: this.name,
			teacher: this.teacher,
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
			this.$router.push('/schedule');
        } catch (error) {
			console.log('Error :(', error.response?.data || error.message);
        }
      },
    },
	mounted() {
		this.fetchGroups();
		this.fetchSemesters();
	},
};
</script>
