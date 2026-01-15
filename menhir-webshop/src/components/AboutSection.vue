<template>
  <v-sheet :color="props.bgColor" class="py-4">
    <v-container fluid class="pa-0">
      <v-row no-gutters align="center">
        <v-col cols="12" md="5" class="pa-6 pa-md-10">
          <div class="text-h4 mb-4 font-weight-bold">About My Shop</div>
          <div class="text-body-1 text-medium-emphasis mb-6">
            <p class="mb-4">
              Welcome to Obelix's Menhir Shop, the only place in Gaul where menhirs are not just heavy, but legendary.
              <br><br>
              Since Obelix accidentally fell into the magic potion as a child, he has dedicated his life to carrying, shaping, and occasionally dropping menhirs. Each stone is carefully selected, hand-carved, and tested for maximum sturdiness, usually by Obelix himself.
              <br><br>
              Whether you're looking for a decorative stone, a historic statement piece, or simply something very, very heavy, you've come to the right place. The menhirs combine traditional Gallic craftsmanship with unmatched strength and a good dose of humor.
              <br><br>
              Warning: Delivery by Obelix may impress your neighbors and scare nearby Romans.
            </p>
          </div>

          <div class="d-flex flex-wrap ga-2">
            <v-chip color="light-blue-darken-3" variant="flat">Superhuman</v-chip>
            <v-chip color="light-blue-darken-3" variant="flat">Hinkelstein</v-chip>
            <v-chip color="light-blue-darken-3" variant="flat">Menhir</v-chip>
            <v-chip color="light-blue-darken-3" variant="flat">Romans Beware</v-chip>
            <v-chip color="light-blue-darken-3" variant="flat">Gaulish Craftsmanship</v-chip>
          </div>
        </v-col>

        <v-col cols="12" md="7" class="pr-md-16 d-flex justify-center">
          <img
            :src="props.image"
            alt="About Obelix"
            class="about-image"
          />
        </v-col>
      </v-row>
    </v-container>
  </v-sheet>
</template>

<script setup>
import obelixImage from '../assets/Obelix.png'

const props = defineProps({
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

<style scoped>
.about-image {
  max-width: 100%;
  height: 500px;
  border-radius: 1rem;
}
</style>