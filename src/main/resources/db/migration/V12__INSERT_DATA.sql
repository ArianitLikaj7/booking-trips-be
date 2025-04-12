
INSERT INTO users (first_name, last_name, username, password, role, email, phone_number, image_url_of_user, id) VALUES
                                                                                                                    ('Ardit', 'Krasniqi', 'ardit.krasniqi', '$2b$12$clSmVdzg/.jRFLMfGvTJIO8tHp.KIhtU1ZHnIepaXXlpLoPnB.zhi', 'USER', 'ardit.krasniqi@example.com', '+355688070603', 'https://randomuser.me/api/portraits/men/1.jpg', 2),
                                                                                                                    ('Elira', 'Berisha', 'elira.berisha', '$2b$12$HzrdGeowjjXKT/iz2Zig8u6.aXV79EvBhpXOll5DkN6YvMV3.AG1u', 'USER', 'elira.berisha@example.com', '+355688613018', 'https://randomuser.me/api/portraits/women/2.jpg', 3),
                                                                                                                    ('Blerim', 'Hoxha', 'blerim.hoxha', '$2b$12$ow5mzXCvvmbGLkT29Jm1je4hR3XE/Lu/dFvFynESbH4BWRe.Imr1.', 'ADMIN', 'blerim.hoxha@example.com', '+355683878737', 'https://randomuser.me/api/portraits/men/3.jpg', 4),
                                                                                                                    ('Gentiana', 'Shehu', 'gentiana.shehu', '$2b$12$UB/JJ0JaCAYLUmCYwF835.TxSLQ9dqKKNqaKuvPkBxHR/PfE.1qnq', 'ADMIN', 'gentiana.shehu@example.com', '+355687918208', 'https://randomuser.me/api/portraits/women/4.jpg', 5),
                                                                                                                    ('Alban', 'Dervishi', 'alban.dervishi', '$2b$12$BEioJYuVs/gnImG3CtR7xunh/yky9ye0dC4G3QQvxy6LPrwF4qKmO', 'ADMIN', 'alban.dervishi@example.com', '+355685011573', 'https://randomuser.me/api/portraits/men/5.jpg', 6),
                                                                                                                    ('Dafina', 'Islami', 'dafina.islami', '$2b$12$iD88IiIeu/ccr5WYCnr8eumstH8FaZ.S7D6v9yAwxmcEVrs5S/rTm', 'ADMIN', 'dafina.islami@example.com', '+355682504350', 'https://randomuser.me/api/portraits/women/6.jpg', 7),
                                                                                                                    ('Luan', 'Musa', 'luan.musa', '$2b$12$f/cFUsJYwMTBhHfy38/yhep2RaTAJDecaVTwBfEyd5Edb09/638We', 'ADMIN', 'luan.musa@example.com', '+355684362454', 'https://randomuser.me/api/portraits/men/7.jpg', 8),
                                                                                                                    ('Flutura', 'Gashi', 'flutura.gashi', '$2b$12$EVFgZRhDVrFUmH5Zg/cE8uanOhsdbfeqGRrvbgtVesAR5cli3cfYK', 'USER', 'flutura.gashi@example.com', '+355685477429', 'https://randomuser.me/api/portraits/women/8.jpg', 9),
                                                                                                                    ('Erion', 'Rama', 'erion.rama', '$2b$12$5XZOgI73tVyyU0iiPSwk8eY2lLfEJ8Uqb9pZpTT5MifigS1z.fSQu', 'USER', 'erion.rama@example.com', '+355684012012', 'https://randomuser.me/api/portraits/men/9.jpg', 10),
                                                                                                                    ('Arbana', 'Meta', 'arbana.meta', '$2b$12$7caZo5Bmu2S7gPCesSpXfem.aLlcZnvYZWNVb7qtF7FLH8OVg7FnS', 'ADMIN', 'arbana.meta@example.com', '+355682471791', 'https://randomuser.me/api/portraits/women/10.jpg', 11);

