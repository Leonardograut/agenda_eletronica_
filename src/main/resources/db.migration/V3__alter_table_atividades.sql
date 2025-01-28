ALTER TABLE atividades ADD COLUMN id_usuario BIGINT NOT NULL;

ALTER TABLE  atividades ADD CONSTRAINT fk_atividades_usuarios
FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario);