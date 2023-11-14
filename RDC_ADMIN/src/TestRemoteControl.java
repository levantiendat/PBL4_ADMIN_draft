import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class TestRemoteControl extends JFrame {

    // TODO: Rename class later..

    public volatile BufferedImage screen, resizeScreen;


    public TestRemoteControl(String key, String targetIP) throws Exception {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1000, 700);
        setResizable(false);
        setVisible(true);
        setBackground(Color.black);
        Thread remoteControlHandler = new Thread(new RemoteControlHandler(key, targetIP, this));
        remoteControlHandler.start();

    }
    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (screen != null){
            resizeScreen = resizeImage(screen, 800, 500);
            g.drawImage(resizeScreen, 100, 100, resizeScreen.getWidth(), resizeScreen.getHeight(), null);
        }
    }

}
