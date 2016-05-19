package com.hubble.algorithm.search;
/**
 * 用平衡二叉搜索树实现符号表
 * @author hubble
 *
 * @param <Key>
 * @param <Value>
 */
public class BanlanceBST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
	private Node root = null;
	protected class Node{
		private Node mParent = null;
		private Node mLeft = null;
		private Node mRight = null;
		private int depth = 0;
		private int size = 0;
		private Key key;
		private Value value;
		
		public Node(Key key, Value value){
			
			this.key = key;
			this.value = value;
			this.depth = 0;
			size = 1;
		}
		
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int size() {
		return root == null ? 0 :root.size;
	}

	@Override
	public boolean contains(Key key) {
		return get(key) != null;
	}

	
	@Override
	public Value get(Key key) {
		return get(root, key);
	
	}
	
	/**
	 * 比较key值，递归的查找节点树； 如果节点树为null 或者 命中目标， 递归结束，返回相应值。
	 * @param x
	 * @param key
	 * @return key 对应的 value
	 */
	private Value get(Node x, Key key) {
		if(x == null) {
			return null;
		}
		
		if(key.compareTo(x.mLeft.key) > 0) {
			return get(x.mRight, key);
		}else if(key.compareTo(x.mRight.key) < 0) {
			return get(x.mLeft, key);
		}else {
			return x.value;
		}
	}

	
	@Override
	public void put(Key key, Value value) {
		if(root == null) {
			root = new Node(key, value);
			root.depth = 0;
			return;
		}
		
		if(root.key.compareTo(key) > 0) {// left
			root = putLeft(root, key, value);
		}
		
		else if(root.key.compareTo(key) < 0) {
			root = putRight(root, key, value);
		}
		
		else {
			root.value = value;
		}
		
	}
	
//	/**
//	 *如果节点为空 , 将一个新的节点插入该节点，递归结束， 重新平衡； 如果 节点的key值 等于  目标key值， 更新该节点， 递归结束；  递归的 将目标节点 插入 当前节点的左节点或右节点
//	 * @param x
//	 * @param key
//	 * @param value
//	 * @return 返回根节点
//	 */
//	private Node put(Node parent, Node x, Key key, Value value) {
//		if(x == null) {
//			x = new Node(key, value);
//			x.depth = parent == null ? 0 : parent.depth + 1;		
//			return x;
//		}
//		
//		if(x.key.compareTo(key) > 0) {// left
//			x.mLeft = put(x, x.mLeft, key, value);
//		}else if(x.key.compareTo(key) < 0) {// right
//			x.mRight = put(x, x.mRight, key, value);
//		}else {
//			x.value = value;
//		}
//		
//		return x;
//	}
	
	private Node putLeft(Node parent, Key key, Value value) {
		if(parent.mLeft == null) {
			parent.mLeft = new Node(key, value);
			parent.mLeft.mParent = parent;
			parent.mLeft.depth = parent.depth + 1;
			return banlance(parent);
		
		}
		
		if(parent.mLeft.key.compareTo(key) > 0) {// left
			parent.mLeft = putLeft(parent.mLeft, key, value);
		}else if(parent.mLeft.key.compareTo(key) < 0) {// right
			parent.mLeft = putRight(parent.mLeft, key, value);
		}else {
			parent.mLeft.value = value;
		}
		return parent;
	}
	
	private Node putRight(Node parent, Key key, Value value) {
		if(parent.mRight == null) {
			parent.mRight= new Node(key, value);
			parent.mRight.depth = parent.depth + 1;
			parent.mRight.mParent = parent;
			return banlance(parent.mParent);

		}
		
		if(parent.mRight.key.compareTo(key) > 0) {// left
			parent.mRight = putLeft(parent.mRight, key, value);
		}else if(parent.mRight.key.compareTo(key) < 0) {// right
			parent.mRight = putRight(parent.mRight, key, value);
		}else {
			parent.mRight.value = value;
		}
		return parent;
		
	}
	
	private Node banlance(Node x) {
		// 右旋
		if(banlanceFactor(x) >= 2 && banlanceFactor(x.mLeft) >= 1) {
		return rotateRight(x);
		}
		
		// 左旋
		if(banlanceFactor(x) <= -2 && banlanceFactor(x.mRight) <= -1) {
			return rotateLeft(x);
		}
		
		// 先左旋再右旋
		if(banlanceFactor(x) >= 2 && banlanceFactor(x.mLeft) <= -1) {
			x.mLeft = rotateLeft(x.mLeft);
			return rotateRight(x);
		}
		
		// 先右旋再左旋
		if(banlanceFactor(x) <= -2 && banlanceFactor(x.mRight) >= 1) {
			x.mRight = rotateRight(x.mRight);
			return rotateLeft(x);
		}
		
		return x;
		
	}
	
	private int depth(Node parent, Node x) {
		if(x == null) {
			return parent.depth - 1;
		}
		return x.depth;
	}
	
	private int banlanceFactor(Node x) {
		if(x == null) {
			return 0;
		}
		return depth(x, x.mLeft) - depth(x, x.mRight);
	}
	
	
	// depth(x.right) > depth(x.left) + 1
	/**
	 * 对一个节点进行左旋
	 * @param x 需要旋转的节点
	 * @return 旋转后的新的根节点节点
	 */
	private Node rotateLeft(Node x) {
		Node y = x.mRight;
		x.mRight = y.mLeft;
		y.mLeft = x;
		return y;
	}
	
	
	// depth(x.left > depth(x.right) + 1)
	/**
	 * 对一个节点进行右旋
	 * @param x 需要旋转的节点
	 * @return 旋转后的新的根节点节点
	 */
	private Node rotateRight(Node x) {
		Node y = x.mLeft;
		x.mLeft = y.mRight;
		y.mRight = x;
		return y;
	}
	

	@Override
	public void delete(Key key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMin() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 递归的 计算每个节点， 平衡因子 小于等于 1， 并且 符合 二叉搜索树性质
	 * @param x
	 * @return
	 */
	public boolean aserrtBanlanceBST(Node x) {
		if(x == null) {
			return true;
		}
		boolean b = aserrtBanlanceBST(x.mLeft) && aserrtBanlanceBST(x.mRight);	
		return assertBST(x) && banlanceFactor(x) <=1 && b;
	}
	
	private boolean assertBST(Node x) {
		boolean lessLeft = true;
		boolean largeRight = true;
		if(x.mLeft != null) {
			if(x.mLeft.key.compareTo(x.key) >= 0) {
				lessLeft =false;
			}
		}
		
		if(x.mRight != null) {
			if(x.mRight.key.compareTo(x.key) < 0) {
				largeRight = false;
			}
		}
		
		return lessLeft && largeRight;
	}
	
	private void printTree(Node x) {
		if(x == null) {
			return;
		}
		System.out.print(x.key);
		printTree(x.mLeft);
		printTree(x.mRight);
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		BanlanceBST<Integer,String> bst = new BanlanceBST<>();
		bst.put(1, "11111");
		bst.put(2, "2222");
		bst.put(3, "3333");
		bst.put(4, "44444");
		bst.put(5, "5555");
		bst.put(6, "66666");
		bst.put(7, "77777");
		bst.put(8, "88888");
		bst.put(9, "9999");
		bst.put(10, "1010101");
		bst.put(11, "11-11-11-11-11");
		bst.put(12, "1212121212");
		bst.put(13, "13131313");
		bst.put(14, "14141414");
		bst.put(15, "15151515");
		System.out.println(bst.aserrtBanlanceBST(bst.root));
		bst.printTree(bst.root);
	}

}
