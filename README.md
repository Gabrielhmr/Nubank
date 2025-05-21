# Capital Gains CLI

Este projeto é uma aplicação de linha de comando (CLI) em Kotlin para calcular o imposto devido sobre operações de compra e venda de ações, seguindo as regras de ganho de capital do mercado financeiro brasileiro.

## Como executar

### 1. Pré-requisitos

- Java 11 ou superior instalado
- [Gradle](https://gradle.org/install/) instalado **ou** use o wrapper do projeto (`./gradlew`)

### 2. Build do projeto

No terminal, na raiz do projeto, execute:

```sh
./gradlew build
```

### 3. Prepare a entrada

Insira a linha contendo uma lista de operações em formato JSON, por exemplo:

```
[{"operation":"buy", "unit-cost":10.00, "quantity": 100},
{"operation":"sell", "unit-cost":15.00, "quantity": 50},
{"operation":"sell", "unit-cost":15.00, "quantity": 50}]
```

### 4. Execute a aplicação

No terminal, rode:

```sh
java -jar build/libs/capital-gains.jar

```
### 5. Veja a saída

A saída será impressa no terminal, uma linha para cada linha de entrada, no formato esperado pelo desafio.

```
[{"tax": 0.0},{"tax": 0.0},{"tax": 0.0}]
```

---

### 6. Testes

- Para rodar testes automatizados:
  ```sh
  ./gradlew test
  ```
---

## Estrutura do Projeto

- `src/main/kotlin/model/data/` — Modelo de dados
- `src/main/kotlin/model/core/` — Lógica de cálculo de imposto
- `src/main/kotlin/Main.kt` — Entrada da aplicação

---