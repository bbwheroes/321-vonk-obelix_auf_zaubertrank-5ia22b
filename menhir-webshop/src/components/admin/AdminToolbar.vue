<template>
  <div class="d-flex align-center justify-space-between mb-4">
    <div>
      <div class="text-h5">Store</div>
      <div class="text-body-2 text-medium-emphasis">
        Manage available stock for each product (dummy for now).
      </div>
    </div>

    <div class="d-flex align-center ga-3">
      <v-chip variant="tonal"> Total stock: {{ totalStock }} </v-chip>

      <v-btn variant="tonal" @click="$emit('reset')" :disabled="!hasChanges">
        Reset
      </v-btn>

      <v-btn color="primary" @click="$emit('save')" :disabled="!hasChanges">
        Save changes
      </v-btn>
    </div>
  </div>
</template>

<script setup>
defineProps({
  totalStock: Number,
  hasChanges: Boolean
});

defineEmits(["save", "reset"]);
</script>
