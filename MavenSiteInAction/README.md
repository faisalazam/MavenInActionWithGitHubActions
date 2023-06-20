## Maven Site In Action

The purpose of this small project is to utilize the [maven's site][maven-site-plugin-url] plugin to generate the project reports.

## Plugins to use
We need to add the following couple of plugins:
```
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-site-plugin</artifactId>
            <version>${maven-site-plugin.version}</version>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-project-info-reports-plugin</artifactId>
            <version>${maven-project-info-reports-plugin.version}</version>
        </plugin>
    </plugins>
</build>
```

The generated site will be deployed automatically as part of every push to the GitHub Pages. The deployment will happen using GitHub workflows/actions.

Learn more about [create the documentation for your project][site-guide-url]

## Configuring the project site
[Site descriptor][site-descriptor-url] is the `site.xml` file located in `src/site`. It's an xml file used to configure
the project site. Resources like images, css, js required for the site can be placed in `src/site/resources`.

## Generating the Project Site for multimodule project

Running the `mvn site` from the parent will actually create the `target/site` folders with proper site generation
for each of the modules, but the problem is, when we try to navigate to the submudule's link from the
parent's site, those links will be broken. In order to fix that issue, a simple solution would be to 
configure the stage goal. It will automatically aggregate the documentation of each module in 
the ${project.baseURI}/target/staging folder. The trick is to add this to the parent pom of all the submodules:

```
<distributionManagement>
    <site>
        <id>${project.artifactId}-site</id>
        <url>${project.baseUri}</url>
    </site>
</distributionManagement>
```

## Configuring Reports
In order to configure a subset of the standard reports which are included by default, include the reporting in pom:
```
<reporting>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-project-info-reports-plugin</artifactId>
            <version>${maven-project-info-reports-plugin.version}</version><!-- define version here if not already defined in build/plugins or build/pluginManagement -->
            <reportSets>
                <reportSet>
                    <reports>
                        <!-- Subset of reports to be included -->
                        <report>index</report>
                        <report>scm</report>
                    </reports>
                </reportSet>
            </reportSets>
        </plugin>
    </plugins>
</reporting>
```
Learn more about the [Project Info Reports Plugin][project-info-report-plugin-url]

## Generating the Project Site

* To clean the `target` directory, run:
  * `mvn clean`
* To generate the project site, run:
  * `mvn site`
* To generate the project site will submodules, run from the pom aggregator:
  * `mvn clean site site:stage`

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[maven-site-plugin-url]:https://maven.apache.org/plugins/maven-site-plugin/
[site-guide-url]:https://maven.apache.org/guides/mini/guide-site.html
[site-descriptor-url]:https://maven.apache.org/guides/mini/guide-site.html#creating-a-site-descriptor
[project-info-report-plugin-url]:https://maven.apache.org/plugins/maven-project-info-reports-plugin/