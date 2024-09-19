<template>
  <div>
    <div class="text-h4">테스트 관리 Page</div>

    <div class="justify-end flex row">
      <q-btn
        class="q-ma-sm"
        label="생성하기 "
        unelebated
        color="primary"
      ></q-btn>
      <q-btn
        class="q-ma-sm"
        label="삭제하기 "
        unelebated
        color="primary"
      ></q-btn>
    </div>
    <section class="q-gutter-y-sm q-mt-lg">
      <q-card v-for="id in 10" :key="id">
        <q-card-section>{{ id }} 번째 게시글 </q-card-section>
      </q-card>
    </section>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>
