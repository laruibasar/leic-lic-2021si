drop table if exists users;
drop table if exists movies;
drop table if exists movies_reviews;
drop table if exists ratings;
drop table if exists reviews;

create table users (
  uid serial primary key ,
  fname varchar(50),
  lname varchar(50),
  email varchar(50) unique
);

create table movies (
  name varchar(50),
  age int,
  genre varchar(50),
  castAndDirectors varchar(80),
  primary key (name, age)
);

create table reviews (
    rid serial primary key,
    summary varchar(50),
    completeReview varchar(150),
    rating int references rating(rid),
    --movie int references movies_reviews(mid)
    movieCritic int references users(uid)
);

create table movies_reviews (
    rid int references reviews(rid),
    name varchar(50) references movies(name),
    age int references movies(age)
);

create table ratings (
    rid serial primary key,
    rating int check (rating between 1 AND 5)
);