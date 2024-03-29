CREATE TABLE netflow_stock (
  id      MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
  symbol  VARCHAR(10)    NOT NULL,
  name    VARCHAR(255)   NOT NULL,
  price   DECIMAL(10, 2) NOT NULL,
  updated TIMESTAMP      NOT NULL,
  CONSTRAINT uc_symbol UNIQUE (symbol)
);