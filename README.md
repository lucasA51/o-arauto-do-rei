# 👑 O Arauto do Rei

> *"A Voz Inquestionável dos Sete Reinos · Ano 300 Depois da Conquista"*

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.2-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-6.3.1-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)](https://spring.io/projects/spring-security)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.x-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)](https://www.thymeleaf.org/)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-5.1.3-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)](https://getbootstrap.com/)
[![H2 Database](https://img.shields.io/badge/Database-H2_In--Memory-003B57?style=for-the-badge&logo=databricks&logoColor=white)](https://www.h2database.com/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](#)

---

## 📖 Sobre o Projeto

**O Arauto do Rei** é uma aplicação web full-stack desenvolvida em **Java com Spring Boot**, concebida como uma **gazeta oficial e portal de notícias *in-universe*** ambientado no riquíssimo universo de *As Crônicas de Gelo e Fogo / Game of Thrones*.

Diferente de portais de entretenimento tradicionais sobre a série, a proposta aqui é a **completa imersão diegética**: as notícias, avisos e despachos são redigidos sob a ótica dos próprios habitantes e escribas de Westeros. Os textos refletem a política da corte, éditos do Trono de Ferro, comunicados da Patrulha da Noite, decretos do Grande Septão, cotações do Banco de Ferro de Braavos e os desdobramentos bélicos das Grandes Casas.

Visualmente, a plataforma reproduz a estética de manuscritos medievais e pergaminhos reais, com lacres de cera, brasões heráldicos, tipografia clássica (*Cinzel*, *EB Garamond* e fontes temáticas) e alertas urgentes no formato de **Despachos de Corvo** (*Raven Dispatches*).

---

## ⚔️ Principais Funcionalidades

### 📰 Portal Público (Para os Habitantes dos Sete Reinos)
- **Página Inicial Dinâmica:**
  - Banner oficial da gazeta do reino com brasão e título personalizáveis.
  - Alerta de última hora em tempo real (*Despacho de Corvo*).
  - Notícia principal em destaque (*Destaque da Coroa*).
  - Grid de matérias recentes com informações de Casa Nobre, Região, Autor e Data de Publicação.
  - Sistema de busca textual integrado para consultar crônicas e despachos no reino.
- **Seções Editoriais Especializadas:**
  - 🏰 **Notícias do Reino:** Éditos da Fortaleza Vermelha e campanhas militares.
  - 🪙 **Economia & Comércio:** Dívidas da Coroa, rotas marítimas e registros do Banco de Ferro de Braavos.
  - 🍷 **Vida na Corte:** Intrigas da nobreza, torneios reais e banquetes em Porto Real.
  - 📜 **Crônicas & Opiniões:** Ensaios dos Arquimestres da Cidadela e homilias do Grande Septão.
  - 🎭 **Arte & Cultura:** Baladas em Alto Valiriano, canções de bardos e peças teatrais.
- **Filtro por Casas Nobres (`/casas`):** Segmentação de acontecimentos por linhagem (Targaryen, Stark, Lannister, Baratheon, Greyjoy, Tully, etc.).
- **Mapa & Arquivos Geográficos (`/arquivos/mapa`):** Navegação por regiões (Porto Real, O Norte, Terras dos Rios, Ilhas de Ferro, Essos, Além da Muralha).
- **Página de Leitura Imersiva (`/noticia/{id}`):** Visualização completa do texto em formato de manuscrito, com metadados do autor e matérias relacionadas.

### 📜 Painel da Cidadela (Área Administrativa para Escribas e Mestres)
- **Autenticação Segura (Spring Security 6):**
  - Controle de acesso às rotas restritas de moderação e publicação.
  - Página de login customizada com estética medieval (`/usuarios/login`).
  - Formulário para registro de novos escribas com validação e criptografia de senhas em **BCrypt** (`/usuarios/registrar`).
  - Usuário mestre pré-configurado para demonstração imediata.
- **Gestão Completa de Notícias (CRUD):**
  - Listagem administrativa de notícias publicadas (`/noticias/listar`).
  - Redação e publicação de novas crônicas (`/noticias/cadastrar`).
  - Edição de notícias existentes (`/noticias/editar/{id}`).
  - Exclusão com confirmação (`/noticias/excluir/{id}`).
- **Semeador Automático de Dados (`DataInitializer`):**
  - Criação automática de usuário administrador e crônicas ricas em lore (O Casamento Vermelho, a Tomada de Yunkai por Daenerys, a ascensão de Euron Greyjoy, etc.) na primeira inicialização.

---

## 🏗️ Arquitetura da Aplicação

O projeto segue o consagrado padrão arquitetural **MVC (Model-View-Controller)** com separação em camadas bem delineadas no ecossistema Spring:

```
o-arauto-do-rei/
├── src/
│   ├── main/
│   │   ├── java/com/santosltda/oarautodorei/
│   │   │   ├── OArautoDoReiApplication.java       # Ponto de entrada da aplicação Spring Boot
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java            # Configuração de rotas, login e filtros do Spring Security
│   │   │   │   └── DataInitializer.java           # CommandLineRunner para carga inicial de dados (Seed)
│   │   │   ├── controller/
│   │   │   │   ├── NavigationController.java      # Rotas públicas, filtros por casa, mapa e categorias
│   │   │   │   ├── NoticiaController.java         # Gestão administrativa (CRUD de crônicas)
│   │   │   │   └── UsuarioController.java         # Autenticação, login e cadastro de novos escribas
│   │   │   ├── domain/
│   │   │   │   ├── BannerHome.java                # Entidade JPA para cabeçalho dinâmico do portal
│   │   │   │   ├── NoticiaHome.java               # Entidade JPA principal com conteúdo, autor, casa e região
│   │   │   │   └── Usuario.java                   # Entidade JPA de credenciais e usuários
│   │   │   ├── repository/
│   │   │   │   ├── BannerHomeRepository.java      # Interface Spring Data JPA para o banner
│   │   │   │   ├── NoticiaHomeRepository.java     # Consultas customizadas com derived queries e @Query
│   │   │   │   └── UsuarioRepository.java         # Repositório de usuários
│   │   │   └── service/
│   │   │       ├── BannerHomeService.java         # Regras de negócio do banner principal
│   │   │       ├── CustomUserDetailsService.java  # Integração do Spring Security com o banco de dados
│   │   │       └── NoticiaHomeService.java        # Regras de negócio, ordenação e buscas de notícias
│   │   └── resources/
│   │       ├── application.properties             # Parâmetros de banco de dados, JPA e servidor
│   │       ├── static/                            # Arquivos estáticos servidos pelo Spring
│   │       │   ├── fonts/                         # Tipografia medieval (got-font.ttf)
│   │       │   ├── images/                        # Brasões, selos e ilustrações temáticas
│   │       │   ├── scripts/                       # Bootstrap JS, jQuery e scripts auxiliares
│   │       │   └── stylesheets/                   # Estilos personalizados (style.css, bootstrap)
│   │       └── templates/                         # Templates Thymeleaf
│   │           ├── fragments/layout.html          # Fragmentos reutilizáveis (head, navbar, corvo, rodapé)
│   │           ├── index.html                     # Página principal do jornal
│   │           ├── casas/index.html               # Listagem filtrada por Casas Nobres
│   │           ├── arquivos/mapa.html             # Listagem filtrada por Regiões de Westeros/Essos
│   │           ├── noticias/                      # Telas de CRUD (cadastrar, editar, listar, detalhes)
│   │           ├── usuarios/                      # Telas de login e registro de usuários
│   │           └── [categorias]/                  # Vistas das seções temáticas editoriais
│   └── test/
│       └── java/com/santosltda/oarautodorei/controller/
│           ├── NavigationControllerTest.java      # Testes de rotas públicas e rendering de views
│           ├── NoticiaControllerTest.java         # Testes de operações de CRUD e autorização
│           └── UsuarioControllerTest.java         # Testes de login e registro com MockMvc
├── pom.xml                                        # Dependências e plugins do Apache Maven
└── mvnw / mvnw.cmd                                # Maven Wrapper para execução independente
```

---

## 🛠️ Tecnologias e Ferramentas

| Tecnologia | Finalidade |
| :--- | :--- |
| **Java 17** | Linguagem base moderna com suporte a LTS. |
| **Spring Boot 3.3.2** | Framework corporativo para configuração automática e produtividade. |
| **Spring MVC** | Estruturação de controladores, roteamento HTTP e passagem de modelos para as visões. |
| **Spring Data JPA & Hibernate** | Mapeamento objeto-relacional (ORM), persistência e queries tipadas. |
| **Spring Security 6** | Autenticação, autorização baseada em sessão, proteção de formulários e hash BCrypt. |
| **Thymeleaf + Extras Spring Security** | Engine de renderização de templates no servidor com renderização condicional por perfil de usuário. |
| **H2 Database** | Banco de dados em memória ideal para testes, desenvolvimento rápido e execução zero-config. |
| **MySQL Connector/J** | Driver JDBC pronto para conexão com banco de dados MySQL em ambiente de produção. |
| **Jakarta Validation** | Validação declarativa de integridade de dados (`@NotBlank`, `@Size`). |
| **Bootstrap 5 & WebJars** | Sistema de grid responsivo e componentes visuais integrados. |
| **Lombok** | Redução de código boilerplate. |
| **JUnit 5, Mockito & MockMvc** | Suíte completa de testes automatizados unitários e de integração. |

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- **Java Development Kit (JDK) 17** ou superior instalado.
- **Git** instalado para clonar o repositório.
- *(Opcional)* Maven instalado globalmente (o projeto já inclui o `mvnw` wrapper).

### 1. Clonar o Repositório
```bash
git clone https://github.com/seu-usuario/o-arauto-do-rei.git
cd o-arauto-do-rei
```

### 2. Executar a Aplicação
Utilize o Maven Wrapper incluso no repositório:

**No Linux / macOS:**
```bash
./mvnw spring-boot:run
```

**No Windows:**
```cmd
mvnw.cmd spring-boot:run
```

A aplicação inicializará no endereço: **`http://localhost:8080`**

---

## 🔐 Acesso e Credenciais

Ao iniciar pela primeira vez, a classe `DataInitializer` semeia automaticamente a base de dados com as crônicas do reino e a conta do Escriba-Mestre:

| Perfil | Usuário | Senha | Acesso |
| :--- | :--- | :--- | :--- |
| **Mestre da Cidadela (Admin)** | `maester` | `citadel123` | Painel de controle (`/noticias/listar`) |

### Console do Banco de Dados H2
O console web do banco H2 está habilitado para inspeção rápida das tabelas durante o desenvolvimento:
- **URL de Acesso:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:oarautodorei`
- **User Name:** `sa`
- **Password:** *(deixe em branco)*

---

## 🧪 Executando os Testes Automatizados

A aplicação conta com testes de controladores utilizando **MockMvc** e cenários com e sem autenticação (`@WithMockUser`):

```bash
./mvnw clean test
```

Para gerar o pacote executável (JAR):
```bash
./mvnw clean package
```
O artefato gerado estará disponível em `target/o-arauto-do-rei-0.0.1-SNAPSHOT.jar`.

---

## 🗄️ Alternando para MySQL (Ambiente de Produção)

Para utilizar o **MySQL** em vez do banco em memória H2, ajuste o arquivo [`src/main/resources/application.properties`](file:///home/lucas/Documents/repos/o-arauto-do-rei/src/main/resources/application.properties):

```properties
# Exemplo de configuração para MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/arauto_do_rei?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver

# Configurações do Hibernate para MySQL
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## ✒️ Autor e Licença

Desenvolvido com inspiração no universo fantástico de George R. R. Martin.

Distribuído sob a licença MIT. Consulte o arquivo de licença para mais detalhes.

> *"Valar Morghulis. Mas as notícias da corte nunca morrem."*
