drop database if exists sb3_react;
create database sb3_react;

drop user if exists sb3_react_admin;
create user sb3_react_admin encrypted password 'password';

drop user if exists sb3_react_user;
create user sb3_react_user encrypted password 'password';

alter database sb3_react owner to sb3_react_admin;
grant connect on database sb3_react to sb3_react_user;
\c sb3_react
grant usage on schema public to sb3_react_user;
alter default privileges for user sb3_react_admin in schema public grant select, insert, update, delete on tables to sb3_react_user;