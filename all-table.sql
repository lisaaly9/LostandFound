-- SQLBook: Code
CREATE TABLE account (
    id_account INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(100) NOT NULL,
    user_password VARCHAR(100) NOT NULL
);

CREATE TABLE found_item (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(200) NOT NULL,
    description_item TEXT,
    location_item VARCHAR(200),
    image_url VARCHAR(200),
    date_found TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    category VARCHAR(200),
    contact VARCHAR(200),
    id_account INT NOT NULL,
    FOREIGN KEY (id_account) REFERENCES account(id_account)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE lost_item (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(200) NOT NULL,
    description_item TEXT,
    location_item VARCHAR(200),
    image_url VARCHAR(200),
    date_lost TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    category VARCHAR(200),
    contact VARCHAR(200),
    id_account INT NOT NULL,
    FOREIGN KEY (id_account) REFERENCES account(id_account)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
