<template>
  <div class="scroll-container" ref="scrollTargetRef">
    <q-infinite-scroll
      @load="onLoadRef"
      :offset="20"
      :scroll-target="scrollTargetRef"
      :disable="!hasNext"
    >
      <TestList :items="testStore.testList"></TestList>

      <!-- 부모 컴포넌트의 내용을 삽입할 슬롯 -->
      <TestMainTestEnd v-if="!hasNext"></TestMainTestEnd>
      <!-- 마지막 데이터 표시 컴포넌트 -->
    </q-infinite-scroll>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useTestStore } from "@/stores/useTestStore";

import TestMainTestEnd from "./TestMainTestEnd.vue";
import TestList from "src/components/Test/TestList.vue";

const scrollTargetRef = ref(null);
const pageNumber = ref(0);

const testStore = useTestStore();

// 부모로부터 받아올 속성 (Props)
const props = defineProps({
  hasNext: Boolean, // 다음 데이터가 있는지 여부
  onLoad: Function, // 데이터를 로드할 함수
  getTestError: Boolean, // 에러 여부
});

// done() 호출 시 무한 스크롤 종료 처리
const onLoadRef = (index, done) => {
  console.log("onLoadRef 실행");

  if (props.getTestError) {
    console.log("서버와의 연결이 안됨");
    return;
  }

  // 부모 컴포넌트에서 넘겨받은 데이터 로드 함수 호출
  props.onLoad(pageNumber.value).then(() => {
    done(); // 로드 완료 후 done() 호출
  });

  pageNumber.value++;
};
</script>

<style scoped>
.scroll-container {
  display: flex;
  flex-direction: column;
}
</style>
