import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";
import authAxios from "src/util/http-commons";

const VITE_SERVER_API_URL = import.meta.env.VITE_SERVER_API_URL;

export const useAdminStore = defineStore("admin", () => {
  const uploadTestRes = ref("");
  const uploadTestPersonality = async function (formData) {
    await authAxios({
      method: "post",
      url: `${VITE_SERVER_API_URL}/admin/upload/personality`,
      data: formData,
    })
      .then((res) => {
        uploadTestRes.value = res.data;
      })
      .catch((err) => {
        uploadTestRes.value = err;
        if (err.status === 403) {
          uploadTestRes.value.result = "권한이 존재하지않습니다.";
        }
      });
  };

  // -------------------------------------------------------------------------------------

  const uploadTestScore = async function (formData) {
    await authAxios({
      method: "post",
      url: `${VITE_SERVER_API_URL}/admin/upload/score`,
      data: formData,
    })
      .then((res) => {
        uploadTestRes.value = res.data;
      })
      .catch((err) => {
        uploadTestRes.value = err;
        if (err.status === 403) {
          uploadTestRes.value.result = "권한이 존재하지않습니다.";
        }
      });
  };

  // -------------------------------------------------------------------------------------

  //테스트 생성후 확인 버튼 누르면 있었던
  //테스트 결과 Res 초기화
  const clearUploadTestRes = function () {
    uploadTestRes.value = "";
  };

  // -------------------------------------------------------------------------------------
  //Test업로드 할 떄 값 입력안된거 확인하는 것

  const emptyInputValue = ref("");
  const emptyInputCheck = function (emptyInput) {
    emptyInputValue.value += emptyInput;
  };
  //확인 하고 창 닫았을 떄 emptyInputValue값 초기화
  const clearCmptyInput = function () {
    emptyInputValue.value = "";
  };

  // -------------------------------------------------------------------------------------

  const deleteTestId = ref(0);
  const deletTeststate = ref(0);
  const deleteTest = async function (testId) {
    await authAxios({
      method: "delete",
      url: `${VITE_SERVER_API_URL}/admin/test/delete/${testId}`,
    })
      .then((res) => {
        deletTeststate.value = res.data.status;
      })
      .catch((err) => {
        deletTeststate.value = err.response.data;
      });
  };

  return {
    uploadTestPersonality,
    uploadTestScore,
    deleteTest,
    deleteTestId,
    deletTeststate,
    uploadTestRes,
    clearUploadTestRes,
    emptyInputValue,
    emptyInputCheck,
    clearCmptyInput,
  };
});
