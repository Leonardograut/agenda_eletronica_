    CREATE TABLE usuarios (
     id_usuario SERIAL PRIMARY KEY,
     nome VARCHAR(100)NOT NULL,
     email VARCHAR(255) UNIQUE TRUE,
     password VARCHAR(100) NOT NULL,
     user_role VARCHAR (50) NOT NULL(user_role IN ("USER"))
    )