package com.Algorithms2;
import java.util.*;
//insertion, deletion, searching time complexity O(n)
class BST<E extends Comparable<E>>{
  protected TreeNode<E> root = null;
  protected int size=0;
    public BST(E[] objects){
        for(int i=0;i<objects.length;i++){
            insert(objects[i]);
        }
    }
    public boolean search(E element) {
        //Repeat the following steps until current is null or the element matches current.element
        TreeNode<E> current = root;
        while (current != null) {
            if (element.compareTo(current.element)<0)
                current = current.left;
            else if (element.compareTo(current.element)>0)
                current = current.right;
            else return true;
        }
        return false;
    }
    public boolean insert(E element) {
        if (root == null)
            root = new TreeNode<>(element);
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (current.element.compareTo(element) > 0) {
                    parent = current;
                    current = current.left;
                } else if (current.element.compareTo(element) < 0) {
                    parent = current;
                    current = current.right;
                } else
                    return false;
            }
            if (parent.element.compareTo(element) > 0)
                parent.left = new TreeNode<>(element);
            else
                parent.right = new TreeNode<>(element);
        }return true;
    }
    public boolean delete(E element) {
        TreeNode<E> parent = null; //parent of the element
        TreeNode<E> current = root;
        while (current != null) { //to find the position of the element
            if (current.element.compareTo(element) > 0) {
                parent = current;
                current = current.left;
            } else if (current.element.compareTo(element) < 0) {
                parent = current;
                current = current.right;
            } else
                break;
        }

        if (current == null)
            return false;

        if (current.left == null) { //1-case: If the to be deleted element has no left child
            if (parent == null) {
                root = current.right;
            } else {
                if (parent.element.compareTo(element) > 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        } else { //If it has a left child, we need to have right most element of the left child (It means most closest low element)
            TreeNode<E> parentOfRightMost = current; //By default, rightMost is the left child of the current element
            TreeNode<E> rightMost = current.left;
            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }
            //replace the most right element of the left child with the to be deleted element
            current.element = rightMost.element;
            //We have to delete rightMost element(because it is current element to be deleted).
            // Hence, rightMost element has no right child, because it is itself,
            // we attache parentOfRightMost with the left child element of the rightMost
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else { //if the rightMost node is left child itself
                parentOfRightMost.left = rightMost.left;
            }
            size--;
        }
        return true;
    }
    public boolean isEmpty(){
        return size==0;
    }
    protected void inorder(TreeNode<E> root){
        if(root==null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }
    protected void postorder(TreeNode<E> root){
        if(root==null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }
    protected void preorder(TreeNode<E> root){
        if(root==null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }
    public void inorder(){
        inorder(root);
    }
    public void postorder(){
        postorder(root);
    }
    public void preorder(){
        preorder(root);
    }
    public int getSize(){
        return size;
    }
    public TreeNode<E> getRoot(){
        return root;
    }
    public ArrayList<TreeNode<E>> getPath(E e){
        ArrayList<TreeNode<E>> result = new ArrayList<>();
        TreeNode<E> current = root;
        while(current!=null){
            result.add(current);
            if(e.compareTo(current.element)<0)
                current=current.left;
            else if(e.compareTo(current.element)>0)
                current = current.right;
            else break;
        }return result;
    }
}
class TreeNode<E> {
    E element;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode(E e) {
        element = e;
    }
}
//    TreeNode<E> current = root; //position of the element to be deleted in the tree
//    TreeNode<E> parent = null;//position of the parent of the current
//        while(current!=null){
//                if(element.compareTo(current.element)>0){
//                parent = current;
//                current = current.right;
//                }else if(element.compareTo(current.element)<0){
//        parent = current;
//        current = current.left;
//        }else break;
//        }
//        if(current ==null) //if the element is not in the tree
//        return false;
//        if(current.left==null){ //1ST CASE: if the element to be deleted doesn't have a left child
//        if(parent==null)
//        root=current.right;
//        else {
//        if(current.element.compareTo(parent.element)>0)
//        parent.right = current.right;
//        else if(current.element.compareTo(parent.element)<0)
//        parent.left = current.right;
//        }
//        }
//        else { //2ND CASE:the element to be deleted has the left child
//        TreeNode<E> rightMost = current.left;
//        TreeNode<E> parentOfRightMost = current;
//        while(rightMost.right!=null){ // until it finds the successor element in th left subtree
//        parentOfRightMost = rightMost;
//        rightMost = rightMost.right;
//        }
//        current.element = rightMost.element; //replace the element in current with the one in the rightmost
//        if(parentOfRightMost.right ==rightMost)
//        parentOfRightMost.right = rightMost.left; //delete the rightmost by replacing it with its left child, because it can't contain the right child
//        else //if the current element is parentOfRightMost
//        parentOfRightMost.left = rightMost.left;
//        }
//        size--;
//        return true;
