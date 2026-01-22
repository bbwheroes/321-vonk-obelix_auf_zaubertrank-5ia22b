<template>
  <v-container class="py-6">
    <AdminToolbar
      :totalStock="totalStock"
      :hasChanges="hasChanges"
      @save="save"
    />

    <AdminInventoryTable
      :items="itemsDraft"
      @change-stock="changeStock"
      @update-item="updateItem"
      @delete-item="deleteItem"
    />

    <v-snackbar v-model="snack" color="success" timeout="2000">
      Saved (dummy). Inventory updated in admin view only.
    </v-snackbar>
  </v-container>
</template>

<script setup>
import { ref, computed } from "vue";
import AdminToolbar from "../components/admin/AdminToolbar.vue";
import AdminInventoryTable from "../components/admin/AdminInventoryTable.vue";

const clone = (obj) =>
  typeof structuredClone === "function"
    ? structuredClone(obj)
    : JSON.parse(JSON.stringify(obj));

const initialItems = [
  { id: 1, title: "Top western road trips", value: 14, stock: 10 },
  { id: 2, title: "Mountain escape", value: 9, stock: 6 },
  { id: 3, title: "Desert adventure", value: 11, stock: 8 },
  { id: 4, title: "Forest retreat", value: 7, stock: 12 },
  { id: 5, title: "Coastal drive", value: 10, stock: 5 },
];

const itemsSaved = ref(clone(initialItems));
const itemsDraft = ref(clone(initialItems));

const snack = ref(false);

const totalStock = computed(() =>
  itemsDraft.value.reduce((sum, item) => sum + (Number(item.stock) || 0), 0)
);

const hasChanges = computed(
  () => JSON.stringify(itemsDraft.value) !== JSON.stringify(itemsSaved.value)
);

function changeStock({ id, delta }) {
  const item = itemsDraft.value.find((x) => x.id === id);
  if (!item) return;
  item.stock = Math.max(0, item.stock + delta);
}

function updateItem({ id, patch }) {
  const item = itemsDraft.value.find((x) => x.id === id);
  if (!item) return;

  if (patch.title !== undefined) item.title = patch.title;
  if (patch.value !== undefined) {
    const v = Number(patch.value);
    item.value = Number.isFinite(v) ? Math.max(0, v) : 0;
  }
}

function deleteItem({ id }) {
  itemsDraft.value = itemsDraft.value.filter((x) => x.id !== id);
}

function save() {
  itemsSaved.value = clone(itemsDraft.value);
  snack.value = true;
}
</script>
