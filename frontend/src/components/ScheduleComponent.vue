<template>
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
            <div v-if="isLessonAtTime(day, hour, quarter)">
              <div class="lesson">
                <span class="lesson-id">{{ getLessonAtTime(day, hour, quarter).startTime }} - </span> <span class="lesson-id">{{ getLessonAtTime(day, hour, quarter).endTime }} </span> 
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
      error: null
    };
  },
  mounted() {
    this.fetchLessons();
  },
  methods: {
    async fetchLessons() {
      const token = sessionStorage.getItem('token');

      try {
        const response = await axios.get('http://localhost:8080/api/lessons',{
          headers: {
              'Authorization': `Bearer ${token}`
            }
        }); 
        this.lessons = response.data; 
      } catch (err) {
        this.error = 'Błąd podczas ładowania danych'; 
      } finally {
        this.loading = false; 
        console.log(this.lessons);
      }
    },
    isLessonAtTime(day, hour, quarter) {
      const lesson = this.lessons.find(lesson => {
        return lesson.dayOfTheWeek === day && 
               this.isTimeInLesson(hour, quarter, lesson.startTime, lesson.endTime);
      });
      return lesson !== undefined;
    },
    getLessonAtTime(day, hour, quarter) {
      return this.lessons.find(lesson => {
        return lesson.dayOfTheWeek === day && 
               this.isTimeInLesson(hour, quarter, lesson.startTime, lesson.endTime);
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
    }
  },
};
</script>

<style scoped>
.schedule {
  display: grid;
  grid-template-columns: 80px repeat(5, 1fr);
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
  width: 19%;
}

.lesson-id {
  font-size: 15px; /* Ustawienie bardzo małej czcionki */
}
</style>
