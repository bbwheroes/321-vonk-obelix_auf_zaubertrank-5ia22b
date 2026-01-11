<template>
    <div class="text-center">
        <v-dialog v-model="dialog" transition="dialog-bottom-transition" fullscreen>
            <template #activator="{ props: activatorProps }">
                <v-btn size="small" text="Checkout" v-bind="activatorProps" />
            </template>

            <v-card>
                <v-toolbar>
                    <v-btn icon="mdi-close" @click="dialog = false" />
                    <v-toolbar-title>Checkout</v-toolbar-title>
                </v-toolbar>

                <v-card-text>
                    <div v-if="cart.items.length === 0" class="text-medium-emphasis">
                        Your cart is empty.
                    </div>

                    <v-list v-else lines="two">
                        <v-list-item v-for="item in cart.items" :key="item.id">
                            <template #prepend>
                                <v-avatar rounded="lg" size="48">
                                    <v-img :src="item.image" cover />
                                </v-avatar>
                            </template>

                            <v-list-item-title>
                                {{ item.title }}
                            </v-list-item-title>

                            <v-list-item-subtitle>
                                Quantity: {{ item.qty }}
                            </v-list-item-subtitle>
                        </v-list-item>
                    </v-list>
                </v-card-text>
            </v-card>
        </v-dialog>
    </div>
</template>

<script setup>
import { shallowRef } from 'vue'
import { useCartStore } from '../stores/cart'

const dialog = shallowRef(false)
const cart = useCartStore()
</script>
