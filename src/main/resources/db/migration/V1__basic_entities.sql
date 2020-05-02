-- table with images
create table image
(
    id   bigint not null
        constraint image_pk
            primary key,
    name varchar
);

alter table image
    owner to postgres;

insert into public.image (id, name)
values (0, 'image_not_found.png'),
       (1, 'apple.png'),
       (2, 'bread.png'),
       (3, 'cheese.png'),
       (4, 'donut.png');

-- table with artists
create table artist
(
    id          bigint     not null
        constraint artist_pk
            primary key,
    name        varchar not null,
    photo_id    bigint
        constraint artist_photo_fk
            references image
            on delete cascade,
    description varchar
);

alter table artist
    owner to postgres;

insert into public.artist (id, name, photo_id, description)
values (0, 'unknown artist', 0,
        'unknown artist'),
       (1, 'apple', 1, 'apple trio'),
       (2, 'bread', 2, 'bread band'),
       (3, 'cheese', 3, 'cheese brothers'),
       (4, 'donut', 4, 'donut fusion');

-- table with albums
create table album
(
    id        bigint     not null
        constraint album_pk
            primary key,
    title     varchar not null,
    year      int,
    cover_id     bigint
        constraint album_image_fk
            references image
            on delete cascade
);

alter table album
    owner to postgres;

insert into public.album (id, title, year, cover_id)
values (0, 'unreleased', 0000, 0),
       (1, 'apple album', 1994, 1),
       (2, 'bread cassette', 2000, 2),
       (3, 'cheese single', 2007, 3),
       (4, 'donut disk', 2020, 4),
       (5, 'hard donut', 2022, 4);

-- table with relation of artist and released albums
create table artist_album
(
    artist_id bigint
        constraint artist_album_artist_fk
            references artist
            on delete cascade,
    album_id  bigint
        constraint artist_album_album_fk
            references album
            on delete cascade,
    constraint artist_album_pk
        primary key (artist_id, album_id)
);

insert into public.artist_album (artist_id, album_id)
values (0, 0),
       (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (4, 5);

-- table with tracks
create table track
(
    id    bigint     not null
        constraint track_pk
            primary key,
    title varchar not null
);

alter table track
    owner to postgres;

insert into public.track (id, title)
values (0, 'unreleased track'),
       (1, 'apple track #1'),
       (2, 'bread track #1'),
       (3, 'cheese track #1'),
       (4, 'donut1 track #1'),
       (5, 'donut1 track #2'),
       (6, 'donut2 track #1');

-- table that describes the relationship of album and tracks it consists of
create table album_track
(
    album_id bigint not null
        constraint album_track_album_fk
            references album,
    track_id bigint not null
        constraint album_track_track_fk
            references track,
    constraint album_track_pk
        primary key (album_id, track_id)
);

insert into public.album_track (album_id, track_id)
values (0, 0),
       (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (4, 5),
       (4, 6);

-- table with genres corresponding to albums
create table album_genre
(
    album_id bigint     not null
        constraint album_genre_album_fk
            references album,
    genre    varchar not null,
    constraint album_genre_pk
        primary key (album_id, genre)
);

alter table album_genre
    owner to postgres;

insert into public.album_genre (album_id, genre)
values (0, 'apple rock'),
       (1, 'bread pop'),
       (2, 'cheese-core'),
       (3, 'donut''n''roll'),
       (4, 'donut''n''bass');
