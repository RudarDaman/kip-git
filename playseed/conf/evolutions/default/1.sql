# --- !Ups
CREATE TABLE userprofile(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(254) NOT NULL,
    name VARCHAR(254),
    password VARCHAR(254) NOT NULL,
    age INT(11) NOT NULL
);

# --- !Downs
DROP TABLE userprofile;