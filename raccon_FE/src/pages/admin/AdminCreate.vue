<template>
  <div class="row items-center flex">
    <div class="q-ma-lg text-h6">퀴즈 개수 :</div>
    <q-select
      v-model="quizLength"
      outlined
      :options="quizLengthList"
      :style="{ width: '4vw', minWidth: '70px' }"
    >
    </q-select>
    <div class="q-ma-lg text-h6">선택지 개수:</div>
    <q-select
      v-model="choiceLength"
      outlined
      :options="choiceLengthList"
      :style="{ width: '4vw', minWidth: '70px' }"
    >
    </q-select>
    <div class="q-ma-lg text-h6">테스트 타입 :</div>
    <q-select
      v-model="testType"
      outlined
      :options="testTypeList"
      :style="{ width: '8vw', minWidth: '150px' }"
    >
    </q-select>
    <q-space></q-space>

    <!-- <q-btn color="primary" label="임시 버튼 " class="q-mr-sm q-ml-lg"></q-btn> -->
    <q-btn color="primary" label="진짜 저장" @click="openCreateDialog"></q-btn>

    <!-- 다이얼로그 컴포넌트 -->
    <TestCreateDialog
      v-model="createDialog"
      @send-close="closeCreateDialog"
    ></TestCreateDialog>

    <TestEmptyInputDialog
      v-model="emptyInputDialog"
      @send-close="closeEmptyInputDialog"
    ></TestEmptyInputDialog>
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
      <q-input class="col-9" v-model="choice.choiceText">
        <template v-slot:prepend>
          <div>선택지 :</div>
        </template>
      </q-input>
      <q-input class="col-3" v-model="choice.score">
        <template v-slot:prepend>
          <div>점수 :</div>
        </template>
      </q-input>
    </div>
  </div>
  <div class="items-center q-mt-lg">
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
            :style="{ width: '400px' }"
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
        max-files="16"
        multiple
        :style="{ width: '400px' }"
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
import TestEmptyInputDialog from "src/components/admin/TestEmptyInputDialog.vue";

const adminStore = useAdminStore();

const createDialog = ref(false);
const emptyInputDialog = ref(false);
// 이걸로 할수 있지만 일부러 나눠서 코드가 늘어나더라도 api 2번 나가는거 방지함
// createDialog.value = !createDialog.value;

const MAX_FILE_SIZE = 50 * 1024 * 1024;

//testType의 값에 따라서 api 호출한다. + 팝업창값도 변경해서 나오게 만듬
//중복 코드가 많이 보이기는함 리팩토링 필요
const validateInputs = () => {
  const isValid = ref(true); // 모든 검증을 확인하는 플래그 변수

  if (!testName.value) {
    adminStore.emptyInputCheck("테스트 이름의 값을 입력해주세요.");
    isValid.value = false;
  }
  if (!testType.value) {
    adminStore.emptyInputCheck("테스트의 타입을 설정해주세요.");
    isValid.value = false;
  }
  if (!questions.length || questions.some((q) => !q.questionText.trim())) {
    adminStore.emptyInputCheck("모든 질문을 입력해주세요.");
    isValid.value = false;
  }
  //숫자만 있어야함
  if (testType.value === "SCORE") {
    const typeTestScore = questions.some((q) =>
      q.choices.some((c) => isNaN(Number(c.score)))
    );
    if (typeTestScore) {
      adminStore.emptyInputCheck(
        "현재 테스트타입은 SCORE타입이여서 선택지에 String값을 넣을 수 없습니다."
      );
      isValid.value = false;
    }
  }

  //숫자만 있으면 안됨
  if (testType.value === "PERSONALITY") {
    const typeTestPersonality = questions.some((q) =>
      q.choices.some((c) => !isNaN(Number(c.score)))
    );

    if (typeTestPersonality) {
      adminStore.emptyInputCheck(
        "현재 테스트타입은 PERSONALITY타입이여서 선택지에 number값을 넣을 수 없습니다."
      );
      isValid.value = false;
    }
  }

  if (questions.some((q) => q.choices.some((c) => !c.choiceText.trim()))) {
    adminStore.emptyInputCheck("모든 선택지를 입력해주세요.");
    isValid.value = false;
  }
  if (!testImage.value) {
    adminStore.emptyInputCheck("테스트의 썸네일 이미지를 넣어주세요.");
    isValid.value = false;
  }
  if (!resultImages.value || resultImages.value.length === 0) {
    adminStore.emptyInputCheck("결과지를 넣어주세요.");
    isValid.value = false;
  }
  resultImages.value.some((file) => {
    if (file.size > MAX_FILE_SIZE) {
      adminStore.emptyInputCheck("파일의 용량은 50MB로 제한되어있습니다.");
      isValid.value = false;
      return true; // 조건을 만족하는 경우 반복 중단
    }
  });

  return isValid.value; // 검증 후 플래그 반환
};

const openCreateDialog = async () => {
  if (!validateInputs()) {
    emptyInputDialog.value = true;
    return;
  }
  const formData = new FormData();

  const json = JSON.stringify({
    testName: testName.value,
    testType: testType.value,
    questions,
  });
  const blob = new Blob([json], { type: "application/json" });

  formData.append("testImage", testImage.value);
  resultImages.value.forEach((file) => {
    const filenameWithoutExtension = file.name.replace(/\.[^/.]+$/, "");
    const newFile = new File([file], filenameWithoutExtension, {
      type: file.type,
    });
    formData.append("resultImages", newFile);
  });

  try {
    if (testType.value === "PERSONALITY") {
      formData.append("uploadTestPersonalityReqDto", blob);
      await adminStore.uploadTestPersonality(formData);
    } else if (testType.value === "SCORE") {
      formData.append("uploadTestScoreReqDto", blob);
      await adminStore.uploadTestScore(formData);
    }

    createDialog.value = true;
  } catch (error) {
    createDialog.value = true;

    console.error(error);
  }
};

const closeCreateDialog = () => {
  createDialog.value = false;
  console.log(createDialog.value);
};

const closeEmptyInputDialog = () => {
  emptyInputDialog.value = false;
  adminStore.clearCmptyInput();
  console.log(emptyInputDialog.value);
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

const testImage = ref();
const resultImages = ref([]);

function clearResultImage() {
  resultImages.value = [];
}
</script>

<style lang="scss" scoped></style>

<!-- 모듈 나누는게 안됨 진짜 저장 부분  -->
