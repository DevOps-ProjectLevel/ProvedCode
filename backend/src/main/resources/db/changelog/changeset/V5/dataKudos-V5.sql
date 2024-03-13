--liquibase formatted sql
--changeset dennis:5
insert into kudos(amount, sponsor_id, proof_skill_id)
values (10, 1, 1);
insert into kudos(amount, sponsor_id, proof_skill_id)
values (20, 2, 1);
insert into kudos(amount, sponsor_id, proof_skill_id)
values (30, 3, 3);
insert into kudos(amount, sponsor_id, proof_skill_id)
values (10, 4, 1);
insert into kudos(amount, sponsor_id, proof_skill_id)
values (10, 1, 4);
