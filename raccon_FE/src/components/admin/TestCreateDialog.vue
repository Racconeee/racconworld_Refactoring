<template>
  <q-dialog v-if="adminStore.uploadTestRes" persistent>
    <q-card class="q-pa-md" style="max-width: 400px">
      <q-card-section class="row items-center q-pb-none">
        <q-icon name="mdi-information" color="primary" size="32px" />
        <div class="text-h6">Test Upload Status</div>
      </q-card-section>

      <q-card-section>
        <q-separator spaced />
        <div>
          <q-badge color="secondary" class="q-mb-sm">
            상태 코드 : {{ adminStore.uploadTestRes.status }}
          </q-badge>
        </div>
        <div class="text-subtitle1 q-mb-md">
          결과 :
          {{ adminStore.uploadTestRes.result }}
        </div>
      </q-card-section>

      <q-card-actions align="right" class="q-pt-none">
        <q-btn color="primary" label="확인" @click="closeDialog" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>
import { useAdminStore } from "src/stores/useAdminStore";
import { useRouter } from "vue-router";

const router = useRouter();
const adminStore = useAdminStore();

const emit = defineEmits(["send-close"]);

const closeDialog = () => {
  adminStore.clearUploadTestRes();
  emit("send-close");
};
</script>

<style scoped>
.q-card {
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}
</style>
