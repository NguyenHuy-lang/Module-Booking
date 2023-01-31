-- insert to table dis
INSERT INTO tbl_food(id, name, price, description, type) VALUES(1, 'chicken', 15, 'des', 'dish');
INSERT INTO tbl_dish(food_id) VALUES((SELECT id from tbl_food WHERE name = 'chicken'));

INSERT INTO tbl_food(id, name, price, description, type) VALUES (2, 'rice bar', '10', 'des', 'dish');
INSERT INTO tbl_dish(food_id) VALUES((SELECT id from tbl_food WHERE name = 'rice bar'));

INSERT INTO tbl_food(id, name, price, description, type) VALUES (3, 'steak', '10', 'des', 'dish');
INSERT INTO tbl_dish(food_id) VALUES((SELECT id from tbl_food WHERE name = 'steak'));

-- insert to table combo
INSERT INTO tbl_food(id, name, price, description, type) VALUES(4, 'combo chicken + juice', 25, 'des', 'combo');
INSERT INTO tbl_combo(food_id) VALUES((SELECT id from tbl_food WHERE name = 'combo chicken + juice'));

INSERT INTO tbl_food(id, name, price, description, type) VALUES (5, 'com bo rice bar + pepsi', 35, 'des', 'combo');
INSERT INTO tbl_combo(food_id) VALUES((SELECT id from tbl_food WHERE name = 'com bo rice bar + pepsi'));

INSERT INTO tbl_food(id, name, price, description, type) VALUES (6, 'combo steak + cocca', 35, 'des', 'combo');
INSERT INTO tbl_combo(food_id) VALUES((SELECT id from tbl_food WHERE name = 'combo steak + cocca'));

-- insert to table member , stafff, customer
INSERT INTO tbl_member(firstname, lastname, email, "password", role, member_type) VALUES
('nguyen', 'huy', 'huynguyend19ptit@gmail.com', '1234', 'customer', 'customer');

INSERT INTO tbl_member(firstname, lastname, email, "password", role, member_type) VALUES
('nguyen', 'Linh', 'huy@gmail.com', '1234', 'customer', 'customer');

-- insert to table TABLE
INSERT INTO tbl_table(id, code, capacity, description) VALUES (1, 'Vti-101', 5, 'des');
INSERT INTO tbl_table(id, code, capacity, description) VALUES (2, 'Vti-102', 5, 'des');
INSERT INTO tbl_table(id, code, capacity, description) VALUES (3, 'Vti-103', 5, 'des');
INSERT INTO tbl_table(id, code, capacity, description) VALUES (4, 'Vti-104', 5, 'des');

--insert to table VOUCHER

INSERT INTO tbl_voucher(id, value, unity, expire_at) VALUES(1, 15, 20, NOW());
INSERT INTO tbl_voucher(id, value, unity, expire_at) VALUES(2, 10, 20, NOW());

SELECT * FROM tbl_food;
SELECT * FROM tbl_dish;
SELECT * FROM tbl_combo;
SELECT * FROM tbl_voucher;
SELECT * FROM tbl_member;
SELECT * FROM tbl_table;
SELECT * FROM tbl_dish_in_combo;
