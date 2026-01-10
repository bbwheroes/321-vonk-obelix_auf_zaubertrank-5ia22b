<script setup>
import { ref } from 'vue'

defineProps({
    imageUrl: {
        type: String
    },
    displayText: {
        type: String,
        default: 'Default Menhir'
    }
});

const itemCount = ref(0)

const decrease = () => {
  itemCount.value = Math.max(0, itemCount.value - 1)
}
</script>

<template>
    <div class="item">
        <div class="left">
            <div>{{ displayText }}</div>
            <div class="item-count">
                <button @click="decrease">-</button>
                <div>{{ itemCount }}</div>
                <button @click="itemCount++">+</button>
            </div>
        </div>
        <img :src="imageUrl" alt="Basket Item" />
    </div>
</template>

<style scoped>

.item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: 10px;
    margin-bottom: 20px;

    padding: 10px;
    border-radius: 10px;
    background-color: hsla(0, 0%, 0%, 0.05);

    font-size: 130%
}

.item .left {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.item img {
    width: 100px;
    height: 150px;
    object-fit: cover;
    border-radius: 7px;
}

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