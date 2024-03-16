insert into posts_feeds
select :post_id, user_id, :post_created_at
from friends
where friend_user_id = :post_author_user_id