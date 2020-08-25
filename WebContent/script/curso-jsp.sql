create  table usuario(
login character varying,
senha character varying
)
with( 
	oids=false
);

create  table usuario2(
login character varying,
senha character varying,
id bigint not null default nextval('serialuser'::regclass)
)
with( 
	oids=false
);
alter table usuario2 owner to postgres;

create  table finalprojetos(
id integer not null default nextval('"final-projetos_id_seq"'::regclass),
datafinal character varying,
constraint "final-projetos_pkey" primary key (id)
)
with( 
	oids=false
);
alter table finalprojetos owner to postgres;

CREATE TABLE eventos (
	data character varying,
	descricao character varying,
	id bigint not null,
	constraint "eventos_pkey" primary key (id)
)with( 
	oids=false
);
alter table eventos owner to postgres;

CREATE TABLE projeto (
	
	id bigint not null,
	nome character varying,
	constraint "projeto_pkey" primary key (id)
)with( 
	oids=false
);
alter table projeto owner to postgres;


CREATE TABLE series (
	id bigint not null,
	nome character varying,
	datainicial character varying,
	datafinal character varying,
	projeto bigint,
	constraint "series_pkey" primary key (id),
	constraint "series_projeto_fkey" foreign key (projeto)
	references projeto (id) match simple
	on update no action on delete no action 
)with( 
	oids=false
);
alter table series owner to postgres;



alter table usuario add column imagem text;
alter table usuario add column tipofile character varying(20);

delete from usuario;

select * from usuario;

select * from eventos;

insert into usuario (login, senha ) values ('Giordano', '12345');

insert into eventos( descricao, id, dataevento ) values('Evento 1', 1, '2020-12-15');
insert into eventos( descricao, id, dataevento ) values('Evento 2', 2, '2020-08-26');
insert into eventos( descricao, id, dataevento ) values('Evento 3', 3, '2020-02-26');

insert into projeto( id, nome ) values(1, 'Projeto Java Web');
insert into projeto( id, nome ) values(2, 'Curso de AngularJS e Spring');
insert into series( id, nome, datainicial, datafinal, projeto ) values(1, 'Planejado', '2017-00-05', '2017-00-20', 1);
insert into series( id, nome, datainicial, datafinal, projeto ) values(2, 'Real', '2017-00-06', '2017-00-17', 1);
insert into series( id, nome, datainicial, datafinal, projeto ) values(3, 'Projetado', '2017-00-05', '2017-00-20', 1);
insert into series( id, nome, datainicial, datafinal, projeto ) values(4, 'Planejado', '2017-00-05', '2017-00-20', 2);



