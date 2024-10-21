<template>
      <!-- Header -->
				<div id="header-wrapper">
					<div class="container">
						<div class="row">
							<div class="col-12">

								<header id="header">
									<h1><router-link to="/" id="logo">minimaxing</router-link></h1>
									<nav id="nav">
										<a href="index.html" class="current-page-item">Homepage</a>
										<router-link to="/">Home</router-link>
										<router-link to="/another">Another Page</router-link>

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