CREATE TABLE users_test (
                       id INTEGER PRIMARY KEY NOT NULL,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR NOT NULL,
                       enabled BOOLEAN
);

CREATE UNIQUE INDEX uniq_email ON users_test(username);

CREATE TABLE users_test (
                       id INTEGER PRIMARY KEY NOT NULL,
                       name VARCHAR(45) NOT NULL
);

CREATE TABLE users_roles_test (
                             user_id INTEGER,
                             role_id INTEGER,
                             FOREIGN KEY (user_id) REFERENCES users_test(id),
                             FOREIGN KEY (role_id) REFERENCES roles_test(id)
);

CREATE TABLE book_test (
                      id INTEGER PRIMARY KEY NOT NULL,
                      name VARCHAR(250) NOT NULL,
                      author VARCHAR(150),
                      year INTEGER,
                      customer_id INTEGER,
                      FOREIGN KEY (customer_id) REFERENCES users_test(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX book_id_name ON book_test (id, name);

CREATE TABLE detail_test (
                        id INTEGER PRIMARY KEY NOT NULL,
                        description VARCHAR,
                        image VARCHAR,
                        book_id INTEGER REFERENCES book_test(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX book_id_description ON detail_test(book_id, description);

CREATE TABLE note_test (
                      id INTEGER PRIMARY KEY NOT NULL,
                      date_time TIMESTAMP,
                      text VARCHAR,
                      book_id INTEGER REFERENCES book_test(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX book_id_note ON note_test(book_id, date_time);