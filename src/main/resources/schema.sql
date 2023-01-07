DROP TABLE IF EXISTS `product`;

CREATE TABLE `product`
(
    id          bigint      NOT NULL,
    name        varchar(30) NOT NULL,
    price       int         NOT NULL,
    stock_count int         NOT NULL,
    created_at  datetime   NOT NULL,
    updated_at  datetime   NOT NULL
);

ALTER TABLE `product`
    ADD CONSTRAINT `PK_PRODUCT` PRIMARY KEY (
                                             `id`
        );