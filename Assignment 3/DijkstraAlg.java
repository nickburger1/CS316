
import java.util.*;
import java.lang.*;
import java.io.*;

//C:\Users\nick2\OneDrive\Documents\.School\CS316\HW3P3Input.txt
    public class DijkstraAlg {
        static final int V = 9;
        int minDistance(int[] dist, Boolean[] sptSet, int V) {
            int min = Integer.MAX_VALUE, min_index = -1;
            for (int v = 0; v < V; v++)
                if (!sptSet[v] && dist[v] <= min) {
                    min = dist[v];
                    min_index = v;
                }
            return min_index;
        }

        void printSolution(int[] dist, int V, int src) {
            System.out.println("Vertex Distance from Source");
            for (int i = 0; i < V; i++)
                System.out.println(src+"-->"+" Node"+i + " : " + dist[i]);
        }

        void dijkstra(int graph[][], int src, int V) {
            int[] dist = new int[V];
            Boolean[] sptSet = new Boolean[V];
            for (int i = 0; i < V; i++) {
                dist[i] = Integer.MAX_VALUE;
                sptSet[i] = false;
            }
            dist[src] = 0;
            for (int count = 0; count < V - 1; count++) {
                int u = minDistance(dist, sptSet, V);
                sptSet[u] = true;
                for (int v = 0; v < V; v++)
                    if (!sptSet[v] && graph[u][v] != 0 &&
                            dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                        dist[v] = dist[u] + graph[u][v];
            }

            printSolution(dist, V, src);
        }

        public static void main(String[] args) throws FileNotFoundException {
            Scanner s = new Scanner(System.in);
            System.out.print("Please enter path to file: ");
            String filePath = s.next();
            File file = new File(filePath);

            Scanner f = new Scanner(file);
            String[] g;
            int[][] graph = new int[9][9];

            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    graph[i][j] = 0;
                }
            }
            int numOfNodes = 0;
            int row = 0;
            while(f.hasNext()){
                g = f.nextLine().split("\\s+");
                for(int i = 0; i < g.length; i++){
                    graph[row][i] = Integer.parseInt(g[i]);
                }
                numOfNodes++;
                row++;
            }
            DijkstraAlg t = new DijkstraAlg();
            int sourceNode=0;
            t.dijkstra(graph, sourceNode, numOfNodes);
        }
    }
