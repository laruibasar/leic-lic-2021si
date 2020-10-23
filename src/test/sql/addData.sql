insert into users(uid, fname, lname, email ) values
(1, 'Mike', 'Albuquerque', 'Mike-Alb@gmail.com'),
(2, 'Alice', 'Nahuw', 'Ali.chi@hotmail.com'),
(3, 'Alvaro', 'De Arriba', 'A.Acima@gmail.com'),
(4, 'Maria', 'Antonieta', 'MariaAnt-1755@gmail.com'),
(5, 'Vasco', 'Couves', 'ze-da-horta@hotmail.com');

insert into movies(name, age, genre, castAndDirectors) values
('Gladiator', 2000, 'Action, Adventure, Drama', 'Ridley Scott, Russell Crowe, Joaquin Phoenix, Connie Nielsen'),
('The Fast and the Furious', 2001, 'Action, Adventure, Drama', 'Rob Cohen,  Vin Diesel, Paul Walker, Michelle Rodriguez' ),
('Finding Nemo', 2003, 'Animation, Adventure, Comedy', 'Andrew Stanton, Lee Unkrich, Albert Brooks');

insert into reviews(rid, summary, completeReview, rating, movie, movieCritic) values
(1234, 'Edge of Your Seat Fun!','Great Story! Great Writing! Great Acting! Great Directing! This movie has it all.',
 111, 159, 1),
 (1125, 'Great','Gladiator is a historical epic from director Ridley Scott.',110, 159,4)
(1236, 'Swimming with Sharks is a Whale of a Time',
'The voice cast are capable and cannily chosen, from young Alexander Gould as Nemo.', 112, 160, 2),
 (1237, 'Yes, its THAT good !!', 'I will be totally honest and confirm to you that everything what they say about this movie is true.',
 114, 160, 3),
 (1238, 'A solid entertainment weel done action heist movie',
 'The Fast and the Furious is a gritty and gratifying cheap thrill, Rob Cohens high-octane hot-car.',113, 161, 5);

 insert into movies_reviews(mid, name, age) values
 (159, 'Gladiator', 2000),
 (160, 'Finding Nemo', 2003),
 (161, 'The Fast and the Furious', 2001);

 insert into ratings(rid, rating) values
 (110, 4),
 (111, 5),
 (112, 3),
 (113, 3),
 (114, 5);

