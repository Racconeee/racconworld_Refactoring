-- Test 테이블 데이터 삽입
INSERT INTO Test (testName, testType, quizLength, choiceLength)
VALUES ('Sample Test', 'score', 5, 4);

-- Test 데이터의 ID를 가져옵니다.
SET @test_id = LAST_INSERT_ID();

-- Question 데이터 삽입
INSERT INTO Question (test_id, text)
VALUES
    (@test_id, 'Question 1'),
    (@test_id, 'Question 2'),
    (@test_id, 'Question 3'),
    (@test_id, 'Question 4');

-- 특정 Question의 ID를 얻기 위해 변수 사용 (MySQL의 경우)
SET @question_id1 = (SELECT question_id FROM Question WHERE text = 'Question 1' AND test_id = @test_id);
SET @question_id2 = (SELECT question_id FROM Question WHERE text = 'Question 2' AND test_id = @test_id);
SET @question_id3 = (SELECT question_id FROM Question WHERE text = 'Question 3' AND test_id = @test_id);
SET @question_id4 = (SELECT question_id FROM Question WHERE text = 'Question 4' AND test_id = @test_id);

-- Choice 데이터 삽입
INSERT INTO Choice (question_id, text, score)
VALUES
    (@question_id1, 'Choice 1', 10),
    (@question_id1, 'Choice 2', 5),
    (@question_id2, 'Choice 1', 8),
    (@question_id2, 'Choice 2', 4),
    (@question_id3, 'Choice 1', 7),
    (@question_id3, 'Choice 2', 3),
    (@question_id4, 'Choice 1', 9),
    (@question_id4, 'Choice 2', 6);

-- Test 테이블 데이터 삽입
INSERT INTO Test (testName, testType, quizLength, choiceLength)
VALUES ('Sample Test', 'score', 5, 4);

-- Test 데이터의 ID를 가져옵니다.
SET @test_id = LAST_INSERT_ID();

-- Question 데이터 삽입
INSERT INTO Question (test_id, text)
VALUES
    (@test_id, 'Question 1'),
    (@test_id, 'Question 2'),
    (@test_id, 'Question 3'),
    (@test_id, 'Question 4');

-- 특정 Question의 ID를 얻기 위해 변수 사용 (MySQL의 경우)
SET @question_id1 = (SELECT question_id FROM Question WHERE text = 'Question 1' AND test_id = @test_id);
SET @question_id2 = (SELECT question_id FROM Question WHERE text = 'Question 2' AND test_id = @test_id);
SET @question_id3 = (SELECT question_id FROM Question WHERE text = 'Question 3' AND test_id = @test_id);
SET @question_id4 = (SELECT question_id FROM Question WHERE text = 'Question 4' AND test_id = @test_id);

-- Choice 데이터 삽입
INSERT INTO Choice (question_id, text, score)
VALUES
    (@question_id1, 'Choice 1', 10),
    (@question_id1, 'Choice 2', 5),
    (@question_id2, 'Choice 1', 8),
    (@question_id2, 'Choice 2', 4),
    (@question_id3, 'Choice 1', 7),
    (@question_id3, 'Choice 2', 3),
    (@question_id4, 'Choice 1', 9),
    (@question_id4, 'Choice 2', 6);
