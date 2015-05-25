import java.util.ArrayList;
import java.util.List;


public class Sommet {

    private String nom;
	private int index;
	private boolean marque;
	private int valeur;
    private List<Sommet> sommetAdjacent;
    private int degresEntrants;
    private int degresSortants;
    

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

	public int getDegresEntrants() {
		return degresEntrants;
	}

	public void setDegresEntrants(int degresEntrants) {
		this.degresEntrants = degresEntrants;
	}

	public int getDegresSortants() {
		return degresSortants;
	}

	public void setDegresSortants(int degresSortants) {
		this.degresSortants = degresSortants;
	}
	
	public int getDegresTotals() {
		return degresSortants + degresEntrants;
	}
}
