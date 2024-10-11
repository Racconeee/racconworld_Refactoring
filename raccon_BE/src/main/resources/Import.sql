-- Insert into test table
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 2', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 3', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 4', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 5', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 6', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 7', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 8', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 9', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 10', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 11', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 12', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 13', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 14', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 15', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 16', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 17', 'SCORE', '/path/to/file1', 'file Name 1', 0);

-- Get the last inserted test ID
SET @test_id1 = LAST_INSERT_ID();

-- Insert into question table
INSERT INTO question (test_id, question_text) VALUES (@test_id1, 'Question 1'), (@test_id1, 'Question 2'), (@test_id1, 'Question 3'), (@test_id1, 'Question 4');

-- Get IDs of the inserted questions
SET @question_id1 = (SELECT question_id FROM question WHERE question_text = 'Question 1' AND test_id = @test_id1);
SET @question_id2 = (SELECT question_id FROM question WHERE question_text = 'Question 2' AND test_id = @test_id1);
SET @question_id3 = (SELECT question_id FROM question WHERE question_text = 'Question 3' AND test_id = @test_id1);
SET @question_id4 = (SELECT question_id FROM question WHERE question_text = 'Question 4' AND test_id = @test_id1);

-- Insert into test table for second test
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 2', 'PERSONALITY', '/path/to/file2', 'file Name 2', 0);

-- Get the last inserted test ID
SET @test_id2 = LAST_INSERT_ID();

-- Insert into question table for second test
INSERT INTO question (test_id, question_text) VALUES (@test_id2, 'Question 5'), (@test_id2, 'Question 6'), (@test_id2, 'Question 7'), (@test_id2, 'Question 8');

-- Get IDs of the inserted questions for second test
SET @question_id5 = (SELECT question_id FROM question WHERE question_text = 'Question 5' AND test_id = @test_id2);
SET @question_id6 = (SELECT question_id FROM question WHERE question_text = 'Question 6' AND test_id = @test_id2);
SET @question_id7 = (SELECT question_id FROM question WHERE question_text = 'Question 7' AND test_id = @test_id2);
SET @question_id8 = (SELECT question_id FROM question WHERE question_text = 'Question 8' AND test_id = @test_id2);

-- Insert into choice table for Personality with MBTI types and NULL for score
INSERT INTO choice (question_id, choice_text, score, dtype, personality) VALUES(@question_id5, 'Choice 1', NULL, 'Personality', 'ENFJ'),(@question_id5, 'Choice 2', NULL, 'Personality', 'INFP'),(@question_id6, 'Choice 1', NULL, 'Personality', 'INTJ'),(@question_id6, 'Choice 2', NULL, 'Personality', 'ESFJ'),(@question_id7, 'Choice 1', NULL, 'Personality', 'ISFP'),(@question_id7, 'Choice 2', NULL, 'Personality', 'ESTJ'),(@question_id8, 'Choice 1', NULL, 'Personality', 'ISTP'),(@question_id8, 'Choice 2', NULL, 'Personality', 'ENTP');

-- Insert into choice table for Score with random scores (0, 10, 20, ..., 100) and NULL for personality
INSERT INTO choice (question_id, choice_text, score, dtype, personality) VALUES(@question_id1, 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL),(@question_id1, 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL),(@question_id2, 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL),(@question_id2, 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL),(@question_id3, 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL),(@question_id3, 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL),(@question_id4, 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL),(@question_id4, 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL);

INSERT INTO result (test_id, file_path, file_name, score) VALUES(@test_id1, '/path/to/result/file1', 'result file Name 1', '85'),(@test_id2, '/path/to/result/file2', 'result file Name 2', '90');

INSERT INTO user (username, password, refresh_token) VALUES ('2', '2', 'refreshToken1');
INSERT INTO user (username, password, refresh_token) VALUES ('1', '1', 'refreshToken2');

-- Assuming user_id for 'user1' is 1 and for 'admin1' is 2
INSERT INTO user_role (user_id, role) VALUES (1, 'USER');
INSERT INTO user_role (user_id, role) VALUES (2, 'ADMIN');

--삭제하지 말길 .. .기억하길  --
-- !! 무조건 있어야하는거임 배포 할때 필수 !! --
INSERT INTO visit (visit_id,visit_count ) VALUES ("total_count", 0);

