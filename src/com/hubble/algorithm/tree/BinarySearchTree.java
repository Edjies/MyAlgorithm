package com.hubble.algorithm.tree;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTreeNode<T> {
	
	public BinarySearchTree(T data) {
		super(data);
		
	}
	
	/**
<<<<<<< HEAD
	 * 增加一个节点 O(Lg(Height))
=======
	 * ����һ���ڵ㡣 O(Lg(Height))
>>>>>>> 6f920c57678c991f10f48b0553c13b2904779570
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
<<<<<<< HEAD
	 * 查找一个节点O(Lg(Height))
=======
	 * �����ڵ� ��O(Lg(Height))��
>>>>>>> 6f920c57678c991f10f48b0553c13b2904779570
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
