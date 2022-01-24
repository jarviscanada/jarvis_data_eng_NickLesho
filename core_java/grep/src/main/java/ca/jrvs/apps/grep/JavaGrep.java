package ca.jrvs.apps.grep;

import java.io.FileNotFoundException;
import java.util.List;
import java.io.File;
import java.io.IOException;

public interface JavaGrep {

    /**
     * Top level search workflow
     * @throws IOException
     */
    void process() throws IOException;

    /**
     * Traverse a given directory and return all files
     * @param rootDir
     * @return files under the rot dir
     */
    List<File> listFiles(String rootDir) throws IOException;

    /**
     * Read a file and return all the lines.
     *
     * @param inputFile
     * @return lines
     * @throws IllegalArgumentException if the given inputFile is not a file
     */
    List<String> readLines(File inputFile) throws IllegalArgumentException, FileNotFoundException, IOException;

    /**
     * check if a line contains the regex pattern (passed by user)
     * @param line
     * @return true if there is a match
     */
    boolean containsPattern(String line);

    /**
     * Write lines to a file
     * @param lines
     * @throws IOException
     */
    void writeToFile(List<String> lines) throws IOException;

    String getRootPath();

    void setRootPath(String rootPath);

    String getRegex();

    void setRegex(String regex);

    String getOutFile();

    void setOutFile(String outFile);
}

