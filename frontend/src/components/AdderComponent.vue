<template>
	<form @submit.prevent="submitForm" class="login-form">
		<div class="wyrownaj">
			<div class="form-group">
				<label for="name">Nazwa zajęć</label>
				<input id="name" name="name" type="text" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="teacher">Prowadzący</label>
				<input id="teacher" name="teacher" type="text" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="studentGroup">Grupa</label>
				<input id="studentGroup" name="studentGroup" type="text" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="room">Sala</label>
				<input id="room" name="room" type="text" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="startTime">Czas rozpoczęcia zajęć</label>
				<input id="startTime" name="startTime" type="time" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="endTime">Czas zakończenia zajęć</label>
				<input id="endTime" name="endTime" type="time" class="form-input" required>
			</div>
			<div class="form-group">
				<label for="dayOfTheWeek">Dzień tygodnia</label>
				<input id="dayOfTheWeek" name="dayOfTheWeek" type="text" class="form-input" required>
			</div>
		</div>
		<button type="submit" class="submit-button">Zapisz</button>
	</form>
</template>

<script>
	export default {
		name: 'AdderComponent'
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
