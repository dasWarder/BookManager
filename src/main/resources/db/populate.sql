DELETE FROM note;
DELETE FROM customer;
DELETE FROM book;

ALTER SEQUENCE book_id_seq RESTART WITH 1;

INSERT INTO customer(login, password) VALUES
    ('marrySmith3', '12345'),
    ('warder', '12345');

INSERT INTO book(name, author, year, customer_id) VALUES
    ('The Hobbit', 'J.R.R.Tolkien', 1937, 1),
    ('Harry Potter', 'Rowling', 1994, 1),
    ('The witcher', 'Sapkowski', 1986, 1),
    ('War and Peace', 'Tolstoy', 1867, 2);

-- INSERT INTO detail(description, image, book_id) VALUES
--     ('SOME DESCRIPTION FOR THE BOOK', 'SOME LINKS TO THE BOOK', 1),
--     ('SOME DESCRIPTION TO THE BOOK 2', 'SOME LINKS TO THE BOOK 2', 2);


-- INSERT INTO note(date_time, text, book_id) VALUES
--     ('2021-06-01 12:44:53', 'I have read this book the first time', 4),
--     ('2021-06-02 14:30:00', 'I think this book is pretty interesting', 4);