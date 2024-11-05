<template>
  <div class="container">
    <div class="text-h5 text-center text-weight-bold q-mb-xl q-mt-xl q-mp-none">
      나의 결과는 !!
    </div>
    <div class="blank-space"></div>

    <q-img
      class="image-container"
      :src="teststore.resultFilePath"
      loading="lazy"
      alt="Thumbnail"
      fit="fill"
      width="350px"
      height="750px"
      :style="{ filter: imageFilter, borderRadius: '5px' }"
    />
    <!-- 안봤으면 나오게 -->
    <div v-if="!resultboolean" class="image-button" :class="`shadow-${19}`">
      <q-card class="card-container text-h6 flex" :style="{ opacity: 1 }">
        <q-card-section flat>
          쿠팡 방문은 Racconworld 개발자에게 많은 힘이 됩니다.
        </q-card-section>

        <q-btn
          @click="clearBlur"
          class="bg-blue text-h6 text-white q-ma-sm"
          :size="xl"
          label="버튼 누르고 결과 보기"
          :style="{ borderRadius: '5px' }"
        ></q-btn>
      </q-card>
    </div>
    <div class="blank-space"></div>

    <div class="flex justify-center q-mt-xl">
      <q-btn class="Test-select text-h6" @click="goToQuiz"
        >다른 테스트 하러가기</q-btn
      >
    </div>
    <ShareLink></ShareLink>
    <div class="q-mt-xl q-mb-l">
      이 포스팅은 쿠팡 파트너스 활동의 일환으로
      <br />
      이에 따른 일정액의 수수료를 제공받습니다.
    </div>
  </div>
</template>

<script setup>
import { useTestStore } from "src/stores/useTestStore";
import { onMounted, ref } from "vue";
import ShareLink from "src/components/etc/ShareLink.vue";

const teststore = useTestStore();

const imageFilter = ref("blur(10px) sepia()");

const clearBlur = () => {
  imageFilter.value = ""; // 블러와 세피아 필터를 제거
  sessionStorage.setItem("resultLink", true);
  resultboolean.value = true;
  window.open(teststore.getVITE_COUPANG_URL_LINK, "_blank").focus();
};
const resultboolean = ref(sessionStorage.getItem("resultLink") || false);

onMounted(async () => {
  await teststore.getResultList(teststore.currentTestId, teststore.resultScore);

  if (resultboolean.value) {
    imageFilter.value = ""; // 블러와 세피아 필터를 제거
  }

  console.log("teststore.resultFilePath => ", teststore.resultFilePath);
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

.Test-select {
  width: 220px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}
</style>
