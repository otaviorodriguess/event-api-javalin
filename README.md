# 📅 Event API — Javalin

**API REST para Gerenciamento de Eventos com Java puro e SQLite**

[![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)](https://www.oracle.com/java/)
[![Javalin](https://img.shields.io/badge/Javalin-6.3.0-5B4FE9?style=for-the-badge)](https://javalin.io/)
[![SQLite](https://img.shields.io/badge/SQLite-3.45-003B57?style=for-the-badge&logo=sqlite)](https://www.sqlite.org/)
[![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven)](https://maven.apache.org/)

---

## 🚀 Sobre o Projeto

Esta API foi desenvolvida com foco em **leveza e simplicidade**, sem frameworks pesados.
Utiliza **Javalin** como servidor HTTP e **SQLite** como banco de dados embarcado, tornando
o projeto totalmente portátil — basta clonar e rodar.

### Principais Destaques:

- **Zero configuração de banco:** SQLite embarcado, banco criado automaticamente na primeira execução.
- **Arquitetura em camadas:** Separação clara entre `Model`, `Repository`, `Service` e `Controller`.
- **Código limpo e objetivo:** Sem anotações mágicas, tudo explícito e legível.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Javalin 6.3.0** — Framework HTTP leve e moderno
- **SQLite + JDBC** — Banco de dados embarcado
- **Jackson** — Serialização/desserialização JSON
- **Maven Shade Plugin** — Geração de JAR executável

---

## 🗂️ Estrutura do Projeto

```
src/main/java/com/eventapi/
├── config/
│   └── Database.java         # Conexão e inicialização do banco
├── model/
│   └── Event.java            # Entidade de evento
├── repository/
│   └── EventRepository.java  # Operações JDBC com o banco
├── service/
│   └── EventService.java     # Regras de negócio
├── controller/
│   └── EventController.java  # Handlers HTTP
└── Main.java                 # Bootstrap do servidor Javalin
```

---

## 🛣️ Endpoints da API

### 📅 Eventos

| Método   | Endpoint       | Descrição                        |
|----------|----------------|----------------------------------|
| `GET`    | `/events`      | Lista todos os eventos           |
| `GET`    | `/events/{id}` | Busca um evento pelo ID          |
| `POST`   | `/events`      | Cria um novo evento              |
| `PUT`    | `/events/{id}` | Atualiza um evento existente     |
| `DELETE` | `/events/{id}` | Remove um evento                 |

---

## 📋 Como Executar

**1. Clone o repositório:**
```bash
git clone https://github.com/otaviorodriguess/event-api-javalin.git
cd event-api-javalin
```

**2. Compile e gere o JAR:**
```bash
mvn clean package
```

**3. Execute:**
```bash
java -jar target/event-api-javalin-1.0-SNAPSHOT.jar
```

**4. Acesse:** A API estará disponível em `http://localhost:7070`

> O banco de dados `eventapi.db` é criado automaticamente na raiz do projeto na primeira execução.

---

Desenvolvido por Otávio Rodrigues 🚀
