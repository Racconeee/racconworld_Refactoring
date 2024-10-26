<template>
  <div class="container">
    <div class="header text-h4 q-my-md text-center">서비스 이용약관</div>
    <div class="content-wrapper flex justify-center q-mb-xl">
      <q-card class="terms-card">
        <q-card-section>
          <div class="section">
            <div class="section-title">목적</div>
            <p class="section-content">
              본 사이트는 유머러스하고 재미있는 테스트를 제공하여 사용자의
              즐거움을 높이는 것을 목적으로 합니다. 모든 테스트는 단순한 재미를
              위해 만들어졌으며, 진지한 참고 자료로 사용되지 않음을
              알려드립니다.
            </p>
          </div>

          <div class="section">
            <div class="section-title">서비스의 내용</div>
            <p class="section-content">
              본 사이트에서 제공하는 테스트는 유머와 가벼운 오락성을 목적으로
              작성되었으며, 과학적 근거가 없고 의학적 소견이 아닙니다. 테스트
              결과는 단순한 재미를 위한 것이며, 실제 상황과 다를 수 있습니다.
              이를 바탕으로 어떠한 결정을 내리지 않길 권장드립니다.
            </p>
          </div>

          <div class="section">
            <div class="section-title">책임의 한계</div>
            <p class="section-content">
              본 사이트에서 제공하는 모든 콘텐츠는 사용자에게 가벼운 즐거움을
              제공하기 위한 것입니다. 콘텐츠와 테스트 결과에 대해 사용자에게
              미치는 어떠한 영향에 대해서도 본 사이트는 책임지지 않습니다. 본
              사이트의 테스트는 전문가의 조언을 대체하지 않으며, 건강, 직업,
              관계 등 중요한 결정을 내리는 데 참고하지 마십시오.
            </p>
          </div>

          <div class="section">
            <div class="section-title">광고 및 제3자 링크</div>
            <p class="section-content">
              본 사이트는 쿠팡 및 기타 광고 링크를 제공합니다. 광고는 사용자가
              더 다양한 콘텐츠를 즐길 수 있도록 지원하는 목적입니다. 광고 클릭
              시 제3자의 사이트로 이동되며, 해당 사이트에서 제공하는 정보와
              서비스에 대해 본 사이트는 책임지지 않습니다.
            </p>
          </div>

          <div class="section">
            <div class="section-title">데이터 수집 및 분석</div>
            <p class="section-content">
              본 사이트는 사용자 경험 개선을 위해 구글 애널리틱스를 사용하여
              방문 기록을 익명으로 수집하고 분석합니다. 수집된 데이터는 통계적
              목적에 한정하여 사용되며, 개인 식별 정보는 포함되지 않습니다. 구글
              애널리틱스를 통한 데이터 수집은 사이트 개선을 위한 자료로
              사용되며, 사용자는 브라우저 설정을 통해 쿠키 수집을 거부할 수
              있습니다.
            </p>
          </div>

          <div class="section">
            <div class="section-title">사용자의 의무</div>
            <p class="section-content">
              본 사이트의 콘텐츠를 비상업적인 개인적 용도로만 사용할 수
              있습니다. 본 사이트의 내용을 무단으로 복제, 배포, 수정하거나
              상업적 목적으로 이용하는 것은 금지됩니다.
            </p>
          </div>

          <div class="section">
            <div class="section-title">서비스 변경 및 중단</div>
            <p class="section-content">
              본 사이트는 언제든지 서비스 내용을 변경하거나 중단할 수 있는
              권리를 보유하고 있습니다. 이는 사전 공지 없이 진행될 수 있습니다.
            </p>
          </div>

          <div class="section">
            <div class="section-title">기타</div>
            <p class="section-content">
              본 약관에 동의하지 않는 경우, 본 사이트의 이용을 중지해 주시기
              바랍니다. 이용 약관은 수시로 변경될 수 있으며, 변경된 내용은
              사이트에 게시됩니다.
            </p>
          </div>
        </q-card-section>
      </q-card>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.header {
  margin-bottom: 20px;
}

.content-wrapper {
  width: 100%;
  max-width: 800px;
}

.terms-card {
  width: 100%;
  background-color: #607d8b;
  color: white;
  border-radius: 10px;
  padding: 20px;
}

.section {
  margin-bottom: 20px;
}

.section-title {
  font-weight: bold;
  font-size: 1.2em;
  margin-bottom: 10px;
}

.section-content {
  line-height: 1.6;
}
</style>
