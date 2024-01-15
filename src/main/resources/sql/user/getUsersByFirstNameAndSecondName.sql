select *
from users
where upper(first_name) LIKE :firstName AND upper(second_name) LIKE :secondName
order by id;