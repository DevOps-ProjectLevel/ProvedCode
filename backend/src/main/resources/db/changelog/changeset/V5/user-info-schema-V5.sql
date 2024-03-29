-- liquibase formatted sql
-- changeset Ren:0
-- tables for sprint 5
-- User
CREATE TABLE users_info (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    talent_id BIGINT REFERENCES talents,
    sponsor_id BIGINT REFERENCES sponsors,
    login VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_locked  BOOLEAN,
    PRIMARY KEY (id)
);
CREATE TABLE authorities (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    authority VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE users_authorities (
    authority_id BIGINT NOT NULL REFERENCES authorities,
    user_id BIGINT NOT NULL REFERENCES users_info,
    PRIMARY KEY (authority_id, user_id)
);