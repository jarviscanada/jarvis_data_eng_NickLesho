import java.util.regex.*;
import java.util.Scanner;

public class RegexExc {

    public boolean matchJpeg(String filename){
        return matchRegex(filename, ".+\\.jpe?g$");
    }

    public boolean matchIp(String ip){
        return matchRegex(ip, "^\\d{1,3}(\\.\\d{1,3}){3}$");
    }

    public boolean isEmptyLine(String line){
        return matchRegex(line, "^(\\s)*$");
    }

    public boolean matchRegex(String text, String pattern){
        Pattern pt = Pattern.compile(pattern);
        Matcher mt = pt.matcher(text);
        return mt.matches();
    }

    public static void main(String[] args){
        RegexExc r = new RegexExc();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter string");
        String input = myObj.nextLine();
        System.out.println("jpeg: " + r.matchJpeg(input));
        System.out.println("ip: " + r.matchIp(input));
        System.out.println("empty : " + r.isEmptyLine(input));
    }

}