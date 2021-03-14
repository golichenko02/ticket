CREATE TABLE journey
(
    id           SERIAL PRIMARY KEY,
    station_from VARCHAR(100) NOT NULL,
    station_to   VARCHAR(100) NOT NULL,
    departure    DATE         NOT NULL,
    arrival      DATE         NOT NULL,
    route        VARCHAR(200) NOT NULL
);

INSERT INTO journey (station_from, station_to, departure, arrival, route)
VALUES ('Odessa', 'Lviv', '2021 - 03 - 25', '2021 - 03 - 26', 'Odessa -> Lviv'),
       ('Odessa', 'Kyiv', '2021-03-27', '2021-03-28', 'Odessa -> Kyiv'),
       ('Kyiv', 'Lviv', '2021-03-23', '2021-03-24', 'Kyiv -> Lviv'),
       ('Kyiv', 'Lviv', '2021-03-25', '2021-03-26', 'Kyiv -> Lviv'),
       ('Kyiv', 'Lviv', '2021-04-14', '2021-04-15', 'Kyiv -> Lviv');