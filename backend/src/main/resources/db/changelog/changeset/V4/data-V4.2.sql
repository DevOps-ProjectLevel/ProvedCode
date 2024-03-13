--liquibase formatted sql
--changeset mykhailo:3

-- Skill on proof


-- Skill on talent
INSERT INTO talent_skill (talent_id, skill_id)
VALUES(1, 1);
INSERT INTO talent_skill (talent_id, skill_id)
VALUES(1, 2);
INSERT INTO talent_skill (talent_id, skill_id)
VALUES(5, 1);
INSERT INTO talent_skill (talent_id, skill_id)
VALUES(5, 2);
INSERT INTO talent_skill (talent_id, skill_id)
VALUES(5, 3);

-- Skills on proof
INSERT INTO proof_skill (proof_id, skill_id)
VALUES (13, 1);
INSERT INTO proof_skill (proof_id, skill_id)
VALUES (13, 2);
INSERT INTO proof_skill (proof_id, skill_id)
VALUES (13, 3);
