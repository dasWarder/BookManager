INSERT INTO CUSTOMER(id, login, password) VALUES (20, 'marrySmith3', '12345');
INSERT INTO CUSTOMER(id, login, password) VALUES (21, 'warder', '12345');

INSERT INTO BOOK(id, name, author, year, customer_id) VALUES (3,'The Hobbit', 'J.R.R.Tolkien', 1937, 20);
INSERT INTO BOOK(id, name, author, year, customer_id) VALUES (4,'Harry Potter', 'Rowling', 1994, 20);
INSERT INTO BOOK(id, name, author, year, customer_id) VALUES (5,'The witcher', 'Sapkowski', 1986, 20);
INSERT INTO BOOK(id, name, author, year, customer_id) VALUES (6,'War and Peace', 'Tolstoy', 1867, 21);

INSERT INTO DETAIL(id, description, image, book_id) VALUES (7, 'THE HOBBIT DESCRIPTION', 'THE HOBBIT BOOK COVER', 3);