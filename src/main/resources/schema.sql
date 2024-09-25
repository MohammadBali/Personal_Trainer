-- category: table
CREATE TABLE `category` (
                            `ID` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                            PRIMARY KEY (`ID`),
                            UNIQUE KEY `Category_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- item: table
CREATE TABLE `item` (
                        `ID` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) DEFAULT NULL,
                        `price` double NOT NULL,
                        `categoryID` int DEFAULT NULL,
                        `unitType` int NOT NULL,
                        PRIMARY KEY (`ID`),
                        UNIQUE KEY `Item_unique` (`name`),
                        KEY `item_category_FK` (`categoryID`),
                        KEY `item_type_FK` (`unitType`),
                        CONSTRAINT `item_category_FK` FOREIGN KEY (`categoryID`) REFERENCES `category` (`ID`),
                        CONSTRAINT `item_type_FK` FOREIGN KEY (`unitType`) REFERENCES `type` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- item_transaction: table
CREATE TABLE `item_transaction` (
                                    `ID` int NOT NULL AUTO_INCREMENT,
                                    `transactionID` int DEFAULT NULL,
                                    `totalUnitPrice` double DEFAULT NULL,
                                    `quantity` double NOT NULL,
                                    `userItemID` int NOT NULL,
                                    `date` date DEFAULT NULL,
                                    PRIMARY KEY (`ID`),
                                    KEY `item_transaction_user_item_FK` (`userItemID`),
                                    KEY `item_transaction_transaction_FK` (`transactionID`),
                                    CONSTRAINT `item_transaction_transaction_FK` FOREIGN KEY (`transactionID`) REFERENCES `transaction` (`ID`) ON DELETE CASCADE,
                                    CONSTRAINT `item_transaction_user_item_FK` FOREIGN KEY (`userItemID`) REFERENCES `user_item` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- update_total_unit_price: trigger
CREATE DEFINER=`root`@`localhost` TRIGGER `update_total_unit_price` BEFORE INSERT ON `item_transaction` FOR EACH ROW BEGIN
    DECLARE itemPrice DOUBLE;

    -- Fetch the item price from the item table, joining via user_item
    SELECT price INTO itemPrice
    FROM item
             JOIN user_item ON item.ID = user_item.itemID
    WHERE user_item.ID = NEW.userItemID;

    -- Calculate the totalUnitPrice based on the item price and the quantity
    SET NEW.totalUnitPrice = itemPrice * NEW.quantity;
end;

-- update_total_transaction_price: trigger
CREATE DEFINER=`root`@`localhost` TRIGGER `update_total_transaction_price` AFTER INSERT ON `item_transaction` FOR EACH ROW BEGIN
    -- Update the totalPrice in the transaction table
    UPDATE `transaction`
    SET totalPrice = (SELECT SUM(totalUnitPrice)
                      FROM item_transaction
                      WHERE transactionID = NEW.transactionID)
    WHERE ID = NEW.transactionID;
end;

-- update_total_transaction_price_on_update: trigger
CREATE DEFINER=`root`@`localhost` TRIGGER `update_total_transaction_price_on_update` AFTER UPDATE ON `item_transaction` FOR EACH ROW BEGIN
    -- Update the totalPrice in the transaction table
    UPDATE `transaction`
    SET totalPrice = (SELECT SUM(totalUnitPrice)
                      FROM item_transaction
                      WHERE transactionID = NEW.transactionID)
    WHERE ID = NEW.transactionID;
END;

-- update_total_transaction_price_on_delete: trigger
CREATE DEFINER=`root`@`localhost` TRIGGER `update_total_transaction_price_on_delete` AFTER DELETE ON `item_transaction` FOR EACH ROW BEGIN
    -- Update the totalPrice in the transaction table after a deletion
    UPDATE `transaction`
    SET totalPrice = (SELECT SUM(totalUnitPrice)
                      FROM item_transaction
                      WHERE transactionID = OLD.transactionID)
    WHERE ID = OLD.transactionID;
END;

-- role: table
CREATE TABLE `role` (
                        `ID` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`ID`),
                        UNIQUE KEY `role_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- token: table
CREATE TABLE `token` (
                         `ID` int NOT NULL AUTO_INCREMENT,
                         `token` varchar(255) DEFAULT NULL,
                         `userID` int DEFAULT NULL,
                         PRIMARY KEY (`ID`),
                         UNIQUE KEY `Token_unique` (`token`),
                         KEY `token_user_FK` (`userID`),
                         CONSTRAINT `token_user_FK` FOREIGN KEY (`userID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- transaction: table
CREATE TABLE `transaction` (
                               `ID` int NOT NULL AUTO_INCREMENT,
                               `userID` int DEFAULT NULL,
                               `totalPrice` double DEFAULT NULL,
                               `isRecurring` tinyint(1) DEFAULT '0',
                               `recurrenceInterval` varchar(255) DEFAULT NULL,
                               `nextOccurrence` date DEFAULT NULL,
                               `createdAt` timestamp NULL DEFAULT NULL,
                               PRIMARY KEY (`ID`),
                               KEY `Transaction_user_FK` (`userID`),
                               CONSTRAINT `Transaction_user_FK` FOREIGN KEY (`userID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- type: table
CREATE TABLE `type` (
                        `ID` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`ID`),
                        UNIQUE KEY `Type_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- user: table
CREATE TABLE `user` (
                        `ID` int NOT NULL AUTO_INCREMENT,
                        `firstName` varchar(255) DEFAULT NULL,
                        `lastName` varchar(255) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `income` double DEFAULT NULL,
                        `updatedAt` date DEFAULT NULL,
                        `createdAt` date DEFAULT NULL,
                        `role` int DEFAULT '2',
                        PRIMARY KEY (`ID`),
                        UNIQUE KEY `User_unique` (`email`),
                        KEY `user_role2_FK` (`role`),
                        CONSTRAINT `user_role2_FK` FOREIGN KEY (`role`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- user_item: table
CREATE TABLE `user_item` (
                             `ID` int NOT NULL AUTO_INCREMENT,
                             `userID` int DEFAULT NULL,
                             `itemID` int DEFAULT NULL,
                             `limit` int DEFAULT NULL,
                             `updatedAt` date DEFAULT NULL,
                             PRIMARY KEY (`ID`),
                             UNIQUE KEY `unique_user_item` (`userID`,`itemID`),
                             KEY `user_item_item_FK` (`itemID`),
                             CONSTRAINT `user_item_item_FK` FOREIGN KEY (`itemID`) REFERENCES `item` (`ID`),
                             CONSTRAINT `user_item_user_FK` FOREIGN KEY (`userID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

