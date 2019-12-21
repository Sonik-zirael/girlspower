delete from user_info;
delete from users;

insert into users (id,  password, username) values
(1, 'a', 'a'),
(2, 'b', 'b');

insert into user_info (id, first_name, last_name, height, weight, aim, user_id) values
(1, 'A', 'A', 1.62, 55, null, 1),
(2, 'B', 'B', 1.62, 67, 60, 2);
