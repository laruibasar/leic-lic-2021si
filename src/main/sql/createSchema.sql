drop table if exists users;
drop table if exists movies;
drop table if exists ratings;
drop table if exists reviews;

create table users (
  uid serial primary key ,
  name varchar(50),
  email varchar(50) unique
);

create table movies (
  mid serial primary key,
  name varchar(50),
  year int,
  unique (name, year)
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
    rateId serial primary key,
    rating int check (rating between 1 AND 5),
    movie int references movies(mid)
);