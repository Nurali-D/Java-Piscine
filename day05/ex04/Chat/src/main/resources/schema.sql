DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;

CREATE TABLE IF NOT EXISTS chat.users (
    id SERIAL PRIMARY KEY,
    login text UNIQUE NOT NULL,
    password text
);

CREATE TABLE IF NOT EXISTS chat.chatrooms (
    id SERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    owner INTEGER REFERENCES chat.users(id) NOT NULL
    );

CREATE TABLE IF NOT EXISTS chat.messages (
    id SERIAL PRIMARY KEY,
    author INTEGER REFERENCES chat.users(id) NOT NULL,
    chatroom INTEGER REFERENCES chat.chatrooms(id) NOT NULL,
    text TEXT NOT NULL,
    timestamp TIMESTAMP
    );

DROP USER root;
CREATE USER root WITH LOGIN PASSWORD '0';