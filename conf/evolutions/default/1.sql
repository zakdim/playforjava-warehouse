# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                        bigint auto_increment not null,
  street                    varchar(255),
  number                    varchar(255),
  postal_code               varchar(255),
  city                      varchar(255),
  country                   varchar(255),
  constraint pk_address primary key (id))
;

create table product (
  id                        bigint auto_increment not null,
  ean                       varchar(255),
  name                      varchar(255),
  description               varchar(255),
  picture                   longblob,
  constraint pk_product primary key (id))
;

create table stock_item (
  id                        bigint auto_increment not null,
  warehouse_id              bigint,
  product_id                bigint,
  quantity                  bigint,
  constraint pk_stock_item primary key (id))
;

create table tag (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_tag primary key (id))
;

create table warehouse (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  address_id                bigint,
  constraint pk_warehouse primary key (id))
;


create table product_tag (
  product_id                     bigint not null,
  tag_id                         bigint not null,
  constraint pk_product_tag primary key (product_id, tag_id))
;
alter table stock_item add constraint fk_stock_item_warehouse_1 foreign key (warehouse_id) references warehouse (id) on delete restrict on update restrict;
create index ix_stock_item_warehouse_1 on stock_item (warehouse_id);
alter table stock_item add constraint fk_stock_item_product_2 foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_stock_item_product_2 on stock_item (product_id);
alter table warehouse add constraint fk_warehouse_address_3 foreign key (address_id) references address (id) on delete restrict on update restrict;
create index ix_warehouse_address_3 on warehouse (address_id);



alter table product_tag add constraint fk_product_tag_product_01 foreign key (product_id) references product (id) on delete restrict on update restrict;

alter table product_tag add constraint fk_product_tag_tag_02 foreign key (tag_id) references tag (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table address;

drop table product;

drop table product_tag;

drop table stock_item;

drop table tag;

drop table warehouse;

SET FOREIGN_KEY_CHECKS=1;

