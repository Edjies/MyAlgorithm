package com.hubble.algorithm.tree;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTreeNode<T> {
	
	public BinarySearchTree(T data) {
		super(data);
		
	}
	
	/**
	 * 增加一个节点 O(Lg(Height))
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
	 * 查找一个节点O(Lg(Height))
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
	
	/**
	 * 删除一个节点 O(Lg(Height))
	 * 基于一个性质： 二叉搜索树 节点左边的值都小于 节点值， 节点右边的值都大于 节点的值。 </br>
	 * 所以，若要删除一个节点， 将节点右边的最小值节点替换掉当前节点（这样并不会改变二叉搜索树的性质），并且 再删除掉右边最小值的节点即可。
	 * @param t
	 * @return
	 */
	public  BinarySearchTree<T> remove(T t) {
		BinaryTreeNode<T> node = search(t);
		if(node == null)
				return null;
		
		
		
		return null;
	}
	
	
	

}
