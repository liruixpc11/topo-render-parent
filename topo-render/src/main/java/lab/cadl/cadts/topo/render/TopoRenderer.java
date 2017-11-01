package lab.cadl.cadts.topo.render;

import lab.cadl.cadts.topo.render.model.Topology;

import java.io.OutputStream;

/**
 * 拓扑渲染器
 */
public interface TopoRenderer {
    void render(Topology topology, OutputStream outputStream);
}
