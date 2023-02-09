create table cidade (id bigint not null auto_increment, nome_cidade varchar(255), fk_estado bigint not null, primary key (id)) engine=InnoDB;
create table estado (id bigint not null auto_increment, nome_estado varchar(255) not null, primary key (id)) engine=InnoDB;

alter table cidade add constraint fk_cidade_estado foreign key (fk_estado) references estado (id);

