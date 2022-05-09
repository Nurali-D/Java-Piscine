#!/bin/sh

# delete target directory
rm -rf target

# creating target directory
mkdir target

# compile java files to make class files in target directory
javac `find . -name "*.java"` -d target

# copy resources to target directory
cp -R src/resources target/.

# make a jar archive in target directory
jar cvfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

# changing permissions of jar archive for execution
chmod u+x target/images-to-chars-printer.jar

# launch jar with arguments
java -jar target/images-to-chars-printer.jar . 0