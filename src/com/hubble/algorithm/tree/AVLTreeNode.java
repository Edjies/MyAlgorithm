package com.hubble.algorithm.tree;

public class AVLTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T> {
	protected int height = 0;

	public AVLTreeNode(T data) {
		super(data);
	}
	
	public void insert() {
		
	}

	
	protected void leftRotate(BinaryTreeNode<T> root, BinaryTreeNode<T> pivot) {
		pivot.mParent = root.mParent;
		root.mParent = pivot;
		root.mLeft = pivot.mRight;
		pivot.mRight = root;	
	}
	
	protected void rightRotate(BinaryTreeNode<T> root, BinaryTreeNode<T> pivot) {
		
	}
	
	protected void leftRightRotate(BinaryTreeNode<T> root, BinaryTreeNode<T> pivot) {
		
	}
	
	protected void rightLeftRotate(BinaryTreeNode<T> root, BinaryTreeNode<T> pivot) {
		
	}
	
	protected void updateHeight(int height, AVLTreeNode<T> node) {
		
	}
	
	protected void getBanlanceFactor() {
		
	}
	
	

}
