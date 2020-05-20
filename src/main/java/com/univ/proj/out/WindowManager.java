package com.univ.proj.out;

import com.univ.proj.util.collect.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Roman Anokhin
 */
public class WindowManager extends JFrame{
    private JPanel pnlMain;
    private JButton btnExecute;
    private JButton btnShow;
    private JTextField vertOne;
    private JTextField vertTwo;
    private JPanel pnlVertex;

    PnlDraw pnlDraw;
    public WindowManager(){
        super("Task 7");
        super.setBounds(300,100,1500,800);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        JPanel pnlUnderMain = new JPanel();
        pnlUnderMain.setLayout(new BoxLayout(pnlUnderMain, BoxLayout.X_AXIS));
        final JPanel pnlMange = new JPanel();
        final JTextArea textArea = new JTextArea();
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setText("Graph s{\n " +
                "a--b[-0.7];\n" +
                "v -- b[0.5];\n" +
                "x --a[0.1];\n" +
                "d -- v[0.23];\n" +
                "d -- a[0.2];\n" +
                "}");
        textArea.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        textArea.setSize(new Dimension(600,800));
        textArea.setMaximumSize(new Dimension(600,800));
        pnlMange.setLayout(new BoxLayout(pnlMange, BoxLayout.Y_AXIS));
        pnlMange.setSize(new Dimension(600,800));
        pnlMange.setMaximumSize(new Dimension(600,800));
        pnlMange.add(textArea);
        pnlMange.add(btnShow);
        pnlDraw = new PnlDraw();
        pnlUnderMain.add(pnlDraw);
        pnlUnderMain.add(pnlMange);

        pnlMain.add(pnlUnderMain);
        pnlVertex.setMaximumSize(new Dimension(500,100));
        pnlMain.add(pnlVertex);
        pnlMain.add(btnExecute);
        super.add(pnlMain);

        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnlDraw.refresh();
                pnlDraw.setVertexGreen(vertOne.getText());
                pnlDraw.setVertexRed(vertTwo.getText());
                pnlDraw.drawGraph(textArea.getText());
            }
        });

        btnExecute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pnlDraw.refresh();
                pnlDraw.setVertexGreen(vertOne.getText());
                pnlDraw.setVertexRed(vertTwo.getText());
                pnlDraw.drawGraphWithColor(textArea.getText());
            }
        });
    }
}

