CREATE DATABASE  IF NOT EXISTS `library`;
USE `library`;

CREATE TABLE `usuario`
(
    `id`    varchar(36)  NOT NULL,
    `login` varchar(20)  NOT NULL,
    `senha` varchar(300) NOT NULL,
    `email` varchar(150) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_usuario_login` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usuario_roles`
(
    `usuario_id` varchar(36) NOT NULL,
    `roles`      varchar(255) DEFAULT NULL,
    KEY          `fk_usuario_roles` (`usuario_id`),
    CONSTRAINT `fk_usuario_roles` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `autor`
(
    `id`               varchar(36)  NOT NULL,
    `nome`             varchar(100) NOT NULL,
    `data_nascimento`  date         NOT NULL,
    `nacionalidade`    varchar(50)  NOT NULL,
    `data_cadastro`    datetime    DEFAULT NULL,
    `data_atualizacao` datetime    DEFAULT NULL,
    `id_usuario`       varchar(36) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                `FKm3mof10o9v4esv0wmv8gusaue` (`id_usuario`),
    CONSTRAINT `FKm3mof10o9v4esv0wmv8gusaue` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `livro`
(
    `id`               varchar(36)  NOT NULL,
    `isbn`             varchar(20)  NOT NULL,
    `titulo`           varchar(150) NOT NULL,
    `data_publicacao`  date         NOT NULL,
    `genero`           varchar(30)  NOT NULL,
    `preco`            decimal(18, 2) DEFAULT NULL,
    `data_cadastro`    datetime       DEFAULT CURRENT_TIMESTAMP,
    `data_atualizacao` datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `id_usuario`       varchar(36)    DEFAULT NULL,
    `id_autor`         varchar(36)  NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `isbn` (`isbn`),
    KEY                `fk_autor` (`id_autor`),
    KEY                `FKhej6dc3g148tvhv818ygpe2au` (`id_usuario`),
    CONSTRAINT `fk_autor` FOREIGN KEY (`id_autor`) REFERENCES `autor` (`id`),
    CONSTRAINT `FKhej6dc3g148tvhv818ygpe2au` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
    CONSTRAINT `chk_genero` CHECK ((`genero` in
                                    (_utf8mb4'FICCAO', _utf8mb4'FANTASIA', _utf8mb4'MISTERIO', _utf8mb4'ROMANCE',
                                     _utf8mb4'BIOGRAFIA', _utf8mb4'CIENCIA')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `client`
(
    `id`            varchar(36)  NOT NULL,
    `client_id`     varchar(150) NOT NULL,
    `client_secret` varchar(400) NOT NULL,
    `redirect_uri`  varchar(200) NOT NULL,
    `scope`         varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `client_id` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;