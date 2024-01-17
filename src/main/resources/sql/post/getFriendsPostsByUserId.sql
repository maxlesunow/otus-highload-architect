select * from posts
where author_user_id in (select friend_user_id from friends where user_id = :userId)
offset :offset
limit :limit