import java.util.*;
import resource.*;

class Assignment2
{
    void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 0; i < n; ++i) 
            System.out.print(arr[i] + " "); 
  
        System.out.println(); 
    }


    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Please Specify The Number Of Elements You Want To Input\n-->");
        int n = input.nextInt();
        System.out.println("Please provide the elements");
        int []arr1 = new int[n];
        for(int i = 0; i < n; i++)
            arr1[i] = input.nextInt();
        System.out.println("Please Select The Sorting Method :");
        System.out.println("1.Insertion Sort");
        System.out.print("2.Bubble Sort\n-->");
        Assignment2 element = new Assignment2();
        Selector object = new Selector();
        int choice = input.nextInt();
        if(choice == 1)
        {
            /*
            **The first choice would result in passing the array
            * insertion sort
            */
            Sort case1 = object.getTechnique(SortVal.INSERTION, arr1);
            int[] ans = case1.Technique();
            element.printArray(ans);
        }
        else if(choice == 2)
        {
            /*
            **The first choice would result in passing the array
            * bubble sort
            */
            Sort case1 = object.getTechnique(SortVal.BUBBLE, arr1);
            int[] ans = case1.Technique();
            element.printArray(ans);
        }
        else
            System.out.println("Wrong Input");
        input.close();
    }
}