-- ---
-- DROP TABLES
-- 
-- ---

DROP TABLE IF EXISTS alocacao;

DROP TABLE IF EXISTS relacionamento_disciplina_horario;

DROP TABLE IF EXISTS horario;

DROP TABLE IF EXISTS turno;

DROP TABLE IF EXISTS disciplina;

DROP TABLE IF EXISTS periodo;

DROP TABLE IF EXISTS colegiado;

DROP TABLE IF EXISTS curso;

DROP TABLE IF EXISTS sala;

DROP TABLE IF EXISTS predio;

DROP TABLE IF EXISTS parametros;

-- ---
-- Table predio
-- 
-- ---

CREATE TABLE predio (
  id_predio SERIAL NOT NULL,
  nome_predio CHARACTER varying(255),
  texto_descricao CHARACTER varying(255),
  CONSTRAINT pk_predio PRIMARY KEY (id_predio)
);

-- ---
-- Table sala
-- 
-- ---

CREATE TABLE sala (
  id_sala SERIAL NOT NULL,
  id_predio INTEGER NOT NULL,
  numero_andar INTEGER NOT NULL,
  numero_vagas INTEGER NOT NULL,
  nome_sala CHARACTER varying(255),
  flag_iluminacao CHARACTER varying(15),
  flag_ativa CHARACTER varying(3),
  CONSTRAINT pk_sala PRIMARY KEY (id_sala)
);

-- ---
-- Table horario
-- 
-- ---

CREATE TABLE horario (
  id_horario SERIAL NOT NULL,
  id_turno INTEGER NOT NULL,
  horario_de CHARACTER varying(5),
  horario_ate CHARACTER varying(5),
  CONSTRAINT pk_horario PRIMARY KEY (id_horario)
);

-- ---
-- Table turno
-- 
-- ---

CREATE TABLE turno (
  id_turno SERIAL NOT NULL,
  texto_descricao CHARACTER varying(255),
  CONSTRAINT pk_turno PRIMARY KEY (id_turno)
);

-- ---
-- Table disciplina
-- 
-- ---

CREATE TABLE disciplina (
  id_disciplina SERIAL NOT NULL,
  id_periodo INTEGER NOT NULL,
  numero_vagas INTEGER NOT NULL,
  texto_codigo CHARACTER varying(255),
  nome_disciplina CHARACTER varying(255),
  texto_turma CHARACTER varying(255),
  flag_iluminacao CHARACTER varying(15),
  CONSTRAINT pk_disciplina PRIMARY KEY (id_disciplina)
);

-- ---
-- Table relacionamento_disciplina_horario
-- 
-- ---

CREATE TABLE relacionamento_disciplina_horario (
  id_relacionamento SERIAL NOT NULL,
  id_disciplina INTEGER NOT NULL,
  id_horario INTEGER NOT NULL,
  dia_horario INTEGER NOT NULL,
  flag_alocado BOOLEAN NOT NULL,
  CONSTRAINT pk_relacionamento_disciplina_horario PRIMARY KEY (id_relacionamento)
);

-- ---
-- Table curso
-- 
-- ---

CREATE TABLE curso (
  id_curso SERIAL NOT NULL,
  nome_curso  CHARACTER varying(255),
  texto_descricao  CHARACTER varying(255),
  CONSTRAINT pk_curso PRIMARY KEY (id_curso)
);

-- ---
-- Table colegiado
-- 
-- ---

CREATE TABLE colegiado (
  id_colegiado SERIAL NOT NULL,
  id_curso INTEGER NOT NULL,
  nome_colegiado  CHARACTER varying(255),
  texto_descricao  CHARACTER varying(255),
  tipo_colegiado  CHARACTER varying(100),
  CONSTRAINT pk_colegiado PRIMARY KEY (id_colegiado)
);

-- ---
-- Table periodo
-- 
-- ---

CREATE TABLE periodo (
  id_periodo SERIAL NOT NULL,
  id_colegiado INTEGER NOT NULL,
  texto_descricao  CHARACTER varying(255),
  flag_optativo CHARACTER varying(3),
  CONSTRAINT pk_periodo PRIMARY KEY (id_periodo)
);

-- ---
-- Table alocacao
-- 
-- ---

