package ca.jrvs.apps.grep;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepLambdaImp extends JavaGrepImp {
    final Logger logger = LoggerFactory.getLogger(ca.jrvs.apps.grep.JavaGrep.class);
    private String regex;
    private String rootPath;
    private String outFile;

    /**
     * Traverse a given directory and return all files
     * @param rootDir
     * @return files under the rot dir
     */
    @Override
    public List<File> listFiles(String rootDir) throws IOException {
        List<File> files = new ArrayList<File>();
        Files.walk(Paths.get(rootDir)).filter(Files::isRegularFile).map(Path::toFile).forEach(files::add);
        return files;
    }
    /**
     * Read a file and return all the lines. (Use FileReader, BufferedReader, CharacterEncoding)
     *
     * @param inputFile
     * @return lines
     * @throws IllegalArgumentException if the given inputFile is not a file
     */
    @Override
    public List<String> readLines(File inputFile) throws IllegalArgumentException, FileNotFoundException, IOException {
        List<String> lines = new ArrayList<String>();
        Files.lines(inputFile.toPath()).forEach(lines::add);
        return lines;
    }

    /**
     * Write lines to a file
     * @param lines
     * @throws IOException
     */
    @Override
    public void writeToFile(List<String> lines) throws IOException{
        PrintWriter pw = new PrintWriter(new FileWriter(getOutFile()));
        lines.forEach(pw::printf);
        pw.close();
    }

    public static void main(String[] args) {
        if (args.length != 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        BasicConfigurator.configure();

        JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
        javaGrepLambdaImp.setRegex(args[0]);
        javaGrepLambdaImp.setRootPath(args[1]);
        javaGrepLambdaImp.setOutFile(args[2]);

        try {
            javaGrepLambdaImp.process();
        } catch (Exception ex) {
            javaGrepLambdaImp.logger.error("Error: Unable to process", ex);
        }

    }
}
