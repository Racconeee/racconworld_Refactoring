<template>
  <div class="container">
    <div class="scroll-container" ref="scrollTargetRef">
      <q-infinite-scroll
        @load="onLoadRef"
        :offset="20"
        :scroll-target="scrollTargetRef"
      >
        <section class="q-gutter-y-sm q-mt-lg">
          <q-card v-for="item in testStore.testList" :key="item.id">
            <q-card-section clickable v-ripple class="flex flex-center q-mt-sm">
              <q-radio
                v-model="adminStore.deleteTestId"
                :val="item.testId"
                :label="'선택'"
              />
              <q-item-section
                class="flex flex-column items-center justify-center text-center"
              >
                <div>조회수 : {{ item.testName }}</div>

                <div>조회수 : {{ item.view }}</div>
              </q-item-section>
            </q-card-section>
          </q-card>
        </section>

        <template v-slot:no-more>
          <div class="q-mt-md">
            <p>No more items</p>
          </div>
        </template>

        <div v-if="testStore.getTestError">
          <p>서버에 연결할 수 없습니다. 다시 시도해 주세요.</p>
        </div>
      </q-infinite-scroll>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useTestStore } from "@/stores/useTestStore";
import { useAdminStore } from "@/stores/useAdminStore";

const testStore = useTestStore();
const adminStore = useAdminStore();

const scrollTargetRef = ref(null);
const pageNumber = ref(0);

const onLoadRef = (index, done) => {

  if (testStore.getTestError) {
    return;
  }
  if (!testStore.testListhasNext) {
    return;
  }

  testStore.getTestList({ pageNumber: pageNumber.value }).then(() => {
    done();
  });

  pageNumber.value++;
};
</script>

<style lang="scss" scoped>
.scroll-container {
  flex: 1;
  overflow: auto;
  height: 100vh;
}
.container {
  height: 100vh;
  display: flex;
  flex-direction: column;
}
</style>
