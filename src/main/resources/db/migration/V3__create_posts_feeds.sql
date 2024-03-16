create table posts_feeds
(
    post_id                 uuid            not null,
    user_id                 uuid            not null,
    post_created_at         timestamptz     not null
);

ALTER TABLE posts_feeds
    ADD CONSTRAINT fk_posts_feeds_user_id FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE posts_feeds
    ADD CONSTRAINT fk_posts_feeds_post_id FOREIGN KEY (post_id) REFERENCES posts (id);

comment on table posts_feeds is 'Хранит ленту постов пользователей социальной сети';

comment on column posts_feeds.user_id is 'Идентификатор пользователя';

comment on column posts_feeds.post_id is 'Идентификатор поста';

comment on column posts_feeds.post_created_at is 'Время создания поста';