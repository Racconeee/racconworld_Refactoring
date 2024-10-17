<template>
  <div class="container">
    <TestMainViewApiError v-if="testStore.getTestError"></TestMainViewApiError>

    <div class="scroll-container" ref="scrollTargetRef">
      <q-infinite-scroll
        @load="onLoadRef"
        :offset="20"
        :scroll-target="scrollTargetRef"
        :disable="!testStore.testListhasNext"
      >
        <TestList :items="testStore.testList"></TestList>

        <TestMainTestEnd v-if="!testStore.testListhasNext"></TestMainTestEnd>
      </q-infinite-scroll>
    </div>
  </div>
</template>

<script setup>
import TestList from "src/components/Test/TestList.vue";
import { ref } from "vue";
import { useTestStore } from "@/stores/useTestStore";
import TestMainTestEnd from "./TestMainTestEnd.vue";
import TestMainViewApiError from "./TestMainViewApiError.vue";

const testStore = useTestStore();

const scrollTargetRef = ref(null);
const pageNumber = ref(0);

// 무한 스크롤 시 데이터 로드

//done()을 호출하게 된다면 무한 스크롤이 더이상 실해되지않다
//testListhasNext (true : 다음 데이터가 더 있다 false : 더이상 없다 )

//done () ->완전 한 끝이 아니고 한번의 로딩이 끝났다는 의미
const onLoadRef = (index, done) => {
  console.log("onLoadRef : 실행");
  console.log(pageNumber.value);
  console.log(testStore.testListhasNext);

  if (testStore.getTestError) {
    console.log("서버와의 연결이 안됨");

    return;
  }
  // if (!testStore.testListhasNext) {
  //   console.log("더이상 데이터가 없음");
  //   return;
  // }

  testStore.getTestList({ pageNumber: pageNumber.value }).then(() => {
    done();
  });

  pageNumber.value++;
};
</script>

<style lang="scss" scoped>
.scroll-container {
  // flex: 1;
  // overflow: auto;
  // height: 80vh;
}
.container {
  // height: 100vh;
  display: flex;
  flex-direction: column;
}
</style>
