<template>
  <v-app>
    <v-main class="pa-6">
      <v-btn color="primary">Test</v-btn>

      <UserView/>
      
    </v-main>
  </v-app>
</template>

<script setup>
import UserView from './views/UserView.vue'
</script>