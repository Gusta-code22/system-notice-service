# 🛰️ System Notice Service

API REST desenvolvida com **Spring Boot + Spring Cloud Config** para expor o **estado atual do sistema** (`OK`, `WARN`, `ERROR`) de forma **dinâmica**, controlada exclusivamente por **configuração externa**, sem necessidade de restart da aplicação.

Este projeto demonstra, de forma prática, **configuração centralizada**, **profiles**, **refresh em runtime** e **separação correta de responsabilidades** em uma arquitetura de microsserviços.

---

## 🎯 Objetivo do Projeto

Permitir que aplicações clientes consultem o **status do sistema** e recebam uma **mensagem contextualizada**, que pode ser alterada em tempo de execução via **Spring Cloud Config Server**, apenas modificando arquivos de configuração em um repositório Git.

---

## 🧱 Arquitetura

Client (System Notice Service)
↓
Spring Cloud Config Server
↓
Repositório Git de Configurações

yaml
Copiar código

- O service **não contém mensagens fixas**
- Todo o comportamento é controlado por **configuração externa**
- Mudanças são aplicadas via **`/actuator/refresh`**

---

## 🚀 Tecnologias Utilizadas

- Java 17  
- Spring Boot  
- Spring Web  
- Spring Cloud Config Client  
- Spring Boot Actuator  
- Maven  

---

## 📌 Conceitos Demonstrados

- Configuração externa via **Spring Cloud Config**
- Uso de **profiles** (`default`, `warn`, `error`)
- Refresh dinâmico **sem restart**
- API REST simples e previsível
- Separação entre código e regra de negócio configurável

---

## 📡 Endpoint Disponível

### Consultar aviso do sistema

GET /systemnotice



### Query Params (opcional)

| Parâmetro | Descrição |
|----------|----------|
| `nome` | Nome do usuário para personalizar a mensagem |

---

## 📦 Exemplo de Resposta

### Request

GET /systemnotice?nome=Gustavo



### Response

```json
{
  "id": 1,
  "level": "OK",
  "message": "Olá, Gustavo. O sistema está OK, pode continuar."
}
Sem o parâmetro nome
json
Copiar código
{
  "id": 2,
  "level": "WARN",
  "message": "Olá, Usuário. Atenção: o sistema pode apresentar instabilidades."
}
⚙️ Configurações Dinâmicas
As configurações são fornecidas por um repositório Git externo, consumido pelo Config Server.

Estrutura do repositório de configurações
pgsql
Copiar código
system-notice-config-repo/
├── system-notice-service.yml
├── system-notice-service-warn.yml
└── system-notice-service-error.yml
Configuração Padrão (OK)

system-notice:
  level: "OK"
  message: "Olá, %s. O sistema está OK, pode continuar."
  default-value: "Usuário"
Configuração de Aviso (WARN)

system-notice:
  level: "WARN"
  message: "Olá, %s. Atenção: o sistema pode apresentar instabilidades."
  default-value: "Usuário"
Configuração de Erro (ERROR)

system-notice:
  level: "ERROR"
  message: "Olá, %s. O sistema está indisponível no momento. Tente novamente mais tarde."
  default-value: "Usuário"
🔄 Atualização em Runtime
A aplicação está em execução

A configuração é alterada no repositório Git

Um commit é realizado

O endpoint abaixo é chamado:


POST /actuator/refresh
O comportamento da API muda sem restart

🧠 Decisões de Design
Mensagens não ficam no código

O controller é responsável apenas por:

receber a request

aplicar fallback simples

retornar a resposta

O template da mensagem é controlado por configuração

O level representa o estado do sistema, não lógica interna

📁 Estrutura do Projeto
arduino
Copiar código
src/main/java
├── controller
├── model
├── config
├── enum
└── service (opcional / evolução)
📈 Possíveis Evoluções
Introdução de camada Service

Uso de @RefreshScope mais granular

Integração com Spring Security

Consumo por outros microsserviços

Dashboard de status do sistema

👤 Autor
Projeto desenvolvido por Gustavo Miranda Brito, com foco em aprendizado prático de Spring Cloud, microsserviços e boas práticas de backend.

🏁 Conclusão
Este projeto demonstra, de forma clara e funcional, como configuração centralizada pode controlar o comportamento de uma API em tempo real — um requisito comum em sistemas distribuídos moderno
