# Customer-Service

This project uses Quarkus, the Supersonic Subatomic Java Framework.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/customer-service-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Microservice Information

### Architecture
- Quarkus RESTful microservice
- Hibernate ORM (Panache)
- JWT authentication
- PostgreSQL database

### Endpoints
- `POST /customers` - Create a new customer
- `GET /customers/{id}` - Get customer by ID
- `GET /customers/exists?document={document}` - Check if customer exists by document

### Usage Examples
**Create customer**
```json
POST /customers
{
  "name": "Juan Perez",
  "document": "44524452",
  "email": "juan.perez@email.com",
  "status": "ACTIVE"
}
```

**Get customer by ID**
```
GET /customers/1
```

**Check existence by document**
```
GET /customers/exists?document=44524452
```

### Test Credentials
- User: testuser
- Password: testpass
- Role: USER
- JWT: Use the Authorization header: `Bearer <token>`

## Evidences

![Create Customer](https://github.com/klintfox/customer-service/blob/master/src/main/resources/evidencias/1.PNG)

![Validation Create Customer](https://github.com/klintfox/customer-service/blob/master/src/main/resources/evidencias/1b.PNG)

![Get Customer By Id](https://github.com/klintfox/customer-service/blob/master/src/main/resources/evidencias/2a.PNG)

![Customer Not Found](https://github.com/klintfox/customer-service/blob/master/src/main/resources/evidencias/2b.PNG)

![Find Customer By Document](https://github.com/klintfox/customer-service/blob/master/src/main/resources/evidencias/3.PNG)

![Customer Disable](https://github.com/klintfox/customer-service/blob/master/src/main/resources/evidencias/3b.PNG)

![q/health](https://github.com/klintfox/customer-service/blob/master/src/main/resources/evidencias/4.PNG)


## Related Guides

- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code for Hibernate ORM via the active record or the repository pattern
- SmallRye Health ([guide](https://quarkus.io/guides/smallrye-health)): Monitor service health
- Hibernate Validator ([guide](https://quarkus.io/guides/validation)): Validate object properties (field, getter) and method parameters for your beans (REST, CDI, Jakarta Persistence)
- SmallRye JWT ([guide](https://quarkus.io/guides/security-jwt)): Secure your applications with JSON Web Token
- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC
- SmallRye Metrics ([guide](https://quarkus.io/guides/smallrye-metrics)): Expose metrics for your services
