create type payement_status as enum ('PAID','UNPAID');
alter table "order"
add column payment_status payement_status;

CREATE TABLE sale (
                      id SERIAL PRIMARY KEY,
                      creation_datetime TIMESTAMP NOT NULL,
                      order_id INTEGER UNIQUE NOT NULL,
                      CONSTRAINT fk_sale_order
                          FOREIGN KEY (order_id)
                              REFERENCES "order"(id)
);
