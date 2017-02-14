/****************************************************
 *   Program Title: Algorithms for the maximum      *
 *                  continuous subsequent sum       *
 *   Author:  Marcus Mallum                         *
 *   Class: CSCI3320, Spring 2017         	        *
 *   Assignment #1 		                            *
 ****************************************************/
public class maxSum
{
    /***************************************************
     *  CONSTRUCTOR: maxSum                      	   *
     *  PURPOSE: Used to initialize a maxSum object    *
     *  INPUT PARAMETERS : None                        *
     *  OUTPUT: None                                   *
     ***************************************************/
    public maxSum()
    {
    }

    /***************************************************
     *  FUNCTION: maxSubSum2                      	   *
     *  PURPOSE: Implementation of Algorithm 2 of the  *
     *           maximum continuous subsequent sum     *
     *  INPUT PARAMETERS : int[] a, holds the sequence *
     *                              of numbers         *
     *  OUTPUT: int, the maximum continuous subsequent *
     *               sum                               *
     ***************************************************/
    public int maxSubSum2(int[] a)
    {
        int maxSum = 0;
        int startIndex = 0;
        int endIndex = 0;
        long startTime = System.nanoTime();

        // Find all possible sums
        for(int i = 0; i < a.length; i++)
        {
            int thisSum = 0;
            for(int j = i; j < a.length; j++)
            {
                thisSum += a[j];
                // If local sum is greater than global sum, update global sum
                // start index, and end index
                if(thisSum > maxSum)
                {
                    maxSum = thisSum;
                    startIndex = i;
                    endIndex = j;
                }
            }
        }
        long timeElapsed = System.nanoTime() - startTime;

        System.out.printf("Max Sum: %d, S_index: %d, E_index: %d\n", maxSum, startIndex, endIndex);
        System.out.printf("Execution Time: %d nanoseconds\n\n", timeElapsed);
        return maxSum;
    }

    /***************************************************
     *  FUNCTION: maxSubSum3                      	   *
     *  PURPOSE: Implementation of Algorithm 3 of the  *
     *           maximum continuous subsequent sum     *
     *  INPUT PARAMETERS : int[] a, holds the sequence *
     *                              of numbers         *
     *  OUTPUT: int, the maximum continuous subsequent *
     *               sum                               *
     ***************************************************/
    public int maxSubSum3(int[] a)
    {
        // Indices for the maxSumValues array
        final int MAXSUM = 0;
        final int STARTINDEX = 1;
        final int ENDINDEX = 2;

        long startTime = System.nanoTime();
        int[] maxSumValues = new int[3];    // Used to hold the max sum, start index, and end index
        maxSumValues = maxSumRec(a, 0, a.length - 1, maxSumValues); // Get max sum using recursive method
        long timeElapsed = System.nanoTime() - startTime;
        System.out.printf("Max Sum: %d, S_index: %d, E_index: %d\n", maxSumValues[MAXSUM], maxSumValues[STARTINDEX],
                                                                     maxSumValues[ENDINDEX]);
        System.out.printf("Execution Time: %d nanoseconds\n\n", timeElapsed);
        return maxSumValues[MAXSUM];
    }

