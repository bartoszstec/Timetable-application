<template>
	<form @submit.prevent="submitForm">
		<div class="wyrownaj">
			<div id="header-wrapper">
				<div class="col-12">
					<header id="header">
						<nav id="nav">
							<router-link to="/addSemester">Dodaj semstr</router-link>
							<router-link to="/addLesson">Dodaj lekcję</router-link>
						</nav>
					</header>
				</div>
			</div>
			<h2>Formularz dodawania grup</h2>
			<div class="form-group">
				<label for="name">Nazwa grupy</label>
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
      };
    },
    methods: {
		async submitForm() {
		const token = sessionStorage.getItem('token');
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
        } catch (error) {
			console.log('Error :(', error.response?.data || error.message);
        }
      },
    },
};
</script>