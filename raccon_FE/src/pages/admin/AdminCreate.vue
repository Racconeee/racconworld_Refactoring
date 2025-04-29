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
import { ref, reactive, watch } from "vue";
import { useAdminStore } from "@/stores/useAdminStore";
import TestCreateDialog from "@/components/admin/TestCreateDialog.vue";
import TestEmptyInputDialog from "src/components/admin/TestEmptyInputDialog.vue";

const adminStore = useAdminStore();

const createDialog = ref(false);
const emptyInputDialog = ref(false);

const MAX_FILE_SIZE = 50 * 1024 * 1024;

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
      return true;
    }
  });

  return isValid.value;
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
  }
};

const closeCreateDialog = () => {
  createDialog.value = false;
};

const closeEmptyInputDialog = () => {
  emptyInputDialog.value = false;
  adminStore.clearCmptyInput();
};

const testName = ref("");

const quizLength = ref(1);
const quizLengthList = Array.from({ length: 20 }, (_, i) => i + 1);

const choiceLength = ref(1);
const choiceLengthList = Array.from({ length: 5 }, (_, i) => i + 1);

const testType = ref("");
const testTypeList = ["SCORE", "PERSONALITY"];

const questions = reactive([]);

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
