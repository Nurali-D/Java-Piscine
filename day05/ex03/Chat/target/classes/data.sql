INSERT INTO chat.users (login, password)
VALUES ('Nick', 'abcdef');
INSERT INTO chat.users (login, password)
VALUES ('John', 'ghijkl');
INSERT INTO chat.users (login, password)
VALUES ('Bob', 'some_passw');
INSERT INTO chat.users (login, password)
VALUES ('Tom', 'toms_passw');
INSERT INTO chat.users (login, password)
VALUES ('Henry', 'henrys_pasw');

INSERT INTO chat.chatrooms (name, owner)
VALUES ('Java', 1);
INSERT INTO chat.chatrooms (name, owner)
VALUES ('C++', 2);
INSERT INTO chat.chatrooms (name, owner)
VALUES ('C', 3);
INSERT INTO chat.chatrooms (name, owner)
VALUES ('Python', 4);
INSERT INTO chat.chatrooms (name, owner)
VALUES ('C#', 5);

INSERT INTO chat.messages (author, chatroom, text, timestamp)
VALUES (1, 1, 'java is good', '1970-01-01 00:00:01');
INSERT INTO chat.messages (author, chatroom, text, timestamp)
VALUES (2, 3, 'c++ is good', '1970-01-01 00:00:01');
INSERT INTO chat.messages (author, chatroom, text, timestamp)
VALUES (5, 3, 'c is good', '1970-01-01 00:00:02');
INSERT INTO chat.messages (author, chatroom, text, timestamp)
VALUES (4, 4, 'python is good', '1970-01-01 00:00:04');
INSERT INTO chat.messages (author, chatroom, text, timestamp)
VALUES (5, 5, 'c# is good', '1970-01-01 00:00:05');