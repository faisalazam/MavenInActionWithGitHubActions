[![Parent CI/CD][parent-cicd-badge-url]][parent-cicd-link-url]
[![pages-build-deployment][pages-build-deployment-badge-url]][pages-build-deployment-link-url]
[![Instructions Coverage][instructions-coverage-badge-url]][instructions-coverage-link-url]
[![Branches Coverage][branches-coverage-badge-url]][branches-coverage-link-url]
[![checkstyle][checkstyle-badge-url]][checkstyle-link-url]
![Known Vulnerabilities][known-vulnerabilities-badge-url]
[![Apache License, Version 2.0, January 2004][apache-license-badge-url]][apache-license-link-url]


<h1 style="text-align:center">
  Maven, Plugins, GitHub Pages, and GitHub Workflows/Actions
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
Following are the sub-modules of this [parent project][parent-project-url]:

| Module                                         | Status                                                                                                                                                                                      |
|------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [SiteGeneration][site-generation-url]          | [![Site CI/CD][site-cicd-badge-url]][site-cicd-link-url] [![pages-build-deployment][pages-build-deployment-badge-url]][pages-build-deployment-link-url]                                     |
| [TestsExecution][tests-execution-url]          |                                                                                                                                                                                             |
| [JacocoExecution][jacoco-execution-url]        |                                                                                                                                                                                             |
| [PuttingAllTogether][putting-all-together-url] | [![PuttingAllTogether CI/CD][putting-all-together-badge-url]][putting-all-together-link-url] [![pages-build-deployment][pages-build-deployment-badge-url]][pages-build-deployment-link-url] |



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[parent-cicd-badge-url]:https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/parent-build.yml/badge.svg
[parent-cicd-link-url]:https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/parent-build.yml
[pages-build-deployment-badge-url]:https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/pages/pages-build-deployment/badge.svg
[pages-build-deployment-link-url]:https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/pages/pages-build-deployment
[instructions-coverage-badge-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/INDIVIDUAL_MODULES/PuttingAllTogether/jacoco-merged/jacoco-resources/badges/jacoco.svg
[instructions-coverage-link-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/INDIVIDUAL_MODULES/PuttingAllTogether/jacoco-merged/index.html
[branches-coverage-badge-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/INDIVIDUAL_MODULES/PuttingAllTogether/jacoco-merged/jacoco-resources/badges/branches.svg
[branches-coverage-link-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/INDIVIDUAL_MODULES/PuttingAllTogether/jacoco-merged/index.html
[checkstyle-badge-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/INDIVIDUAL_MODULES/PuttingAllTogether/badges/checkstyle-result.svg
[checkstyle-link-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/INDIVIDUAL_MODULES/PuttingAllTogether/checkstyle.html
[known-vulnerabilities-badge-url]:https://snyk.io/test/github/faisalazam/maveninactionwithgithubactions/badge.svg
[apache-license-badge-url]:https://img.shields.io/github/license/apache/maven.svg?label=License
[apache-license-link-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/licenses.html
[site-cicd-badge-url]:https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/site-generation.yml/badge.svg
[site-cicd-link-url]:https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/site-generation.yml
[putting-all-together-badge-url]:https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/putting-all-together.yml/badge.svg
[putting-all-together-link-url]:https://github.com/faisalazam/MavenInActionWithGitHubActions/actions/workflows/putting-all-together.yml
[parent-project-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/index.html
[site-generation-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/SiteGeneration/sitegeneration/index.html
[tests-execution-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/TestsExecution/testsexecution/index.html
[jacoco-execution-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/JacocoExecution/jacocoexecution/index.html
[putting-all-together-url]:https://faisalazam.github.io/MavenInActionWithGitHubActions/staging/PuttingAllTogether/puttingalltogether/index.html