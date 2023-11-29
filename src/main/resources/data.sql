insert into unicorn (name) values ('Bubbles Starbreeze');

insert into unicorn_part (unicorn_id, leg_position, color, leg_size) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'FRONT_LEFT', 'RASPBERRY', 'LARGE');
insert into unicorn_part (unicorn_id, leg_position, color, leg_size) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'FRONT_RIGHT', 'PEACH', 'LARGE');
insert into unicorn_part (unicorn_id, leg_position, color, leg_size) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'BACK_LEFT', 'PINK', 'LARGE');
insert into unicorn_part (unicorn_id, leg_position, color, leg_size) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'BACK_RIGHT', 'RUBY', 'LARGE');