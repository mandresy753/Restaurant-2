create database "mini_dish_db";

create user "mini_dish_db_manager" with password '123456';

GRANT ALL PRIVILEGES ON DATABASE mini_dish_db
    TO mini_dish_db_manager;

GRANT ALL PRIVILEGES ON TABLE ingredient TO myuser;
GRANT USAGE, SELECT, UPDATE ON SEQUENCE ingredient_id_seq TO myuser;

TRUNCATE TABLE ingredient RESTART IDENTITY CASCADE;

SELECT initial_stock + SUM(CASE WHEN type='IN' THEN quantity ELSE -quantity END) AS stock
FROM stock_movement
WHERE id_ingredient = 1
GROUP BY id_ingredient;

