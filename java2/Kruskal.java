import java.util.ArrayList;
import java.util.Collections;

class Edge implements Comparable<Edge>{
  int src, dst, weight;

  public Edge (int src, int dst, int weight) {
    this.src = src;
    this.dst = dst;
    this.weight = weight;
  }

  public int compareTo(Edge other) {
    return (weight - other.weight);
  }
}

class Graph {
  ArrayList<Integer> vertices;
  ArrayList<Edge> edges;

  public Graph() {
    vertices = new ArrayList<Integer>();
    edges = new ArrayList<Edge>();
  }

  public Graph(int vertices){
    this();
    for(int i=0; i<vertices; i++){
      this.vertices.add(i);
    }
  }

  class Subset {
    int parent;
    int rank;

    public Subset(){
      parent = -1;
      rank = 0;
    }
  }

  public int addVertex(){
    int size = vertices.size();
    vertices.add(size);
    return size;
  }

  public boolean addEdge(int src, int dst, int weight) {
    if(!vertices.contains(src) || !vertices.contains(dst))
      return false;
    Edge e = new Edge(src, dst, weight);
    return addEdge(e);
  }

  public boolean addEdge(Edge e){
    return edges.add(e);
  }

  public boolean removeEdge(Edge e){
    return edges.remove(e);
  }

  private static int findParent(Subset[] subsets, int x){
    if(subsets[x].parent == -1)
      return x;
    else
      return findParent(subsets, subsets[x].parent);
  }

  private static void union(Subset[] subsets, int parentA, int parentB){
    if(subsets[parentA].rank > subsets[parentB].rank){
      subsets[parentB].parent = parentA;
    } else if(subsets[parentB].rank > subsets[parentA].rank){
      subsets[parentA].parent = parentB;
    } else {
      subsets[parentA].parent = parentB;
      subsets[parentB].rank++;
    }
  }

  public boolean isCyclic(){
    Subset[] subsets = new Subset[vertices.size()];

    for(int i=0; i<subsets.length; i++){
      subsets[i] = new Subset();
    }

    for(Edge e: edges) {
      int srcParent = findParent(subsets, e.src);
      int dstParent = findParent(subsets, e.dst);

      if(srcParent == dstParent)
        return true;

      union(subsets, srcParent, dstParent);
    }

    return false;
  }

  public void printVertices(){
    for(int i=0; i<vertices.size(); i++){
      System.out.printf("%d  ", vertices.get(i));
    }
    System.out.println("");
  }

  public void printEdges(){
    int sum = 0;
    for(int i=0; i<edges.size(); i++){
      Edge e = edges.get(i);
      sum += e.weight;
      System.out.printf("Edge %2d: %2d -- %d  ;  %2d\n", i, e.src, e.dst, e.weight);
    }
    System.out.println("Total weight = " + sum);
  }
}

public class Kruskal {
  public static void main(String[] args){
    Graph g = new Graph(8);
    g.addEdge(0, 1, 8);
    g.addEdge(0, 6, 4);
    g.addEdge(0, 3, 5);
    g.addEdge(1, 2, 9);
    g.addEdge(1, 4, 3);
    g.addEdge(2, 3, 6);
    g.addEdge(2, 4, 2);
    g.addEdge(2, 5, 5);
    g.addEdge(3, 5, 8);
    g.addEdge(3, 6, 3);
    g.addEdge(3, 7, 8);
    g.addEdge(4, 5, 4);
    g.addEdge(5, 7, 1);
    g.printVertices();
    g.printEdges();
    System.out.println(g.isCyclic());

    Graph mst = Kruskal.MST(g);
    System.out.println("");
    mst.printVertices();
    mst.printEdges();
    System.out.println(mst.isCyclic());
  }

  public static Graph MST(Graph g){
    Graph mst = new Graph(g.vertices.size());
    Collections.sort(g.edges);
    for(Edge e : g.edges) {
      mst.addEdge(e);
      if(mst.isCyclic())
        mst.removeEdge(e);
    }

    return mst;
  }
}
