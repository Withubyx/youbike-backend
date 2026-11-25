INSERT INTO bike_ride_organizers (id, name, name_lowercase)
VALUES (100001, 'Fast Bikes', 'fast bikes');
INSERT INTO bike_ride_organizers (id, name, name_lowercase)
VALUES (100002, 'Bikes and Beer Cafe', 'bikes and beer cafe');
INSERT INTO bike_ride_organizers (id, name, name_lowercase)
VALUES (100003, 'Bike Service', 'bike service');
INSERT INTO bike_ride_organizers (id, name, name_lowercase)
VALUES (100004, 'Jerry', 'jerry');
INSERT INTO bike_ride_organizers (id, name, name_lowercase)
VALUES (100005, 'Susan Speed', 'susan speed');
INSERT INTO bike_ride_organizers (id, name, name_lowercase)
VALUES (100006, 'Joep Leopard', 'joep leopard');
INSERT INTO bike_ride_organizers (id, name, name_lowercase)
VALUES (100007, 'Mountain Goat', 'mountain goat');

INSERT INTO bike_companies (id, bike_company_type, street, house_number, city, country_code)
VALUES (100001, 0, 'Spoorlaan', '4', 'Utrecht', 0);
INSERT INTO bike_companies (id, bike_company_type, street, house_number, city, country_code)
VALUES (100002, 2, 'KapelStraat', '17', 'Brugge', 1);
INSERT INTO bike_companies (id, bike_company_type, street, house_number, city, country_code)
VALUES (100003, 1, 'Dorfallee', '11', 'Dusseldorf', 2);

INSERT INTO cyclists (id, bike_image_upload_count, city, country_code)
VALUES (100004, 0, 'Heerenveen', 0);
INSERT INTO cyclists (id, bike_image_upload_count, city, country_code)
VALUES (100005, 0, 'Arnhem', 0);
INSERT INTO cyclists (id, bike_image_upload_count, city, country_code)
VALUES (100006, 5, 'Gent', 1);
INSERT INTO cyclists (id, bike_image_upload_count, city, country_code)
VALUES (100007, 3, 'Bad Bentheim', 2);

INSERT INTO users (id, email, email_lowercase, password, role, cyclist_id, bike_company_id)
VALUES (100001, 'FastBikes@abc.com', 'fastbikes@abc.com', '$2a$10$Ig9L5kDOSDLk6pVntc9MFOylHxD7UquwfREdtVjczQQ8OsQLjQ84.', 0, NULL, 100001);
INSERT INTO users (id, email, email_lowercase, password, role, cyclist_id, bike_company_id)
VALUES (100002, 'BikesAndBeer@abc.com', 'bikesandbeer@abc.com', '$2a$10$T8lNaA/QnG2kb11WcQhDcOzuKQS.S0cV83esejeb3xle.8nleWRpG', 0, NULL, 100002);
INSERT INTO users (id, email, email_lowercase, password, role, cyclist_id, bike_company_id)
VALUES (100003, 'BikeService@abc.com', 'bikeservice@abc.com', '$2a$10$HGucbT.z7TgGutmYVbdiDemEmVl1sX2O1Ul/v/JPopQrk8QEUakK.', 0, NULL, 100003);
INSERT INTO users (id, email, email_lowercase, password, role, cyclist_id, bike_company_id)
VALUES (100004, 'Jerry@abc.com', 'jerry@abc.com', '$2a$10$93njB1Uq6HvPF47aTjR2Au8S6gA.EebOeYyGiOFiYLzMMyBmrz.iC', 1, 100004, NULL);
INSERT INTO users (id, email, email_lowercase, password, role, cyclist_id, bike_company_id)
VALUES (100005, 'Susan@abc.com', 'susan@abc.com', '$2a$10$0lUtHuTpucFGBfrLkiTyx.QhZP6H.GysFi.ZivNJb/85kSc6lpePC', 1, 100005, NULL);
INSERT INTO users (id, email, email_lowercase, password, role, cyclist_id, bike_company_id)
VALUES (100006, 'Joep@abc.com', 'joep@abc.com', '$2a$10$USw8nAsnwDIU6N5J/Qiav.xvuVZsElYW4taKC2ggO/HyEazN/3MSS', 1, 100006, NULL);
INSERT INTO users (id, email, email_lowercase, password, role, cyclist_id, bike_company_id)
VALUES (100007, 'Goat@abc.com', 'goat@abc.com', '$2a$10$Z/TwDe2EBBo65hDRb2uxc.Ta7ka5POvgyFMtmGt9r6jP/dEShyElS', 1, 100007, NULL);

INSERT INTO bike_rides (id, title, start_date_time, description, distance, speed, street, house_number, city, country_code, organizer_id)
VALUES (100001, 'Recovery Training', '2027-03-08 10:00:00', 'Let the blood flow through the muscles', 80, 4, 'Kirchstrasse', '1', 'Dusseldorf', 2, 100003);
INSERT INTO bike_rides (id, title, start_date_time, description, distance, speed, street, house_number, city, country_code, organizer_id)
VALUES (100002, 'Cobblestones Hills spaghetti', '2027-03-11 09:30:00', 'Thrilling ride', 100, 3, 'Jaarmarktstraat', '9', 'Gent', 1, 100006);
INSERT INTO bike_rides (id, title, start_date_time, description, distance, speed, street, house_number, city, country_code, organizer_id)
VALUES (100003, 'Through Flanders Fields', '2027-03-12 08:00:00', 'WW1 historic places', 140, 5, 'Korenmarkt', '22', 'Wevelgem', 1, 100006);
INSERT INTO bike_rides (id, title, start_date_time, description, distance, speed, street, house_number, city, country_code, organizer_id)
VALUES (100004, 'IJsselmeer Tour', '2027-03-14 11:00:00', NULL, 180, 7, 'Haven', '4', 'Volendam', 0, 100007);

