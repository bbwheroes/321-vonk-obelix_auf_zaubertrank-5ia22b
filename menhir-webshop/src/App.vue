<template>
  <v-app>
    <v-app-bar app flat color="light-blue-lighten-4">
      <v-app-bar-title>My App</v-app-bar-title>
      <v-spacer />
      <v-btn icon="mdi-cart-outline" @click="drawer = !drawer" />
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" location="right" temporary width="360">
      <v-list>
        <v-list-item title="My Cart" subtitle="Pinia" />
        <v-divider class="my-2" />

        <div v-if="cart.items.length === 0" class="pa-4 text-medium-emphasis">
          Your cart is empty.
        </div>

        <BasketItem v-for="item in cart.items" :key="item.id" :item="item" @inc="cart.inc(item.id)"
          @dec="cart.dec(item.id)" />
      </v-list>

      <v-divider class="my-2" />

      <div class="pa-4 d-flex align-center justify-space-between">
        <div class="text-subtitle-2">Items</div>
        <div class="text-subtitle-2">{{ cart.totalCount }}</div>
      </div>
    </v-navigation-drawer>

    <v-main>
      <v-container class="pa-6">
        <UserView @add-to-cart="onAddToCart" />
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
import { ref } from 'vue'
import UserView from './views/UserView.vue'
import BasketItem from './components/BasketItem.vue'
import { useCartStore } from './stores/cart'

const drawer = ref(false)
const cart = useCartStore()

function onAddToCart(product) {
  cart.add(product)
  drawer.value = true
}
</script>