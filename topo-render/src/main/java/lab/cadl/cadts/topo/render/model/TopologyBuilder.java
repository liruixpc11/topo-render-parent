package lab.cadl.cadts.topo.render.model;

import java.awt.Image;

/**
 */
public class TopologyBuilder {
    private static final double WIDTH = 20;
    private static final double HEIGHT = 20;

    private int locationIndex = 0;
    private Topology topology = new Topology();

    public Topology build() {
        return topology;
    }

    private Location nextLocation() {
        int baseI = (int) Math.sqrt(locationIndex);
        int baseJ = (int) Math.pow(baseI, 2);

        int delta = locationIndex - baseJ;
        locationIndex += 1;

        if (delta == 0) {
            return new Location(baseI * (WIDTH + 5), baseI * (HEIGHT + 5), WIDTH, HEIGHT);
        } else if (delta <= baseI) {
            return new Location((baseI + 1) * (WIDTH + 5), delta * ( HEIGHT + 5), WIDTH, HEIGHT);
        } else {
            return new Location((delta - baseI) * (WIDTH + 5), (baseI + 1) * (HEIGHT + 5), WIDTH, HEIGHT);
        }
    }

    public TopologyBuilder addNode(String name, Image image) {
        topology.addNode(name, image, nextLocation());
        return this;
    }

    public TopologyBuilder addLink(String node1Name, String node2Name) {
        topology.addLink(node1Name, node2Name);
        return this;
    }

    public TopologyBuilder addLan(String switchName, Image switchImage, String baseTerminalName, Image terminalImage, int count) {
        Node switchNode = topology.addNode(switchName, switchImage, nextLocation());

        for (int i = 1; i <= count; i++) {
            String terminalName = String.format("%s-%04d", baseTerminalName, i);
            Node terminalNode = topology.addNode(terminalName, terminalImage, nextLocation());
            topology.addLink(switchNode, terminalNode);
        }

        return this;
    }
}
