<!-- TOC -->
  * [Testing in action with Maven](#testing-in-action-with-maven)
  * [Plugin Configurations](#plugin-configurations)
  * [Maven profiles](#maven-profiles)
  * [Conventions](#conventions)
  * [Running the tests](#running-the-tests)
  * [Running a single test](#running-a-single-test)
  * [Configure test reporting](#configure-test-reporting)
      * [Using the `reportSets` Tag](#using-the-reportsets-tag)
  * [Binding the goals and phases to achieve something different](#binding-the-goals-and-phases-to-achieve-something-different)
  * [Read further if you want to set custom paths for tests' results](#read-further-if-you-want-to-set-custom-paths-for-tests-results)
<!-- TOC -->

## Testing in action with Maven

The purpose of this small project (built on top of [SiteGeneration][site-generation-url]) is to utilize the maven's [surefire][surefire-url], [failsafe][failsafe-url],
and [surefire-report][surefire-report-url] plugins
to run the unit tests as well as integration tests both separately and running them together, as well as generate their
separate reports and include them in the generated project reports through `mvn site`.

The generated site will be deployed automatically as part of every push to the GitHub Pages.
The deployment will happen using `GitHub workflows/actions`.

## Plugin Configurations
We basically need `groupId` and `artifactId` to declare a plugin in [pom.xml](pom.xml) file.
Important Note: Always define the `version` of each plugin used to guarantee build reproducibility.

But if we need more control over the plugin's configuration, then we can make use of the `configuration` element
and the child elements (i.e. `skipTests` and `testFailureIgnore` in the code below) of the `configuration` element will be mapped
to the properties/fields of the plugin's Mojo. So, we can set those fields like this (in [pom.xml](pom.xml) file):

```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>${maven-surefire-plugin.version}</version>
    <configuration>
        <skipTests>${skip.unit.tests}</skipTests>
        <testFailureIgnore>${ignore.test.failures}</testFailureIgnore>
    </configuration>
</plugin>
```

This [parameter mapping][parameter-mapping-url] can be of different types like:
* Mapping complex objects
* Mapping collection types
* Mapping Maps
* Mapping properties

Learn more about [Configuring plugins][configuring-plugins-url]

## Maven profiles
* There are some profiles setup which can be used switch on/off certain things.
* To see the list of configured profiles, run `mvn help:all-profiles`
    * Configured profiles (in [pom.xml](pom.xml) file) are:

      | Profile ID | Profile Description                                          |
      |------------|--------------------------------------------------------------|
      | `ut`       | It'll run only the unit tests                                |
      | `it`       | It'll run only the integration tests                         |
      | `uit`      | It'll run both the unit and the integration tests            |
      | `nt`       | It'll run no tests at all (skipping execution of all tests). |

Learn more about [Maven Profiles][maven-profiles-url]

## Conventions

This project follows the following basic conventions out of the box:

|                              | unit test                  | integration test         |
|------------------------------|----------------------------|--------------------------|
| **resides in:**              | `src/test/java/*Test.java` | `src/test/java/*IT.java` |
| **executes in Maven phase:** | test                       | verify                   |
| **handled by Maven plugin:** | [surefire][surefire-url]   | [failsafe][failsafe-url] |


## Running the tests

| Clean the `target` directory | Run only the unit tests | Run only the integration tests   | Run both the unit and the integration tests |
|------------------------------|-------------------------|----------------------------------|---------------------------------------------|
| `mvn clean`                  | `mvn test`              | `mvn integration-test -P it`, or | `mvn verify`, or                            |
|                              |                         | `mvn verify -P it`               | `mvn verify -P uit` or                      |
|                              |                         |                                  | `mvn clean install`                         |

Using the `verify` lifecycle of maven will also run the configured verification checks and will fail the build if any of the checks are not met.

## Running a single test
During development, you may run a single test class repeatedly. To run this through Maven, set the test property to a 
specific test case. `mvn -Dtest=TestCircle test`
Learn more about [running a single test][single-test-run-url]

## Configure test reporting

Configuring the reporting to include our test report in the generated site.

`mvn site`
Since [maven-site-plugin 3.4][maven-site-plugin-url], it uses the parameters defined in the `<configuration>` element of each reporting Plugin
specified in the `<reporting>` element, in addition to the parameters defined in the `<configuration>` element of
each plugin specified in `<build>` (parameters from `<build>` section were previously ignored).

Learn more about [Site Reporting][site-reporting-url]

#### Using the `reportSets` Tag
You can configure a reporting plugin using the `reportSets` tag. This is most commonly used to generate
reports selectively when running `mvn site`. The following (in [pom.xml](pom.xml) file) will generate only the `surefire` and `failsafe` reports:
```
<reporting>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-report-plugin</artifactId>
            <version>${maven-surefire-report-plugin.version}</version>
            <reportSets>
                <reportSet>
                    <reports>
                        <report>report-only</report>
                        <report>failsafe-report-only</report>
                    </reports>
                </reportSet>
            </reportSets>
        </plugin>
    </plugins>
</reporting>
```

## Binding the goals and phases to achieve something different
Another thing to learn is, to bind the [goals][maven-goals-url] of a plugin to certain [phase][maven-phases-url] of the [build lifecycle][build-lifecycle-url].
We can make use of the [executions][executions-url] tag, which is most commonly used for mojos that are intended to
participate in some phases of the [build lifecycle][build-lifecycle-url].

In the following example, we are binding the [report-only goal][report-only-goal-url] of the [surefire-report][surefire-report-url]
plugin to the [tset phase][test-phase-url] ([surefire:test goal][surefire-test-goal-url] 
of [surefire][surefire-url] plugin binds to the [tset phase][test-phase-url] by default) and 
then the [failsafe-report-only goal][failsafe-report-only-goal-url] of the [surefire-report][surefire-report-url] plugin 
to the [integration-test phase][integration-test-phase-url] ([failsafe:integration-test goal][failsafe-integration-test-goal-url]
of [failsafe][failsafe-url] plugin binds to the [integration-test phase][integration-test-phase-url] by default). 
That configuration will trigger the [report-only goal][report-only-goal-url] once the tests are run to generate the 
surefire report and then, the [failsafe-report-only goal][failsafe-report-only-goal-url] once the integration tests 
are run to generate the failsafe report. See in [pom.xml](pom.xml) file.
```
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-report-plugin</artifactId>
            <version>${maven-surefire-report-plugin.version}</version>
            <executions>
                <execution>
                    <id>unit-tests</id>
                    <phase>test</phase>
                    <goals>
                        <goal>report-only</goal>
                    </goals>
                    <!-- Depending on your setup, the following configurations may or may not be optional -->
                    <configuration>
                        <linkXRef>true</linkXRef>
                        <reportsDirectories>
                            <reportsDirectories>${unit.tests.results.directory}</reportsDirectories>
                        </reportsDirectories>
                    </configuration>
                </execution>
                <execution>
                    <id>integration-tests</id>
                    <phase>integration-test</phase>
                    <goals>
                        <goal>failsafe-report-only</goal>
                    </goals>
                    <!-- Depending on your setup, the following configurations may or may not be optional -->
                    <configuration>
                        <linkXRef>true</linkXRef>
                        <reportsDirectories>
                            <reportsDirectories>${integration.tests.results.directory}</reportsDirectories>
                        </reportsDirectories>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

## Read further if you want to set custom paths for tests' results
If you want to set the custom output directories for the tests' results, then continue reading.

Following is what should go in the build section for custom paths (in [pom.xml](pom.xml) file):
```
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>${maven-surefire-plugin.version}</version>
            <configuration>
                <skipTests>${skip.unit.tests}</skipTests>
                <testFailureIgnore>${ignore.test.failures}</testFailureIgnore>
                <reportsDirectory>${unit.tests.results.directory}</reportsDirectory>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-failsafe-plugin</artifactId>
            <version>${maven-failsafe-plugin.version}</version>
            <configuration>
                <skipTests>${skip.integration.tests}</skipTests>
                <testFailureIgnore>${ignore.test.failures}</testFailureIgnore>
                <reportsDirectory>${integration.tests.results.directory}</reportsDirectory>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Now, as the generated tests' summaries as part of the tests' run are not in their default locations, so we have to tell
the reporting plugin to where to look for the test summaries to generate reports and hence, following is what should go
in the reporting section (in [pom.xml](pom.xml) file):
```
<reporting>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-report-plugin</artifactId>
            <version>${maven-surefire-report-plugin.version}</version>
            <reportSets>
                <reportSet>
                    <id>unit-tests</id>
                    <configuration>
                        <linkXRef>true</linkXRef>
                        <reportsDirectories>
                            <reportsDirectories>${unit.tests.results.directory}</reportsDirectories>
                        </reportsDirectories>
                    </configuration>
                    <reports>
                        <report>report-only</report>
                    </reports>
                </reportSet>
                <reportSet>
                    <id>integration-tests</id>
                    <configuration>
                        <linkXRef>true</linkXRef>
                        <reportsDirectories>
                            <reportsDirectories>${integration.tests.results.directory}</reportsDirectories>
                        </reportsDirectories>
                    </configuration>
                    <reports>
                        <report>failsafe-report-only</report>
                    </reports>
                </reportSet>
            </reportSets>
        </plugin>
    </plugins>
</reporting>
```

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[surefire-url]:https://maven.apache.org/surefire/maven-surefire-plugin/
[failsafe-url]:https://maven.apache.org/surefire/maven-failsafe-plugin/
[surefire-report-url]:https://maven.apache.org/surefire/maven-surefire-report-plugin/
[parameter-mapping-url]:https://maven.apache.org/guides/mini/guide-configuring-plugins.html#configuring-parameters
[configuring-plugins-url]:https://maven.apache.org/guides/mini/guide-configuring-plugins.html#generic-configuration
[maven-profiles-url]:https://maven.apache.org/guides/introduction/introduction-to-profiles.html
[maven-site-plugin-url]:https://maven.apache.org/plugins/maven-site-plugin/
[site-reporting-url]:https://maven.apache.org/guides/mini/guide-configuring-plugins.html#configuring-reporting-plugins
[executions-url]:https://maven.apache.org/guides/mini/guide-configuring-plugins.html#configuring-build-plugins
[single-test-run-url]:https://maven.apache.org/surefire/maven-surefire-plugin/examples/single-test.html
[build-lifecycle-url]:https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html
[maven-goals-url]:https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#a-build-phase-is-made-up-of-plugin-goals
[maven-phases-url]:https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#a-build-lifecycle-is-made-up-of-phases
[surefire-test-goal-url]:https://maven.apache.org/surefire/maven-surefire-plugin/test-mojo.html
[failsafe-integration-test-goal-url]:https://maven.apache.org/surefire/maven-failsafe-plugin/integration-test-mojo.html
[report-only-goal-url]:https://maven.apache.org/surefire/maven-surefire-report-plugin/report-only-mojo.html
[failsafe-report-only-goal-url]:https://maven.apache.org/surefire/maven-surefire-report-plugin/failsafe-report-only-mojo.html
[test-phase-url]:https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#lifecycle-reference
[integration-test-phase-url]:https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#lifecycle-reference
[site-generation-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/SiteGeneration/sitegeneration/index.html