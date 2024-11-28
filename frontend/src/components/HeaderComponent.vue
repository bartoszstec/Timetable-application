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
										<router-link to="/showdata">Harmonogram pracowników</router-link>
										<router-link to="/addLesson">Add Lesson</router-link>
										<router-link to="/signup">Zarejestruj się</router-link>
										<router-link to="/login">Zaloguj się</router-link>
										<router-link to="/logout">Wyloguj się</router-link>
									</nav>
								</header>

							</div>
						</div>
					</div>
				</div>
  </template>
  
  <script>
  export default {
    name: 'HeaderComponent'
  };
  </script>