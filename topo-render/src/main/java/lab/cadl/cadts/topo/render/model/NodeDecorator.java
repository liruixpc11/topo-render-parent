package lab.cadl.cadts.topo.render.model;

/**
 */
public abstract class NodeDecorator {
    private NodeDecoratorCategory category;

    protected NodeDecorator(NodeDecoratorCategory category) {
        this.category = category;
    }

    public NodeDecoratorCategory getCategory() {
        return category;
    }

    public void visit(NodeDecoratorVisitor visitor) {
        visitor.visit(this);
    }
}
