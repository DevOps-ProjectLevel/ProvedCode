--liquibase formatted sql
--changeset dennis:5
-- Authority
insert into authorities (id, authority)
values (1, 'TALENT');
insert into authorities (id, authority)
values (2, 'SPONSOR');
