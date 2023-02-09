create table grupo (id bigint not null auto_increment, nome_grupo varchar(255), primary key (id)) engine=InnoDB;
create table grupo_permissao (fk_grupo bigint not null, fk_permissao bigint not null) engine=InnoDB;
create table pedido (id bigint not null auto_increment, data_cancelamento datetime, data_confirmacao datetime, data_criacao datetime, data_entrega datetime, bairro varchar(255), cep varchar(255), complemento varchar(255), logradouro varchar(255), numero varchar(255), subtotal decimal(19,2), taxa_frete decimal(19,2), valor_total decimal(19,2), primary key (id)) engine=InnoDB;
create table permissao (id bigint not null auto_increment, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB;
create table produto (id bigint not null auto_increment, ativo bit not null, nome varchar(255) not null, preco double precision not null, restaurante_id bigint, primary key (id)) engine=InnoDB;
create table restaurante (id bigint not null auto_increment, nome_restaurante varchar(255), primary key (id)) engine=InnoDB;
create table usuario (id bigint not null auto_increment, data_criacao datetime, email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB;
create table usuario_grupo (fk_usuario bigint not null, fk_grupo bigint not null) engine=InnoDB;

alter table grupo_permissao add constraint fk_grupo_permissao foreign key (fk_permissao) references permissao (id);
alter table grupo_permissao add constraint fk_permissao_grupo foreign key (fk_grupo) references grupo (id);
alter table produto add constraint fk_restaurante_produto foreign key (restaurante_id) references restaurante (id);
alter table usuario_grupo add constraint fk_usuario_grupo_usuario foreign key (fk_grupo) references grupo (id);
alter table usuario_grupo add constraint fk_grupo_usuario_usuario foreign key (fk_usuario) references usuario (id);

