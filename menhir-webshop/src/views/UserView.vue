<template>
  <v-app>
    <v-main class="pa-6">
      <div style="width: 25%; margin-left: 75%; padding: 2%; background-color: hsla(0, 0%, 0%, 0.05);">
        <div 
          v-for="(item, index) in menhirImages" 
          :key="index" 
          :id="`menhir-${index + 1}`"
        >
          <BasketItem :imageUrl="item.url" />
        </div>
      </div>
    </v-main>
  </v-app>
</template>

<script setup>
import BasketItem from '../components/BasketItem.vue';

const menhirImages = [
  {
    id: 1,
    name: 'Menhir 1',
    url: `https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Kerloas_menhir.JPG/960px-Kerloas_menhir.JPG`
  },
  {
    id: 2,
    name: 'Menhir 2',
    url: `https://upload.wikimedia.org/wikipedia/commons/d/dc/Menhir_kerloas2.jpg`
  },
  {
    id: 3,
    name: 'Menhir 3',
    url: `https://upload.wikimedia.org/wikipedia/commons/0/05/Breizh_35_-_Dol_-_Peulvan_maez_al_lanv_06.jpg`
  },
  {
    id: 4,
    name: 'Menhir 4',
    url: `https://www.worldhistory.org/uploads/images/17698.jpeg`
  }
];
</script>