<template>
  <v-container class="py-6">
    <AdminToolbar
      :totalStock="totalStock"
      :hasChanges="hasChanges"
      @save="save"
      @reset="resetChanges"
    />

    <AdminInventoryTable :items="itemsDraft" @change-stock="changeStock" />

    <v-snackbar v-model="snack" color="success" timeout="2000">
      Saved (dummy). Inventory updated in admin view only.
    </v-snackbar>
  </v-container>
</template>

<script setup>
import { ref, computed } from "vue";
import AdminToolbar from "../components/admin/AdminToolbar.vue";
import AdminInventoryTable from "../components/admin/AdminInventoryTable.vue";

const initialItems = [
  {
    id: 1,
    title: "Top western road trips",
    value: 14,
    stock: 10,
    image: "https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
  },
  {
    id: 2,
    title: "Mountain escape",
    value: 9,
    stock: 6,
    image: "https://cdn.vuetifyjs.com/images/cards/mountain.jpg",
  },
  {
    id: 3,
    title: "Desert adventure",
    value: 11,
    stock: 8,
    image: "https://cdn.vuetifyjs.com/images/cards/desert.jpg",
  },
  {
    id: 4,
    title: "Forest retreat",
    value: 7,
    stock: 12,
    image: "https://cdn.vuetifyjs.com/images/cards/forest.jpg",
  },
  {
    id: 5,
    title: "Coastal drive",
    value: 10,
    stock: 5,
    image: "https://cdn.vuetifyjs.com/images/cards/road.jpg",
  },
];

const itemsSaved = ref(structuredClone(initialItems));
const itemsDraft = ref(structuredClone(initialItems));

const snack = ref(false);

const totalStock = computed(() =>
  itemsDraft.value.reduce((sum, item) => sum + item.stock, 0)
);

const hasChanges = computed(
  () => JSON.stringify(itemsDraft.value) !== JSON.stringify(itemsSaved.value)
);

function changeStock({ id, delta }) {
  const item = itemsDraft.value.find((x) => x.id === id);
  if (!item) return;
  item.stock = Math.max(0, item.stock + delta);
}

function resetChanges() {
  itemsDraft.value = structuredClone(itemsSaved.value);
}

function save() {
  itemsSaved.value = structuredClone(itemsDraft.value);
  snack.value = true;
}
</script>
