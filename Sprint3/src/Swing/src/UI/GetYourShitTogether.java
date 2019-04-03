package UI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

public class GetYourShitTogether extends JPanel {
    public GetYourShitTogether(){
        super(new GridLayout(1,1));

        JTabbedPane tabbedPane=new JTabbedPane();
        

        MediaPage movies=new MediaPage();
        movies.reccomended=new CardList();
        movies.backlog=new CardList();
        movies.completed=new CardList();
        movies.populate();

        ImageIcon movieIcon = createImageIcon("/images/movie.png");
        tabbedPane.addTab("Movies", movieIcon,movies);

        MediaPage tv=new MediaPage();
        tv.reccomended=new CardList();
        tv.backlog=new CardList();
        tv.completed=new CardList();
        tv.populate();

        ImageIcon tvIcon = createImageIcon("/images/tv.png");
        tabbedPane.addTab("TV", tvIcon, tv);

        MediaPage anime=new MediaPage();
        anime.reccomended=new CardList();
        anime.backlog=new CardList();
        anime.completed=new CardList();
        anime.populate();
        

        ImageIcon animeIcon = createImageIcon("/images/anime.png");
        tabbedPane.addTab("Anime", animeIcon, anime);
        
        MediaPage games=new MediaPage();
        games.reccomended=new CardList();
        games.backlog=new CardList();
        games.completed=new CardList();
        games.populate();

        ImageIcon gameIcon = createImageIcon("/images/game.png");
        tabbedPane.addTab("Games", gameIcon, games);

        add(tabbedPane);



    }
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = GetYourShitTogether.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GYST");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new GetYourShitTogether(), BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                createAndShowGUI();
            }
        });
    }

}
