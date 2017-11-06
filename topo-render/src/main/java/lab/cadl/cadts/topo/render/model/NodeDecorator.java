package lab.cadl.cadts.topo.render.model;

/**
 */
public abstract class NodeDecorator {
    private NodeDecoratorCategory category;
    private Node node;

    protected NodeDecorator(NodeDecoratorCategory category) {
        this.category = category;
    }

    public NodeDecoratorCategory getCategory() {
        return category;
    }

    public void visit(NodeDecoratorVisitor visitor) {
        visitor.visit(this);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