-- 0910이후 sql 문
---------------------------------------
-- Insert into test table
-- INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0), ('Sample Test 2', 'SCORE', '/path/to/file2', 'file Name 2', 0), ('Sample Test 3', 'SCORE', '/path/to/file3', 'file Name 3', 0), ('Sample Test 4', 'SCORE', '/path/to/file4', 'file Name 4', 0), ('Sample Test 5', 'SCORE', '/path/to/file5', 'file Name 5', 0), ('Sample Test 6', 'SCORE', '/path/to/file6', 'file Name 6', 0), ('Sample Test 7', 'SCORE', '/path/to/file7', 'file Name 7', 0), ('Sample Test 8', 'SCORE', '/path/to/file8', 'file Name 8', 0), ('Sample Test 9', 'SCORE', '/path/to/file9', 'file Name 9', 0), ('Sample Test 10', 'SCORE', '/path/to/file10', 'file Name 10', 0), ('Sample Test 11', 'SCORE', '/path/to/file11', 'file Name 11', 0), ('Sample Test 12', 'SCORE', '/path/to/file12', 'file Name 12', 0), ('Sample Test 13', 'SCORE', '/path/to/file13', 'file Name 13', 0), ('Sample Test 14', 'SCORE', '/path/to/file14', 'file Name 14', 0), ('Sample Test 15', 'SCORE', '/path/to/file15', 'file Name 15', 0), ('Sample Test 16', 'SCORE', '/path/to/file16', 'file Name 16', 0), ('Sample Test 17', 'SCORE', '/path/to/file17', 'file Name 17', 0), ('Sample Test 18', 'PERSONALITY', '/path/to/file18', 'file Name 18', 0);
--
-- -- Get the last inserted test ID
-- SET @test_id1 = LAST_INSERT_ID();
-- SET @test_id2 = (SELECT test_id FROM test WHERE test_name = 'Sample Test 18');
--
-- -- Insert into question table for SCORE type tests
-- INSERT INTO question (test_id, question_text) VALUES (@test_id1, 'Question 1'), (@test_id1, 'Question 2'), (@test_id1, 'Question 3'), (@test_id1, 'Question 4'), (@test_id1, 'Question 5'), (@test_id1, 'Question 6'), (@test_id1, 'Question 7'), (@test_id1, 'Question 8'), (@test_id1, 'Question 9'), (@test_id1, 'Question 10'), (@test_id1, 'Question 11'), (@test_id1, 'Question 12'), (@test_id1, 'Question 13'), (@test_id1, 'Question 14'), (@test_id1, 'Question 15'), (@test_id1, 'Question 16'), (@test_id1, 'Question 17'), (@test_id1, 'Question 18'), (@test_id1, 'Question 19'), (@test_id1, 'Question 20');
--
-- -- Insert into question table for PERSONALITY type test
-- INSERT INTO question (test_id, question_text) VALUES (@test_id2, 'Question 1'), (@test_id2, 'Question 2'), (@test_id2, 'Question 3'), (@test_id2, 'Question 4'), (@test_id2, 'Question 5'), (@test_id2, 'Question 6'), (@test_id2, 'Question 7'), (@test_id2, 'Question 8'), (@test_id2, 'Question 9'), (@test_id2, 'Question 10'), (@test_id2, 'Question 11'), (@test_id2, 'Question 12');
--
-- -- Insert into choice table for SCORE type tests with random scores
-- INSERT INTO choice (question_id, choice_text, score, dtype, personality) VALUES ((SELECT question_id FROM question WHERE question_text = 'Question 1' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 1' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 2' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 2' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 3' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 3' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 4' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 4' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 5' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 5' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 6' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 6' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 7' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 7' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 8' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 8' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 9' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 9' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 10' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 10' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 11' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 11' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 12' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 12' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 13' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 13' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 14' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 14' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 15' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 15' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 16' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 16' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 17' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 17' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 18' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 18' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 19' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 19' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 20' AND test_id = @test_id1), 'Choice 1', FLOOR(RAND() * 11) * 10, 'Score', NULL), ((SELECT question_id FROM question WHERE question_text = 'Question 20' AND test_id = @test_id1), 'Choice 2', FLOOR(RAND() * 11) * 10, 'Score', NULL);
--
-- -- Insert into choice table for PERSONALITY type test
-- INSERT INTO choice (question_id, choice_text, score, dtype, personality) VALUES ((SELECT question_id FROM question WHERE question_text = 'Question 1' AND test_id = @test_id2), 'Choice 1', NULL, 'Personality', 'Personality Type A'), ((SELECT question_id FROM question WHERE question_text = 'Question 1' AND test_id = @test_id2), 'Choice 2', NULL, 'Personality', 'Personality Type B'), ((SELECT question_id FROM question WHERE question_text = 'Question 2' AND test_id = @test_id2), 'Choice 1', NULL, 'Personality', 'Personality Type A'), ((SELECT question_id FROM question WHERE question_text = 'Question 2' AND test_id = @test_id2), 'Choice 2', NULL, 'Personality', 'Personality Type B'), ((SELECT question_id FROM question WHERE question_text = 'Question 3' AND test_id = @test_id2), 'Choice 1', NULL, 'Personality', 'Personality Type A'), ((SELECT question_id FROM question WHERE question_text = 'Question 3' AND test_id = @test_id2), 'Choice 2', NULL, 'Personality', 'Personality Type B'), ((SELECT question_id FROM question WHERE question_text = 'Question 4' AND test_id = @test_id2), 'Choice 1', NULL, 'Personality', 'Personality Type A'), ((SELECT question_id FROM question WHERE question_text = 'Question 4' AND test_id = @test_id2), 'Choice 2', NULL, 'Personality', 'Personality Type B'), ((SELECT question_id FROM question WHERE question_text = 'Question 5' AND test_id = @test_id2), 'Choice 1', NULL, 'Personality', 'Personality Type A'), ((SELECT question_id FROM question WHERE question_text = 'Question 5' AND test_id = @test_id2), 'Choice 2', NULL, 'Personality', 'Personality Type B'), ((SELECT question_id FROM question WHERE question_text = 'Question 6' AND test_id = @test_id2), 'Choice 1', NULL, 'Personality', 'Personality Type A'), ((SELECT question_id FROM question WHERE question_text = 'Question 6' AND test_id = @test_id2), 'Choice 2', NULL, 'Personality', 'Personality Type B'), ((SELECT question_id FROM question WHERE question_text = 'Question 7' AND test_id = @test_id2), 'Choice 1', NULL, 'Personality', 'Personality Type A'), ((SELECT question_id FROM question WHERE question_text = 'Question 7' AND test_id = @test_id2), 'Choice 2', NULL, 'Personality', 'Personality Type B'), ((SELECT question_id FROM question WHERE question_text = 'Question 8' AND test_id = @test_id2), 'Choice 1', NULL, 'Personality', 'Personality Type A'), ((SELECT question_id FROM question WHERE question_text = 'Question 8' AND test_id = @test_id2), 'Choice 2', NULL, 'Personality', 'Personality Type B'), ((SELECT question_id FROM question WHERE question_text = 'Question 9' AND test_id = @test_id2), 'Choice 1', NULL, 'Personality', 'Personality Type A'), ((SELECT question_id FROM question WHERE question_text = 'Question 9' AND test_id = @test_id2), 'Choice 2', NULL, 'Personality', 'Personality Type B'), ((SELECT question_id FROM question WHERE question_text = 'Question 10' AND test_id = @test_id2), 'Choice 1', NULL, 'Personality', 'Personality Type A'), ((SELECT question_id FROM question WHERE question_text = 'Question 10' AND test_id = @test_id2), 'Choice 2', NULL, 'Personality', 'Personality Type B'), ((SELECT question_id FROM question WHERE question_text = 'Question 11' AND test_id = @test_id2), 'Choice 1', NULL, 'Personality', 'Personality Type A'), ((SELECT question_id FROM question WHERE question_text = 'Question 11' AND test_id = @test_id2), 'Choice 2', NULL, 'Personality', 'Personality Type B'), ((SELECT question_id FROM question WHERE question_text = 'Question 12' AND test_id = @test_id2), 'Choice 1', NULL, 'Personality', 'Personality Type A'), ((SELECT question_id FROM question WHERE question_text = 'Question 12' AND test_id = @test_id2), 'Choice 2', NULL, 'Personality', 'Personality Type B');

INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('여자편 내 연애 점수는? 몇 점일까?', 'SCORE', '/path/to/quiz/file', 'quiz_file', 0);

-- Get the last inserted test ID
SET @test_id3 = LAST_INSERT_ID();

-- Insert into question table
INSERT INTO question (test_id, question_text) VALUES (@test_id3, '남자친구가 당신과의 계획을 자주 변경할 때, 당신의 반응은?'), (@test_id3, '남자친구가 당신이 스트레스를 받을 때, 어떻게 행동하는가?'), (@test_id3, '남자친구가 당신을 위해서 해준 일 중 가장 기억에 남는 것은?'), (@test_id3, '당신이 남자친구와 처음 만난 날, 어떤 행동을 기억하고 있는가?'), (@test_id3, '남자친구가 특별한 날, 당신에게 해준 서프라이즈는?'), (@test_id3, '남자친구가 당신의 가족과 만났을 때, 어떤 태도를 보였는가?'), (@test_id3, '당신이 기분이 우울할 때, 남자친구는 어떻게 해주는가?'), (@test_id3, '남자친구가 당신에게 미안한 일이 생겼을 때, 어떤 방법으로 사과하는가?'), (@test_id3, '남자친구가 약속을 지키지 않았을 때, 당신의 반응은?'), (@test_id3, '남자친구가 친구들 모임에 자주 나가지만, 당신과의 시간을 덜 보내는 경우, 당신의 반응은?');

-- Get IDs of the inserted questions
SET @question_id1 = (SELECT question_id FROM question WHERE question_text = '남자친구가 당신과의 계획을 자주 변경할 때, 당신의 반응은?' AND test_id = @test_id3);
SET @question_id2 = (SELECT question_id FROM question WHERE question_text = '남자친구가 당신이 스트레스를 받을 때, 어떻게 행동하는가?' AND test_id = @test_id3);
SET @question_id3 = (SELECT question_id FROM question WHERE question_text = '남자친구가 당신을 위해서 해준 일 중 가장 기억에 남는 것은?' AND test_id = @test_id3);
SET @question_id4 = (SELECT question_id FROM question WHERE question_text = '당신이 남자친구와 처음 만난 날, 어떤 행동을 기억하고 있는가?' AND test_id = @test_id3);
SET @question_id5 = (SELECT question_id FROM question WHERE question_text = '남자친구가 특별한 날, 당신에게 해준 서프라이즈는?' AND test_id = @test_id3);
SET @question_id6 = (SELECT question_id FROM question WHERE question_text = '남자친구가 당신의 가족과 만났을 때, 어떤 태도를 보였는가?' AND test_id = @test_id3);
SET @question_id7 = (SELECT question_id FROM question WHERE question_text = '당신이 기분이 우울할 때, 남자친구는 어떻게 해주는가?' AND test_id = @test_id3);
SET @question_id8 = (SELECT question_id FROM question WHERE question_text = '남자친구가 당신에게 미안한 일이 생겼을 때, 어떤 방법으로 사과하는가?' AND test_id = @test_id3);
SET @question_id9 = (SELECT question_id FROM question WHERE question_text = '남자친구가 약속을 지키지 않았을 때, 당신의 반응은?' AND test_id = @test_id3);
SET @question_id10 = (SELECT question_id FROM question WHERE question_text = '남자친구가 친구들 모임에 자주 나가지만, 당신과의 시간을 덜 보내는 경우, 당신의 반응은?' AND test_id = @test_id3);

