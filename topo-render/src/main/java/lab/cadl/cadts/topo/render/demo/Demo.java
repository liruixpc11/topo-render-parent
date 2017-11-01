package lab.cadl.cadts.topo.render.demo;

import lab.cadl.cadts.topo.render.PictureTopoRenderer;
import lab.cadl.cadts.topo.render.model.Topology;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by lirui on 2017/11/1.
 */
public class Demo {
    public static void main(String[] args) throws FileNotFoundException {
        Topology topology = new DemoTopologyGenerator().create();
        topology.display(System.out);

        new PictureTopoRenderer().render(topology, new FileOutputStream("demo.png"));
    }
}
