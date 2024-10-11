<template>
  <div class="container">
    <div class="text-h5 text-center text-weight-bold q-mb-xl q-mt-xl q-mp-none">
      결과지
    </div>
    <div class="blank-space"></div>

    <q-img
      class="image-container"
      :src="`/images/Thumbnail.png`"
      loading="lazy"
      alt="Thumbnail"
      fit="fill"
      width="350px"
      height="750px"
      :style="{ filter: imageFilter }"
    />
    <!-- 안봤으면 나오게 -->
    <div v-if="!resultboolean" class="image-button" :class="`shadow-${19}`">
      <q-card class="card-container text-h6 flex">
        <q-card-section flat dense no-border>
          adsa shadow-2aasdasd asdasdafw
        </q-card-section>

        <q-btn
          @click="clearBlur"
          class="bg-black text-h5 text-white q-ma-sm"
          :size="xl"
          label="Clear Blur"
        ></q-btn>
      </q-card>
    </div>
    <div class="blank-space"></div>
    <div class="text-h4">끝</div>
  </div>
</template>

<script setup>
import { useTestStore } from "src/stores/useTestStore";
import { onMounted, ref } from "vue";

const VITE_COUPANG_URL_LINK = import.meta.env.VITE_COUPANG_URL_LINK;

const teststore = useTestStore();

const openLink = () => {
  window.open(VITE_COUPANG_URL_LINK, "_blank").focus();
};

const imageFilter = ref("blur(10px) sepia()");

const clearBlur = () => {
  imageFilter.value = ""; // 블러와 세피아 필터를 제거
  sessionStorage.setItem("resultLink", true);
  resultboolean.value = true;
  openLink();
};
const resultboolean = ref(sessionStorage.getItem("resultLink") || false);

onMounted(async () => {
  await teststore.getResultList(teststore.currentTestId, teststore.resultScore);

  if (resultboolean.value) {
    imageFilter.value = ""; // 블러와 세피아 필터를 제거
  }
});
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.image-container {
  transition: filter 0.3s ease;
  position: relative;
}
.image-button {
  position: absolute;
}
.card-container {
  display: flex;
  justify-content: center;
  max-width: 250px;
  opacity: 0.8;
}

.blank-space {
  height: 10vh;
}
</style>
