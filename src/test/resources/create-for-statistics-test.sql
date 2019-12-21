delete from user_info;
delete from statistics;
delete from users;

insert into users (id,  password, username) values
(1, 'a', 'a'),
(2, 'b', 'b');

insert into user_info (id, first_name, last_name, height, weight, user_id) values
(1, 'A', 'A', 1.62, 55, 1),
(2, 'B', 'B', 1.62, 66, 2);


insert into statistics(id, date, weight, height, owner) values
(1, '2019-12-17', 55, 1.62, 1),
(2, '2019-12-18', 56, 1.62, 1),
(3, '2019-12-20', 57, 1.62, 1);