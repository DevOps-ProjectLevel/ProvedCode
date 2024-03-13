--liquibase formatted sql
--changeset dennis:1

insert into authority (id, authority)
values (1, 'TALENT');
-- -- FOR USER AUTHORITY
-- -- SELECT USER_INFO.ID , LOGIN , PASSWORD, talent_id , AUTHORITY FROM
-- --     USER_INFO
-- --         JOIN user_authorities ON talent_id = USER_INFO.ID
-- --         JOIN AUTHORITY ON AUTHORITY.ID = id

insert into talent (first_name, last_name, specialization, image)
values ('Serhii', 'Soloviov', 'Java-Developer', 'https://i.pinimg.com/564x/e1/08/49/e10849923a8b2e85a7adf494ebd063e6.jpg');
insert into talent_description (talent_id, BIO, addition_info)
values((select id from talent order by id desc limit 1), 'Hello! I was born in Ukraine. My profession is Java Back-end developer. Now I`m developing a Java backend using Spring Boot.', 'My cat`s name is Igor');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Java Core');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Spring Core');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Spring boot');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'H2 Database');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'first_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'second_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'third_contact');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'Here I wrote a program that transforms the written code into music', 'PUBLISHED', '2022-01-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to second proof', 'DRAFT', '2023-03-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to third proof', 'HIDDEN', '2021-06-08 16:00:19');

insert into user_info (talent_id, login, password)
values ((select id from talent order by id desc limit 1), 'SerhiiSoloviov@gmail.com', 'password');
insert into user_authorities (user_id, authority_id)
values ((select id from user_info order by id desc limit 1),
        (select authority.id from authority where id = 1));

insert into talent (first_name, last_name, specialization, image)
values ('Mykhailo', 'Ordyntsev', 'Java-Developer', 'https://i.pinimg.com/564x/c2/41/31/c24131fe00218467721ba5bacdf0a256.jpg');
insert into talent_description (talent_id, BIO, addition_info)
values((select id from talent order by id desc limit 1), 'Hello! I was born in Ukraine. My profession is Java Back-end developer. Now I`m developing a Java backend using Spring Boot.', 'I very like anime');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Java Core');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Hibernate');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Spring Boot');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Git');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'MykhailoOrdyntsev_first_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'MykhailoOrdyntsev_second_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'MykhailoOrdyntsev_third_contact');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to first proof', 'DRAFT', '2022-08-07 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to second proof', 'HIDDEN', '2022-04-08 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to third proof', 'DRAFT', '2022-09-02 16:00:19');

insert into user_info (talent_id, login, password)
values ((select id from talent order by id desc limit 1), 'MykhailoOrdyntsev@gmail.com', 'password');
insert into user_authorities (user_id, authority_id)
values ((select id from user_info order by id desc limit 1),
        (select authority.id from authority where id = 1));

insert into talent (first_name, last_name, specialization, image)
values ('Denis', 'Boyko', 'Java-Developer', 'https://i.pinimg.com/564x/2a/0c/08/2a0c08c421e253ca895c3fdc8c9e08d9.jpg');
insert into talent_description (talent_id, BIO, addition_info)
values((select id from talent order by id desc limit 1), 'Hello! I was born in Ukraine. My profession is Java Back-end developer. Now I`m developing a Java backend using Spring Boot.', 'I`m a student ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Java Core');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Spring Security');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Spring Core');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'DenisBoyko_first_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'DenisBoyko_second_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'DenisBoyko_third_contact');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to first proof', 'DRAFT', '2022-02-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to second proof', 'DRAFT', '2021-09-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to third proof', 'DRAFT', '2023-04-04 16:00:19');

insert into user_info (talent_id, login, password)
values ((select id from talent order by id desc limit 1), 'DenisBoyko@gmail.com', 'password');
insert into user_authorities (user_id, authority_id)
values ((select id from user_info order by id desc limit 1),
        (select authority.id from authority where id = 1));

insert into talent (first_name, last_name, specialization, image)
values ('Ihor', 'Schurenko', 'Java-Developer', 'https://i.pinimg.com/564x/e1/11/2f/e1112f0b7b63644dc3e313084936dedb.jpg');
insert into talent_description (talent_id, BIO, addition_info)
values((select id from talent order by id desc limit 1), 'Hello! I was born in Ukraine. My profession is Java Back-end developer. Now I`m developing a Java backend using Spring Boot.', 'I will get married soon');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Java Core');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'REST API');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'IhorShchurenko_first_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'IhorShchurenko_second_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'IhorShchurenko_third_contact');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');

insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'Here I describe my rest api project in java', 'PUBLISHED', '2021-08-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to second proof', 'DRAFT', '2022-06-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to third proof', 'DRAFT', '2023-05-04 16:00:19');

insert into user_info (talent_id, login, password)
values ((select id from talent order by id desc limit 1), 'IhorShchurenko@gmail.com', 'password');
insert into user_authorities (user_id, authority_id)
values ((select id from user_info order by id desc limit 1),
        (select authority.id from authority where id = 1));

