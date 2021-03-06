// CIS 1C Assignment #5
// Instructor Solution Client

import cs_1c.*;

class PrintObject<E> implements Traverser<E>
{
   public void visit(E x)
   {
      System.out.print( x + " ");
   }
};

//------------------------------------------------------
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int k;
      FHsplayTree<Integer> searchTree = new FHsplayTree<Integer>();
      PrintObject<Integer> intPrinter = new PrintObject<Integer>();

      searchTree.traverse(intPrinter);

      System.out.println( "Initial size: " + searchTree.size() );
      for (k = -1; k <= 4; k++)
         searchTree.insert(k);
      System.out.println( "New size: " + searchTree.size() );

      System.out.println( "Traversal:");
      printInfo(searchTree);

      searchTree.insert(10);
      System.out.println( "Traversal after insert of 10");
      printInfo(searchTree);

      searchTree.insert(9);
      System.out.println( "Traversal after insert of 9");
      printInfo(searchTree);

      searchTree.remove(10);
      System.out.println( "Traversal after remove of 10");
      printInfo(searchTree);

      for (k = -1; k < 10; k++)
      {
         try
         {
            System.out.println( "\nfound " + searchTree.find(k));
         }
         catch( Exception e )
         {
            System.out.println( "\n oops " + e.getMessage() +" " + e.toString());
         }


      System.out.println( "\nTraversal after find");
      printInfo(searchTree);

      System.out.println( "splay " + k + " --> root: "
              + searchTree.showRoot()
              + " height: " + searchTree.showHeight() );
      }
   }

   public static void printInfo(FHsplayTree<Integer> searchTree)
   {
      PrintObject<Integer> intPrinter = new PrintObject<Integer>();
      searchTree.traverse(intPrinter);
      System.out.println();
      System.out.println("root: " + searchTree.showRoot()
              + " height: " + searchTree.showHeight() + "\n");
   }
}