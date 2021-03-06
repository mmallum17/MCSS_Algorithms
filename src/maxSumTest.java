/****************************************************
 *   Program Title: Tests the algorithms for the    *
 *                  maximum continuous subsequent   *
 *                  sum                             *
 *   Author:  Marcus Mallum                         *
 *   Class: CSCI3320, Spring 2017         	        *
 *   Assignment #1 		                            *
 ****************************************************/
import java.util.Random;
import java.util.Scanner;

public class maxSumTest
{
    public static void main(String[] args)
    {
        int size = 0;
        int randomNumbers[] = null;
        maxSum ms;
        Random random = new Random();
        Scanner input = new Scanner(System.in);
        boolean valid = false;

        // Keep prompting user for valid input size, if user enters invalid size
        while(!valid)
        {
            try
            {
                // Get input size
                System.out.print("Please enter the size of the problem (N): ");
                size = input.nextInt();
                // If size <= 0, it is invalid size
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
