import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";
import authAxios from "src/util/http-commons"; // 해당 api 로 추가 설정하기

const VITE_SERVER_API_URL = import.meta.env.VITE_SERVER_API_URL;

export const useAdminStore = defineStore("admin", () => {
  const uploadTestPersonality = async function (
    testName,
    testType,
    questions,
    testImage,
    resultImages
  ) {
    const formData = new FormData();
    const json = JSON.stringify({ testName, testType, questions });
    const blob = new Blob([json], { type: "application/json" });
    formData.append("uploadTestPersonalityReqDto", blob);
    formData.append("testImage", testImage);
    resultImages.forEach((file) => {
      formData.append("resultImages", file);
    });

    try {
      const res = await axios({
        method: "post",
        url: `${VITE_SERVER_API_URL}/upload/personality`,
        data: formData,
      });
      return res.data; // 응답 데이터 반환
    } catch (err) {
      console.error(err);
      throw err; // 에러를 상위 호출로 전달
    }
  };

  const uploadTestScore = async function (
    testName,
    testType,
    questions,
    testImage,
    resultImages
  ) {
    const formData = new FormData();
    const json = JSON.stringify({ testName, testType, questions });
    const blob = new Blob([json], { type: "application/json" });
    formData.append("UploadTestScoreReqDto", blob);
    formData.append("testImage", testImage);
    resultImages.forEach((file) => {
      formData.append("resultImages", file);
    });

    try {
      const res = await axios({
        method: "post",
        url: `${VITE_SERVER_API_URL}/upload/score`,
        data: formData,
      });
      return res.data; // 응답 데이터 반환
    } catch (err) {
      console.error(err);
      throw err; // 에러를 상위 호출로 전달
    }
  };

  // -------------------------------------------------------------------------------------

  const deleteTest = async function (params = {}) {
    await axios({
      method: "delete",
      url: `${VITE_SERVER_API_URL}/api/admin/test/delete/{testId}`,
      params: params,
    })
      .then((res) => {
        console.log(res);

        testList.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return {
    uploadTestPersonality,
    uploadTestScore,
    deleteTest,
  };
});
