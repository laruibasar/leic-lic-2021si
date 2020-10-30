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
select AVG(rating) as average,
    where mid = ?;
--TODO: not done, think about it!!

--GET /movies/{mid}/reviews - returns all the reviews for the movie identified by mid. The information for each review must not include the full review text.
select movie, summary, rating, movieCritic from reviews where mid = ?;

--GET /movies/{mid}/reviews/{rid} - returns the full information for the review rid of the movie identified by mid.
select * from reviews where rid = ?; --AND mid = ?; Useless because rid is primary key then is unique in the search

--GET /users/{uid}/reviews - returns all the reviews made from the user identified by uid. The information for each review must not include the full review text.
select movie Critic, movie, summary, rating from reviews where uid =?;

--GET /users/{uid}/reviews/{rid} - returns the full information for the review rid made by the user identified by uid.
select * from reviews where rid = ?; --AND uid = ?; Useless again bor the same reason

--GET /tops/ratings - returns a list with the movies, given the following parameters:
    --n - max number of movies to list;
   --average - two possible values:
       -- highest- movies with the highest average ratings
        --lowest- movies with the lowest average ratings
    --min - minimum number of votes
select name, year
from movies join ratings on(movies.mid = ratings.mid)
where COUNT(mid) > min
--TODO: NOT DONE! n shows the n first tuples, average shows the AVG DESC OR ASC, min is just a condition