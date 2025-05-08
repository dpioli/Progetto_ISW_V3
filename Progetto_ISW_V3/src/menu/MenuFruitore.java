package menu;

import java.util.*;

import applicazione.CampoCaratteristico;
import applicazione.Categoria;
import applicazione.CategoriaFoglia;
import applicazione.Gerarchia;
import applicazione.Proposta;
import applicazione.PropostaScambio;
import applicazione.StatoProposta;
import applicazione.TipoProposta;
import persistenza.GestorePersistenza;
import persistenza.LogicaPersistenza;
import utenti.Fruitore;
import util.InputDati;
import util.Menu;

/**
 * Classe per definite il menu delle funzionalità del fruitore
 * 
 * @author Irene Romano 736566
 *
 */
public class MenuFruitore extends Menu{
	
	private Fruitore fruit;
	private LogicaPersistenza logica;
	
	private static final String titolo = "\tMENU FRUITORE";
	
	private static final String NAVIGA = "Naviga tra le gerarchie";
	private static final String RICHIEDI_PRESTAZIONI = "Richiedi prestazioni al server";
	private static final String X = "\n******************************************";
	private static final String MSG_INIZIALE = "Gerarchie presenti nel tuo comprensorio:";
	private static final String MSG_ASSENZA_GERARCH = "Non ci sono gerarchie presenti per il tuo comprensorio.";
	private static final String MSG_SELEZ_GERARCH = "Seleziona una gerarchia > ";
	private static final String CATEGORIA_CORRENTE = "\nCategoria corrente: ";
	private static final String VALORI_IMPOSTATI = "Valori impostati finora: ";
	private static final String MSG_CATEG_FOGLIA = "Sei arrivato a una categoria foglia: ";
	private static final String MSG_MENU_PRINCIPALE = "Ritorno al menu principale.\n";
	private static final String MSG_ASSENZA_SOTTOCATEG = "Non ci sono sottocategorie. Ritorno al menu principale.";
	private static final String MSG_CAMPO_CARATT = "Campo caratteristico: ";
	private static final String MSG_SOTTOCATEG_DISP = "Sottocategorie disponibili:";
	private static final String DOT = ". ";
	private static final String COLON = ": ";
	private static final String MSG_VOCE_TORNA_INDIETRO = "0. Torna al menu";
	
	private static String[] vociFruit = {NAVIGA, RICHIEDI_PRESTAZIONI};
	
	/**
	 * Construttore di MenuFruitore
	 * 
	 * @param fruit
	 * @param logica
	 */
	public MenuFruitore(Fruitore fruit, LogicaPersistenza logica) {
		super(titolo, vociFruit);
		this.fruit = fruit;
		this.logica = logica;
	}
	
	/**
	 * Metodo per navigare in profondita' tra le gerarchie
	 */
	public void naviga() {
		System.out.println(X);
		System.out.println(MSG_INIZIALE);
		ArrayList<Gerarchia> gerarch = new ArrayList<Gerarchia>();
		for(Gerarchia g: logica.getGerarchie()) {
			if(g.getNomeComprensorio().equals(fruit.getNomeComprensorio())) {
				gerarch.add(g);
			}
		}
		Gerarchia gScelta;
		if(gerarch.isEmpty()) {
			System.out.println(MSG_ASSENZA_GERARCH);
			return;
		} else {
			gScelta = selezionaGerarchia(gerarch);
            navigaCategoria(gScelta.getCatRadice(), new HashMap<>()); 
            // Inizializziamo la mappa dei valori di campo per la navigazione
		}
		
		
	}
	
	/**
	 * Metodo per selezionare una gerarchia presente all'interno del comprensorio selezionato
	 * @param gerarch
	 * @return
	 */
	private Gerarchia selezionaGerarchia(ArrayList<Gerarchia> gerarch) {
		for(int i = 0; i < gerarch.size(); i++) {
			System.out.println(i + COLON + gerarch.get(i).getCatRadice().getNome());
		}
		
		int scelta = InputDati.leggiIntero(MSG_SELEZ_GERARCH, 0, gerarch.size() - 1);
		return gerarch.get(scelta);
	}



