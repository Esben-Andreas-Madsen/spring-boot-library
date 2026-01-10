CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birth_year INT
);

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    published_year INT,
    isbn VARCHAR(255) UNIQUE,
    pages INT,
    language VARCHAR(50)
);

CREATE TABLE book_authors (
    book_id INT NOT NULL REFERENCES books(id),
    author_id INT NOT NULL REFERENCES authors(id),
    PRIMARY KEY (book_id, author_id)
);