CREATE TABLE alocacao (
  id_alocacao SERIAL NOT NULL,
  id_sala INTEGER NOT NULL,
  id_horario INTEGER NOT NULL,
  dia_alocacao INTEGER NOT NULL,
  id_disciplina INTEGER,
  CONSTRAINT pk_alocacao PRIMARY KEY (id_alocacao)
);


-- ---
-- Table alocacao
-- 
-- ---
CREATE TABLE parametros (
  id_parametro SERIAL NOT NULL,
  elitismo BOOLEAN NOT nULL,
  taxa_crossover NUMERIC(2,2) NOT nULL,
  taxa_mutacao NUMERIC(2,2) NOT NULL,
  tamanho_populacao INTEGER NOT NULL,
  maximo_geracoes INTEGER NOT NULL,
  peso_discliplina_alocada INTEGER NOT NULL,
  peso_graduacao INTEGER NOT NULL,
  peso_pos INTEGER NOT NULL,
  peso_mesmo_periodo INTEGER NOT NULL,
  peso_capacidade INTEGER NOT NULL,
  peso_optativa INTEGER NOT NULL,
  peso_iluminacao INTEGER NOT NULL,
  CONSTRAINT pk_parametro PRIMARY KEY (id_parametro)
);



-- ---
-- Foreign Keys 
-- ---

ALTER TABLE sala ADD FOREIGN KEY (id_predio) REFERENCES predio (id_predio);
ALTER TABLE horario ADD FOREIGN KEY (id_turno) REFERENCES turno (id_turno);
ALTER TABLE disciplina ADD FOREIGN KEY (id_periodo) REFERENCES periodo (id_periodo);
ALTER TABLE relacionamento_disciplina_horario ADD FOREIGN KEY (id_disciplina) REFERENCES disciplina (id_disciplina);
ALTER TABLE relacionamento_disciplina_horario ADD FOREIGN KEY (id_horario) REFERENCES horario (id_horario);
ALTER TABLE colegiado ADD FOREIGN KEY (id_curso) REFERENCES curso (id_curso);
ALTER TABLE periodo ADD FOREIGN KEY (id_colegiado) REFERENCES colegiado (id_colegiado);
ALTER TABLE alocacao ADD FOREIGN KEY (id_sala) REFERENCES sala (id_sala);
ALTER TABLE alocacao ADD FOREIGN KEY (id_horario) REFERENCES horario (id_horario);
ALTER TABLE alocacao ADD FOREIGN KEY (id_disciplina) REFERENCES disciplina (id_disciplina);

INSERT INTO parametros (id_parametro,elitismo,taxa_crossover,taxa_mutacao,tamanho_populacao,maximo_geracoes,peso_discliplina_alocada,peso_graduacao,peso_pos,peso_mesmo_periodo,peso_capacidade,peso_optativa,peso_iluminacao) VALUES (1,true,0.6,0.3,100,50,1000,100,10,10,10,10,10);

INSERT INTO turno (id_turno, texto_descricao) VALUES (50, 'Manhã');
INSERT INTO turno (id_turno, texto_descricao) VALUES (51, 'Tarde');
INSERT INTO turno (id_turno, texto_descricao) VALUES (52, 'Noite');

SELECT pg_catalog.setval('turno_id_turno_seq', 52);

INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (50, 50, '7:30', '8:20');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (51, 50, '8:20', '9:10');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (52, 50, '9:30', '10:20');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (53, 50, '10:20', '11:10');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (54, 50, '11:10', '12:00');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (55, 51, '13:00', '13:45');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (56, 51, '13:50', '14:40');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (57, 51, '14:50', '15:40');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (58, 51, '15:40', '16:30');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (59, 51, '16:30', '17:20');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (60, 51, '17:20', '18:10');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (61, 52, '19:00', '19:50');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (62, 52, '19:50', '20:40');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (63, 52, '20:50', '21:40');
INSERT INTO horario (id_horario, id_turno, horario_de, horario_ate) VALUES (64, 52, '21:40', '22:30');

SELECT pg_catalog.setval('horario_id_horario_seq', 64);

INSERT INTO predio (id_predio, nome_predio, texto_descricao) VALUES (50, 'Fafich', 'Faculdade de Filosofia e Ciências Humanas');

