CREATE TABLE tipo_associado (
                                id UUID PRIMARY KEY,
                                tipo VARCHAR(20) NOT NULL UNIQUE,
                                valor_base NUMERIC(10,2) NOT NULL
);

CREATE TABLE associado (
                           id UUID PRIMARY KEY,
                           nome VARCHAR(100) NOT NULL,
                           rg VARCHAR(20) UNIQUE NOT NULL,
                           cpf VARCHAR(14) UNIQUE NOT NULL,
                           endereco VARCHAR(255) NOT NULL,
                           cep VARCHAR(10) NOT NULL,
                           bairro VARCHAR(50) NOT NULL,
                           cidade VARCHAR(50) NOT NULL,
                           estado VARCHAR(2) NOT NULL,
                           telefone_residencial VARCHAR(15),
                           telefone_comercial VARCHAR(15),
                           celular VARCHAR(15) NOT NULL,
                           data_cadastro DATE NOT NULL,
                           tipo_associado_id UUID REFERENCES tipo_associado(id),
                           status VARCHAR(20) DEFAULT 'ativo',
                           carteirinha_ativa BOOLEAN DEFAULT TRUE
);

CREATE TABLE dependente (
                            id UUID PRIMARY KEY,
                            nome VARCHAR(100) NOT NULL,
                            rg VARCHAR(20) UNIQUE NOT NULL,
                            associado_id UUID REFERENCES associado(id) ON DELETE CASCADE
);

CREATE TABLE area (
                      id UUID PRIMARY KEY,
                      nome VARCHAR(50) NOT NULL,
                      quantidade_disponivel INTEGER NOT NULL,
                      reservavel BOOLEAN DEFAULT TRUE,
                      tipo_reserva VARCHAR(20)
);

CREATE TABLE reserva (
                         id UUID PRIMARY KEY,
                         associado_id UUID REFERENCES associado(id),
                         area_id UUID REFERENCES area(id),
                         data_reserva DATE NOT NULL,
                         hora_inicio TIME,
                         hora_fim TIME,
                         numero_pessoas INTEGER
);

CREATE TABLE mensalidade (
                             id UUID PRIMARY KEY,
                             associado_id UUID REFERENCES associado(id),
                             mes_referencia DATE NOT NULL,
                             valor_base NUMERIC(10,2) NOT NULL,
                             valor_corrigido NUMERIC(10,2),
                             data_vencimento DATE NOT NULL,
                             data_pagamento DATE
);

CREATE TABLE pagamento (
                           id UUID PRIMARY KEY,
                           associado_id UUID REFERENCES associado(id),
                           valor NUMERIC(10,2) NOT NULL,
                           data_pagamento DATE NOT NULL,
                           forma_pagamento VARCHAR(20),
                           mensalidade_id UUID UNIQUE REFERENCES mensalidade(id)
);
