package com.xiaoming.unit16;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by panxiaoming on 15/12/23.
 */
public class MySwingApp extends JFrame {
    JButton exitButton = new JButton();
    JTextArea jTextArea = new JTextArea();
    String dir = System.getProperty("user.dir");
    String fileName = "temp.txt";
    MyShutdownHook shutdownHook = new MyShutdownHook();

    public MySwingApp() {
        exitButton.setText("Exit");
        exitButton.setBounds(new Rectangle(304, 248, 76, 37));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButton_actionPerformed(e);
            }
        });
        this.getContentPane().setLayout(null);
        jTextArea.setText("Click the Exit button to quit");
        jTextArea.setBounds(new Rectangle(9, 7, 371, 235));
        this.getContentPane().add(exitButton, null);
        this.getContentPane().add(jTextArea, null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(0, 0, 400, 330);
        this.setVisible(true);
        initialize();
    }

    private void initialize() {
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        File file = new File(dir, fileName);
        try {
            System.out.println("Creating temporary file");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void shutdown() {
        File file = new File(dir, fileName);
        if(file.exists()) {
            System.out.println("Deleting temporary file.");
            file.delete();
        }
    }

    private void exitButton_actionPerformed(ActionEvent e) {
        shutdown();
        Runtime.getRuntime().removeShutdownHook(shutdownHook);
        System.exit(0);
    }

    public static void main(String[] args) {
        MySwingApp mySwingApp = new MySwingApp();
    }

    private class MyShutdownHook extends Thread {
        public void run() {
            shutdown();
        }
    }
}
