#!/bin/sh
# remove target folder
rm -rf target

# create target folder
mkdir target

# compile src java files to make class files in target folders
javac -sourcepath src/java `find . -name "*.java"` -d target

# launch java program with arguments
java -classpath ./target edu.school21.printer.app.Program . 0 /home/dn/Desktop/it.bmp