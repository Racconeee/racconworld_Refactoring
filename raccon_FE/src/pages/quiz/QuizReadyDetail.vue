<template>
  <div>
    <q-item
      clickable
      v-ripple
      @click="setStoreTestId"
      class="flex flex-center q-mt-sm"
    >
      <q-item-section
        class="flex flex-column items-center justify-center text-center"
      >
        <div class="text-h4 q-mt-xl">{{ testStore.quizList.testName }}</div>

        <q-img
          loading="lazy"
          :src="filePath"
          alt="Thumbnail"
          height="330px"
          width="330px"
          fit="cover"
          :style="{ borderRadius: '5px' }"
        />
        <div class="q-mt-xl text-h6">참여자 수</div>
        <div class="text-h6">
          <q-icon name="pets" />{{ testStore.quizList.view }}
        </div>
      </q-item-section>
    </q-item>
    <div class="flex justify-center q-mt-xl">
      <q-btn class="Test-select text-h6">Test 시작하기!!</q-btn>
    </div>
  </div>
</template>

<script setup>
import { useTestStore } from "src/stores/useTestStore";

const testStore = useTestStore();
</script>

<style lang="scss" scoped>
.Test-select {
  width: 220px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}
</style>
