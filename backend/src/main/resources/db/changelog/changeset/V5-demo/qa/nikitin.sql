--liquibase formatted sql
--changeset Maslyna:11

insert into talents (first_name, last_name, specialization, image)
values (
           'Nikitin',
           'Oleksii',
           'Quality assurance engineer',
           'https://media.discordapp.net/attachments/1067116531265310872/1113782820314296370/2023-06-01_13.55.07.jpg?width=575&height=575'
       );
insert into descriptions (talent_id, bio, addition_info)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'In my first year at university, I started learning C++, but I stumbled upon QA courses and realized that this profession was more attractive to me. Now I am actively studying water supply.'
       ,'GLORY TO UKRAINE'
       );

insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), '+380673457856'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'oleksn@mail.ua'
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
               ), 'http://dev.provedcode.pepega.cloud/talents?page=0&size=5', 'This is the site we made with my team', 'Hidden', '2023-03-19 20:45:27'
       );
insert into proofs (talent_id, link, text, status, created)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.canva.com/design/DAFeaYPjj- E/lMskbFVL8SwNLicffDE81w/edit?utm_content=DAFeaYPjj- E&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton', 'This is a presentation of the ProvedCode project with my team', 'Draft', '2023-05-29 16:23:27'
       );
insert into users_info (talent_id, login, password)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'oleksn@mail.ua', '$2a$10$nDObO3nDlhWev29qCnzNuOszdg/.ANaMlTirDVWVyLMapYmtSSqza'
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
values (10, 151);
insert into talents_skills(talent_id, skill_id)
values (10, 9);
insert into talents_skills(talent_id, skill_id)
values (10, 58);
insert into talents_skills(talent_id, skill_id)
values (10, 59);
insert into talents_skills(talent_id, skill_id)
values (10, 60);


insert into proofs_skills (skill_id, proof_id)
values (9, 19);
insert into proofs_skills (skill_id, proof_id)
values (151, 19);
insert into proofs_skills (skill_id, proof_id)
values (124, 19);

insert into proofs_skills (skill_id, proof_id)
values (58, 20);
insert into proofs_skills (skill_id, proof_id)
values (59, 20);
insert into proofs_skills (skill_id, proof_id)
values (60, 20);
insert into proofs_skills (skill_id, proof_id)
values (193, 20);

insert into proofs_skills (skill_id, proof_id)
values (124, 21);
insert into proofs_skills (skill_id, proof_id)
values (127, 21);