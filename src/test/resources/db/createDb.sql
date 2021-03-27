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
