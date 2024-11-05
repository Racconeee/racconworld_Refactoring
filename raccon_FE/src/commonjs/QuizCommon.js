let scoreTypeResult = 0;
let personalityTypeResult = ""; // 오타 수정

let extraversionCount = 0;
let introversionCount = 0;
let sensingCount = 0;
let intuitionCount = 0;
let thinkingCount = 0;
let feelingCount = 0;
let judgingCount = 0;
let perceivingCount = 0;

const quizScoreCalculate = (choiceScoreList, testType) => {
  if (testType === "SCORE") {
    scoreTypeResult = choiceScoreList.value.reduce(
      (accumulator, currentValue) => accumulator + currentValue,
      0 // 초기값 설정
    );
    return Math.round(scoreTypeResult);
  } else {
    extraversionCount = choiceScoreList.value.filter(
      (choice) => choice === "Extraversion"
    ).length;
    introversionCount = choiceScoreList.value.filter(
      (choice) => choice === "Introversion"
    ).length;
    sensingCount = choiceScoreList.value.filter(
      (choice) => choice === "Sensing"
    ).length;
    intuitionCount = choiceScoreList.value.filter(
      (choice) => choice === "Intuition"
    ).length;
    thinkingCount = choiceScoreList.value.filter(
      (choice) => choice === "Thinking"
    ).length;
    feelingCount = choiceScoreList.value.filter(
      (choice) => choice === "Feeling"
    ).length;
    judgingCount = choiceScoreList.value.filter(
      (choice) => choice === "Judging"
    ).length;
    perceivingCount = choiceScoreList.value.filter(
      (choice) => choice === "Perceiving"
    ).length;

    personalityTypeResult +=
      extraversionCount > introversionCount ? "Extraversion" : "Introversion";
    personalityTypeResult +=
      sensingCount > intuitionCount ? "Sensing" : "Intuition";
    personalityTypeResult +=
      thinkingCount > feelingCount ? "Thinking" : "Feeling";
    personalityTypeResult +=
      judgingCount > perceivingCount ? "Judging" : "Perceiving";

    return personalityTypeResult;
  }
};

const QuizCommon = {
  quizScoreCalculate,
  scoreTypeResult,
  personalityTypeResult,
};

export default QuizCommon;
