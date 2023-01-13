DROP TABLE IF EXISTS `ITEM`;

CREATE TABLE `ITEM`
(
    `ITEM_ID`      bigint       NOT NULL AUTO_INCREMENT,
    `ITEM_NM`      varchar(30)  NOT NULL,
    `ITEM_AMT`     int          NOT NULL,
    `ITEM_INV_QTY` int          NOT NULL,
    `SHPP_CD`      varchar(30)  NOT NULL,
    `ITEM_IMG_URL` varchar(300) NOT NULL,
    `REG_DTS`      datetime     NOT NULL,
    `REGPE_ID`     bigint NULL,
    `MOD_DTS`      datetime     NOT NULL,
    `MODPE_ID`     bigint NULL,
    PRIMARY KEY (`ITEM_ID`)
);