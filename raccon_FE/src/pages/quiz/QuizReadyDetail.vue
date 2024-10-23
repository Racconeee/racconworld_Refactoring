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
          <q-icon name="pets" />{{ selectedTest?.view || 0 }}
        </div>
      </q-item-section>
    </q-item>
    <div class="flex justify-center q-mt-xl">
      <q-btn class="Test-select text-h6" @click="goToQuiz"
        >Test 시작하기!!</q-btn
      >
    </div>
  </div>
</template>

<script setup>
import { useTestStore } from "src/stores/useTestStore";
import { computed } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const testStore = useTestStore();
const selectedTest = computed(() => {
  return testStore.testList.find(
    (item) => item.testId == testStore.currentTestId
  );
});

//따로 api 만들지 말고
//차라리 결과 볼 때 결과 줄때 view 값 increment 하자

const goToQuiz = () => {
  router.push({ name: "quizpage" });
};
</script>

<style lang="scss" scoped>
.Test-select {
  width: 220px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}
</style>
