let scoreTypeResult = 0;
let personaltiyTypeResult = "";

let extraversionCount = 0;
let introversionCount = 0;
let sensingCount = 0;
let intuitionCount = 0;
let thinkingCount = 0;
let feelingCount = 0;
let judgingCount = 0;
let perceivingCount = 0;

const quizScoreCalculate = (choiceScoreList, testType) => {
  console.log(choiceScoreList.value);
  console.log(testType);

  if (testType == "SCORE") {
    console.log("SCORE TYPE 으로 결과 계산을 시작합니다.");

    scoreTypeResult = choiceScoreList.value.reduce(
      (accumulator, currentValue) => {
        return accumulator + currentValue;
      }
    );
    return scoreTypeResult;
  } else {
    console.log("Personaltiy 로 결과 계산을 시작합니다.");
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

    if (extraversionCount > introversionCount) {
      personaltiyTypeResult = +"Extraversion";
    } else {
      personaltiyTypeResult = +"Introversion";
    }

    if (sensingCount > intuitionCount) {
      personaltiyTypeResult = +"Sensing";
    } else {
      personaltiyTypeResult = +"Intuition";
    }

    if (thinkingCount > feelingCount) {
      personaltiyTypeResult = +"Thinking";
    } else {
      personaltiyTypeResult = +"Feeling";
    }

    if (judgingCount > perceivingCount) {
      personaltiyTypeResult = +"Extraversion";
    } else {
      personaltiyTypeResult = +"Introversion";
    }
    return personaltiyTypeResult;
  }
};

const QuizCommon = {
  quizScoreCalculate,
  scoreTypeResult,
  personaltiyTypeResult,
};

export default QuizCommon;
