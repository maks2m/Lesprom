alter table if exists employee_workplace
    drop constraint if exists buffer_employee_fk;
alter table if exists employee_workplace
    drop constraint if exists buffer_workplace_fk;
alter table if exists order_workplace
    drop constraint if exists buffer_order_fk;
alter table if exists order_workplace
    drop constraint if exists buffer_workplace_fk;
alter table if exists time_of_employee_on_order
    drop constraint if exists time_of_employee_on_order_employee_fk;
alter table if exists time_of_employee_on_order
    drop constraint if exists time_of_employee_on_order_order_fk;
alter table if exists user_role
    drop constraint if exists buffer_role_fk;
alter table if exists user_role
    drop constraint if exists buffer_user_fk;
drop table if exists baguette cascade;
drop table if exists cutter cascade;
drop table if exists employee cascade;
drop table if exists employee_workplace cascade;
drop table if exists order_on_create cascade;
drop table if exists order_workplace cascade;
drop table if exists role cascade;
drop table if exists time_of_employee_on_order cascade;
drop table if exists user_role cascade;
drop table if exists usr cascade;
drop table if exists workplace cascade;
create table baguette
(
    id            int8 generated by default as identity,
    baguette_name varchar(255),
    primary key (id)
);
create table cutter
(
    id          int8 generated by default as identity,
    cutter_name varchar(255),
    primary key (id)
);
create table employee
(
    id        int8 generated by default as identity,
    full_name varchar(255),
    primary key (id)
);
create table employee_workplace
(
    id_workplace int8 not null,
    id_employee  int8 not null,
    primary key (id_employee, id_workplace)
);
create table order_on_create
(
    id           int8 generated by default as identity,
    number_order int4,
    primary key (id)
);
create table order_workplace
(
    id_workplace int8 not null,
    id_order     int8 not null,
    primary key (id_order, id_workplace)
);
create table role
(
    id   int8 generated by default as identity,
    role varchar(255),
    primary key (id)
);
create table time_of_employee_on_order
(
    id               int8 generated by default as identity,
    time_finish_work timestamp,
    time_start_work  timestamp,
    id_employee      int8,
    id_order         int8,
    primary key (id)
);
create table user_role
(
    id_user int8 not null,
    id_role int8 not null,
    primary key (id_role, id_user)
);
create table usr
(
    id       int8 generated by default as identity,
    password varchar(255),
    username varchar(255),
    primary key (id)
);
create table workplace
(
    id             int8 generated by default as identity,
    name_workplace varchar(255),
    primary key (id)
);
alter table if exists employee_workplace
    add constraint buffer_employee_fk foreign key (id_employee) references employee;
alter table if exists employee_workplace
    add constraint buffer_workplace_fk foreign key (id_workplace) references workplace;
alter table if exists order_workplace
    add constraint buffer_order_fk foreign key (id_order) references order_on_create;
alter table if exists order_workplace
    add constraint buffer_workplace_fk foreign key (id_workplace) references workplace;
alter table if exists time_of_employee_on_order
    add constraint time_of_employee_on_order_employee_fk foreign key (id_employee) references employee;
alter table if exists time_of_employee_on_order
    add constraint time_of_employee_on_order_order_fk foreign key (id_order) references order_on_create;
alter table if exists user_role
    add constraint buffer_role_fk foreign key (id_role) references role;
alter table if exists user_role
    add constraint buffer_user_fk foreign key (id_user) references usr;


insert into public.usr (password, username)
values  ('elkin', 'elkin'),
        ('ivanov', 'ivanov'),
        ('petrov', 'petrov'),
        ('sidorov', 'sidorov');

create extension if not exists pgcrypto;
update usr
set password = crypt(password, gen_salt('bf', 8));

insert into public.role (role)
values  ('USER'),
        ('ADMIN');

insert into public.user_role (id_user, id_role)
values  (1, 1),
        (2, 1),
        (3, 1),
        (4, 1),
        (1, 2);

insert into public.baguette (baguette_name)
values  ('фигурный'),
        ('прямой');

insert into public.cutter (cutter_name)
values  ('Пазовая фреза'),
        ('Кромочная фреза');

insert into public.workplace (name_workplace)
values  ('Столярный участок'),
        ('Участок шлифовки'),
        ('Малярный участок'),
        ('Корпусной участок'),
        ('Участок сборки'),
        ('Участок упаковки'),
        ('Участок отгрузки');

insert into public.employee (full_name)
values  ('Алексеев'),
        ('Иванов'),
        ('Петров'),
        ('Сидоров'),
        ('Михайлов'),
        ('Выбегалло'),
        ('Ойра-ойра'),
        ('Амперян'),
        ('Корнеев'),
        ('Привалов'),
        ('Кокоберидзе'),
        ('Катцман'),
        ('Воронин'),
        ('Горчаков');

insert into public.employee_workplace (id_workplace, id_employee)
values  (1, 1),
        (1, 2),
        (2, 3),
        (2, 4),
        (3, 5),
        (3, 6),
        (4, 7),
        (4, 8),
        (5, 9),
        (5, 10),
        (6, 11),
        (6, 12),
        (7, 13),
        (7, 14);
