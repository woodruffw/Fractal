/* FractalViewer.java    Author: William Woodruff
 * Provides a simple, user-friendly GUI for the Fractal class
 * Coded 3/18/13
 */

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class FractalViewer
{
  //Fractal instance with deafult values for size & scale
  private Fractal mand = new Fractal(512, 10, 3);
  
  //gui components
  private JFrame frame = new JFrame("Fractal Viewer");
  
  private JMenuBar menuBar = new JMenuBar();
  private JMenu fileMenu = new JMenu("File");
  private JMenuItem infoItem = new JMenuItem("Info...");
  private JMenuItem quitItem = new JMenuItem("Quit");
  private JPanel argsPanel = new JPanel();
  private JPanel viewPanel = new JPanel();
  
  private JButton argsButton = new JButton("Enter arguments");
  private JButton viewButton = new JButton("View");
  
  //constructor
  public FractalViewer()
  {
    //jmenubar and subcomponents added
    frame.setJMenuBar(menuBar);
    
    fileMenu.add(infoItem);
    fileMenu.add(quitItem);
    
    menuBar.add(fileMenu);
    
    //buttons added to respective panels
    argsPanel.add(argsButton);
    viewPanel.add(viewButton);
    
    //layout and buttons organized
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(argsPanel, BorderLayout.EAST);
    frame.getContentPane().add(viewPanel, BorderLayout.WEST);
    
    //AL instantiated and added to components
    ListenActions la = new ListenActions();
    
    infoItem.addActionListener(la);
    quitItem.addActionListener(la);
    
    argsButton.addActionListener(la);
    viewButton.addActionListener(la);
  }
  
  //disp 
  //prepares the GUI for use
  public void disp()
  {
    //attempts to make the UI OS-native
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    //crazy exceptions
    catch (ClassNotFoundException e) {}
    catch (InstantiationException e) {}
    catch (IllegalAccessException e) {}
    catch (UnsupportedLookAndFeelException e) {}
    //defaults
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 100);
    frame.setResizable(false);
    frame.setVisible(true);
  }
  
  //main
  public static void main(String[] args)
  {
    FractalViewer mv = new FractalViewer();
    mv.disp();
  }
  
  //class ListenActions
  //listens for actions upon various GUI components
  public class ListenActions implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      //action for 'Info' in the MenuBar
      if (e.getSource().equals(infoItem))
      {
        //information pop-up
        JOptionPane.showMessageDialog(null, "This program recursively calculates valid points in the Fractal and Julia sets.\n"
                                        + "The shape and detail of the fractal are defined by its arguments under \'Enter arguments\'.\n"
                                        + "A low scale and/or high complexity may cause long loading times, so please exercise patience.\n"
                                        + "If a pixel on the mapped image is pink, it belongs to the set.", "Information",
                                      JOptionPane.INFORMATION_MESSAGE);
      }
      
      //action for 'Quit' in the MenuBar
      else if (e.getSource().equals(quitItem))
      {
        //user offered a second chance before program termination
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?");
        if (choice == JOptionPane.YES_OPTION)
          System.exit(0); //program termination
      }
      
      //action for 'Enter Arguments' JButton
      else if (e.getSource().equals(argsButton))
      {
        try
        {
          //two JTextFields instantiated within an array
          JTextField[] fields = 
          {
            new JTextField("Image size..."),
            new JTextField("Image scale..."),
            new JTextField("Fractal complexity...")
          };
          JOptionPane inputPane = new JOptionPane(fields); //JOP containing JTFs
          JDialog dialog = inputPane.createDialog(null, "Enter arguments:");
          dialog.setVisible(true); //JOP made visible
          //text from each JTF parsed
          int size = Integer.parseInt(fields[0].getText());
          int scale = Integer.parseInt(fields[1].getText());
          int complexity = Integer.parseInt(fields[2].getText());
          //Fractal instantiated
          mand = new Fractal(size, scale, complexity);
        }
        catch (NumberFormatException nfe)
        {
          JOptionPane.showMessageDialog(null, "Error: Input cannot be parsed. Enter only valid data.", "NFE",
                                        JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalArgumentException iae)
        {
          JOptionPane.showMessageDialog(null, "Error: Input does not match required arguments.", "IAE",
                                        JOptionPane.ERROR_MESSAGE);
        }
      }
      
      //action for 'View' JButton
      else if (e.getSource().equals(viewButton))
      {
        if (mand == null)
          JOptionPane.showMessageDialog(null, "Error: Fractal cannot be constructed without arguments.", 
                                        "Error: No Arguments", JOptionPane.ERROR_MESSAGE);
        else
        {
          //a new frame is created and displayed to hold the image
          JFrame imgFrame = new JFrame("Image");
          
          //creates a JMB with a single option for saving the image
          JMenuBar imgMB = new JMenuBar();
          JMenu imgMenu = new JMenu("File...");
          JMenuItem imgSaveItem = new JMenuItem("Save...");
          imgMB.add(imgMenu);
          imgMenu.add(imgSaveItem);
          imgFrame.setJMenuBar(imgMB);
          imgFrame.setContentPane(new JLabel(new ImageIcon(mand.draw()))); //draws the image onto the screen
          //listens for an action on the "Save..." JMI
          imgSaveItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              JFileChooser saveDialog = new JFileChooser();
              saveDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
              JOptionPane.showMessageDialog(null, "Make sure to end your filename with \'.jpg\' or \'.png\'.");
              int ret = saveDialog.showSaveDialog(null);
              
              if (ret == JFileChooser.APPROVE_OPTION)
              {
                try
                {
                  //writes the image to a user selected location
                  ImageIO.write(mand.getImage(), "png", saveDialog.getSelectedFile());
                }
                catch (IOException ioe) 
                {
                  JOptionPane.showMessageDialog(null, "Error: Image cannot be created.", 
                                                "IO", JOptionPane.ERROR_MESSAGE);
                }
              }
            }});
          
          //prepares the frame for use
          imgFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          imgFrame.setSize(mand.getSize(), mand.getSize());
          imgFrame.setResizable(false);
          imgFrame.pack();
          imgFrame.setVisible(true);
        }
      }
    }
  } //ListenActions ends
  
} //FractalViewer.java ends