<template>
      <!-- Header -->
				<div id="header-wrapper">
					<div class="container">
						<div class="row">
							<div class="col-12">

								<header id="header">
									<h1><router-link to="/schedule" id="logo">timetable</router-link></h1>
									<nav id="nav">
										<router-link to="/schedule">Harmonogram</router-link>
                    <router-link to="/showdata" v-if="isRole('OFFICE') || isRole('TEACHER')">Harmonogram pracowników</router-link>
                    <router-link to="/addLesson" v-if="isRole('OFFICE')">Dodawanie</router-link>
                    <router-link to="/signup" v-if="!isAuthenticated">Zarejestruj się</router-link>
                    <router-link to="/signup" v-if="isRole('OFFICE')">Rejestracja użytkowników</router-link>
                    <router-link to="/login" v-if="!isAuthenticated">Zaloguj się</router-link>
                    <router-link to="/logout" v-if="isAuthenticated">Wyloguj się</router-link>
									</nav>
								</header>

							</div>
						</div>
					</div>
				</div>
  </template>
  
  <script>
import { mapGetters } from 'vuex';

export default {
  name: 'HeaderComponent',
  computed: {
        ...mapGetters(['isAuthenticated', 'userRole']),
    },
  methods: {
        isRole(expectedRole) {
            return this.userRole === expectedRole;
        },
  },

};
</script>