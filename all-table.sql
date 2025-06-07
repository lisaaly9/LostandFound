-- SQLBook: Code
CREATE Table found_item(
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(200),
    description_item TEXT,
    location_item VARCHAR(200),
    image_url VARCHAR(200),
    date_found TIMESTAMP,
    category VARCHAR(200),
    contact VARCHAR(200)
);

ALTER TABLE lost_item
ADD COLUMN contact VARCHAR(255);

-- Tambahkan ke tabel lost_item
ALTER TABLE lost_item
ADD COLUMN id_account INT;

-- Tambahkan ke tabel found_item
ALTER TABLE found_item
ADD COLUMN id_account INT;

-- Buat foreign key di lost_item
ALTER TABLE lost_item
ADD CONSTRAINT fk_lost_account
FOREIGN KEY (id_account)
REFERENCES account(id_account)
ON DELETE SET NULL
ON UPDATE CASCADE;

-- Buat foreign key di found_item
ALTER TABLE found_item
ADD CONSTRAINT fk_found_account
FOREIGN KEY (id_account)
REFERENCES account(id_account)
ON DELETE SET NULL
ON UPDATE CASCADE;


SELECT * FROM lost_item WHERE id_account IS NULL;
SELECT * FROM found_item WHERE id_account IS NULL;

SELECT li.id_item, li.id_account FROM lost_item li;
SELECT fi.id_item, fi.id_account FROM found_item fi;


SELECT * FROM account;
SELECT * FROM lost_item;
SELECT * FROM found_item;

UPDATE lost_item SET id_account = 1;
UPDATE found_item SET id_account = 1;


SELECT id_item, id_account FROM lost_item;
SELECT id_item, id_account FROM found_item;