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