-- Insert into choice table for the new quiz
-- Choices with their respective points
INSERT INTO choice (question_id, choice_text, score, dtype, personality) VALUES (@question_id1, '아무리 바빠도, 치킨 시켜놓고 혼자서라도 시간 맞춰줘야지!', 10, 'Score', NULL), (@question_id1, '계획이 바뀌었군! 그럼 오늘은 집에서 치킨이나 시켜 먹자.', 5, 'Score', NULL), (@question_id1, '계획 변경? 내 시간을 이렇게 무시하다니!', 0, 'Score', NULL), (@question_id2, '피시방 가서 스트레스 좀 풀고 오라고 돈을 보내준다.', 10, 'Score', NULL), (@question_id2, '스트레스는 왜 받는 건지 물어보며 진지하게 대화한다.', 5, 'Score', NULL), (@question_id2, '그냥 내 스트레스에 대해 입 닫고, 뽀뽀 한 번 해준다.', 0, 'Score', NULL), (@question_id3, '모든 걸 다 잊고, 나를 위해 깜짝 여행을 계획해준 거!', 10, 'Score', NULL), (@question_id3, '나의 무거운 짐을 들어주며, 그냥 함께 있어준 거.', 5, 'Score', NULL), (@question_id3, '혼자서 밥 먹을 때 보내준 "잘 지내니?"라는 메시지.', 0, 'Score', NULL), (@question_id4, '첫 만남부터 진지하게 계획을 세워서 멋진 데이트를 준비한 거!', 10, 'Score', NULL), (@question_id4, '아무 준비 없이 그냥 자연스럽게 만나서 편안했던 거.', 5, 'Score', NULL), (@question_id4, '처음 만났는데도 긴장해서 무슨 말인지 잘 기억이 안 나는 거.', 0, 'Score', NULL), (@question_id5, '그날을 위해서 준비한 특별한 이벤트와 선물!', 10, 'Score', NULL), (@question_id5, '기억해주고, 간단한 선물과 축하의 메시지.', 5, 'Score', NULL), (@question_id5, '그냥 날 잊어버리고 평범한 하루를 보낸 거.', 0, 'Score', NULL), (@question_id6, '정말 친절하게 대하고, 가족들에게도 잘 어울리는 모습!', 10, 'Score', NULL), (@question_id6, '조금 어색하긴 했지만, 최대한 노력하려고 한 모습.', 5, 'Score', NULL), (@question_id6, '가족과의 만남에서 많이 긴장한 모습.', 0, 'Score', NULL), (@question_id7, '정말 신경 써서, 좋아하는 음식을 사주고 대화를 나눈다.', 10, 'Score', NULL), (@question_id7, '조용히 옆에서 함께 있어주고, 위로의 말을 건넨다.', 5, 'Score', NULL), (@question_id7, '기분이 우울하다고 하니, 자신도 같이 우울해진다.', 0, 'Score', NULL), (@question_id8, '진심어린 사과와 함께, 멋진 선물까지 준비해준다.', 10, 'Score', NULL), (@question_id8, '미안하다고 말하며, 나중에 어떻게든 보답할 것을 약속한다.', 5, 'Score', NULL), (@question_id8, '미안하다고 말하면서, 그냥 상황이 지나가길 바란다.', 0, 'Score', NULL), (@question_id9, '그럼 오늘은 내가 한턱 쏘는 거야! 다음에는 꼭 지켜줘!', 10, 'Score', NULL), (@question_id9, '약속이 어긋나도 크게 신경 쓰지 않고, 다른 계획을 세운다.', 5, 'Score', NULL), (@question_id9, '계속 잔소리하며, 다음에도 또 이렇게 될까 걱정한다.', 0, 'Score', NULL), (@question_id10, '친구들과 잘 지내는 걸 응원하며, 혼자서도 잘 지낸다.', 10, 'Score', NULL), (@question_id10, '가끔은 나와도 되지 않냐고 부드럽게 말한다.', 5, 'Score', NULL), (@question_id10, '시간이 부족하다고 불만을 토로하며, 따지기도 한다.', 0, 'Score', NULL);

-- Insert into result table with score ranges and descriptions
INSERT INTO result (test_id, file_path, file_name, score) VALUES (@test_id3, '/path/to/result/file', 'result_file', 0), (@test_id3, '/path/to/result/file', 'result_file', 10), (@test_id3, '/path/to/result/file', 'result_file', 20), (@test_id3, '/path/to/result/file', 'result_file', 30), (@test_id3, '/path/to/result/file', 'result_file', 40), (@test_id3, '/path/to/result/file', 'result_file', 50), (@test_id3, '/path/to/result/file', 'result_file', 60), (@test_id3, '/path/to/result/file', 'result_file', 70), (@test_id3, '/path/to/result/file', 'result_file', 80), (@test_id3, '/path/to/result/file', 'result_file', 90), (@test_id3, '/path/to/result/file', 'result_file', 100);


---
---@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
--남자편 내 연애 점수는? 몇 점일까?
---
---

INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('남자편 내 연애 점수는? 몇 점일까?', 'SCORE', '/path/to/quiz/file', 'quiz_file', 0);

-- Get the last inserted test ID
SET @test_id4 = LAST_INSERT_ID();

-- Insert into question table
INSERT INTO question (test_id, question_text) VALUES  (@test_id4, '여자친구가 길을 걷다가 지나가는 여자를 보고 "저 여자 정말 이쁘다"라고 말했을 때, 당신의 반응은?'), (@test_id4, '여자친구가 다이어트를 하기로 결심했을 때, 당신의 가장 복잡한 대응은?'), (@test_id4, '여자친구가 다이어트를 하기로 결심했지만 치킨을 먹으러 갔을 때, 당신의 반응은?'), (@test_id4, '여자친구가 일상에서 스트레스를 받았을 때, 당신이 지원하는 방식은?'), (@test_id4, '여자친구가 중요한 결정을 내려야 할 때, 당신의 도움은?'), (@test_id4, '여자친구가 새로운 취미를 시작하고 싶어 할 때, 당신의 반응은?'), (@test_id4, '여자친구가 직장에서 어려움을 겪고 있을 때, 당신의 대응은?'), (@test_id4, '여자친구가 장기적인 계획을 세우고 싶어 할 때, 당신의 지원 방식은?'), (@test_id4, '여자친구가 최근에 자신을 의심하고 있을 때, 당신의 대응은?'), (@test_id4, '여자친구가 친구들과의 모임을 계획할 때, 당신의 반응은?');

