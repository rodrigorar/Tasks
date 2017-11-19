#!/bin/bash

build=tasks-0.1.3

function build_directories() {
	echo "Creating directory structure ..."
	mkdir $build
	mkdir $build/resources
}

function copy_resources() {
	echo "Copying resources ..."
	cp LICENSE $build/
	cp README.md $build/
	cp src/main/resources/install.sh $build/
	cp src/main/resources/tasks.png $build/resources/
	cp src/main/resources/tasks.desktop $build/resources/
	cp src/main/resources/tasks.sh $build/resources/
	cp src/main/resources/base_data_file.xml $build/resources/
	cp src/main/resources/settings.xml $build/resources/
	cp --recursive src/main/resources/languages $build/resources/
}

function create_jar() {
	mvn clean
	mvn compile
	mvn package
	cp target/$build.jar $build
	cp --recursive target/dependency-jars $build
	mvn clean
}

function package() {
	tar -cf $build.tar.gz $build
	rm --recursive --force $build
}

build_directories
copy_resources
create_jar
package
echo "Package Finished."
