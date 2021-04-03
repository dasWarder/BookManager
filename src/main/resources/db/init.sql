DROP TABLE IF EXISTS detail CASCADE;
DROP TABLE IF EXISTS book CASCADE;
DROP SEQUENCE IF EXISTS book_id_seq;

CREATE SEQUENCE book_id_seq START WITH 1;

CREATE TABLE book (
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
    name VARCHAR(250) NOT NULL,
    author VARCHAR(150),
    year INTEGER
);

CREATE UNIQUE INDEX book_id_name ON book (id, name);

CREATE TABLE detail (
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
    description VARCHAR,
    image VARCHAR,
    book_id INTEGER REFERENCES book(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX book_id_description ON detail(book_id, description);


