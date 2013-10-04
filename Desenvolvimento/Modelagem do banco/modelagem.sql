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

-- INSERT INTO predio (id_predio,nome_predio,texto_descricao) VALUES
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

