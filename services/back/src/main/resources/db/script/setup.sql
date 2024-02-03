drop database if exists sb3_react;
create database sb3_react;

drop user if exists sb3_react_admin;
create user sb3_react_admin encrypted password 'password';

drop user if exists sb3_react_user;
create user sb3_react_user encrypted password 'password';

grant all privileges on database sb3_react to sb3_react_admin;
alter default privileges for user sb3_react_admin in schema public grant select, insert, update, delete on tables to sb3_react_user;