SELECT pg_catalog.setval('predio_id_predio_seq', 50);

INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (50, 50, 1, 90, '1012', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (51, 50, 2, 75, '2060', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (52, 50, 2, 40, '2013', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (53, 50, 2, 60, '2017', 'Clara', 'Não');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (54, 50, 2, 40, '2019', 'Escura', 'Não');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (55, 50, 2, 60, '2045', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (56, 50, 2, 50, '2055', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (57, 50, 2, 40, '2070', 'Clara', 'Não');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (58, 50, 2, 40, '2072', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (59, 50, 2, 40, '2074', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (60, 50, 2, 60, '2076', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (61, 50, 2, 40, '2080', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (62, 50, 2, 40, '2082', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (63, 50, 2, 40, '2084', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (64, 50, 2, 60, '2090', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (65, 50, 2, 60, '2094', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (66, 50, 2, 40, '2096', 'Clara', 'Não');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (67, 50, 3, 20, '3001', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (68, 50, 3, 60, '3002', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (69, 50, 3, 30, '3003', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (70, 50, 3, 40, '3004', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (71, 50, 3, 30, '3005', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (72, 50, 3, 40, '3006', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (73, 50, 3, 40, '3008', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (74, 50, 3, 40, '3010', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (75, 50, 3, 40, '3011', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (76, 50, 3, 40, '3012', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (77, 50, 3, 40, '3014', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (78, 50, 3, 40, '3016', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (79, 50, 3, 40, '3018', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (80, 50, 3, 40, '3020', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (81, 50, 3, 30, '3022', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (82, 50, 3, 60, '3030', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (83, 50, 3, 60, '3032', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (84, 50, 3, 20, '3034', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (85, 50, 3, 30, '3036', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (86, 50, 3, 30, '3038', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (87, 50, 3, 40, '3040', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (88, 50, 3, 40, '3042', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (89, 50, 3, 40, '3044', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (90, 50, 3, 40, '3046', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (91, 50, 3, 40, '3048', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (92, 50, 3, 60, '3049', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (93, 50, 3, 45, '3050', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (94, 50, 3, 60, '3052', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (95, 50, 3, 60, '3054', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (96, 50, 3, 60, '3056', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (97, 50, 3, 30, '3068', 'Escura', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (98, 50, 3, 40, '3070', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (99, 50, 3, 60, '3100', 'Clara', 'Sim');
INSERT INTO sala (id_sala, id_predio, numero_andar, numero_vagas, nome_sala, flag_iluminacao, flag_ativa) VALUES (100, 50, 3, 40, '3112', 'Escura', 'Sim');

SELECT pg_catalog.setval('sala_id_sala_seq', 100);

-- ---
-- Table Properties
-- ---

-- ALTER TABLE predio ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE sala ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE horario ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE turno ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE disciplina ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE relacionamento_disciplina_horario ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE curso ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE colegiado ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE periodo ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE alocacao ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- 
-- (,,);
-- INSERT INTO sala (id_sala,id_predio,numero_andar,numero_vagas,nome_sala,flag_iluminacao,flag_ativa) VALUES
-- (,,,,,,);
-- INSERT INTO horario (id_horario,id_turno,horario_de,horario_ate) VALUES
-- (,,,);
-- INSERT INTO turno (id_turno,texto_descricao) VALUES
-- (,);
-- INSERT INTO disciplina (id_disciplina,id_periodo,numero_vagas,texto_codigo,nome_disciplina,texto_turma) VALUES
-- (,,,,,);
-- INSERT INTO relacionamento_disciplina_horario (id_relacionamento,id_disciplina,id_horario) VALUES
-- (,,);
-- INSERT INTO curso (id_curso,nome_curso,texto_descricao) VALUES
-- (,,);
-- INSERT INTO colegiado (id_colegiado,id_curso,nome_colegiado,texto_descricao) VALUES
-- (,,,);
-- INSERT INTO periodo (id_periodo,id_colegiado,nome_descricao,flag_optativo) VALUES
-- (,,,);
-- INSERT INTO alocacao (id_alocacao,id_sala,id_horario,id_disciplina) VALUES
-- (,,,);

