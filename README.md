# Code Challenge: Fintech API

A microservice that interacts and fetches information about the most important companies of the world. This project get
the information from the third party [Yahoo! Finance](https://finance.yahoo.com).

The solution is made in **Kotlin and Spring Boot Framework with gradle** and is based on DDD and hexagonal architecture,
so the structure is based on three layers: infrastructure, application and domain.

---

## Getting started

The repository provides a compiled version of gradle called `gradlew` (for unix) and `gradle.bat` (for windows users).
Use it to compile, test and run the application.

### How to run it

#### Using Docker

A pre-compiled version is provided by
DockerHub: [marceldape/fintech-code-challenge](https://hub.docker.com/repository/docker/marceldape/fintech-code-challenge)

```bash
docker run -d -p 8080:8080 --name fintech-code-challenge marceldape/fintech-code-challenge:latest
```

Note: all the images are build by the CI pipeline.

#### From CLI:

From the root directory, grant execute permission for gradlew (`chmod +x gradlew`)

```bash
./gradlew bootRun
```

### How to test it

This project has an extreme high level of testing, from unit tests to acceptance tests covering all three layers.

```bash
./gradlew test
```

---

## API Documentation

This microservice exposes two endpoints:

### GET Companies Endpoint

Provide three random stock symbol (aka ticker) that provides from the InMemoryCompaniesRepository.

```text
http://localhost:8080/api/v1/companies
```

### GET Stocks Endpoint

Provide information about the company and their value for a certain interval from the third party Yahoo! Finances.

```text
http://localhost:8080/api/v1/stocks/<STOCK_SYMBOL>?interval=<INTERVAL>
```

Where:

- _STOCK_SYMBOL_ is the ticker of the company. Example: AAPL for Apple, AMZN for Amazon, etc...
- _INTERVAL_ defines the price in relation to a time interval. Must be: `daily`, `weekly` or `monthly`

For example: use `http://localhost:8080/api/v1/stocks/AAPL?interval=weekly` in order to get information on the APPLE
company and its weekly value

---

## Continuous integration

This project ensure the code quality of the new features using the continuous integration pipeline (
See [ci.yaml](.github/workflows/ci.yml)) run by GitHub Actions. The pipeline will:

- Compile
- Test
- Build Docker image
- Push Docker image to DockerHub
