import java.awt.*;
import javax.swing.*;

public class loo {

    public loo() {
        // Panel
        JPanel p = new JPanel();

        // Frame
        JFrame f = new JFrame();

        p.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        p.setLayout(new GridLayout());

        f.add(p, BorderLayout.CENTER);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Places in 'Loo");
        f.pack();
        f.setVisible(true);
    }

    public static void main(String args[]) {
        new loo();

    }
}