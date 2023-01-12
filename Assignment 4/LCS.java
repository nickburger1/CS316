import java.util.Scanner;
public class LCS {
    static void longestcommonsubsequence(String X, String Y, int m, int n) {
        int[][] array = new int[m+1][n+1];
        for (int i=0; i<=m; i++) {
            for (int j=0; j<=n; j++) {
                if (i == 0 || j == 0)
                    array[i][j] = 0;
                else if (X.charAt(i-1) == Y.charAt(j-1))
                    array[i][j] = array[i-1][j-1] + 1;
                else
                    array[i][j] = Math.max(array[i-1][j], array[i][j-1]);
            }
        }

        int index = array[m][n];
        int temp = index;

        char[] longestcommonsubsequence = new char[index+1];
        longestcommonsubsequence[index] = ' ';
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (X.charAt(i-1) == Y.charAt(j-1)) {
                longestcommonsubsequence[index-1] = X.charAt(i-1);
                i--;
                j--;
                index--;
            }
            else if (array[i-1][j] > array[i][j-1])
                i--;
            else
                j--;
        }
        for(int k=0;k<=temp;k++)
            System.out.print(longestcommonsubsequence[k]);
    }

    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter First String: ");
        String X = sc.nextLine();
        System.out.print("Enter Second String: ");
        String Y = sc.nextLine();
        int m = X.length();
        int n = Y.length();
        longestcommonsubsequence(X, Y, m, n);
    }
}
