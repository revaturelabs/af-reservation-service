DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS room;

CREATE TABLE room
(
    room_id       INTEGER AUTO_INCREMENT,
    room_name     VARCHAR(200) NOT NULL,
    room_type     VARCHAR(200) NOT NULL,
    building_id   INTEGER      NOT NULL,
    room_capacity INTEGER      NOT NULL,
    PRIMARY KEY (room_id)
);

CREATE TABLE reservation
(
    reservation_id     INTEGER AUTO_INCREMENT,
    reserver           VARCHAR(200) NOT NULL,
    start_time         INTEGER      NOT NULL,
    end_time           INTEGER      NOT NULL,
    reservation_status VARCHAR(50)  NOT NULL,
    room_id            INTEGER      NOT NULL,
    PRIMARY KEY (reservation_id),
    CONSTRAINT reservation_room FOREIGN KEY (room_id) REFERENCES room (room_id)
);