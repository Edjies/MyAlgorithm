package com.hubble.algorithm.graph;
/**
 * 深度优先搜索算法</br>
 * 算法思想：</br>
 * &nbsp;&nbsp;标记每一个访问过的点，然后递归的遍历一个顶点的没有访问过得邻接点</br>
 * 	
 * @author hubble
 *
 */
public class DepthFirstSearch {
	private boolean[] marked;
	private Graph graph;
	private int s;
	private int count = 0;
	
	public DepthFirstSearch(Graph graph, int s) {
		this.graph = graph;
		this.s = s;
		marked = new boolean[graph.V()];
	}
	
	/**
	 * 标记每一个访问过的顶点，然后递归的遍历该顶点的没有访问过得邻接点</br>
	 * 时间复杂度： O(N),  每个点只会被访问一次  </br>
	 * @param graph
	 * @param v
	 */
	public void depthSearch(Graph graph, int v) {
			marked[v] = true;
			count++;
			System.out.print(v + ",");
	
		for(Integer w : graph.adj(v)) {
			if(!marked[w]) {
				depthSearch(graph, w);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] graph = {5,5,  0,3,  1,2,  2,3,  3,4,  0,4};
		SimpleGraph sg = new SimpleGraph(graph);
		DepthFirstSearch dfs = new DepthFirstSearch(sg, 0);
		dfs.depthSearch(dfs.graph, 0);
		
	}
}
