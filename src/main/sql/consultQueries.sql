--GET /users - returns the list of users.
select fname, lname from users;

--GET /users/{uid} - returns the details for the user identified by uid
select * from users where uid = ?;

--GET /movies - returns a list with all movies.
select name, year from movies;

--GET /movies/{mid} - returns the detailed information for the movie identified by mid
select * from movies where mid = ?;

--GET /movies/{mid}/ratings - returns the rating information for the movie identified by mid. This rating information include:
    --The rating average
    --The number of votes for each rating value
select
	avg(rating)::numeric(3,2) as average,
	(select count(rating) from(select rating
		from ratings
		where movie = 1
		union all
		select rating
		from reviews
		where movie = 1) as rating where rating = 1) as votesOne ,
	(select count(rating) from(select rating
		from ratings
		where movie = 1
		union all
		select rating
		from reviews
		where movie = 1) as rating where rating = 2) as votesTwo ,
	(select count(rating) from(select rating
		from ratings
		where movie = 1
		union all
		select rating
		from reviews
		where movie = 1) as rating where rating = 3) as votesThree,
	(select count(rating) from(select rating
		from ratings
		where movie = 1
		union all
		select rating
		from reviews
		where movie = 1) as rating where rating = 4) as votesFour,
	(select count(rating) from(select rating
		from ratings
		where movie = 1
		union all
		select rating
		from reviews
		where movie = 1) as rating where rating = 5) as votesFive
from (select rating
	from ratings
	where movie = 1
	union all
	select rating
	from reviews
	where movie = 1) as rating;

--GET /movies/{mid}/reviews - returns all the reviews for the movie identified by mid. The information for each review must not include the full review text.
select movie, summary, rating, movieCritic from reviews where mid = ?;

--GET /movies/{mid}/reviews/{rid} - returns the full information for the review rid of the movie identified by mid.
select * from reviews where rid = ?; --AND mid = ?; Useless because rid is primary key then is unique in the search
--TODO: Finish!

--GET /users/{uid}/reviews - returns all the reviews made from the user identified by uid. The information for each review must not include the full review text.
select movie Critic, movie, summary, rating from reviews where uid =?;

--GET /users/{uid}/reviews/{rid} - returns the full information for the review rid made by the user identified by uid.
select * from reviews where rid = ?; --AND uid = ?; Useless again bor the same reason
--TODO: Finish!

--GET /tops/ratings - returns a list with the movies, given the following parameters:
    --n - max number of movies to list;
   --average - two possible values:
       -- highest- movies with the highest average ratings
        --lowest- movies with the lowest average ratings
    --min - minimum number of votes
select name, year, avg(rating)::numeric(10,2) as average,count(rating)
from movies join ratings on(movies.mid = ratings.movie)
Group by name, year
HAVING count(rating) > 3
ORDER BY average ASC;

--TODO: O count ainda apenas conta os ratings de ratings, falta contar os ratings de reviews