package org.example;
import java.util.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;
public class StrGraph {
    public Map<String, Map<String, Integer>> list;
    public StrGraph() {
        this.list = new HashMap<>();
    }
    public void addV(String x) {
        this.list.put(x, new HashMap<>());
    }
    public boolean addE(String x, String y) {
        if (this.list.containsKey(x) && this.list.containsKey(y)) {
            if (this.list.get(x).containsKey(y)) {
                this.list.get(x).put(y, this.list.get(x).get(y) + 1);
            } else {
                this.list.get(x).put(y, 1);
            }
            return true;
        } else {
            return false;
        }
    }

    public void readStrArr(String[] words) {
        for (String s : words) {
            this.addV(s);
        }
        for (int i = 0; i < words.length - 1; i++) {
            this.addE(words[i], words[i + 1]);
        }
    }

    public void display() {
        // 创建一个有向图
        Graph graph = new SingleGraph("Directed Graph");

        // 设置 UI 属性
        System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing.util.Display");

        // 定义样式表
        String styleSheet =
                "node {" +
                        "   fill-color: black;" +
                        "   text-size: 20;" +
                        "   text-color: red;" +
                        "   text-background-mode: rounded-box;" +
                        "   text-background-color: white;" +
                        "   text-alignment: under;" +
                        "}" +
                        "edge {" +
                        "   text-size: 20;" +
                        "   text-color: blue;" +
                        "   text-background-mode: rounded-box;" +
                        "   text-background-color: white;" +
                        "   text-alignment: above;" +
                        "}";

        // 将样式表应用到图中
        graph.setAttribute("ui.stylesheet", styleSheet);

        // 添加顶点并设置标签
        for (String i : this.list.keySet()) {
            Node nodeC = graph.addNode(i);
            nodeC.setAttribute("ui.label", i);
        }
        // 添加边并设置权值标签
        for (String i : this.list.keySet()) {
            for (String j : this.list.get(i).keySet()) {
                Edge edgeBC = graph.addEdge(i + j, i, j, true);
                edgeBC.setAttribute("ui.label", this.list.get(i).get(j).toString());
            }
        }
        // 显示图形
        graph.display();
    }
}
