alter table if exists employee_workplace
    drop constraint if exists buffer_employee_fk;
alter table if exists employee_workplace
    drop constraint if exists buffer_workplace_fk;

alter table if exists order_baguette
    drop constraint if exists buffer1_baguette_fk;
alter table if exists order_baguette
    drop constraint if exists buffer1_order_fk;
alter table if exists order_cutter
    drop constraint if exists buffer2_cutter_fk;
alter table if exists order_cutter
    drop constraint if exists buffer2_order_fk;

alter table technological_process
    drop constraint technological_process_employee_fk;
alter table technological_process
    drop constraint technological_process_order_fk;
alter table technological_process
    drop constraint technological_process_workplace_fk;

alter table if exists user_role
    drop constraint if exists buffer_role_fk;
alter table if exists user_role
    drop constraint if exists buffer_user_fk;

drop table if exists baguette cascade;
drop table if exists cutter cascade;
drop table if exists employee cascade;
drop table if exists employee_workplace cascade;
drop table if exists order_baguette cascade;
drop table if exists order_cutter cascade;
drop table if exists order_on_create cascade;
drop table if exists role cascade;
drop table if exists technological_process cascade;
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

create table order_baguette
(
    id_order    int8 not null,
    id_baguette int8 not null,
    primary key (id_baguette, id_order)
);

create table order_cutter
(
    id_order  int8 not null,
    id_cutter int8 not null,
    primary key (id_cutter, id_order)
);

create table order_on_create
(
    id                 int8 generated by default as identity,
    binding            varchar(255),
    color              varchar(255),
    duty               varchar(255),
    finish_date        timestamp,
    glass              varchar(255),
    notes              varchar(2048),
    number_order       varchar(255),
    number_order_other varchar(255),
    radius             varchar(255),
    start_date         timestamp,
    wood_mass          float4,
    wood_veneer        float4,
    primary key (id)
);

create table role
(
    id   int8 generated by default as identity,
    role varchar(255),
    primary key (id)
);

create table technological_process
(
    id               bigserial not null,
    operation_code   int4,
    time_finish_work timestamp,
    time_start_work  timestamp,
    id_employee      int8,
    id_order         int8,
    id_workplace     int8,
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
    sequence       int4,
    primary key (id)
);

alter table usr
    add constraint UK_usr_username unique (username);

alter table if exists employee_workplace
    add constraint buffer_employee_fk foreign key (id_employee) references employee;
alter table if exists employee_workplace
    add constraint buffer_workplace_fk foreign key (id_workplace) references workplace;

alter table order_baguette
    add constraint buffer1_baguette_fk foreign key (id_baguette) references baguette;
alter table order_baguette
    add constraint buffer1_order_fk foreign key (id_order) references order_on_create;

alter table order_cutter
    add constraint buffer2_cutter_fk foreign key (id_cutter) references baguette;
alter table order_cutter
    add constraint buffer2_order_fk foreign key (id_order) references order_on_create;

alter table technological_process
    add constraint technological_process_employee_fk foreign key (id_employee) references employee;
alter table technological_process
    add constraint technological_process_order_fk foreign key (id_order) references order_on_create;
alter table technological_process
    add constraint technological_process_workplace_fk foreign key (id_workplace) references workplace;

alter table if exists user_role
    add constraint buffer_role_fk foreign key (id_role) references role;
alter table if exists user_role
    add constraint buffer_user_fk foreign key (id_user) references usr;


insert into public.usr (password, username)
values ('elkin', 'elkin'),
       ('ivanov', 'ivanov'),
       ('petrov', 'petrov'),
       ('sidorov', 'sidorov');

create extension if not exists pgcrypto;
update usr
set password = crypt(password, gen_salt('bf', 8));

insert into public.role (role)
values ('USER'),
       ('ADMIN');

insert into public.user_role (id_user, id_role)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (1, 2);

insert into public.baguette (baguette_name)
values ('фигурный'),
       ('прямой');

insert into public.cutter (cutter_name)
values ('Пазовая фреза'),
       ('Кромочная фреза');

insert into public.workplace (name_workplace)
values ('Столярный участок'),
       ('Участок шлифовки'),
       ('Малярный участок'),
       ('Корпусной участок'),
       ('Участок сборки'),
       ('Участок упаковки'),
       ('Участок отгрузки');

insert into public.employee (full_name)
values ('Алексеев'),
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
values (1, 1),
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

insert into public.order_on_create (number_order, number_order_other, start_date, finish_date, duty, color, wood_mass,
                                    wood_veneer, radius, glass, binding, notes)
values ('1111a', '11sdfgs', '2022-01-05', '2022-02-05', 5000, 'синий', 5.58, 1.68, 3, 'стекло заказчика', 'нет',
        'детская кроватка двухярусная из мареного дуба с золотыми вставками'),
       ('2222b', '22sdfgs', '2022-01-01', '2022-02-02', 15000, 'серый', 9.58, 8.68, 5, 'стекло заказчика', 'нет',
        'шкаф'),
       ('3333c', '33sdfgs', '2022-03-15', '2022-05-21', 5482, 'голубой', 5.58, 2.68, 4, 'стекло зеленое', 'да',
        'кровать'),
       ('4444d', '44sdfgs', '2022-06-08', '2022-07-28', 8659878, 'коричневый', 1.58, 0.68, 9, 'нужно стекло',
        'да',
        'диван'),
       ('5555e', '55sdfgs', '2022-04-19', '2022-05-02', 50, 'черный', 0.58, 1.68, 1, 'стекло заказчика', 'нет',
        'заказчик козел');

insert into public.order_cutter (id_order, id_cutter)
values (1, 1),
       (2, 2),
       (3, 1),
       (4, 2),
       (5, 1);

insert into public.order_baguette (id_order, id_baguette)
values (1, 1),
       (2, 1),
       (3, 2),
       (4, 2),
       (5, 2);

insert into public.technological_process (id_order, id_workplace, id_employee, time_start_work, time_finish_work, operation_code)
values (1, 1, 1, '2022-08-19 09:36:49.000000', '2022-08-20 09:36:49.000000', 101),
       (1, 2, 3, '2022-08-18 09:36:49.000000', '2022-08-20 09:36:49.000000', 201),
       (2, 1, 1, '2022-08-19 09:36:49.000000', '2022-08-20 09:36:49.000000', 101),
       (2, 5, 9, '2022-08-15 09:36:49.000000', '2022-08-20 09:36:49.000000', 501),
       (3, 2, 4, '2022-08-14 09:36:49.000000', '2022-08-20 09:36:49.000000', 201),
       (3, 3, 5, '2022-08-15 09:36:49.000000', '2022-08-20 09:36:49.000000', 301),
       (4, 3, 6, '2022-08-01 09:36:49.000000', '2022-08-20 09:36:49.000000', 301),
       (4, 4, 7, '2022-07-01 09:36:49.000000', '2022-08-20 09:36:49.000000', 401),
       (5, 4, 8, '2022-06-01 09:36:49.000000', '2022-08-20 09:36:49.000000', 401),
       (5, 6, 12, '2022-05-01 09:36:49.000000', '2022-08-20 09:36:49.000000', 601);