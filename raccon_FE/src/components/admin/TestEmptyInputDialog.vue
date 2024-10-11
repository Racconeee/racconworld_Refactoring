<template>
  <q-dialog v-if="adminStore.emptyInputValue" persistent>
    <q-card class="q-pa-md" style="max-width: 400px">
      <q-card-section class="row items-center q-pb-none">
        <div class="text-h6 q-ml-sm">Test EmptyInput Status</div>
      </q-card-section>

      <q-card-section>
        <q-separator spaced />
        <q-badge class="text-h6 q-mt-sm q-mb-sm bg-green-4 text-black"
          >주의</q-badge
        >
        <div>
          <div
            border
            v-html="emptyInputErr"
            class="q-mb-sm text-body1 bg-teal-2 q-border q-border-secondary rounded-borders"
          ></div>
        </div>
      </q-card-section>

      <q-card-actions align="right" class="q-pt-none">
        <q-btn color="primary" label="확인" @click="closeDialog" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>
import { defineEmits, computed } from "vue";
import { useAdminStore } from "src/stores/useAdminStore";

const adminStore = useAdminStore();

const emit = defineEmits(["send-close"]);

const closeDialog = () => {
  adminStore.clearUploadTestRes();
  emit("send-close");
};
const emptyInputErr = computed(() => {
  return adminStore.emptyInputValue.split(".").join(".<br>");
});
</script>

<style scoped>
.q-card {
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}
</style>
