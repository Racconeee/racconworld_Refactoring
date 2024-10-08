<template>
  <div>
    <div class="text-h4 q-ma-lg flex flex-center">Test 생성방법</div>
    <div class="flex flex-center">
      <q-card class="my-card bg-secondary text-white">
        <q-card-section class="">
          <div class="text-h5 flex flex-center q-mb-lg text-black text-bold">
            공통사항
          </div>
          <div class="text-subtitle2">Test 썸네일 결과지는 단일로 1장</div>
          <div class="text-subtitle2">
            Reulst 결과지는 12장 까지 가능합니다.
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
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>
