<template>
  <div class="container">
    <div class="text-h5 text-center text-weight-bold q-mb-xl q-mt-xl q-mp-none">
      결과지
    </div>
    <div class="blank-space"></div>

    <q-img
      class="image-container"
      :src="`/images/Thumbnail.png`"
      alt="Thumbnail"
      :fit="cover"
      width="80vw"
      height="90vh"
      :style="{ filter: imageFilter }"
    />

    <div v-if="showButton" class="image-button" :class="`shadow-${19}`">
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
import { ref } from "vue";

const imageFilter = ref("blur(10px) sepia()");
const showButton = ref(true);
const clearBlur = () => {
  imageFilter.value = ""; // 블러와 세피아 필터를 제거
  showButton.value = "";
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.image-container {
  transition: filter 0.3s ease; /* 필터 변경 시 부드러운 애니메이션 효과 */
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
