package resource;

public class BubbleSort implements Sort
{
    public int arr[];
    public BubbleSort(int arr1[]) 
    { 
        arr = new int[arr1.length];
        for(int i = 0; i < arr1.length; i++)
            arr[i] = arr1[i];
    }
        
    @Override
    public int[] Technique() 
    {
        int n = arr.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (arr[j] > arr[j+1]) 
                { 
                    // swap arr[j+1] and arr[i] 
                    int temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
        return arr;
    }
  
}