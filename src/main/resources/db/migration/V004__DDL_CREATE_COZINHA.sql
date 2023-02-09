SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS cozinha;
create table cozinha (id bigint auto_increment not null ,nome_cozinha varchar(100),primary key (id)) engine=InnoDB;

alter table restaurante add COLUMN fk_cozinha bigint NOT NULL;
alter table restaurante add constraint fk_restaurante_cozinha foreign key (fk_cozinha) references cozinha (id);

SET FOREIGN_KEY_CHECKS = 1;
