package com.hubble.algorithm.graph;

import java.util.ArrayList;


/**
 * 加权无向图</br>
 * 加权无向图是一种 为每一条边关联一个 权重 或 成本 的图模型。</br>
 * 数据结构设计：</br>
 * 仍然采用 邻接表的 数据结构， 不过 一个顶点的 邻接表信息 不应该只包含一个顶点， 而是包含 两个顶点和 边的权重
 * @author hubble
 *
 */
public class EdgeWeightedGraph {
	private int V;
	private int E;
	private ArrayList<Edge>[] adj;
	
	/** 从一个数组中初始化一个图
	 * 
	 * @param graph [<顶点数>, <边数>, <顶点v1>, <顶点w1>, <权重 weight1> ... ]
	 */
	public EdgeWeightedGraph(int[] graph) {
		this.V = graph[0];
		this.E = graph[1];
		
		adj = new ArrayList[V];
		for(int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 2; i < graph.length - 2; i++) {
			addEdge(graph[i], graph[i + 1], graph[i + 2]);
		}
	}
	
	/** 顶点的数量*/
	public int V() {
		return V;
	}
	
	/** 边的数量*/
	public int E() {
		return E;
	}
	
	/** 给图中增加一条边*/
	public void addEdge(int v, int w, double weight) {
		Edge edge = new Edge(v, w, weight);
		adj[v].add(edge);
		adj[w].add(edge);
		E++;
	}
	
	/** 获取一个顶点的 邻接点信息*/
	public ArrayList<Edge> adj(int v) {
		return adj[v];
	}
}


class Edge implements Comparable<Edge>{
	public int v;
	public int w;
	public double weight;
	
	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	/** 边的任意一个顶点*/
   public int either() {
	   return v;
   }
   
   /** 根据边的一个顶点 获取 另外一个顶点*/
   public int other(int v) {
	   if(v == this.v) {
		   return this.w;
	   }
	   
	   else if(v == this.w) {
		   return this.v;
	   }
	   
	   else {
		   throw new RuntimeException("顶点不存在于边中");
	   }
   }
   
   @Override
public int compareTo(Edge that) {
	   if(this.weight > that.weight) {
		   return 1;
	   }
	   
	   else if(this.weight < that.weight) {
		   return -1;
	   }
	return 0;
}
}