INSERT INTO trips (id, created_by, origin, destination, available_seats, total_seats, route, price, title, description, type_of_trip, company_name, local_date_time) VALUES
                                                                                                                                                                         (1, 3, 'Gjakovë', 'Tiranë', 1, 11, 'Gjakovë - Tiranë', 29.93, 'Pushime', 'Udhëtim nga Gjakovë në Tiranë me Albanian Travel', 'One-way', 'Albanian Travel', '2024-11-06 13:25:14'),
                                                                                                                                                                         (2, 5, 'Elbasan', 'Durrës', 2, 27, 'Elbasan - Durrës', 86.81, 'Shëtitje', 'Udhëtim nga Elbasan në Durrës me EuroTrip', 'One-way', 'EuroTrip', '2024-10-10 13:25:14'),
                                                                                                                                                                         (3, 4, 'Prishtinë', 'Gjakovë', 1, 16, 'Prishtinë - Gjakovë', 13.91, 'Shëtitje', 'Udhëtim nga Prishtinë në Gjakovë me Kosova Tours', 'One-way', 'Kosova Tours', '2024-12-12 13:25:14'),
                                                                                                                                                                         (4, 10, 'Elbasan', 'Berat', 16, 31, 'Elbasan - Berat', 26.56, 'Shëtitje', 'Udhëtim nga Elbasan në Berat me Kosova Tours', 'One-way', 'Kosova Tours', '2024-09-03 13:25:14'),
                                                                                                                                                                         (5, 7, 'Pejë', 'Berat', 15, 44, 'Pejë - Berat', 21.78, 'Shëtitje', 'Udhëtim nga Pejë në Berat me Balkan Express', 'One-way', 'Balkan Express', '2024-11-16 13:25:14'),
                                                                                                                                                                         (6, 4, 'Durrës', 'Pejë', 6, 22, 'Durrës - Pejë', 49.67, 'Vizitë familjare', 'Udhëtim nga Durrës në Pejë me EuroTrip', 'One-way', 'EuroTrip', '2024-11-15 13:25:14'),
                                                                                                                                                                         (7, 1, 'Berat', 'Tetovë', 23, 41, 'Berat - Tetovë', 17.95, 'Udhëtim pune', 'Udhëtim nga Berat në Tetovë me Albanian Travel', 'One-way', 'Albanian Travel', '2024-06-11 13:25:14'),
                                                                                                                                                                         (8, 10, 'Berat', 'Vlorë', 15, 25, 'Berat - Vlorë', 18.44, 'Pushime', 'Udhëtim nga Berat në Vlorë me EuroTrip', 'One-way', 'EuroTrip', '2025-03-24 13:25:14'),
                                                                                                                                                                         (9, 5, 'Pejë', 'Durrës', 34, 35, 'Pejë - Durrës', 96.92, 'Pushime', 'Udhëtim nga Pejë në Durrës me Balkan Express', 'One-way', 'Balkan Express', '2025-02-08 13:25:14'),
                                                                                                                                                                         (10, 2, 'Elbasan', 'Berat', 6, 35, 'Elbasan - Berat', 17.97, 'Udhëtim pune', 'Udhëtim nga Elbasan në Berat me Albanian Travel', 'One-way', 'Albanian Travel', '2025-02-03 13:25:14');

INSERT INTO trip_images (trip_id, image_url) VALUES
                                                 (1, 'https://cdn.konica.al/wp-content/uploads/2024/02/shutterstock_1549882628-750x375.jpg'),
                                                 (2, 'https://cdn.konica.al/wp-content/uploads/2024/02/shutterstock_1178671877-750x375.jpg'),
                                                 (3, 'https://cdn.konica.al/wp-content/uploads/2024/02/shutterstock_1542964233-750x375.jpg'),
                                                 (4, 'https://cdn.konica.al/wp-content/uploads/2024/02/shutterstock_1163519850-750x375.jpg'),
                                                 (5, 'https://cdn.konica.al/wp-content/uploads/2024/02/shutterstock_1736326544-750x375.jpg'),
                                                 (6, 'https://cdn.konica.al/wp-content/uploads/2024/02/shutterstock_1103642918-750x375.jpg'),
                                                 (7, 'https://cdn.konica.al/wp-content/uploads/2024/02/shutterstock_1608812102-750x375.jpg'),
                                                 (8, 'https://cdn.konica.al/wp-content/uploads/2024/02/shutterstock_1015748036-750x375.jpg'),
                                                 (9, 'https://cdn.konica.al/wp-content/uploads/2024/02/shutterstock_1516673968-750x375.jpg'),
                                                 (10, 'https://cdn.konica.al/wp-content/uploads/2024/02/shutterstock_1891680963-750x375.jpg');

INSERT INTO reservations (id, user_id, trip_id, seat_number, reserved_for, created_at, updated_at) VALUES
                                                                                                       (1, 7, 1, 7, 'Gentiana Berisha', '2024-11-06 13:25:14', '2024-11-06 13:25:14'),
                                                                                                       (2, 6, 1, 8, 'Luan Shehu', '2024-11-06 13:25:14', '2024-11-06 13:25:14'),
                                                                                                       (3, 4, 2, 4, 'Flutura Gashi', '2024-10-10 13:25:14', '2024-10-10 13:25:14'),
                                                                                                       (4, 9, 3, 1, 'Flutura Krasniqi', '2024-12-12 13:25:14', '2024-12-12 13:25:14'),
                                                                                                       (5, 2, 3, 14, 'Elira Krasniqi', '2024-12-12 13:25:14', '2024-12-12 13:25:14'),
                                                                                                       (6, 4, 4, 27, 'Arbana Shehu', '2024-09-03 13:25:14', '2024-09-03 13:25:14'),
                                                                                                       (7, 6, 4, 27, 'Flutura Islami', '2024-09-03 13:25:14', '2024-09-03 13:25:14'),
                                                                                                       (8, 2, 5, 11, 'Arbana Musa', '2024-11-16 13:25:14', '2024-11-16 13:25:14'),
                                                                                                       (9, 10, 5, 26, 'Dafina Rama', '2024-11-16 13:25:14', '2024-11-16 13:25:14'),
                                                                                                       (10, 8, 6, 15, 'Elira Berisha', '2024-11-15 13:25:14', '2024-11-15 13:25:14'),
                                                                                                       (11, 8, 6, 1, 'Arbana Shehu', '2024-11-15 13:25:14', '2024-11-15 13:25:14'),
                                                                                                       (12, 9, 7, 39, 'Blerim Dervishi', '2024-06-11 13:25:14', '2024-06-11 13:25:14'),
                                                                                                       (13, 9, 8, 12, 'Blerim Rama', '2025-03-24 13:25:14', '2025-03-24 13:25:14'),
                                                                                                       (14, 4, 8, 18, 'Dafina Islami', '2025-03-24 13:25:14', '2025-03-24 13:25:14'),
                                                                                                       (15, 10, 9, 12, 'Blerim Rama', '2025-02-08 13:25:14', '2025-02-08 13:25:14'),
                                                                                                       (16, 6, 9, 13, 'Blerim Musa', '2025-02-08 13:25:14', '2025-02-08 13:25:14'),
                                                                                                       (17, 1, 10, 30, 'Dafina Berisha', '2025-02-03 13:25:14', '2025-02-03 13:25:14'),
                                                                                                       (18, 1, 10, 32, 'Erion Shehu', '2025-02-03 13:25:14', '2025-02-03 13:25:14');

INSERT INTO favorites (id, user_id, trip_id, created_at) VALUES
                                                             (1, 7, 1, '2024-11-06 13:25:14'),
                                                             (2, 3, 2, '2024-10-10 13:25:14'),
                                                             (3, 4, 3, '2024-12-12 13:25:14'),
                                                             (4, 2, 4, '2024-09-03 13:25:14'),
                                                             (5, 2, 5, '2024-11-16 13:25:14'),
                                                             (6, 8, 6, '2024-11-15 13:25:14'),
                                                             (7, 3, 7, '2024-06-11 13:25:14'),
                                                             (8, 6, 8, '2025-03-24 13:25:14'),
                                                             (9, 3, 9, '2025-02-08 13:25:14'),
                                                             (10, 1, 10, '2025-02-03 13:25:14');
