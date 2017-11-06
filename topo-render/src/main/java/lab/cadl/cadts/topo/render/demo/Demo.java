package lab.cadl.cadts.topo.render.demo;

import lab.cadl.cadts.topo.render.PictureTopoRenderer;
import lab.cadl.cadts.topo.render.model.Topology;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;

/**
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        Topology topology = new DemoTopologyGenerator().create();
        topology.display(System.out);

        try (FileOutputStream outputStream = new FileOutputStream("/tmp/demo.png")) {
            new PictureTopoRenderer().render(topology, outputStream, Collections.emptyMap());
        }
    }
}
