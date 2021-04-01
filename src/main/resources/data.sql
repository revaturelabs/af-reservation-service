INSERT INTO room (room_name, room_type, building_id, room_capacity) VALUES
    ('room1', 'virtual', 1, 100),
    ('room2', 'meeting', 1, 15),
    ('room3', 'office', 1, 1);

INSERT INTO reservation (reserver, start_time, end_time, reservation_status, room_id) VALUES
('trainer1@revature.email', 0, 3600, 'reserved', 1),
('trainer2@revature.email', 1200, 4800, 'reserved', 2),
('trainer1@revature.email', 3700, 7300, 'reserved', 1),
('trainer2@revature.email', 7200, 10800, 'reserved', 2),
('trainer1@revature.email', 2000, 4000, 'cancelled', 1),
('trainer2@revature.email', 6000, 8000, 'cancelled', 1);