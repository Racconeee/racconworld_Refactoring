<template>
  <q-dialog>
    <q-card>
      <q-card-section>
        <div>{{ apiRes }}</div>
      </q-card-section>
      <q-card-actions>
        <q-btn flat label="확인" @click="closeDialog" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>
import { defineEmits, defineProps } from "vue";

defineProps({
  apiRes: String,
});

const emit = defineEmits(["send-close"]);

const closeDialog = () => {
  emit("send-close");
};
</script>
