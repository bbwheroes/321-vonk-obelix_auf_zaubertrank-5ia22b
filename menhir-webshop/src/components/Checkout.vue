<template>
  <div class="text-center">
    <v-dialog v-model="dialog" transition="dialog-bottom-transition" fullscreen>
      <template #activator="{ props: activatorProps }">
        <v-btn block color="primary" text="Checkout" v-bind="activatorProps" />
      </template>

      <v-card>
        <v-toolbar>
          <v-btn icon="mdi-close" @click="dialog = false" />
          <v-toolbar-title>Checkout</v-toolbar-title>

          <v-spacer />

          <v-btn
            variant="text"
            text="Purchase"
            :disabled="!canPurchase"
            @click="purchase"
          />
        </v-toolbar>

        <v-card-text>
          <div v-if="cart.items.length === 0" class="text-medium-emphasis">
            Your cart is empty.
          </div>

          <div v-else>
            <div v-for="item in cart.items" :key="item.id" class="mb-6">
              <div class="d-flex align-center ga-3">
                <v-avatar rounded="lg" size="56">
                  <v-img :src="item.image" cover />
                </v-avatar>

                <div class="flex-grow-1">
                  <div class="text-subtitle-1">{{ item.title }}</div>
                  <div class="text-body-2 text-medium-emphasis">
                    Qty: {{ item.qty }} · Item value: {{ item.value }} ·
                    Required: <b>{{ requiredValue(item) }}</b>
                  </div>
                </div>

                <v-chip
                  :color="isCovered(item) ? 'success' : 'warning'"
                  variant="flat"
                >
                  {{ isCovered(item) ? "Covered" : "Not enough" }}
                </v-chip>
              </div>

              <v-list class="mt-3" density="compact" lines="one">
                <v-list-item v-for="offer in offers" :key="offer.id">
                  <v-list-item-title>
                    {{ offer.name }}
                  </v-list-item-title>

                  <v-list-item-subtitle>
                    Value each: {{ offer.value }}
                  </v-list-item-subtitle>

                  <template #append>
                    <div class="d-flex align-center ga-2">
                      <v-btn
                        size="small"
                        variant="text"
                        icon="mdi-minus"
                        :disabled="getOfferQty(item.id, offer.id) === 0"
                        @click="changeOffer(item.id, offer.id, -1)"
                      />
                      <span style="min-width: 18px; text-align: center">
                        {{ getOfferQty(item.id, offer.id) }}
                      </span>
                      <v-btn
                        size="small"
                        variant="text"
                        icon="mdi-plus"
                        @click="changeOffer(item.id, offer.id, 1)"
                      />
                    </div>
                  </template>
                </v-list-item>
              </v-list>

              <!-- Totals per item -->
              <div class="d-flex justify-end mt-2 text-body-2">
                Offered value: <b class="ml-1">{{ offeredValue(item.id) }}</b>
              </div>

              <v-divider class="mt-4" />
            </div>
          </div>
        </v-card-text>
      </v-card>
    </v-dialog>
  </div>
</template>

<script setup>
import { shallowRef, reactive, computed } from "vue";
import { useCartStore } from "../stores/cart";

const dialog = shallowRef(false);
const cart = useCartStore();

const offers = [
  { id: "offer1", name: "Offer 1", value: 2 },
  { id: "offer2", name: "Offer 2", value: 3 },
  { id: "offer3", name: "Offer 3", value: 6 },
];

const allocations = reactive({});

function ensureItem(itemId) {
  if (!allocations[itemId]) allocations[itemId] = {};
  for (const offer of offers) {
    if (allocations[itemId][offer.id] == null)
      allocations[itemId][offer.id] = 0;
  }
}

function requiredValue(item) {
  return (item.value || 0) * (item.qty || 0);
}

function getOfferQty(itemId, offerId) {
  ensureItem(itemId);
  return allocations[itemId][offerId] || 0;
}

function changeOffer(itemId, offerId, delta) {
  ensureItem(itemId);
  const next = (allocations[itemId][offerId] || 0) + delta;
  allocations[itemId][offerId] = Math.max(0, next);
}

function offeredValue(itemId) {
  ensureItem(itemId);
  let sum = 0;
  for (const offer of offers) {
    sum += (allocations[itemId][offer.id] || 0) * offer.value;
  }
  return sum;
}

function isCovered(item) {
  return offeredValue(item.id) >= requiredValue(item);
}

const canPurchase = computed(() => {
  if (cart.items.length === 0) return false;
  return cart.items.every(isCovered);
});

function purchase() {
  if (!canPurchase.value) return;

  cart.clear();
  for (const key of Object.keys(allocations)) delete allocations[key];
  dialog.value = false;
}
</script>
