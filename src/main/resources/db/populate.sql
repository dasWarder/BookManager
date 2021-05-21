DELETE FROM note;
DELETE FROM book;
DELETE FROM users_roles;
DELETE FROM roles;
DELETE FROM users;

ALTER SEQUENCE book_id_seq RESTART WITH 1;

INSERT INTO users(id, username, password, enabled) VALUES
    (1, 'alex@gmail.com', '$2a$10$zsPdPQk4sfhdVvGl4mjfNegLTqukxLG7IC0XePBVsaZNn.qoD9COu', true),
    (2, 'james@gmail.com', '$2a$10$zsPdPQk4sfhdVvGl4mjfNegLTqukxLG7IC0XePBVsaZNn.qoD9COu', true);

INSERT INTO roles(id, name) VALUES
    (1000, 'USER');

INSERT INTO users_roles VALUES
    (1, 1000),
    (2, 1000);

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