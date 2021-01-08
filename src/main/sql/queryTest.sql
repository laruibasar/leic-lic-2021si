select users.uid, users.name, users.email, reviews.rid, reviews.summary, reviews.rating, movies.mid, movies.title, movies.year
from users
    inner join reviews on users.uid = reviews.movieCritic
     inner join movies on reviews.movie = movies.mid
         where uid = 1;

select reviews.rid, reviews.summary, reviews.completeReview, reviews.rating, movies.title, movies.year, users.uid, users.name
                    from reviews
                          inner join movies on reviews.movie = movies.mid
                          inner join users on reviews.movieCritic = users.uid
                    where rid = 1 and movie = 3;