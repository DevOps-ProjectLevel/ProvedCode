--liquibase formatted sql
--changeset maslyna:1

-- Sponsor
-- Maksym Khudoliy
insert into sponsors (amount_kudos, first_name, last_name, image)
values (
           888,
           'Maksym',
           'Khudoliy',
           'https://i.pinimg.com/564x/e1/08/49/e10849923a8b2e85a7adf494ebd063e6.jpg'
       );
insert into users_info (sponsor_id, login, password)
values (
           (
               select id
               from sponsors
               order by id desc
               limit 1
               ), 'MaksymKhudoliy@gmail.com', '$2a$10$pDrAuawhi3ADZpVDDr7C6eAcaQwDr5oQ9GdZUUZHSsqyM/vVkpruy'
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
               where id = 2
               )
       );
-- Oleksandr Butrym
insert into sponsors (amount_kudos, first_name, last_name, image)
values (
           888,
           'Oleksandr',
           'Butrym',
           'https://i.pinimg.com/564x/c2/41/31/c24131fe00218467721ba5bacdf0a256.jpg'
       );
insert into users_info (sponsor_id, login, password)
values (
           (
               select id
               from sponsors
               order by id desc
               limit 1
               ), 'OleksandrButrym@gmail.com', '$2a$10$R0o8os0t86qyBvg0bO/a6ukuy9VesLapxIkZFLjNupWjvr5Hdjyge'
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
               where id = 2
               )
       );
-- Olha Shutylieva
insert into sponsors (amount_kudos, first_name, last_name, image)
values (
           888,
           'Olha',
           'Shutylieva',
           'https://i.pinimg.com/564x/2a/0c/08/2a0c08c421e253ca895c3fdc8c9e08d9.jpg'
       );
insert into users_info (sponsor_id, login, password)
values (
           (
               select id
               from sponsors
               order by id desc
               limit 1
               ), 'OlhaShutylieva@gmail.com', '$2a$10$UzwVTVR7E2BW.5hA4XWgy.g0XcM.UbIMBoY1cDnYNPQDhCXEa7eGm'
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
               where id = 2
               )
       );
-- Vladyslav Khrychov
insert into sponsors (amount_kudos, first_name, last_name, image)
values (
           888,
           'Vladyslav',
           'Khrychov',
           'https://i.pinimg.com/564x/e1/11/2f/e1112f0b7b63644dc3e313084936dedb.jpg'
       );
insert into users_info (sponsor_id, login, password)
values (
           (
               select id
               from sponsors
               order by id desc
               limit 1
               ), 'VladyslavKhrychov@gmail.com', '$2a$10$o2va23ZPVVSptyCaSBO/oubpML4xEPZo9Ie0ASt154zNVSKdFrN02'
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
               where id = 2
               )
       );