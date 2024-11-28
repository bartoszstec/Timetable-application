<template>
  <br>
  <div class="semester-selector">
      <label for="semester">Wybierz semestr:</label><br>
      <select v-model="selectedSemester" @change="fetchLessons">
        <option v-for="semester in semesters" :key="semester.id" :value="semester.id">
          {{ semester.name }}
        </option>
      </select>
  </div>

  <div class="semester-selector">
     <label for="occurrence">Wybierz występowanie:</label><br>
     <select v-model="selectedOccurrence" @change="fetchLessons">
       <option value="ODD">Nieparzyste</option>
       <option value="EVEN">Parzyste</option>
     </select>
  </div>

  <div class="schedule-container">
    <div class="student-groups">
      <div v-for="group in studentGroups" :key="group.id" class="student-group" :class="{ selected: group.id === selectedGroup }" @click="setSelectedGroup(group.id)">
        {{ group.name }}
        <br>-----------------------------------
      </div>
    </div>

    <div class="schedule">
      <div class="header">
        <div class="time-column"></div>
        <div class="day-column" v-for="day in days" :key="day">{{ day }}</div>
      </div>
      
      <div class="content">
        <div class="time-row" v-for="hour in hours" :key="hour">
          <div class="time-cell">{{ hour }}</div>
          <div class="day-cell" v-for="day in days" :key="day">
            <div class="quarter-cell" v-for="quarter in 4" :key="quarter">
              <div v-if="getLessonAtTime(day, hour, quarter)">
                <div class="lesson">
                  <span class="lesson-id">{{ getLessonAtTime(day, hour, quarter).startTime }} - </span> 
                  <span class="lesson-id">{{ getLessonAtTime(day, hour, quarter).endTime }} </span><br>
                  <span class="lesson-id">{{ getLessonAtTime(day, hour, quarter).name }}</span><br>
                  <span class="lesson-id">Sala: {{ getLessonAtTime(day, hour, quarter).room }}</span><br>
                  <span class="lesson-id">Prowadzący: {{ getLessonAtTime(day, hour, quarter).teacher }}</span>
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
      days: ["Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek"],
      hours: [
        "08:00", "09:00", "10:00", "11:00", "12:00", 
        "13:00", "14:00", "15:00", "16:00", 
        "17:00", "18:00", "19:00", "20:00"
      ],
      lessons: [],
      studentGroups: [],
      loading: true,
      error: null,
      selectedGroup: null,
      selectedOccurrence: 'ODD',
      i:0,
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
      if (!token) return;

      try {
        const response = await axios.get('http://localhost:8080/api/semesters', {
          headers: { 'Authorization': `Bearer ${token}` }
        });
        this.semesters = response.data;
        if (this.semesters.length > 0) {
          this.fetchLessons();
        }
      } catch (err) {
        this.error = 'Błąd podczas ładowania semestrów';
        alertStore.addAlert('Wystąpił błąd podczas ładowania semestrów', 'danger');
      }
    },
    async fetchLessons() {
      const token = this.checkAuthorization();
      if (!token) return;

      try {
        const response = await axios.get('http://localhost:8080/api/lessons', {
          headers: {
            'Authorization': `Bearer ${token}`,
          },
        });
        this.lessons = response.data;

        if (this.selectedOccurrence && this.selectedOccurrence !== 'ALL') {
          this.lessons = this.lessons.filter(lesson => lesson.occurrence === this.selectedOccurrence || lesson.occurrence === 'ALL');
        }

        this.studentGroups = this.getUniqueGroups(this.lessons);

        if (this.lessons.length > 0 && this.i == 0 ) {
          this.selectedGroup = this.lessons[0].studentGroup.id;
          this.selectedSemester = this.semesters[0].id;
          this.i++;
        }
      } catch (err) {
        this.error = 'Błąd podczas ładowania danych';
        alertStore.addAlert('Wystąpił błąd podczas ładowania danych', 'danger');
      } finally {
        this.loading = false;
      }
    },
    getUniqueGroups(lessons) {
      const groups = lessons.map(lesson => lesson.studentGroup);
      return [...new Map(groups.map(group => [group.id, group])).values()];
    },
    getLessonAtTime(day, hour, quarter) {
      const daysMapping = {
        MONDAY: "Poniedziałek",
        TUESDAY: "Wtorek",
        WEDNESDAY: "Środa",
        THURSDAY: "Czwartek",
        FRIDAY: "Piątek",
      };

      return this.lessons.find(lesson => {
        const lessonDay = daysMapping[lesson.dayOfTheWeek];
        
        return lessonDay === day &&
          this.isTimeInLesson(hour, quarter, lesson.startTime, lesson.endTime) &&
          lesson.semester.id === this.selectedSemester &&
          lesson.studentGroup.id === this.selectedGroup &&
          (lesson.occurrence === 'ALL' || lesson.occurrence === this.selectedOccurrence);
      });
    },
    isTimeInLesson(hour, quarter, startTime, endTime) {
      const timeInMinutes = this.convertTimeToMinutes(hour, quarter);
      const startInMinutes = this.convertTimeToMinutes(startTime, +1);
      const endInMinutes = this.convertTimeToMinutes(endTime, -1);
      return timeInMinutes >= startInMinutes && timeInMinutes < endInMinutes;
    },
    convertTimeToMinutes(time, quarter = 0) {
      const [h, m] = time.split(':').map(Number);
      return h * 60 + m + quarter * 15;
    },
    setSelectedGroup(groupId) {
      this.selectedGroup = groupId;
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
  font-size: 15px;
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
  background-color: #077e25;
  color: white;
}
</style>
