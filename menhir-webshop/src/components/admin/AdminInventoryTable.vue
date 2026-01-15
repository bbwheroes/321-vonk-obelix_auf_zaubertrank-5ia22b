<template>
  <v-card>
    <v-card-title>Inventory</v-card-title>
    <v-divider />

    <v-table>
      <thead>
        <tr>
          <th style="width: 72px">Image</th>
          <th>Item</th>
          <th style="width: 160px">Value</th>
          <th style="width: 220px" class="text-right">Stock</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="item in items" :key="item.id">
          <td>
            <v-avatar rounded="lg" size="48">
              <v-img :src="item.image" cover />
            </v-avatar>
          </td>

          <td>
            <div class="text-subtitle-2">{{ item.title }}</div>
            <div class="text-body-2 text-medium-emphasis">
              ID: {{ item.id }}
            </div>
          </td>

          <td>
            <v-chip variant="tonal">
              {{ item.value }}
            </v-chip>
          </td>

          <td class="text-right">
            <div class="d-flex justify-end align-center ga-2">
              <v-btn
                size="small"
                variant="text"
                icon="mdi-minus"
                :disabled="item.stock <= 0"
                @click="$emit('change-stock', { id: item.id, delta: -1 })"
              />
              <v-chip label>
                {{ item.stock }}
              </v-chip>
              <v-btn
                size="small"
                variant="text"
                icon="mdi-plus"
                @click="$emit('change-stock', { id: item.id, delta: 1 })"
              />
            </div>
          </td>
        </tr>
      </tbody>
    </v-table>
  </v-card>
</template>

<script setup>
defineProps({
  items: Array
});

defineEmits(["change-stock"]);
</script>
