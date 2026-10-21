/*
 * Members:
 * Nash Portas,
 * Aaron Rafael,
 * Dilan Daza,
 * Michael Pepito*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AVLTreeGit {

    static class Node {
        int key;
        int height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    static int height(Node node) {
        return node == null ? 0 : node.height;
    }

    static int getBalance(Node node) {
        return node == null
                ? 0
                : height(node.left) - height(node.right);
    }

    // Right rotation
    static Node rotateRight(Node y) {
        Node x = y.left;
        Node temp = x.right;

        x.right = y;
        y.left = temp;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    // Left rotation
    static Node rotateLeft(Node x) {
        Node y = x.right;
        Node temp = y.left;

        y.left = x;
        x.right = temp;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    // Describe where the new number will be inserted
    static String describeInsertion(Node root, int key) {
        if (root == null) {
            return "Inserted " + key + " as the root.";
        }

        Node current = root;

        while (true) {
            if (key < current.key) {
                if (current.left == null) {
                    return "Inserted " + key
                            + " as the LEFT child of " + current.key + ".";
                }
                current = current.left;

            } else if (key > current.key) {
                if (current.right == null) {
                    return "Inserted " + key
                            + " as the RIGHT child of " + current.key + ".";
                }
                current = current.right;

            } else {
                return key + " already exists. No insertion performed.";
            }
        }
    }

    // Insert a number and record any rotations
    static Node insert(Node node, int key, List<String> rotations) {

        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key, rotations);
        } else if (key > node.key) {
            node.right = insert(node.right, key, rotations);
        } else {
            return node;
        }

        // Update height
        node.height = 1 + Math.max(
                height(node.left),
                height(node.right)
        );

        int balance = getBalance(node);

        // LL case
        if (balance > 1 && key < node.left.key) {
            rotations.add(
                    "LL imbalance at " + node.key
                    + ": RIGHT rotation. "
                    + node.left.key + " becomes the new subtree root."
            );
            return rotateRight(node);
        }

        // RR case
        if (balance < -1 && key > node.right.key) {
            rotations.add(
                    "RR imbalance at " + node.key
                    + ": LEFT rotation. "
                    + node.right.key + " becomes the new subtree root."
            );
            return rotateLeft(node);
        }

        // LR case
        if (balance > 1 && key > node.left.key) {
            rotations.add(
                    "LR imbalance at " + node.key
                    + ": LEFT rotation at " + node.left.key
                    + ", followed by RIGHT rotation at " + node.key + "."
            );

            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RL case
        if (balance < -1 && key < node.right.key) {
            rotations.add(
                    "RL imbalance at " + node.key
                    + ": RIGHT rotation at " + node.right.key
                    + ", followed by LEFT rotation at " + node.key + "."
            );

            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Calculate the horizontal position of each node
    static int xPosition(int depth, int position, int width) {
        return ((2 * position + 1) * width)
                / (1 << (depth + 1));
    }

    // Draw a top-down tree
    static void drawTree(
            Node node,
            int depth,
            int position,
            int levels,
            int width,
            char[][] canvas) {

        if (node == null) {
            return;
        }

        int x = xPosition(depth, position, width);
        int nodeY = depth * 4;

        // Draw connections to the children
        if (depth + 1 < levels
                && (node.left != null || node.right != null)) {

            int branchY = nodeY + 2;
            canvas[nodeY + 1][x] = '\u2502';

            if (node.left != null && node.right != null) {

                int leftX = xPosition(
                        depth + 1, position * 2, width
                );

                int rightX = xPosition(
                        depth + 1, position * 2 + 1, width
                );

                for (int col = leftX; col <= rightX; col++) {
                    canvas[branchY][col] = '\u2500';
                }

                canvas[branchY][x] = '\u252c';
                canvas[branchY][leftX] = '\u2534';
                canvas[branchY][rightX] = '\u2534';

                canvas[nodeY + 3][leftX] = '\u2502';
                canvas[nodeY + 3][rightX] = '\u2502';

            } else if (node.left != null) {

                int childX = xPosition(
                        depth + 1, position * 2, width
                );

                for (int col = childX; col <= x; col++) {
                    canvas[branchY][col] = '\u2500';
                }

                canvas[branchY][x] = '\u2518';
                canvas[branchY][childX] = '\u250c';
                canvas[nodeY + 3][childX] = '\u2502';

            } else {

                int childX = xPosition(
                        depth + 1, position * 2 + 1, width
                );

                for (int col = x; col <= childX; col++) {
                    canvas[branchY][col] = '\u2500';
                }

                canvas[branchY][x] = '\u2514';
                canvas[branchY][childX] = '\u2510';
                canvas[nodeY + 3][childX] = '\u2502';
            }
        }

        // Print the node value
        String value = String.valueOf(node.key);
        int startX = x - value.length() / 2;

        for (int i = 0; i < value.length(); i++) {
            if (startX + i >= 0 && startX + i < width) {
                canvas[nodeY][startX + i] = value.charAt(i);
            }
        }

        // Draw the left and right subtrees
        drawTree(
                node.left, depth + 1, position * 2,
                levels, width, canvas
        );

        drawTree(
                node.right, depth + 1, position * 2 + 1,
                levels, width, canvas
        );
    }

    static void printTreeTopDown(Node root) {
        if (root == null) {
            System.out.println("(Empty tree)");
            return;
        }

        int levels = height(root);
        int width = (1 << (levels - 1)) * 8;
        int rows = (levels - 1) * 4 + 1;

        char[][] canvas = new char[rows][width];

        for (char[] row : canvas) {
            Arrays.fill(row, ' ');
        }

        drawTree(root, 0, 0, levels, width, canvas);

        for (char[] row : canvas) {
            System.out.println(new String(row).stripTrailing());
        }
    }

    public static void main(String[] args) {

        int[] values = {
            60, 30, 90, 20, 40, 70,
            100, 10, 25, 35,
            45, 65, 80, 5, 15,
            22, 28, 62, 68, 75
        };

        Node root = null;

        for (int value : values) {

            System.out.println("\n========================================");
            System.out.println("INSERTING NUMBER: " + value);
            System.out.println("========================================");

            System.out.println(describeInsertion(root, value));

            List<String> rotations = new ArrayList<>();
            root = insert(root, value, rotations);

            if (rotations.isEmpty()) {
                System.out.println(
                        "Rotation: None. The tree remains balanced."
                );
            } else {
                for (String rotation : rotations) {
                    System.out.println("ROTATION: " + rotation);
                }
            }

            System.out.println("\nAVL TREE AFTER INSERTING " + value + ":");
            printTreeTopDown(root);
        }

        System.out.println("\n========================================");
        System.out.println("FINAL AVL TREE");
        System.out.println("========================================");

        printTreeTopDown(root);
    }
}
