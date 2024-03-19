package fr.univ_orleans.iut45.mud;
class UIWeekEnd {    
    public static void main(String[] args) {
	WeekEnd we = new WeekEnd();
	AppWeekEnd app = new AppWeekEnd(we);
	Personne pierre = new Personne("Durand","Pierre"); 
	System.out.println(pierre.getNom()); 
	Personne paul = new Personne("Dupond","Paul"); 
	Personne marie = new Personne("Dumond","Marie");
	Personne anne = new Personne("Dunon","Anne");  
	Depense d1 = new Depense(pierre, 12, "pain"); 	
	Depense d2 = new Depense(paul, 100, "pizza"); 
	Depense d3 = new Depense(pierre, 70, "essence");
	Depense d4 = new Depense(marie, 15, "vin");  
	Depense d5 = new Depense(paul, 10, "vin"); 
	we.ajouterPersonne(pierre); 
	we.ajouterPersonne(paul);
	we.ajouterPersonne(marie);
	we.ajouterPersonne(anne); 
	we.ajouterDepense(d1);   
	we.ajouterDepense(d2); 
	we.ajouterDepense(d4);   
	we.ajouterDepense(d3);
	we.ajouterDepense(d5);
	app.run();
    }
}
    
class AppWeekEnd {

    WeekEnd we;
    boolean quitter;
	private Personne personne = null;

    AppWeekEnd(WeekEnd we) {
	this.we = we;
	this.quitter = false;
    }

    public void run() {
	bienvenue();
	boolean continuer = true;
	while(!quitter) {
	    menu();
	}
	au_revoir();
    }

	public void menuPersonne(){
		System.out.println(we.getAmis());
		boolean commande_faite = false;
		while(!commande_faite) {
		System.out.println("╭──────────────────────╮");
		System.out.println("│Que voulez vous faire?│");
	    System.out.println("│L: Liste              │");
		System.out.println("│S: Sélection          │");
		System.out.println("│T: Total dépense      │");
		System.out.println("│A: Avoir              │");
		System.out.println("│+: Ajouter quelqu'un  │");
		System.out.println("│Q: Quitter            │");
		System.out.println("╰──────────────────────╯");
		String commande_brute = System.console().readLine();
	    String commande = commande_brute.strip().toLowerCase();
		if(commande.equals("q")) {
			commande_faite = true;
		} else if (commande.equals("l")) {
			System.out.println(we.getAmis());
			commande_faite = true;
		} else if (commande.equals("t")){
			if (this.personne != null) {
				System.out.println(this.personne.getPrenom() + "a dépensé " + we.totalDepensesPersonne(this.personne)+"€");
			} else {
				System.out.println("Veuillez sélectioner quelqu'un d'abord");	
			}
		} else if (commande.equals("s")) {
			System.out.println("Donnez le matricule de la personne à sélectionner");
			String num = System.console().readLine();
			try {
				selection(num);
				System.out.println("Vous avez selectionné " + personne.toString());
			} catch (NumberFormatException e) {
				System.out.println("Vous n'avez pas donner un nombre");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Veuillez sélectionner un nombre valide");
			}
		} else if (commande.equals("a")) {
			if (this.personne != null) {
				System.out.println(this.personne.getPrenom() + "a un avoir de " + we.avoirPersonne(this.personne)+"€");
				
			} else {
				System.out.println("Veuillez sélectioner quelqu'un d'abord");
			}
		} else if (commande.equals("+")) {
			this.ajout();
		} else {
			System.out.println("Commande '" + commande_brute + "' invalide.");
		}
		}
	}
	
	public void ajout(){
		System.out.println("Quel est son nom");
		String nom = System.console().readLine();
		System.out.println("Quel est son prenom");
		String prenom = System.console().readLine();
		Personne personneAjout = new Personne(nom, prenom);
		we.ajouterPersonne(personneAjout);
		System.out.println(nom + " " + prenom + " a été ajoutée");
	}

	public void selection(String num) {this.personne = we.getAmis().get(Integer.parseInt(num));}

	public void menuDepense(){
		System.out.println(we.getAmis());
		boolean commande_faite = false;
		while(!commande_faite) {
			System.out.println("╭──────────────────────╮");
			System.out.println("│Que voulez vous faire?│");
			System.out.println("│L: Liste des dépenses │");
			System.out.println("│+: Ajouter dépenses   │");
			System.out.println("│Q: Quitter            │");
			System.out.println("╰──────────────────────╯");
		String commande_brute = System.console().readLine();
	    String commande = commande_brute.strip().toLowerCase();
		if(commande.equals("q")) {
			commande_faite = true;
		} else if (commande.equals("l")){
			System.out.println("Voici toutes les dépenses du weekend");
			System.out.println(we.affiche_depenses());
		} else if (commande.equals("+")) {
			this.saisie_depense();
		} else {
			System.out.println("Commande '" + commande_brute + "' invalide.");
		}
		}
	}

	public void saisie_depense(){
		System.out.println("C'est quoi ce truc là ?");
		String nomProduit = System.console().readLine();
		System.out.println("Combien que ça coûte mon copain ?");
		String prixProduit = System.console().readLine();
		try {
			Integer prix = Integer.parseInt(prixProduit);
			System.out.println(we.getAmis());
			System.out.println("Sélectionnez l'indice de quelqu'un");
			String num = System.console().readLine();
			this.selection(num);
			System.out.println("Vous avez selectionné " + personne.toString());
			we.ajouterDepense(new Depense(this.personne, prix, nomProduit));
		} catch (NumberFormatException e) {
			System.out.println("Veuillez sélectionner un nombre");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Veuillez sélectionner un indice valide");
		}

	}

    public void menu() {
	boolean commande_faite = false;
	while(!commande_faite) {
		System.out.println("╭────────────────────────╮");
		System.out.println("│Que voulez vous faire?  │");
	    System.out.println("│P: Voir les personnes   │");
		System.out.println("│D: Voir les dépenses    │");
		System.out.println("│T: Voir total dépense   │");
		System.out.println("│M: Voirs moyenne dépense│");
		System.out.println("│Q: Quitter              │");
		System.out.println("╰────────────────────────╯");
	    String commande_brute = System.console().readLine();
	    String commande = commande_brute.strip().toLowerCase();
	    if(commande.equals("q")) {
			quitter = true;
			commande_faite = true;
	    } else if (commande.equals("m")) {
			System.out.println(we.depensesMoyenne());
			commande_faite = true;
		} else if (commande.equals("t")) {
			System.out.println(we.totalDepenses());
			commande_faite = true;
		} else if (commande.equals("p")) {
			this.menuPersonne();
			commande_faite = true;
		} else if (commande.equals("d")) {
			this.menuDepense();
			commande_faite = true;
		} else {
		System.out.println("Commande '" + commande_brute + "' invalide.");
	    }
	}
    }

    /// Affiche un message de bienvenue
    public void bienvenue() {
	System.out.println("╭────────────────────────────────────────────────────────────────────────────────────╮");
	System.out.println("│ Bienvenue! En week-end comme dans la semaine, les bons comptes font les bons amis. │");
	System.out.println("╰────────────────────────────────────────────────────────────────────────────────────╯");
    }

    /// Affiche un message d'au revoir
    public void au_revoir() {
	System.out.println("╭───────────────────────────────────────────────────────────────────────────────────────────────────╮");
	System.out.println("│ Aurevoir! N'oubliez pas ! En week-end comme dans la semaine, les bons comptes font les bons amis. │");
	System.out.println("╰───────────────────────────────────────────────────────────────────────────────────────────────────╯");
    }

}
