-- Add user
insert into users(fname,lname,email) values(?,?,?)

-- Add movie
insert into movies(title,year,genre,castAndDirectors) values(?,?,?,?)

-- Add review
insert into reviews(summary,completeReview,rating,movie,movieCritic) values(?,?,?,?,?)

-- Add rating
insert into ratings(mid,rating) values(?,?)

