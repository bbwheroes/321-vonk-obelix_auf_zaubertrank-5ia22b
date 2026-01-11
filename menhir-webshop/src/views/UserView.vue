<template>
  <Shop @add-to-cart="$emit('add-to-cart', $event)" />
</template>

<script setup>
import Shop from '../components/Shop.vue'

defineEmits(['add-to-cart'])
</script>
