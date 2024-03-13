--liquibase formatted sql
--changeset Maslyna:1

insert into talents (first_name, last_name, specialization, image)
values (
           'Denis',
           'Boyko',
           'Java-Developer',
           'https://i.pinimg.com/564x/10/25/36/1025366d863e5fe94c9fc4cfca37291d.jpg'
       );
insert into descriptions (talent_id, bio, addition_info)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'Motivated and dedicated Junior Java Developer with a passion for coding and problem-solving. Strong foundation in Java programming and object-oriented concepts, with a keen interest in backend development. Eager to contribute to a dynamic development team and further enhance my skills in software development.',
           'GLORY TO UKRAINE'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.instagram.com/boyko_denisss/'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://github.com/Denis973'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.linkedin.com/in/den-boyko-8846b81a2/'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), '+380974996162'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'denisboyko@gmail.com'
       );
insert into proofs (talent_id, link, text, status, created)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://github.com/ProvedCode/backend', 'Project aims to develop a Java-based professional networking platform that shares. The platform will provide a robust and user-friendly environment for professionals to connect, network, and showcase their skills and proofs', 'PUBLISHED', '2023-04-04 12:11:19'
       );
insert into proofs (talent_id, link, text, status, created)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.youtube.com/watch?v=Geq60OVyBPg&ab_channel=Amigoscode', 'Software Testing Tutorial - Learn Unit and Integration Testing', 'DRAFT', '2023-04-024 19:11:19'
       );
insert into proofs (talent_id, link, text, status, created)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.youtube.com/watch?v=f5j1TaJlc0w&ab_channel=Amigoscode', 'In this video you will learn functional programming with Java Streams. I will introduce you the differences between imperative vs declarative programming using Java Streams API', 'HIDDEN', '2023-05-024 19:11:19'
       );
insert into users_info (talent_id, login, password)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'denisboyko@gmail.com', '$2a$10$tLm27FGH8Sabz57eNkTwm.bSnhmJHINcqt7dNfZI0NfOwD2o/Drse'
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
values (3, 21);
insert into talents_skills(talent_id, skill_id)
values (3, 22);
insert into talents_skills(talent_id, skill_id)
values (3, 1);
insert into talents_skills(talent_id, skill_id)
values (3, 2);
insert into talents_skills(talent_id, skill_id)
values (3, 3);
insert into talents_skills(talent_id, skill_id)
values (3, 4);
insert into talents_skills(talent_id, skill_id)
values (3, 5);
insert into talents_skills(talent_id, skill_id)
values (3, 6);

insert into proofs_skills (skill_id, proof_id)
values (1, 5);
insert into proofs_skills (skill_id, proof_id)
values (2, 5);
insert into proofs_skills (skill_id, proof_id)
values (3, 5);
insert into proofs_skills (skill_id, proof_id)
values (4, 5);
insert into proofs_skills (skill_id, proof_id)
values (5, 5);
insert into proofs_skills (skill_id, proof_id)
values (6, 5);
insert into proofs_skills (skill_id, proof_id)
values (7, 5);

insert into proofs_skills (skill_id, proof_id)
values (7, 6);
insert into proofs_skills (skill_id, proof_id)
values (1, 6);
insert into proofs_skills (skill_id, proof_id)
values (6, 6);
insert into proofs_skills (skill_id, proof_id)
values (22, 7);
insert into proofs_skills (skill_id, proof_id)
values (7, 7);
insert into proofs_skills (skill_id, proof_id)
values (2, 7);