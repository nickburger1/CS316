import java.io.*;
import java.util.*;

//C:\Users\nick2\OneDrive\Documents\.School\CS316\HW3P2Input.txt

public class PrimAlg {

    int minKey(int[] key, Boolean[] mstSet, int V) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    void printMST(int[] parent, int[][] graph, int V) {

        for (int i = 1; i < V; i++){
            int[] output = new int[V];
            for(int j = 0; j < V; j++){
                output[j] = 0;
            }
            output[i] = graph[i][parent[i]];
            System.out.println(Arrays.toString(output));
        }
    }

    void primMST(int[][] graph, int V) {

        int[] parent = new int[V];
        int[] key = new int[V];
        Boolean[] mstSet = new Boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet, V);
            mstSet[u] = true;
            for (int v = 0; v < V; v++)
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }
        printMST(parent, graph, V);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        System.out.print("Please enter path to file: ");
        String filePath = s.next();
        File file = new File(filePath);

        Scanner f = new Scanner(file);
        String[] g;
        int[][] graph = new int[10][10];

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
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

        PrimAlg p = new PrimAlg();
        p.primMST(graph, numOfNodes);
    }
}
