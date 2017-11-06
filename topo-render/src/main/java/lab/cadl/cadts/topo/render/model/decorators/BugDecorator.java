package lab.cadl.cadts.topo.render.model.decorators;

import lab.cadl.cadts.topo.render.model.NodeDecorator;
import lab.cadl.cadts.topo.render.model.NodeDecoratorCategory;
import lab.cadl.cadts.topo.render.model.exploit.Vulnerability;

import java.util.ArrayList;
import java.util.List;

public class BugDecorator extends NodeDecorator {
    public enum State {
        INIT,
        EXPLOITED
    }

    private State state;
    private int index;
    private Vulnerability vulnerability;

    private List<BugDecorator> expectedNextList = new ArrayList<>();
    private BugDecorator realNext;

    public BugDecorator(State state, int index, Vulnerability vulnerability) {
        super(NodeDecoratorCategory.STATE);
        this.state = state;
        this.index = index;
        this.vulnerability = vulnerability;
    }

    public void addExpectedNext(BugDecorator nextBug) {
        this.expectedNextList.add(nextBug);
    }

    public List<BugDecorator> getExpectedNextList() {
        return expectedNextList;
    }

    public BugDecorator getRealNext() {
        return realNext;
    }

    public void setRealNext(BugDecorator realNext) {
        this.realNext = realNext;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Vulnerability getVulnerability() {
        return vulnerability;
    }

    public void setVulnerability(Vulnerability vulnerability) {
        this.vulnerability = vulnerability;
    }

    public void exploit() {
        this.state = State.EXPLOITED;
    }

}
