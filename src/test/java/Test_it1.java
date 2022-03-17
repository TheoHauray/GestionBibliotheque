
import java.io.IOException;
import modele.Bibliotheque;
import util.Persisteur;
import vue.IHM;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author haurayt
 */
public class Test_it1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Bibliotheque bibliotheque = new Bibliotheque();

    try {
        bibliotheque = Persisteur.lireEtat();
    } catch (ClassNotFoundException | IOException ignored) {
        System.err.println("Erreur irrécupérable pendant le chargement de l'état : fin d'exécution !");
        System.err.flush();
        System.exit(Main.EXIT_ERR_LOAD);
    }
    
    IHM ihm = new IHM(bibliotheque);
    ihm.afficherInterface();

    try {
        Persisteur.sauverEtat(bibliotheque);
    } catch (IOException ignored) {
        System.err.println("Erreur irrécupérable pendant la sauvegarde de l'état : fin d'exécution !");
        System.err.flush();
        System.exit(Main.EXIT_ERR_SAVE);
    }
    }
    
}
