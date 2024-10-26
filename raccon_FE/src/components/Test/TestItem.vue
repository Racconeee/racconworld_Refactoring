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
        :src="filePath"
        alt="Thumbnail"
        height="270px"
        width="270px"
        fit="cover"
        :style="{ borderRadius: '5px' }"
      />
      <div>{{ testName }}</div>
      <div><q-icon name="pets" /> {{ view }}</div>
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
  //이부분 나중에 변경하기
  // quiz {id} 동적 경로 설정한다면.
  testStore.setCurrentTestId(props.testId);
  // localStorage.setItem("testId", props.testId);
  console.log("데이터를 저장합니다.");
  router.push({ path: `/quiz/ready/${props.testId}` }); // 페이지 이동
};

const props = defineProps({
  testId: {
    type: [String, Number],
  },
  view: {
    type: Number,
    default: 0,
  },
  testName: {
    type: String,
  },
  filePath: {
    type: String,
  },
});
</script>

<style lang="scss" scoped></style>
