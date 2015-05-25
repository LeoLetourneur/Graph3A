import java.util.ArrayList;
import java.util.List;


public class Graph {

	private static int numero = 0;
	private String nom;
	private List<Sommet> sommets;
	
	public Graph(String p_nom, ArrayList<Sommet> p_sommets) {
		numero++;
		setNom(p_nom);
		if(p_nom=="")
			setNom("N°"+numero);
		setSommets(p_sommets);
	}
	
	public Graph(ArrayList<Sommet> p_sommets) {
		this("", p_sommets);
	}

	public Graph(String p_nom) {
		this(p_nom, new ArrayList<Sommet>());
	}
	
	public Graph() {
		this("", new ArrayList<Sommet>());
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

}
