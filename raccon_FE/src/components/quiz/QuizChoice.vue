<template>
  <div v-if="TotalQuizList.questions && TotalQuizList.questions.length > 0">
    <div class="flex flex-center text-h4 q-pa-lg q-mb-xl">
      {{ currentPage }}/{{ TotalQuizList.questions.length }}
    </div>
    <section class="flex flex-center">
      <q-card :style="{ width: '80vw' }">
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
          align="around"
          class="btn-fixed-width"
          color="brown-5"
          :label="choice.choiceText"
          icon="lightbulb_outline"
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
//pinia에서 계속 데이터를 가져오는것보다 한번 설정해서 사용하기
const TotalQuizList = computed(() => testStore.quizList);
//현재 몇번 문제 푸는지 저장
const currentPage = ref(0);
//지금까지의 점수 저장
const choiceScoreList = ref([]);
//결과 값 저장
let quizResult = "";

//라우터 설정 해주기
const router = useRouter();

const incrementCurrentPage = (choice) => {
  console.log("page : => ", currentPage.value);
  console.log(TotalQuizList.value.questions.length - 1);
  console.log(choice.score);
  choiceScoreList.value.push(choice.score);

  if (TotalQuizList.value.questions.length - 1 <= currentPage.value) {
    console.log("더이상의 데이터는 존재하지않습니다. 계산 ㄱㄱ ");
    //문제를 모두 풀엇으면 QuizCommon파일로 지금까지의 답변과 TestType 보내서 계산해서 받음
    quizResult = QuizCommon.quizScoreCalculate(
      choiceScoreList,
      TotalQuizList.value.testType
    );

    testStore.setresultScore(quizResult);
    console.log("testStore.resultScore : -> ", testStore.resultScore.value);
    router.push({ name: "resultpage" }); // 페이지 이동
    return;
  }
  currentPage.value++;
};
</script>

<style lang="scss" scoped></style>
