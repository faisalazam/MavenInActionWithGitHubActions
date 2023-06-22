## Jacoco in action with Maven

## Running the tests
See `TestsExecution` project

## Jacoco's Code Coverage Reports

* Code coverage reports for
    * units tests are generated right after the execution of unit tests and can be found at `target/site/jacoco` directory.
    * integration tests are generated right after the execution of integration tests and can be found at `target/site/jacoco-it` directory.
* Combined or merged report for both the unit and the integration tests' will also be generated right after the execution of integration tests.
* Once the reports are generated, point a browser at the output in any of the following directories to see:
    * unit tests' coverage report: `target/site/jacoco/index.html`
    * integration tests' coverage report: `target/site/jacoco-it/index.html`
    * all tests' coverage report: `target/site/jacoco-merged/index.html`
