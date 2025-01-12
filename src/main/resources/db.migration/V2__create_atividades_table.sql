CREATE TABLE atividades (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data_hora_inicio TIMESTAMP NOT NULL,
    data_hora_termino TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL CHECK (status IN ('PENDENTE', 'CONCLUIDA', 'CANCELADA')),
    qtd_views INTEGER NOT NULL DEFAULT 0
);


