// src/api/test.js
import { apiAxios } from "../util/http-commons";

//여기서 호출해서 사용하는거 아님 이거는 뭔가 직접적인 api 를 사용한다고 생각하자 .
const local = apiAxios();

async function findByAllTestList() {
  try {
    // API 호출
    const response = await local.get("/api/test/list");
    return response.data;
  } catch (error) {
    // 에러 처리
    console.error("Error fetching test list:", error);
    throw error; // 에러를 다시 던져서 호출하는 곳에서 처리하게 함
  }
}

export { findByAllTestList };
