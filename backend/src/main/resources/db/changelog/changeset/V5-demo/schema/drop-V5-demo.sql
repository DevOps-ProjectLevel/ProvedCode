--liquibase formatted sql
--changeset Ren:1
-- Talent
drop table if exists talents cascade;
drop table if exists attached_files cascade;
drop table if exists contacts cascade;
drop table if exists descriptions cascade;
drop table if exists links cascade;
drop table if exists proofs cascade;
drop table if exists talents_skills cascade;
drop table if exists proofs_skills cascade;
drop table if exists skills cascade;
-- User
drop table if exists authorities cascade;
drop table if exists users_authorities cascade;
drop table if exists users_info cascade;
drop table if exists deleted_user cascade;
-- Sponsor
drop table if exists sponsors cascade;
drop table if exists kudos cascade;