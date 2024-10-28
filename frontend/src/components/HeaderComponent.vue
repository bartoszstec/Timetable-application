<template>
      <!-- Header -->
				<div id="header-wrapper">
					<div class="container">
						<div class="row">
							<div class="col-12">

								<header id="header">
									<h1><router-link to="/" id="logo">timetable</router-link></h1>
									<nav id="nav">
										<router-link to="/">Home</router-link>
										<router-link to="/another">Another Page</router-link>
										<router-link to="/schedule">Harmonogram</router-link>
										<router-link to="/showdata">Show data</router-link>
										<router-link to="/addLesson">Add Lesson</router-link>
										<router-link to="/login">Login</router-link>
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