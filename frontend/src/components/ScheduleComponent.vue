<template>
  <br>
  <div class="semester-selector">
      <label for="semester">Wybierz semestr:</label><br>
      <select v-model="selectedSemester" @change="fetchLessons">
        <option v-for="semester in semesters" :key="semester.id" :value="semester.id">
          {{ semester.id }}
        </option>
      </select>
    </div>
  <br>
  <div class="schedule-container">
    <div class="student-groups">
      <div v-for="lesson in lessons" :key="lesson.id" class="student-group" :class="{ selected: lesson.studentGroup === selectedGroup }" @click="setSelectedGroup(lesson.studentGroup)">
        {{ lesson.studentGroup }}
        <br>-----------------------------------
      </div>
    </div>
  <div class="schedule">
    <div class="header">
      <div class="time-column"></div>
      <div class="day-column" v-for="day in days" :key="day">
        {{ day }}
      </div>
    </div>
    <div class="content">
      <div class="time-row" v-for="hour in hours" :key="hour">
        <div class="time-cell">{{ hour }}</div>
        <div class="day-cell" v-for="day in days" :key="day">
          <div class="quarter-cell" v-for="quarter in 4" :key="quarter">
            <div v-if="getLessonAtTime(day, hour, quarter)">
              <div class="lesson">
                <span class="lesson-id">{{ getLessonAtTime(day, hour, quarter).startTime }} - </span> <span class="lesson-id">{{ getLessonAtTime(day, hour, quarter).endTime }} </span> 
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios';
import { alertStore } from '@/alert.js';

export default {
  name: 'ScheduleComponent',
  data() {
    return {
      days: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"],
      hours: [
        "08:00", "09:00", "10:00", "11:00", "12:00", 
        "13:00", "14:00", "15:00", "16:00", 
        "17:00", "18:00", "19:00", "20:00"
      ],
      lessons: [],
      loading: true,
      error: null,
      selectedGroup: null,
    };
  },
  mounted() {
    this.fetchSemesters();
  },
  methods: {
    checkAuthorization() {
    const token = sessionStorage.getItem('token');
    if (!token) {
      this.$router.push({ path: '/login' });
      alertStore.addAlert('Zaloguj się, aby uzyskać dostęp do tej strony.', 'info');
      return false;
    }
    return token;
  },
    async fetchSemesters() {
      const token = this.checkAuthorization();
      if (!token) return; // Przerwij metodę, jeśli nie ma tokena

    try {
      const response = await axios.get('http://localhost:8080/api/semesters', {
        headers: { 'Authorization': `Bearer ${token}` }
      });
      this.semesters = response.data; // Zapisujemy semestry do danych
      if (this.semesters.length > 0) {
        this.selectedSemester = this.semesters[0].id;  // Domyślnie wybieramy pierwszy semestr
        this.fetchLessons();  // Od razu ładujemy plan zajęć dla pierwszego semestru
      }
    } catch (err) {
      this.error = 'Błąd podczas ładowania semestrów';
      alertStore.addAlert('Wystąpił błąd podczas ładowania semestrów', 'danger');
    }
  },
    async fetchLessons() {
      const token = this.checkAuthorization();
      if (!token) return; // Przerwij metodę, jeśli nie ma tokena

      try {
        const response = await axios.get('http://localhost:8080/api/lessons',{
          headers: {
              'Authorization': `Bearer ${token}`
            }
        }); 
        this.lessons = response.data; 
        if (this.lessons.length > 0) {
        this.selectedGroup = this.lessons[0].studentGroup;  
      }
      } catch (err) {
        this.error = 'Błąd podczas ładowania danych'; 
      } finally {
        this.loading = false; 
        console.log(this.lessons);
      }
    },
    getLessonAtTime(day, hour, quarter) {
      console.log("BBBBBBBB")
      return this.lessons.find(lesson => {
        
      console.log(lesson.semester.id)
        return lesson.dayOfTheWeek === day && 
        this.isTimeInLesson(hour, quarter, lesson.startTime, lesson.endTime) &&
        lesson.semester.id === this.selectedSemester&&
        lesson.studentGroup ===this.selectedGroup; 
        
      });
    },
    isTimeInLesson(hour, quarter, startTime, endTime) {
      const timeInMinutes = this.convertTimeToMinutes(hour, quarter);
      const startInMinutes = this.convertTimeToMinutes(startTime, +1); // Odjęcie 30 minut
      const endInMinutes = this.convertTimeToMinutes(endTime, -1); // Odjęcie 30 minut
      return timeInMinutes >= startInMinutes && timeInMinutes < endInMinutes;
    },
    convertTimeToMinutes(time, quarter = 0) {
      const [h, m] = time.split(':').map(Number);
      return h * 60 + m + quarter * 15; // Kwartały dodają 15 minut
    },
    setSelectedGroup(group) {
      this.selectedGroup = group;
    }
  },

};
</script>

<style scoped>
.schedule {
  display: grid;
  grid-template-columns: 80px repeat(5, 1fr);
  width: 90%;
  float: right;
}

.header {
  display: contents;
}

.time-column,
.day-column {
  padding: 10px;
  background-color: #f0f0f0;
  text-align: center;
  border: 1px solid #ddd;
}
.semester-selector {
  margin-bottom: 10px;
  text-align: center;
}

.semester-selector select {
  padding: 5px;
  font-size: 16px;
  width:10%;
}

.content {
  display: contents;
}

.time-row {
  display: contents;
}

.time-cell {
  padding: 10px;
  background-color: #f0f0f0;
  text-align: center;
  border: 1px solid #ddd;
}

.day-cell {
  padding: 0; 
  border: 1px solid #ddd;
  display: flex;
  flex-direction: column; 
}

.quarter-cell {
  flex: 1; 
  border-top: 1px solid #ddd; 
  height: 25%; 
}

.lesson {
  background-color: #077e25;
  position: absolute;
  color: white;
  padding: 5px;
  border-radius: 5px;
  text-align: center;
  width: 17%;
}

.lesson-id {
  font-size: 15px; /* Ustawienie bardzo małej czcionki */
}
.student-groups {
  padding: 10px;
  background-color: #f0f0f0;
  text-align: center;
  border-right: 1px solid #ddd;
  display: flex;
  flex-direction: column;
  width: 7%;
}
.schedule-container {
  display: flex;
  width: 100%;
}
.selected {
  background-color: #077e25; /* Kolor tła dla wybranej grupy */
  color: white; /* Kolor tekstu */
  font-weight: bold; /* Pogrubienie tekstu */
}
</style>