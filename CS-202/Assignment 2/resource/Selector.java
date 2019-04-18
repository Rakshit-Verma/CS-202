package resource;

public class Selector
{
    public int arr[];
    public Sort getTechnique(SortVal type, int arr1[])
    {
        Sort result = null;
        arr = new int[arr1.length];
        for(int i = 0; i < arr1.length; i++)
            arr[i] = arr1[i];
        switch(type)
        {
            case INSERTION:
            result = new InsertionSort(arr);
            break;

            case BUBBLE:
            result = new BubbleSort(arr);
            break;
        }
        return result;
    }
}