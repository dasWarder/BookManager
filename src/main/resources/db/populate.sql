DELETE FROM book;

ALTER SEQUENCE book_id_seq RESTART WITH 1;

INSERT INTO book(name, author, year) VALUES
    ('The Hobbit', 'J.R.R.Tolkien', 1937),
    ('Harry Potter', 'Rowling', 1994),
    ('The witcher', 'Sapkowski', 1986),
    ('War and Peace', 'Tolstoy', 1867);