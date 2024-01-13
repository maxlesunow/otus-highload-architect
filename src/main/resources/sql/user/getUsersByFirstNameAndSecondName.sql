select *
from users
where first_name LIKE :firstName AND second_name LIKE :secondName;