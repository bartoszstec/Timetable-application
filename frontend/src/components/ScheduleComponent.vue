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
          <div class="quarter-cell" v-for="quarter in 4" :key="quarter"></div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
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
    };
  },
  methods: {
    getLesson(day, hour) {
      return this.lessons.find(lesson => {
        return lesson.day === day && lesson.hour === hour;
      });
    },
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
  padding: 0; /* Wyłącz padding */
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
  background-color: #2196F3;
  color: white;
  padding: 5px;
  border-radius: 5px;
  text-align: center;
}
</style>
