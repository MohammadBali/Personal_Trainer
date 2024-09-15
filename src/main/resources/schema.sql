CREATE TABLE `category`
(
    `ID`   int          NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE KEY `Category_unique` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `item`
(
    `ID`         int          NOT NULL AUTO_INCREMENT,
    `name`       varchar(100) NOT NULL,
    `price`      double       NOT NULL,
    `categoryID` int DEFAULT NULL,
    `unitType`   int          NOT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE KEY `Item_unique` (`name`),
    KEY          `item_category_FK` (`categoryID`),
    KEY          `item_type_FK` (`unitType`),
    CONSTRAINT `item_category_FK` FOREIGN KEY (`categoryID`) REFERENCES `category` (`ID`),
    CONSTRAINT `item_type_FK` FOREIGN KEY (`unitType`) REFERENCES `type` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `token`
(
    `ID`     int          NOT NULL AUTO_INCREMENT,
    `token`  varchar(100) NOT NULL,
    `userID` int DEFAULT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE KEY `Token_unique` (`token`),
    KEY      `token_user_FK` (`userID`),
    CONSTRAINT `token_user_FK` FOREIGN KEY (`userID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `transaction`
(
    `ID`                 int NOT NULL AUTO_INCREMENT,
    `userID`             int         DEFAULT NULL,
    `totalPrice`         double      DEFAULT NULL,
    `isRecurring`        tinyint(1) DEFAULT '0',
    `recurrenceInterval` varchar(20) DEFAULT NULL,
    `nextOccurrence`     datetime    DEFAULT NULL,
    PRIMARY KEY (`ID`),
    KEY                  `Transaction_user_FK` (`userID`),
    CONSTRAINT `Transaction_user_FK` FOREIGN KEY (`userID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `user`
(
    `ID`        int NOT NULL AUTO_INCREMENT,
    `firstName` varchar(255) DEFAULT NULL,
    `lastName`  varchar(255) DEFAULT NULL,
    `email`     varchar(255) DEFAULT NULL,
    `password`  varchar(255) DEFAULT NULL,
    `income`    double       DEFAULT NULL,
    `updatedAt` date         DEFAULT NULL,
    `createdAt` date         DEFAULT NULL,
    `role`      int          DEFAULT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE KEY `User_unique` (`email`),
    KEY         `user_role2_FK` (`role`),
    CONSTRAINT `user_role2_FK` FOREIGN KEY (`role`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- personaltrainer.`role` definition

CREATE TABLE `role`
(
    `ID`   int          NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE KEY `role_unique` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `type`
(
    `ID`   int          NOT NULL AUTO_INCREMENT,
    `Name` varchar(100) NOT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE KEY `Type_unique` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- MANY TO MANY TABLE
-- S

CREATE TABLE `user_item`
(
    `ID`     int NOT NULL AUTO_INCREMENT,
    `userID` int      DEFAULT NULL,
    `itemID` int      DEFAULT NULL,
    `limit`  int      DEFAULT NULL,
    `date`   datetime DEFAULT NULL,
    `amount` double   DEFAULT NULL,
    PRIMARY KEY (`ID`),
    KEY      `user_item_item_FK` (`itemID`),
    KEY      `user_item_user_FK` (`userID`),
    CONSTRAINT `user_item_item_FK` FOREIGN KEY (`itemID`) REFERENCES `item` (`ID`),
    CONSTRAINT `user_item_user_FK` FOREIGN KEY (`userID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- personaltrainer.item_transaction definition

CREATE TABLE `item_transaction`
(
    `ID`             int    NOT NULL AUTO_INCREMENT,
    `transactionID`  int    DEFAULT NULL,
    `itemID`         int    DEFAULT NULL,
    `totalUnitPrice` double DEFAULT NULL,
    `quantity`       double NOT NULL,
    PRIMARY KEY (`ID`),
    KEY              `item_transaction_item_FK` (`itemID`),
    KEY              `item_transaction_transaction_FK` (`transactionID`),
    CONSTRAINT `item_transaction_item_FK` FOREIGN KEY (`itemID`) REFERENCES `item` (`ID`),
    CONSTRAINT `item_transaction_transaction_FK` FOREIGN KEY (`transactionID`) REFERENCES `transaction` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;