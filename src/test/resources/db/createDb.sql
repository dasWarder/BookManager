DROP TABLE IF EXISTS detail_test;
DROP TABLE IF EXISTS book_test;
DROP TABLE IF EXISTS customer_test;


CREATE TABLE customer_test (
                          id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
                          login VARCHAR(255) NOT NULL,
                          password VARCHAR NOT NULL
);



CREATE TABLE book_test (
                      id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
                      name VARCHAR(250) NOT NULL,
                      author VARCHAR(150),
                      year INTEGER,
                      customer_id INTEGER,

                      FOREIGN KEY (customer_id) REFERENCES customer_test(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX book_id_name ON book_test (id, name);

CREATE TABLE detail_test (
                        id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('book_id_seq'),
                        description VARCHAR,
                        image VARCHAR,
                        book_id INTEGER REFERENCES book_test(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX book_id_description ON detail_test(book_id, description);

