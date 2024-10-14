insert into unicorn (unicorn_uuid, name) values ('ffffffff-ffff-ffff-ffff-ffffffffffff', 'Bubbles Starbreeze');

insert into unicorn_leg (unicorn_id, leg_position, color, leg_size) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'FRONT_LEFT', 'PURPLE', 'LARGE');
insert into unicorn_leg (unicorn_id, leg_position, color, leg_size) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'FRONT_RIGHT', 'PURPLE', 'LARGE');
insert into unicorn_leg (unicorn_id, leg_position, color, leg_size) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'BACK_LEFT', 'GREY', 'LARGE');
insert into unicorn_leg (unicorn_id, leg_position, color, leg_size) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'BACK_RIGHT', 'PURPLE', 'SMALL');