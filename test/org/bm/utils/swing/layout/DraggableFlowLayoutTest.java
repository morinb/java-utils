package org.bm.utils.swing.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.ButtonGroup;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class DraggableFlowLayoutTest {
    private static JDesktopPane desktopPane;

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                go();
            }
        });

    }

    protected static void go() {
        JFrame f = new JFrame("DraggableFlowLayoutTest");

        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            }
        }

        f.setLayout(new BorderLayout());
        desktopPane = new JDesktopPane();
        f.add(desktopPane, BorderLayout.CENTER);
        f.add(createPanel(DraggableFlowLayout.LEFT), BorderLayout.NORTH);
        // f.add(createPanel(DraggableFlowLayout.TOP), BorderLayout.WEST);
        // f.add(createPanel(), BorderLayout.EAST);
        // f.add(createPanel(), BorderLayout.SOUTH);

        f.setSize(785, 378);

        f.setLocationRelativeTo(null);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        f.setVisible(true);

    }

    private static Container createPanel(int type) {
        JPanel p = new JPanel(new DraggableFlowLayout(type, 4, 4));
        p.setDoubleBuffered(true);
        p.setOpaque(false);
        Color backg = p.getBackground();
        Color transparentBg = new Color((float) backg.getRed() / 255, (float) backg.getGreen() / 255,
                (float) backg.getBlue() / 255, 0.5f);
        p.setBackground(transparentBg);

        ButtonGroup bg = new ButtonGroup();

        p.add(createJButton("Button 1", bg));
        p.add(createJButton("Button 2", bg));
        p.add(createJButton("Button 3", bg));
        p.add(createJButton("Button 4", bg));
        p.add(createJButton("Button 5", bg));
        p.add(createJButton("Button 6", bg));
        p.add(createJButton("Button 7", bg));
        p.add(createJButton("Button 8", bg));
        p.add(createJButton("Button 9", bg));

        return p;
    }

    private static Component createJButton(String string, ButtonGroup bg) {
        final JToggleButton b = new JToggleButton(string);
        bg.add(b);

        return b;
    }

}
