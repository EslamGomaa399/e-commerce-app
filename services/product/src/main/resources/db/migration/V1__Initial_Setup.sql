
-- Create table for Category
CREATE TABLE IF NOT EXISTS category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
);

-- Create table for Product
CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    available_quantity BIGINT,
    price DECIMAL(19,2),
    category_id BIGINT,
    CONSTRAINT FK_category FOREIGN KEY (category_id) REFERENCES category(id)
);