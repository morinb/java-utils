package org.bm.utils.swing;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class CloseableTabTest {

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
        JFrame f = new JFrame("CloseableTabTest");
        
        JTabbedPane tp = new JTabbedPane();
        for(int i=0 ; i < 11 ; i++) {
            addTab(tp, i);
        }
        f.add(tp);
        
        f.setSize(800, 600);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private static void addTab(JTabbedPane tp, int i) {
        tp.addTab("Tab n°"+i, new JScrollPane(new JTextArea()));
        tp.setTabComponentAt(i, new CloseableTab(tp));
    }

}
