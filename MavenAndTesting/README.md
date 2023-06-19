The purpose of this small project is to utilize the maven's site plugin to generate the project reports.

The generated site will be deployed automatically as part of every push to the GitHub Pages. The deployment will happen using GitHub workflows/actions.

## Generating the Project Site

* To clean the `target` directory, run:
    * `mvn clean`
* To generate the project site, run:
    * `mvn site`