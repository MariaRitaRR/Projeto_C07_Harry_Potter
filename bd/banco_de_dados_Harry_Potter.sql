-- Criação do schema e uso do banco de dados
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8;
USE `mydb`;


create user 'vizualiza'@'localhost' identified by '1234';
grant select on mydb.* to 'vizualiza'@'localhost';
grant insert on mydb.* to 'vizualiza'@'localhost';

-- -----------------------------------------------------
-- Table `Casas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Casas` (
  `idCasa` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `fundador` VARCHAR(45) NULL,
  `cores` VARCHAR(100) NULL,
  `mascote` VARCHAR(45) NULL,
  `fantasma` VARCHAR(45) NULL,
  PRIMARY KEY (`idCasa`)
);

-- -----------------------------------------------------
-- Table `Varinhas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Varinhas` (
  `idVarinha` INT NOT NULL AUTO_INCREMENT,
  `nucleo` VARCHAR(45) NULL,
  `madeira` VARCHAR(45) NULL,
  `comprimento` FLOAT NULL,
  `flexibilidade` VARCHAR(100) NULL,
  PRIMARY KEY (`idVarinha`)
);

-- -----------------------------------------------------
-- Table `Bruxos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bruxos` (
  `idBruxos` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `idade` INT NULL,
  `Casas_idCasa` INT NOT NULL,
  `Varinhas_idVarinha` INT NULL, 
  `data_cadastro` DATETIME NULL,
  PRIMARY KEY (`idBruxos`),
  INDEX `fk_Bruxos_Casas_idx` (`Casas_idCasa` ASC) VISIBLE,
  INDEX `fk_Bruxos_Varinhas1_idx` (`Varinhas_idVarinha` ASC) VISIBLE,
  CONSTRAINT `fk_Bruxos_Casas`
    FOREIGN KEY (`Casas_idCasa`)
    REFERENCES `Casas` (`idCasa`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Bruxos_Varinhas1`
    FOREIGN KEY (`Varinhas_idVarinha`)
    REFERENCES `Varinhas` (`idVarinha`)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table `Feiticos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Feiticos` (
  `idFeitico` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `efeito` VARCHAR(100) NOT NULL,
  `nivelDificuldade` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idFeitico`)
);

-- -----------------------------------------------------
-- Table `CriaturasMagicas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CriaturasMagicas` (
  `idCriatura` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `periculosidade` VARCHAR(45) NULL,
  `habitat` VARCHAR(100) NULL,
  PRIMARY KEY (`idCriatura`)
);

