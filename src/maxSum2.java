/**
 * Created by Marcus on 2/10/2017.
 */
public class maxSum2
{
    private int startIndex;
    private int endIndex;
    private int maxSum;
    public maxSum2()
    {
        startIndex = 0;
        endIndex = 0;
        maxSum = 0;
    }

    public int maxSubSum2(int[] a)
    {
        int maxSum = 0;
        int startIndex = 0;
        int endIndex = 0;
        long startTime = System.nanoTime();

        for(int i = 0; i < a.length; i++)
        {
            int thisSum = 0;
            for(int j = i; j < a.length; j++)
            {
                thisSum += a[j];

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

    public int maxSubSum3(int[] a)
    {
        long startTime = System.nanoTime();
        int maxSum = maxSumRec(a, 0, a.length - 1);
        long timeElapsed = System.nanoTime() - startTime;
        System.out.printf("Max Sum: %d, S_index: %d, E_index: %d\n", maxSum, startIndex, endIndex);
        System.out.printf("Execution Time: %d nanoseconds\n", timeElapsed);
        return maxSum;
    }

    private int maxSumRec(int[] a, int left, int right)
    {
        int leftStartIndex;
        int rightEndIndex;
        if(left == right)
        {
            if(a[left] > 0)
            {
                return a[left];
            }
            else
            {
                return 0;
            }
        }

        int center = (left + right) / 2;
        int maxLeftSum = maxSumRec(a, left, center);
        int maxRightSum = maxSumRec(a, center + 1, right);

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
        if(a[leftStartIndex] < 0)
        {
            leftStartIndex = center + 1;
        }

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
        if(a[rightEndIndex] < 0)
        {
            rightEndIndex = center;
        }

        int maxSum = Math.max(maxLeftSum, Math.max(maxRightSum, maxLeftBorderSum + maxRightBorderSum));
        if(maxSum > this.maxSum)
        {
            this.maxSum = maxSum;
            if (maxLeftBorderSum + maxRightBorderSum == maxSum)
            {
                    startIndex = leftStartIndex;
                    endIndex = rightEndIndex;
            }
        }

        return maxSum;
    }

    public int maxSubSum4(int[] a)
    {
        int maxSum = 0;
        int thisSum = 0;
        int startIndex = 0;
        int possibleStartIndex = 0;
        int endIndex = 0;
        long startTime = System.nanoTime();

        for(int j = 0; j < a.length; j++)
        {
            thisSum += a[j];

            if(thisSum > maxSum)
            {
                endIndex = j;
                startIndex = possibleStartIndex;
                maxSum = thisSum;
            }
            else if(thisSum < 0)
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

