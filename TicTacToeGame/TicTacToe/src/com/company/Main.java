package com.company;


import java.awt.EventQueue;

import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    new TwoPlayerGame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
