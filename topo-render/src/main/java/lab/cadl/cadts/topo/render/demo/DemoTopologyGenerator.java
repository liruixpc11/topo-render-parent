package lab.cadl.cadts.topo.render.demo;

import lab.cadl.cadts.topo.render.io.ClassPathImageRepository;
import lab.cadl.cadts.topo.render.io.ImageRepository;
import lab.cadl.cadts.topo.render.model.Topology;
import lab.cadl.cadts.topo.render.model.TopologyBuilder;
import lab.cadl.cadts.topo.render.model.decorators.BugDecorator;
import lab.cadl.cadts.topo.render.model.exploit.ExploitGraph;
import lab.cadl.cadts.topo.render.model.exploit.Vulnerability;
import lab.cadl.cadts.topo.render.model.exploit.VulnerabilityCategory;

import java.awt.*;

/**
 */
public class DemoTopologyGenerator {
    private ImageRepository imageRepository = new ClassPathImageRepository();

    public Topology create() {
        Topology topology = new TopologyBuilder()
//                .addLan("内网交换机", image("工作组交换机"), "内网终端", image("计算机"), 3)
//                .addLan("外网交换机", image("工作组交换机"), "外网终端", image("计算机"), 3)
//                .addLan("DMZ交换机", image("工作组交换机"), "DMZ服务器", image("Web服务器"), 2)
//                .addNode("核心路由器", image("路由器"))
//                .addNodeDecorator("核心路由器", new BugDecorator())
//                .addNodeDecorator("核心路由器", new BugDecorator())
//                .addNodeDecorator("核心路由器",new BugDecorator(BugDecorator.State.EXPLOITED))
//                .addLink("核心路由器", "内网交换机")
//                .addLink("核心路由器", "外网交换机")
//                .addLink("核心路由器", "DMZ交换机")
                .addNode("内网终端.01", image("计算机"), 50, 20)
                .addNode("内网终端.02", image("计算机"), 20, 50)
                .addNode("内网终端.03", image("计算机"), 80, 50)
                // .addNode("内网终端.04", image("计算机"),75,50)
                .addNode("内网交换机", image("工作组交换机"), 50, 50)
                .addNode("外网终端.01", image("计算机"), 150, 20)
                .addNode("外网终端.02", image("计算机"), 120, 50)
                .addNode("外网终端.03", image("计算机"), 180, 50)
                //  .addNode("外网终端.04", image("计算机"),175,50)
                .addNode("外网交换机", image("工作组交换机"), 150, 50)
                .addNode("核心路由器", image("路由器"), 100, 75)
                .addNode("DMZ交换机.01", image("工作组交换机"), 50, 100)
                .addNode("DMZ服务器.01", image("Web服务器"), 50, 125)
                .addNode("DMZ交换机.02", image("工作组交换机"), 150, 100)
                .addNode("DMZ服务器.02", image("Web服务器"), 150, 125)
                .addLink("内网终端.01", "内网交换机")
                .addLink("内网终端.02", "内网交换机")
                .addLink("内网终端.03", "内网交换机")
                //  .addLink("内网终端.04","内网交换机")
                .addLink("外网终端.01", "外网交换机")
                .addLink("外网终端.02", "外网交换机")
                .addLink("外网终端.03", "外网交换机")
                //  .addLink("外网终端.04","外网交换机")
                .addLink("核心路由器", "内网交换机")
                .addLink("核心路由器", "外网交换机")
                .addLink("核心路由器", "DMZ交换机.01")
                .addLink("核心路由器", "DMZ交换机.02")
                .addLink("DMZ服务器.01", "DMZ交换机.01")
                .addLink("DMZ服务器.02", "DMZ交换机.02")
                .build();

        topology.beginBuildExploitGraph()
                .nextAction(new Vulnerability("本地提权", VulnerabilityCategory.Local), "外网终端.01")
                .nextAction(new Vulnerability("SQL注入", VulnerabilityCategory.Remote), "DMZ服务器.01")
                .nextAction(new Vulnerability("口令泄露", VulnerabilityCategory.WeakPassword), "核心路由器")
                .nextAction(new Vulnerability("后门", VulnerabilityCategory.Remote), "内网终端.01")
                .prevAction()
                .nextAction(new Vulnerability("缓冲区溢出", VulnerabilityCategory.Remote), "内网终端.02")
                .prevAction()
                .nextAction(new Vulnerability("敏感信息泄露", VulnerabilityCategory.Remote), "内网终端.03")
                .prevAction()
                .beginExploit()
                .exploit("外网终端.01", "本地提权")
                .exploit("核心路由器", "口令泄露")
                .exploit("内网终端.02", "缓冲区溢出")
                .build();

        return topology;
    }

    private Image image(String name) {
        return imageRepository.load(String.format("/images/%s.png", name));
    }
}
