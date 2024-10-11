<template>
  <div class="row justify-between">
    <div class="col-6 q-mb-md" v-for="item in items" :key="item.id">
      <TestItem v-bind="item"></TestItem>
    </div>
  </div>
</template>

<script setup>
import TestItem from "./TestItem.vue";

defineProps({
  items: {
    type: Array,
    required: true,
  },
});
</script>

<style lang="scss" scoped></style>
