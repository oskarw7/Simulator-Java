package App;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Simulation.World;
import Utils.EventListener;

public class App extends JFrame {
    public App(int worldWidth, int worldHeight){
        this.setSize(windowWidth, windowHeight);
        countFieldSize(worldHeight);
        this.setMinimumSize(new Dimension(fieldSize*worldWidth, fieldSize*worldHeight));
        this.setTitle("Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.darkGray);
        this.setVisible(true);
        this.setLayout(null);

        this.world = World.getWorld(worldWidth, worldHeight, "Rectangular");

        this.gui = new WorldGUI(fieldSize*worldWidth, fieldSize*worldHeight);
        this.add(gui);
        gui.paint(gui.getGraphics());

        addButtons(worldHeight);
        addEventArea(worldWidth);

        revalidate();
        repaint();
    }

    private void countFieldSize(int worldHeight){
        if(worldHeight<=20)
            fieldSize = 30;
        else if(worldHeight<=35)
            fieldSize = 20;
        else if(worldHeight<=70)
            fieldSize = 10;
        else if(worldHeight<=100)
            fieldSize = 7;
        else
            fieldSize = 5;
    }

    private void addButtons(int worldHeight){
        turnButton = new JButton("Next turn");
        turnButton.setBounds(10, fieldSize*worldHeight+10, 200, 50);
        this.add(turnButton);

        turnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.getEventListener().clearEvents();
                gui.newTurn();
                showEvents();
            }
        });
    }

    private void addEventArea(int worldWidth){
        eventArea = new JTextArea();
        eventArea.setEditable(false);
        eventArea.setLineWrap(true);
        eventArea.setWrapStyleWord(true);
        eventArea.setBounds(fieldSize*worldWidth+10, 0, windowWidth-fieldSize*worldWidth-10, windowHeight);
        this.getContentPane().add(eventArea);
    }

    private void showEvents(){
        eventArea.setText("");
        EventListener eventListener = world.getEventListener();
        ArrayList<String> events = eventListener.getEvents();
        for(String event : events){
            eventArea.append("  " + event + "\n");
        }
        //eventArea.print(this.getGraphics());
    }


    private final int windowWidth = 1470;
    private final int windowHeight = 840;
    private int fieldSize;
    private World world;
    private WorldGUI gui;
    private JButton turnButton;
    private JTextArea eventArea;
}
