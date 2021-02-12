import java.util.Random;
public class RBTree {
   private static boolean RED = true;
   private static boolean BLACK = false;
   private Node root;
   private Node nil;
   
   public void insert(RBTree t, int key) {
      if (root == nil) {
         root = new Node(key, nil, nil, nil);
      } else {
         insert(t,key, root);
      }
   }
   
   
   
   private void RBInsertFix(RBTree t, Node z){
      while(z.parent.color == RED){
         if(z.parent == z.parent.parent.left){
            Node y = z.parent.parent.right;
            if(y.color == RED){
               z.parent.color = BLACK;
               y.color = BLACK;
               z.parent.parent.color = RED;
               z = z.parent.parent;
               
            }else{
               if(z == z.parent.right){
                   z = z.parent;
                   leftRotate(t,z);
               }
               z.parent.color = BLACK;
               z.parent.parent.color = RED;
               rightRotate(t,z.parent.parent);
            }
            
         }else{
            Node y = z.parent.parent.left;
            if(y.color == RED){
               z.parent.color = BLACK;
               y.color = BLACK;
               z.parent.parent.color = RED;
               z = z.parent.parent;
            }else{
               if(z == z.parent.left){
                  z = z.parent;
                  rightRotate(t,z);
               }
               z.parent.color = BLACK;
               z.parent.parent.color = RED;
               leftRotate(t,z.parent.parent);
            }
         }
      }
      t.root.color = BLACK;
   }   
   
   
   private void insert(RBTree t, int key, Node n) {
         Node z = bstinsert(key, n);
         z.color = RED;
         RBInsertFix(t,z);
  }
  
  
  
  
      


   
   private void leftRotate(RBTree t, Node x) {
      Node y = x.right;
         x.right = y.left;
         if(y.left != nil){
            y.left.parent = x;
         }
         y.parent = x.parent;
         if(x.parent == nil){
            root = y;
         }
         else if(x == x.parent.left){
            x.parent.left = y;
         }
         else {
            x.parent.right = y;
         }
         y.left = x;
         x.parent = y;
      
      }
   
   private void rightRotate(RBTree t, Node x) {
     Node y = x.left;
    
         x.left = y.right;
         if(y.right != nil){
            y.right.parent = x;
         }
         y.parent = x.parent;
         if(y.parent == nil){
            root = y;
         }
         else if( x == x.parent.right){
            x.parent.right = y;
         }
         else{
            x.parent.left = y;
         }
         y.right = x;
         x.parent = y;
         }
         
     
     
     
   
   private Node bstinsert(int key, Node n) {
      assert n != nil;
      if (key < n.key) {
         if (n.left != nil) {
            return bstinsert(key, n.left);
         } else {
            n.left = new Node(key, n, nil, nil);
            return n.left;
         }
      } else if (key > n.key) {
         if (n.right != nil) {
            return bstinsert(key, n.right);
         } else {
            n.right = new Node(key, n, nil, nil);
            return n.right;
         }
      } else {
         return n;
      }
   }
   
   private void bstreplace(Node n, Node child) {
      assert n != nil; // but child might be nil
      Node parent = n.parent;
      if (child != nil) child.parent = parent;
      if (parent == nil) {
         root = child;
      }
      else if (parent.left == n) {
         parent.left = child;
      } else {
         parent.right = child;
      }
   }
   
   private static class Node {
      private int key;
      private Node parent, left, right;
      private boolean color;
      
      private Node(int key, Node parent, Node left, Node right) {
         this.key = key;
         this.parent = parent;
         this.left = left;
         this.right = right;
         color = BLACK;
      }
   }
   
   public RBTree() {
      nil = new Node(-1, nil, nil, nil);
      root = nil;
   }
   
   public static void main(String[] args) {
      Random rando = new Random();
      RBTree t = new RBTree();
      for (int i = 0; i < 20; i++) {
         t.insert(t,i);
      }
      RBTree rbt = new RBTree();
      for(int j = 0; j < 20; j++){
         rbt.insert(t, rando.nextInt(100));
      }
   }
   
}
