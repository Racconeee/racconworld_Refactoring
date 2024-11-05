const quizScoreCalculate = (choiceScoreList, testType) => {
  if (testType === "SCORE") {
    const scoreTypeResult = choiceScoreList.value.reduce(
      (accumulator, currentValue) => accumulator + currentValue,
      0 // 초기값 설정
    );
    return { scoreTypeResult: Math.round(scoreTypeResult) };
  } else {
    let personalityTypeResult = "";

    const extraversionCount = choiceScoreList.value.filter(
      (choice) => choice === "Extraversion"
    ).length;
    const introversionCount = choiceScoreList.value.filter(
      (choice) => choice === "Introversion"
    ).length;
    const sensingCount = choiceScoreList.value.filter(
      (choice) => choice === "Sensing"
    ).length;
    const intuitionCount = choiceScoreList.value.filter(
      (choice) => choice === "Intuition"
    ).length;
    const thinkingCount = choiceScoreList.value.filter(
      (choice) => choice === "Thinking"
    ).length;
    const feelingCount = choiceScoreList.value.filter(
      (choice) => choice === "Feeling"
    ).length;
    const judgingCount = choiceScoreList.value.filter(
      (choice) => choice === "Judging"
    ).length;
    const perceivingCount = choiceScoreList.value.filter(
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

    return { personalityTypeResult };
  }
};

const QuizCommon = {
  quizScoreCalculate,
};

export default QuizCommon;
