use [master]
GO

IF EXISTS (SELECT * FROM sys.databases WHERE name = 'StoreDB')
	DROP DATABASE StoreDB
GO

CREATE DATABASE StoreDB 
GO
use StoreDB 
GO

CREATE TABLE Accounts (
id varchar(10) PRIMARY KEY(id),
Username varchar(50),
Password varchar(50),
FirstName nvarchar(50),
LastName nvarchar(50),
image varchar(100),
role int,
hehe int
);


GO
INSERT INTO Accounts (id, Username, Password, FirstName, LastName, image, role, hehe) VALUES 
('SH1', 'user1', 'pass123', N'Hồ', N'Nguyễn', 'SH1.png', 0, 0),
('SH2', 'user2', 'pass123', N'Tuấn', N'Trần', 'SH2.png', 1, 0),
('SH3', 'user3', 'pass123', N'Linh', N'Phạm', 'SH3.png', 0, 0),
('SH4', 'user4', 'pass123', N'Đức', N'Lê', 'SH4.png', 1, 0),
('SH5', 'user5', 'pass123', N'Anh', N'Trần', 'SH5.png', 0, 0),
('SH6', 'user6', 'pass123', N'Thảo', N'Nguyễn', 'SH6.png', 1, 0),
('SH7', 'user7', 'pass123', N'Tâm', N'Lê', 'SH7.png', 0, 0),
('SH8', 'user8', 'pass123', N'Hải', N'Phạm', 'SH8.png', 1, 0),
('SH9', 'user9', 'pass123', N'Minh', N'Nguyễn', 'SH9.png', 0, 0),
('SH10', 'user10', 'pass123', N'Thành', N'Trần', 'SH10.png', 1, 0);
GO


CREATE TABLE Product_Category  (
id varchar(10) PRIMARY KEY (id),
name nvarchar(30)
);


GO
INSERT INTO Product_Category (id, name) VALUES 
('PC1', N'ring'),
('PC2', N'rings'),
('PC3', N'necklace'),
('PC4', N'earrings'),
('PC5', N'other');
GO


CREATE TABLE Products  (
id varchar(10) PRIMARY KEY(id),
name nvarchar(100),
description nvarchar(max),
image varchar(15),
price float,
quantity int,
category_id varchar(10),
FOREIGN KEY(category_id) REFERENCES Product_Category(id)
);

