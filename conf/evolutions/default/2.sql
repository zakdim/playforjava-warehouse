# --- Sample dataset

# --- !Ups

insert into address (id, street, number, postal_code, city, country) values (1, 'Main Street', '1234', 'A1B2C3', 'Montreal', 'CA');

insert into warehouse (id,name,address_id) values (  1,' Warehouse St Quentin', 1);

insert into product (id,name,description,ean) values (  1,'Stainless steel paperclips', 'Paperclips', 'abcde12f123123123');
insert into product (id,name,description,ean) values (  2,'Plastic paperclips', 'Paperclips', 'abcdea12312312321');
insert into product (id,name,description,ean) values (  3,'Red color paperclips', 'Paperclips', 'abcdeb13s1231233');
insert into product (id,name,description,ean) values (  4,'Cool paperclips', 'Paperclips', 'abcdec0123456789');

insert into stock_item (id, warehouse_id, product_id , quantity) values (  1, 1, 1, 120);
insert into stock_item (id, warehouse_id, product_id , quantity) values (  2, 1, 2, 190);
insert into stock_item (id, warehouse_id, product_id , quantity) values (  3, 1, 3, 200);
insert into stock_item (id, warehouse_id, product_id , quantity) values (  4, 1, 4, 250);

insert into tag (id, name) values (  1, 'Steal');
insert into tag (id, name) values (  2, 'Plastic');
insert into tag (id, name) values (  3, 'Metal');

insert into product_tag(product_id,tag_id) values(1,1);
insert into product_tag(product_id,tag_id) values(2,2);
insert into product_tag(product_id,tag_id) values(3,3);
insert into product_tag(product_id,tag_id) values(4,1);
insert into product_tag(product_id,tag_id) values(4,3);

# --- !Downs

delete from stock_item;
delete from address;
delete from warehouse;
delete from product_tag;
delete from product;
delete from tag;
