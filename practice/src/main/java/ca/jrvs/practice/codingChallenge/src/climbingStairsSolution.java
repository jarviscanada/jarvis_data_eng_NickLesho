package ca.jrvs.practice.codingChallenge;

public class climbingStairsSolution {
  public int climbingStairs(Integer stairs){
      if (stairs <= 3){
          return stairs;
      }
      return fibonacci(stairs);
  }

  public int fibonacci(int position){
      if (position == 1 || position == 2){
          return 1;
      }
      return fibonacci(position - 1) + fibonacci(position - 2);
  }
}
