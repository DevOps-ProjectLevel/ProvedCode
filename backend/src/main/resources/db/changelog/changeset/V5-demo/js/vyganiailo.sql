--liquibase formatted sql
--changeset Maslyna:5

insert into talents (first_name, last_name, specialization, image)
values (
           'Olexander',
           'Vyganiailo',
           'front-end developer',
           'https://media.discordapp.net/attachments/1067116531265310872/1113782729683763200/2023-06-01_13.54.29.jpg?width=575&height=575'
       );
insert into descriptions (talent_id, bio, addition_info)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'Hi, I am Olexander , student of 4-th grade in Sumy State University . Last 3 years I work with programming languages such: HTML, CSS, JS, C++ . Hard working and responsible student, who is ready to work and to absorb information '
       , 'GLORY TO UKRAINE'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://github.com/whoisalex1W'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.linkedin.com/in/%D0%B0%D0%BB%D0%B5%D0%BA%D1%81%D0%B0%D0%BD%D0%B4%D1%80-%D0%B2%D1%8B%D0%B3%D0%B0%D0%BD%D1%8F%D0%B9%D0%BB%D0%BE-b78421221/'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), '+380956506901'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'olexander.vyganiailo@gmail.com'
       );
insert into proofs (talent_id, link, text, status, created)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.youtube.com/', 'Such tools as NodeJS, MongoDB, Express were used to create the server part of the web-oriented information system. Unlike relational databases, MongoDB uses an approach without SQL queries and tables, instead, a document-oriented data model is used, which allows you to make working with the database scalable and simple.', 'PUBLISHED', '2023-05-04 11:11:11'
       );
insert into users_info (talent_id, login, password)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'olexander.vyganiailo@gmail.com', '$2a$10$nDObO3nDlhWev29qCnzNuOszdg/.ANaMlTirDVWVyLMapYmtSSqza'
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

insert into talents_skills(talent_id, skill_id)
values (5, 11), (5, 58), (5, 57), (5, 59), (5, 24);

insert into proofs_skills(proof_id, skill_id)
values (9, 11), (9, 58), (9, 57), (9, 59), (9, 24);