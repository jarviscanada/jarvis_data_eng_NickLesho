package ca.jrvs.practice.codingChallenge;

public class fibonacciSolution {
    public class Integer fibonacci(Integer position){
        if (position == 1 || position == 2){
            return 1;
        }
        return fibonacci(position - 1) + fibonacci(position - 2);
    }
}
