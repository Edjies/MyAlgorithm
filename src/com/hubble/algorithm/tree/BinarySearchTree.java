package com.hubble.algorithm.tree;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTreeNode<T> {
	
	public BinarySearchTree(T data) {
		super(data);
		
	}
	
	/**
	 * 增加一个节点。 O(Lg(Height))
	 * @param newNode
	 * @return
	 */
	public boolean add(BinarySearchTree<T> newNode) {
		// parent == null 
		if(mParent == null) {
			mParent = newNode;
			mSize++;
			return true;
		}
		
		BinaryTreeNode<T> node = mParent;
		while(node != null) {
			if(newNode.mData.compareTo(node.mData) <= 0) {
				if(node.mLeft == null) {
					node.mLeft = newNode;
					newNode.mParent = node;
					mSize++;
					return true;
				}
				node = node.mLeft;
			}else {
				if(node.mRight == null) {
					node.mRight = newNode;
					newNode.mParent = node;
					mSize++;
					return true;
				}
				node = node.mRight;
			}
		}
		
		return false;
	}
	
	/**
	 * 搜索节点 （O(Lg(Height))）
	 * @param node
	 */
	public BinarySearchTree<T> search(T t) {
		BinaryTreeNode<T> node = mParent;
		while(node != null) {
			if(node.mData.compareTo(t) == 0) {
				return (BinarySearchTree<T>)node;
			}
			
			else if(node.mData.compareTo(t) < 0) {
				node = node.mLeft;
			}
			
			else if(node.mData.compareTo(t) > 0) {
				node = node.mRight;
			}
		}
		return null;
	}

}
