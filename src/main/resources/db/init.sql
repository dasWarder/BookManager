DROP TABLE IF EXISTS note;
DROP TABLE IF EXISTS detail;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS customer;
DROP SEQUENCE IF EXISTS book_id_seq;

CREATE SEQUENCE book_id_seq START WITH 1;

CREATE TABLE customer (
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
    login VARCHAR(255) NOT NULL,
    password VARCHAR NOT NULL
);


CREATE TABLE book (
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
    name VARCHAR(250) NOT NULL,
    author VARCHAR(150),
    year INTEGER,
    customer_id INTEGER,
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX book_id_name ON book (id, name);

CREATE TABLE detail (
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
    description VARCHAR,
    image VARCHAR,
    book_id INTEGER REFERENCES book(id) ON DELETE CASCADE,
    customer_comment VARCHAR
);

CREATE UNIQUE INDEX book_id_description ON detail(book_id, description);

CREATE TABLE note (
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
    date_time TIMESTAMP,
    text VARCHAR,
    book_id INTEGER REFERENCES book(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX book_id_note ON note(book_id, date_time);

