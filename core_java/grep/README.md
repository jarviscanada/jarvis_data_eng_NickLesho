# Introduction
The Java Grep application mimics the Linux grep command 
by taking in a given root directory, regex pattern and 
output file, and then recursively traversing through all 
the files in the directory, going through each line with each file,
and writing each line that contains the regex pattern to 
the output file. 

The project was built in Java using the IntelliJ IDE and 
relies on the Maven project management tool. The project uses Lists,
regex, lf4j, slf4j, log4j, java.io, java.nio.file, lambda and stream.
A docker image was created for the project and uploaded to Docker Hub.

# Quick Start
The grep application can be run by passing a regular expression, 
root directory and out file as CLI arguments to the 
JavaGrepLambdaImp file. 

#Implemenation
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
(30-60 words)
Discuss the memory issue and how would you fix it

# Test
How did you test your application manually? (e.g. prepare sample data, run some test cases manually, compare result)

# Deployment
How you dockerize your app for easier distribution?

# Improvement
1. Create an executable file with a GUI.
2. Rather than just listing the lines that match the regex pattern, indicate the location of each file that contains the pattern and the location(s) within the file where the pattern is found.
3. Allow to pass in a list of regular expressions at once, and a list of root directories at once.