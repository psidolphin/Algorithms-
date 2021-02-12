/* 
Program 3: Jared Ren, Noel Guderjahn.
*/
/*
For the cutoff, you're not using the cutoff in recursive calls, and you need to modify insertion sort to have left, right parameters so that it only works on part of the array.  (-2)  The testing code needs a loop for different cutoffs (-2), and you want 8 million, not 80.  And dataArray is not a good name for the data array.

*/

import java.util.Random;
public class QSort {
  
  public static void insertionSort(int[] items, int left, int right) {
      for (int i = left; i < right; i++) {
         int key = items[i];
         int j = i - 1;
         while (j >= 0 && key < items[j]) {
            items[j+1] = items[j];
            j--;
         }
         items[j + 1] = key;
      }
   }
  
    private static boolean isSorted(int[] items) {
      for (int i = 0; i < items.length - 1; i++) {
         if (items[i] > items[i+1]) return false;
      }
      return true;
   }
  
   
   public static void swap(int[] arr,int indexA, int indexB) {
      int tmp = arr[indexA];
      arr[indexA] = arr[indexB];
      arr[indexB] = tmp;
   }
   
   
    private static int partition(int[] s, int low, int high) {
      int p = low;
      for(int i = low; i < high; i++) {
         if(s[i] < s[high]) {
            swap(s, i, p);
            p++;
         }
      }
      swap(s,p,high);
      return p;
   }


   public static void quickSort(int[] a) {
         quickSort(a, 0, a.length-1);
}

//called inside determineCutoff
   public static void quickSort(int[] a, int cutoff) {
   if(a.length - 1 < cutoff) {
   int left = 0;
   int right = 8;
      insertionSort(a,left,right);
   } else {
      quickSort(a);
   }
   // int high ;
//    int low;
//       quickSort(a, 0, a.length-1, cutoff);
//         if(high-low  <= cutoff) {
//                insertionSort(a,0,0);
//    }else
//          if(low < high) {
//          int partitionPoint = partition(a, low, high);
//          quickSort(a, low, partitionPoint - 1);
//          quickSort(a, partitionPoint + 1, high);
//       }
   
   }
  
  
   private static void quickSort(int[] a, int low, int high) {
      if(low < high) {
         int partitionPoint = partition(a, low, high);
         quickSort(a, low, partitionPoint - 1);
         quickSort(a, partitionPoint + 1, high);
      }
   }
  //  private static void quickSort(int[] a, int low, int high, int cutoff) {
//    if(high-low  <= cutoff){
//       insertionSort(a, 0, a.length);
//    }else{
//       if(low < high) {
//          int partitionPoint = partition(a, low, high);
//          quickSort(a, low, partitionPoint - 1);
//          quickSort(a, partitionPoint + 1, high);
//       }
//      }
//    }   

  
   private static void fillRandom(int[] items, int max) {
      Random gen = new Random();
      for (int i = 0; i < items.length; i++) {
         items[i] = gen.nextInt(max);
      }
   }
   
   public static void determineBigO() {
      final int TRIALS = 10;
      final int MAX = Integer.MAX_VALUE;
      for (int size = 10000; size <= 40960000; size *= 2) {
         int[] a = new int[size];
         long time = 0;
         for (int trial = 0; trial < TRIALS; trial++) {
            time = 0;
            fillRandom(a, MAX);
            long start = System.currentTimeMillis();
            quickSort(a);
            time += System.currentTimeMillis() - start;
            if (!isSorted(a)) System.out.println("Sort failed!");
         }
         System.out.println("Size: " + size + "\tAvg time: " + time/TRIALS);
   }
   }



   public static void determineCutoff() {
   //array size stays the same
      int[] dataArray = new int[8000000];
      final int MAX = Integer.MAX_VALUE;
      int partitionPoint = partition(dataArray, 0, dataArray.length-1);
      //ten trials for each cutoff, so have cutoff starting from 0 to 100
      for(int cutoff = 0; cutoff < 200; cutoff += 10) {
         long time = 0;
         for(int trials = 1; trials < 11; trials++) {
            fillRandom(dataArray, MAX);
            long start = System.currentTimeMillis();
            quickSort(dataArray, cutoff);
            time += System.currentTimeMillis() - start;
            if (!isSorted(dataArray)) System.out.println("Sort failed!");
            if(isSorted(dataArray)) System.out.println("sort success on cutoff " + cutoff);
            
         }
         System.out.println("Size: " + 8000000 + "\tAvg time: " + time/10);
      }
      }
      // /*quickSort(dataArray, 0, partitionPoint - 1);
//       quickSort(dataArray, partitionPoint + 1, dataArray.length-1);*/
//       for (int trial = 0; trial < TRIALS; trial++) {
//             fillRandom(dataArray, MAX);
//             long start = System.currentTimeMillis();
//             quickSort(dataArray, 0);
//             time += System.currentTimeMillis() - start;
//             cutoff += 10;
//             if (!isSorted(dataArray)) System.out.println("Sort failed!");
//          }
//          System.out.println("Size: " + 8 + "\tAvg time: " + time/TRIALS);

   
   public static void main(String[] args) {
      determineCutoff();
      determineBigO();
   }

}