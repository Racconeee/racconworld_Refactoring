<template>
  <q-item
    clickable
    v-ripple
    @click="setStoreTestId"
    class="flex flex-center q-mt-sm"
  >
    <q-item-section
      class="flex flex-column items-center justify-center text-center"
    >
      <q-img
        loading="lazy"
        :src="`/images/Thumbnail.png`"
        alt="Thumbnail"
        height="170px"
        width="170px"
        fit="cover"
      />
      <div>{{ testName }}</div>
      <div>조회수 : {{ view }}</div>
    </q-item-section>
  </q-item>
</template>

<script setup>
import { useTestStore } from "src/stores/useTestStore";
import { useRouter } from "vue-router";

const router = useRouter();
const testStore = useTestStore();

const setStoreTestId = () => {
  console.log(props.testId);

  //로컬 말고 pinia 사용하자
  testStore.setCurrentTestId(props.testId);
  // localStorage.setItem("testId", props.testId);
  console.log("데이터를 저장합니다.");

  router.push({ name: "quizpage" }); // 페이지 이동
};

const props = defineProps({
  testId: {
    type: Number,
  },
  view: {
    type: Number,
    default: 0,
  },
  testName: {
    type: String,
  },
  fileName: {
    type: String,
  },
  filePath: {
    type: String,
  },
});
</script>

<style lang="scss" scoped></style>
