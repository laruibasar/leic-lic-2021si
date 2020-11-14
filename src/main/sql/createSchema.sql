drop table if exists reviews;
drop table if exists ratings;
drop table if exists users;
drop table if exists movie_details;
drop table if exists movies;

create table users (
  uid serial primary key ,
  name varchar(50),
  email varchar(50) unique
);

create table movies (
  mid serial primary key,
  title varchar(50),
  year int,
  unique (title, year)
);

create table movie_details (
    id serial primary key,
    mid int references movies(mid),
    genre varchar(50),
    director text,
    actors text
);

create table reviews (
    rid serial primary key,
    summary varchar(50),
    completeReview varchar(150),
    rating int check (rating between 1 AND 5),  --int references rating(rateId),
    movie int references movies(mid),
    movieCritic int references users(uid)
);

create table ratings (
    ratingId serial primary key,
    rating int check (rating between 1 AND 5),
    movie int references movies(mid)
);