--liquibase formatted sql
--changeset mykhailo:2

drop table if exists authority cascade;
drop table if exists talent cascade;
drop table if exists talent_attached_file cascade;
drop table if exists talent_contact cascade;
drop table if exists talent_description cascade;
drop table if exists talent_link cascade;
drop table if exists talent_proofs cascade;
drop table if exists talent_talents cascade;
drop table if exists user_authorities cascade;
drop table if exists user_info cascade;
drop table if exists kudos cascade;
drop table if exists sponsor cascade;
