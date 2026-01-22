<template>
  <v-card>
    <v-card-title>Inventory</v-card-title>
    <v-divider />

    <v-table>
      <thead>
        <tr>
          <th>Item</th>
          <th style="width: 160px">Value</th>
          <th style="width: 220px" class="text-center">Stock</th>
          <th style="width: 90px" class="text-center">Actions</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="item in items" :key="item.id">
          <td style="min-width: 260px">
            <v-text-field
              :model-value="item.title"
              @update:model-value="(v) => emitUpdate(item.id, { title: v })"
              density="compact"
              variant="underlined"
              hide-details
              placeholder="Name"
            />
            <div class="text-caption text-medium-emphasis">
              ID: {{ item.id }}
            </div>
          </td>

          <td>
            <v-text-field
              :model-value="String(item.value ?? '')"
              @update:model-value="(v) => emitUpdate(item.id, { value: v })"
              density="compact"
              variant="underlined"
              hide-details
              type="number"
              min="0"
            />
          </td>

          <td class="text-center">
            <div class="d-flex justify-center align-center ga-2">
              <v-btn
                size="small"
                variant="text"
                icon="mdi-minus"
                :disabled="item.stock <= 0"
                @click="$emit('change-stock', { id: item.id, delta: -1 })"
              />
              <v-chip label>{{ item.stock }}</v-chip>
              <v-btn
                size="small"
                variant="text"
                icon="mdi-plus"
                @click="$emit('change-stock', { id: item.id, delta: 1 })"
              />
            </div>
          </td>

          <td class="text-center">
            <v-btn
              icon="mdi-delete"
              variant="text"
              color="error"
              @click="askDelete(item)"
            />
          </td>
        </tr>
      </tbody>
    </v-table>

    <v-dialog v-model="confirmOpen" max-width="420">
      <v-card>
        <v-card-title class="text-subtitle-1">
          Delete item?
        </v-card-title>

        <v-card-text>
          This will remove <b>{{ toDelete?.title }}</b>.
        </v-card-text>

        <v-card-actions class="justify-end ga-2">
          <v-btn variant="text" @click="confirmOpen = false">Cancel</v-btn>
          <v-btn color="error" @click="doDelete">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script setup>
import { ref } from "vue";

defineProps({
  items: {
    type: Array,
    required: true,
  },
});

const emit = defineEmits(["change-stock", "update-item", "delete-item"]);

const confirmOpen = ref(false);
const toDelete = ref(null);

function emitUpdate(id, patch) {
  emit("update-item", { id, patch });
}

function askDelete(item) {
  toDelete.value = item;
  confirmOpen.value = true;
}

function doDelete() {
  if (!toDelete.value) return;
  emit("delete-item", { id: toDelete.value.id });
  confirmOpen.value = false;
  toDelete.value = null;
}
</script>
