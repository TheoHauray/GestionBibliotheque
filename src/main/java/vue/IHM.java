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
        case EMPRUNTER_EXEMPLAIRE:
            bibliotheque.emprunterExemplaire(this);
            break;
        case RENDRE_EXEMPLAIRE:
            bibliotheque.rendreExemplaire(this);
            break;
        case CONSULTER_EMPRUNT_LECTEUR:
            bibliotheque.consulterEmpruntsLecteur(this);
            break;
        default:
            assert false : "Commande inconnue.";
    }
}

public void afficherLecteur(String nom, String prenom, int numero, LocalDate dateDeNaissance, String adresse, String email){
    ES.afficherTexte("Informations du lecteur : ");
    ES.afficherNom(nom);
    ES.afficherPrenom(prenom);
    ES.afficherNumero(numero);
    ES.afficherDate("Date de naissance : ",dateDeNaissance);
    ES.afficherAddresse(adresse);
    ES.afficherEmail(email);
}

public void afficherExemplaire(int numero, LocalDate dateDeReception, boolean empruntable){
    ES.afficherTexte("Informations de l'exemplaire : ");
    ES.afficherNumero(numero);
    ES.afficherDate("Date de réception : ",dateDeReception);
    ES.afficherBool("Empruntable : ", empruntable);
}

public void afficheOuvrage(String nISBN, String titre, ArrayList<String> nomAuteurs, String nomEditeur, LocalDate dateParution){
    ES.afficherTexte("Informations de l'ouvrage : ");
    ES.afficherTitre(titre);
    ES.afficherISBN(nISBN);
    ES.afficherAuteurs(nomAuteurs);
    ES.afficherEditeur(nomEditeur);
    ES.afficherDate("Date de parution", dateParution);
} 

public void afficherLecteurEmprunt(String nom,String prenom,int numero)
{
    ES.afficherTexte("Informations du lecteur : ");
    ES.afficherNom(nom);
    ES.afficherPrenom(prenom);
    ES.afficherNumero(numero);
}

public void afficheEmpruntExemplaire(String titre,String nISBN,int nExemplaire,LocalDate dateDebut,LocalDate dateFin)
{
    ES.afficherTexte("Informations de l'exemplaire : ");
    ES.afficherTitre(titre);
    ES.afficherISBN(nISBN);
    ES.afficherNumero(nExemplaire);
    ES.afficherDate("Date début : ", dateDebut);
    ES.afficherDate("Date fin : ", dateDebut);
}

public void informerUtilisateur(String message, Boolean valide)
{
    ES.afficherLibelle(message + " : " + (valide ? "[OK]" : "[KO]"));
}



//Utiliser sc.nextInt au lieu de sc.nextLine
public String saisirISBNnonExiste(ArrayList<String> numsISBN){
    ES.afficherLibelle("Entrez le Numéro ISBN:");
    String nISBN = ES.lireChaine();
    boolean test = true;
    
    while(test == true && ES.existeString(numsISBN,nISBN)){
        test = ES.lireBoolean("Le n° existe déjà, voulez-vous réessayer ?");
        
        if(test == true)
        {
            ES.afficherLibelle("Entrez un nouveau numéro ISBN:");
            nISBN = ES.lireChaine();
        }
        else
        {
            nISBN = "0";
        }
    } 
   return nISBN;
}

public String saisirISBNExiste(ArrayList<String> numsISBN){
    ES.afficherLibelle("Entrez le Numéro ISBN:");
    String nISBN = ES.lireChaine();
    boolean test = true;
    
    while(test == true && !ES.existeString(numsISBN,nISBN)){
        test = ES.lireBoolean("Le n° n'existe pas, voulez-vous réessayer ?");
        
        if(test == true)
        {
            ES.afficherLibelle("Entrez un nouveau numéro ISBN:");
            nISBN = ES.lireChaine();
        }
        else
        {
            nISBN = "0";
        }
   }
   return nISBN;
}

public int saisirExemplaireExiste(ArrayList<Integer> numsExemplaires)
{
    ES.afficherLibelle("Entrez le Numéro de l'exemplaire:");
    int nExemplaire = ES.lireEntier();
    boolean test = true;
    
    while(test == true && !ES.existeInteger(numsExemplaires,nExemplaire)){
        test = ES.lireBoolean("Le n° n'existe pas, voulez-vous réessayer ?");
        
        if(test == true)
        {
            ES.afficherLibelle("Entrez un nouveau numéro d'exemplaire:");
            nExemplaire = ES.lireEntier();
        }
        else
        {
            nExemplaire = 0;
        }
   }
   return nExemplaire;
}

public int saisirLecteurExiste(ArrayList<Integer> numsLecteur){
    ES.afficherLibelle("Entrez le Numéro du lecteur :");
    int nLecteur = ES.lireEntier();
    boolean test = true;
    
    while(test == true && !ES.existeInteger(numsLecteur,nLecteur)){
        test = ES.lireBoolean("Le n° n'existe pas, voulez-vous réessayer ?");
        
        if(test == true)
        {
            ES.afficherLibelle("Entrez un nouveau numéro de lecteur:");
            nLecteur = ES.lireEntier();
        }
        else
        {
            nLecteur = 0;
        }
    } 
    return nLecteur;
}

public InfosOuvrage saisirOuvrage()
{
    String titre, nomEditeur;
    LocalDate dateParution;
    ArrayList<String> auteurs = new ArrayList<String>();
    PublicVise publicVise = null;
    Boolean test = true;
    int nPublic = 0;
    Scanner sc= new Scanner(System.in);
    
    ES.afficherTexte("Saisir les informations de l'ouvrage");
    titre = ES.lireChaine("- Titre de l'ouvrage");
    dateParution = ES.lireDate("- Date de parution");

    while(dateParution.isAfter(now()))
    {
        dateParution = ES.lireDate("-- La date de parution doit être antérieur à la date du jour.");
    }
    nomEditeur = ES.lireChaine("- Nom de l'éditeur");
    
    while(test == true)
    {
        auteurs.add(ES.lireChaine("- Entrer le nom d'un auteur"));
        test = ES.lireBoolean("Voulez-vous entrer un autre auteur ?");
    }
    
    test = true;
    ES.afficherLibelle("- Entrer 1 pour adulte, 2 pour ado, 3 pour enfant");
    
    while(test == true)
    {
        nPublic = sc.nextInt(); 
        if(nPublic != 1 && nPublic != 2 && nPublic != 3)
        {
            ES.afficherLibelle("-- Le numéro doit être 1, 2 ou 3");
        }
        else 
        {
            test = false;
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
    
    while(dateReception.isBefore(dateParution) || dateReception.isAfter(now()))
    {
        dateReception = ES.lireDate("-- La date de réception doit être postérieure à la date de parution.\n Veuillez entrer une nouvelle date");
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
        dateDeNaissance = ES.lireDate("-- La date de naissance doit être antérieure à la date du jour.");
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