GO
INSERT INTO Products (id, name, description, image, price, quantity, category_id)
VALUES 
  ('P1', 'Product 1 Name', 'Product 1 Description', 'P1.png', 1000000.00, 10, 'PC4'),
  ('P2', 'Product 2 Name', 'Product 2 Description', 'P2.png', 1200000.00, 15, 'PC4'),
  ('P3', 'Product 3 Name', 'Product 3 Description', 'P3.png', 1500000.00, 20, 'PC4'),
  ('P4', 'Product 4 Name', 'Product 4 Description', 'P4.png', 800000.00, 5, 'PC4'),
  ('P5', 'Product 5 Name', 'Product 5 Description', 'P5.png', 2000000.00, 12, 'PC4'),
  ('P6', 'Product 6 Name', 'Product 6 Description', 'P6.png', 1800000.00, 8, 'PC3'),
  ('P7', 'Product 7 Name', 'Product 7 Description', 'P7.png', 2200000.00, 14, 'PC3'),
  ('P8', 'Product 8 Name', 'Product 8 Description', 'P8.png', 2500000.00, 18, 'PC3'),
  ('P9', 'Product 9 Name', 'Product 9 Description', 'P9.png', 1200000.00, 7, 'PC3'),
  ('P10', 'Product 10 Name', 'Product 10 Description', 'P10.png', 2800000.00, 10, 'PC3'),
  ('P11', 'Product 11 Name', 'Product 11 Description', 'P11.png', 1500000.00, 5, 'PC2'),
  ('P12', 'Product 12 Name', 'Product 12 Description', 'P12.png', 2000000.00, 8, 'PC2'),
  ('P13', 'Product 13 Name', 'Product 13 Description', 'P13.png', 3500000.00, 12, 'PC2'),
  ('P14', 'Product 14 Name', 'Product 14 Description', 'P14.png', 1800000.00, 10, 'PC1'),
  ('P15', 'Product 15 Name', 'Product 15 Description', 'P15.png', 2200000.00, 15, 'PC1'),
  ('P16', 'Product 16 Name', 'Product 16 Description', 'P16.png', 2500000.00, 20, 'PC1'),
  ('P17', 'Product 17 Name', 'Product 17 Description', 'P17.png', 1200000.00, 8, 'PC1'),
  ('P18', 'Product 18 Name', 'Product 18 Description', 'P18.png', 2000000.00, 12, 'PC1'),
  ('P19', 'Product 19 Name', 'Product 19 Description', 'P19.png', 2800000.00, 18, 'PC1'),
  ('P20', 'Product 20 Name', 'Product 20 Description', 'P20.png', 3500000.00, 22, 'PC1'),
  ('P21', 'Product 21 Name', 'Product 21 Description', 'P21.png', 1800000.00, 10, 'PC1'),
  ('P22', 'Product 22 Name', 'Product 22 Description', 'P22.png', 2200000.00, 15, 'PC1'),
  ('P23', 'Product 23 Name', 'Product 23 Description', 'P23.png', 2500000.00, 20, 'PC1'),
  ('P24', 'Product 24 Name', 'Product 24 Description', 'P24.png', 1200000.00, 8, 'PC1'),
  ('P25', 'Product 25 Name', 'Product 25 Description', 'P25.png', 2000000.00, 12, 'PC1'),
  ('P26', 'Product 26 Name', 'Product 26 Description', 'P26.png', 2800000.00, 18, 'PC1'),
  ('P27', 'Product 27 Name', 'Product 27 Description', 'P27.png', 3500000.00, 22, 'PC1'),
  ('P28', 'Product 28 Name', 'Product 28 Description', 'P28.png', 2000000.00, 15, 'PC1'),
  ('P29', 'Product 29 Name', 'Product 29 Description', 'P29.png', 3000000.00, 20, 'PC1'),
  ('P30', 'Product 30 Name', 'Product 30 Description', 'P30.png', 2500000.00, 12, 'PC1'),
  ('P31', 'Product 31 Name', 'Product 31 Description', 'P31.png', 1800000.00, 10, 'PC1'),
  ('P32', 'Product 32 Name', 'Product 32 Description', 'P32.png', 2200000.00, 15, 'PC1'),
  ('P33', 'Product 33 Name', 'Product 33 Description', 'P33.png', 2500000.00, 20, 'PC1'),
  ('P34', 'Product 34 Name', 'Product 34 Description', 'P34.png', 1200000.00, 8, 'PC1'),
  ('P35', 'Product 35 Name', 'Product 35 Description', 'P35.png', 2000000.00, 12, 'PC1'),
  ('P36', 'Product 36 Name', 'Product 36 Description', 'P36.png', 2800000.00, 18, 'PC1'),
  ('P37', 'Product 37 Name', 'Product 37 Description', 'P37.png', 3500000.00, 22, 'PC1'),
  ('P38', 'Product 38 Name', 'Product 38 Description', 'P38.png', 3900000.00, 30, 'PC5'),
  ('P39', 'Product 39 Name', 'Product 39 Description', 'P39.png', 4900000.00, 20, 'PC5'),
  ('P40', 'Product 40 Name', 'Product 40 Description', 'P40.png', 5900000.00, 15, 'PC5'),
  ('P41', 'Product 41 Name', 'Product 41 Description', 'P41.png', 4100000.00, 25, 'PC5'),
  ('P42', 'Product 42 Name', 'Product 42 Description', 'P42.png', 4200000.00, 22, 'PC5'),
  ('P43', 'Product 43 Name', 'Product 43 Description', 'P43.png', 4300000.00, 18, 'PC5'),
  ('P44', 'Product 44 Name', 'Product 44 Description', 'P44.png', 4400000.00, 20, 'PC5'),
  ('P45', 'Product 45 Name', 'Product 45 Description', 'P45.png', 4500000.00, 30, 'PC5'),
  ('P46', 'Product 46 Name', 'Product 46 Description', 'P46.png', 4600000.00, 15, 'PC5'),
  ('P47', 'Product 47 Name', 'Product 47 Description', 'P47.png', 4700000.00, 28, 'PC5'),
  ('P48', 'Product 48 Name', 'Product 48 Description', 'P48.png', 4800000.00, 25, 'PC5'),
  ('P49', 'Product 49 Name', 'Product 49 Description', 'P49.png', 4900000.00, 22, 'PC5'),
  ('P50', 'Product 50 Name', 'Product 50 Description', 'P50.png', 5000000.00, 20, 'PC5'),
  ('P51', 'Product 51 Name', 'Product 51 Description', 'P51.png', 5100000.00, 25, 'PC5'),
  ('P52', 'Product 52 Name', 'Product 52 Description', 'P52.png', 5200000.00, 22, 'PC5'),
  ('P53', 'Product 53 Name', 'Product 53 Description', 'P53.png', 5300000.00, 18, 'PC5'),
  ('P54', 'Product 54 Name', 'Product 54 Description', 'P54.png', 5400000.00, 20, 'PC5'),
  ('P55', 'Product 55 Name', 'Product 55 Description', 'P55.png', 5500000.00, 30, 'PC5'),
  ('P56', 'Product 56 Name', 'Product 56 Description', 'P56.png', 5600000.00, 15, 'PC5'),
  ('P57', 'Product 57 Name', 'Product 57 Description', 'P57.png', 5700000.00, 28, 'PC5');
