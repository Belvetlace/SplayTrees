import cs_1c.*;

public class FHsplayTree<E extends Comparable<? super E>>
        extends FHsearch_tree<E>
{

   @Override
   public boolean insert( E x )
   {
      int compareResult;
      FHs_treeNode mRoot = super.mRoot;

      if (mRoot == null)
      {
         mRoot = new FHs_treeNode(x,null,null);
         mSize++;
         return true;
      }

      FHs_treeNode newNode, parent;
      parent = mRoot;

      while(true)
      {
         compareResult = parent.data.compareTo(x);
         if ( compareResult > 0 )
         {
            if( parent.lftThread != null )
               parent = parent.lftChild;
            else
            {
               // place as new left child
               newNode = new FHs_treeNode(x, parent.lftChild, parent);
               parent.lftChild = newNode;
               parent.lftThread = false;
               break;
            }
         }
         else if ( compareResult < 0 )
         {
            if( !(parent.rtThread) )
               parent = parent.rtChild;
            else
            {
               // place as new right child
               newNode = new FHs_treeNode
                       (x, parent, parent.rtChild);
               parent.rtChild = newNode;
               parent.rtThread = false;
               break;
            }
         }
         else
            return false;  // duplicate
      }

      mSize++;
      return true;
   }

   @Override
   public boolean remove( E x )
   {

   }

   // returns (doesn't really show) the m_root data,
   // or null if there is nothing in the tree.
   // This is useful for debugging and proving that
   // the class really is splaying.
   public E showRoot()
   {

      return null;
   }

   //Add the following protected (or private) methods:

   // this method is analyzed in depth in the modules
   // and a detailed algorithm is given there.
   protected FHs_treeNode<E> splay( FHs_treeNode<E> root, E x )
   {
      FHs_treeNode<E> rightTree, leftTree, rightTreeMin, leftTreeMax = null;
      while(root != null){
         int compareResult = root.data.compareTo(x);
         if(compareResult > 0) {
            if (root.lftChild == null) {
               break;
            }
            compareResult = root.lftChild.data.compareTo(x);
            if (compareResult > 0) {
               FHs_treeNode foo = rotateWithLeftChild(root);
               if (foo.lftChild == null) {
                  break;
               }
               
               //add root to rightTree under rightTree's minimum node (as the left child of rightTreeMin), unless of course rightTree is null (in which case you should set rightTree to be root)
               //then update the rightTreeMin to point to the working root node

            }
         }
            //update the new working root:  set root to root's left child
         if(compareResult < 0){
               if(root.rtChild == null){
                  break;
               }
               compareResult = root.rtChild.data.compareTo(x);
               if(compareResult < 0){
                  FHs_treeNode foo = rotateWithRightChild(root);
                  if(foo.rtChild == null){
                     break;
                  }
               }
               //add root to leftTree under leftTree's maximum node (as the left child of leftTreeMax), unless of course leftTree is null (in which case you should set leftTree to be root)
              // then update the leftTreeMax to point to the working root node
              // update the new working root:  set root to root's right child
            }
            break;
      }
   }


   // this is (almost) identical with the version found in FHavlTree.java.
   // Make the trivial adjustments and you've got it.
   protected FHs_treeNode<E> rotateWithLeftChild( FHs_treeNode<E> k2 )
   {
      FHs_treeNode<E> k1 = k2.lftChild;
      k2.lftChild = k1.rtChild;
      k1.rtChild = k2;
      k2.setHeight( Math.max( heightOf(k2.lftChild),  heightOf(k2.rtChild) ) + 1 );
      k1.setHeight( Math.max( heightOf(k1.lftChild),  k2.getHeight() ) + 1 );
      return k1;
   }
   protected static int heightOf(FHs_treeNode t)
   { return t == null? -1 : t.getHeight(); }


   // this is (almost) identical with the version found in FHavlTree.java.
   // Make the trivial adjustments and you've got it.
   protected FHs_treeNode<E> rotateWithRightChild( FHs_treeNode<E> k2 )
   {
      FHs_treeNode<E> k1 = k2.rtChild;
      k2.rtChild = k1.lftChild;
      k1.lftChild = k2;
      k2.setHeight( Math.max( heightOf(k2.lftChild),  heightOf(k2.rtChild) ) + 1 );
      k1.setHeight( Math.max( heightOf(k1.rtChild),  k2.getHeight() ) + 1 );
      return k1;
      return null;
   }


   // this is the private partner of the public find().
   @Override
   protected FHs_treeNode<E> find( FHs_treeNode<E> root, E x )
   {

      return null;
   }

}
