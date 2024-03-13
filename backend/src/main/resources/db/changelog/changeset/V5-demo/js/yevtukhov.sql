--liquibase formatted sql
--changeset Maslyna:6

insert into talents (first_name, last_name, specialization, image)
values (
           'Daniil',
           'Yevtukhov',
           'JS front-end developer',
           'https://i.pinimg.com/564x/0f/2b/4c/0f2b4c6358ced7a70566c05171eb1e9d.jpg'
       );
insert into descriptions (talent_id, bio, addition_info)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'I am a JS backend developer. ' ||
                  'I started my career in programming by learning Pascal at school, ' ||
                  'then dabbled in Python for a while. I also have some knowledge of ' ||
                  'java script and php. At the moment, I''m studying java, specifically spring boot. ' ||
                  'I am also familiar with database management systems such as msql, postgres, and mysql.'
       , 'GLORY TO UKRAINE'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.instagram.com/ievtukhovofficial/'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://github.com/daniilievtukhov'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.linkedin.com/in/daniil-yevtukhov-b9811220b/'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), '+380505168717'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'daniil.ievtukhov@nure.ua'
       );

insert into proofs (talent_id, link, text, status, created)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://github.com/ProvedCode/fontend', 'Our project aims to develop a JS-based professional networking platform that shares. The platform will provide a robust and user-friendly environment for professionals to connect, network, and showcase their skills and proofs.	','PUBLISHED', '2023-05-04 13:11:19'
       );
insert into proofs (talent_id, link, text, status, created)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.youtube.com/watch?v=CxgOKJh4zWE&ab_channel=BogdanStashchuk', 'JavaScript courses', 'DRAFT', '2023-05-26 18:10:56'
       );
insert into users_info (talent_id, login, password)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'daniil.ievtukhov@nure.ua', '$2a$10$EzYxG1DEUek/veK.HzP7B.ynSKE42VbLb4pvFd/v4OwGPNol6buEC'
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
VALUES (10, 11),
       (10, 10),
       (10, 58),
       (10, 59),
       (10, 57);
INSERT INTO proofs_skills (proof_id, skill_id)
VALUES (11, 57);
INSERT INTO talents_skills (talent_id, skill_id)
VALUES (6, 11),
       (6, 10),
       (6, 58),
       (6, 59),
       (6, 57);