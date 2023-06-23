[![Parent CI/CD](https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/parent-build.yml/badge.svg)](https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/parent-build.yml)
[![pages-build-deployment](https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/pages/pages-build-deployment)
[![Instructions Coverage](https://faisalazam.github.io/MavenInActionWithGitHubActions/INDIVIDUAL_MODULES/PuttingAllTogether/jacoco-merged/jacoco-resources/badges/jacoco.svg)](https://faisalazam.github.io/MavenInActionWithGitHubActions/INDIVIDUAL_MODULES/PuttingAllTogether/jacoco-merged/index.html)
[![Branches Coverage](https://faisalazam.github.io/MavenInActionWithGitHubActions/INDIVIDUAL_MODULES/PuttingAllTogether/jacoco-merged/jacoco-resources/badges/branches.svg)](https://faisalazam.github.io/MavenInActionWithGitHubActions/INDIVIDUAL_MODULES/PuttingAllTogether/jacoco-merged/index.html)
[![checkstyle](https://faisalazam.github.io/MavenInActionWithGitHubActions/INDIVIDUAL_MODULES/PuttingAllTogether/badges/checkstyle-result.svg)](https://faisalazam.github.io/MavenInActionWithGitHubActions/INDIVIDUAL_MODULES/PuttingAllTogether/checkstyle.html)
[![Known Vulnerabilities](https://snyk.io/test/github/faisalazam/maveninactionwithgithubactions/badge.svg)](https://snyk.io/test/github/faisalazam/maveninactionwithgithubactions)
[![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/apache/maven.svg?label=License)](https://www.apache.org/licenses/LICENSE-2.0)

<h1 style="text-align:center">
  Maven/Plugins &amp; GitHub Pages/Workflows/Actions
  <img src="https://github.com/faisalazam/MavenInActionWithGitHubActions/raw/master/.github/assets/awesome-badge.svg" alt="" />
</h1>

<p>
    <a href="https://faisalazam.github.io/MavenInActionWithGitHubActions">This project</a> 
    will automatically be deployed to <a href="https://pages.github.com/">GitHub Pages</a> 
    with <a href="https://github.com/features/actions">GitHub Actions</a> by going through the following steps:
</p>
<p>
    <a href="https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/putting-all-together.yml">
        <img src="https://github.com/faisalazam/MavenInActionWithGitHubActions/raw/master/.github/assets/putting-all-together.png" alt="" />
    </a>
</p>

## Inspiration
<p>
    While working on a personal project, felt a need to develop and share a template maven project solving 
    the basic problems. Some of them are included below which led to a series of small projects in this repo:
</p>
<ul>
    <li>Running all tests</li>
    <li>Running single test, class, package etc.</li>
    <li>Running unit and integration tests separately</li>
    <li>Code coverage for unit and integration tests separately</li>
    <li>Combined code coverage for unit and integration tests</li>
    <li>Other checks like checkstyle, PMD, etc.</li>
    <li>Project Site generation for single module setup</li>
    <li>Project Site generation for multi module setup</li>
    <li>Automated deployment to GitHub Pages using GitHub Workflows and Actions</li>
</ul>

## Modules
Following are the submodules of this [parent project][parent-project-url]:

| Module                                         | Status                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
|------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [SiteGeneration][site-generation-url]          | [![Site CI/CD](https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/site-generation.yml/badge.svg)](https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/site-generation.yml) [![pages-build-deployment](https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/pages/pages-build-deployment)                         |
| [TestsExecution][tests-execution-url]          |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [JacocoExecution][jacoco-execution-url]        |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [PuttingAllTogether][putting-all-together-url] | [![PuttingAllTogether CI/CD](https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/putting-all-together.yml/badge.svg)](https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/putting-all-together.yml) [![pages-build-deployment](https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/pages/pages-build-deployment) |

Every next submodule is built on top of the previous submodule. For example, [TestsExecution][tests-execution-url] is 
built on top of [SiteGeneration][site-generation-url].

## Maven and Java Version
Following are the versions which are installed and used to develop these projects:
```
Apache Maven 3.9.2 (c9616018c7a021c1c39be70fb2843d6f5f9b8a1c)
Maven home: /usr/local/Cellar/maven/3.9.2/libexec
Java version: 20.0.1, vendor: Homebrew, runtime: /usr/local/Cellar/openjdk/20.0.1/libexec/openjdk.jdk/Contents/Home
Default locale: en_AU, platform encoding: UTF-8
OS name: "mac os x", version: "13.4", arch: "x86_64", family: "mac"
```

Contributing
------------

We accept Pull Requests via GitHub. Comments on the PRs is the main channel of communication for contributors.
There are some guidelines which will make applying PRs easier for us:
+ No tabs! Please use spaces for indentation.
+ Respect the code style.
+ Create minimal diffs - disable on save actions like reformat source code or organize imports. If you feel the source code should be reformatted create a separate PR for this change.
+ Provide [JUnit tests][junit-url] for your changes and ensure that your changes don't break any existing tests by running:
    + ```mvn clean test verify```
+ You can check whether there are currently broken tests at the [Continuous Integration][parent-cicd-link-url] page.

If you plan to contribute on a regular basis, please consider filing a [contributor license agreement][contributor-license-agreement-url].
You can learn more about contributing via GitHub in our [contribution guidelines](CONTRIBUTING.md).

License
-------
This code is under the [Apache Licence v2][apache-license-link-url].

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[parent-cicd-link-url]:https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/parent-build.yml
[apache-license-badge-url]:https://img.shields.io/github/license/apache/maven.svg?label=License
[apache-license-link-url]:https://www.apache.org/licenses/LICENSE-2.0
[parent-project-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/index.html
[site-generation-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/SiteGeneration/sitegeneration/index.html
[tests-execution-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/TestsExecution/testsexecution/index.html
[jacoco-execution-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/JacocoExecution/jacocoexecution/index.html
[putting-all-together-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/PuttingAllTogether/puttingalltogether/index.html
[junit-url]:https://junit.org/junit5/docs/current/user-guide
[contributor-license-agreement-url]:https://www.apache.org/licenses/#clas