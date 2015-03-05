import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;


public class Rubrica {
	private Vector<Persona> rubrica;
	private Scanner scan;
	
	public Rubrica() {
		rubrica=new Vector<Persona>();
		scan = new Scanner(System.in);
	}
	
	public void menuPrincipale() {
		String testoMenu="Menu principale:\n\n"
				+ "Scegli una opzione:\n"
				+ "1: Aggiungi un contatto\n"
				+ "2: Modifica un contatto\n"
				+ "3: Elimina un contatto\n"
				+ "4: Mostra l'elenco dei contatti \n"
				+ "5: Salvare la rubrica\n"
				+ "6: Carica la rubrica\n"
				+ "e: Esci\n";
		menuLoop:
		while (true) {
			System.out.println(testoMenu);
			System.out.print("Scegli un numero (1-6), o e per uscire: ");
			String input = scan.nextLine();
			switch(input) {
				case "1": 
					aggiungiContatto();
					System.out.print("Premi enter per tornare al menu principale: ");
					scan.nextLine();
					break;
				case "2": 
					modificaContatto(); 
					System.out.print("Premi enter per tornare al menu principale: ");
					scan.nextLine();
					break;
				case "3": 
					eliminaContatto();
					System.out.print("Premi enter per tornare al menu principale: ");
					scan.nextLine();
					break;
				case "4": 
					visualizzaElencoContatti(); 
					System.out.print("Premi enter per tornare al menu principale: ");
					scan.nextLine();
					break;
				case "5":
					salvaRubrica();
					System.out.print("Premi enter per tornare al menu principale: ");
					scan.nextLine();
					break;
				case "6":
					caricaRubrica();
					System.out.print("Premi enter per tornare al menu principale: ");
					scan.nextLine();
					break;
				case "e": 
					break menuLoop;
				default: 
					System.out.println("Devi scrivere o un numero tra 1 e 6, o la lettera e per uscire\n");
			}
		}
		//Fine della programma, chiudi l'oggetto Scanner
		scan.close();
	}
	
	public void aggiungiContatto() {
		String nome=queryNome();
		String cognome=queryCognome();
		String indirizzo=queryIndirizzo();
		String telefono=queryTelefono();
		Persona nuovoContatto=new Persona(nome,cognome,indirizzo,telefono);
		rubrica.add(nuovoContatto);
		System.out.println("Una persona è stata aggiunta alla rubrica con i seguenti dati: ");
		nuovoContatto.visualizzaPersona();
	}
	
	public void modificaContatto() {
		if (rubrica.isEmpty()) {
			System.out.println("La rubrica risulta vuota, non ci sono contatti da modificare.");
			
		}
		else {
			visualizzaElencoContatti();
			System.out.print("Digita il numero del contatto da modificare: ");
			int i=queryNum();
			if (i>=0 && i<rubrica.size()) {
				String scelta="Scegli tra i seguenti attribuiti da modificare: \n"
						+ "1: Nome\n"
						+ "2: Cognome\n"
						+ "3: Indirizzo\n"
						+ "4: Numero di telefono";
				System.out.println(scelta);
				System.out.print("Digita il numero (1-4)");
				int j = queryNum();
				switch (j) {
					case 1:
						String nome=queryNome();
						rubrica.get(i).modificaNome(nome);
						break;
					case 2:
						String cognome=queryCognome();
						rubrica.get(i).modificaCognome(cognome);
						break;
					case 3:
						String indirizzo=queryIndirizzo();
						rubrica.get(i).modificaIndirizzo(indirizzo);
						break;
					case 4:
						String telefono=queryTelefono();
						rubrica.get(i).modificaTelefono(telefono);
						break;
					default:
						System.out.println("Errore: Devi digitare un numero tra 1 e 4.");
				}
				System.out.println("E' stato modificato il contatto numero " + i + " con i seguenti dati attuali:");
				rubrica.get(i).visualizzaPersona();
			}
			else {
				System.out.println("Devi scegliere un numero tra 0 e " + (rubrica.size()-1));
			}
		}
	}
	
