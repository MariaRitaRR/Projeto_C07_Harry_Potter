CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8;
USE `mydb`;

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


-- Inserções na tabela Casas --
INSERT INTO Casas (nome, fundador, cores, mascote, fantasma) VALUES 
('Grifinória', 'Godric Gryffindor', 'Vermelho e Dourado', 'Leão', 'Nick Quase-Sem-Cabeça'),
('Sonserina', 'Salazar Slytherin', 'Verde e Prata', 'Serpente', 'Barão Sangrento');

-- Inserções na tabela Varinhas --
INSERT INTO Varinhas (nucleo, madeira, comprimento, flexibilidade) VALUES 
('Pena de Fênix', 'Azevinho', 28.5, 'Rígida'),
('Pelo de Unicórnio', 'Salgueiro', 26, 'Flexível');

-- Inserções na tabela Bruxos --
INSERT INTO Bruxos (nome, idade, Casas_idCasa, Varinhas_idVarinha) VALUES 
('Harry Potter', 17, 1, 1),
('Draco Malfoy', 17, 2, 2);

-- Inserções na tabela Feiticos --
INSERT INTO Feiticos (nome, efeito, nivelDificuldade) VALUES 
('Expelliarmus', 'Desarma o oponente', 'Intermediário'),
('Avada Kedavra', 'Mata instantaneamente', 'Avançado');


-- Inserções na tabela CriaturasMagicas --
INSERT INTO CriaturasMagicas (nome, periculosidade, habitat) VALUES 
('Basilisco', 'Extremamente perigoso', 'Câmaras secretas'),
('Hipogrifo', 'Moderadamente perigoso', 'Florestas');

-- Inserções na tabela Feiticos_has_Bruxos --
INSERT INTO Feiticos_has_Bruxos (Feiticos_idFeitico, Bruxos_idBruxos, nivelPericia) VALUES 
(1, 1, 'Avançado'),
(2, 2, 'Intermediário');

-- Inserções na tabela Bruxos_has_CriaturasMagicas --
INSERT INTO Bruxos_has_CriaturasMagicas (Bruxos_idBruxos, CriaturasMagicas_idCriatura, dataDeEncontro, statusDeInteracao) VALUES 
(1, 1, '1993-05-29', 'Derrotado'),
(2, 2, '1994-06-06', 'Domado');

-- Atualizações na tabela Casas --
UPDATE Casas SET mascote = 'Leão Dourado' WHERE idCasa = 1;
UPDATE Casas SET fantasma = 'Barão Sanguinário' WHERE idCasa = 2;

-- Atualizações na tabela Varinhas --
UPDATE Varinhas SET comprimento = 29.0 WHERE idVarinha = 1;
UPDATE Varinhas SET flexibilidade = 'Muito Flexível' WHERE idVarinha = 2;

-- Atualizações na tabela Bruxos --
UPDATE Bruxos SET idade = 18 WHERE idBruxos = 1;
UPDATE Bruxos SET nome = 'Draco Lucius Malfoy' WHERE idBruxos = 2;

-- Atualizações na tabela Feiticos --
UPDATE Feiticos SET nivelDificuldade = 'Fácil' WHERE idFeitico = 1;
UPDATE Feiticos SET efeito = 'Maldição da morte' WHERE idFeitico = 2;

-- Atualizações na tabela CriaturasMagicas --
UPDATE CriaturasMagicas SET periculosidade = 'Mortal' WHERE idCriatura = 1;
UPDATE CriaturasMagicas SET habitat = 'Terras selvagens' WHERE idCriatura = 2;

-- Atualizações nas tabelas de relacionamento --
UPDATE Feiticos_has_Bruxos SET nivelPericia = 'Mestre' WHERE Bruxos_idBruxos = 1;
UPDATE Bruxos_has_CriaturasMagicas SET statusDeInteracao = 'Morto' WHERE Bruxos_idBruxos = 1;

-- Exclusões na tabela Casas (feito uma mudança para os bruxos pertencentes a casa excluida não serem excluidos) --
UPDATE Bruxos SET Casas_idCasa = 2 WHERE Casas_idCasa = 1;
DELETE FROM Casas WHERE idCasa = 1;

-- Exclusões nas tabelas de relacionamento --
DELETE FROM Feiticos_has_Bruxos WHERE Bruxos_idBruxos = 2;
DELETE FROM Bruxos_has_CriaturasMagicas WHERE Bruxos_idBruxos = 2;


-- Listar bruxos, suas casas e varinhas --
SELECT b.nome AS Bruxo, c.nome AS Casa, v.madeira AS Madeira_Varinha, v.nucleo AS Nucleo_Varinha
FROM Bruxos b
JOIN Casas c ON b.Casas_idCasa = c.idCasa
LEFT JOIN Varinhas v ON b.Varinhas_idVarinha = v.idVarinha;

-- Listar bruxos, feitiços que conhecem e nível de perícia -- 
SELECT b.nome AS Bruxo, f.nome AS Feitico, f.efeito, fb.nivelPericia
FROM Bruxos b
JOIN Feiticos_has_Bruxos fb ON b.idBruxos = fb.Bruxos_idBruxos
JOIN Feiticos f ON fb.Feiticos_idFeitico = f.idFeitico;

-- Listar interações de bruxos com criaturas mágicas --
SELECT b.nome AS Bruxo, c.nome AS Criatura, c.periculosidade, bc.dataDeEncontro, bc.statusDeInteracao
FROM Bruxos b
JOIN Bruxos_has_CriaturasMagicas bc ON b.idBruxos = bc.Bruxos_idBruxos
JOIN CriaturasMagicas c ON bc.CriaturasMagicas_idCriatura = c.idCriatura;


-- VIEW: Bruxos com informações completas --
-- Justificativa: Esta view será usada frequentemente em relatórios que necessitam
-- de informações consolidadas sobre os bruxos, evitando repetição de JOINs complexos
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

-- TRIGGER: Registrar data de cadastro de novos bruxos (adicionando coluna primeiro) --
-- Justificativa: Automatiza o registro temporal de quando um bruxo foi cadastrado,
-- garantindo consistência nos dados e evitando esquecimento deste campo.
ALTER TABLE Bruxos ADD COLUMN data_cadastro DATETIME;

DELIMITER //
CREATE TRIGGER tr_bruxos_antes_inserir
BEFORE INSERT ON Bruxos
FOR EACH ROW
BEGIN
    SET NEW.data_cadastro = NOW();
END//
DELIMITER ;

INSERT INTO Bruxos (nome, idade, Casas_idCasa) VALUES ('Hermione Granger', 18, 1);
SELECT * FROM Bruxos WHERE nome = 'Hermione Granger';