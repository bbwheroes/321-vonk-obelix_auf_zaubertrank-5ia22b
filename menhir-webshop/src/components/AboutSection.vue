<template>
  <v-sheet :color="props.bgColor" class="py-4">
    <v-container fluid class="pa-0">
      <v-row no-gutters align="center">
        <v-col cols="12" md="5" class="pa-6 pa-md-10">
          <div class="text-h4 mb-4 font-weight-bold">{{ props.title }}</div>
          <div class="text-body-1 text-medium-emphasis" style="white-space: pre-line; line-height: 1.4;">
            {{ props.text }}
          </div>
        </v-col>

        <v-col cols="12" md="7" class="pr-md-16 d-flex justify-center">
          <v-img
          :src="props.image"
          height="400"
          aspect-ratio="16/9"
          contain
          class="rounded-lg"
          />
        </v-col>
      </v-row>
    </v-container>
  </v-sheet>
</template>

<script setup>
import obelixImage from '../assets/Obelix.png'

const props = defineProps({
  title: {
    type: String,
    default: "About Our Shop",
  },
  text: {
    type: String,
    default:
      "Welcome to Obelix's Menhir Shop, the only place in Gaul where menhirs are not just heavy, but legendary.\n\nSince Obelix accidentally fell into the magic potion as a child, he has dedicated his life to carrying, shaping, and occasionally dropping menhirs. Each stone is carefully selected, hand-carved, and tested for maximum sturdiness, usually by Obelix himself.\n\nWhether you're looking for a decorative stone, a historic statement piece, or simply something very, very heavy, you've come to the right place. Our menhirs combine traditional Gallic craftsmanship with unmatched strength and a good dose of humor.\n\nWarning: Delivery by Obelix may impress your neighbors and scare nearby Romans.",
  },
  image: {
    type: String,
    default: obelixImage,
  },
  bgColor: {
    type: String,
    default: "grey-lighten-4",
  },
});
</script>
