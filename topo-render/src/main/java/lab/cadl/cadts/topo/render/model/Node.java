package lab.cadl.cadts.topo.render.model;

import lab.cadl.cadts.topo.render.model.decorators.BugDecorator;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * 节点
 */
public class Node {
    private String name;
    private Image image;
    private Location location;
    private NodeCategory category;
    private List<Port> portList = new ArrayList<>();
    private List<NodeDecorator> decoratorList = new ArrayList<>();

    public Node(String name, Image image, Location location, NodeCategory category) {
        this.name = name;
        this.image = image;
        this.location = location;
        this.category = category;
    }

    public Node(String name, Image image, Location location) {
        this(name, image, location, NodeCategory.NORMAL);
    }

    public Port addPort() {
        int index = 0;
        index = portList.stream().map(Port::getIndex).max(Integer::compareTo).orElse(0) + 1;
        Port port = new Port(index, this);
        portList.add(port);
        return port;
    }

    public String getName() {
        return name;
    }

    public NodeCategory getCategory() {
        return category;
    }

    public Image getImage() {
        return image;
    }

    public Location getLocation() {
        return location;
    }

    public List<Port> ports() {
        return portList;
    }

    public List<NodeDecorator> decorators() {
        return decoratorList;
    }

    public Port queryPort(int index) {
        return portList.stream().filter(p -> p.getIndex() == index).findAny().orElse(null);
    }

    public void addDecorator(NodeDecorator decorator) {
        decoratorList.add(decorator);
        decorator.setNode(this);
    }

    public BugDecorator checkBug(String vulnerabilityName) {
        for (NodeDecorator decorator : decoratorList) {
            if (decorator instanceof BugDecorator) {
                BugDecorator bugDecorator = (BugDecorator) decorator;
                if (bugDecorator.getVulnerability().getName().equals(vulnerabilityName)) {
                    return bugDecorator;
                }
            }
        }

        throw new IllegalArgumentException(String.format("BUG %s not found", vulnerabilityName));
    }
}
