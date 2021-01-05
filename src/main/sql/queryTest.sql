select users.uid, users.name, users.email, reviews.rid, reviews.summary, reviews.rating, movies.mid, movies.title, movies.year
from users
    inner join reviews on users.uid = reviews.movieCritic
     inner join movies on reviews.movie = movies.mid
         where uid = 4;


select reviews.summary, reviews.completeReview, reviews.rating, movies.title, movies.year
from reviews
         inner join movies on reviews.movie = movies.mid
where rid = 2;