    /*****************************************************
     *  FUNCTION: maxSumRec                      	     *
     *  PURPOSE: Implements the recursion of algorithm 3 *
     *  INPUT PARAMETERS : int[] a                       *
     *                          holds the sequence of    *
     *                          numbers                  *
     *                     int left                      *
     *                          the left index of the    *
     *                          sequence                 *
     *                     int right                     *
     *                          the right index of the   *
     *                          sequence                 *
     *                     int [] maxSumValues           *
     *                          holds the global maximum *
     *                          sum, the start index,    *
     *                          and the end index        *
     *  OUTPUT: int[], the maximum sum, the starting     *
     *                 index, and the end index          *
     *****************************************************/
    private int[] maxSumRec(int[] a, int left, int right, int[] maxSumValues)
    {
        // Indices for the maxSumValues array
        final int GLOBALMAXSUM = 0;
        final int STARTINDEX = 1;
        final int ENDINDEX = 2;

        int leftStartIndex; // Hold left index of middle sum
        int rightEndIndex;  // Hold right index of middle sum
        // Base case
        if(left == right)
        {
            // If input size is only 1, and element is > 0, set max sum
            if(a[left] > 0 && a.length == 1)
            {
                maxSumValues[GLOBALMAXSUM] = a[left];
            }
            return maxSumValues;
        }

        int center = (left + right) / 2;    // Split sequence in half
        int[] maxLeftSum = maxSumRec(a, left, center, maxSumValues); // Find max of left half
        int[] maxRightSum = maxSumRec(a, center + 1, right, maxLeftSum); // Find max of right half

        // Find max of left half that includes the last element of the left half
        int maxLeftBorderSum = 0;
        int leftBorderSum = 0;
        leftStartIndex = center;
        for(int i = center; i >= left; i--)
        {
            leftBorderSum += a[i];
            if(leftBorderSum > maxLeftBorderSum)
            {
                maxLeftBorderSum = leftBorderSum;
                leftStartIndex = i;
            }
        }
        // Edge case for if only one element in entire sequence is positive
        if(a[leftStartIndex] < 0)
        {
            leftStartIndex = center + 1;
        }

        // Find max of right half that includes the first element on the right half
        int maxRightBorderSum = 0;
        int rightBorderSum = 0;
        rightEndIndex = center + 1;
        for(int i = center + 1; i <= right; i++)
        {
            rightBorderSum += a[i];
            if(rightBorderSum > maxRightBorderSum)
            {
                maxRightBorderSum = rightBorderSum;
                rightEndIndex = i;
            }
        }
        // Edge case for if only one element in entire sequence is positive
        if(a[rightEndIndex] < 0)
        {
            rightEndIndex = center;
        }

        // Get maximum of three sums
        int maxSum = Math.max(maxLeftSum[GLOBALMAXSUM], Math.max(maxRightSum[GLOBALMAXSUM],
                              maxLeftBorderSum + maxRightBorderSum));
        // Update global sum if local sum is greater
        if(maxSum > maxSumValues[GLOBALMAXSUM])
        {
            maxSumValues[GLOBALMAXSUM] = maxSum;
            // Update start and end index if sum is created from the middle sum
            if (maxLeftBorderSum + maxRightBorderSum == maxSum)
            {
                maxSumValues[STARTINDEX] = leftStartIndex;
                maxSumValues[ENDINDEX] = rightEndIndex;
            }
        }

        return maxSumValues;
    }

    /***************************************************
     *  FUNCTION: maxSubSum4                      	   *
     *  PURPOSE: Implementation of Algorithm 4 of the  *
     *           maximum continuous subsequent sum     *
     *  INPUT PARAMETERS : int[] a, holds the sequence *
     *                              of numbers         *
     *  OUTPUT: int, the maximum continuous subsequent *
     *               sum                               *
     ***************************************************/
    public int maxSubSum4(int[] a)
    {
        int maxSum = 0;
        int thisSum = 0;
        int startIndex = 0;
        int possibleStartIndex = 0;
        int endIndex = 0;
        long startTime = System.nanoTime();

        // Run through every number in sequence
        for(int j = 0; j < a.length; j++)
        {
            thisSum += a[j];

            // If local sum is greater than global sum, update global sum, start index, and end index
            if(thisSum > maxSum)
            {
                endIndex = j;
                startIndex = possibleStartIndex;
                maxSum = thisSum;
            }
            else if(thisSum < 0)    // If local sum < 0, reset local sum, and store index for the possible start
            {
                possibleStartIndex = j + 1;
                thisSum = 0;
            }
        }
        long timeElapsed = System.nanoTime() - startTime;

        System.out.printf("Max Sum: %d, S_index: %d, E_index: %d\n", maxSum, startIndex, endIndex);
        System.out.printf("Execution Time: %d nanoseconds\n", timeElapsed);

        return maxSum;
    }
}