insert into talent (first_name, last_name, specialization, image)
values ('Dmytro', 'Uzun', 'Dev-Ops', 'https://i.pinimg.com/564x/1c/af/87/1caf8771ef3edf351f6f2bf6f1c0a276.jpg');
insert into talent_description (talent_id, BIO, addition_info)
values((select id from talent order by id desc limit 1), 'I am instructing a team that is currently writing my own biography for me.', 'He-he-he, hello everyone!');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Git');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Docker');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Mentor');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'DmytroUzun_first_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'DmytroUzun_second_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'DmytroUzun_third_contact');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');

insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://github.com/ProvedCode', 'My project where I am a team mentor', 'PUBLISHED', '2023-02-08 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to second proof', 'DRAFT', '2021-03-03 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to third proof', 'DRAFT', '2023-09-05 16:00:19');

insert into user_info (talent_id, login, password)
values ((select id from talent order by id desc limit 1), 'DmytroUzun@gmail.com', 'password');
insert into user_authorities (user_id, authority_id)
values ((select id from user_info order by id desc limit 1),
        (select authority.id from authority where id = 1));

insert into talent (first_name, last_name, specialization, image)
values ('Viktor', 'Voloshko', 'Dev-Ops', 'https://i.pinimg.com/564x/a9/51/ab/a951ab682413b89617235e65564c1e5e.jpg');
insert into talent_description (talent_id, BIO, addition_info)
values((select id from talent order by id desc limit 1), 'Hi all! I was born in Ukraine. I am a student. My profession is dev-ops.', 'I like videogames!');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Git');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Docker');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'ViktorVoloshko_first_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'ViktorVoloshko_second_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'ViktorVoloshko_third_contact');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');

insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to first proof', 'HIDDEN', '2022-02-09 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to second proof', 'DRAFT', '2020-04-02 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to third proof', 'DRAFT', '2023-08-06 16:00:19');

insert into user_info (talent_id, login, password)
values ((select id from talent order by id desc limit 1), 'ViktorVoloshko@gmail.com', 'password');
insert into user_authorities (user_id, authority_id)
values ((select id from user_info order by id desc limit 1),
        (select authority.id from authority where id = 1));

insert into talent (first_name, last_name, specialization, image)
values ('Olha', 'Moiseienko', 'QA', 'https://i.pinimg.com/564x/6d/9d/43/6d9d437baf4db114c047d927307beb84.jpg');
insert into talent_description (talent_id, BIO, addition_info)
values((select id from talent order by id desc limit 1), 'Hi all! I was born in Ukraine. I am a student. My profession is QA.', 'Glory to Ukraine');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Git');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Jira');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'QA');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'OlhaMoiseienko_first_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'OlhaMoiseienko_second_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'OlhaMoiseienko_third_contact');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');

insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to first proof', 'HIDDEN', '2023-06-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'My QA Jira project', 'PUBLISHED', '2022-09-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to third proof', 'DRAFT', '2021-01-09 16:00:19');

insert into user_info (talent_id, login, password)
values ((select id from talent order by id desc limit 1), 'OlhaMoiseienko@gmail.com', 'password');
insert into user_authorities (user_id, authority_id)
values ((select id from user_info order by id desc limit 1),
        (select authority.id from authority where id = 1));

insert into talent (first_name, last_name, specialization, image)
values ('Maxim', 'Kiyashko', 'QA', 'https://i.pinimg.com/564x/80/2d/58/802d58b0302985f9486893d499d3634d.jpg');
insert into talent_description (talent_id, BIO, addition_info)
values((select id from talent order by id desc limit 1), 'Hi all! I was born in Ukraine. I am a student. My profession is QA.', 'Glory to Ukraine');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Git');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'QA');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'MaximKiyashko_first_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'MaximKiyashko_second_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'MaximKiyashko_third_contact');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');

insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'My custom Arduino OS', 'PUBLISHED', '2023-08-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to second proof', 'DRAFT', '2023-01-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to third proof', 'PUBLISHED', '2023-02-09 16:00:19');

insert into user_info (talent_id, login, password)
values ((select id from talent order by id desc limit 1), 'MaximKiyashko@gmail.com', 'password');
insert into user_authorities (user_id, authority_id)
values ((select id from user_info order by id desc limit 1),
        (select authority.id from authority where id = 1));

insert into talent (first_name, last_name, specialization, image)
values ('Nikolaiev', 'Oleksii', 'QA', 'https://i.pinimg.com/564x/54/d1/0d/54d10dfce64afefabc9fbbce5de82c87.jpg');
insert into talent_description (talent_id, BIO, addition_info)
values((select id from talent order by id desc limit 1), 'Hi all! I was born in Ukraine. I am a student. My profession is QA.', 'Glory to Ukraine');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'QA');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Git');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'NikolaievOleksii_third_talents');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'NikolaievOleksii_first_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'NikolaievOleksii_second_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'NikolaievOleksii_third_contact');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');

insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'Link to my magnum opus:', 'PUBLISHED', '2023-06-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to second proof', 'DRAFT', '2023-06-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to third proof', 'HIDDEN', '2023-06-04 16:00:19');

