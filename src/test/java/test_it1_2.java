/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.ArrayList;
import modele.Bibliotheque;
import vue.IHM;
/**
 *
 * @author hauraytheo
 */
public class test_it1_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Bibliotheque bibliotheque = new Bibliotheque();
        IHM ihm = new IHM(bibliotheque);
        
        ArrayList<String> liste = new ArrayList<String>();
        liste.add("asasas");
        liste.add("ababba");
        liste.add("abasbba");
        liste.add("abaqqqbba");

        String nISBN = ihm.saisirISBNExiste(liste);
    }
    
}
