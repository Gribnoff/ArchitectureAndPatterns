-- table with images
create table image
(
    id   uuid default uuid_generate_v4() not null
        constraint image_pk
            primary key,
    name varchar
);

alter table image
    owner to postgres;

insert into public.image (id, name)
values ('2691f87e-3fb2-4cfc-8160-e4e811458bf4', 'image_not_found.png'),
       ('08b39bd2-5357-43f1-aa3e-d1b9ce028e5e', 'apple.png'),
       ('290679fa-9812-44c6-aa22-df5689d99e14', 'bread.png'),
       ('b85d8706-d663-4311-a15a-fea21e476ee9', 'cheese.png'),
       ('90806402-753e-436b-85c4-cbe84e4644eb', 'donut.png');

-- table with artists
create table artist
(
    id          uuid default uuid_generate_v4() not null
        constraint artist_pk
            primary key,
    name        varchar                         not null,
    photo_id    uuid
        constraint artist_photo_fk
            references image
            on delete cascade,
    description varchar
);

alter table artist
    owner to postgres;

insert into public.artist (id, name, photo_id, description)
values ('2691f87e-3fb2-4cfc-8160-e4e811458bf4', 'unknown artist', '2691f87e-3fb2-4cfc-8160-e4e811458bf4',
        'unknown artist'),
       ('08b39bd2-5357-43f1-aa3e-d1b9ce028e5e', 'apple', '08b39bd2-5357-43f1-aa3e-d1b9ce028e5e', 'apple trio'),
       ('290679fa-9812-44c6-aa22-df5689d99e14', 'bread', '290679fa-9812-44c6-aa22-df5689d99e14', 'bread band'),
       ('b85d8706-d663-4311-a15a-fea21e476ee9', 'cheese', 'b85d8706-d663-4311-a15a-fea21e476ee9', 'cheese brothers'),
       ('90806402-753e-436b-85c4-cbe84e4644eb', 'donut', '90806402-753e-436b-85c4-cbe84e4644eb', 'donut fusion');

-- table with albums
create table album
(
    id        uuid default uuid_generate_v4() not null
        constraint album_pk
            primary key,
    title     varchar                         not null,
    artist_id uuid default '2691f87e-3fb2-4cfc-8160-e4e811458bf4'::uuid,
    year      smallint,
    cover     uuid
        constraint album_image_fk
            references image
            on delete cascade
);

alter table album
    owner to postgres;

insert into public.album (id, title, artist_id, year, cover)
values ('2691f87e-3fb2-4cfc-8160-e4e811458bf4', 'unreleased', '2691f87e-3fb2-4cfc-8160-e4e811458bf4', 0000,
        '2691f87e-3fb2-4cfc-8160-e4e811458bf4'),
       ('08b39bd2-5357-43f1-aa3e-d1b9ce028e5e', 'apple album', '08b39bd2-5357-43f1-aa3e-d1b9ce028e5e', 1994,
        '08b39bd2-5357-43f1-aa3e-d1b9ce028e5e'),
       ('290679fa-9812-44c6-aa22-df5689d99e14', 'bread cassette', '290679fa-9812-44c6-aa22-df5689d99e14', 2000,
        '290679fa-9812-44c6-aa22-df5689d99e14'),
       ('b85d8706-d663-4311-a15a-fea21e476ee9', 'cheese single', 'b85d8706-d663-4311-a15a-fea21e476ee9', 2007,
        'b85d8706-d663-4311-a15a-fea21e476ee9'),
       ('90806402-753e-436b-85c4-cbe84e4644eb', 'donut disk', '90806402-753e-436b-85c4-cbe84e4644eb', 2020,
        '90806402-753e-436b-85c4-cbe84e4644eb'),
       ('90806402-753e-436b-85c4-cbe84e4644ec', 'hard donut', '90806402-753e-436b-85c4-cbe84e4644eb', 2022,
        '90806402-753e-436b-85c4-cbe84e4644eb');

