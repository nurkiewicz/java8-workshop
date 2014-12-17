# Java 8 and RxJava workshop driven by tests

## Smoke testing

### Maven

Run `mvn clean test` to make sure you have JDK 8 and all dependencies in place.

### IDE

Run `J01_HelloWorldTest.java` from your favourite IDE and make sure it compiles and passes.

## Troubleshooting

### Error `invalid target release: 1.8` during maven build

If you see this error message during maven build:

	[INFO] BUILD FAILURE
	...
	[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.1:compile (default-compile) on project java8:
	Fatal error compiling: invalid target release: 1.8 -> [Help 1]

it means you are not compiling using Java 8. Download JDK 8 and let maven use it:

	$ export JAVA_HOME=/path/to/jdk8

## License
This project is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
