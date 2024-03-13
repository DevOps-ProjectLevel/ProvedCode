--liquibase formatted sql
--changeset Maslyna:8

insert into talents (first_name, last_name, specialization, image)
values (
           'Maxim',
           'Kiyashko',
           'Quality assurance engineer',
           'https://media.discordapp.net/attachments/1067116531265310872/1113782535021920327/image.png?width=575&height=575'
       );
insert into descriptions (talent_id, bio, addition_info)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'My name is Max, I am from Dnipro. I have been studying in Nure for 3 years. My specialization is computer engineering.'
       , 'GLORY TO UKRAINE'
       );

insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), '+380967802165'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'maqime8@gmail.com'
       );

insert into proofs (talent_id, link, text, status, created)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://olhammm.atlassian.net/jira/software/projects/PC/boards/1', 'This is my first Jira project with my team.', 'PUBLISHED', '2023-05-28 12:28:20'
       );
insert into proofs (talent_id, link, text, status, created)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'http://dev.provedcode.pepega.cloud/talents?page=0&size=5', 'This is the site we made with my team', 'PUBLISHED', '2023-03-19 20:45:27'
       );
insert into proofs (talent_id, link, text, status, created)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.canva.com/design/DAFeaYPjj E/lMskbFVL8SwNLicffDE81w/edit?ut m_content=DAFeaYPjj E&utm_campaign=designshare&utm_medium=link2&utm _source=sharebutton', 'This is a presentation of the ProvedCode project with my team', 'Draft', '2023-05-29 16:23:27'
       );
insert into users_info (talent_id, login, password)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'maqime8@gmail.com', '$2a$10$y.g9qHYUOPEkIL8xDc2h1.EdVAG5DYh6OKxf9CRb6s16oHHbr8Bny'
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
values (7, 9);
insert into talents_skills(talent_id, skill_id)
values (7, 34);
insert into talents_skills(talent_id, skill_id)
values (7, 199);
insert into talents_skills(talent_id, skill_id)
values (7, 58);
insert into talents_skills(talent_id, skill_id)
values (7, 198);
insert into talents_skills(talent_id, skill_id)
values (7, 151);
insert into proofs_skills (skill_id, proof_id)
values (9, 12);
insert into proofs_skills (skill_id, proof_id)
values (124, 12);
insert into proofs_skills (skill_id, proof_id)
values (151, 12);
insert into proofs_skills (skill_id, proof_id)
values (58, 13);
insert into proofs_skills (skill_id, proof_id)
values (58, 13);
insert into proofs_skills (skill_id, proof_id)
values (193, 13);
insert into proofs_skills (skill_id, proof_id)
values (127, 14);
insert into proofs_skills (skill_id, proof_id)
values (124, 14);