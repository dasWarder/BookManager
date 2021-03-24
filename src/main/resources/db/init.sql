DROP TABLE IF EXISTS book;

DROP SEQUENCE IF EXISTS book_id_seq;

CREATE SEQUENCE book_id_seq START WITH 1;

CREATE TABLE book (
    id INTEGER NOT NULL DEFAULT nextval('book_id_seq'),
    name VARCHAR(250) NOT NULL,
    author VARCHAR(150),
    year INTEGER
);

CREATE UNIQUE INDEX book_id_name ON book (id, name);