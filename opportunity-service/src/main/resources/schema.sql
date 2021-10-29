CREATE TABLE IF NOT EXISTS opportunity (
  id bigint NOT NULL AUTO_INCREMENT,
  account_id bigint DEFAULT NULL,
  decision_maker_id bigint DEFAULT NULL,
  product varchar(255) DEFAULT NULL,
  quantity int DEFAULT NULL,
  sales_rep_id bigint DEFAULT NULL,
  status varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
