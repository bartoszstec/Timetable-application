import { reactive } from 'vue';

export const alertStore = reactive({ 
  alerts: [],

  addAlert(message, type = 'info', duration = 2500) {
    if (this.alerts.some(alert => (alert.message === message && alert.type === type))) {
        return; // Nie dodawaj duplikatu
      }

    const alert = { message, type };

    this.alerts.push(alert);

    // Usuń alert automatycznie po określonym czasie
    setTimeout(() => {
      this.removeAlert(alert);
    }, duration);
  },

  removeAlert(alert) {
    const index = this.alerts.indexOf(alert);
    if (index !== -1) {
      this.alerts.splice(index, 1); // Usuń tylko ten alert
    }
  },
  clearAlerts() {
    this.alerts = []
  }
});