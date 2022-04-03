create table CLIENT
(
    id       serial primary key,
    name     varchar(128) not null,
    password bytea        not null,
    salt     bytea        not null
);