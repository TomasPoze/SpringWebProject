CREATE TABLE Cars
(
    id          INT UNSIGNED      NOT NULL AUTO_INCREMENT,
    brand       VARCHAR(50)       NOT NULL,
    model       VARCHAR(50)       NOT NULL,
    year        INT               NOT NULL,
    kw          INT               NOT NULL,
    PRIMARY KEY (id)
);