-- Get IDs of the inserted questions
SET @question_id1 = (SELECT question_id FROM question WHERE question_text = '여자친구가 길을 걷다가 지나가는 여자를 보고 "저 여자 정말 이쁘다"라고 말했을 때, 당신의 반응은?' AND test_id = @test_id4);
SET @question_id2 = (SELECT question_id FROM question WHERE question_text = '여자친구가 다이어트를 하기로 결심했을 때, 당신의 가장 복잡한 대응은?' AND test_id = @test_id4);
SET @question_id3 = (SELECT question_id FROM question WHERE question_text = '여자친구가 다이어트를 하기로 결심했지만 치킨을 먹으러 갔을 때, 당신의 반응은?' AND test_id = @test_id4);
SET @question_id4 = (SELECT question_id FROM question WHERE question_text = '여자친구가 일상에서 스트레스를 받았을 때, 당신이 지원하는 방식은?' AND test_id = @test_id4);
SET @question_id5 = (SELECT question_id FROM question WHERE question_text = '여자친구가 중요한 결정을 내려야 할 때, 당신의 도움은?' AND test_id = @test_id4);
SET @question_id6 = (SELECT question_id FROM question WHERE question_text = '여자친구가 새로운 취미를 시작하고 싶어 할 때, 당신의 반응은?' AND test_id = @test_id4);
SET @question_id7 = (SELECT question_id FROM question WHERE question_text = '여자친구가 직장에서 어려움을 겪고 있을 때, 당신의 대응은?' AND test_id = @test_id4);
SET @question_id8 = (SELECT question_id FROM question WHERE question_text = '여자친구가 장기적인 계획을 세우고 싶어 할 때, 당신의 지원 방식은?' AND test_id = @test_id4);
SET @question_id9 = (SELECT question_id FROM question WHERE question_text = '여자친구가 최근에 자신을 의심하고 있을 때, 당신의 대응은?' AND test_id = @test_id4);
SET @question_id10 = (SELECT question_id FROM question WHERE question_text = '여자친구가 친구들과의 모임을 계획할 때, 당신의 반응은?' AND test_id = @test_id4);

-- Insert into choice table for the new quiz
-- Choices with their respective points
INSERT INTO choice (question_id, choice_text, score, dtype, personality) VALUES  (@question_id1, '엥 아니, 자기가 훨씬 더 이쁘다며 자신감을 높이려고 한다.', 5, 'Score', NULL), (@question_id1, '그냥 무시하고 계속 걸어간다.', 10, 'Score', NULL), (@question_id1, '아니야, 성형한 것 같아. 자기도 하면 훨씬 더 이뻐질 거야.', 0, 'Score', NULL), (@question_id2, '다이어트 실패 시 5만원 걸게라며 스스로에게 부담을 준다.', 0, 'Score', NULL), (@question_id2, '운동을 도와주겠다며 함께 운동하러 가자고 제안한다.', 5, 'Score', NULL), (@question_id2, '기프티콘을 꺼내며 ‘이거 치킨인데 오늘까지니까 같이 먹자. 안 해도 이뻐’라고 유혹한다.', 10, 'Score', NULL), (@question_id3, '미안해, 왜 저러는 걸까? 라며 그녀의 선택을 이해하려고 한다.', 5, 'Score', NULL), (@question_id3, '왜 나한테 짜증내냐고 반응하며 불편해 한다.', 0, 'Score', NULL), (@question_id3, '빠르게 닭다리를 입에 넣어주며 콜라를 따라준다.', 10, 'Score', NULL), (@question_id4, '그의 스트레스를 존중하며, 스스로 해결하도록 둔다.', 10, 'Score', NULL), (@question_id4, '스트레스를 줄이기 위한 간단한 방법들을 함께 시도해보자고 제안한다.', 5, 'Score', NULL), (@question_id4, '스트레스를 완화할 수 있는 특별한 계획을 세우고, 전반적인 지원을 아끼지 않는다.', 0, 'Score', NULL), (@question_id5, '그의 결정을 존중하고, 개입하지 않는다.', 10, 'Score', NULL), (@question_id5, '결정을 내리기 전에 장단점을 함께 분석하며 조언을 해준다.', 5, 'Score', NULL), (@question_id5, '결정을 내리기 위한 모든 옵션을 함께 논의하고, 추가적인 리서치도 함께 한다.', 0, 'Score', NULL), (@question_id6, '새로운 취미를 무시하고, 별로 관심을 보이지 않는다.', 10, 'Score', NULL), (@question_id6, '새로운 취미에 대해 조언을 하고, 관심을 가지려 한다.', 5, 'Score', NULL), (@question_id6, '새로운 취미를 함께 시도하고, 필요한 장비를 사주며 적극적으로 참여한다.', 0, 'Score', NULL), (@question_id7, '그의 어려움을 들어주고, 감정적인 지원만 제공한다.', 10, 'Score', NULL), (@question_id7, '문제 해결을 위한 실질적인 조언을 제공한다.', 5, 'Score', NULL), (@question_id7, '문제를 해결하기 위한 구체적인 계획을 함께 세우고, 필요시 전문가와 상담을 주선한다.', 0, 'Score', NULL), (@question_id8, '기본적인 격려만 제공하고, 개입하지 않는다.', 10, 'Score', NULL), (@question_id8, '계획을 세우는 데 도움을 주며, 필요한 조언을 제공한다.', 5, 'Score', NULL), (@question_id8, '계획의 모든 단계에서 적극적으로 참여하고, 필요한 자원을 지원한다.', 0, 'Score', NULL), (@question_id9, '그의 감정을 존중하고, 스스로 해결하게 둔다.', 10, 'Score', NULL), (@question_id9, '자신감을 북돋우기 위해 격려와 조언을 제공한다.', 5, 'Score', NULL), (@question_id9, '자신감을 회복할 수 있는 구체적인 방법을 제시하고, 직접적인 지원을 아끼지 않는다.', 0, 'Score', NULL), (@question_id10, '그의 계획을 존중하고, 혼자서 시간을 보낸다.', 10, 'Score', NULL), (@question_id10, '친구들과의 시간도 중요하다고 말하며, 함께 즐길 수 있는 활동을 제안한다.', 5, 'Score', NULL), (@question_id10, '그 친구들이 모두 모여서 계획을 세우는 걸 도와주며, 모든 준비를 챙긴다.', 0, 'Score', NULL);


