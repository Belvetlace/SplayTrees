import cs_1c.*;

public class FHsplayTree<E extends Comparable<? super E>>
        extends FHsearch_tree<E>
{

   @Override
   public boolean insert(E x)
   {
      FHs_treeNode<E> root;
      if (super.mRoot == null)
      {
         super.mRoot = new FHs_treeNode<E>(x, null, null);
         printDebugg();
      } else
      {
         root = splay(super.mRoot, x);
         int compareResult;
         compareResult = x.compareTo(root.data);
         if (compareResult < 0)
         {
            super.mRoot = new FHs_treeNode<E>(x, root.lftChild, root);
            printDebugg();
         } else if (compareResult > 0)
         {
            super.mRoot = new FHs_treeNode<E>(x, root, root.rtChild);
            printDebugg();
         } else
         {
            return false;
         }
      }
      super.mSize++;
      return true;
   }

   @Override
   public boolean remove(E x)
   {
      FHs_treeNode<E> mRoot = super.mRoot;
      FHs_treeNode<E> splayedNode;
      if (mRoot == null)
      {
         return false;
      } else
      {
         splayedNode = splay(super.mRoot, x);
      }
      if (x != mRoot)
      {
         return false;
      }
      if (splayedNode.lftChild == null)
      {
         splayedNode = mRoot.rtChild;
      } else
      {
         splayedNode = mRoot.lftChild;
         FHs_treeNode<E> max = findMax(splayedNode);
         splayedNode = splay(splayedNode, x);
         splayedNode.rtChild = mRoot.rtChild;
      }
      super.mRoot = splayedNode;
      return true;
   }

   // returns (doesn't really show) the m_root data,
   // or null if there is nothing in the tree.
   // This is useful for debugging and proving that
   // the class really is splaying.
   public E showRoot()
   {
      if (super.mRoot == null)
      {
         return null;
      }
      return super.mRoot.data;
   }

   //Add the following protected (or private) methods:

   // this method is analyzed in depth in the modules
   // and a detailed algorithm is given there.
   protected FHs_treeNode<E> splay(FHs_treeNode<E> root, E x)
   {
      FHs_treeNode<E> rightTree = null, leftTree = null, rightTreeMin = null, leftTreeMax = null;
      FHs_treeNode<E> newRoot = null;
      while (root != null)
      {
         int compareResult = root.data.compareTo(x);
         if (compareResult > 0)
         {
            if (root.lftChild == null) { break; }
            newRoot = root;
            compareResult = root.lftChild.data.compareTo(x);
            if (compareResult > 0)
            {
               newRoot = rotateWithLeftChild(root);
               if (newRoot.lftChild == null) { break; }
            }
            if (rightTree == null)
            {
               rightTree = newRoot;
               //newRoot.lftChild = null; // cut reference to left child
            } else
            {
               rightTreeMin = newRoot;
            }
            root = root.lftChild;
            rightTreeMin = newRoot.lftChild;
            //add root to rightTree under rightTree's minimum node (as the left child of rightTreeMin),
            // unless of course rightTree is null (in which case you should set rightTree to be root)
            //then update the rightTreeMin to point to the working root node

         }
         //update the new working root:  set root to root's left child
         else if (compareResult < 0)
         {
            if (root.rtChild == null) { break; }
            compareResult = root.rtChild.data.compareTo(x);
            if (compareResult < 0)
            {
               newRoot = rotateWithRightChild(root);
               if (newRoot.rtChild == null) { break; }

            }
            if (leftTree == null)
            {
               leftTree = newRoot;
               //newRoot.rtChild = null; // cut reference to right child
            } else
            {
               leftTreeMax = newRoot;
            }
            root = root.rtChild;
            leftTreeMax.rtChild = newRoot;

            //add root to leftTree under leftTree's maximum node (as the left child of leftTreeMax), unless of course leftTree is null (in which case you should set leftTree to be root)
            // then update the leftTreeMax to point to the working root node
            // update the new working root:  set root to root's right child
         } else
         { break; }
      }
      if (leftTree != null)
      {
         leftTreeMax = root.lftChild;
         root.lftChild = leftTree;
      }
      if (rightTree != null)
      {
         rightTreeMin = root.rtChild;
         root.rtChild = rightTree;
      }
      super.mRoot = root;
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


   // this is the private partner of the public find().
   @Override
   protected FHs_treeNode<E> find(FHs_treeNode<E> root, E x)
   {
      if (root == null)
      {
         return null;
      }
      //find x, if it is there splay for it, otherwise return null
      int compareResult;
      boolean found = false;
      while (!found && root != null)
      {
         compareResult = x.compareTo(root.data);
         if (compareResult < 0)
            root = root.lftChild;
         else if (compareResult > 0)
            root = root.rtChild;
         else
            found = true;
      }
      if (!found)
         return null;
      return splay(super.mRoot, x);
   }

   public void printDebugg()
   {
      System.out.print("new m root " + super.mRoot.data);
      if (super.mRoot.lftChild != null)
         System.out.print(" lftChild: " + super.mRoot.lftChild.data);
      else
         System.out.print(" lftChild: null");
      if (super.mRoot.rtChild != null)
         System.out.print(" rtChild: " + super.mRoot.rtChild.data);
      else
         System.out.print(" rtChild: null");
      System.out.println();
   }
}