	/**
	 * Metodo per andare a modificare l'output in base ai percorsi selezionati
	 * @param categoriaCorrente
	 * @param valoriImpostati
	 */
	private void navigaCategoria(Categoria categoriaCorrente, Map<String, String> valoriImpostati) {
	    System.out.println(CATEGORIA_CORRENTE + categoriaCorrente.getNome());

	    if (!valoriImpostati.isEmpty()) {
	        System.out.println(VALORI_IMPOSTATI + valoriImpostati);
	    }

	    if (categoriaCorrente.isFoglia()) {
	        System.out.println(MSG_CATEG_FOGLIA + categoriaCorrente.getNome());
	        System.out.println(MSG_MENU_PRINCIPALE);
	        return;
	    }

	    List<Categoria> sottocategorie = categoriaCorrente.getSottoCateg();
	    if (sottocategorie == null || sottocategorie.isEmpty()) {
	        System.out.println(MSG_ASSENZA_SOTTOCATEG);
	        return;
	    }

	    CampoCaratteristico campo = categoriaCorrente.getCampCaratt();
	    Categoria prossimaCategoria = null;

	    if (campo != null) {
	        System.out.println(MSG_CAMPO_CARATT + campo.getNomeCampo());
	    }

	    prossimaCategoria = selezionaSottoCategoria(sottocategorie);

	    if (prossimaCategoria != null) {
	        navigaCategoria(prossimaCategoria, new HashMap<>(valoriImpostati));
	    }
	}
	
	/**
	 * Metodo per selezionare una sottocategoria presente
	 * @param sottocategorie
	 * @return
	 */
	private Categoria selezionaSottoCategoria(List<Categoria> sottocategorie) {
	    System.out.println(MSG_SOTTOCATEG_DISP);
	    for (int i = 0; i < sottocategorie.size(); i++) {
	        System.out.println((i + 1) + DOT + sottocategorie.get(i).getNome());
	    }
	    
	    System.out.println(MSG_VOCE_TORNA_INDIETRO);
	    int scelta = InputDati.leggiIntero(MSG_SELEZ_GERARCH, 0, sottocategorie.size());
	    if (scelta == 0) return null;
	    return sottocategorie.get(scelta - 1);
	}
	
	public void richiediPrestazioni() {
		/**
		 * formulazione richiesta
		 * 1. il fruitore visualizza le categorie foglia a disposizione
		 * 2. seleziona categoria della richiesta
		 * 3. inserice ore (durata della prestazione d'opera desiderata)
		 * 4. creo oggetto Proposta di tipo richiesta
		 * 5. seleziona quale prestazione offre in cambio
		 * 6. formulo l'offerta
		 * 7. propongo lo scambio con questi due oggetti
		 * 8. se accettata salvo salvando fruitore
		 * @return
		 */
		ArrayList<CategoriaFoglia> foglie = logica.getCategorieFoglia();
		stampaPrestazioni(foglie); //poi sistema grazie fai tipo ID : nome
		
		//RICHIESTA
		int scelta = InputDati.leggiIntero("Seleziona la prestazione di interesse: ") - 1;
		double ore = InputDati.leggiDouble("Inserisci il numero di ore di questa prestazione che ti interessano:");
		Proposta richiesta = new Proposta(foglie.get(scelta), TipoProposta.RICHIESTA, ore);

		//OFFERTA
		int incambio = InputDati.leggiIntero("Quale prestazione offri in cambio?") - 1;
		ArrayList<Double> fattori = logica.getFatConversione().prendiRiga(scelta); //prendendo tutti i fdc dalla tabella uscenti da id della prestazione richiesta
	    int valore = (int) (fattori.get(incambio) * ore);
		Proposta offerta = new Proposta(foglie.get(incambio), TipoProposta.OFFERTA, valore);
		
		//SCAMBIO
		PropostaScambio scambio = new PropostaScambio(richiesta, offerta);
		boolean sn = InputDati.yesOrNo("Vuoi accettare la seguente proposta:\n" + scambio.toString());
		if(sn) {
			scambio.setStato(StatoProposta.ACCETTATA);
			scambio.setFruitoreAssociato(fruit);
			logica.addScambio(scambio);
			GestorePersistenza.salvaScambi(logica.getScambi());
		} else
			scambio.setStato(StatoProposta.RIFIUTATA);
	}
	private void stampaPrestazioni(ArrayList<CategoriaFoglia> foglie) {
		StringBuffer sb = new StringBuffer();
		sb.append("Prestazioni a disposizione >>\n");		
		for(CategoriaFoglia f : foglie) {
			sb.append(f.getId());
			sb.append(": ");
			sb.append(f.getNome());
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
		
/*	private Proposta formulaProposta(ArrayList<CategoriaFoglia> foglie) {
		System.out.println("Le prestazioni a disposizione: " + foglie.toString()); //poi sistema grazie fai tipo ID : nome
		int scelta = InputDati.leggiIntero("Seleziona la prestazione di interesse");
		double ore = InputDati.leggiDouble("Inserisci il numero di ore di prestazione che ti interessano");
		Proposta p = new Proposta(foglie.get(scelta), TipoProposta.RICHIESTA, ore);
		p.setAssociato(fruit);
		return p;
	}
*/
}
