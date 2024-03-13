--liquibase formatted sql
--changeset Maslyna:10

insert into talents (first_name, last_name, specialization, image)
values (
           'Olha',
           'Moiseienko',
           'QA',
           'https://i.pinimg.com/564x/8c/6a/9c/8c6a9c334e1da809282f544f12d18829.jpg'
       );
insert into descriptions (talent_id, bio, addition_info)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'I am from Ukraine. I live in Sumy. I graduated from college and have a diploma, now I am continuing my studies at the university.I can write test plans, checklists, test cases and bug-reports.'
       , 'GLORY TO UKRAINE'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), '+38066333553'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'olhamoiseienko@mail.org'
       );
insert into proofs (talent_id, link, text, status, created)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://olhammm.atlassian.net/jira/software/projects/PC/boards/1/roadmap', 'This link reflects the planning and organization of our team''s work in Jira. ', 'PUBLISHED', '2023-03-10 12:00:19'
       );
insert into proofs (talent_id, link, text, status, created)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'http://dev.provedcode.pepega.cloud/talents?page=0&size=5', 'This site was made by our team and mostly tested by me.', 'DRAFT', '2023-05-28 18:00:00'
       );

insert into users_info (talent_id, login, password)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'OlhaMoiseienko@gmail.com', '$2a$10$lvvX7DZOwCS/Q7zSo.k.oeayTcKHh8rO1yBBkgIbU4VAC7abPfIa2'
       );
insert into users_authorities (user_id, authority_id)
values (
           (
               select id
               from users_info
               order by id desc
               limit 1
               ), (
               select id
               from authorities
               where id = 1
               )
       );

INSERT INTO proofs_skills (proof_id, skill_id)
VALUES (17, 151),
       (17, 9),
       (17, 199),
       (17, 124),
       (17, 110),
       (17, 139);
INSERT INTO proofs_skills (proof_id, skill_id)
VALUES (18, 151),
       (18, 9),
       (18, 199),
       (18, 124),
       (18, 110),
       (18, 3),
       (18, 22),
       (18, 7),
       (18, 139);
INSERT INTO talents_skills (talent_id, skill_id)
VALUES (9, 151),
       (9, 9),
       (9, 199),
       (9, 124),
       (9, 110),
       (9, 139);