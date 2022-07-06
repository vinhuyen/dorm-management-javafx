create database ktx_db;
use ktx_db;

create table rooms
(
    id       int auto_increment
        primary key,
    name     varchar(100) not null,
    capacity int unsigned not null
);

create table members
(
    id        int auto_increment
        primary key,
    full_name varchar(250)                       not null,
    room_id   int                                not null,
    joined_at datetime default CURRENT_TIMESTAMP null,
    constraint members_ibfk_1
        foreign key (room_id) references rooms (id)
);

create index room_id
    on members (room_id);

