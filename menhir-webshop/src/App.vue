<template>
  <v-app>
    <v-app-bar app flat>
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

      <v-spacer />

      <v-divider />

      <div class="pa-4">
        <div class="d-flex align-center justify-space-between mb-3">
          <div class="text-subtitle-2">Items</div>
          <div class="text-subtitle-2">{{ cart.totalCount }}</div>
        </div>

        <Checkout />
      </div>
    </v-navigation-drawer>

    <v-main>
      <v-container class="pa-6">
        <router-view @add-to-cart="onAddToCart" />
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
import { ref } from "vue";
import { useCartStore } from "./stores/cart";

import BasketItem from "./components/BasketItem.vue";
import Checkout from "./components/Checkout.vue";

const drawer = ref(false);
const cart = useCartStore();

function onAddToCart(product) {
  cart.add(product);
  drawer.value = true;
}
</script>
