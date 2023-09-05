INSERT INTO pessoa (nome, id) VALUES
    ('Paulo Vitor', 1L),
    ('Fouquet', 2L),
    ('Leonardo', 3L);

INSERT INTO papel (id, nome, prioridade) VALUES
    (1L, 'ADMINISTRADOR', 3L),
    (2L, 'SCRUM MASTER', 2L),
    (3L, 'DESENVOLVEDOR', 1L);

INSERT INTO usuario (Id_Pessoa, Id_Usuario, email) VALUES
    (1L, 1L, 'pvlb@gmail.com'),
    (1L, 2L, 'pvlb-dev@gmail.com'),
    (2L, 3L, 'Fqt@gmail.com'),
    (3L, 4L, 'LeonardoDavid@gmail.com');

INSERT INTO usuario_tem_papeis(usuario_id, papel_id) VALUES
    (1L, 2L),
    (1L, 3L),
    (2L, 3L),
    (3L, 3L),
    (4L, 1L);
