CREATE TABLE "user"
(
    "id" BIGINT NOT NULL,
    "user_name" VARCHAR(255) NOT NULL,
    "first_name" VARCHAR(255) NOT NULL,
    "last_name" VARCHAR(255) NOT NULL,
    "age" INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE SEQUENCE "user_seq"
    MINVALUE 1
    MAXVALUE 9999999999
    START WITH 6
    INCREMENT BY 1;