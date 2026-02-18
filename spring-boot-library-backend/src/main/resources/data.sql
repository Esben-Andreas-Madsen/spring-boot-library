-- insert some data
INSERT INTO authors (name, birth_year)
VALUES 
    ('George Orwell', 1903),
    ('Jane Austen', 1775),
    ('J.K. Rowling', 1965);

INSERT INTO books (title, published_year, isbn, pages, language)
VALUES
    ('1984', 1949, '9780451524935', 328, 'English'),
    ('Pride and Prejudice', 1813, '9780141439518', 279, 'English'),
    ('Harry Potter and the Sorcerer''s Stone', 1997, '9780590353427', 309, 'English');

INSERT INTO book_authors (book_id, author_id)
VALUES (
    (SELECT id FROM books WHERE title = '1984'),
    (SELECT id FROM authors WHERE name = 'George Orwell')
);
