create table friends
(
    user_id         uuid    not null,
    friend_user_id  uuid    not null
);

ALTER TABLE friends
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE friends
    ADD CONSTRAINT fk_friend_user_id  FOREIGN KEY (friend_user_id) REFERENCES users (id);

comment on table friends is 'Хранит друзей социальной сети';

comment on column friends.user_id is 'Идентификатор пользователя';

comment on column friends.friend_user_id is 'Идентификатор друга';