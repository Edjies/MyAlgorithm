package com.hubble.algorithm.tree;

public class TreeNode<T> {
	private TreeNode mNextSlibing = null;
	private TreeNode mParent = null;
	private TreeNode mLeftChild = null;
	
	private T mData;
	
	public TreeNode(T data) {
		this.mData = data;
		
	}
}
