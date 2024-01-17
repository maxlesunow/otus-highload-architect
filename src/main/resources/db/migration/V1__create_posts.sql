create table posts
(
    id              uuid    not null,
    text            text    not null,
    author_user_id  uuid    not null
);

ALTER TABLE posts
    ADD CONSTRAINT fk_author_user FOREIGN KEY (author_user_id) REFERENCES users (id);

comment on table posts is 'Хранит посты пользователей социальной сети';

comment on column posts.id is 'Идентификатор поста';

comment on column posts.text is 'Текст';

comment on column posts.author_user_id is 'Автор поста';
