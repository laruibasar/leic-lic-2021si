insert into users(name, email ) values
('Mike Albuquerque', 'Mike-Alb@gmail.com'),
('Alice Nahuw', 'Ali.chi@hotmail.com'),
('Alvaro Abajo', 'A.Acima@gmail.com'),
('Maria Antonieta', 'MariaAnt-1755@gmail.com'),
('Vasco Couves', 'ze-da-horta@hotmail.com');

insert into movies(name, year) values
('Gladiator', 2000),
('The Fast and the Furious', 2001),
('Finding Nemo', 2003);

insert into reviews(summary, completeReview, rating, movie, movieCritic) values
('Edge of Your Seat Fun!','Great Story! Great Writing! Great Acting! Great Directing! This movie has it all.',5, 1, 1),
('Great','Gladiator is a historical epic from director Ridley Scott.',4, 1,4),
('Swimming with Sharks is a Whale of a Time', 'The voice cast are capable and cannily chosen, from young Alexander Gould as Nemo.', 3, 2, 2),
('Yes, its THAT good !!', 'I will be totally honest and confirm to you that everything what they say about this movie is true.',5, 2, 3),
('A solid entertainment weel done action heist movie', 'The Fast and the Furious is a gritty and gratifying cheap thrill, Rob Cohens high-octane hot-car.',3, 3, 5);

 insert into ratings(rating, movie) values
 ( 4, 1),
 (5, 1),
 (3, 2),
 (3, 2),
 (5, 3);