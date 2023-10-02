INSERT INTO orders (orderId, customerId, customerName, customerEmail,orderValue, discount, retailPrice, shippingDiscount, shippingFee) VALUES (1, 1,  'testname1', 'testemail1',1000,10,143,3,0);
INSERT INTO orders (orderId, customerId, customerName, customerEmail,orderValue, discount, retailPrice, shippingDiscount, shippingFee) VALUES (2, 1, 'testname2', 'testemail2',1000,10,143,3,0);
INSERT INTO orders (orderId, customerId, customerName, customerEmail,orderValue, discount, retailPrice, shippingDiscount, shippingFee) VALUES (3, 1, 'testname3', 'testemail3',1000,10,143,3,0);
INSERT INTO orders (orderId, customerId, customerName, customerEmail,orderValue, discount, retailPrice, shippingDiscount, shippingFee) VALUES (4, 1, 'testname4', 'testemail4',1000,10,143,3,0);

INSERT INTO order_items (id, order_id, productId, quantity, price) VALUES (1, 1, 4, 1, 30);
INSERT INTO order_items (id, order_id, productId, quantity, price) VALUES (2, 1, 3, 1, 50);
INSERT INTO order_items (id, order_id, productId, quantity, price) VALUES (3, 1, 5, 1, 200);
INSERT INTO order_items (id, order_id, productId, quantity, price) VALUES (4, 1, 1, 4, 5);
INSERT INTO order_items (id, order_id, productId, quantity, price) VALUES (5, 1, 2, 1, 60);
INSERT INTO order_items (id, order_id, productId, quantity, price) VALUES (6, 1, 6, 1, 20);
INSERT INTO order_items (id, order_id, productId, quantity, price) VALUES (7, 2, 3, 1, 45);
INSERT INTO order_items (id, order_id, productId, quantity, price) VALUES (8, 2, 6, 1, 20);
INSERT INTO order_items (id, order_id, productId, quantity, price) VALUES (9, 3, 1, 5, 5);
INSERT INTO order_items (id, order_id, productId, quantity, price) VALUES (10, 4, 2, 1, 60);