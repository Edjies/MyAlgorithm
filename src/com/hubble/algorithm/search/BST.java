package com.hubble.algorithm.search;

/**
 * 用二叉搜索树实现符号表功能</br>
 * 二叉搜索树的特点：</br>
 * &nbsp;&nbsp;1. 一个节点的左子树小于 该节点， 一个节点的右子树大于或等于该节点
 * API实现思想：</br>
 * get: 通过key 获取 value</br>
 * put: 插入或更新一个 key-value对</br>
 * delete： 删除一个key-value对</br
 * @author hubble
 *
 * @param <Key>
 * @param <Value>
 */
public class BST <Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value>{
	
	/*定义结构及属性*/
	private Node root = null;
	
	
	class Node {
		private Node mRight;
		private Node mLeft;
		private Key key;
		private Value value;
		// 以该节点作为根节点时 树的节点数量
		private int N;
		
		public Node(Key key, Value value) {
			N = 1;
			this.key = key;
			this.value = value;
			mRight = null;
			mLeft = null;
		}
	}

	/*API实现*/
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int size() {
		if(isEmpty()) {
			return 0;
		}
		
		return root.mLeft.N + root.mRight.N + 1;
	}

	@Override
	public boolean contains(Key key) {
		return get(key) != null;
	}

	@Override
	public Value get(Key key) {
		
		return get(root, key);
	}
	
	private Value get(Node node, Key key) {
		// 如果节点为空，返回空
		if(node == null) {
			return null;
		}
		
		// 如果节点Key相等， 返回Value
		if(node.key.compareTo(key) == 0) {
			return node.value;
		}
		
		// 如果节点key小于查询的key, 继续和节点的右节点比较
		if(node.key.compareTo(key) < 0) {
			return get(node.mRight, key);
		}
		
		// 如果节点key大于查询的key, 继续和节点的左节点比较
		else{
			return get(node.mLeft, key);
		}
	}

	@Override
	public void put(Key key, Value value) {
		root = put(root, key, value);
	}
	
	/**
	 * 返回根节点的节点
	 * @param x
	 * @param key
	 * @param value
	 * @return
	 */
	private Node put(Node x, Key key, Value value) {
		// 不存在key值，插入键值对
		if(x == null) {
			x = new Node(key, value);
			return x;
		}
		
		// 递归
		if(x.key.compareTo(key) > 0) {
			x.mLeft = put(x.mLeft, key, value);
		}
		
		// 递归
		else if(x.key.compareTo(key) <= 0) {
			x.mRight = put(x.mRight, key, value);
		}
		
		// 存在key值则更新
		else {
			x.value = value;
		}
		return x;
		
	}
	
	/**
	 * 
	 */
	@Override
	public void delete(Key key) {
		if(key == null) {
			throw new RuntimeException("键不应该为空");
		}
		
		root = delete(root, key);
			
	}
	
	/**
	 * 递归的删除特定节点所在的子树， 如果节点为空或者 找到节点， 递归结束， 执行删除操作</br>
	 * 删除操作：</br>
	 * 如果 目标节点的左节点为空， 返回右节点</br>
	 * 如果目标节点的右节点为空，返回左节点</br>
	 * 如果目标节点的两个子节点都不为空，则将目标节点用右节点的最小节点替换，删除右节点的最小节点，并返回替换后的节点。
	 * 
	 * @param x   删除一个节点树的某个节点
	 * @param key  要删除的节点的Key
	 * @return 返回删除节点树一个节点后的根节点
	 */
	private Node delete(Node x, Key key) {
		if(x == null) {
			return null;
		}
		
		// 递归删除右节点里的目标节点
		if(key.compareTo(x.key) > 0) {
			x.mRight = delete(x.mRight, key);
		}
		
		// 递归删除左节点里的目标节点
		else if(key.compareTo(x.key) < 0) {
			x.mLeft = delete(x.mLeft, key);
		}
		
		// 命中目标
		else {
			if(x.mLeft == null) {
				return x.mRight;
			}else if(x.mRight == null) {
				return x.mLeft;
			}else {
				Node temp = x;
				Node minRight = min(x.mRight);
				x = minRight;
				x.mLeft = temp.mLeft;
				x.mRight = deleteMin(x.mRight);
			}			
		}
		
		// 更新节点大小
		x.N = x.mLeft.N + x.mRight.N + 1;
		return x;
	}
	
	@Override
	public void deleteMin() {
		if(root == null) {
			return;
		}
		root = deleteMin(root);
	}
	
	/**
	 * 递归的删除树最左边的节点，  如果一个节点的左节点为空，则该节点为最小节点，递归结束
	 * @param root 节点树的根节点
	 * @return 一个节点树删除最小值后的根节点
	 */
	private Node deleteMin(Node root) {
		//如果一个节点的左节点为空，则该节点为最小节点，递归结束
		if(root.mLeft == null) {
			return root.mRight;
		}	
		
		// 递归的删除左节点，
		root.mLeft = deleteMin(root.mLeft);
		
		// 更新size
		root.N = root.mLeft.N + root.mRight.N + 1;
		return root;
	}
	
	/**
	 * 找到一个节点树的最小节点</br>
	 * 递归的查找一个节点的左节点， 如果某个节点的左节点为空， 递归结束， 返回当前节点
	 * @param x
	 * @return
	 */
	private Node min(Node x) {
		if(x.mLeft == null) {
			return x;
		}
		return min(x.mLeft);
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
		BST<Integer, String> bst = new BST<>();
		bst.put(9, "9999");
		bst.put(7, "77777");
		bst.put(2, "2222");
		bst.put(1, "11111");
		bst.put(4, "44444");
		bst.put(3, "3333");
		bst.put(6, "66666");
		bst.put(5, "5555");
		
		
		bst.put(8, "88888");
		
		bst.put(10, "1010101");
		bst.put(11, "11-11-11-11-11");
		bst.put(12, "1212121212");
		bst.put(13, "13131313");
		bst.put(14, "14141414");
		bst.put(15, "15151515");
		bst.printTree(bst.root);
	}
}