INSERT INTO bike_rides_surface_types (bike_ride_id, surface_type)
VALUES (100001, 'PAVED');
INSERT INTO bike_rides_surface_types (bike_ride_id, surface_type)
VALUES (100002, 'COBBLESTONES');
INSERT INTO bike_rides_surface_types (bike_ride_id, surface_type)
VALUES (100002, 'PAVED');
INSERT INTO bike_rides_surface_types (bike_ride_id, surface_type)
VALUES (100003, 'GRAVEL');
INSERT INTO bike_rides_surface_types (bike_ride_id, surface_type)
VALUES (100004, 'PAVED');

INSERT INTO bike_images (id, file_name)
VALUES (100001, '100006-1-cervelo-(data_sql_used_file).jpeg');
INSERT INTO bike_images (id, file_name)
VALUES (100002, '100006-2-gios-(data_sql_used_file).jpeg');
INSERT INTO bike_images (id, file_name)
VALUES (100003, '100006-3-ridley-(data_sql_used_file).jpeg');
INSERT INTO bike_images (id, file_name)
VALUES (100004, '100006-4-rose-(data_sql_used_file).jpeg');
INSERT INTO bike_images (id, file_name)
VALUES (100005, '100006-5-pennyf-(data_sql_used_file).jpeg');
INSERT INTO bike_images (id, file_name)
VALUES (100006, '100007-1-specialized-(data_sql_used_file).jpeg');
INSERT INTO bike_images (id, file_name)
VALUES (100007, '100007-2-giant-(data_sql_used_file).jpeg');
INSERT INTO bike_images (id, file_name)
VALUES (100008, '100007-3-trek-(data_sql_used_file).jpeg');

INSERT INTO bikes (id, bike_type, brand, model, color, description, owner_id, bike_image_id)
VALUES (100001, 0, 'Cervelo', 'Aero', 'Black', 'Aero Bullet', 100006, 100001);
INSERT INTO bikes (id, bike_type, brand, model, color, description, owner_id, bike_image_id)
VALUES (100002, 0, 'Gios', 'Torino', 'Blue', 'Classic Beauty', 100006, 100002);
INSERT INTO bikes (id, bike_type, brand, model, color, description, owner_id, bike_image_id)
VALUES (100003, 1, 'Ridley', 'Cross 2.0', 'Black', 'Winter Bike', 100006, 100003);
INSERT INTO bikes (id, bike_type, brand, model, color, description, owner_id, bike_image_id)
VALUES (100004, 2, 'Rose', 'Flyer', 'Light Blue', 'My bike to discover off-road places', 100006, 100004);
INSERT INTO bikes (id, bike_type, brand, model, color, description, owner_id, bike_image_id)
VALUES (100005, 4, 'PennyF', 'Cruiser', 'Green', 'Head Turner', 100006, 100005);
INSERT INTO bikes (id, bike_type, brand, model, color, description, owner_id, bike_image_id)
VALUES (100006, 0, 'Specialized', 'S-works', 'red', 'Red Beauty', 100007, 100006);
INSERT INTO bikes (id, bike_type, brand, model, color, description, owner_id, bike_image_id)
VALUES (100007, 2, 'Giant', 'Discover', 'Metallic Green', 'Dirt Bike', 100007, 100007);
INSERT INTO bikes (id, bike_type, brand, model, color, description, owner_id, bike_image_id)
VALUES (100008, 3, 'Trek', 'Rocker', 'Red', NULL, 100007, 100008);

INSERT INTO bike_comments (id, comment, created_date_time, updated_date_time, author_id, bike_id)
VALUES (100001, 'Nice slim old school bike! Where did you buy it?', '2026-11-20 09:11:00', '2026-11-20 09:21:00', 100007, 100002);
INSERT INTO bike_comments (id, comment, created_date_time, updated_date_time, author_id, bike_id)
VALUES (100002, 'How much pressure do you put in the tires?', '2026-11-21 19:50:00', '2026-11-21 19:50:00', 100007, 100003);
INSERT INTO bike_comments (id, comment, created_date_time, updated_date_time, author_id, bike_id)
VALUES (100003, 'On which terrains can you use this bike off-road?', '2026-11-21 22:17:00', '2026-11-21 22:17:00', 100007, 100004);
INSERT INTO bike_comments (id, comment, created_date_time, updated_date_time, author_id, bike_id)
VALUES (100004, 'You must not be afraid of heights', '2026-11-21 22:44:00', '2026-11-21 22:53:00', 100007, 100005);
INSERT INTO bike_comments (id, comment, created_date_time, updated_date_time, author_id, bike_id)
VALUES (100005, '4 Bar', '2026-11-22 14:07:00', '2026-11-22 14:07:00', 100006, 100003);
INSERT INTO bike_comments (id, comment, created_date_time, updated_date_time, author_id, bike_id)
VALUES (100006, 'Love the color', '2026-11-22 18:01:00', '2026-11-22 18:01:00', 100006, 100007);