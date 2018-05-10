import cs_1c.*;

public class FHsplayTree<E extends Comparable<? super E>>
        extends FHsearch_tree<E>
{

   @Override
   public boolean insert( E x )
   {

      return true;
   }

   @Override
   public boolean remove( E x )
   {

      return true;
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

      return null;
   }


   // this is (almost) identical with the version found in FHavlTree.java.
   // Make the trivial adjustments and you've got it.
   protected FHs_treeNode<E> rotateWithLeftChild( FHs_treeNode<E> k2 )
   {

      return null;
   }


   // this is (almost) identical with the version found in FHavlTree.java.
   // Make the trivial adjustments and you've got it.
   protected FHs_treeNode<E> rotateWithRightChild( FHs_treeNode<E> k2 )
   {

      return null;
   }


   // this is the private partner of the public find().
   @Override
   protected FHs_treeNode<E> find( FHs_treeNode<E> root, E x )
   {

      return null;
   }

}
