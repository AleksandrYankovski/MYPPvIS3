package view;

import controller.DataController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

/**
 * Created by Александр on 28.05.2017.
 */
public class MainFrame implements ActionListener {

    private JFrame mainFrame;
    private JTable table;
    private DekartSystem dekartSystem;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JToolBar toolBar;
    private JButton calculate;
    private JLabel scaleLabel;
    private int percantage;
    private DataController controller;
    private final int delta = 45;

    public MainFrame(DataController controller) {
        this.controller = controller;
        mainFrame = new JFrame("Лабораторная работа №3");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(initTable(), BorderLayout.WEST);
        mainFrame.add(initToolBar(), BorderLayout.SOUTH);
        mainFrame.add(initDekarkSystem());
        mainFrame.setVisible(true);


    }


    private JScrollPane initTable() {
        String[] columnNames = {"количество элементов в массиве", "время сортировки"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        return scrollPane;
    }

    private JToolBar initToolBar() {
        toolBar = new JToolBar();
        toolBar.setPreferredSize(new Dimension(mainFrame.getWidth(), 33));
        Box buttonsBox = Box.createHorizontalBox();
        toolBar.setLayout(new FlowLayout());
        this.percantage = 100;
        scaleLabel = new JLabel("масштаб:" + percantage + "%");
        calculate = new JButton("построить график");
        calculate.addActionListener(this);
        toolBar.add(calculate);
        toolBar.add(scaleLabel);
        toolBar.add(buttonsBox);
        return toolBar;
    }

    private JScrollPane initDekarkSystem() {
        dekartSystem = new DekartSystem();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(dekartSystem);
        dekartSystem.addMouseWheelListener(new MouseAdapter() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                int currentScale = dekartSystem.getScalingPercentage();
                if (e.getPreciseWheelRotation() < 0) {
                    if (currentScale > 50) {
                        dekartSystem.setScalingPercentage(currentScale - 5);
                        dekartSystem.setPreferredSize(new Dimension(dekartSystem.getWidth() - delta, dekartSystem.getHeight() - delta));
                        scaleLabel.setText("масштаб:" + (currentScale - 5) + "%");
                    }
                } else {
                    if (currentScale < 250 && currentScale >= 100) {

                        dekartSystem.setScalingPercentage(currentScale + 5);
                        dekartSystem.setPreferredSize(new Dimension(dekartSystem.getWidth() + delta, dekartSystem.getHeight() + delta));
                        scaleLabel.setText("масштаб:" + (currentScale + 5) + "%");
                    }
                    if (currentScale  >= 50 && currentScale < 100) {
                        dekartSystem.setScalingPercentage(currentScale + 5);
                        scaleLabel.setText("масштаб:" + (currentScale + 5) + "%");
                    }
                }

                dekartSystem.revalidate();
                dekartSystem.repaint();


            }
        });

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        return scrollPane;

    }


    public DekartSystem getDekartSystem() {
        return dekartSystem;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("построить график")) {
            new DialogParam(controller);
            Thread thread = new Thread(controller);
            thread.start();
        }


    }


}


