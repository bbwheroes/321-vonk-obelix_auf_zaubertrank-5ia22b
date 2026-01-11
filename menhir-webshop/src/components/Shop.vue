<template>
  <v-container>
    <v-row>
      <v-col v-for="item in items" :key="item.id" cols="12" md="4">
        <Card
          :title="item.title"
          :subtitle="item.subtitle"
          :description="item.description"
          :image="item.image"
          :buttonText="item.buttonText"
          @add-to-cart="onAdd(item)"
        />
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import Card from './Card.vue'

const emit = defineEmits(['add-to-cart'])

function onAdd(item) {
  // send only what cart needs
  emit('add-to-cart', {
    id: item.id,
    title: item.title,
    image: item.image,
  })
}

const items = [
  {
    id: 1,
    title: 'Top western road trips',
    subtitle: '1,000 miles of wonder',
    image: 'https://cdn.vuetifyjs.com/images/cards/sunshine.jpg',
    description: 'I’m a thing. But, like most politicians, he promised more than he could deliver.',
    buttonText: 'Add to cart',
  },
  {
    id: 2,
    title: 'Mountain escape',
    subtitle: 'Fresh air guaranteed',
    image: 'https://cdn.vuetifyjs.com/images/cards/mountain.jpg',
    description: 'You won’t have time for sleeping, soldier.',
    buttonText: 'Add to cart',
  },
  {
    id: 3,
    title: 'Desert adventure',
    subtitle: 'Heat, dust, glory',
    image: 'https://cdn.vuetifyjs.com/images/cards/desert.jpg',
    description: 'Then we’ll go with that data file!',
    buttonText: 'Add to cart',
  },
  {
    id: 4,
    title: 'Forest retreat',
    subtitle: 'Silence and pine trees',
    image: 'https://cdn.vuetifyjs.com/images/cards/forest.jpg',
    description: 'Sometimes the best plan is no plan at all.',
    buttonText: 'Add to cart',
  },
  {
    id: 5,
    title: 'Coastal drive',
    subtitle: 'Cliffs, waves, freedom',
    image: 'https://cdn.vuetifyjs.com/images/cards/road.jpg',
    description: 'Every turn comes with a view worth stopping for.',
    buttonText: 'Add to cart',
  },
  {
    id: 6,
    title: 'City lights',
    subtitle: 'Never sleeps',
    image: 'https://cdn.vuetifyjs.com/images/cards/city.jpg',
    description: 'Concrete, neon, and endless stories.',
    buttonText: 'Add to cart',
  },
  {
    id: 7,
    title: 'Snowy peaks',
    subtitle: 'Cold air, clear mind',
    image: 'https://cdn.vuetifyjs.com/images/cards/snow.jpg',
    description: 'Up here, everything feels simpler.',
    buttonText: 'Add to cart',
  },
  {
    id: 8,
    title: 'Lakeside calm',
    subtitle: 'Still water, slow days',
    image: 'https://cdn.vuetifyjs.com/images/cards/lake.jpg',
    description: 'Time moves differently by the water.',
    buttonText: 'Add to cart',
  },
  {
    id: 9,
    title: 'Countryside ride',
    subtitle: 'Back roads only',
    image: 'https://cdn.vuetifyjs.com/images/cards/house.jpg',
    description: 'Sometimes the long way around is the point.',
    buttonText: 'Add to cart',
  },
]
</script>
