# 🏞️ Clube de Campo – Sistema de Gestão de Associados e Dependentes

[![Java Version](https://img.shields.io/badge/Java-21-blue)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-green)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

Sistema web para gerenciamento de associados e dependentes em clube de campo, desenvolvido em **Java com Spring Boot**.
---

## 🚀 Tecnologias Utilizadas

| Categoria       | Tecnologias                                                                 |
|-----------------|-----------------------------------------------------------------------------|
| Backend         | Java 21, Spring Boot 3, Spring Data JPA, Hibernate, MapStruct, Bean Validation |
| Banco de Dados  | PostgreSQL                                                                  |
| Ferramentas     | Docker, Docker Compose, Maven, Postman                                      |


---

## ⚙️ Funcionalidades

- ✅ Cadastro de associados
- ✅ Cadastro de dependentes vinculados a um associado
- ✅ Atualização parcial com estratégia de `@BeanMapping` e `NullValuePropertyMappingStrategy.IGNORE`
- ✅ Validações personalizadas (evitar RG duplicado, obrigatoriedade de dados, etc)
- ✅ Busca de dependentes com retorno em DTOs simplificados
- ✅ Integração com MapStruct para mapeamento entre entidades e DTOs

---

## 🛠️ Como Rodar Localmente

### 1. Pré-requisitos

- Java 21
- Maven
- PostgreSQL (ou use Docker)
- Docker (opcional)

### 2. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/clube-de-campo.git
cd clube-de-campo
```

### 3. Subir o Banco de Dados via DockerCompose

```bash
docker compose up -d
```
<sub>💡 Isso subirá o banco PostgreSQL configurado com usuário, senha e database corretos (veja o application.yml).</sub>

### 4. Rodar com Maven

```bash
./mvnw spring-boot:run
```

## 🗃️ Estrutura de Pacotes

```text
com.clubedecampo
├── 📁 config       # Configurações da aplicação
├── 📁 controller   # Controladores da API
├── 📁 converter    # Conversores de dados
├── 📁 dtos         # Objetos de Transferência de Dados
├── 📁 entity       # Entidades do domínio
├── 📁 exception    # Tratamento de exceções
├── 📁 mappers      # Mapeadores de objetos
├── 📁 repository   # Repositórios de dados
├── 📁 service      # Lógica de negócio
└── 📁 validator    # Validações customizadas
```

## 🌐 Endpoints da API

A API segue o padrão RESTful e utiliza JSON para troca de informações. Todos os endpoints retornam `application/json`.

### 📌 Rotas Principais

```text
📦 API
├── 📁 /areas          - Gestão de áreas do clube
├── 📁 /associados     - Gestão de associados
├── 📁 /dependentes    - Gestão de dependentes
├── 📁 /mensalidades   - Gestão de mensalidades
├── 📁 /pagamentos     - Gestão de pagamentos
└── 📁 /reservas       - Gestão de reservas de área
```

### 🏗️ Estrutura Comum dos Endpoints

#### Todos os endpoints seguem o mesmo padrão de operações:

```http request
GET    /{recurso}          - Lista todos os registros
GET    /{recurso}/{id}     - Obtém um registro específico
POST   /{recurso}          - Cria um novo registro
PUT    /{recurso}/{id}     - Atualiza um registro existente
DELETE /{recurso}/{id}     - Remove um registro
```

### 📚 Detalhes dos Endpoints

#### 🏞️ Áreas (/areas)

```http request
POST   /areas
Payload: { "nome": string, "quantidadeDisponivel": number, "reservavel": boolean, "tipoReserva": string }

GET    /areas/{id}

PUT    /areas/{id}
Payload: { "nome": string (opcional), "quantidadeDisponivel": number (opcional), ... }

DELETE /areas/{id}
```

#### 👥 Associados (/associados)

```http request
POST   /associados
Payload: { "nome": string, "cpf": string, "rg": string, ... }

GET    /associados/{id}

PUT    /associados/{id}
Payload: { "nome": string (opcional), "cpf": string (opcional), ... }

DELETE /associados/{id}
```

#### 👶 Dependentes (/dependentes)

```http request
POST   /dependentes
Payload: { "nome": string, "rg": string, "associadoId": UUID }

GET    /dependentes/{id}

PUT    /dependentes/{id}
Payload: { "nome": string (opcional), "rg": string (opcional), "associadoId": UUID (opcional) }

DELETE /dependentes/{id}
```

#### 💰 Mensalidades (/mensalidades)

```http request
POST   /mensalidades
Payload: { "valorOriginal": number, "dataVencimento": string, "associadoId": UUID }

GET    /mensalidades/{id}

PUT    /mensalidades/{id}
Payload: { "valorCorrigido": number (opcional), "status": string (opcional), ... }

DELETE /mensalidades/{id}
```

#### 💳 Pagamentos (/pagamentos)

```http request
POST   /reservas
Payload: { "dataReserva": string, "horaInicio": string, "horaFim": string, "numeroPessoas": number, "associadoId": UUID, "areaId": UUID }

GET    /pagamentos/{id}

PUT    /pagamentos/{id}
Payload: { "status": string (opcional) }

DELETE /pagamentos/{id}
```

#### 🗓️ Reservas (/reservas)

```http request
POST   /reservas
Payload: { "dataReserva": string, "horaInicio": string, "horaFim": string, "numeroPessoas": number, "associadoId": UUID, "areaId": UUID }

GET    /reservas/{id}

PUT    /reservas/{id}
Payload: { "dataReserva": string (opcional), "horaInicio": string (opcional), ... }

DELETE /reservas/{id}
```

### 🔄 Respostas da API

A API utiliza os seguintes códigos HTTP e padrões de resposta:

#### ✅ Sucesso
| Código | Status        | Descrição                                                                 | Exemplo de Uso                      |
|--------|---------------|---------------------------------------------------------------------------|-------------------------------------|
| 200    | OK            | Requisição bem-sucedida com retorno de conteúdo                           | GET /{recurso}/{id}                 |
| 201    | Created       | Recurso criado com sucesso. Inclui header `Location` com URL do recurso   | POST /{recurso}                     |
| 204    | No Content    | Operação bem-sucedida sem conteúdo para retornar                          | PUT /{recurso}/{id}, DELETE /{recurso}/{id} |

#### ⚠️ Erros Cliente (4xx)
| Código | Status         | Descrição                                                                 | Estrutura de Resposta               |
|--------|----------------|---------------------------------------------------------------------------|-------------------------------------|
| 400    | Bad Request    | Validação falhou ou requisição malformada                                 | ```json
```http
{
"status": 400,
"mensagem": "Erro de validação",
"erros": [
"Campo 'nome' é obrigatório",
"Campo 'cpf' inválido"
]
}

```http |
| 404    | Not Found      | Recurso não encontrado                                                    | ```json
{
  "status": 404,
  "mensagem": "Área não cadastrada",
  "erros": []
}
``` 


#### 🛑 Erros Servidor (5xx)
| Código | Status         | Descrição                                                                 |
|--------|----------------|---------------------------------------------------------------------------|
| 500    | Internal Error | Erro interno do servidor (logs contêm detalhes)                          |

#### 📝 Exemplo Completo
**Requisição:**
```http
POST /areas
Content-Type: application/json

{
  "nome": "Quadra de Tênis",
  "quantidadeDisponivel": 2,
  "reservavel": true
}
```
**Resposta de Sucesso**
```http
HTTP/1.1 201 Created
Location: /areas/3fa85f64-5717-4562-b3fc-2c963f66afa6
Content-Length: 0
```
**Resposta de Erro**
```http
HTTP/1.1 400 Bad Request
Content-Type: application/json

{
  "status": 400,
  "mensagem": "Erro de validação",
  "erros": [
    "quantidadeDisponivel: campo obrigatório",
    "tipoReserva: campo fora do tamanho padrão"
  ]
}
```