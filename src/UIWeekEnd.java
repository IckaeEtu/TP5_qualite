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
		System.out.println("Que voulez vous faire?");
	    System.out.println("L: Liste");
		System.out.println("S: Sélection");
		System.out.println("Q: Quitter");
		String commande_brute = System.console().readLine();
	    String commande = commande_brute.strip().toLowerCase();
		if(commande.equals("q")) {
			quitter = true;
			commande_faite = true;
		} else if (commande.equals("l")) {
			System.out.println(we.getAmis());
			commande_faite = true;
		} else if (commande.equals("s")) {
			System.out.println("Donnez le matricule de la personne à sélectionner");
			String num = System.console().readLine();
			try {
				selection(num);
				System.out.println("Vous avez selectionné " + personne.toString());
			} catch (NumberFormatException e) {
				System.out.println("Vous n'avez pas donner un nombre");
			}
			commande_faite = true;
		} else {
			System.out.println("Commande '" + commande_brute + "' invalide.");
		}
		}
	}

	public void selection(String num) {this.personne = we.getAmis().get(Integer.parseInt(num));}

	public void menuDepense(){
		
	}


    public void menu() {
	boolean commande_faite = false;
	while(!commande_faite) {
	    System.out.println("Que voulez vous faire?");
	    System.out.println("P: Voir les personnes du weekend");
		System.out.println("D: Voir les dépense du weekend");
		System.out.println("T: Voir le total des dépense du weekend");
		System.out.println("M: Voir la moyenne des dépenses par personnes");
		System.out.println("Q: quitter");
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
			System.out.println(we.affiche_depenses());
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
