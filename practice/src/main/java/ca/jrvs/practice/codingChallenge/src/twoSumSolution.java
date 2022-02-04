package ca.jrvs.practice.codingChallenge;

/*
Big-O: O(n) where n is the length of the array passed in.
In the worst case, the function will loop through the whole array, checking
for a value in the HashMap and adding values to the HashMap. Both of these processes
have a O(1) run-time. Therefore, by looping through the array and performing two O(1)
operations each time, the run time is O(n)
 */
public class twoSumSolution {
  public int[] twoSum(int[] arr, int target){
      HashMap<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < arr.length; i++){
        if (map.containsKey(target - arr[i])){
          return [map.get(target-arr[i]), i];
        }
        map.put(arr[i], i);
      }
      return [-1, -1];
  }
}
