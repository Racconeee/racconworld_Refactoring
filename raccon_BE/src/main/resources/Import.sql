-- Insert into test table
INSERT INTO test (test_name, test_type, file_path, file_download)VALUES ('Sample Test', 'SCORE', '/path/to/file', 'download link');

-- Get the last inserted test ID
SET @test_id = LAST_INSERT_ID();

-- Insert into question table
INSERT INTO question (test_id, question_text) VALUES(@test_id, 'Question 1'),(@test_id, 'Question 2'),(@test_id, 'Question 3'),(@test_id, 'Question 4');

-- Get IDs of the inserted questions
SET @question_id1 = (SELECT question_id FROM question WHERE question_text = 'Question 1' AND test_id = @test_id);
SET @question_id2 = (SELECT question_id FROM question WHERE question_text = 'Question 2' AND test_id = @test_id);
SET @question_id3 = (SELECT question_id FROM question WHERE question_text = 'Question 3' AND test_id = @test_id);
SET @question_id4 = (SELECT question_id FROM question WHERE question_text = 'Question 4' AND test_id = @test_id);

-- Insert into choice table with dtype
INSERT INTO choice (question_id, choice_text, dtype) VALUES (@question_id1, 'Choice 1', 'Score'), (@question_id1, 'Choice 2', 'Score'), (@question_id2, 'Choice 1', 'Score'), (@question_id2, 'Choice 2', 'Score'), (@question_id3, 'Choice 1', 'Score'), (@question_id3, 'Choice 2', 'Score'), (@question_id4, 'Choice 1', 'Score'), (@question_id4, 'Choice 2', 'Score'), (@question_id1, 'Choice 1', 'Personality'), (@question_id1, 'Choice 2', 'Personality'), (@question_id2, 'Choice 1', 'Personality'), (@question_id2, 'Choice 2', 'Personality'), (@question_id3, 'Choice 1', 'Personality'), (@question_id3, 'Choice 2', 'Personality'), (@question_id4, 'Choice 1', 'Personality'), (@question_id4, 'Choice 2', 'Personality');

-- Insert into result table
INSERT INTO result (test_id, file_path, file_download, score) VALUES (@test_id, '/path/to/result/file', 'result download link', '85');
