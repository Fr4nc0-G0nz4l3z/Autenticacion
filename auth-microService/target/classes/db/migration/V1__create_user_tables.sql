--Creacion de usaurios-- 
CREATE TABLE usuarios(
    id BEGIN AUTO_INCREMET PRIMARY KEY,
    usuarioNombre VARCHAR(60)NOT NULL UNIQUE,
    clave VARCHAR(20) NOT NULL
);

--Creacion de los roles de los usuarios--
CREATE TABLE roll_usuarios(
    usuario_id BIGINT NOT NULL,
    roll VARCHAR(50) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    PRIMARY KEY (usuario_id, roll)
) 

