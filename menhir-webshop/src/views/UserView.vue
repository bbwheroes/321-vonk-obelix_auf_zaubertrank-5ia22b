<script>
const menhirImages = [`https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Kerloas_menhir.JPG/960px-Kerloas_menhir.JPG`,
                    `https://upload.wikimedia.org/wikipedia/commons/d/dc/Menhir_kerloas2.jpg`,
                    `https://upload.wikimedia.org/wikipedia/commons/0/05/Breizh_35_-_Dol_-_Peulvan_maez_al_lanv_06.jpg`,
                    `https://www.worldhistory.org/uploads/images/17698.jpeg`];
</script>

<template>
  <v-app>
    <v-main class="pa-6">
      <div 
        v-for="(item, index) in menhirImages" 
        :key="index" 
        :id="`menhir-${index + 1}`"
      >
        <BasketItem :imageUrl="item" />
      </div>
    </v-main>
  </v-app>
</template>

<script setup>
import Card from '../components/Card.vue'
import BasketItem from '../components/BasketItem.vue';
</script>