	public void eliminaContatto() {
		if (rubrica.isEmpty()) {
			System.out.println("La rubrica risulta vuota, non ci sono contatti da eliminare.");
		}
		else {
			visualizzaElencoContatti();
			System.out.print("Scegli il numero del contatto da eliminare: ");
			int i=queryNum();
			if (i>=0 && i<rubrica.size()) {
				System.out.print("Sei sicuro di voler eliminare il contatto numero: " + i + "? (s/n) ");
				String input = scan.nextLine();
				switch(input) {
					case "s":
						Persona personaRimossa=rubrica.remove(i);
						System.out.println("La persona con i seguenti dati è stata rimossa dalla rubrica: ");
						personaRimossa.visualizzaPersona();
						break;
					case "n":
					default:
						System.out.println("Non è stata rimossa nessuna persona dalla rubrica.");
				}
			}
			else {
				System.out.println("Devi scegliere un numero tra 0 e " + (rubrica.size()-1));
			}
		}
	}
	
	public void visualizzaElencoContatti() {
		if (rubrica.isEmpty()) {
			System.out.println("La rubrica risulta vuota, aggiungi dei contatti prima di visualizzarla.");
			
		}
		else {
			System.out.println("Rubrica:");
			for (int i=0; i<rubrica.size();i++) {
				rubrica.get(i).visualizzaPersonaElenco(i);
			}
		}
	}
	
	public void salvaRubrica() {
		try {
			 ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("rubrica"));
			 out.writeObject(rubrica);
			 out.flush();
			 out.close();
		}
		catch(IOException e1) {
			System.out.println("Errore: Non è stato possibile salvare la rubrica.");
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void caricaRubrica() {
		try {
			 ObjectInputStream in = new ObjectInputStream(new FileInputStream("rubrica"));
			 rubrica = (Vector<Persona>) in.readObject();
			 in.close();
		}
		catch(IOException e1) {
			System.out.println("Errore: Non è stato possibile caricare la rubrica.");
			e1.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String queryNome() {
		String input;
		while(true) {
			System.out.print("Scrivi il nome (prima lettera maiuscola): ");
			input=scan.nextLine();
			if(input.isEmpty()) {
				System.out.println("Errore: Devi scrivere un nome prima di premere enter.");
				continue;
			}
			//Controlla se la prima lettera è maiuscola
			if(Character.isUpperCase(input.charAt(0))) {
				break;
			}
			System.out.println("Errore: La prima lettera del nome deve essere maiuscola");
		}
		return input;
	}
	
	public String queryCognome() {
		String input;
		while(true) {
			System.out.print("Scrivi il cognome (prima lettera maiuscola): ");
			input=scan.nextLine();
			if(input.isEmpty()) {
				System.out.println("Errore: Devi scrivere un cognome prima di premere enter.");
				continue;
			}
			//Controlla se la prima lettera è maiuscola
			if(Character.isUpperCase(input.charAt(0))) {
				break;
			}
			System.out.println("Errore: La prima lettera del cognome deve essere maiuscola");
		}
		return input;
	}
	
	public String queryIndirizzo() {
		System.out.print("Scrivi l'indirizzo: ");
		return scan.nextLine();
	}
	
	public String queryTelefono() {
		String input;
		while (true) {
			System.out.print("Scrivi il numero di telefono: ");
			input = scan.nextLine();
			if(input.isEmpty()) {
				System.out.println("Errore: Devi scrivere un numero prima di premere enter.");
				continue;
			}
			//Controlla se l'input contiene soltanto numeri
			if (input.matches("[0-9]+")) {
				break;
			}
			System.out.println("Errore: Il numero di telefono può soltanto contenere dei numeri");
		}
		return input;
	}
	
	public int queryNum() {
		int num = scan.nextInt();
		scan.nextLine(); //necessario per assicurare che il prossimo nextLine nell'esecuzione non legge una riga vuota.
		return num;
	}
}
