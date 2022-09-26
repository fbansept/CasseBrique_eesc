package edu.fbansept.cassebrique;

import javax.swing.*;
import java.awt.*;

public class CasseBrique extends Canvas {

    protected int largeurEcran = 500;
    protected int hauteurEcran = 700;

    public CasseBrique() throws InterruptedException {
        JFrame fenetre = new JFrame("Casse brique");
        //On récupère le panneau de la fenetre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();
        //On définie la hauteur / largeur de l'écran
        panneau.setPreferredSize(new Dimension(500, 500));
        setBounds(0, 0, 500,500);
        //On ajoute cette classe (qui hérite de Canvas) comme composant du panneau principal
        panneau.add(this);

        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.requestFocus();

        //On indique que le raffraichissement de l'ecran doit être fait manuellement.
        createBufferStrategy(2);
        setIgnoreRepaint(true);
        this.setFocusable(false);

        demarrer();
    }

    public void demarrer() throws InterruptedException {

        long indexFrame = 0;
        Balle balle = new Balle(
                250,
                250,
                4,
                -6,
                30,
                Color.GREEN);

        while(true) {
            indexFrame ++;
            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            //-----------------------------
            //reset dessin
            dessin.setColor(Color.WHITE);
            dessin.fillRect(0,0,500,500);

            //dessin balle
            balle.deplacer();
            balle.dessiner(dessin);

            //mouvement balle
            if(balle.getX() < 0 || balle.getX() > 500 - balle.getDiametre()) {
                balle.inverseVitesseHorizontal();
            }

            if(balle.getY() < 0 || balle.getY() > 500 - balle.getDiametre()) {
                balle.inverseVitesseVertical();
            }

            //-----------------------------
            dessin.dispose();
            getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new CasseBrique();
    }
}