-- -----------------------------------------------------
-- Table `Feiticos_has_Bruxos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Feiticos_has_Bruxos` (
  `Feiticos_idFeitico` INT NOT NULL,
  `Bruxos_idBruxos` INT NOT NULL,
  `nivelPericia` VARCHAR(45) NULL,
  PRIMARY KEY (`Feiticos_idFeitico`, `Bruxos_idBruxos`),
  CONSTRAINT `fk_Feiticos_has_Bruxos_Feiticos1`
    FOREIGN KEY (`Feiticos_idFeitico`)
    REFERENCES `Feiticos` (`idFeitico`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Feiticos_has_Bruxos_Bruxos1`
    FOREIGN KEY (`Bruxos_idBruxos`)
    REFERENCES `Bruxos` (`idBruxos`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table `Bruxos_has_CriaturasMagicas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bruxos_has_CriaturasMagicas` (
  `Bruxos_idBruxos` INT NOT NULL,
  `CriaturasMagicas_idCriatura` INT NOT NULL,
  `dataDeEncontro` DATE NULL,
  `statusDeInteracao` VARCHAR(45) NULL,
  PRIMARY KEY (`Bruxos_idBruxos`, `CriaturasMagicas_idCriatura`),
  CONSTRAINT `fk_Bruxos_has_CriaturasMagicas_Bruxos1`
    FOREIGN KEY (`Bruxos_idBruxos`)
    REFERENCES `Bruxos` (`idBruxos`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Bruxos_has_CriaturasMagicas_CriaturasMagicas1`
    FOREIGN KEY (`CriaturasMagicas_idCriatura`)
    REFERENCES `CriaturasMagicas` (`idCriatura`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- VIEW: Bruxos com informações completas
-- -----------------------------------------------------
CREATE VIEW vw_BruxosCompletos AS
SELECT 
    b.idBruxos,
    b.nome AS Nome_Bruxo,
    b.idade,
    c.nome AS Casa,
    c.fundador AS Fundador_Casa,
    v.madeira AS Madeira_Varinha,
    v.nucleo AS Nucleo_Varinha
FROM 
    Bruxos b
JOIN 
    Casas c ON b.Casas_idCasa = c.idCasa
LEFT JOIN 
    Varinhas v ON b.Varinhas_idVarinha = v.idVarinha;

-- -----------------------------------------------------
-- TRIGGER: Registrar data de cadastro de novos bruxos
-- -----------------------------------------------------
DELIMITER //
CREATE TRIGGER tr_bruxos_antes_inserir
BEFORE INSERT ON Bruxos
FOR EACH ROW
BEGIN
    SET NEW.data_cadastro = NOW();
END//
DELIMITER ;

-- -----------------------------------------------------
-- Inserções iniciais na tabela Casas
-- -----------------------------------------------------
INSERT INTO Casas (nome, fundador, cores, mascote, fantasma) VALUES 
('Grifinória', 'Godric Gryffindor', 'Vermelho e Dourado', 'Leão', 'Nick Quase-Sem-Cabeça'),
('Sonserina', 'Salazar Slytherin', 'Verde e Prata', 'Serpente', 'Barão Sangrento'),
('Corvinal', 'Rowena Ravenclaw', 'Azul e Bronze', 'Águia', 'Dama Cinzenta'),
('Lufa-Lufa', 'Helga Hufflepuff', 'Amarelo e Preto', 'Texugo', 'Frei Gorducho'),
('Beco Diagonal', NULL, 'Variadas', NULL, NULL);

-- -----------------------------------------------------
-- Inserções iniciais na tabela Varinhas
-- -----------------------------------------------------
INSERT INTO Varinhas (nucleo, madeira, comprimento, flexibilidade) VALUES 
('Pena de Fênix', 'Azevinho', 28.5, 'Rígida'),
('Pelo de Unicórnio', 'Salgueiro', 26, 'Flexível'),
('Pelo de Rabo de Testrálio', 'Teixo', 31.5, 'Rígida'),
('Fibra de Coração de Dragão', 'Carvalho', 30.0, 'Média'),
('Pena de Fênix', 'Videira', 26.5, 'Flexível'),
('Cabelo de Veela', 'Salgueiro', 27.0, 'Muito Flexível'),
('Núcleo Desconhecido', 'Sabugueiro', 38.0, 'Variável');

-- -----------------------------------------------------
-- Inserções iniciais na tabela Bruxos
-- -----------------------------------------------------
INSERT INTO Bruxos (nome, idade, Casas_idCasa, Varinhas_idVarinha) VALUES 
('Harry Potter', 17, 1, 1),
('Draco Malfoy', 17, 2, 2),
('Hermione Granger', 18, 3, 3),
('Ronald Weasley', 17, 1, 4),
('Luna Lovegood', 17, 3, 5),
('Cedrico Diggory', 18, 4, 6),
('Bellatrix Lestrange', 45, 2, 7),
('Alvo Dumbledore', 115, 1, NULL),
('Minerva McGonagall', 65, 1, NULL),
('Severo Snape', 38, 2, NULL);

-- -----------------------------------------------------
-- Inserções iniciais na tabela Feiticos
-- -----------------------------------------------------
INSERT INTO Feiticos (nome, efeito, nivelDificuldade) VALUES 
('Expelliarmus', 'Desarma o oponente', 'Intermediário'),
('Avada Kedavra', 'Mata instantaneamente', 'Avançado'),
('Lumos', 'Cria luz na ponta da varinha', 'Fácil'),
('Wingardium Leviosa', 'Levita objetos', 'Intermediário'),
('Expecto Patronum', 'Conjura um patrono protetor', 'Avançado'),
('Crucio', 'Maldição da dor', 'Avançado'),
('Imperio', 'Maldição de controle mental', 'Avançado'),
('Protego', 'Escudo protetor', 'Intermediário'),
('Accio', 'Chama objetos à distância', 'Intermediário'),
('Riddikulus', 'Transforma bichos-papões em algo engraçado', 'Fácil');

-- -----------------------------------------------------
-- Inserções iniciais na tabela CriaturasMagicas
-- -----------------------------------------------------
INSERT INTO CriaturasMagicas (nome, periculosidade, habitat) VALUES 
('Basilisco', 'Extremamente perigoso', 'Câmaras secretas'),
('Hipogrifo', 'Moderadamente perigoso', 'Florestas'),
('Dragão', 'Extremamente perigoso', 'Montanhas'),
('Fênix', 'Inofensivo', 'Florestas antigas'),
('Testrálio', 'Moderadamente perigoso', 'Florestas sombrias'),
('Veela', 'Perigoso quando provocado', 'Europa Oriental'),
('Elfo Doméstico', 'Inofensivo', 'Casas de bruxos'),
('Dementador', 'Extremamente perigoso', 'Azkaban'),
('Tronquilho', 'Moderadamente perigoso', 'Florestas'),
('Centauro', 'Perigoso quando provocado', 'Florestas');

-- -----------------------------------------------------
-- Inserções iniciais na tabela Feiticos_has_Bruxos
-- -----------------------------------------------------
-- Verifique primeiro quais IDs existem nas tabelas referenciadas
SELECT idFeitico FROM Feiticos;
SELECT idBruxos FROM Bruxos;

-- Depois ajuste sua inserção para usar apenas IDs existentes
INSERT INTO Feiticos_has_Bruxos (Feiticos_idFeitico, Bruxos_idBruxos, nivelPericia) VALUES 
(1, 1, 'Avançado'), 
(2, 2, 'Intermediário'), 
(3, 1, 'Avançado'), 
(4, 5, 'Mestre'), 
(5, 5, 'Mestre'), 
(6, 1, 'Intermediário'), 
(7, 1, 'Intermediário'), 
(8, 3, 'Avançado'), 
(1, 2, 'Intermediário'),  -- Alterado de 9 para 1 (feitico existente)
(2, 4, 'Avançado'),      -- Alterado de 10 para 2 (feitico existente)
(3, 6, 'Lendário'),      -- Alterado de 11 para 3 (feitico existente)
(4, 7, 'Mestre');        -- Alterado de 12 para 4 (feitico existente)
-- -----------------------------------------------------
-- Inserções iniciais na tabela Bruxos_has_CriaturasMagicas
-- -----------------------------------------------------
INSERT INTO Bruxos_has_CriaturasMagicas (Bruxos_idBruxos, CriaturasMagicas_idCriatura, dataDeEncontro, statusDeInteracao) VALUES 
(1, 1, '1993-05-29', 'Derrotado'),
(2, 2, '1994-06-06', 'Domado'),
(1, 3, '1995-06-24', 'Domado'),
(2, 4, '1994-11-22', 'Observado'),
(3, 5, '1996-03-15', 'Amigável'),
(4, 6, '1994-02-28', 'Respeitado'),
(5, 7, '1981-10-31', 'Aliado'),
(6, 8, '1945-08-12', 'Companheiro'),
(7, 9, '1970-09-01', 'Empregador'),
(8, 10, '1994-08-25', 'Admirador');

-- -----------------------------------------------------
-- Atualizações nas tabelas
-- -----------------------------------------------------
-- Atualizações na tabela Casas
UPDATE Casas SET mascote = 'Leão Dourado' WHERE idCasa = 1;
UPDATE Casas SET fantasma = 'Barão Sanguinário' WHERE idCasa = 2;

-- Atualizações na tabela Varinhas
UPDATE Varinhas SET comprimento = 29.0 WHERE idVarinha = 1;
UPDATE Varinhas SET flexibilidade = 'Muito Flexível' WHERE idVarinha = 2;

-- Atualizações na tabela Bruxos
UPDATE Bruxos SET idade = 18 WHERE idBruxos = 1;
UPDATE Bruxos SET nome = 'Draco Lucius Malfoy' WHERE idBruxos = 2;

-- Atualizações na tabela Feiticos
UPDATE Feiticos SET nivelDificuldade = 'Fácil' WHERE idFeitico = 1;
UPDATE Feiticos SET efeito = 'Maldição da morte' WHERE idFeitico = 2;

-- Atualizações na tabela CriaturasMagicas
UPDATE CriaturasMagicas SET periculosidade = 'Mortal' WHERE idCriatura = 1;
UPDATE CriaturasMagicas SET habitat = 'Terras selvagens' WHERE idCriatura = 2;

-- Atualizações nas tabelas de relacionamento
UPDATE Feiticos_has_Bruxos SET nivelPericia = 'Mestre' WHERE Bruxos_idBruxos = 1;
UPDATE Bruxos_has_CriaturasMagicas SET statusDeInteracao = 'Morto' WHERE Bruxos_idBruxos = 1;

-- -----------------------------------------------------
-- Exclusões de dados de teste
-- -----------------------------------------------------
-- Exclusões na tabela Casas (feito uma mudança para os bruxos pertencentes a casa excluida não serem excluidos)
UPDATE Bruxos SET Casas_idCasa = 2 WHERE Casas_idCasa = 1;
DELETE FROM Casas WHERE idCasa = 1;

-- Exclusões nas tabelas de relacionamento
DELETE FROM Feiticos_has_Bruxos WHERE Bruxos_idBruxos = 2;
DELETE FROM Bruxos_has_CriaturasMagicas WHERE Bruxos_idBruxos = 2;

-- -----------------------------------------------------
-- Consultas de exemplo
-- -----------------------------------------------------
-- Listar bruxos, suas casas e varinhas
SELECT b.nome AS Bruxo, c.nome AS Casa, v.madeira AS Madeira_Varinha, v.nucleo AS Nucleo_Varinha
FROM Bruxos b
JOIN Casas c ON b.Casas_idCasa = c.idCasa
LEFT JOIN Varinhas v ON b.Varinhas_idVarinha = v.idVarinha;

-- Listar bruxos, feitiços que conhecem e nível de perícia
SELECT b.nome AS Bruxo, f.nome AS Feitico, f.efeito, fb.nivelPericia
FROM Bruxos b
JOIN Feiticos_has_Bruxos fb ON b.idBruxos = fb.Bruxos_idBruxos
JOIN Feiticos f ON fb.Feiticos_idFeitico = f.idFeitico;

-- Listar interações de bruxos com criaturas mágicas
SELECT b.nome AS Bruxo, c.nome AS Criatura, c.periculosidade, bc.dataDeEncontro, bc.statusDeInteracao
FROM Bruxos b
JOIN Bruxos_has_CriaturasMagicas bc ON b.idBruxos = bc.Bruxos_idBruxos
JOIN CriaturasMagicas c ON bc.CriaturasMagicas_idCriatura = c.idCriatura;

-- Consulta para ver todas as casas e seus membros
SELECT c.nome AS Casa, COUNT(b.idBruxos) AS Membros
FROM Casas c
LEFT JOIN Bruxos b ON c.idCasa = b.Casas_idCasa
GROUP BY c.nome;

-- Consulta para ver feitiços mais comuns entre os bruxos
SELECT f.nome AS Feitico, COUNT(fb.Bruxos_idBruxos) AS BruxosQueConhecem
FROM Feiticos f
LEFT JOIN Feiticos_has_Bruxos fb ON f.idFeitico = fb.Feiticos_idFeitico
GROUP BY f.nome
ORDER BY BruxosQueConhecem DESC;

-- Consulta para ver interações com criaturas mágicas
SELECT cm.nome AS Criatura, cm.periculosidade, 
       COUNT(bc.Bruxos_idBruxos) AS InteracoesRegistradas
FROM CriaturasMagicas cm
LEFT JOIN Bruxos_has_CriaturasMagicas bc ON cm.idCriatura = bc.CriaturasMagicas_idCriatura
GROUP BY cm.nome, cm.periculosidade
ORDER BY cm.periculosidade DESC;


-- Consulta de Bruxos de cada casa --
SELECT b.idBruxos, b.nome, b.idade, c.nome AS casa 
FROM Bruxos b JOIN Casas c ON b.Casas_idCasa = c.idCasa
WHERE c.nome LIKE '%grif%';

-- Busca de Feitiços por Bruxos --
SELECT f.nome, f.efeito, fb.nivelPericia
FROM Feiticos_has_Bruxos fb
JOIN Feiticos f ON fb.Feiticos_idFeitico = f.idFeitico
JOIN Bruxos b ON fb.Bruxos_idBruxos = b.idBruxos
WHERE b.nome = 'Harry Potter';

-- Busca de Criaturas por Periculosidade --
SELECT nome, habitat FROM CriaturasMagicas 
WHERE periculosidade = 'Extremamente perigoso';