---
---@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
-- 내성격으로 동물이 된다면
---
---

INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('내 성격으로 동물이 된다면?', 'PERSONALITY', '/path/to/personality_test', 'personality_test_file', 0);

-- Get the last inserted test ID
SET @test_id = LAST_INSERT_ID();

-- Insert into question table
INSERT INTO question (test_id, question_text) VALUES (@test_id, '친구가 갑자기 약속을 취소한다면?'), (@test_id, '혼자 있을 때, 어떻게 시간을 보내나요?'), (@test_id, '파티에서 대화가 끊기면 어떻게 하나요?'), (@test_id, '생일 파티를 준비할 때, 어떻게 하나요?'), (@test_id, '새로운 취미를 시작할 때, 어떻게 접근하나요?'), (@test_id, '일상에서 문제가 생겼을 때, 어떻게 해결하나요?'), (@test_id, '친구가 어려운 상황에 처했을 때, 어떻게 반응하나요?'), (@test_id, '중요한 결정을 내려야 할 때, 어떻게 접근하나요?'), (@test_id, '팀 프로젝트에서 의견 충돌이 있을 때, 어떻게 대처하나요?'), (@test_id, '주말 계획을 세울 때, 어떻게 하나요?'), (@test_id, '마감일이 다가올 때, 어떻게 준비하나요?'), (@test_id, '새로운 프로젝트를 시작할 때, 어떻게 접근하나요?');

-- Get IDs of the inserted questions
SET @question_id1 = LAST_INSERT_ID() - 11;  -- 첫 번째 질문 ID
SET @question_id2 = LAST_INSERT_ID() - 10;  -- 두 번째 질문 ID
SET @question_id3 = LAST_INSERT_ID() - 9;   -- 세 번째 질문 ID
SET @question_id4 = LAST_INSERT_ID() - 8;   -- 네 번째 질문 ID
SET @question_id5 = LAST_INSERT_ID() - 7;   -- 다섯 번째 질문 ID
SET @question_id6 = LAST_INSERT_ID() - 6;   -- 여섯 번째 질문 ID
SET @question_id7 = LAST_INSERT_ID() - 5;   -- 일곱 번째 질문 ID
SET @question_id8 = LAST_INSERT_ID() - 4;   -- 여덟 번째 질문 ID
SET @question_id9 = LAST_INSERT_ID() - 3;   -- 아홉 번째 질문 ID
SET @question_id10 = LAST_INSERT_ID() - 2;  -- 열 번째 질문 ID
SET @question_id11 = LAST_INSERT_ID() - 1;  -- 열한 번째 질문 ID
SET @question_id12 = LAST_INSERT_ID();       -- 열두 번째 질문 ID

-- Insert into choice table for personality types
INSERT INTO choice (question_id, choice_text, score, dtype, personality) VALUES (@question_id1, '아, 아쉽네... 다음에 밥 사자.', NULL, 'Personality', 'Introversion'), (@question_id1, '내일 뭐하고 놀지 기대했는데, 다른 친구라도 만나야겠다.', NULL, 'Personality', 'Extraversion'), (@question_id2, '책을 읽거나 영화 보며 조용히 시간을 보낸다.', NULL, 'Personality', 'Introversion'), (@question_id2, '새로운 카페를 탐방하거나 친구와 모임을 가진다.', NULL, 'Personality', 'Extraversion'), (@question_id3, '조용히 한쪽 구석에서 나만의 시간을 가진다.', NULL, 'Personality', 'Introversion'), (@question_id3, '새로운 사람에게 다가가 대화를 시작한다.', NULL, 'Personality', 'Extraversion'), (@question_id4, '세세한 계획을 세우고 준비물 리스트를 체크한다.', NULL, 'Personality', 'Sensing'), (@question_id4, '창의적이고 특별한 아이디어를 떠올리며 준비한다.', NULL, 'Personality', 'Intuition'), (@question_id5, '기본부터 배우며 세심하게 기술을 익힌다.', NULL, 'Personality', 'Sensing'), (@question_id5, '다양한 방법을 시도해보며 자유롭게 배운다.', NULL, 'Personality', 'Intuition'), (@question_id6, '구체적이고 실용적인 방법으로 접근한다.', NULL, 'Personality', 'Sensing'), (@question_id6, '상황을 종합적으로 분석하고 창의적인 해결책을 찾는다.', NULL, 'Personality', 'Intuition'), (@question_id7, '문제를 논리적으로 분석해 해결책을 제시한다.', NULL, 'Personality', 'Thinking'), (@question_id7, '친구의 감정을 이해하고 공감하며 위로한다.', NULL, 'Personality', 'Feeling'), (@question_id8, '객관적인 데이터를 분석해 결정을 내린다.', NULL, 'Personality', 'Thinking'), (@question_id8, '사람들의 감정과 의견을 고려해 결정한다.', NULL, 'Personality', 'Feeling'), (@question_id9, '논리적인 근거를 들어 의견을 제시하고 조율한다.', NULL, 'Personality', 'Thinking'), (@question_id9, '각자의 감정을 이해하고 조화롭게 의견을 조율한다.', NULL, 'Personality', 'Feeling'), (@question_id10, '미리 계획을 세우고 일정을 정리한다.', NULL, 'Personality', 'Judging'), (@question_id10, '그때그때 상황에 맞게 즉흥적으로 계획을 세운다.', NULL, 'Personality', 'Perceiving'), (@question_id11, '미리 계획하고 준비를 철저히 한다.', NULL, 'Personality', 'Judging'), (@question_id11, '마지막 순간까지 유연하게 작업하며 대처한다.', NULL, 'Personality', 'Perceiving'), (@question_id12, '체계적인 계획을 세우고 순서대로 진행한다.', NULL, 'Personality', 'Judging'), (@question_id12, '상황에 따라 유연하게 접근하며 필요한 조정을 한다.', NULL, 'Personality', 'Perceiving');

