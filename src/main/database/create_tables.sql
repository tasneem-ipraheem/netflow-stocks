DROP TABLE STOCKS;

CREATE TABLE STOCKS (
  ID      MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
  SYMBOL  VARCHAR(10)    NOT NULL,
  NAME    VARCHAR(255)   NOT NULL,
  PRICE   DECIMAL(10, 2) NOT NULL,
  UPDATED TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
);