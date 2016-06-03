package com.hubble.algorithm.graph;

import java.util.Stack;

/**
 * 有向无环图的拓扑排序</br>
 * 拓扑排序：</br>
 * 给定一副有向图， 对所有的顶点进行排序， 使得所有的有向边均从排在前面的元素指向排在后面的元素</br>
 *排序思想：</br>
 *对有向图的所有连通分量 进行深度优先搜索， 在递归调用的返回阶段，将该顶点入栈， （在返回阶段，表明栈外已没有该节点指向的节点）</br>
 *拓扑排序的前提是 图是一个有向无环图</br>
 *拓扑排序的顺序不唯一</br>
 *</br>
 *递归：</br>
 *递归需要考虑三个阶段： 第一个是函数的递归调用前的阶段， 第二个是函数的递归调用阶段， 第三个是函数的递归返回阶段。</br>
 *递归函数 {    						</br>
 *   递归前处理；				</br>
 *   递归；								</br>
 *   递归后处理；				</br>
 *}											</br>
 * @author hubble
 *
 */
public class TopologicSort {
	/**顶点遍历标记*/
	private boolean [] marked;
	
	private Stack<Integer> order;
	
	private DirectedGraph graph;
	
	public TopologicSort(DirectedGraph graph) {
		this.graph = graph;
		marked = new boolean[graph.V()];
		order = new Stack<>();
	}
	
	/**
	 * 拓扑排序</br>
	 * 对有向图的所有连通分量 进行深度优先搜索， 在递归调用的返回阶段，将该顶点入栈</br>
	 * 时间复杂度: V + E</br>
	 * @return
	 */
	public Stack<Integer> topoSort() {
		for(int i = 0; i < graph.V(); i++) {
			if(!marked[i]) {
				dfs(graph, i);
			}
		}
		return order;
	}
	
	private void dfs(DirectedGraph graph, int v) {
		marked[v] = true;
		for(int w : graph.adj(v)) {
			if(!marked[w]) {
				dfs(graph, w);
			}
		}
	   order.push(v);
	}
	
	// 测试
	public static void main(String[] args) {
		int[] graph = {9,  11, 7,3,  7,6,  3,6,  8,6,  4,0,     4,1,  4,2,  4,5, 6,1,  6,5,  1,2};
		DirectedGraph dg = new DirectedGraph(graph);
		TopologicSort  ts = new TopologicSort(dg);
		Stack<Integer> order = ts.topoSort();
		while(!order.isEmpty()) {
			System.out.print(order.pop() + "   ");
		}
	}
	
}