GO


CREATE TABLE Shipping  (
id varchar(10) PRIMARY KEY (id),
name nvarchar(100),
shipping_fee float
);



GO
INSERT INTO Shipping (id, name, shipping_fee)
VALUES 
  ('S1', N'Giao hàng nhanh', 50000.00),
  ('S2', N'Giao hàng tiêu chuẩn', 30000.00),
  ('S3', N'Giao hàng hỏa tốc', 80000.00);
GO


CREATE TABLE Shop_Order (
id varchar(10) PRIMARY KEY (id),
date_order DATE,
total_price float,
status_order bit,
address_shipping nvarchar(1000),
shipping_id varchar(10),
account_id varchar(10),
phonenumber varchar(12),
FOREIGN KEY (shipping_id) REFERENCES Shipping(id),
FOREIGN KEY (account_id) REFERENCES Accounts(id)
);


GO
INSERT INTO Shop_Order (id, date_order, total_price, status_order, address_shipping, shipping_id, account_id, phonenumber)
VALUES 
  ('SO1', '2024-03-09', 1500000.00, 1, N'123 Đường Chính, Quận Hoàn Kiếm, Hà Nội, Việt Nam', 'S1', 'SH1', '09000000001'),
  ('SO2', '2024-03-10', 2200000.00, 1, N'456 Đường Sài Gòn, Quận 1, TP. Hồ Chí Minh, Việt Nam', 'S2', 'SH2', '09000000002'),
  ('SO3', '2024-03-11', 2800000.00, 0, N'789 Đường Nguyễn Văn Linh, Quận Thanh Khê, Đà Nẵng, Việt Nam', 'S1', 'SH3', '09000000003'),
  ('SO4', '2024-03-12', 2000000.00, 1, N'101 Đường Lê Lợi, Thành phố Huế, Việt Nam', 'S3', 'SH4', '09000000004'),
  ('SO5', '2024-03-13', 3500000.00, 1, N'202 Đường Nguyễn Huệ, Quận Ninh Kiều, Cần Thơ, Việt Nam', 'S2', 'SH5', '09000000005'),
  ('SO6', '2024-03-14', 1800000.00, 0, N'303 Đường Lê Thành Phương, TP. Nha Trang, Việt Nam', 'S3', 'SH6', '09000000006'),
  ('SO7', '2024-03-15', 2500000.00, 1, N'404 Đường Nguyễn Huệ, Quận Ngô Mây, Qui Nhơn, Việt Nam', 'S1', 'SH7', '09000000007'),
  ('SO8', '2024-03-16', 3000000.00, 1, N'505 Đường Bà Rịa, TP. Vũng Tàu, Việt Nam', 'S2', 'SH8', '09000000008'),
  ('SO9', '2024-03-17', 3200000.00, 0, N'606 Đường Tuy Phong, Bà Rịa - Vũng Tàu, Việt Nam', 'S3', 'SH9', '09000000009'),
  ('SO10', '2024-03-18', 2800000.00, 1, N'707 Đường Hạ Long, Quảng Ninh, Việt Nam', 'S1', 'SH10', '09000000010');
