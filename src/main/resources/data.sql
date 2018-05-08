/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');



insert into vehicle (booking_status, color, date_created, deleted, license_plate, convertible, engine_type, name, seat_count, manufacturer_name, rating, id) values ('UNBOOK', 'RED', now(), false, 'WB04555', false, 'ELECTRIC', 'BMWi23', 3, 'BMW', 8, 10023 );


insert into vehicle (booking_status, color, date_created, deleted, license_plate, convertible, engine_type, name, seat_count, manufacturer_name, rating, id) values ('UNBOOK', 'BLUE', now(), false, 'PN04551', false, 'PETROL', 'honda city', 3, 'Honda', 8, 10024 );


insert into vehicle (booking_status, color, date_created, deleted, license_plate, convertible, engine_type, name, seat_count, manufacturer_name, rating, id) values ('UNBOOK', 'BLACK', now(), false, 'KA04555', false, 'GAS', 'maruti 800', 3, 'Maruti', 7, 10025);---- Create 1 Manufacturer

-- Creating Users for Implementing Security
INSERT INTO User (id, date_created, username, password, enabled) VALUES (1, now(), 'Alex123','$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', true);
INSERT INTO User (id, date_created, username, password, enabled) VALUES (2, now(), 'Tom234', '$2a$04$PCIX2hYrve38M7eOcqAbCO9UqjYg7gfFNpKsinAxh99nms9e.8HwK', true);
INSERT INTO User (id, date_created, username, password, enabled) VALUES (3, now(), 'Adam', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', true);
