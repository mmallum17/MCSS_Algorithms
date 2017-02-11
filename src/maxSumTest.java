import java.util.Random;
import java.util.Scanner;

/**
 * Created by Marcus on 2/10/2017.
 */
public class maxSumTest
{
    public static void main(String[] args)
    {
        int size = 0;
        int randomNumbers[];
        int maxSum = 0;
        maxSum ms;
        Random random = new Random();
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter the size of the problem (N): ");
        size = input.nextInt();
        randomNumbers = new int[size];

        // Generate random numbers
        for(int i = 0; i < size; i++)
        {
            randomNumbers[i] = random.nextInt(19999) - 9999;
            if(size < 50)
            {
                System.out.printf("%d\n", randomNumbers[i]);
            }
        }

        // Create instance and call algorithms
        ms = new maxSum();
        ms.maxSubSum2(randomNumbers);
        ms.maxSubSum3(randomNumbers);
        System.out.printf("Max Value: %d", ms.maxSubSum4(randomNumbers));
    }
}
