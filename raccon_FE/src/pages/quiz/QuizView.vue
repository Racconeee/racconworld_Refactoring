<template>
  <div class="bg-teal-2" :style="{ height: '100vh' }">
    <div>
      <QuizChoice></QuizChoice>
    </div>
  </div>
</template>

<script setup>
import QuizChoice from "@/components/quiz/QuizChoice.vue";
import { computed, onMounted } from "vue";
import { useTestStore } from "@/stores/useTestStore";
import { useRouter } from "vue-router";

const router = useRouter();
const testStore = useTestStore();

onMounted(async () => {
  //name으로 하면 문제점 발생 그냥 화면만 가고 데이터가 로드가 안됨
  //하지만 path로 가면 데이터로드까지 정상적으로 된다.
  if (!testStore.currentTestId) {
    router.push({ path: "/" }); // 홈 경로로 리다이렉트
    return;
  }
  console.log("testStore.currentTestId", testStore.currentTestId);
  if (testStore.currentTestId) {
    // testStore.setCurrentTestId()
    console.log(testStore.currentTestId);

    return await testStore.getQuizList(testStore.currentTestId).then(() => {});
  }
  return await testStore
    .getQuizList(localStorage.getItem("testId"))
    .then(() => {});

  // quizList 비동기 데이터 호출
});

// computed((testStore.currentTestId) => {

// })
</script>

<style></style>
