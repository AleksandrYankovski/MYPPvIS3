package view;

import controller.DataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Александр on 28.05.2017.
 */
public class DialogParam implements ActionListener {

    private JDialog dialog;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton buttonOk;
    private DataController controller;

    public DialogParam(DataController controller) {
        this.controller = controller;
        dialog = new JDialog();
        dialog.setLocation(463,235);
        dialog.setTitle("Ввод");
        dialog.setModal(true);
        dialog.setContentPane(components());
        dialog.pack();
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK")) {
            try {
                Long kolMass = Long.valueOf(textField1.getText());
                Integer kolElement = Integer.valueOf(textField2.getText());
                controller.start(kolMass, kolElement);
                dialog.dispose();
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(dialog, "не корректные данные");
            }

        }

    }

    private JPanel components() {
        JPanel mainpanel = new JPanel();
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(2, 2, 6, 12));

        label1 = new JLabel("Кол-во массивов");
        label2 = new JLabel("Макс. кол-во элементов в массиве");

        textField1 = new JTextField(10);
        textField2 = new JTextField(10);

        buttonOk = new JButton("OK");
        buttonOk.addActionListener(this);

        fields.add(label1);
        fields.add(textField1);
        fields.add(label2);
        fields.add(textField2);

        mainpanel.add(fields);
        mainpanel.add(buttonOk, BorderLayout.SOUTH);
        mainpanel.setPreferredSize(new Dimension(460, 90));
        return mainpanel;
    }

}
