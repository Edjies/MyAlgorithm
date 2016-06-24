package com.hubble.algorithm.graph;

import java.util.Stack;

/**
 * 有向环：</br>
 * 若一个有向图中存在一条 起点 和 终点相同的有向路径，则 该图为一个有向有环图。</br>
 * 有向环的探测：</br>
 * 遍历有向图的所有连通分量， 采用深度优先搜索，在递归前将节点入栈， 递归时，若发现一个 节点v 的邻接节点w 已在栈中，则表明存在一条有向环 w -->...v-->w， 搜索结束，保存有向环路径， 否则，在递归结束后，将节点出栈。</br>
 * 
 *  
 * @author hubble
 *
 */
public class DirectedCicle extends DirectedGraph {
   // 用于标记遍历节点
	private boolean[] marked;
	
	// 如果存在环， 则 用于保存 环的路径信息
	private Stack<Integer> ciclePath;
	
	// 用于 标记环
	private Stack<Integer> onCircle;
	
	public DirectedCicle(int[] graph) {
		super(graph);
		marked = new boolean[V];
		onCircle = new Stack<Integer>();
		
		

	}
	

	
	

}
