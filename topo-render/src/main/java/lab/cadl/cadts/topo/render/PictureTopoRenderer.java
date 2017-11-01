package lab.cadl.cadts.topo.render;

import lab.cadl.cadts.topo.render.model.Link;
import lab.cadl.cadts.topo.render.model.Node;
import lab.cadl.cadts.topo.render.model.Topology;

import java.io.OutputStream;

/**
 * Created by lirui on 2017/11/1.
 */
public class PictureTopoRenderer implements TopoRenderer {
    @Override
    public void render(Topology topology, OutputStream outputStream) {
        // TODO 完成渲染

        for (Node node : topology.nodes()) {
            // render node
        }

        for (Link link : topology.links()) {
            // render link
            // get nodes by link.getPort1().getNode() and link.getPort2().getNode()
        }
    }
}
