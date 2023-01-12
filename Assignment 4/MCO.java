import java.util.*;

public class MCO {
    static char name;
    static void printParenthesis(int i, int j, int n, int[][] bracket)
    {
        if (i == j) {
            System.out.print(name++);
            return;
        }
        System.out.print('(');
        printParenthesis(i, bracket[j][i], n, bracket);
        printParenthesis(bracket[j][i] + 1, j, n, bracket);
        System.out.print(')');
    }

    static void matrixChainOrder(int[] p, int n) {
        int[][] m = new int[n][n];
        for (int L = 2; L < n; L++) {
            for (int i = 1; i < n - L + 1; i++) {
                int j = i + L - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        m[j][i] = k;
                    }
                }
            }
        }

        name = 'A';
        System.out.print("Optimal Parenthesization is: ");
        printParenthesis(1, n - 1, n, m);
        System.out.print("\nOptimal Cost is :" + m[1][n - 1]);
    }

    public static void main(String[] args) {
        int[] arr = { 40, 20, 30, 10, 30 };
        int n = arr.length;
        System.out.println("Input: " + Arrays.toString(arr));
        matrixChainOrder(arr, n);
    }
}
