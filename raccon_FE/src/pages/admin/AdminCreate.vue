<template>
  <div class="row items-center flex">
    <div class="q-ma-lg text-h6">퀴즈 개수 :</div>
    <q-select
      v-model="quizLength"
      outlined
      :options="quizLengthList"
      :style="{ width: '4vw' }"
    >
    </q-select>
    <div class="q-ma-lg text-h6">선택지 개수:</div>
    <q-select
      v-model="choiceLength"
      outlined
      :options="choiceLengthList"
      :style="{ width: '4vw' }"
    >
    </q-select>
    <div class="q-ma-lg text-h6">Test type :</div>
    <q-select
      v-model="testType"
      outlined
      :options="testTypeList"
      :style="{ width: '8vw' }"
    >
    </q-select>
    <q-space></q-space>

    <!-- <q-btn color="primary" label="임시 버튼 " class="q-mr-sm q-ml-lg"></q-btn> -->
    <q-btn color="primary" label="진짜 저장" @click="openDialog"></q-btn>

    <!-- 다이얼로그 컴포넌트 -->
    <TestCreateDialog
      v-model="createDialog"
      :api-res="uploadTestRes"
      @send-close="closeDialog"
    ></TestCreateDialog>
  </div>

  <div>
    <q-input standout v-model="testName">
      <template v-slot:prepend>
        <div>테스트 이름 :</div>
      </template>
    </q-input>
  </div>
  <q-separator class="q-ma-lg"></q-separator>
  <div v-for="(question, qIndex) in questions" :key="'question-' + qIndex">
    <q-input standout v-model="question.questionText">
      <template v-slot:prepend>
        <div>질문 :</div>
      </template>
    </q-input>
    <div
      v-for="(choice, cIndex) in question.choices"
      :key="'choice-' + cIndex"
      class="row"
    >
      <q-input class="col-10" v-model="choice.choiceText">
        <template v-slot:prepend>
          <div>선택지 :</div>
        </template>
      </q-input>
      <q-input class="col-2" v-model="choice.score">
        <template v-slot:prepend>
          <div>점수 :</div>
        </template>
      </q-input>
    </div>
  </div>
  <div class="items-center q-ma-lg">
    <div class="q-gutter-md row items-start q-ml-sm">
      <q-file filled bottom-slots v-model="testImage" counter>
        <template v-slot:before>
          Test 결과 &nbsp;
          <q-icon name="folder_open" />
        </template>

        <template v-slot:hint> Test </template>

        <template v-slot:append>
          <q-btn round dense flat icon="add" @click.stop.prevent />
        </template>

        <template v-slot:remove>
          <q-icon
            name="close"
            @click.stop.prevent="testImage = null"
            class="cursor-pointer"
          />
        </template>
      </q-file>
    </div>
  </div>

  <div class="items-center q-ma-lg q-mt-xl">
    <div class="q-gutter-md">
      <q-file
        filled
        bottom-slots
        v-model="resultImages"
        counter
        max-files="12"
        multiple
        :style="{ width: '30vw' }"
      >
        <template v-slot:before>
          Result 결과 &nbsp;
          <q-icon name="folder_open" />
        </template>
        <template v-slot:hint> Result </template>

        <template v-slot:append>
          <q-icon
            name="close"
            @click.stop.prevent="clearResultImage"
            class="cursor-pointer"
          />
        </template>
      </q-file>
    </div>
  </div>
</template>

<script setup>
// url="http://localhost:4444/upload" .env 파일에서 데이터 가져와서 하는걸로 변경하기
import { ref, reactive, watch } from "vue";
import { useAdminStore } from "@/stores/useAdminStore";
import TestCreateDialog from "@/components/admin/TestCreateDialog.vue";

const adminStore = useAdminStore();

const createDialog = ref(false);
// 이걸로 할수 있지만 일부러 나눠서 코드가 늘어나더라도 api 2번 나가는거 방지함
// createDialog.value = !createDialog.value;

//testType의 값에 따라서 api 호출한다. + 팝업창값도 변경해서 나오게 만듬
//중복 코드가 많이 보이기는함 리팩토링 필요
const uploadTestRes = ref();
const openDialog = async () => {
  if (testType.value === "PERSONALITY") {
    console.log("PERSONALITY 실행");

    try {
      uploadTestRes.value = await adminStore.uploadTestPersonality(
        testName.value,
        testType.value,
        questions,
        testImage.value,
        resultImages.value
      );
    } catch (error) {
      console.error(error);
    }
  }
  if (testType.value === "SCORE") {
    console.log("SCORE 실행");

    try {
      uploadTestRes.value = await adminStore.uploadTestScore(
        testName.value,
        testType.value,
        questions,
        testImage.value,
        resultImages.value
      );
      console.log(uploadTestRes.value); // Ref의 내부 값 접근
    } catch (error) {
      console.error(error);
    }
  }
  console.log(uploadTestRes);
  console.log(uploadTestRes);
  console.log(uploadTestRes);

  console.log(uploadTestRes.value); // Ref의 내부 값 접근

  console.log(uploadTestRes.value); // Ref의 내부 값 접근

  createDialog.value = true;
  console.log(createDialog.value);
};

const closeDialog = () => {
  createDialog.value = false;
  console.log(createDialog.value);
};

const testName = ref("");

const quizLength = ref(1);
const quizLengthList = Array.from({ length: 20 }, (_, i) => i + 1);

const choiceLength = ref(1);
const choiceLengthList = Array.from({ length: 5 }, (_, i) => i + 1);

const testType = ref("");
const testTypeList = ["SCORE", "PERSONALITY"];

const questions = reactive([]);

//2개의 데이터 중 하나라도 변경된다면
//일부러 null 값을 넣어서 동적으로 타입 넣기
watch(
  [quizLength, choiceLength],
  () => {
    questions.length = 0;

    for (let i = 0; i < quizLength.value; i++) {
      questions.push({
        questionText: "",
        choices: Array.from({ length: choiceLength.value }, () => ({
          choiceText: "",
          score: testType.value === "SCORE" ? 0 : null,
        })),
      });
    }
  },
  { immediate: true }
);

const testImage = ref([]);
const resultImages = ref([]);

function clearResultImage() {
  resultImages.value = [];
}
</script>

<style lang="scss" scoped></style>

<!-- 모듈 나누는게 안됨 진짜 저장 부분  -->
