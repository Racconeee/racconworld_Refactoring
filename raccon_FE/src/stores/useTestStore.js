// src/stores/testStore.js
import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";

const VITE_SERVER_API_URL = import.meta.env.VITE_SERVER_API_URL;

export const useTestStore = defineStore("test", () => {
  // etc ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
  const currentTestId = ref(0);

  // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡPㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

  const setCurrentTestId = (testId) => {
    currentTestId.value = testId;
    console.log("currentTestId.value 설정 완료 했습니다.");
    console.log(currentTestId.value);
  };

  //전체 테스트 조회
  const testList = ref([]);
  const testListhasNext = ref(true);
  const getTestError = ref(false);
  const getTestList = async function (params = { pageNumber: 0 }) {
    console.log("params : ", params);
    console.log("params : ", params.value);

    await axios({
      method: "get",
      url: `${VITE_SERVER_API_URL}/test/list`,
      params: params,
    })
      .then((res) => {
        testList.value = [
          ...testList.value,
          ...res.data.result.showTestListDtos,
        ];
        testListhasNext.value = res.data.result.hasNext;
      })
      .catch((err) => {
        getTestError.value = true;
        console.log(err);
      });
  };

  //:id 테스트 질문들 조회하기
  //quizList 를 선언해서 받을 수도 있지만 굳이 싶기는 하다 이렇게해서 장점이 뭘까 ? 그냥 코드를 한번 더
  //반복적으로 사용하는게 아닐까 ? 만약 ts를 도입후 interface의 기능을 활용하면 유지보수는 올릴수 있을 것 같다고
  //생각이 들지만 현재에 있어서는 좀 반복적인 코드를 사용하는 것 같다.
  const quizList = ref([]);
  const getQuizList = async function (testId) {
    console.log("url : ", `${VITE_SERVER_API_URL}/quiz/detail/${testId}`);
    console.log(testId);

    await axios({
      method: "get",
      url: `${VITE_SERVER_API_URL}/quiz/detail/${testId}`,
    })
      .then((res) => {
        console.log(res.data.result);

        quizList.value = res.data.result;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  //resultList
  const resultList = ref();
  const resultScore = ref(0);

  const resultFilePath = ref();
  const getResultList = async function (testId, score) {
    await axios({
      method: "get",
      url: `${VITE_SERVER_API_URL}/result/show`,
      params: {
        testId: testId,
        score: score,
      },
    })
      .then((res) => {
        console.log(res);
        resultList.value = res.data;
        // resultFilePath.value = res.data.file 여기에 파일 path넣기
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const setresultScore = (Score) => {
    resultScore.value = Score;
  };

  return {
    testList,
    testListhasNext,
    getTestList,
    getTestError,
    quizList,
    getQuizList,
    resultScore,
    resultList,
    setresultScore,
    currentTestId,
    setCurrentTestId,
    resultFilePath,
    getResultList,
  };
});
