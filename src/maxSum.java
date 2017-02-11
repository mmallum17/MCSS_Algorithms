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
        int start_index = 0;
        int end_index = 0;
        long start_time = System.nanoTime();

        for(int i = 0; i < a.length; i++)
        {
            int thisSum = 0;
            for(int j = i; j < a.length; j++)
            {
                thisSum += a[j];

                if(thisSum > maxSum)
                {
                    maxSum = thisSum;
                    start_index = i;
                    end_index = j;
                }
            }
        }
        long timeElapsed = System.nanoTime() - start_time;

        System.out.printf("Max Sum: %d, S_index: %d, E_index: %d\n", maxSum, start_index, end_index);
        System.out.printf("Execution Time: %d nanoseconds\n", timeElapsed);
        return maxSum;
    }

    public int maxSubSum3(int[] a)
    {
        return maxSumRec(a, 0, a.length - 1);
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
}
