-- liquibase formatted sql
-- changeset Ren:0
-- tables for sprint 4.1
-- Talent
CREATE TABLE talent
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name     VARCHAR(20),
    last_name      VARCHAR(20),
    specialization VARCHAR(30),
    image          VARCHAR(1000),
    image_name     VARCHAR(100),
    CONSTRAINT pk_talent PRIMARY KEY (id)
);
CREATE TABLE talent_attached_file
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    talent_id     BIGINT                                  NOT NULL,
    attached_file VARCHAR(100),
    CONSTRAINT pk_talent_attached_file PRIMARY KEY (id)
);
CREATE TABLE talent_contact
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    talent_id BIGINT                                  NOT NULL,
    contact   VARCHAR(255),
    CONSTRAINT pk_talent_contact PRIMARY KEY (id)
);
CREATE TABLE talent_description
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    talent_id     BIGINT                                  NOT NULL,
    bio           VARCHAR(2000),
    addition_info VARCHAR(500),
    CONSTRAINT pk_talent_description PRIMARY KEY (id)
);
CREATE TABLE talent_link
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    talent_id BIGINT                                  NOT NULL,
    link      VARCHAR(500),
    CONSTRAINT pk_talent_link PRIMARY KEY (id)
);
CREATE TABLE talent_proofs
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    talent_id BIGINT                                  NOT NULL,
    link      VARCHAR(100),
    text      VARCHAR(1000),
    status    VARCHAR(20)                             NOT NULL,
    created   TIMESTAMP,
    CONSTRAINT pk_talent_proofs PRIMARY KEY (id)
);
CREATE TABLE talent_skill
(
    talent_id BIGINT NOT NULL,
    skill_id  BIGINT NOT NULL,
    CONSTRAINT pk_talent_skill PRIMARY KEY (talent_id, skill_id)
);
CREATE TABLE proof_skill
(
    proof_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,
    CONSTRAINT pk_proof_skill PRIMARY KEY (proof_id, skill_id)
);
CREATE TABLE skill
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    skill VARCHAR(30),
    CONSTRAINT pk_skill PRIMARY KEY (id)
);
-- User
CREATE TABLE user_info
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    talent_id  BIGINT,
    sponsor_id BIGINT,
    login      VARCHAR(100)                            NOT NULL,
    password   VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_user_info PRIMARY KEY (id)
);
CREATE TABLE authority
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    authority VARCHAR(20)                             NOT NULL,
    CONSTRAINT pk_authority PRIMARY KEY (id)
);
CREATE TABLE user_authorities
(
    authority_id BIGINT NOT NULL,
    user_id      BIGINT NOT NULL,
    CONSTRAINT pk_user_authorities PRIMARY KEY (authority_id, user_id)
);
-- Sponsor
CREATE TABLE sponsor
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name   VARCHAR(20),
    last_name    VARCHAR(20),
    image        VARCHAR(1000),
    image_name   VARCHAR(100),
    amount_kudos BIGINT,
    CONSTRAINT pk_sponsor PRIMARY KEY (id)
);
CREATE TABLE kudos
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    sponsor_id BIGINT,
    proof_id   BIGINT,
    amount     BIGINT,
    CONSTRAINT pk_kudos PRIMARY KEY (id)
);
-- Foreign keys
ALTER TABLE talent_attached_file
    ADD CONSTRAINT FK_TALENT_ATTACHED_FILE_ON_TALENT FOREIGN KEY (talent_id) REFERENCES talent (id);
ALTER TABLE talent_contact
    ADD CONSTRAINT FK_TALENT_CONTACT_ON_TALENT FOREIGN KEY (talent_id) REFERENCES talent (id);
ALTER TABLE talent_description
    ADD CONSTRAINT FK_TALENT_DESCRIPTION_ON_TALENT FOREIGN KEY (talent_id) REFERENCES talent (id);
ALTER TABLE talent_link
    ADD CONSTRAINT FK_TALENT_LINK_ON_TALENT FOREIGN KEY (talent_id) REFERENCES talent (id);
ALTER TABLE talent_proofs
    ADD CONSTRAINT FK_TALENT_PROOFS_ON_TALENT FOREIGN KEY (talent_id) REFERENCES talent (id);
ALTER TABLE user_info
    ADD CONSTRAINT FK_USER_INFO_ON_TALENT FOREIGN KEY (talent_id) REFERENCES talent (id);
ALTER TABLE user_authorities
    ADD CONSTRAINT FK_useaut_on_authority FOREIGN KEY (authority_id) REFERENCES authority (id);
ALTER TABLE user_authorities
    ADD CONSTRAINT FK_useaut_on_user_info FOREIGN KEY (user_id) REFERENCES user_info (id);
ALTER TABLE kudos
    ADD CONSTRAINT FK_KUDOS_ON_PROOF FOREIGN KEY (proof_id) REFERENCES talent_proofs (id);
ALTER TABLE kudos
    ADD CONSTRAINT FK_KUDOS_ON_SPONSOR FOREIGN KEY (sponsor_id) REFERENCES sponsor (id);
ALTER TABLE proof_skill
    ADD CONSTRAINT FK_proof_skill_ON_TALENT_PROOF FOREIGN KEY (proof_id) REFERENCES talent_proofs (id);
ALTER TABLE proof_skill
    ADD CONSTRAINT FK_proof_skill_ON_SKILL FOREIGN KEY (skill_id) REFERENCES skill (id);
ALTER TABLE talent_skill
    ADD CONSTRAINT FK_talent_skill_ON_TALENT FOREIGN KEY (talent_id) REFERENCES talent (id);
ALTER TABLE talent_skill
    ADD CONSTRAINT FK_talent_skill_ON_SKILL FOREIGN KEY (skill_id) REFERENCES skill (id);
-- Indexes
CREATE UNIQUE INDEX idx_login ON user_info (login)