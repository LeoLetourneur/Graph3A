
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Sommet {

    private String nom;
	private int index;
	private boolean marque;
	private int valeur;
    private List<Sommet> sommetAdjacent;
    private int degres;
    

    public Sommet(String p_nom, int p_index) {
        setNom(p_nom);
        setIndex(p_index);
        setMarque(false);
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
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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
	}

	public int getDegres() {
		return degres;
	}

	public void setDegres(int degres) {
		this.degres = degres;
	}
	
	public static List<Sommet> parcoursLargeur(List<Sommet> graphe, Sommet depart) {
		List<Sommet> temp = new ArrayList<Sommet>();
		List<Sommet> traiter = new ArrayList<Sommet>();
		
		for(int i = 0; i < graphe.size(); i++) {
			graphe.get(i).setMarque(false);
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
	
	public static List<Sommet> lectureFichier(String p_path) {
		
		ArrayList<Sommet> liste = new ArrayList<Sommet>();
		
		try{
			InputStream ips = new FileInputStream(p_path); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			String nom;
			String index;
			
			while ((ligne=br.readLine())!=null){
				nom = ligne.split(":")[0];
				index = ligne.split(":")[1];
				liste.add(new Sommet(nom, Integer.parseInt(index)));
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		return liste;
	}

	public static void main(String[] args) {
		List<Sommet> graphe = new ArrayList<Sommet>();
		
		Sommet s1 = new Sommet("a",1);
		Sommet s2 = new Sommet("b",2);
		Sommet s3 = new Sommet("c",3);
		Sommet s4 = new Sommet("d",4);
		
		s1.ajouterVoisin(s2);
		s1.ajouterVoisin(s3);
		s2.ajouterVoisin(s1);
		s3.ajouterVoisin(s1);
		s3.ajouterVoisin(s4);
		s4.ajouterVoisin(s3);
		
		List<Sommet> parcours;
		
		parcours = Sommet.parcoursLargeur(graphe, s2);
		
		System.out.println(parcours.toString());
		
		
		List<Sommet> graph2 = new ArrayList<Sommet>();
		graph2 = lectureFichier("src/text.txt");
		System.out.println(graph2.toString());
	}
}
