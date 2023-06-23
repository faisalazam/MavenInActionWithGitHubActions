<!-- TOC -->
  * [Goals utilization](#goals-utilization)
  * [Running the tests](#running-the-tests)
  * [Jacoco Code Coverage Reports](#jacoco-code-coverage-reports)
  * [How to set up JaCoCo Plugin with Maven?](#how-to-set-up-jacoco-plugin-with-maven)
      * [Configuration for unit tests with custom paths](#configuration-for-unit-tests-with-custom-paths)
      * [Configuration for integration tests](#configuration-for-integration-tests)
  * [Merge the data/exec files](#merge-the-dataexec-files)
  * [Generate the merged coverage report](#generate-the-merged-coverage-report)
  * [Add code coverage check](#add-code-coverage-check)
  * [How to set up JaCoCo Plugin with Maven for parallel execution?](#how-to-set-up-jacoco-plugin-with-maven-for-parallel-execution)
<!-- TOC -->

<h1 style="text-align:center">
  Jacoco in action with Maven
  <img src="https://github.com/faisalazam/MavenInActionWithGitHubActions/raw/master/.github/assets/awesome-badge.svg" alt="" />
</h1>

[Jacoco-Maven][jacoco-url] (abbreviation for Java Code Coverage) plugin is an open-source code coverage tool for Java. 
It creates code coverage reports and integrates well with IDEs(Integrated development environments) like Eclipse IDE.

The purpose of this small project (built on top of [TestsExecution][tests-execution-url]) is to utilize the 
[Jacoco][jacoco-url] plugin for code coverage reports.

## Goals utilization
Following goals of the [Jacoco][jacoco-url] plugin can be used to generate coverage reports:
* The [report goal][report-goal-url] can be used to generate the coverage report for unit tests only with little bit of config.
* The [report-integration goal][report-integration-goal-url] can be used to generate the coverage report for integration 
  tests only with little bit of config.
* The [merge goal][merge-goal-url] can be used to generate the combined/merged coverage report for all the tests.

Individual reports for the code covered by unit tests as well as
integration tests will be generated. And then, a combined coverage report will also be generated to show the total coverage.
These generated coverage reports will then be included in the project reports through `mvn site`.


## Running the tests
See [TestsExecution][tests-execution-readme-url] project

## Jacoco Code Coverage Reports

* Code coverage reports for
    * units tests are generated right after the execution of unit tests and can be found in `target/site/jacoco` directory.
    * integration tests are generated right after the execution of integration tests and can be found in `target/site/jacoco-it` directory.
    * Combined or merged report for both the unit and the integration tests' will also be generated right after the 
      execution of integration tests and can be found in `target/site/jacoco-merged` directory.
* Once the reports are generated, point a browser at the output in any of the following directories to see:
    * unit tests' coverage report: `target/site/jacoco/index.html`
    * integration tests' coverage report: `target/site/jacoco-it/index.html`
    * all tests' coverage report: `target/site/jacoco-merged/index.html`

## How to set up JaCoCo Plugin with Maven?
Here are the steps to integrate JaCoCo Maven plugin with a Maven project:
* Declare the [Jacoco-Maven][jacoco-url] plugin in the [pom.xml](pom.xml) file as follows:
    ```
    <build>
        <plugins>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>${jacoco-maven-plugin.version}</version>
            </plugin>
        </plugins>
    </build>
    ```
* The above declaration won't be of much use without further configuration, so after the `version` tag, we add the
  [executions][executions-url] tag. This tag prepares the properties or execution to point to the JaCoCo agent 
  and is passed as a VM (in this case, JVM) argument.
* The bare minimum for running simple unit tests (`mvn test`) is to set up a [prepare-agent goal][prepare-agent-goal-url] 
  and [report goal][report-goal-url]. The following config will run the JaCoCo [report goal][report-goal-url]
  during the [test phase][test-phase-url] of the [build lifecycle][build-lifecycle-url]:
    ```
    <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>${jacoco-maven-plugin.version}</version>
        <executions>
            <execution>
                <id>before-unit-test-execution</id>
                <goals>
                    <goal>prepare-agent</goal>
                </goals>
            </execution>
            <execution>
                <id>after-unit-test-execution</id>
                <phase>test</phase>
                <goals>
                    <goal>report</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    ```
    * #### Prepare-agent goal: 
      The [prepare-agent goal][prepare-agent-goal-url] prepares the JaCoCo runtime agent to record 
      the execution data. It records the number of lines executed, backtracked, etc. By default, the execution data is 
      written to the file `target/jacoco.exec`.
    * #### Report goal: 
      The [report goal][report-goal-url] creates code coverage reports from the execution data recorded by 
      the JaCoCo runtime agent. Since we have specified the phase property, so the reports will be created after the 
      compilation of the [test phase][test-phase-url] of the [build lifecycle][build-lifecycle-url]. 
      By default, the execution data is read from the file `target/jacoco.exec`, and the code coverage report is written 
      to the directory `target/site/jacoco/index.html`.

#### Configuration for unit tests with custom paths
The following configuration inside the `<id>before-unit-test-execution</id>` execution block, will set the data execution
file for the unit tests' coverage to be stored in `target/jacoco-execs/ut.exec` file and we achieve that by overwriting
the `surefire.jacoco.exec.file.name.arg` property used while running unit tests. 
```
<configuration>
    <destFile>target/jacoco-execs/ut.exec</destFile>
    <propertyName>surefire.jacoco.exec.file.name.arg</propertyName>
</configuration>
```

We also need to tell where to find the data execution file while running the [report goal][report-goal-url], so we'll use
the following configuration in `after-unit-test-execution` execution block. We've also set the `outputDirectory` which
is an optional setting, and thats where the generated report will be stored.
```
<configuration>
    <destFile>target/jacoco-execs/ut.exec</destFile>
    <outputDirectory>${jacoco.ut.reporting.directory}</outputDirectory>
</configuration>
```
So, the full configuration for running the unit tests will look like this and we're all set as far as running and 
generating report for unit tests:
```
<execution>
    <id>before-unit-test-execution</id>
    <goals>
        <goal>prepare-agent</goal>
    </goals>
    <configuration>
        <destFile>target/jacoco-execs/ut.exec</destFile>
        <propertyName>surefire.jacoco.exec.file.name.arg</propertyName>
    </configuration>
</execution>
<execution>
    <id>after-unit-test-execution</id>
    <phase>test</phase>
    <goals>
        <goal>report</goal>
    </goals>
    <configuration>
        <destFile>target/jacoco-execs/ut.exec</destFile>
        <outputDirectory>${jacoco.ut.reporting.directory}</outputDirectory>
    </configuration>
</execution>
```

#### Configuration for integration tests
Now we gonna need the exact same configuration for integration tests as follows:
```
<execution>
    <id>before-integration-test-execution</id>
    <phase>pre-integration-test</phase>
    <goals>
        <goal>prepare-agent-integration</goal>
    </goals>
    <configuration>
        <destFile>target/jacoco-execs/it.exec</destFile>
        <propertyName>failsafe.jacoco.exec.file.name.arg</propertyName>
    </configuration>
</execution>
<execution>
    <id>after-integration-test-execution</id>
    <phase>post-integration-test</phase>
    <goals>
        <goal>report-integration</goal>
    </goals>
    <configuration>
        <destFile>target/jacoco-execs/it.exec</destFile>
        <outputDirectory>${jacoco.it.reporting.directory}</outputDirectory>
    </configuration>
</execution>
```

In the above configuration, instead of using [prepare-agent goal][prepare-agent-goal-url] and [report goal][report-goal-url], 
we used [prepare-agent-integration goal][prepare-agent-integration-goal-url] and 
[report integration goal][report-integration-goal-url] which are more related to the execution of integration tests.

The above config will run the JaCoCo [report integration goal][report-integration-goal-url] during the 
[post-integration-test phase][test-phase-url] of the [build lifecycle][build-lifecycle-url].

## Merge the data/exec files
Add the following config after `<id>after-integration-test-execution</id>` execution block:
```
<execution>
    <id>merge-unit-and-integration</id>
    <phase>post-integration-test</phase>
    <goals>
        <goal>merge</goal>
    </goals>
    <configuration>
        <destFile>target/jacoco-execs/merged.exec</destFile>
        <fileSets>
            <fileSet>
                <directory>target/jacoco-execs</directory>
                <includes>
                    <include>ut.exec</include>
                    <include>it.exec</include>
                </includes>
            </fileSet>
        </fileSets>
    </configuration>
</execution>
```
In the above configuration, we've bound the [merge goal][merge-goal-url] to the [post-integration-test phase][test-phase-url]
of the [build lifecycle][build-lifecycle-url], which will merge the included `exec` files into one single combined `exec` file.

## Generate the merged coverage report
Add the following config after `<id>merge-unit-and-integration</id>` execution block:
```
<execution>
    <id>create-merged-report</id>
    <phase>post-integration-test</phase>
    <goals>
        <goal>report</goal>
    </goals>
    <configuration>
        <dataFile>${jacoco.merged.exec}</dataFile>
        <outputDirectory>${jacoco.merged.reporting.directory}</outputDirectory>
    </configuration>
</execution>
```
In the above configuration, we've bound the [report goal][report-goal-url] to the [post-integration-test phase][test-phase-url]
of the [build lifecycle][build-lifecycle-url], which will generate the combined coverage report from the provided `exec` file.

## Add code coverage check
Add the following config after `<id>create-merged-report</id>` execution block:
```
<execution>
    <id>jacoco-check</id>
    <phase>verify</phase>
    <goals>
        <goal>check</goal>
    </goals>
    <configuration>
        <rules>
            <rule>
                <element>CLASS</element>
                <excludes>
                    <exclude>*Test</exclude>
                    <exclude>*IT</exclude>
                </excludes>
                <limits>
                    <limit>
                        <counter>LINE</counter>
                        <value>COVEREDRATIO</value>
                        <minimum>${jacoco.minimum.line.coverage}</minimum>
                    </limit>
                </limits>
            </rule>
        </rules>
        <dataFile>${jacoco.merged.exec}</dataFile>
    </configuration>
</execution>
```
In the above configuration, we've bound the [check goal][check-goal-url] to the [verify phase][test-phase-url]
of the [build lifecycle][build-lifecycle-url], which will perform the verification checks according the specified rules
on the provided `exec` file, and will fail the build if the checks are not met.


## How to set up JaCoCo Plugin with Maven for parallel execution?
More configuration is required if we want to run the unit tests and integration tests parallel/simultaneously on multiple
machines. For example, in CI/CD environment, we usually want parallel execution of jobs as much as possible to speed up
the build process. So, in order to achieve that, let's first have separate execution data files for unit and
integration tests.

To achieve the parallel execution, use the following commands:
* One job can run `mvn test -P ut`, which will generate a file named `jacoco.exec` or `jacoco-ut.exec`, store or 
  upload that file somewhere for later use
* Another job can run `mvn integration-test -P it`, which will generate a file named `jacoco-it.exec`, store or
  upload that file somewhere for later use. **NOTE**: you might be wondering why we just didn't do `mvn verify -P nt`, 
  that's because all the data files won't be present and hence the generated report won't be correct
* Then in the report generation job:
  * Download the `jacoco-ut.exec` and `jacoco-it.exec` data files
  * Place them in the `target/jacoco-execs` directory or whichever directory you're using for the data files
  * Run the `mvn post-integration-test -P nt` or `mvn verify -P nt`, and the generated reports will be there in the 
    `target/site` directory


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[tests-execution-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/TestsExecution/testsexecution/index.html
[tests-execution-readme-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/TestsExecution/testsexecution/README.html
[jacoco-url]:https://www.eclemma.org/jacoco/trunk/doc/maven.html
[report-goal-url]:https://www.eclemma.org/jacoco/trunk/doc/report-mojo.html
[report-integration-goal-url]:https://www.eclemma.org/jacoco/trunk/doc/report-integration-mojo.html
[check-goal-url]:https://www.eclemma.org/jacoco/trunk/doc/check-mojo.html
[merge-goal-url]:https://www.eclemma.org/jacoco/trunk/doc/merge-mojo.html
[prepare-agent-goal-url]:https://www.eclemma.org/jacoco/trunk/doc/prepare-agent-mojo.html
[prepare-agent-integration-goal-url]:https://www.eclemma.org/jacoco/trunk/doc/prepare-agent-integration-mojo.html
[executions-url]:https://maven.apache.org/guides/mini/guide-configuring-plugins.html#configuring-build-plugins
[test-phase-url]:https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#lifecycle-reference
[build-lifecycle-url]:https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html