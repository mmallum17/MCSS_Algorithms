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
        int randomNumbers[] = null;
        int maxSum = 0;
        maxSum ms;
        Random random = new Random();
        Scanner input = new Scanner(System.in);
        boolean valid = false;

        while(!valid)
        {
            try
            {
                System.out.print("Please enter the size of the problem (N): ");
                size = input.nextInt();
                if(size <= 0)
                {
                    throw new Exception();
                }
                randomNumbers = new int[size];
                valid = true;
            }
            catch(Exception e)
            {
                input.nextLine();
                System.out.println("Invalid input for the size. Try again!");
                valid = false;
            }
        }

        // Generate random numbers
        for(int i = 0; i < size; i++)
        {
            randomNumbers[i] = random.nextInt(19999) - 9999;
            if(size < 50)
            {
                System.out.printf("%d\n", randomNumbers[i]);
            }
        }
        System.out.println();

        // Create instance and call algorithms
        ms = new maxSum();
        ms.maxSubSum2(randomNumbers);
        ms.maxSubSum3(randomNumbers);
        ms.maxSubSum4(randomNumbers);
    }
}
