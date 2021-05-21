DROP TABLE IF EXISTS note;
DROP TABLE IF EXISTS detail;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS users_roles;
DROP SEQUENCE IF EXISTS book_id_seq;

CREATE SEQUENCE book_id_seq START WITH 1;

CREATE TABLE users (
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
    username VARCHAR(255) NOT NULL,
    password VARCHAR NOT NULL,
    enabled BOOLEAN
);

CREATE TABLE roles (
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
    name VARCHAR(45) NOT NULL
);

CREATE TABLE users_roles (
    user_id INTEGER,
    role_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE book (
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
    name VARCHAR(250) NOT NULL,
    author VARCHAR(150),
    year INTEGER,
    customer_id INTEGER,
    FOREIGN KEY (customer_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX book_id_name ON book (id, name);

CREATE TABLE detail (
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
    description VARCHAR,
    image VARCHAR,
    book_id INTEGER REFERENCES book(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX book_id_description ON detail(book_id, description);

CREATE TABLE note (
    id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
    date_time TIMESTAMP,
    text VARCHAR,
    book_id INTEGER REFERENCES book(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX book_id_note ON note(book_id, date_time);

