<template>
    <div>
      <h2>Lista Lekcji</h2>
      <div v-if="loading">Ładowanie danych...</div>
      <div v-else-if="error">{{ error }}</div>
      <div v-else>
        <ul>
          <li v-for="lesson in lessons" :key="lesson.id">
            {{ lesson.name }} - {{ lesson.room }} - {{ lesson.teacher }} - {{ lesson.id }}
            <!-- wszystkie kolumny tabeli lesson: id, day_of_the_week, end_time, name, room, start_time, student_group, teacher, semestr_id -->
          </li>
        </ul>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'DataComponent',
    data() {
      return {
        lessons: [], // Tutaj będą przechowywane dane
        loading: true, // Flaga ładowania
        error: null // Flaga błędu
      };
    },
    mounted() {
      this.fetchLessons();
    },
    methods: {
      async fetchLessons() {
        try {
          const response = await axios.get('http://localhost:8080/lessons'); 
          this.lessons = response.data; // Przypisz otrzymane dane do stanu komponentu
        } catch (err) {
          this.error = 'Błąd podczas ładowania danych'; // Obsłuż błąd
        } finally {
          this.loading = false; // Zmień flagę ładowania po zakończeniu żądania
          console.log(this.lessons);
        }
      }
    }
  };
  </script>