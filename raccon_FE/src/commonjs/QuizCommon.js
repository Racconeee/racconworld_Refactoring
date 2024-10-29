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
    return Math.round(scoreTypeResult);
  } else {
    console.log("Personaltiy 로 결과 계산을 시작합니다.");
    extraversionCount = choiceScoreList.value.filter(
      (choice) => choice === "Ex"
    ).length;
    introversionCount = choiceScoreList.value.filter(
      (choice) => choice === "In"
    ).length;
    sensingCount = choiceScoreList.value.filter(
      (choice) => choice === "Se"
    ).length;
    intuitionCount = choiceScoreList.value.filter(
      (choice) => choice === "In"
    ).length;
    thinkingCount = choiceScoreList.value.filter(
      (choice) => choice === "Th"
    ).length;
    feelingCount = choiceScoreList.value.filter(
      (choice) => choice === "Fe"
    ).length;
    judgingCount = choiceScoreList.value.filter(
      (choice) => choice === "Ju"
    ).length;
    perceivingCount = choiceScoreList.value.filter(
      (choice) => choice === "Pe"
    ).length;

    if (extraversionCount > introversionCount) {
      personaltiyTypeResult = +"Ex";
    } else {
      personaltiyTypeResult = +"In";
    }

    if (sensingCount > intuitionCount) {
      personaltiyTypeResult = +"Se";
    } else {
      personaltiyTypeResult = +"In";
    }

    if (thinkingCount > feelingCount) {
      personaltiyTypeResult = +"Th";
    } else {
      personaltiyTypeResult = +"Fe";
    }

    if (judgingCount > perceivingCount) {
      personaltiyTypeResult = +"Ex";
    } else {
      personaltiyTypeResult = +"In";
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
