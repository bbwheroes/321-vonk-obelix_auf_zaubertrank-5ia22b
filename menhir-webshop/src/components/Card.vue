<template>
  <v-card class="mx-auto" max-width="344">
    <v-img :src="props.image" height="200px" cover />

    <v-card-title>{{ props.title }}</v-card-title>
    <v-card-subtitle>{{ props.subtitle }}</v-card-subtitle>

    <v-card-actions>
      <v-btn color="orange-lighten-2" :text="props.buttonText" />
      <v-spacer />
      <v-btn
        :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
        @click="show = !show"
      />
    </v-card-actions>

    <v-expand-transition>
      <div v-show="show">
        <v-divider />
        <v-card-text>{{ props.description }}</v-card-text>
      </div>
    </v-expand-transition>
  </v-card>
</template>

<script setup>
import { ref } from 'vue'

const props = defineProps({
  title: String,
  subtitle: String,
  description: String,
  image: String,
  buttonText: {
    type: String,
    default: 'Add to cart',
  },
})

const show = ref(false)
</script>
