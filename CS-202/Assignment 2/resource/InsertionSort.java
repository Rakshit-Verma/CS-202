package resource;

public class InsertionSort implements Sort
{
    public int []arr;
	public InsertionSort(int arr1[]) 
    {
        arr = new int[arr1.length];
        for(int i = 0; i < arr1.length; i++)
            arr[i] = arr1[i];
    }
    
    @Override
    public int[] Technique() 
    {
        int n = arr.length; 
        for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            int j = i - 1; 
    
            /* Move elements of arr[0..i-1], that are 
                greater than key, to one position ahead 
                of their current position */
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        }
        return arr;
    }
}