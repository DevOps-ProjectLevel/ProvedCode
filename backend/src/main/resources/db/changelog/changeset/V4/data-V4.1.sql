--liquibase formatted sql
--changeset dennis:3
-- Skill in proof
INSERT INTO proof_skill (proof_id, skill_id)
VALUES (1, 1);
INSERT INTO proof_skill (proof_id, skill_id)
VALUES (1, 2);
INSERT INTO proof_skill (proof_id, skill_id)
VALUES (1, 3);
INSERT INTO proof_skill (proof_id, skill_id)
VALUES (1, 4);
INSERT INTO proof_skill (proof_id, skill_id)
VALUES (4, 1);
INSERT INTO proof_skill (proof_id, skill_id)
VALUES (5, 2);
INSERT INTO proof_skill (proof_id, skill_id)
VALUES (6, 3);

