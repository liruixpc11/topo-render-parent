package lab.cadl.cadts.topo.render;

import lab.cadl.cadts.topo.render.io.ClassPathImageRepository;
import lab.cadl.cadts.topo.render.io.ImageRepository;
import lab.cadl.cadts.topo.render.model.*;
import lab.cadl.cadts.topo.render.model.decorators.BugDecorator;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.io.OutputStream;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Color;

/**
 * Created by lirui on 2017/11/1.
 */

public class PictureTopoRenderer extends AbstractTopoRenderer {
    private ImageRepository imageRepository = new ClassPathImageRepository();

    private Image image(String name) {
        return imageRepository.load(String.format("/images/%s.png", name));
    }

    @Override
    protected void render(Topology topology, OutputStream outputStream) {
        final boolean renderBug = c("renderBug", true);
        final boolean connectBug = c("connectBug", true);
        final String fontName = c("fontName", "仿宋");
        final String formatName = c("formatName", "PNG");

        int imageWidth = 10240;
        int imageHeight = 7240;
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, 15000, 7240);

        for (Link link : topology.links()) {
            g.setColor(Color.darkGray);
            Node node_1 = link.getPort1().getNode();
            Node node_2 = link.getPort2().getNode();
            g.setStroke(new BasicStroke(10.0f));
            g.drawLine((int) (node_1.getLocation().getX() * 5 + 20) * 10,
                    ((int) node_1.getLocation().getY() * 4 + 20) * 10,
                    ((int) node_2.getLocation().getX() * 5 + 20) * 10,
                    ((int) node_2.getLocation().getY() * 4 + 20) * 10);
        }

        for (Node node : topology.nodes()) {
            g.setColor(Color.white);
            g.fillRoundRect((int) (node.getLocation().getX() * 5 - 20) * 10, (int) (node.getLocation().getY() * 4 - 5) * 10+420, 800, 170, 15, 15);//画着色块
            //g.setColor(Color.darkGray);
            //g.drawRoundRect((int) (node.getLocation().getX() * 5 - 20) * 10, (int) (node.getLocation().getY() * 4 - 5) * 10+450, 800, 150, 15, 15);

        }

        int xx = 0;
        int yy = 0;

