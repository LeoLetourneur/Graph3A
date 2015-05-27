import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Sommet {

    private String nom;
	private boolean marque;
	private boolean couleur;
	private int valeur;
    private List<Sommet> sommetAdjacent;
    private int degres;
    

    public Sommet(String p_nom) {
        setNom(p_nom);
        setMarque(false);
        setCouleur(false);
        setValeur(0);
        setSommetAdjacent(new ArrayList<Sommet>());
    }

    public void ajouterVoisin(Sommet v) {
    	sommetAdjacent.add(v);
    }
    
    public String toString() {
        return "Sommet "+nom;
    }

    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public boolean isMarque() {
		return marque;
	}

	public void setMarque(boolean marque) {
		this.marque = marque;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public List<Sommet> getSommetAdjacent() {
		return sommetAdjacent;
	}

	public void setSommetAdjacent(List<Sommet> sommetAdjacent) {
		this.sommetAdjacent = sommetAdjacent;
		this.degres = sommetAdjacent.size();
	}

	public int getDegres() {
		return degres;
	}

	public void setDegres(int degres) {
		this.degres = degres;
	}
	
	public boolean isCouleur() {
		return couleur;
	}

	public void setCouleur(boolean couleur) {
		this.couleur = couleur;
	}
	
	
	
	
	
	//TODO
		public static void main(String[] args) {
			
			Graph graph = new Graph();
			graph.lectureFichier("motsdelongueur6.txt");
			System.out.println("Le nombre de sommet est de "+graph.getSommets().size());
			System.out.println("Le nombre d'arc est de "+graph.getArcs().size());
		}
}





//TODO
class Arc {
	
	private Sommet sommet1;
	private Sommet sommet2;
	
	public Arc(Sommet p_sommet1, Sommet p_sommet2) {
		setSommet1(p_sommet1);
		setSommet2(p_sommet2);
	}
	
	public Sommet getSommet1() {
		return sommet1;
	}

	public void setSommet1(Sommet sommet1) {
		this.sommet1 = sommet1;
	}

	public Sommet getSommet2() {
		return sommet2;
	}

	public void setSommet2(Sommet sommet2) {
		this.sommet2 = sommet2;
	}
}






//TODO
class Graph {

	private String nom;
	private List<Sommet> sommets;
	private List<Arc> arcs;
	
	public Graph(ArrayList<Sommet> p_sommets, ArrayList<Arc> p_arcs) {
		setSommets(p_sommets);
		setArcs(p_arcs);
	}

	public Graph() {
		this(new ArrayList<Sommet>(), new ArrayList<Arc>());
	}
	
	public List<Sommet> parcoursLargeur(Sommet depart) {
		List<Sommet> temp = new ArrayList<Sommet>();
		List<Sommet> traiter = new ArrayList<Sommet>();
		
		for(int i = 0; i < this.getSommets().size(); i++) {
			this.getSommets().get(i).setMarque(false);
		}
		
		depart.setMarque(true);
		temp.add(depart);
		
		while (temp.size() > 0) {
			Sommet currentSommet = temp.get(0);
			for(int i = 0; i < currentSommet.getSommetAdjacent().size(); i++) {
				if(!currentSommet.getSommetAdjacent().get(i).isMarque()) {
					currentSommet.getSommetAdjacent().get(i).setMarque(true);
					temp.add(currentSommet.getSommetAdjacent().get(i));
				}
			}
			traiter.add(currentSommet);
			temp.remove(currentSommet);
		}
		return traiter;
	}
	
	public void lectureFichier(String p_path) {
		
		this.setSommets(new ArrayList<Sommet>());
		this.setArcs(new ArrayList<Arc>());
		
		try{
			InputStream ips = new FileInputStream(p_path); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			String nom;
			int cpt;
			
			while ((ligne=br.readLine())!=null){
				nom = ligne;
				Sommet sommetCreer = new Sommet(nom);
				
				for(Sommet sommetCompare : this.getSommets()) {
						cpt = 0;
						for(int i=0;i<6;i++) {
							if(sommetCreer.getNom().charAt(i) != sommetCompare.getNom().charAt(i)) {
								cpt++;
								if(cpt>1)
									break;
							}
						}
						if(cpt == 1) {
							sommetCreer.getSommetAdjacent().add(sommetCompare);
							sommetCompare.getSommetAdjacent().add(sommetCreer);
							this.getArcs().add(new Arc(sommetCreer, sommetCompare));
						}
				}
				this.getSommets().add(sommetCreer);
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void ajoutSommet(Sommet p_sommet) {
		getSommets().add(p_sommet);
	}
	
	public void suppressionSommet(Sommet p_sommet) {
		getSommets().remove(p_sommet);
	}
	
	public int nbSommet() {
		return getSommets().size();
	}

	public String toString() {
        return "Graph "+nom;
    }
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Sommet> getSommets() {
		return sommets;
	}

	public void setSommets(List<Sommet> sommets) {
		this.sommets = sommets;
	}

	public List<Arc> getArcs() {
		return arcs;
	}

	public void setArcs(List<Arc> arcs) {
		this.arcs = arcs;
	}
}