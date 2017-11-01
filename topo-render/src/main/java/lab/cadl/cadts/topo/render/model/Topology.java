package lab.cadl.cadts.topo.render.model;

import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lirui on 2017/11/1.
 */
public class Topology {
    private Map<String, Node> nodeMap = new HashMap<>();
    private List<Link> linkList = new ArrayList<>();

    public Node queryNode(String name) {
        return nodeMap.get(name);
    }

    public Node checkNode(String name) {
        Node node = queryNode(name);
        if (node == null) {
            throw new IllegalArgumentException(String.format("Node %s Not Found", name));
        }

        return node;
    }

    public List<Node> nodes() {
        return new ArrayList<>(nodeMap.values());
    }

    public List<Link> links() {
        return linkList;
    }

    public Node addNode(String name, Image image, Location location) {
        Node node = new Node(name, image, location);
        nodeMap.put(name, node);
        return node;
    }

    public Link addLink(Port port1, Port port2) {
        Link link = new Link(port1, port2);
        linkList.add(link);
        return link;
    }

    public Link addLink(Node node1, Node node2) {
        return addLink(node1.addPort(), node2.addPort());
    }

    public Link addLink(String node1Name, String node2Name) {
        return addLink(checkNode(node1Name), checkNode(node2Name));
    }

    public void display(PrintStream os) {
        for (Node node : nodes()) {
            os.printf("[NODE] %s (%f, %f)\n", node.getName(), node.getLocation().getX(), node.getLocation().getY());
        }

        for (Link link : links()) {
            os.printf("[LINK] %s.%02d - %s.%02d\n", link.getPort1().getNode().getName(), link.getPort1().getIndex(),
                    link.getPort2().getNode().getName(), link.getPort2().getIndex());
        }
    }
}
