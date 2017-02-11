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

        for(int i = 0; i < a.length; i++)
        {
            int thisSum =0;
            for(int j = i; j < a.length; j++)
            {
                thisSum +=a[j];

                if(thisSum < maxSum)
                {
                    maxSum = thisSum;
                }
            }
        }

        return maxSum;
    }
}
