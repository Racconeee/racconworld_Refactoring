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

  testStore.setCurrentTestId(props.testId);
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
