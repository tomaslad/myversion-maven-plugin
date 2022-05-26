myversion-maven-plugin
=====================

The goal of myversion-maven-plugin is to provide a standardized way to calculate a project version from a git repository.

[How to use it](https://github.com/tomaslad/myversion-maven-plugin-example)

# Opinions

Project using this plugin will use [semantic versions](http://semver.org/) style strings. e.g. `major.minor.patch-<prerelease>+<metadata>`

# Goals

* Compute the version of your project from git repository. 
* Bump the version for a release.

# Minimal requirements

* Java 8 
* Maven 3.6.3
* Git 2.9.5

# Inspired from

* https://github.com/jgitver/jgitver-maven-plugin
* https://github.com/qoomon/maven-git-versioning-extension
* https://github.com/nebula-plugins/nebula-release-plugin
* https://github.com/palantir/gradle-git-version
* https://github.com/allegro/axion-release-plugin
