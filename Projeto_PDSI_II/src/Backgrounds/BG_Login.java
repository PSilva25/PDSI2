package Backgrounds;

import Backgrounds.Background_Principal;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BG_Login extends JPanel{
    
    public void paintComponent(Graphics g){
    
        Dimension t = getSize();

        ImageIcon img = new ImageIcon(new ImageIcon(getClass().getResource("/Imagens/Login2.png")).getImage());

        g.drawImage (img.getImage(), 0, 0, t.width, t.height, null);
    
    }
    
    public static void main(String[]args){
        
        new BG_Login();
        
    }
    
}
