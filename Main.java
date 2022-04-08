import java.util.ArrayList;

public class Main {

    private static boolean subsetSum(ArrayList<Integer> array, Integer currentIndex, Integer len, Integer sum) {
        // stop if the sum reaches a negative number
        if (sum < 0) return false;

        /*
         * if we reached the maximum number of elements that we are looking for
         * check if the sum of all the elements that were taken until now is
         * the desired sum
         */
        if (len == 0) {
            return sum == 0;
        }

        // if we do not have any elements to check, return false
        if (currentIndex == -1) return false;

        /*
         * check if we can get the desired sum if we go to the next element, without taking
         * the current element into consideration
         */
        boolean currentElemNotTaken = subsetSum(array, currentIndex - 1, len, sum);

        /*
         * check if we can get the desired sum if we go to the next element, taking the current
         * element into consideration
         */
        boolean currentElementTaken = subsetSum(array, currentIndex - 1, len - 1, sum - array.get(currentIndex));

        return currentElemNotTaken || currentElementTaken;
    }

    // compute the sum of the given array
    private static int computeSum(ArrayList<Integer> array) {
        int sum = 0;

        for (Integer elem : array) {
            sum += elem;
        }

        return sum;
    }

    public static boolean splitArray(ArrayList<Integer> array) {
        int arrayLength = array.size();
        boolean solutionFound = false;

        // compute the sum of elements in the array
        int arraySum = computeSum(array);

        /*
         * search for a subset of length "i" having the sum of all elements in that subset
         * equal to a potential sum computed using the sum of all elements in the input
         * list, the length of the input list, and the length "i"
         */
        for (int i = 1; i < arrayLength && !solutionFound; i++) {
            Integer potentialSum = (arraySum * i) / arrayLength;

            /*
             * check if the potential sum that we are looking for
             * is an integer number
             */
            if ((arraySum * i) % arrayLength == 0) {
                /*
                 * check if we can find a list in which the sum of all elements
                 * is equal to the potential sum
                 */
                if ((subsetSum(array, arrayLength - 1, i, potentialSum)))
                    solutionFound = true;
            }
        }

        return solutionFound;
    }

    public static void main(String[] args) {
        ArrayList<Integer> inputArray = new ArrayList<>();

        // add test data into the array
        for (int i = 1; i <= 8; i++) {
            inputArray.add(i);
        }

        System.out.println(splitArray(inputArray));
    }
}
