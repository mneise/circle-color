# circle-color

A small ClojureScript project to try out JS transforms and JS module support.

## Setup

For this example to work, you need to build your own version of the Google Closure compiler and the ClojureScript compiler. Go ahead and clone my fork of the Google Closure compiler and then check out the `umd-support` branch:

```
$ git clone git@github.com:mneise/closure-compiler.git
$ cd closure-compile
$ git checkout -t origin/umd-support
```

Let's build the Google Closure compiler and install a new version into our local maven repository:

```
$ mvn install
```

This should have installed the version `1.0-SNAPSHOT` of the Google Closure compiler. Now we need to build our own version of the ClojureScript compiler. Let's check out the project:

```
$ git clone git@github.com:clojure/clojurescript.git
$ cd clojurescript
```

Before building it, we need to change the version of the Google Closure compiler to the one we just build. Open the file `pom.template.xml` and find the dependency for the Google Closure compiler. The file should contain an entry that looks similar to this:

```
<dependency>
    <groupId>com.google.javascript</groupId>
    <artifactId>closure-compiler</artifactId>
    <version>v20150729</version>
</dependency>
```

Change the version from `v20150729` to `1.0-SNAPSHOT`. You entry now should look like this:

```
<dependency>
    <groupId>com.google.javascript</groupId>
    <artifactId>closure-compiler</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

We can now build a new version of the ClojureScript compiler:

```
$ ./script/build
```

Assuming you have already cloned this project, change the ClojureScript version in this project's `project.clj` file from `1.7.48` to the version we just build, e.g. `1.7.68`, and build the project:

```
$ cd circle-color
$ ./scripts/build
$ open resources/public/index.html
```

## License

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
