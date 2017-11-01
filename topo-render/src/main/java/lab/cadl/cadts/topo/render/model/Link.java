package lab.cadl.cadts.topo.render.model;

/**
 * Created by lirui on 2017/11/1.
 */
public class Link {
    private Port port1;
    private Port port2;
    private LinkCategory category;

    public Link(Port port1, Port port2, LinkCategory category) {
        this.port1 = port1;
        this.port2 = port2;
        this.category = category;
    }

    public Link(Port port1, Port port2) {
        this(port1, port2, LinkCategory.NORMAL);
    }

    public Port getPort1() {
        return port1;
    }

    public Port getPort2() {
        return port2;
    }

    public LinkCategory getCategory() {
        return category;
    }
}
