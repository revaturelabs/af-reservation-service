INSERT INTO room (room_name, room_type, bld_id, capacity)
VALUES ('room1', 'virtual', 1, 100),
       ('room2', 'meeting', 1, 15),
       ('room3', 'office', 1, 1);

INSERT INTO reservation (reserver, reservation_title, start_time, end_time, reservation_status, room_id)
VALUES ('trainer1@revature.email', 'First Meeting', 0, 3600, 'reserved', 1),
       ('trainer2@revature.email', 'Room 2 Meeting', 1200, 4800, 'reserved', 2),
       ('trainer1@revature.email', 'Second Meeting', 3700, 7300, 'reserved', 1),
       ('trainer2@revature.email', 'My meeting', 7200, 10800, 'reserved', 2),
       ('trainer1@revature.email', 'This meeting was cancelled', 2000, 4000, 'cancelled', 1),
       ('trainer2@revature.email', 'This meeting was also cancelled', 6000, 8000, 'cancelled', 1);