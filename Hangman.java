import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class Hangman 
{
    static private JFrame frm;
    static private JPanel panel;
    static public Container win;
    public JFrame getfrm()
    {
        return frm;
    }
    public static void main(String [] args){
        frm = new JFrame("Hangman");
        win=frm.getContentPane();
        win.setLayout(new FlowLayout());
        MainMenu mainmenu=new MainMenu();
        panel=mainmenu.getPanel();
        win.add(panel);

        frm.setSize(800,400);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);

    }
    
    public Hangman(){
        win=win;
    }

    public Container getWin(){
        return win;
    }

}
    
   
   
  