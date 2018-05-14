import cs_1c.*;

public class FHsplayTree<E extends Comparable<? super E>>
        extends FHsearch_tree<E>
{

   // we should splay for x first, then it goes into the new root
   @Override
   public boolean insert(E x)
   {
      FHs_treeNode<E> root;
      if(super.mRoot == null)
      {
         super.mRoot = new FHs_treeNode<E>(x, null, null);
      }
      else {  // otherwise, splay for x.
         root = splay(super.mRoot, x);
         int compareResult;
         compareResult = x.compareTo(root.data);
         if (compareResult < 0) //if x < root
         {
            super.mRoot = new FHs_treeNode<E>(x, root.lftChild, root);
            //create a new node around x
            // and make its left child the splayed root's left child
            // and its right child the splayed root.
            //set the tree root to be the new node.
            //return true.
         }
         else if (compareResult > 0) //otherwise, if x > root
         {
            super.mRoot = new FHs_treeNode<E>(x, root, root.rtChild);
            //create a new node around x
            // and make its left child the splayed root
            // and its right child the splayed root's right child.
            //set the tree root to be the new node.
            //return true.
         } else { return false; } // otherwise, x is already in the tree; return false.
      }
      return true;
   }

   // we should splay for x first, then remove it. why should we do this???
   @Override
   public boolean remove(E x)
   {
      if(super.mRoot == null){
         return false;
      }
      else{
         super.mRoot = splay(super.mRoot, x);  // should capture the returned value
      }
      return true;
   }

   // returns (doesn't really show) the m_root data,
   // or null if there is nothing in the tree.
   // This is useful for debugging and proving that
   // the class really is splaying.
   public E showRoot()
   {
      if(super.mRoot == null){
         return null;
      }
      return super.mRoot.data;
   }

   //Add the following protected (or private) methods:

   protected FHs_treeNode<E> splay(FHs_treeNode<E> root, E x)
   {
      FHs_treeNode<E> rightTree, leftTree, rightTreeMin, leftTreeMax;
      rightTree = null;
      leftTree = null;
      rightTreeMin = null;
      leftTreeMax = null;
      int compareResult;

      //loop while root != null (root should not become null, but this protects against null parameter)
      while ( root != null)
      {
         compareResult = x.compareTo(root.data);

         if (compareResult < 0) //if x < root
         {
            //check for root's left child null.  If so, x not in tree, break loop.
            if(root.lftChild == null) { break; }
            // left child is not null
            root = root.lftChild;
            compareResult = x.compareTo(root.data);
            if (compareResult < 0)
            {
               //if x < root's left child we have zig zig (left) so do a single rotate (left) at root
               root = rotateWithLeftChild(root);
               //check for (new) root's left child null.  If so, x not in tree, break loop.
               if(root.lftChild == null) { break; }
            }
            // add root to rightTree under rightTree's minimum node (as the left child of rightTreeMin),
            // unless of course rightTree is null (in which case you should set rightTree to be root)
            if (rightTree == null)
            {
               rightTree = root;
            } else {
               rightTreeMin.lftChild = root;
            }
            //then update the rightTreeMin to point to the working root node
            rightTreeMin = root.lftChild;
            //update the new working root:  set root to root's left child
            root = root.lftChild;
         }
         else if (compareResult > 0) // otherwise, if x > root
         {
            //check for root's right child null.  If so, x not in tree, break loop.
            if(root.rtChild == null) { break; }
            root = root.rtChild;
            compareResult = x.compareTo(root.data);
            //if x > root's right child we have zig zig (right) so do a single rotate (right) at root
            if (compareResult < 0){
               root = rotateWithRightChild(root);
               //check for (new) root's right child null.  If so, x not in tree, break loop.
               if(root.rtChild == null) { break; }
            }
            //add root to leftTree under leftTree's maximum node (as the left child of leftTreeMax),
            // unless of course leftTree is null (in which case you should set leftTree to be root)
            if (leftTree == null)
            {
               leftTree = root;
            } else {
               leftTreeMax.rtChild = root;  // bug in teacher's algorithm???
            }

            //then update the leftTreeMax to point to the working root node
            leftTreeMax = root.rtChild;
            //update the new working root:  set root to root's right child
            root = root.rtChild;

         }
         else { break; } //otherwise we have found x at root. break.
      }
      //reassemble
      if (leftTree != null){
         // hang root's left child onto its maximum
         leftTreeMax = root.lftChild;
         // and set root's left child = the left tree.
         root.lftChild = leftTree;
      }
      if (rightTree != null)
      {
         // hang root's right child onto its minimum
         rightTreeMin = root.rtChild;
         // and set root's right child = the right tree.
         root.rtChild = rightTree;
      }
      return root;
   }


   // this is (almost) identical with the version found in FHavlTree.java.
   // Make the trivial adjustments and you've got it.
   protected FHs_treeNode<E> rotateWithLeftChild(FHs_treeNode<E> k2)
   {
      FHs_treeNode<E> k1 = k2.lftChild;
      k2.lftChild = k1.rtChild;
      k1.rtChild = k2;
      return k1;
   }


   // this is (almost) identical with the version found in FHavlTree.java.
   // Make the trivial adjustments and you've got it.
   protected FHs_treeNode<E> rotateWithRightChild(FHs_treeNode<E> k2)
   {
      FHs_treeNode<E> k1 = k2.rtChild;
      k2.rtChild = k1.lftChild;
      k1.lftChild = k2;
      return k1;
   }

   // not required but we probably should have it
   protected FHs_treeNode<E> doubleWithLeftChild(FHs_treeNode<E> k3)
   {
      k3.lftChild = rotateWithRightChild(k3.lftChild);
      return rotateWithLeftChild(k3);
   }

   // not required but we probably should have it
   protected FHs_treeNode<E> doubleWithRightChild(FHs_treeNode<E> k3)
   {
      k3.rtChild = rotateWithLeftChild(k3.rtChild);
      return rotateWithRightChild(k3);
   }

   // this is the private partner of the public find().
   @Override
   protected FHs_treeNode<E> find(FHs_treeNode<E> root, E x)
   {
      if(root == null){
         return null;
      }
      return splay(root, x);
   }

}
