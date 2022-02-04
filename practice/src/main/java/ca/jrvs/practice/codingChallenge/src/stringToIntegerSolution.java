package ca.jrvs.practice.codingChallenge;
public class stringToIntegerSolution {
  public int stringToInteger(String s){
      String digits = "0123456789";
      int posNeg = 1;
      int conversion = 0;
      for (char c: s){
        if (c == '-'){
          posNeg = -1 * posNeg;
        }
        if (digits.contains(c)){
          conversion = 10 * conversion + (int) c;
        }
      }
      return posNeg * conversion;
  }
}
