--liquibase formatted sql
--changeset Ren:0
-- Talent
drop table if exists talent cascade;
drop table if exists talent_attached_file cascade;
drop table if exists talent_contact cascade;
drop table if exists talent_description cascade;
drop table if exists talent_link cascade;
drop table if exists talent_proofs cascade;
drop table if exists talent_skill cascade;
drop table if exists skill cascade;
-- User
drop table if exists authority cascade;
drop table if exists user_authorities cascade;
drop table if exists user_info cascade;
-- Sponsor
drop table if exists sponsor cascade;
drop table if exists kudos cascade;