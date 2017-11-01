package lab.cadl.cadts.topo.render.model;

/**
 * Created by lirui on 2017/11/1.
 */
public class Port {
    private int index;
    private Node node;
    private PortCategory category;

    public Port(int index, Node node, PortCategory category) {
        this.index = index;
        this.node = node;
        this.category = category;
    }

    public Port(int index, Node node) {
        this(index, node, PortCategory.NORMAL);
    }

    public int getIndex() {
        return index;
    }

    public Node getNode() {
        return node;
    }

    public PortCategory getCategory() {
        return category;
    }
}
