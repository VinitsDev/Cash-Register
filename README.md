# Cash Register

API REST para controle de caixa, produtos e vendas, feita em **Java + Spring Boot**.

---

## Tecnologias usadas

- Java 17
- Spring Boot
- MySQL
- Hibernate/JPA
- Swagger (OpenAPI)

---

## Funcionalidades

✅ Cadastro e gerenciamento de produtos  
✅ Abertura e fechamento do caixa (Cash Register)  
✅ Controle do valor em caixa (abertura x fechamento)  
✅ Registro de vendas com múltiplos itens  
✅ Integração com Swagger para documentação da API  
✅ Tratamento básico de exceções

---

## Endpoints

### Products
- `POST /products`
- `GET /products`
- `GET /products/{id}`
- `PUT /products/{id}`
- `DELETE /products/{id}`

### Cash Register
- `POST /cash-register/open`
- `PUT /cash-register/close/{id}`
- `GET /cash-register`
- `GET /cash-register/{id}`

### Sales
- `POST /sales`
- `GET /sales`
- `GET /sales/{id}`
- `DELETE /sales/{id}`

---

## Melhorias Futuras
- Testes unitários
- Documentação completa
- Desenvolver Front-end
- Melhoras práticas de código limpo
- Spring Security
- Serviços em nuvem



