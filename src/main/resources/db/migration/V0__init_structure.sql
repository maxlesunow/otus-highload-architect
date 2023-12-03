create table users
(
    id          uuid    not null,
    first_name  text    not null,
    second_name text    not null,
    birthdate   date,
    biography   text,
    city        text,
    password    text    not null
);

comment on table users is 'Хранит пользователей социальной сети';

comment on column users.id is 'Идентификатор пользователя';

comment on column users.first_name is 'Имя';

comment on column users.second_name is 'Фамилия';

comment on column users.birthdate is 'Дата рождения';

comment on column users.biography is 'Интересы';

comment on column users.city is 'Город';

comment on column users.password is 'Секретная строка';
