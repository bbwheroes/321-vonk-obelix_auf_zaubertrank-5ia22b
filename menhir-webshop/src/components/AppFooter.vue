<template>
  <v-footer app border class="px-6">
    <v-container class="d-flex align-center justify-space-between py-2">
      <div class="text-caption">
        © {{ year }} BBW Heroes Obelix's Shop
      </div>

      <div class="d-flex ga-4 align-center">
        <span class="text-caption text-medium-emphasis">Kontakt:</span>
        <a
          class="text-caption text-medium-emphasis"
          href="mailto:heroes@bbw.ch"
        >
          heroes@bbw.ch
        </a>
      </div>
    </v-container>
  </v-footer>
</template>

<script setup>
const year = new Date().getFullYear();
</script>
