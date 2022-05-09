#!/bin/sh

# delete target directory
rm -rf target

# delete lib directory
rm -rf lib

# creating target directory
mkdir target

# creating lib directory
mkdir lib

# download libraries
touch lib/jcommander-1.82.jar
curl -o lib/jcommander-1.82.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar

touch lib/JCDP-4.0.2.jar
curl -o lib/JCDP-4.0.2.jar https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar

# compilation
javac -d target -sourcepath src/java -cp lib/JCDP-4.0.2.jar:lib/jcommander-1.82.jar:. `find . -name "*.java"`

# copy
cp -a src/resources target

# copy class-files from jar
jar -xf lib/jcommander-1.82.jar
jar -xf lib/JCDP-4.0.2.jar
rm -rf META-INF
mv com target

# archivation
jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .

# run program:
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN