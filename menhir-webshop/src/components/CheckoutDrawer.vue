<template>
  <v-navigation-drawer
    :model-value="modelValue"
    @update:model-value="(val) => emit('update:modelValue', val)"
    location="right"
    temporary
    width="360"
  >
    <div class="drawer-header">
      <div class="title">Basket</div>

      <v-btn
        icon="mdi-close"
        variant="text"
        @click="emit('update:modelValue', false)"
        aria-label="Close basket"
      />
    </div>

    <v-divider />

    <div class="drawer-body">
      <div v-if="items.length === 0" class="empty">Basket is empty.</div>

      <v-list v-else class="bg-transparent" lines="two">
        <v-list-item v-for="item in items" :key="item.id" class="basket-item">
          <v-list-item-title class="item-name">
            {{ item.name }}
          </v-list-item-title>

          <v-list-item-subtitle class="item-sub">
            Qty: {{ item.quantity }}
          </v-list-item-subtitle>

          <template #append>
            <div class="qty-controls">
              <v-btn
                icon="mdi-minus"
                size="small"
                variant="tonal"
                @click="emit('decrement', item.id)"
                aria-label="Decrease quantity"
              />
              <v-btn
                icon="mdi-plus"
                size="small"
                variant="tonal"
                @click="emit('increment', item.id)"
                aria-label="Increase quantity"
              />
            </div>
          </template>
        </v-list-item>
      </v-list>

      <v-divider class="my-4" />

      <div class="footer">
        <div class="total-row">
          <span>Total</span>
          <strong>{{ total.toFixed(2) }} €</strong>
        </div>

        <v-btn
          block
          color="primary"
          variant="flat"
          :disabled="items.length === 0"
          @click="emit('checkout')"
        >
          Go to checkout
        </v-btn>
      </div>
    </div>
  </v-navigation-drawer>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  modelValue: { type: Boolean, default: false },
  items: { type: Array, default: () => [] },
});

const emit = defineEmits([
  'update:modelValue',
  'increment',
  'decrement',
  'checkout',
]);

const total = computed(() => {
  return props.items.reduce((sum, item) => {
    const price = Number(item.price ?? 0);
    const qty = Number(item.quantity ?? 0);
    return sum + price * qty;
  }, 0);
});
</script>

<style scoped>
.drawer-header {
  padding: 16px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.title {
  font-size: 18px;
  font-weight: 600;
}

.drawer-body {
  padding: 16px;
}

.empty {
  opacity: 0.7;
}

.basket-item {
  border: 1px solid rgba(0, 0, 0, 0.08);
  border-radius: 10px;
  margin-bottom: 10px;
}

.item-name {
  font-weight: 600;
}

.item-sub {
  opacity: 0.75;
}

.qty-controls {
  display: flex;
  gap: 8px;
  align-items: center;
}

.footer {
  display: grid;
  gap: 12px;
}

.total-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
