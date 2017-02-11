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
}
