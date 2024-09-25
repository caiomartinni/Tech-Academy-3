create database TechAcademy3; 

create table cena(
id_cena int primary key not null,
texto_cena varchar(255) not null
);

create table item(
id_item int primary key not null,
nome_item varchar(60)
);

alter table cena 
add constraint fk_cena
foreign key (item_id) references item (id_item)


ALTER TABLE cena 
ADD item_id INT NULL; 

insert into cena (id_cena, texto_cena) values
(10, 'Abri a cortina e não conseguir ver nada direito, somente um vazio imenso, e um clima muito gelado, e com uma neblina muito obscura, agora vou falar com meus PAIS, para ver o porque esta esse clima.'),
(11, 'Está tudo muito escuro, não tem como eu andar nessa casa sem pegar uma LANTERNA para iluminar meu caminho, quero logo descobrir porque está sem luz, pois as luzes do corredor nunca se apagam.'),
(12, 'Peguei meu celular, mas esta sem sinal, que droga! Então estou sem escolhas, vou PROCURAR eles na casa, vai que perderam o sono e estao assistindo TV.');

select * from cena

create table inventario(
id_save int primary key not null,
id_cena_atual int
);

select * from inventario

delete table cena;

drop table cena

drop table item


create table Cenas(
idCena int primary key auto_increment,
descricao text,
opcoes text,
proximasCenas text
);

drop table inventario;

ALTER TABLE techacademy3.cenas CHANGE id id int(11) NOT NULL;

insert into cenas (id, descricao, opcoes, proximasCenas) values
(1, 'Dia 4 de Agosto de 2001. Olá, me chamo Henri e este é meu diário. Acordei em um dia muito estranho, com um clima vazio e sombrio. Devo olhar a JANELA ou procurar meus PAIS?','"Olhar a Janela", "Procurar meus pais"', '2, 3'),
(2, 'Abri a cortina, mas só vi um vazio imenso, um clima muito gelado e uma neblina obscura. Agora vou procurar meus PAIS para saber o que está acontecendo.', '"Procurar meus pais"', '3'),
(3, 'Abri a porta e fui em direção ao quarto dos meus pais. A casa está muito escura. Devo procurar uma LANTERNA ou continuar no escuro?', '"Procurar lanterna", "Continuar no escuro"', '20, 5'),
(20, 'Que maravilha pelo menos algo deu certo, achei uma lanterna! Agora preciso usá-la!', '"Utilizar lanterna"', '4'),
(4, 'Estava muito escuro, agora com a luz da lanterna consigo ver alguma coisa, vou procurar meus PAIS, mas não há ninguém no quarto. Devo procurar meu CELULAR ou continuar procurando pela casa?', '"Procurar celular", "Procurar pela casa",', '6, 7, 5'),
(5, 'Você tentou continuar no escuro, mas acabou se perdendo e ficou muito confuso. Talvez seja melhor procurar uma lanterna.', '"Voltar para procurar uma lanterna"', '3'),
(6, 'Peguei meu celular, mas está sem sinal. Vou ter que continuar procurando pela casa.', '"Procurar pela casa"', '7'),
(7, 'Ao abrir a porta do corredor, encontrei meus pais sentados no sofá. A TV exibe uma imagem toda chuviscada e o cheiro de sangue está no ar. Devo CONVERSAR com eles ou ignorar meu medo?', '"Conversar com os pais"', '8'),
(8, 'Cheguei perto e o cheiro de sangue aumentou. Meus pais estavam mortos, com os órgãos expostos. Corri desesperadamente para meu quarto para pensar no que fazer.', '"Correr para o quarto"', '9'),
(9, 'Liguei o computador e descobri que o mundo está em um apocalipse zumbi. Sem sinal no celular, talvez eu deva usar o TELEFONE-FIXO da casa.', '"Usar telefone-fixo"', '10'),
(10, 'Após ligar para a polícia, eles dizem para correr para a delegacia. Devo seguir o conselho e correr para a DELEGACIA, ou investigar o barulho na sala?', '"Ir para a delegacia", "Investigar barulho"', '11, 12'),
(11, 'Você correu para a delegacia e conseguiu se salvar. O mundo está em caos, mas pelo menos você está seguro por enquanto. Fim de Jogo - Você sobreviveu!', '', ''),
(12, 'Você decidiu investigar o barulho e encontrou seus pais transformados em zumbis. Antes de poder reagir, eles te atacam. Fim de Jogo - Você morreu!', '', '');

INSERT INTO cenas (id, descricao, opcoes, proximasCenas) VALUES
(1, 'Dia 4 de Agosto de 2001. Olá, me chamo Henri e este é meu diário. Acordei em um dia muito estranho, com um clima vazio e sombrio. Devo olhar a JANELA ou procurar meus PAIS?', 'Olhar a Janela, Procurar meus pais', '2,3'),
(2, 'Abri a cortina, mas só vi um vazio imenso, um clima muito gelado e uma neblina obscura. Agora vou procurar meus PAIS para saber o que está acontecendo.', 'Procurar meus pais', '3'),
(3, 'Abri a porta e fui em direção ao quarto dos meus pais. A casa está muito escura. Devo procurar uma LANTERNA ou continuar no escuro?', 'Procurar lanterna, Continuar no escuro', '20,5'),
(20, 'Que maravilha pelo menos algo deu certo, achei uma lanterna! Agora preciso usá-la!', 'Utilizar lanterna', '4'),
(4, 'Estava muito escuro, agora com a luz da lanterna consigo ver alguma coisa, vou procurar meus PAIS, mas não há ninguém no quarto. Devo procurar meu CELULAR ou continuar procurando pela casa?', 'Procurar celular, Procurar pela casa', '6,7,5'),
(5, 'Você tentou continuar no escuro, mas acabou se perdendo e ficou muito confuso. Talvez seja melhor procurar uma lanterna.', 'Voltar para procurar uma lanterna', '3'),
(6, 'Peguei meu celular, mas está sem sinal. Vou ter que continuar procurando pela casa.', 'Procurar pela casa', '7'),
(7, 'Ao abrir a porta do corredor, encontrei meus pais sentados no sofá. A TV exibe uma imagem toda chuviscada e o cheiro de sangue está no ar. Devo CONVERSAR com eles ou ignorar meu medo?', 'Conversar com os pais', '8'),
(8, 'Cheguei perto e o cheiro de sangue aumentou. Meus pais estavam mortos, com os órgãos expostos. Corri desesperadamente para meu quarto para pensar no que fazer.', 'Correr para o quarto', '9'),
(9, 'Liguei o computador e descobri que o mundo está em um apocalipse zumbi. Sem sinal no celular, talvez eu deva usar o TELEFONE-FIXO da casa.', 'Usar telefone-fixo', '10'),
(10, 'Após ligar para a polícia, eles dizem para correr para a delegacia. Devo seguir o conselho e correr para a DELEGACIA, ou investigar o barulho na sala?', 'Ir para a delegacia, Investigar barulho', '11,12'),
(11, 'Você correu para a delegacia e conseguiu se salvar. O mundo está em caos, mas pelo menos você está seguro por enquanto. Fim de Jogo - Você sobreviveu!', '', ''),
(12, 'Você decidiu investigar o barulho e encontrou seus pais transformados em zumbis. Antes de poder reagir, eles te atacam. Fim de Jogo - Você morreu!', '', '');





