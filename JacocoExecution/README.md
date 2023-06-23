## Jacoco in action with Maven
The purpose of this small project (built on top of [TestsExecution][tests-execution-url]) is to utilize the 
[Jacoco][jacoco-url] plugin for code coverage reports. Individual reports for the code covered by unit tests as well as
integration tests will be generated. And then, a combined coverage report will also be generated to show the total coverage.
These generated coverage reports will then be included in the project reports through `mvn site`.


## Running the tests
See [TestsExecution][tests-execution-readme-url] project

## Jacoco's Code Coverage Reports

* Code coverage reports for
    * units tests are generated right after the execution of unit tests and can be found at `target/site/jacoco` directory.
    * integration tests are generated right after the execution of integration tests and can be found at `target/site/jacoco-it` directory.
* Combined or merged report for both the unit and the integration tests' will also be generated right after the execution of integration tests.
* Once the reports are generated, point a browser at the output in any of the following directories to see:
    * unit tests' coverage report: `target/site/jacoco/index.html`
    * integration tests' coverage report: `target/site/jacoco-it/index.html`
    * all tests' coverage report: `target/site/jacoco-merged/index.html`

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[tests-execution-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/TestsExecution/testsexecution/index.html
[tests-execution-readme-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/TestsExecution/testsexecution/README.html
[jacoco-url]:https://www.eclemma.org/jacoco/trunk/doc/maven.html