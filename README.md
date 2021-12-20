# okvote
* voting simple
* https://v.okdevi.net/

## Features
* voting simple

## Requirements
* [jdk1.8+](https://okdevtv.com/mib/sdkman)

## Installation
* `git clone https://github.com/kenu/okvote`
* `cd okvote`
* `mvnw package` # or `./mvnw clean package`
* `java -jar target/okvote-0.0.1-SNAPSHOT.jar`

## Usage
* http://localhost:8080/

## License
* MIT

## Contributing
* Fork and clone the repository
* Create a new branch
* Commit your changes
* Push your branch to your fork
* Create a pull request

## Deploy on EC2

```sql
curl -o install-env.sh -L https://raw.githubusercontent.com/kenu/okvote/main/scripts/install-env.sh && sh install-env.sh && rm -rf install-env.sh
```
