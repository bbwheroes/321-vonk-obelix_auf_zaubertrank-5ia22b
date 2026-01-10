<script setup>
import { ref } from 'vue'

defineProps({
    imageUrl: {
        type: String
    }
});

const itemCount = ref(0)

const decrease = () => {
  itemCount.value = Math.max(0, itemCount.value - 1)
}
</script>

<template>
    <img :src="imageUrl" alt="Basket Item" width="200" />
    <div class="item-count">
        <button @click="decrease">-</button>
        <div>{{ itemCount }}</div>
        <button @click="itemCount++">+</button>
    </div>
</template>

<style scoped>
.item-count {
    display: flex;
    align-items: center;
    gap: 10px;
}

.item-count > button {
    padding: 5px 12px;
    border-radius: 5px;
}
.item-count > button:hover {
    background-color: hsla(0, 0%, 0%, 0.1);
}

</style>