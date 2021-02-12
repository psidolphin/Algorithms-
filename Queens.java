/*
   Noel Guderjahn and Jared Ren
*/

public class Queens {
   private static int[] array;
   private static int numSolutions;
   private static int[][] board;
   private static int[][] cant;
   private static int last = 0;
   private static int done = 0;
   
   public Queens(int n) {
      array = new int[n];
      this.numSolutions = numSolutions;
      board = new int[n][n];
      cant = new int[n][n];
   }
   
   public static boolean isCandidate(int j, int i) {

     if(cant[i][j] == 1)
         return false;
      for(int x = 0; x < board.length; x++) {
      if(board[i][x] == 1) {
         return false;
      } 
      if(board[x][j] == 1) {
         return false;
      }
      if(x-i+j > -1 && x-i+j < board.length)
         if(board[x][x-i+j] == 1){
            
            return false;
         }//  \
      if(board.length-1-x > -1 && board.length-1-x < board.length)
         if(x-(board.length-1)+i+j > -1 && x-(board.length-1)+i+j < board.length)
            if(board[board.length-1-x][x-(board.length-1)+i+j] == 1){
               return false;
            }//  /
      }
     return true;
   } 
   
   public static void display() {
      for(int i : array) {
         System.out.print(i + " ");
      }
      System.out.println();
      numSolutions ++;
   }
   

   public static int find(int k){
      for(int i = 0; i < board.length; i++)
      {
         if(board[i][k] == 1 )
         return i;
      }
      return -111;
   }
   
   public static void backtrack(int k) {
   if(done == 0){
      //System.out.println("K: " + k );
       for (int i = 0; i < array.length; i++) {
            if (isCandidate(k, i)) {
               array[k] = i;
               board[i][k] = 1;
               if (k == array.length - 1) {
                  display();
                  last = find(array.length-1);
                  board[last][array.length-1] = 0;
               } else {
                  backtrack(k + 1);
               }
            }
         }
         if(k != 0 && done == 0){
            last = find(k-1);
            cant[last][k-1] = 1;    // say NO to drugs
            clear(k);
            board[last][k-1] = 0;
            backtrack(k-1);     //then retry that last spot
         }
         if(k == 0 && done == 0)
         {
            System.out.println("Solutions count: " + numSolutions);
            done = 1;
            //we done at this(point);
         }     
        
         //then retry that last spot
      }
   }
   
 
   public static void clear(int j){
      for(int i = j; i < array.length; i++)
         for(int k = 0; k < array.length; k++)
            cant[k][i] = 0;
   }
   
   public static void main(String[] args) {
   
      Queens q = new Queens(8);
      
     /* q.array[0]  = 3;
      q.array[1]  = 1;
      q.array[2]  = 4;
      q.array[3]  = 8;
      q.display();*/
            //board[0][0] = 1;
      //boolean done = false;
      q.backtrack(0);
   }
}