insert into user_info (talent_id, login, password)
values ((select id from talent order by id desc limit 1), 'NikolaievOleksiio@gmail.com', 'password');
insert into user_authorities (user_id, authority_id)
values ((select id from user_info order by id desc limit 1),
        (select authority.id from authority where id = 1));

insert into talent (first_name, last_name, specialization, image)
values ('Artem', 'Lytvynenko', 'QA', 'https://i.pinimg.com/564x/87/63/55/87635509c5fa7ee496ec351fa7e67eaa.jpg');
insert into talent_description (talent_id, BIO, addition_info)
values((select id from talent order by id desc limit 1), 'Hi all! I was born in Ukraine. I am a student. My profession is QA.', 'Glory to Ukraine');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'QA');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Git');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'ArtemLytvynenko_first_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'ArtemLytvynenko_second_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'ArtemLytvynenko_third_contact');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');

insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'Here I wrote tasks for the project', 'PUBLISHED', '2023-06-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to second proof', 'DRAFT', '2023-06-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to third proof', 'HIDDEN', '2023-06-04 16:00:19');

insert into user_info (talent_id, login, password)
values ((select id from talent order by id desc limit 1), 'ArtemLytvynenko@gmail.com', 'password');
insert into user_authorities (user_id, authority_id)
values ((select id from user_info order by id desc limit 1),
        (select authority.id from authority where id = 1));

insert into talent (first_name, last_name, specialization, image)
values ('Daniil', 'Yevtukhov', 'Java-Script-Developer', 'https://i.pinimg.com/564x/fe/b1/37/feb137d88a3d1c8fb28796db6cbc576f.jpg');
insert into talent_description (talent_id, BIO, addition_info)
values((select id from talent order by id desc limit 1), 'Hi all! I was born in Ukraine. I am a student. My profession is JavaScript developer.', 'I have my own Instagram');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'JavaScript Core');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'React');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'DaniilYevtukhov_first_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'DaniilYevtukhov_second_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'DaniilYevtukhov_third_contact');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');

insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'My main project where I am making REACT application', 'PUBLISHED', '2023-06-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to second proof', 'HIDDEN', '2023-06-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to third proof', 'DRAFT', '2023-06-04 16:00:19');

insert into user_info (talent_id, login, password)
values ((select id from talent order by id desc limit 1), 'DaniilYevtukhov@gmail.com', 'password');
insert into user_authorities (user_id, authority_id)
values ((select id from user_info order by id desc limit 1),
        (select authority.id from authority where id = 1));

insert into talent (first_name, last_name, specialization, image)
values ('Ruslan', 'Morozov', 'Java-Script-Developer', 'https://i.pinimg.com/736x/36/ae/0e/36ae0ea4aad656f7c3d3175bc33b8399.jpg');
insert into talent_description (talent_id, BIO, addition_info)
values((select id from talent order by id desc limit 1), 'Hi all! I was born in Ukraine. I am a student. My profession is JavaScript developer.', 'Glory to Ukraine');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'JavaScript Core');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'React');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Node.js');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'RuslanMorozov_first_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'RuslanMorozov_second_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'RuslanMorozov_third_contact');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');

insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'Here my container for styles', 'PUBLISHED', '2023-06-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to second proof', 'DRAFT', '2023-06-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to third proof', 'DRAFT', '2023-06-04 16:00:19');

insert into user_info (talent_id, login, password)
values ((select id from talent order by id desc limit 1), 'RuslanMorozov@gmail.com', 'password');
insert into user_authorities (user_id, authority_id)
values ((select id from user_info order by id desc limit 1),
        (select authority.id from authority where id = 1));

insert into talent (first_name, last_name, specialization, image)
values ('Ihor', 'Kopieichykov', 'Java-Script-Developer', 'https://i.pinimg.com/564x/0d/f0/83/0df083121bac75f64e3d93c7c5682d04.jpg');
insert into talent_description (talent_id, BIO, addition_info)
values((select id from talent order by id desc limit 1), 'Hi all! I was born in Ukraine. I am a student. My profession is JavaScript developer.', 'Glory to Ukraine');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_link (talent_id, link)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'JavaScript Core');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'React');
insert into talent_talents (talent_id, talent_name)
values ((select id from talent order by id desc limit 1), 'Angular');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'IhorKopieichykov_first_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'IhorKopieichykov_second_contact');
insert into talent_contact (talent_id, contact)
values ((select id from talent order by id desc limit 1), 'IhorKopieichykov_third_contact');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');
insert into talent_attached_file (talent_id, attached_file)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ');

insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'Here is my JavaScript library', 'PUBLISHED', '2023-06-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'Here is my main project in Angular', 'PUBLISHED', '2023-06-04 16:00:19');
insert into talent_proofs (talent_id, link, text, status, created)
values ((select id from talent order by id desc limit 1), 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 'text to third proof', 'DRAFT', '2023-06-04 16:00:19');

insert into user_info (talent_id, login, password)
values ((select id from talent order by id desc limit 1), 'IhorKopieichykov@gmail.com', 'password');
insert into user_authorities (user_id, authority_id)
values ((select id from user_info order by id desc limit 1),
        (select authority.id from authority where id = 1));