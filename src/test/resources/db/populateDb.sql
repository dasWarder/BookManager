INSERT INTO users(id, username, password, enabled) VALUES (20, 'marrySmith3', '12345', true);
INSERT INTO users(id, username, password, enabled) VALUES (21, 'warder', '12345', true);

INSERT INTO book(id, name, author, year, customer_id) VALUES (3,'The Hobbit', 'J.R.R.Tolkien', 1937, 20);
INSERT INTO book(id, name, author, year, customer_id) VALUES (4,'Harry Potter', 'Rowling', 1994, 20);
INSERT INTO book(id, name, author, year, customer_id) VALUES (5,'The witcher', 'Sapkowski', 1986, 20);
INSERT INTO book(id, name, author, year, customer_id) VALUES (6,'War and Peace', 'Tolstoy', 1867, 21);

INSERT INTO detail(id, description, image, book_id) VALUES (7, 'THE HOBBIT DESCRIPTION', 'THE HOBBIT BOOK COVER', 3);

INSERT INTO note(id, date_time, text, book_id) VALUES (12, '2021-04-10 12:22:00', 'First time I read this book', 4);
INSERT INTO note(id, date_time, text, book_id) VALUES (13, '2021-04-12 14:44:54','The second note about this book', 4);

/*
Note(8, LocalDateTime.of(2021, 04, 10, 12, 22, 00), "First time I read this book");
Note(9, LocalDateTime.of(2021, 04, 12, 14, 44, 54), "The second note about this book");
 */