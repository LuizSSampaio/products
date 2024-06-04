
# Products

A simple spring api for create, read, update and delete products.


## Run Locally

Clone the project

```bash
  git clone https://github.com/LuizSSampaio/products.git
```

Go to the project directory

```bash
  cd products
```

Build the project

```bash
  mvn clean install
```

Running the application

```bash
  mvn spring-boot:run
```

Running Tests

```bash
  mvn test
```
## Roadmap

- Authentication system


## API Reference

#### Create product

```http
  POST /api/products
```

| Parameter | Type     | Description                              |
|:----------|:---------|:-----------------------------------------|
| `name`    | `string` | **Required**. name of product to create  |
| `price`   | `double` | **Required**. price of product to create |
| `stock`   | `int`    | **Required**. stock of product to create |

#### Get all products

```http
  GET /api/products
```

#### Get one product

```http
  GET /api/products/{id}
```

| Parameter | Type   | Description                           |
|:----------|:-------|:--------------------------------------|
| `id`      | `UUID` | **Required**. id of product to create |

#### Update product

```http
  PUT /api/products/{id}
```

| Parameter | Type     | Description                              |
|:----------|:---------|:-----------------------------------------|
| `id`      | `UUID`   | **Required**. id of product to create    |
| `name`    | `string` | **Required**. name of product to create  |
| `price`   | `double` | **Required**. price of product to create |
| `stock`   | `int`    | **Required**. stock of product to create |

#### Delete product

```http
  DELETE /api/products/{id}
```

| Parameter | Type   | Description                           |
|:----------|:-------|:--------------------------------------|
| `id`      | `UUID` | **Required**. id of product to create |

