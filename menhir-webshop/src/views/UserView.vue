<template>
  <div>
    <AboutSection />
    <Shop @add-to-cart="$emit('add-to-cart', $event)" />
  </div>
</template>

<script setup>
import AboutSection from '../components/AboutSection.vue'
import Shop from '../components/Shop.vue'

defineEmits(['add-to-cart'])
</script>
