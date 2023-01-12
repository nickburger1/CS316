import java.util.*;

class Node {
    int value;
    char ch;
    Node left, right;
}

class Comp implements Comparator<Node> {
    public int compare(Node x, Node y) {
        return x.value - y.value;
    }
}


public class Huffman {

    private static Map<Character, Integer> map;
    static {
        map = new HashMap<>();
    }

    public static void printTable(Node root, String str) {

        if (root.left == null && root.right == null && root.ch != '-') {
            System.out.println(root.ch + "  :  " + str);
            map.put(root.ch, str.length());
            return;
        }
        printTable(root.left, str + "0");
        printTable(root.right, str + "1");
    }

    public static void main(String[] args) {

        System.out.print("Enter String to Compress: " );
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        char[] char_arr = str.toCharArray();
        int[] freq = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            freq[i] = 1;
            for (int j = i + 1; j < str.length(); j++) {
                if (char_arr[i] == char_arr[j]) {
                    freq[i]++;
                    char_arr[j] = '0';
                }
            }
        }
        int n = char_arr.length;
        char[] cArray = new char[char_arr.length];
        int[] cFreq = new int[char_arr.length];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (char_arr[i] != '0') {
                cArray[j] = char_arr[i];
                cFreq[j] = freq[i];
                j++;
            }
        }
        n = j;

        PriorityQueue<Node> que = new PriorityQueue<Node>(n, new Comp());
        for (int i = 0; i < n; i++) {
            Node temp = new Node();
            temp.ch = cArray[i];
            temp.value = cFreq[i];
            temp.left = null;
            temp.right = null;
            que.add(temp);
        }

        Node root = null;
        while (que.size() > 1) {
            Node a = que.peek();
            que.poll();
            Node b = que.peek();
            que.poll();
            Node c = new Node();
            c.value = a.value + b.value;
            c.ch = '-';
            c.left = a;
            c.right = b;
            root = c;
            que.add(c);
        }
        printTable(root, "");
        int old_size = str.length() * 8;
        int new_size = 0;
        for (int i = 0; i < str.length(); i++) {
            new_size += map.get(str.charAt(i));
        }
        float cr = (float) new_size / old_size;
        System.out.println("Compression ratio: " + cr);
    }
}
