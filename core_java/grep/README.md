# Introduction
The Java Grep application mimics the Linux grep command 
by taking in a given root directory, regex pattern and 
output file, and then recursively traversing through all 
the files in the directory, going through each line within each file,
and writing each line that contains the regex pattern to 
the output file. 

The project was built in Java using the IntelliJ IDE and 
relies on the Maven project management tool. The project uses Lists,
regex, lf4j, slf4j, log4j, java.io, java.nio.file, lambda and stream.
A docker image was created for the project and uploaded to Docker Hub.

# Quick Start
The grep application can be run by passing a regular expression, 
root directory and out file as CLI arguments to the 
JavaGrepLambdaImp file. This can be achieved by opening JavaGrepLambdaImp.java in IntelliJ,
navigating to the Run/Debug configurations, and inputting the CLI
arguments. Then, the program can be executed by running the JavaGrepLambdaImp.java file.

#Implemenation
A JavaGrep interface was created to define the following methods:
- A process method for high-level workflow purposes. 
- Getter and setter methods  for each of the three private variables (regex, rootPath 
and outFile). 
- A listFiles method to recursively traverse a given directory and return all files.
- A readLines method to read a file and return all the lines.
- A containsPattern method to check if a line contains the regex pattern passed by the user.
- A writeToFile method to write lines to a file.

These methods were implemented in the JavaGrepImp.java file. To incorporate Java Streams 
and Lambda functions, a JavaGrepLambdaImp file was created that extends the JavaGrepImp.java file.

## Pseudocode
Below is the psudocode for the process method:
```
matchedLines = []
for file in listFilesRecursively(rootDir)
    for line in readLines(file)
        if containsPattern(line)
            matchedLines.add(line)
writeToFile
```

## Performance Issue
A memory issue occurs when the grep app attempts to process a huge amount of data, given the limitations to heap memory.
This leads to an OutOfMemory error, since there is insufficient space on the heap to allocate the given object.
A way to solve this issue is to use BufferedReader and BufferedWriter for data I/O purposes, because of their fast
performance and lower reliance on CPU and memory. Another solution is to use Java 8 Streams instead of for loops.


# Test
To test the application, an output file was created and a regex pattern, 
the path of root directory and the path of the output file were passed as CLI arguments to ensure
the program works.

# Deployment
The program was deployed using Docker. First, a docker hub account was registered.
Then, a Dockerfile was created with the following lines of code:
```
FROM openjdk:8-alpine
COPY target/grep*.jar /usr/local/app/grep/lib/grep.jar
ENTRYPOINT ["java","-jar","/usr/local/app/grep/lib/grep.jar"]
```
Once the Dockerfile has been created, the java app was packaged by running the 
"mvn clean package" command. Then, a docker image was created and pushed to Docker Hub.

# Improvement
The app can be improved in the following ways:
1. Create an executable file with a GUI.
2. In addition to listing the lines that match the regex pattern, indicate the location of each file that contains the pattern and the location(s) within the file where the pattern is found.
3. Allow to pass in a list of multiple regular expressions at once, and a list of multiple root directories at once.