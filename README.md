<h1 align="center">URL SHORTENER</h1>

![Badge Concluído](https://img.shields.io/static/v1?label=Status&message=Desenvolvendo&color=red&style=for-the-badge)
![Badge Java](https://img.shields.io/static/v1?label=Java&message=17&color=red&style=for-the-badge&logo=java)
![Badge Spring](https://img.shields.io/static/v1?label=SpringBoot&message=v3.4.5&color=brightgreen&style=for-the-badge&logo=SpringBoot)
![Badge Postgresql](https://img.shields.io/static/v1?label=H2-Database&message=v2.3.232&color=blue&style=for-the-badge&logo=H2database)

## Resumo do projeto

Este projeto é uma API de encurtamento de URLs desenvolvida com Java e Spring Boot. Ele permite que usuários autenticados gerem URLs curtas, acessem seus redirecionamentos, listem seus próprios links e (caso sejam administradores) excluam links encurtados. Proposto pelo [Rafael Coelho](https://racoelho.com.br/) o desafio [#8 Encurtador de Links](https://racoelho.com.br/listas/desafios/encurtador-de-links).

## Tecnologias

- Java 21
- Spring Boot (Security, Oauth2-resource-server, Data-jpa, Web)
- H2 Database
- JPA/Hibernate
- SHA-256 + Base62 (hash das URLs)
- JWT (autenticação)
- Docker + Docker Compose
- Traefik (reverse proxy)
- Swagger/OpenAPI (documentação)
- JUnit (testes)

## Funcionalidades
- 🔐 Autenticação via token JWT
- ✂️ Encurtamento de URLs com hash criptográfico
- 🔁 Redirecionamento automático com base no código da URL
- 📈 Contador de acessos por URL
- 🐳 Containerização com Docker
- 🌐 Acesso externo facilitado com Traefik

## 📦 Como Rodar Localmente

### Pré-requisitos

- Docker + Docker Compose
- Java 21
- Maven (caso queira rodar fora do Docker)

### Subindo o projeto com Docker Compose

```bash
    git clone https://github.com/jvictornascimento/springboot-url-shortener.git
    cd springboot-url-shortener
    docker-compose up --build
````

### Configuração do Dominio `short.local`

Para melhor experiência de testes locais, você pode configurar o domínio `short.local` no seu arquivo `hosts`.

### Windows:
1. Execute o Prompt de Comando como Administrador
2. Digite:
   ```cmd
   notepad C:\Windows\System32\drivers\etc\hosts
   ```
3. Adicione a seguinte linha:
   ```
   127.0.0.1 short.local
   ```

### Linux:
1. Execute o terminal com sudo:
   ```bash
   sudo nano /etc/hosts
   ```
2. Adicione:
   ```
   127.0.0.1 short.local
   ```

Depois disso, você pode acessar: `http://short.local/{short_code}`

---
### Autenticação

**POST** `/login`
- Requisição de login para geração do token JWT.
- Requer `Authentication` no corpo da requisição.
- Respostas:
    - `200 OK` - Login bem-sucedido
    - `401 Unauthorized` - Usuário ou senha inválidos

---

### Redirecionamento

**GET** `/{short_code}`
- Redireciona o usuário para a URL original com base no código encurtado.
- Respostas:
    - `302 Found` - Redirecionamento bem-sucedido
    - `401 Unauthorized` - Código não encontrado
    - `410 Gone` - Link expirado

---

### Encurtar URL

**POST** `/api/shorten`
- Requer autenticação via JWT.
- Corpo da requisição:
  ```json
  {
    "originUrl": "https://exemplo.com"
  }
  ```
- Respostas:
    - `200 OK` - URL encurtada com sucesso
    - `401 Unauthorized` - Usuário não autorizado

---

### Listar URLs do Usuário

**GET** `/api/links`
- Retorna todas as URLs encurtadas pelo usuário logado.
- Respostas:
    - `200 OK` - Lista retornada
    - `401 Unauthorized`

---

### Deletar URL (ADMIN)

**DELETE** `/api/links/{short_code}`
- Requer permissão de administrador (`SCOPE_ROLE_ADMIN`).
- Respostas:
    - `200 OK` - Deletado com sucesso
    - `401 Unauthorized`

---

## Segurança

- Autenticação via JWT
- Expiração dos tokens
- Autorização por roles (usuário e admin)
- Expiração de links

---
## Observações

Para utilizar os endpoints de forma completa, é necessário autenticar-se com um token JWT válido.

Este projeto está pronto para ser containerizado via Docker e acessado localmente com `short.local` graças ao uso do Traefik como proxy reverso.

## Roadmap
- [x] Autenticação com JWT
- [x] Criação e redirecionamento de URLs
- [x] Deploy com Docker + Traefik
- [x] Estatísticas de acesso por URL
- [ ] Persistência com PostgreSQL (futura)
- [ ] Testes automatizados (futura)
- [ ] Interface frontend (futura)