-- Insert into result table for animal personality types
INSERT INTO result (test_id, file_path, file_name, score) VALUES (@test_id, '/path/to/result/lion', '사자', 'ExtraversionIntuitionThinkingJudging'), (@test_id, '/path/to/result/bear', '곰', 'IntroversionSensingThinkingJudging'), (@test_id, '/path/to/result/fox', '여우', 'ExtraversionIntuitionFeelingPerceiving'), (@test_id, '/path/to/result/owl', '올빼미', 'IntroversionIntuitionThinkingJudging'), (@test_id, '/path/to/result/monkey', '원숭이', 'ExtraversionSensingThinkingPerceiving'), (@test_id, '/path/to/result/tiger', '호랑이', 'ExtraversionIntuitionThinkingPerceiving'), (@test_id, '/path/to/result/panda', '팬더', 'IntroversionIntuitionFeelingJudging'), (@test_id, '/path/to/result/dolphin', '돌고래', 'ExtraversionFeelingJudging'), (@test_id, '/path/to/result/wolf', '늑대', 'ExtraversionSensingThinkingPerceiving'), (@test_id, '/path/to/result/deer', '사슴', 'IntroversionFeelingJudging'), (@test_id, '/path/to/result/cheetah', '치타', 'ExtraversionThinkingPerceiving'), (@test_id, '/path/to/result/skunk', '스컹크', 'IntroversionFeelingPerceiving'), (@test_id, '/path/to/result/turtle', '거북이', 'IntroversionSensingFeelingJudging'), (@test_id, '/path/to/result/rabbit', '토끼', 'IntroversionFeelingPerceiving'), (@test_id, '/path/to/result/hippo', '하마', 'IntroversionSensingFeelingJudging'), (@test_id, '/path/to/result/elephant', '코끼리', 'IntroversionSensingFeelingJudging');


---
---@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
-- 내성격으로 동물이 된다면
---
---


INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('내 성격으로 물고기가 된다면?', 'PERSONALITY', '/path/to/fish_personality_test', 'fish_personality_test_file', 0);

-- Get the last inserted test ID
SET @test_id = LAST_INSERT_ID();

-- Insert into question table
INSERT INTO question (test_id, question_text)  VALUES  (@test_id, '친구가 갑자기 집에 놀러 오겠다고 하면 너는 어떻게 해?'), (@test_id, '혼자서 쉬는 날, 가장 좋아하는 활동은?'), (@test_id, '갑자기 친구들이 모두 와서 파티를 열자고 하면?'), (@test_id, '새로운 레시피를 시도할 때, 너는 어떻게 해?'), (@test_id, '일정을 계획할 때, 네가 선호하는 방식은?'), (@test_id, '새로운 영화나 책을 추천 받을 때, 너는 어떻게 반응해?'), (@test_id, '생일 선물로 실용적인 것을 받을 때, 너의 반응은?'), (@test_id, '친구가 힘든 상황에 처했을 때, 너는 어떻게 도와줘?'), (@test_id, '친구가 갑자기 큰 결정을 내렸을 때, 네가 해줄 조언은?'), (@test_id, '계획이 갑자기 변경되었을 때, 너는 어떻게 대처해?'), (@test_id, '갑자기 생긴 마감일이 있을 때, 네가 하는 행동은?'), (@test_id, '친구와 여행을 계획할 때, 네가 선호하는 방식은?');

-- Get IDs of the inserted questions
SET @question_id1 = LAST_INSERT_ID() - 11;  -- 첫 번째 질문 ID
SET @question_id2 = LAST_INSERT_ID() - 10;  -- 두 번째 질문 ID
SET @question_id3 = LAST_INSERT_ID() - 9;   -- 세 번째 질문 ID
SET @question_id4 = LAST_INSERT_ID() - 8;   -- 네 번째 질문 ID
SET @question_id5 = LAST_INSERT_ID() - 7;   -- 다섯 번째 질문 ID
SET @question_id6 = LAST_INSERT_ID() - 6;   -- 여섯 번째 질문 ID
SET @question_id7 = LAST_INSERT_ID() - 5;   -- 일곱 번째 질문 ID
SET @question_id8 = LAST_INSERT_ID() - 4;   -- 여덟 번째 질문 ID
SET @question_id9 = LAST_INSERT_ID() - 3;   -- 아홉 번째 질문 ID
SET @question_id10 = LAST_INSERT_ID() - 2;  -- 열 번째 질문 ID
SET @question_id11 = LAST_INSERT_ID() - 1;  -- 열한 번째 질문 ID
SET @question_id12 = LAST_INSERT_ID();       -- 열두 번째 질문 ID

