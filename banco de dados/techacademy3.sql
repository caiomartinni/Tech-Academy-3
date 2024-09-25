-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 25/09/2024 às 13:31
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `techacademy3`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `cenas`
--

CREATE TABLE `cenas` (
  `id` int(11) NOT NULL,
  `descricao` text DEFAULT NULL,
  `opcoes` text DEFAULT NULL,
  `proximasCenas` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cenas`
--

INSERT INTO `cenas` (`id`, `descricao`, `opcoes`, `proximasCenas`) VALUES
(1, 'Dia 4 de Agosto de 2001. Olá, me chamo Henri e este é meu diário. Acordei em um dia muito estranho, com um clima vazio e sombrio. Devo olhar a JANELA ou procurar meus PAIS?', 'Olhar a Janela, Procurar meus pais', '2,3'),
(2, 'Abri a cortina, mas só vi um vazio imenso, um clima muito gelado e uma neblina obscura. Agora vou procurar meus PAIS para saber o que está acontecendo.', 'Procurar meus pais', '3'),
(3, 'Abri a porta e fui em direção ao quarto dos meus pais. A casa está muito escura. Devo procurar uma LANTERNA ou continuar no escuro?', 'Procurar lanterna, Continuar no escuro', '20,5'),
(4, 'Estava muito escuro, agora com a luz da lanterna consigo ver alguma coisa, vou procurar meus PAIS, mas não há ninguém no quarto. Devo procurar meu CELULAR ou continuar procurando pela casa?', 'Procurar celular, Procurar pela casa', '6,7,5'),
(5, 'Você tentou continuar no escuro, mas acabou se perdendo e ficou muito confuso. Talvez seja melhor procurar uma lanterna.', 'Voltar para procurar uma lanterna', '3'),
(6, 'Peguei meu celular, mas está sem sinal. Vou ter que continuar procurando pela casa.', 'Procurar pela casa', '7'),
(7, 'Ao abrir a porta do corredor, encontrei meus pais sentados no sofá. A TV exibe uma imagem toda chuviscada e o cheiro de sangue está no ar. Devo CONVERSAR com eles ou ignorar meu medo?', 'Conversar com os pais', '8'),
(8, 'Cheguei perto e o cheiro de sangue aumentou. Meus pais estavam mortos, com os órgãos expostos. Corri desesperadamente para meu quarto para pensar no que fazer.', 'Correr para o quarto', '9'),
(9, 'Liguei o computador e descobri que o mundo está em um apocalipse zumbi. Sem sinal no celular, talvez eu deva usar o TELEFONE-FIXO da casa.', 'Usar telefone-fixo', '10'),
(10, 'Após ligar para a polícia, eles dizem para correr para a delegacia. Devo seguir o conselho e correr para a DELEGACIA, ou investigar o barulho na sala?', 'Ir para a delegacia, Investigar barulho', '11,12'),
(11, 'Você correu para a delegacia e conseguiu se salvar. O mundo está em caos, mas pelo menos você está seguro por enquanto. Fim de Jogo - Você sobreviveu!', '', ''),
(12, 'Você decidiu investigar o barulho e encontrou seus pais transformados em zumbis. Antes de poder reagir, eles te atacam. Fim de Jogo - Você morreu!', '', ''),
(20, 'Que maravilha pelo menos algo deu certo, achei uma lanterna! Agora preciso usá-la!', 'Utilizar lanterna', '4');

-- --------------------------------------------------------

--
-- Estrutura para tabela `itens`
--

CREATE TABLE `itens` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `descricao` text DEFAULT NULL,
  `cena_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `itens`
--

INSERT INTO `itens` (`id`, `nome`, `descricao`, `cena_id`) VALUES
(1, 'Lanterna', 'Uma lanterna útil para iluminar áreas escuras', 20),
(2, 'Celular', 'Um celular, mas sem sinal no momento', 6);

-- --------------------------------------------------------

--
-- Estrutura para tabela `progresso`
--

CREATE TABLE `progresso` (
  `id` int(11) NOT NULL,
  `jogador` varchar(50) DEFAULT NULL,
  `cena_atual` int(11) DEFAULT NULL,
  `inventario` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `cenas`
--
ALTER TABLE `cenas`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `itens`
--
ALTER TABLE `itens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cena_id` (`cena_id`);

--
-- Índices de tabela `progresso`
--
ALTER TABLE `progresso`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `progresso`
--
ALTER TABLE `progresso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `itens`
--
ALTER TABLE `itens`
  ADD CONSTRAINT `itens_ibfk_1` FOREIGN KEY (`cena_id`) REFERENCES `cenas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
