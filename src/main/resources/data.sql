INSERT INTO atividades (nome, descricao, data_hora_inicio, data_hora_termino, status, qtd_views)
VALUES
    ('Estudar Lombok', 'Revisar conceitos de Lombok e suas anotações úteis', '2024-12-10 10:00:00', '2024-12-10 12:00:00', 'PENDENTE', 0),
    ('Planejar Projeto', 'Definir cronograma do projeto e responsabilidades', '2024-12-11 09:00:00', '2024-12-11 11:00:00', 'CONCLUIDA', 0),
    ('Reunião de Equipe', 'Discutir as metas e obstáculos da semana', '2024-12-12 14:00:00', '2024-12-12 15:30:00', 'PENDENTE', 0),
    ('Apresentação de Resultados', 'Mostrar os progressos alcançados ao cliente', '2024-12-13 10:00:00', '2024-12-13 12:00:00', 'CANCELADA', 0),
    ('Atualizar Documentação', 'Revisar e atualizar a documentação do sistema', '2024-12-14 16:00:00', '2024-12-14 18:00:00', 'CONCLUIDA', 0)
ON CONFLICT DO NOTHING;
