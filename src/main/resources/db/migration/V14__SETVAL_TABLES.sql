-- Për tabelën users
SELECT setval('users_id_seq', (SELECT COALESCE(MAX(id), 0) + 1 FROM users), false);

-- Për tabelën trips
SELECT setval('trips_id_seq', (SELECT COALESCE(MAX(id), 0) + 1 FROM trips), false);

-- Për tabelën reservations
SELECT setval('reservations_id_seq', (SELECT COALESCE(MAX(id), 0) + 1 FROM reservations), false);

-- Për tabelën favorites
SELECT setval('favorites_id_seq', (SELECT COALESCE(MAX(id), 0) + 1 FROM favorites), false);
