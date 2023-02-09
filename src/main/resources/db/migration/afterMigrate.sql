set foreign_key_checks = 0;

delete from grupo;
delete from grupo_permissao;
delete from pedido;
delete from permissao;
delete from produto;
delete from restaurante;
delete from usuario;
delete from usuario_grupo;
delete from estado;
delete from cozinha;




alter table grupo auto_increment = 1;
alter table grupo_permissao auto_increment = 1;
alter table pedido auto_increment = 1;
alter table permissao auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;
alter table usuario_grupo auto_increment = 1;
alter table produto auto_increment = 1;
alter table estado auto_increment = 1;
alter table cozinha auto_increment = 1;



INSERT INTO algafoodapi.usuario (data_criacao,email,nome,senha)
	VALUES (utc_timestamp,'YAN@TESTE.COM','YAN','123456');
INSERT INTO algafoodapi.usuario (data_criacao,email,nome,senha)
	VALUES (utc_timestamp,'GAEL@TESTE.COM','GAEL','987654');

INSERT INTO algafoodapi.permissao (descricao,nome)
	VALUES ('LIBERACAO COMPLETA','FULL');
INSERT INTO algafoodapi.permissao (descricao,nome)
	VALUES ('ACESSO RESTRITO','RESTRICT');

INSERT INTO algafoodapi.grupo (nome_grupo)
	VALUES ('DEVELOPER');
INSERT INTO algafoodapi.grupo (nome_grupo)
	VALUES ('ADMIN');
INSERT INTO algafoodapi.grupo (nome_grupo)
	VALUES ('SIMPLES');

INSERT INTO algafoodapi.estado (nome_estado) 
	VALUES ("Bahia");

INSERT INTO algafoodapi.estado (nome_estado) 
	VALUES ("São Paulo");

INSERT INTO cozinha (nome_cozinha) 
	VALUES ("Brasileira");
INSERT INTO cozinha (nome_cozinha) 
	VALUES ("Chinesa");
INSERT INTO cozinha (nome_cozinha) 
	VALUES ("Tailandesa");


INSERT INTO algafoodapi.restaurante (nome_restaurante,fk_cozinha)
	VALUES ('RESTAURANTE 01',1);
INSERT INTO algafoodapi.restaurante (nome_restaurante,fk_cozinha)
	VALUES ('RESTAURANTE 02',3);

INSERT INTO algafoodapi.produto (ativo,nome,preco,restaurante_id)
	VALUES (1,'QUEIJO PARMESAO CILINDRO',55,1);
INSERT INTO algafoodapi.produto (ativo,nome,preco,restaurante_id)
	VALUES (1,'QUEIJO PARMESAO FAIXA AZUL',21,1);


INSERT INTO algafoodapi.pedido (data_cancelamento,data_confirmacao,data_criacao,bairro,cep,complemento,logradouro,numero,subtotal,taxa_frete,valor_total)
	VALUES ('2022-09-20 14:35:44.306','2022-09-20 15:35:52.928','2022-09-20 14:34:03.125','Marechal Rondon','41280640','Esquina com rua Curitiba','Tv Joel Lopes','11',500.0,80.0,580.0);
INSERT INTO algafoodapi.pedido (data_cancelamento,data_confirmacao,data_criacao,bairro,cep,complemento,logradouro,numero,subtotal,taxa_frete,valor_total)
	VALUES ('2022-09-20 14:37:43.557','2022-09-20 14:39:47.758','2022-09-20 14:41:01.233','Caji','4700000','Mais Galpões','Av Santo Amaro de Ipitanga','3250',925.0,75.0,1000.0);


INSERT INTO algafoodapi.grupo_permissao (fk_grupo,fk_permissao)
	VALUES (1,1);
INSERT INTO algafoodapi.grupo_permissao (fk_grupo,fk_permissao)
	VALUES (2,1);
INSERT INTO algafoodapi.grupo_permissao (fk_grupo,fk_permissao)
	VALUES (3,2);


INSERT INTO algafoodapi.usuario_grupo (fk_usuario,fk_grupo)
	VALUES (1,1);
INSERT INTO algafoodapi.usuario_grupo (fk_usuario,fk_grupo)
	VALUES (2,3);


	
set foreign_key_checks = 1;

