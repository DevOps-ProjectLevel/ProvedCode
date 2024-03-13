--liquibase formatted sql
--changeset Maslyna:4

insert into talents (first_name, last_name, specialization, image)
values (
           'Ruslan',
           'Morozov',
           'JS front-end developer',
           'https://i.pinimg.com/564x/54/d1/0d/54d10dfce64afefabc9fbbce5de82c87.jpg'
       );
insert into descriptions (talent_id, bio, addition_info)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'Student of the Karazin Kharkiv National University. Confident and ambitious Junior React developer. I like to solve problems and look for new and non-standard approaches to them. Always developing and striving for knowledge'
       , 'GLORY TO UKRAINE'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.instagram.com/ruslan1903_/'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://github.com/Ruslanchik01'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.linkedin.com/in/ruslan-morozov-185b17238/'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), '+380667538060'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'morozov.ruslan2003@gmail.com'
       );
insert into proofs (talent_id, link, text, status, created)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://github.com/ProvedCode/frontend', 'Our project aims to develop a JS-based professional networking platform that shares. The platform will provide a robust and user-friendly environment for professionals to connect, network, and showcase their skills and proofs.	', 'PUBLISHED', '2023-05-04 11:11:11'
       );
insert into users_info (talent_id, login, password)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'morozov.ruslan2003@gmail.com', '$2a$10$nDObO3nDlhWev29qCnzNuOszdg/.ANaMlTirDVWVyLMapYmtSSqza'
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
values (4, 11), (4, 10), (4, 58), (4, 59), (4, 57);

insert into proofs_skills(proof_id, skill_id)
values (8, 11), (8, 10), (8, 58), (8, 59), (8, 57);


