DROP TABLE IF EXISTS details_test;
DROP TABLE IF EXISTS book_test;

DROP SEQUENCE IF EXISTS id_sqnc;

CREATE SEQUENCE id_sqnc START WITH 1;

CREATE TABLE book_test
(
    id     INTEGER      NOT NULL DEFAULT nextval('book_id_seq'),
    name   VARCHAR(250) NOT NULL,
    author VARCHAR(150),
    year   INTEGER
);

CREATE TABLE details_test
(
    id INTEGER NOT NULL DEFAULT  nextval('book_id_seq'),
    description VARCHAR,
    image VARCHAR,
    book_id INTEGER,
    FOREIGN KEY (book_id) REFERENCES book_test(id)
);
