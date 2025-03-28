INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES
-- Precios para marca 1 (ZARA)
(1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
(2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
(3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
(4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR'),

-- Precios para marca 2 (OTRA_MARCA)
(5, 2, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 40.00, 'EUR'),
(6, 2, '2020-06-14 12:00:00', '2020-06-14 15:30:00', 2, 35455, 1, 35.00, 'EUR'),

-- Precios para marca 1 (ZARA) pero con otro producto
(7, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 99999, 0, 100.00, 'EUR'),

-- Precios para marca 2 (OTRA_MARCA) con otro producto
(8, 2, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 99999, 0, 90.00, 'EUR');