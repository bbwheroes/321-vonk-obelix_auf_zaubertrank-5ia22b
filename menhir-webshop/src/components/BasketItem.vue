<template>
    <v-list-item>
        <template #prepend>
            <v-avatar rounded="lg" size="48">
                <v-img :src="item.image" cover />
            </v-avatar>
        </template>

        <v-list-item-title class="text-body-2">
            {{ item.title }}
        </v-list-item-title>

        <template #append>
            <div class="d-flex align-center ga-2">
                <v-btn size="small" variant="text" icon="mdi-minus" @click="$emit('dec')" />
                <span style="min-width: 18px; text-align: center;">
                    {{ item.qty }}
                </span>
                <v-btn size="small" variant="text" icon="mdi-plus" @click="$emit('inc')" />
            </div>
        </template>
    </v-list-item>

    <v-divider />
</template>

<script setup>
defineProps({
    item: {
        type: Object,
        required: true,
    },
})

defineEmits(['inc', 'dec'])
</script>