-- Insert into choice table for personality types
INSERT INTO choice (question_id, choice_text, score, dtype, personality) VALUES (@question_id1, '“음악 틀고, 소파에 누워서 조용히 기다린다.”', NULL, 'Personality', 'Introversion'),(@question_id1, '“친구 맞이할 준비를 하면서 함께 게임도 하자고 한다!”', NULL, 'Personality', 'Extraversion'),(@question_id2, '“침대에 누워서 좋아하는 책을 읽거나 영화를 본다.”', NULL, 'Personality', 'Introversion'),(@question_id2, '“카페에 가서 사람들이랑 소통하면서 커피 한 잔!”', NULL, 'Personality', 'Extraversion'),(@question_id3, '“음악과 간단한 간식 준비하면서 조용히 대기한다.”', NULL, 'Personality', 'Introversion'),(@question_id3, '“파티 분위기를 띄우기 위해 열심히 음악과 음식을 준비한다!”', NULL, 'Personality', 'Extraversion'),(@question_id4, '“레시피를 정확히 따르면서 재료를 신중하게 준비한다.”', NULL, 'Personality', 'Sensing'),(@question_id4, '“레시피를 약간 수정해서 나만의 스타일로 만들어본다.”', NULL, 'Personality', 'Intuition'),(@question_id5, '“모든 세부 사항을 미리 계획하고 체크리스트를 작성한다.”', NULL, 'Personality', 'Sensing'),(@question_id5, '“전체적인 계획만 세우고 그때그때 상황에 맞게 조정한다.”', NULL, 'Personality', 'Intuition'),(@question_id6, '“추천된 내용이나 장르를 꼼꼼히 조사해본다.”', NULL, 'Personality', 'Sensing'),(@question_id6, '“추천받은 작품의 독창성이나 리뷰를 참고해서 결정한다.”', NULL, 'Personality', 'Intuition'),(@question_id7, '“우와, 정말 유용할 것 같아. 잘 사용하겠어!”', NULL, 'Personality', 'Thinking'),(@question_id7, '“와, 정말 생각해줘서 고마워! 마음이 너무 따뜻해.”', NULL, 'Personality', 'Feeling'),(@question_id8, '“문제를 분석하고 해결 방법을 제시해준다.”', NULL, 'Personality', 'Thinking'),(@question_id8, '“친절하게 위로하고 감정을 이해해주며 지원한다.”', NULL, 'Personality', 'Feeling'),(@question_id9, '“논리적으로 장단점을 분석해서 조언해준다.”', NULL, 'Personality', 'Thinking'),(@question_id9, '“그 결정이 친구에게 맞는지 감정적으로 공감하며 조언해준다.”', NULL, 'Personality', 'Feeling'),(@question_id10, '“미리 계획한 대로 진행할 수 있도록 최대한 노력한다.”', NULL, 'Personality', 'Judging'),(@question_id10, '“계획이 바뀌면 즉흥적으로 새로운 계획을 세운다.”', NULL, 'Personality', 'Perceiving'),(@question_id11, '“계획적으로 미리 준비하고 마지막까지 꼼꼼히 확인한다.”', NULL, 'Personality', 'Judging'),(@question_id11, '“마감일에 맞춰 마지막 순간까지 일을 완성하기 위해 열심히 한다.”', NULL, 'Personality', 'Perceiving'),(@question_id12, '“모든 일정을 미리 세우고 세세하게 계획한다.”', NULL, 'Personality', 'Judging'),(@question_id12, '“여행 중에 그때그때 상황에 맞게 결정하면서 즐긴다.”', NULL, 'Personality', 'Perceiving');

-- Insert into result table for fish personality types
INSERT INTO result (test_id, file_path, file_name, score) VALUES (@test_id, '/path/to/result/shark', '상어', 'ExtraversionIntuitionThinkingJudging'),(@test_id, '/path/to/result/clownfish', '클라운피쉬', 'ExtraversionFeelingSensingJudging'),(@test_id, '/path/to/result/piranha', '피라냐', 'ExtraversionThinkingJudging'),(@test_id, '/path/to/result/coral', '산호초', 'IntroversionFeelingSensingJudging'),(@test_id, '/path/to/result/whale', '고래', 'ExtraversionFeelingJudging'),(@test_id, '/path/to/result/fugu', '복어', 'IntroversionFeelingPerceiving'),(@test_id, '/path/to/result/octopus', '문어', 'IntroversionThinkingPerceiving'),(@test_id, '/path/to/result/goldfish', '금붕어', 'IntroversionFeelingPerceiving'),(@test_id, '/path/to/result/bluefish', '전갱이', 'ExtraversionIntuitionPerceiving'),(@test_id, '/path/to/result/sea_horse', '해마', 'IntroversionFeelingPerceiving'),(@test_id, '/path/to/result/parrotfish', '파랑돔', 'ExtraversionSensingPerceiving'),(@test_id, '/path/to/result/clam', '조개', 'IntroversionFeelingSensingJudging'),(@test_id, '/path/to/result/seal', '물범', 'ExtraversionFeelingPerceiving'),(@test_id, '/path/to/result/jellyfish', '해파리', 'IntroversionIntuitionJudging'),(@test_id, '/path/to/result/turtle', '거북이', 'IntroversionThinkingJudging'),(@test_id, '/path/to/result/dolphin', '돌고래', 'ExtraversionIntuitionFeelingPerceiving');















