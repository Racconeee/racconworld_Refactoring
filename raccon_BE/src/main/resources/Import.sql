-- Insert into test table
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);
INSERT INTO test (test_name, test_type, file_path, file_name, view) VALUES ('Sample Test 1', 'SCORE', '/path/to/file1', 'file Name 1', 0);

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


