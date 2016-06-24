package com.hubble.algorithm.graph;
import java.util.ArrayList;

import com.hubble.algorithm.sort.MinHeap;
/**
 * 最小生成树：</br>
 * 定义： 给定一个加权无向图， 找到一幅连通所有顶点的无环子图，使得子图的边的权重之和最小。</br> 
 * </br>
 * 性质：</br>
 * 在一幅加权图中， 给定任意切分，他的横切边中权重最小者必然属于图的最小生成树。</br>
 * </br>
 * Prim算法思想：</br>
 * 切分： 对正在生成的最小生成树 和  整个图 不断进行切分， 找到权重最小的横切边，若该条边 不在 最小生成树中， 将该条边加入最小生成树。
 *
 * 
 * @author hubble
 *
 */
public class LazyPrimMST {
	private boolean[] marked;
	private MinHeap<Edge> edges;
	private ArrayList<Edge> mst;
	private EdgeWeightedGraph graph;
	
	public LazyPrimMST(EdgeWeightedGraph graph) {
		marked = new boolean[graph.V()];
		edges = new MinHeap<>(graph.E());
		mst = new ArrayList<>();
		this.graph = graph;
		
		// 将起始点加入最小生成树， 访问该节点。
		visit(0);
		
		// 遍历横切边集合， 取出最小横切边， 若 最小横切边连接的有一个顶点不属于最小生成树， 则 访问该节点， 将该节点加入最小生成树。 否则获取下一个最小横切边。
		while(!edges.isEmpty()) {
			Edge edge = edges.deleteMin();
			int v = edge.either();
			int w = edge.other(v);
			if(marked[v] && marked[w]) {
				continue;
			}
			if(!marked[v]) {
				visit(v);
			}
			
			if(!marked[w]) {
				visit(w);
			}
			
			mst.add(edge);
		}
	}
	
	/**
	 * 每访问一个节点，将该节点的不在横切边集合中的邻接边加入横切边集合， 并标记。
	 * @param v
	 */
	private void visit(int v) {
		marked[v] = true;
		for(Edge e : graph.adj(v)) {
			if(!marked[e.other(v)]) {
				edges.insert(e);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] graph = new int[]{ 6, 8,  0,1,2,  0,3,6,   0,2,5 ,  1,3,4,  1,2,3,  2,4,1, 1,5,1, 0,4,6};
		LazyPrimMST mst = new LazyPrimMST(new EdgeWeightedGraph(graph));
		System.out.println(mst.mst);
	}
}