-- table with relation of artist and released albums
create table artist_album
(
    artist_id uuid
        constraint artist_album_artist_fk
            references artist
            on delete cascade,
    album_id uuid
        constraint artist_album_album_fk
            references album
            on delete cascade,
    constraint artist_album_pk
        primary key (artist_id, album_id)
);

insert into public.artist_album (artist_id, album_id)
values ('2691f87e-3fb2-4cfc-8160-e4e811458bf4', '2691f87e-3fb2-4cfc-8160-e4e811458bf4'),
       ('08b39bd2-5357-43f1-aa3e-d1b9ce028e5e', '08b39bd2-5357-43f1-aa3e-d1b9ce028e5e'),
       ('290679fa-9812-44c6-aa22-df5689d99e14', '290679fa-9812-44c6-aa22-df5689d99e14'),
       ('b85d8706-d663-4311-a15a-fea21e476ee9', 'b85d8706-d663-4311-a15a-fea21e476ee9'),
       ('90806402-753e-436b-85c4-cbe84e4644eb', '90806402-753e-436b-85c4-cbe84e4644eb');

-- table with tracks
create table track
(
    id    uuid default uuid_generate_v4() not null
        constraint track_pk
            primary key,
    title varchar                         not null
);

alter table track
    owner to postgres;

insert into public.track (id, title)
values ('2691f87e-3fb2-4cfc-8160-e4e811458bf4', 'unreleased track'),
       ('08b39bd2-5357-43f1-aa3e-d1b9ce028e5e', 'apple track #1'),
       ('290679fa-9812-44c6-aa22-df5689d99e14', 'bread track #1'),
       ('b85d8706-d663-4311-a15a-fea21e476ee9', 'cheese track #1'),
       ('90806402-753e-436b-85c4-cbe84e4644eb', 'donut1 track #1'),
       ('90806402-753e-436b-85c4-cbe84e4644ec', 'donut1 track #2'),
       ('90806402-753e-436b-85c4-cbe84e4644ed', 'donut2 track #1');

-- table that describes the relationship of album and tracks it consists of
create table album_track
(
    album_id uuid not null
        constraint album_track_album_fk
            references album,
    track_id uuid not null
        constraint album_track_track_fk
            references track,
    constraint album_track_pk
        primary key (album_id, track_id)
);

insert into public.album_track (album_id, track_id)
values ('2691f87e-3fb2-4cfc-8160-e4e811458bf4', '2691f87e-3fb2-4cfc-8160-e4e811458bf4'),
       ('08b39bd2-5357-43f1-aa3e-d1b9ce028e5e', '08b39bd2-5357-43f1-aa3e-d1b9ce028e5e'),
       ('290679fa-9812-44c6-aa22-df5689d99e14', '290679fa-9812-44c6-aa22-df5689d99e14'),
       ('b85d8706-d663-4311-a15a-fea21e476ee9', 'b85d8706-d663-4311-a15a-fea21e476ee9'),
       ('90806402-753e-436b-85c4-cbe84e4644eb', '90806402-753e-436b-85c4-cbe84e4644eb'),
       ('90806402-753e-436b-85c4-cbe84e4644eb', '90806402-753e-436b-85c4-cbe84e4644ec'),
       ('90806402-753e-436b-85c4-cbe84e4644ec', '90806402-753e-436b-85c4-cbe84e4644ed');

-- table with genres corresponding to albums
create table album_genre
(
    album_id uuid not null
        constraint album_genre_album_fk
            references album,
    genre varchar not null,
    constraint album_genre_pk
        primary key (album_id, genre)
);

alter table album_genre owner to postgres;

insert into public.album_genre (album_id, genre)
values ('08b39bd2-5357-43f1-aa3e-d1b9ce028e5e', 'apple rock'),
       ('290679fa-9812-44c6-aa22-df5689d99e14', 'bread pop'),
       ('b85d8706-d663-4311-a15a-fea21e476ee9', 'cheese-core'),
       ('90806402-753e-436b-85c4-cbe84e4644eb', 'donut''n''roll'),
       ('90806402-753e-436b-85c4-cbe84e4644ec', 'donut''n''bass');
