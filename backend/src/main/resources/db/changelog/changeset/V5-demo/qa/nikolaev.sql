--liquibase formatted sql
--changeset Maslyna:1

insert into talents (first_name, last_name, specialization, image)
values (
           'Oleksii',
           'Nikolaiev',
           'QA',
           'https://media.discordapp.net/attachments/1067116531265310872/1113796561923756042/photo_2023-05-31_16-53-14.jpg?width=507&height=674'
       );
insert into descriptions (talent_id, bio, addition_info)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'Discover the realm of Quality Assurance Engineering with Oleksii, your expert companion in the world of software testing and quality control. Whether youre a seasoned professional or just starting your journey in the field, this is the ultimate destination for all things QA. From automation testing to bug tracking, from test case design to performance optimization, Oleksii has got you covered. Ask Oleksii anything about QA Engineering, and unlock the secrets to ensuring top-notch software quality. Are you ready to enhance your skills and become a QA master? Join us now!',
           'GLORY TO UKRAINE'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.work.ua/jobseeker/my/'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://www.linkedin.com/in/oleksii-nikolaiev-b4655b262/'
       );
insert into links (talent_id, link)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'https://djinni.co/q/7ef35a52a7/'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), '+380661237254'
       );
insert into contacts (talent_id, contact)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'oleksii.email@gmail.com'
       );

insert into users_info (talent_id, login, password)
values (
           (
               select id
               from talents
               order by id desc
               limit 1
               ), 'oleksii.email@gmail.com  ', '$2a$10$tLm27FGH8Sabz57eNkTwm.bSnhmJHINcqt7dNfZI0NfOwD2o/Drse'
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