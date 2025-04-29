<template>
  <div v-if="TotalQuizList.questions && TotalQuizList.questions.length > 0">
    <section class="flex flex-center">
      <q-linear-progress
        :value="(currentPage + 1) / TotalQuizList.questions.length"
        color="primary"
        track-color="grey-4"
        size="10px"
        class="q-mb-xl q-mt-xl responsive-card"
        rounded
      />

      <q-card class="responsive-card">
        <q-card-section>
          {{ TotalQuizList.questions[currentPage].questionText }}
        </q-card-section>
      </q-card>
    </section>
    <div class="q-ma-md flex flex-center column">
      <div
        class="q-pa-md q-gutter-sm"
        v-for="choice in TotalQuizList.questions[currentPage].choices || []"
        :key="choice.id"
      >
        <q-btn
          color="brown-5"
          :label="choice.choiceText"
          @click="incrementCurrentPage(choice)"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { useTestStore } from "@/stores/useTestStore";
import { ref, computed } from "vue";
import QuizCommon from "@/commonjs/QuizCommon.js";
import { useRouter } from "vue-router";

const testStore = useTestStore();
const TotalQuizList = computed(() => testStore.quizList);
const currentPage = ref(0);
const choiceScoreList = ref([]);
let quizResult = "";

const router = useRouter();

const incrementCurrentPage = (choice) => {
  choiceScoreList.value.push(choice.score);

  if (TotalQuizList.value.questions.length - 1 <= currentPage.value) {
    quizResult = QuizCommon.quizScoreCalculate(
      choiceScoreList,
      TotalQuizList.value.testType
    );

    testStore.setresultScore(quizResult);
    router.push({ name: "resultpage" }); // 페이지 이동
    return;
  }
  currentPage.value++;
};
</script>

<style lang="scss" scoped>
.responsive-card {
  width: 80vw;
}

@media (min-width: 1000px) {
  .responsive-card {
    width: 600px;
  }
}
</style>
