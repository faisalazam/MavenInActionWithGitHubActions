#!/bin/bash

git remote set-url origin https://git:"${GITHUB_TOKEN}"@github.com/"${GITHUB_REPOSITORY}".git
mkdir /tmp/gh-pages
cp README.md /tmp/gh-pages
cp -r target/site /tmp/gh-pages/site
cp -r target/surefire-reports /tmp/gh-pages/surefire-reports
cp target/*-SNAPSHOT*.jar* /tmp/gh-pages
npx gh-pages --dist /tmp/gh-pages -u "github-actions-bot <support+actions@github.com>"
rm -rf /tmp/gh-pages