        for (Node node : topology.nodes()) {

            int x = (int) node.getLocation().getX();
            int y = (int) node.getLocation().getY();

            g.drawImage(node.getImage(), (int) x * 50, (int) y * 40, 400, 400, null);
            g.setColor(Color.black);
            Font f4 = new Font(fontName, Font.PLAIN, 120);
            g.setFont(f4);
            g.drawString(node.getName(), (int) (x * 5 - 12) * 10, (int) (y * 4 + 53) * 10);

            if (renderBug) {

                for (NodeDecorator decorator : node.decorators()) {
                    if (decorator instanceof BugDecorator) {
                        BugDecorator bug = (BugDecorator) decorator;

                        int index = bug.getIndex();

                        switch (bug.getState()) {
                            case INIT:
                                // 画未被攻击的BUG
                                g.drawImage(image("initbug"), (int) (x * 5 + 12) * 10-50, (int) (y * 4 - 25) * 10, 270, 270, null);
                                break;
                            case EXPLOITED:
                                // 画已被攻击的BUG
                                g.drawImage(image("exploitedbug"), (int) (x * 5 + 12) * 10-50, (int) (y * 4 - 25) * 10, 270, 270, null);
                                break;
                            default:
                                break;
                        }

                        g.drawImage(image(String.valueOf(index)), (int) (x * 5 + 25) * 10+60, (int) (y * 4 - 13) * 10+10, 120, 120, null);
                    }
                }
            }


            if (connectBug) {

                for (NodeDecorator decorator : node.decorators()) {
                    if (decorator instanceof BugDecorator) {
                        BugDecorator bug = (BugDecorator) decorator;

                        int index = bug.getIndex();
                        double realxa = 0;
                        double realya = 0;
                        double realxb = 0;
                        double realyb = 0;

                        switch (bug.getState()) {
                            case INIT:
                                if (xx != 0 & yy != 0) {
                                    float[] dash = {30, 30}; //短划线图案
                                    BasicStroke bs = new BasicStroke(15, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f); //实例化新画刷
                                    g.setStroke(bs); //设置新的画刷
                                    g.setColor(Color.red);

                                    g.drawLine((x * 5 + 25) * 10+180, (y * 4 - 13) * 10 + 130, (xx * 5 + 25) * 10-120, (yy * 4 - 13) * 10 + 130); //用新的画刷绘制虚线
                                    int realx = (x * 5 + 25) * 10+180;
                                    int realy = (y * 4 - 13) * 10 + 130;
                                    int realxx = (xx * 5 + 25) * 10-120;
                                    int realyy = (yy * 4 - 13) * 10 + 130;

                                    double D = Math.abs(Point2D.distance(realx, realy, realxx, realyy));
                                    if (D > 0.0000000001) {
                                        realxa = realx + 80 * ((realxx - realx) + (realyy - realy) / 2) / D;
                                        realya = realy + 80 * ((realyy - realy) - (realxx - realx) / 2) / D;
                                        realxb = realx + 80 * ((realxx - realx) - (realyy - realy) / 2) / D;
                                        realyb = realy + 80 * ((realyy - realy) + (realxx - realx) / 2) / D;
                                    }
                                    GeneralPath filledTrigon = new GeneralPath();

                                    filledTrigon.moveTo((float) realx, (float) realy);
                                    filledTrigon.lineTo((float) realxa, (float) realya);
                                    filledTrigon.moveTo((float) realx, (float) realy);
                                    filledTrigon.lineTo((float) realxb, (float) realyb);

                                    g.setStroke(new BasicStroke(15.0f));
                                    g.draw(filledTrigon);

                                    xx = x;
                                    yy = y;
                                }
                                break;
                            case EXPLOITED:
                                // 画已被攻击的BUG
                                if (xx != 0 & yy != 0) {
                                    float[] dash = {30, 30}; //短划线图案
                                    BasicStroke bs = new BasicStroke(15, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f); //实例化新画刷
                                    g.setStroke(bs); //设置新的画刷
                                    g.setColor(Color.red);

                                    g.drawLine((x * 5 + 25) * 10+180, (y * 4 - 13) * 10 + 130, (xx * 5 + 25) * 10-120, (yy * 4 - 13) * 10 + 130); //用新的画刷绘制虚线
                                    int realx = (x * 5 + 25) * 10+180;
                                    int realy = (y * 4 - 13) * 10 + 130;
                                    int realxx = (xx * 5 + 25) * 10-120;
                                    int realyy = (yy * 4 - 13) * 10 + 130;

                                    double D = Math.abs(Point2D.distance(realx, realy, realxx, realyy));
                                    if (D > 0.0000000001) {
                                        realxa = realx + 80 * ((realxx - realx) + (realyy - realy) / 2) / D;
                                        realya = realy + 80 * ((realyy - realy) - (realxx - realx) / 2) / D;
                                        realxb = realx + 80 * ((realxx - realx) - (realyy - realy) / 2) / D;
                                        realyb = realy + 80 * ((realyy - realy) + (realxx - realx) / 2) / D;
                                    }
                                    GeneralPath filledTrigon = new GeneralPath();

                                    filledTrigon.moveTo((float) realx, (float) realy);
                                    filledTrigon.lineTo((float) realxa, (float) realya);
                                    filledTrigon.moveTo((float) realx, (float) realy);
                                    filledTrigon.lineTo((float) realxb, (float) realyb);

                                    g.setStroke(new BasicStroke(15.0f));
                                    g.draw(filledTrigon);

                                    xx = x;
                                    yy = y;
                                }
                                break;
                            default:
                                break;
                        }
                    }

                    xx = x;
                    yy = y;

                }
            }
        }

        try {
            ImageIO.write(image, formatName, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}









