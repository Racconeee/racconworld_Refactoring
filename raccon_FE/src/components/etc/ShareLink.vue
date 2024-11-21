<template>
  <div>
    <q-btn
      class="share_btn q-mt-md"
      label="공유하기"
      @click="copyTextToClipboard"
    />

    <div v-if="showImage" class="success-image">
      테스트 링크가 복사되었습니다.
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { copyToClipboard } from "quasar";
import { useTestStore } from "src/stores/useTestStore";

const testStore = useTestStore();
const showImage = ref(false);

//Test링크가 없어진다면 대체 링크 생성하기
const copyTextToClipboard = () => {
  if (testStore.ShareLink === undefined) {
    console.log("testStore.ShareLink 의 값이  undefined 인 관계로 링크생성");

    testStore.setShareLink("/quiz/ready/" + localStorage.getItem("testId"));
  }
  console.log("생성된 링크 값 : ");
  console.log(testStore.ShareLink);

  copyToClipboard(testStore.ShareLink)
    .then(() => {
      showImage.value = true;

      setTimeout(() => {
        showImage.value = false;
      }, 2000);
    })
    .catch(() => {});
};
</script>

<style lang="scss" scoped>
.success-image {
  position: fixed;
  top: 20px;
  right: 20px;
  background: #fff;
  border: 1px solid #ddd;
  padding: 10px;
  border-radius: 12px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
  display: flex;
  align-items: center;
  justify-content: center;
}

.share_btn {
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
