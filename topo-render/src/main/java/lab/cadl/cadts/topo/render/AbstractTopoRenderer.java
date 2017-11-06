package lab.cadl.cadts.topo.render;

import lab.cadl.cadts.topo.render.model.Topology;

import java.io.OutputStream;
import java.util.Map;

public abstract class AbstractTopoRenderer implements TopoRenderer {
    private Map<String, Object> config;

    @Override
    public void render(Topology topology, OutputStream outputStream, Map<String, Object> config) {
        this.config = config;
        render(topology, outputStream);
    }

    @SuppressWarnings("unchecked")
    protected <T> T c(String name, T defaultValue) {
        return (T) config.getOrDefault(name, defaultValue);
    }

    protected abstract void render(Topology topology, OutputStream outputStream);
}
