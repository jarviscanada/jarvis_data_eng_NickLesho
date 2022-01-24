package ca.jrvs.apps.grep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepImp implements JavaGrep {
    final Logger logger = LoggerFactory.getLogger(ca.jrvs.apps.grep.JavaGrep.class);
    private String regex;
    private String rootPath;
    private String outFile;

    @Override
    public String getRegex() { return regex; }

    @Override
    public void setRegex(String regex) { this.regex = regex; }

    @Override
    public String getOutFile() { return outFile; }

    @Override
    public void setOutFile(String outFile) { this.outFile = outFile; }

    @Override
    public String getRootPath() { return rootPath; }

    @Override
    public void setRootPath(String rootPath) { this.rootPath = rootPath; }

    /**
     * check if a line contains the regex pattern (passed by user)
     * @param line
     * @return true if there is a match
     */
    @Override
    public boolean containsPattern(String line) { return Pattern.matches(getRegex(), line); }

    /**
     * Traverse a given directory and return all files
     * @param rootDir
     * @return files under the rot dir
     */
    @Override
    public List<File> listFiles(String rootDir) throws IOException {
        File folder = new File(rootDir);
        File[] filesList = folder.listFiles();
        List<File> files = new ArrayList<File>();
        for(File file : filesList) {
            if (file.isDirectory()){
                for (File f: listFiles(file.getPath())){
                    files.add(f);
                }
            }
            if(file.isFile()) {
                files.add(file);
            }
        }
        return files;
    }
    /**
     * Read a file and return all the lines.
     *
     * @param inputFile
     * @return lines
     * @throws IllegalArgumentException if the given inputFile is not a file
     */
    @Override
    public List<String> readLines(File inputFile) throws IllegalArgumentException, FileNotFoundException, IOException {
        List<String> lines = new ArrayList<String>();
        FileInputStream fstream = new FileInputStream(inputFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null)   {
            lines.add(strLine);
        }
        fstream.close();
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
        for (String line: lines) {
            pw.printf(line);
        }
        pw.close();
    }

    /**
     * Top level search workflow
     * @throws IOException
     */
    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<String>();
        List<File> filesList = listFiles(getRootPath());
        for (File file : filesList){
            for (String line : readLines(file)) {
                if (containsPattern(line)){
                    matchedLines.add(line);
                }
            }
        }

        writeToFile(matchedLines);
    }

    public static void main(String[] args) {
        if (args.length != 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        BasicConfigurator.configure();

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        } catch (Exception ex) {
            javaGrepImp.logger.error("Error: Unable to process", ex);
        }

    }
}

























