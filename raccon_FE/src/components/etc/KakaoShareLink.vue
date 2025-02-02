<template>
  <div>
    <button @click="sendKakaoShare">카카오톡으로 공유하기</button>
  </div>
</template>

<script>
export default {
  methods: {
    sendKakaoShare() {
      if (window.Kakao) {
        window.Kakao.Share.sendScrap({
          requestUrl: window.location.href, // 현재 페이지 URL
          templateId: 1151133,
          templateArgs: {
            title: "공유할 타이틀",
            description: "설명",
          },
        });
      }
    },
  },
};
</script>
