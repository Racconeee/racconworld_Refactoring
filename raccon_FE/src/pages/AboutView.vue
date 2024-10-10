<template>
  <div class="container">
    <div class="text-h4 q-ma-lg flex flex-center">Test 즐기는 방법</div>
    <div class="flex flex-center">
      <q-card class="my-card bg-secondary text-white">
        <q-card-section>
          <div class="text-h5 flex flex-center q-mb-lg text-black text-bold">
            라쿤의 한마디!
          </div>
          <div class="text-subtitle2">
            1. 거짓으로 하지말고 진심으로 해주세요.
          </div>
          <div class="text-subtitle2">2. 누구보다 재미있게 즐겨주세요.</div>
          <div class="text-subtitle2">3. 결과지를 친구들에게 공유하세요.</div>
          <div class="text-subtitle2">4. 친구들과 결과를 비교해보세요.</div>
          <div class="text-subtitle2">
            5. 나를 알아가는 감정에 행복을 느껴보세요.
          </div>
        </q-card-section>

        <q-separator dark />
      </q-card>
    </div>
    <div class="row q-mt-xl flex space-between">
      <q-card
        class="col-5 flex flex-center column my-card bg-secondary text-white"
      >
        <q-card-section>
          <div class="text-h6 text-black q-mt-lg text-bold">PERSONALITY</div>
        </q-card-section>

        <q-card-section>
          <div>12문제, 선택지 2개를 기본으로 만듭니다.</div>
          <div>선택지들의 데이터 타입은 String입니다.</div>
        </q-card-section>

        <q-separator dark />
      </q-card>
      <div class="col-2"></div>

      <q-card
        class="col-5 flex flex-center column my-card bg-secondary text-white"
      >
        <q-card-section>
          <div class="text-h6 text-black q-mt-lg text-bold">SCORE</div>
        </q-card-section>

        <q-card-section>
          <div>10문제, 선택지 3개를 기본으로 만듭니다.</div>
          <div>선택지들의 데이터 타입은 Number입니다.</div>
          <div>기본적으로 점수는 0~100점으로 이루어져 있으며</div>
          <div>점수의 기본 단위는 0~10을 권장합니다.</div>
          <div>100점을 넘겨도 상관없지만 그만큼 결과지가 늘어나게 됩니다.</div>
        </q-card-section>

        <q-separator dark />
      </q-card>
    </div>

    <div class="flex flex-center q-ma-lg">
      <q-card class="my-card bg-secondary text-white">
        <q-card-section>
          <div class="text-h5 flex flex-center q-mb-lg text-black text-bold">
            라이센스
          </div>
          <div class="text-subtitle2">글꼴</div>
          <div class="text-subtitle2">2. 누구보다 재미있게 즐겨주세요.</div>
          <div class="text-subtitle2">3. 결과지를 친구들에게 공유하세요.</div>
          <div class="text-subtitle2">4. 친구들과 결과를 비교해보세요.</div>
          <div class="text-subtitle2">
            5. 나를 알아가는 감정에 행복을 느껴보세요.
          </div>
        </q-card-section>

        <q-separator dark />
      </q-card>
    </div>
    <div class="flex flex-center q-ma-lg">
      <q-card class="my-card bg-secondary text-white">
        <q-card-section>
          <div class="text-h5 flex flex-center q-mb-lg text-black text-bold">
            라이센스
          </div>
          <div class="text-subtitle2">글꼴</div>
          <div class="text-subtitle2">2. 누구보다 재미있게 즐겨주세요.</div>
          <div class="text-subtitle2">3. 결과지를 친구들에게 공유하세요.</div>
          <div class="text-subtitle2">4. 친구들과 결과를 비교해보세요.</div>
          <div class="text-subtitle2">
            5. 나를 알아가는 감정에 행복을 느껴보세요.
          </div>
        </q-card-section>

        <q-separator dark />
      </q-card>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped>
.container {
  // height: 100vh;
  display: flex;
  flex-direction: column;
}
</style>
