/**
 * Created by Marcus on 2/10/2017.
 */
public class maxSum
{
    public maxSum()
    {
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
        System.out.printf("Execution Time: %d nanoseconds\n", timeElapsed);
        return maxSum;
    }

    public int maxSubSum3(int[] a)
    {
        long startTime = System.nanoTime();
        int maxSum = maxSumRec(a, 0, a.length - 1);
        long timeElapsed = System.nanoTime() - startTime;
        System.out.printf("Max Sum: %d\n", maxSum);
        System.out.printf("Execution Time: %d nanoseconds\n", timeElapsed);
        return maxSum;
    }

    private int maxSumRec(int[] a, int left, int right)
    {
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
        for(int i = center; i >= left; i--)
        {
            leftBorderSum += a[i];
            if(leftBorderSum > maxLeftBorderSum)
            {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        int maxRightBorderSum = 0;
        int rightBorderSum = 0;
        for(int i = center +1; i <= right; i++)
        {
            rightBorderSum += a[i];
            if(rightBorderSum > maxRightBorderSum)
            {
                maxRightBorderSum = rightBorderSum;
            }
        }

        return Math.max(maxLeftSum, Math.max(maxRightSum, maxLeftBorderSum + maxRightBorderSum));
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
