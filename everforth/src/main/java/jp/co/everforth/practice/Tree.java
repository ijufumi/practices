package jp.co.everforth.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Tree {
    private static final String TOP_LEAF_NAME = "/";
    private Leaf topLeaf;
    private Map<String, Leaf> leaves;

    public static Tree createTree(Path filePath) {
        Tree tree = new Tree();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("/");
                int idx = 0;
                for (int i = 0; i < data.length; i++) {
                    if (data[i].length() == 0) {
                        continue;
                    }
                    Leaf leaf = tree.getLeaf(data[i]);
                    if (leaf == null) {
                        leaf = new Leaf(data[i]);
                    }
                    if (i != data.length - 1) {
                        leaf.addChild(data[i + 1]);
                    }
                    if (idx == 0) {
                        tree.getLeaf(TOP_LEAF_NAME).addChild(data[i]);
                    }
                    tree.addLeaf(data[i], leaf);
                    idx++;
                }
            }
        }
        catch (IOException | SecurityException e) {
            System.out.println("ファイルの読み込みに失敗しました。:" + filePath.toString());
            e.printStackTrace();
            System.exit(1);
        }

        return tree;
    }

    private Tree() {
        this.topLeaf = new Leaf(TOP_LEAF_NAME);
        this.leaves = new HashMap<>();
        addLeaf(TOP_LEAF_NAME, topLeaf);
    }

    private boolean addLeaf(String name, Leaf leaf) {
        if (leaves.containsKey(name)) {
            return false;
        }
        leaves.put(name, leaf);
        return true;
    }

    private Leaf getLeaf(String name) {
        return leaves.get(name);
    }

    public Set<String> getNames() {
        return leaves.keySet();
    }

    public String toString() {
        return getChild(topLeaf, "");
    }

    private String getChild(Leaf leaf, String prefix) {
        StringBuilder sb = new StringBuilder();
        String[] children = leaf.getChildren();
        if (children.length == 0) {
            return sb.toString();
        }
        for (int i = 0; i < children.length; i++) {
            Leaf child = leaves.get(children[i]);
            if (i == 0) {
                sb.append("+-");
            }
            else {
                sb.append("|-");
            }
            sb.append(child.getName());
            if (child.getChildren().length != 0) {
                sb.append("-");
            }
            else {
                sb.append(" ");
            }

            if (i != children.length - 1) {
                sb.append(getChild(child, prefix + "|" + fillSpace(child.getName().length() + 2)));
            }
            else {
                sb.append(getChild(child, prefix + fillSpace(child.getName().length() + 3)));
            }

            if (i != children.length - 1) {
                sb.append(System.lineSeparator());
                sb.append(prefix);
            }
        }
        return sb.toString();
    }

    private String fillSpace(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
