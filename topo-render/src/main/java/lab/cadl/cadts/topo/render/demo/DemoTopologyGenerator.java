package lab.cadl.cadts.topo.render.demo;

import lab.cadl.cadts.topo.render.io.ClassPathImageRepository;
import lab.cadl.cadts.topo.render.io.ImageRepository;
import lab.cadl.cadts.topo.render.model.Topology;
import lab.cadl.cadts.topo.render.model.TopologyBuilder;

import java.awt.*;

/**
 */
public class DemoTopologyGenerator {
    private ImageRepository imageRepository = new ClassPathImageRepository();

    public Topology create() {
        return new TopologyBuilder()
                .addLan("内网交换机", image("工作组交换机"), "内网终端", image("计算机"), 10)
                .addLan("外网交换机", image("工作组交换机"), "外网终端", image("计算机"), 10)
                .addLan("DMZ交换机", image("工作组交换机"), "DMZ服务器", image("Web服务器"), 4)
                .addNode("核心路由器", image("路由器"))
                .addLink("核心路由器", "内网交换机")
                .addLink("核心路由器", "外网交换机")
                .addLink("核心路由器", "DMZ交换机")
                .build();
    }

    private Image image(String name) {
        return imageRepository.load(String.format("/images/%s.png", name));
    }
}
