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
        @click="deleteTest"
        color="primary"
      ></q-btn>
    </div>
    <TestInfoList></TestInfoList>
  </div>
</template>

<script setup>
import TestInfoList from "src/components/admin/TestInfoList.vue";
import { useAdminStore } from "src/stores/useAdminStore";

const adminstore = useAdminStore();

const deleteTest = async () => {
  console.log(adminstore.deleteTestId);
  console.log(adminstore.deleteTestId);
  console.log(adminstore.deleteTestId);
  console.log(adminstore.deleteTestId);
  console.log(adminstore.deleteTestId);

  if (adminstore.deleteTestId === 0) {
    alert("Test를 선택하세요.");
    return;
  }

  await adminstore.deleteTest(adminstore.deleteTestId);

  if (adminstore.deletTeststate === 200) {
    alert("TEST가 정상적으로 삭제되었습니다.");
    window.location.reload();
  } else {
    alert("오류가 발생하였습니다.");
  }
};

// onMounted(()=> {
//   teststore.
// })
</script>

<style lang="scss" scoped></style>
