/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;
import modele.PublicVise;
import java.util.Date;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.util.Scanner;
import java.util.ArrayList;
import modele.Bibliotheque;
import util.ES;
import util.Commande;
/**
 *
 * @author jaghdamm
 */
public class IHM {
    
    
private final Bibliotheque bibliotheque;

public IHM(Bibliotheque bibliotheque) 
{
    this.bibliotheque = bibliotheque;
}

//-----  affichage menu et saisie des commandes par l'utilisateur  -------------------------------------------------

/**
* afficherInterface permet l'affichage du menu et le choix d'une commande
*par l'utilisateur (dialogueSaisirCommande) puis l'invocation de la méthode
*de la classe Bibliotheque réalisant l'action  (gererDialogue)
*/

public void afficherInterface() {
    Commande cmd;
    do {
        cmd = this.dialogueSaisirCommande();
        this.gererDialogue(cmd);
    } while (cmd != Commande.QUITTER);
}

private Commande dialogueSaisirCommande() {
    ES.afficherTitre("===== Bibliotheque =====");
    ES.afficherLibelle(Commande.synopsisCommandes());
    ES.afficherLibelle("===============================================");
    ES.afficherLibelle("Saisir l'identifiant de l'action choisie :");
    return Commande.lireCommande();
}

private void gererDialogue(Commande cmd) {
    switch (cmd) {
        case QUITTER:
            break;
        case CREER_LECTEUR:
            bibliotheque.nouveauLecteur(this);
            break;
        case CONSULTER_LECTEURS:
            bibliotheque.consulterLecteur(this);
            break;
        case CREER_OUVRAGE:
            bibliotheque.nouvelOuvrage(this);
            break;
        case AFFICHER_OUVRAGE:
            bibliotheque.consulterOuvrage(this);
            break;
        case CREER_EXEMPLAIRE:
            bibliotheque.nouvelExemplaire(this);
            break;
        case AFFICHER_EXEMPLAIRE:
            bibliotheque.consulterExemplaireOuvrage(this);
            break;
        default:
            assert false : "Commande inconnue.";
    }
}

public void afficher(String message){
    System.out.println(message);
}

public void afficherLecteur(String nom, String prenom, int numero, LocalDate dateDeNaissance, String adresse, String email){
    ES.afficherNom(nom);
    ES.afficherPrenom(prenom);
    ES.afficherNumero(numero);
    ES.afficherDate(dateDeNaissance);
    ES.afficherAddresse(adresse);
    ES.afficherEmail(email);
}

public void afficherExemplaire(int numero, LocalDate dateDeReception, boolean empruntable){
    ES.afficherNumero(numero);
    ES.afficherDate(dateDeReception);
    ES.afficherBool(empruntable);
}

public void afficheOuvrage(String nISBN, String titre, ArrayList<String> nomAuteurs, String nomEditeur, LocalDate dateParution){
    ES.afficherISBN(nISBN);
    ES.afficherTitre(titre);
    ES.afficherAuteurs(nomAuteurs);
    ES.afficherEditeur(nomEditeur);
    ES.afficherDate(dateParution);
} 

public boolean existeISBN(ArrayList<String> numsISBN, String numISBN){
  
    for (String s: numsISBN){
       
        if (s.equals(numISBN)){
               return true;    
        }
    }
    return false;
}

public boolean existeLecteur(ArrayList<Integer> numsLecteur, int numLecteur){
  
    for (int s: numsLecteur){
       
        if (s == numLecteur){
               return true;    
        }
    }
    return false;
}

//Utiliser sc.nextInt au lieu de sc.nextLine
public String saisirISBNnonExiste(ArrayList<String> numsISBN){
    System.out.println("Entrez le Numéro ISBN:");
    Scanner sc= new Scanner(System.in);
    String n = sc.nextLine();
    while(n !="0" && existeISBN(numsISBN,n)){
        System.out.println("Le n° existe déjà, entrez un nouveau numéro ISBN:");
        n = sc.nextLine();
    } 
   return n;
}

public String saisirISBNExiste(ArrayList<String> numsISBN){
   String n = "";
   while(n !="0" && !existeISBN(numsISBN,n)){
        Scanner sc= new Scanner(System.in);
        System.out.println("Entrez le Numéro ISBN:");
        n = sc.nextLine();
    } 
   return n;
}

public int saisirLecteurExiste(ArrayList<Integer> numsLecteur){
   int n = 0;
   while(n !=0 && !existeLecteur(numsLecteur,n)){
        Scanner sc= new Scanner(System.in);
        System.out.println("Entrez le Numéro de Lecteur:");
        n = sc.nextInt();
    } 
   return n;
}

public InfosOuvrage saisirOuvrage()
{
    String titre, nomEditeur;
    LocalDate dateParution;
    ArrayList<String> auteurs = new ArrayList<String>();
    PublicVise publicVise = null;
    Scanner sc= new Scanner(System.in);
    int test = 1;
    int nPublic = 0;
    
    ES.afficherTitre("Saisir les informations de l'ouvrage");
    titre = ES.lireChaine("- Titre de l'ouvrage");
    dateParution = ES.lireDate("- Date de parution");

    while(dateParution.isAfter(now()))
    {
        dateParution = ES.lireDate("-- La date de parution doit être antérieur à la date du jour.");
    }
    nomEditeur = ES.lireChaine("- Nom de l'éditeur");
    
    while(test != 0)
    {
        auteurs.add(ES.lireChaine("- Entrer le nom d'un auteur"));
        afficher("Il y a-t-il un autre auteur ? - Si non, entrez 0");
        test = sc.nextInt();
    }
    
    test = 1;
    afficher("- Entrer 1 pour adulte, 2 pour ado, 3 pour enfant");
    
    while(test != 0)
    {
        nPublic = sc.nextInt(); 
        if(nPublic != 1 && nPublic != 2 && nPublic != 3)
        {
            afficher("-- Le numéro doit être 1, 2 ou 3");
        }
        else 
        {
            test = 0;
        }
    }
    
    switch (nPublic) 
    {
        case 1:
            publicVise = PublicVise.ADULTE;
            break;
        case 2:
            publicVise = PublicVise.ADO;
            break;
        case 3:
            publicVise = PublicVise.ENFANT;
            break;
    }
    
    InfosOuvrage infosOuvrage = new InfosOuvrage(titre, dateParution, nomEditeur, auteurs, publicVise);
    
    return infosOuvrage;
}

public InfosExemplaire saisirExemplaire(LocalDate dateParution)
{
    LocalDate dateReception;
    boolean empruntable;
    
    ES.afficherTitre("Saisir les informations de l'exemplaire");
    dateReception = ES.lireDate("- Date de réception");
    
    while(dateReception.isBefore(dateParution))
    {
        dateReception = ES.lireDate("-- La date de réception doit être postérieure à la date de parution.");
    }
    
    empruntable = ES.lireBoolean("- L'exemplaire est-il empruntable ?");
    InfosExemplaire infosExemplaire = new InfosExemplaire(dateReception, empruntable);
    
    return infosExemplaire;
}

public InfosLecteur saisirLecteur(int nLecteur)
{
    String nom, prenom, adresse, email;
    LocalDate dateDeNaissance;

    ES.afficherTitre("Saisir les informations du lecteur");
    nom = ES.lireChaine("- Nom : ");
    prenom = ES.lireChaine("- Prenom : ");
    adresse = ES.lireChaine("- Adresse : ");
    email = ES.lireEmail("- Email : ");
    
    dateDeNaissance = ES.lireDate("- Date de naissance : ");
    
    while(dateDeNaissance.isAfter(now()))
    {
        dateDeNaissance = ES.lireDate("-- La date de réception doit être antérieure à la date de parution.");
    }
    
    InfosLecteur infosLecteur = new InfosLecteur(nom, prenom, dateDeNaissance, adresse, email);
    
    return infosLecteur;
}

public static class InfosOuvrage {
    public final String titre;
    public final LocalDate dateParution;
    public final String nomEditeur;
    public final ArrayList<String> auteurs;
    public final PublicVise publicVise;
     
    public InfosOuvrage(final String titre, final LocalDate dateParution, final String nomEditeur, final ArrayList<String> auteurs, final PublicVise publicVise){
        this.titre = titre;
        this.dateParution = dateParution;
        this.nomEditeur = nomEditeur;
        this.auteurs = auteurs;
        this.publicVise = publicVise;
    }
}

public static class InfosExemplaire {
    public final LocalDate dateReception;
    public final Boolean empruntable;

     
    public InfosExemplaire(final LocalDate dateReception, final Boolean empruntable){
        this.dateReception = dateReception;
        this.empruntable = empruntable;
    }
}
 
public static class InfosLecteur {
    public final String nom;
    public final String prenom;
    public final LocalDate dateDeNaissance;
    public final String adresse;
    public final String email;
     
    public InfosLecteur(final String nom, final String prenom, final LocalDate dateDeNaissance, final String adresse, final String email){
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.email = email;
    }
}
}


