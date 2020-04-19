package me.cyberghost.zooManagerProject.Gui;

import me.cyberghost.zooManagerProject.Managers.Animal;
import me.cyberghost.zooManagerProject.Utilities.utils;
import me.cyberghost.zooManagerProject.zooManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiPanel extends JPanel {
    private JLabel title;
    private JLabel title2;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField textbox1;
    private JTextField textbox2;
    private JTextField textbox3;
    private JTextField textbox4;
    private JTextField textbox5;
    private JButton button;
    public GuiPanel() {


        this.setBackground(Color.DARK_GRAY);
        //construct components
        title = new JLabel("ZooManager");
        title.setForeground(Color.white);
        title2 = new JLabel("Create Section");
        title2.setForeground(Color.white);
        Font f = new Font("Arial",3,25);
        Font f3 = new Font("Arial",Font.BOLD,12);
        Font f2 = new Font("Arial",3,15);
        title.setFont(f);
        label1 = new JLabel("Animal Name");
        label1.setForeground(Color.white);
        label2 = new JLabel("Animal Password");
        label2.setForeground(Color.white);
        label3 = new JLabel("Animal Weight");
        label3.setForeground(Color.white);
        label4 = new JLabel("Animal Max Age");
        label4.setForeground(Color.white);
        label5 = new JLabel("Animal Class");
        label5.setForeground(Color.white);
        title2.setFont(f2);
        label1.setFont(f3);
        label2.setFont(f3);
        label3.setFont(f3);
        label4.setFont(f3);
        label5.setFont(f3);
        textbox1 = new JTextField(5);
        textbox2 = new JTextField(5);
        textbox3 = new JTextField(5);
        textbox4 = new JTextField(5);
        textbox5 = new JTextField(5);
        button = new JButton("SaveAnimal");

        //adjust size and set layout
        this.setPreferredSize(new Dimension(500, 600));
        setLayout(null);

        //add components
        add(title);
        add(title2);

        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(textbox1);
        add(textbox2);
        add(textbox3);
        add(textbox4);
        add(textbox5);
        add(button);

        //set component bounds (only needed by Absolute Positioning)
        title.setBounds(180, -20, 220, 95);
        title2.setBounds(190, 0, 220, 95);

        label1.setBounds(50, 100, 100, 25);
        label2.setBounds(50, 150, 100, 25);
        label3.setBounds(50, 200, 100, 25);
        label4.setBounds(50, 250, 100, 25);
        label5.setBounds(50, 300, 300, 25);
        textbox1.setBounds(180, 100, 180, 30);
        textbox2.setBounds(180, 150, 180, 30);
        textbox3.setBounds(180, 200, 180, 30);
        textbox4.setBounds(180, 250, 180, 30);
        textbox5.setBounds(180, 300, 180, 30);
        button.setBounds(190, 350, 123, 49);




        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               if(!utils.isInteger(textbox2.getText()) || utils.isPasswordUsed(textbox2.getText()) || textbox2.getText().length()<1){
                   JOptionPane.showMessageDialog(null, "Password is Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }
                if(!utils.isDouble(textbox3.getText()) || textbox3.getText().length()<1){
                    JOptionPane.showMessageDialog(null, "Weight is Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!utils.isDouble(textbox4.getText()) || textbox4.getText().length()<1){
                    JOptionPane.showMessageDialog(null, "Age is Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!utils.isAnimalClassValid(textbox5.getText()) || textbox5.getText().length()<1){
                    JOptionPane.showMessageDialog(null, "Class is Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save the following animal\n\nAnimal Name: "+textbox1.getText()
                        +"\nAnimal Password: "+textbox2.getText()+"\nAnimal Weight: "+textbox3.getText()
                        +"\nAnimal Max Age: "+textbox4.getText() +"\nAnimal Class: "+textbox5.getText(),"CONFRIM",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    zooManager.animals.put(zooManager.animalUUID++,new Animal(Integer.parseInt(textbox2.getText()),textbox1.getText(),textbox5.getText(),Double.parseDouble(textbox3.getText()),Double.parseDouble(textbox4.getText()) ));
                    JOptionPane.showMessageDialog(null,"Animal Saved Successfully!","Saved",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
}
