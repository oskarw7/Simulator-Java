package App;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import Simulation.World;
import Utils.EventListener;
import Utils.FileManager;

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

        this.fileManager = new FileManager();

        addButtons(worldHeight);
        addEventArea(worldWidth);
        initMenuBar();

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
    }

    private void initMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        fileMenu.add(save);
        JMenuItem load = new JMenuItem("Load");
        fileMenu.add(load);
        menuBar.add(fileMenu);
        JMenu worldMenu = new JMenu("Select World");
        JMenuItem rectangular = new JMenuItem("Rectangular");
        worldMenu.add(rectangular);
        JMenuItem hexagonal = new JMenuItem("Hexagonal");
        worldMenu.add(hexagonal);
        menuBar.add(worldMenu);

        setJMenuBar(menuBar);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fileManager.saveWorld();
                } catch (IOException exeption) {
                    exeption.printStackTrace();
                }
            }
        });
    }


    private final int windowWidth = 1470;
    private final int windowHeight = 840;
    private int fieldSize;
    private World world;
    private WorldGUI gui;
    private FileManager fileManager;
    private JButton turnButton;
    private JTextArea eventArea;
}
