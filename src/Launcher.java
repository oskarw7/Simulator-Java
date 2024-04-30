import javax.swing.JOptionPane;

import App.App;

public class Launcher {
    public static void main(String[] args) {
        /* //DEBUG
        String widthString = JOptionPane.showInputDialog("Enter world width:");
        String heightString = JOptionPane.showInputDialog("Enter world height:");
        if(widthString == null || heightString == null)
            System.exit(1);
        try{
            int width = Integer.parseInt(widthString);
            int height = Integer.parseInt(heightString);
            App app = new App(width, height);
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        */ //DEBUG
        App app = new App(20, 20);
    }
}