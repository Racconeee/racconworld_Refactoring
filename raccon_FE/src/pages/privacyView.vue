<template>
  <div class="container">
    <div class="header text-h4 q-my-md text-center">개인정보취급방침</div>
    <div class="content-wrapper flex justify-center">
      <q-card class="policy-card">
        <q-card-section>
          <div class="section-title">현재는 테스트 목적입니다.</div>
          <div class="section-title">개인정보의 수집 및 이용 목적</div>
          <p class="section-content">
            본 사이트는 사용자의 개인정보를 수집하지 않습니다. 모든 서비스는
            사용자 정보 없이 운영됩니다. 테스트 진행 및 결과 저장을 위해 로컬
            스토리지와 세션 스토리지를 사용합니다. 일부 테스트 결과는 세션
            스토리지에서 꺼내어 서버로 전송될 수 있으며, 이는 결과 확인을 위한
            목적으로만 사용됩니다.본 사이트는 광고 목적으로 쿠팡 및 기타 광고
            링크를 제공합니다. 광고를 클릭하면 제3자의 개인정보 처리 방침이
            적용될 수 있습니다.
          </p>

          <div class="section-title">개인정보의 보유 및 이용 기간</div>
          <p class="section-content">
            본 사이트는 서버에 개인정보를 저장하지 않으며, 로컬 스토리지와 세션
            스토리지에 저장된 데이터는 사용자가 직접 삭제할 수 있습니다. 로컬
            스토리지와 세션 스토리지의 데이터는 사용자 기기 내에서만 존재하며,
            사이트 방문이 끝난 후에도 남을 수 있습니다.
          </p>

          <div class="section-title">개인정보의 제3자 제공</div>
          <p class="section-content">
            본 사이트는 개인정보를 제3자에게 제공하지 않습니다. 다만, 쿠팡 및
            기타 광고 링크 클릭 시 해당 제3자의 개인정보 처리 방침에 따라 정보가
            수집될 수 있습니다.
          </p>

          <div class="section-title">쿠키 및 기타 기술의 사용</div>
          <p class="section-content">
            본 사이트는 사용자 경험 개선 및 사이트 분석을 위해 구글 애널리틱스를
            사용합니다. 이를 통해 수집된 데이터는 익명으로 처리되며, 사용자의
            개인 식별 정보는 포함되지 않습니다. 구글 애널리틱스는 쿠키를
            사용하여 사용자의 사이트 방문 기록을 분석합니다. 이 정보는 사이트
            개선을 위한 통계적 자료로만 사용됩니다. 사용자 활동을 추적하기 위해
            쿠키를 사용할 수 있으며, 사용자 기기 내에서 관리할 수 있습니다.
          </p>

          <div class="section-title">광고 및 제3자 링크</div>
          <p class="section-content">
            본 사이트는 쿠팡 및 기타 제3자의 광고 링크를 통해 수익을 창출할 수
            있습니다. 광고 클릭 시 제3자의 개인정보 처리 방침이 적용되며, 해당
            광고 제공자와 관련된 정보 수집에 대해 본 사이트는 책임지지 않습니다.
          </p>

          <div class="section-title">
            개인정보 보호를 위한 기술적/관리적 대책
          </div>
          <p class="section-content">
            본 사이트는 개인정보를 저장하거나 관리하지 않으므로, 추가적인 보안
            대책이 필요하지 않습니다. 로컬 스토리지와 세션 스토리지는 사용자
            기기 내에서만 작동하며, 웹 브라우저의 보안 설정을 통해 관리할 수
            있습니다.
          </p>

          <div class="section-title">문의처</div>
          <p class="section-content">
            서비스와 관련된 문의 사항은 관리자에게 연락해 주시기 바랍니다.
          </p>
        </q-card-section>
        <q-separator dark />
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

.policy-card {
  width: 100%;
  background-color: #607d8b;
  color: white;
  border-radius: 10px;
}

.section-title {
  font-weight: bold;
  margin-top: 15px;
  margin-bottom: 10px;
}

.section-content {
  line-height: 1.6;
  margin-bottom: 15px;
}
</style>
