CREATE TABLE  IF NOT EXISTS item (
    id       INT         NOT NULL,
    item_type VARCHAR(50) NOT NULL,
    cost NUMERIC(20, 2),
    PRIMARY KEY (id)
);


CREATE TABLE  IF NOT EXISTS discount (
    id       INT         NOT NULL AUTO_INCREMENT,
    discount_code VARCHAR(20) NOT NULL,
    percentage NUMERIC (10,2),
    discount_type VARCHAR(20) NOT NULL,
    item_type VARCHAR(50),
    item_count INT,
    total_cost NUMERIC(20, 2),
    PRIMARY KEY (id)
);
