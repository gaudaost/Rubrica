import java.io.Serializable;

public class Persona implements Serializable {
	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	
	public Persona(String nome, String cognome, String indirizzo, String telefono) {
		this.nome=nome;
		this.cognome=cognome;
		this.indirizzo=indirizzo;
		this.telefono=telefono;
	}
	
	public void modificaNome(String nome) {
		this.nome=nome;
	}
	
	public void modificaCognome(String cognome) {
		this.cognome=cognome;
	}
	
	public void modificaIndirizzo(String indirizzo) {
		this.indirizzo=indirizzo;
	}
	
	public void modificaTelefono(String telefono) {
		this.telefono=telefono;
	}
	
	public String getNome() {return nome;}
	
	public String getCognome() {return cognome;}
	
	public String getIndirizzo() {return indirizzo;}
	
	public String getTelefono() {return telefono;}
	
	public void visualizzaPersona() {
		String informazione = "Nome: " + nome + "\n"
				+ "Cognome: " + cognome + "\n"
				+ "Indirizzo: " + indirizzo + "\n"
				+ "Telefono: " + telefono;
		System.out.println(informazione);
	}
	public void visualizzaPersonaElenco(int numContatto) {
		String informazione = numContatto + ":  "
				+ "Nome: " + nome + "\t"
				+ "Cognome: " + cognome + "\t"
				+ "Indirizzo: " + indirizzo + "\t"
				+ "Telefono: " + telefono;
		System.out.println(informazione);
	}
}
