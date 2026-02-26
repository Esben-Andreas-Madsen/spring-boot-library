-- =========================
-- AUTHORS
-- =========================
INSERT INTO authors (name, birth_year) VALUES
('George Orwell', 1903),
('Jane Austen', 1775),
('J.K. Rowling', 1965),
('J.R.R. Tolkien', 1892),
('Stephen King', 1947),
('Agatha Christie', 1890),
('Neil Gaiman', 1960),
('Terry Pratchett', 1948),
('Stephen Baxter', 1957);


-- =========================
-- BOOKS
-- =========================
INSERT INTO books (title, published_year, isbn, pages, language) VALUES

-- George Orwell
('1984', 1949, '9780451524935', 328, 'English'),
('Animal Farm', 1945, '9780451526342', 112, 'English'),

-- Jane Austen
('Pride and Prejudice', 1813, '9780141439518', 279, 'English'),
('Emma', 1815, '9780141439587', 474, 'English'),

-- J.K. Rowling
('Harry Potter and the Sorcerer''s Stone', 1997, '9780590353427', 309, 'English'),
('Harry Potter and the Chamber of Secrets', 1998, '9780439064873', 341, 'English'),

-- Tolkien
('The Hobbit', 1937, '9780547928227', 310, 'English'),
('The Fellowship of the Ring', 1954, '9780547928210', 423, 'English'),

-- Stephen King
('The Shining', 1977, '9780307743657', 447, 'English'),
('Misery', 1987, '9781501143106', 320, 'English'),

-- Agatha Christie
('Murder on the Orient Express', 1934, '9780062693662', 256, 'English'),

-- Co-authored
('Good Omens', 1990, '9780060853983', 432, 'English'),
('The Long Earth', 2012, '9780062067753', 416, 'English');


-- =========================
-- BOOK_AUTHORS (Single Author)
-- =========================

INSERT INTO book_authors (book_id, author_id)
SELECT b.id, a.id
FROM books b
JOIN authors a ON a.name = 'George Orwell'
WHERE b.title IN ('1984', 'Animal Farm');

INSERT INTO book_authors (book_id, author_id)
SELECT b.id, a.id
FROM books b
JOIN authors a ON a.name = 'Jane Austen'
WHERE b.title IN ('Pride and Prejudice', 'Emma');

INSERT INTO book_authors (book_id, author_id)
SELECT b.id, a.id
FROM books b
JOIN authors a ON a.name = 'J.K. Rowling'
WHERE b.title IN (
'Harry Potter and the Sorcerer''s Stone',
'Harry Potter and the Chamber of Secrets'
);

INSERT INTO book_authors (book_id, author_id)
SELECT b.id, a.id
FROM books b
JOIN authors a ON a.name = 'J.R.R. Tolkien'
WHERE b.title IN ('The Hobbit', 'The Fellowship of the Ring');

INSERT INTO book_authors (book_id, author_id)
SELECT b.id, a.id
FROM books b
JOIN authors a ON a.name = 'Stephen King'
WHERE b.title IN ('The Shining', 'Misery');

INSERT INTO book_authors (book_id, author_id)
SELECT b.id, a.id
FROM books b
JOIN authors a ON a.name = 'Agatha Christie'
WHERE b.title = 'Murder on the Orient Express';


-- =========================
-- BOOK_AUTHORS (Co-Authored)
-- =========================

-- Good Omens (Neil Gaiman + Terry Pratchett)
INSERT INTO book_authors (book_id, author_id)
SELECT b.id, a.id
FROM books b
JOIN authors a
    ON a.name IN ('Neil Gaiman', 'Terry Pratchett')
WHERE b.title = 'Good Omens';

-- The Long Earth (Terry Pratchett + Stephen Baxter)
INSERT INTO book_authors (book_id, author_id)
SELECT b.id, a.id
FROM books b
JOIN authors a
    ON a.name IN ('Terry Pratchett', 'Stephen Baxter')
WHERE b.title = 'The Long Earth';


-- =========================
-- POSTGRESQL BULK DATA
-- =========================

INSERT INTO authors (name, birth_year)
SELECT
    'Generated Author ' || gs,
    1850 + (gs % 150)
FROM generate_series(1, 1000) AS gs;