GO


CREATE TABLE Shopping_Cart (
id varchar(10) PRIMARY KEY(id),
shop_order_id varchar(10),
account_id varchar(10),
FOREIGN KEY(shop_order_id) REFERENCES Shop_Order(id),
FOREIGN KEY(account_id) REFERENCES Accounts(id)
);


GO
INSERT INTO Shopping_Cart (id, shop_order_id, account_id)
VALUES 
  ('SC1', 'SO1', 'SH1'),
  ('SC2', 'SO2', 'SH2'),
  ('SC3', 'SO3', 'SH3'),
  ('SC4', 'SO4', 'SH4'),
  ('SC5', 'SO5', 'SH5'),
  ('SC6', 'SO6', 'SH6'),
  ('SC7', 'SO7', 'SH7'),
  ('SC8', 'SO8', 'SH8');
GO


CREATE TABLE Shopping_Cart_Item  (
id varchar(10) PRIMARY KEY(id),
quantity int,
product_id varchar(10),
shopping_cart_id varchar(10),
FOREIGN KEY (product_id) REFERENCES Products(id),
FOREIGN KEY (shopping_cart_id) REFERENCES Shopping_Cart(id)
);

GO
INSERT INTO Shopping_Cart_Item (id, quantity, product_id, shopping_cart_id)
VALUES 
  ('SCI1', 2, 'P1', 'SC1'),
  ('SCI2', 3, 'P2', 'SC1'),
  ('SCI3', 1, 'P3', 'SC2'),
  ('SCI4', 4, 'P4', 'SC2'),
  ('SCI5', 2, 'P5', 'SC3'),
  ('SCI6', 1, 'P6', 'SC3'),
  ('SCI7', 3, 'P7', 'SC4'),
  ('SCI8', 2, 'P8', 'SC4');
GO

CREATE TABLE Payment (
id varchar(10) PRIMARY KEY(id),
shopping_cart_id varchar(10),
account_id varchar(10),
total_price float,
FOREIGN KEY(shopping_cart_id) REFERENCES Shopping_Cart(id),
FOREIGN KEY(account_id) REFERENCES Accounts(id)
);
GO

GO
INSERT INTO Payment (id, shopping_cart_id, account_id,total_price)
VALUES 
  ('PAY1', 'SC1', 'SH1',5000000.0),
  ('PAY2', 'SC2', 'SH2',12000000.0),
  ('PAY3', 'SC3', 'SH3',7000000.0),
  ('PAY4', 'SC4', 'SH4',19000000.0),
  ('PAY5', 'SC5', 'SH5',2000000.0);
GO
select * from Accounts ORDER BY id
select * from Product_Category ORDER BY id
select * from Products ORDER BY category_id
select * from Shipping ORDER BY id
select * from Shop_Order ORDER BY id
select * from Shopping_Cart ORDER BY id
select * from Shopping_Cart_Item ORDER BY id
select * from Payment ORDER BY id

select * from Products where name LIKE '%2%'ORDER BY id OFFSET 1 ROWS FETCH NEXT 12 ROWS ONLY;
 /*drop table
drop table Payment
drop table Shopping_cart_item
drop table Shopping_Cart
drop table Shop_order
drop table Shipping
drop table Products
drop table Product_Category
drop table Accounts
*/