package applicazione;

import utenti.Fruitore;

public class Proposta {
	
	private Fruitore associato = null; //impostato solo quando la proposta viene accettata per essere salvata
	private CategoriaFoglia prestazione;
	private TipoProposta tipo;
	private double quantitaOre;
	
	/**
	 * Costruttore dell'oggetto proposta
	 * @param prestazione
	 * @param tipo della proposta (richiesta / offerta)
	 * @param quantitaOre
	 */
	public Proposta(CategoriaFoglia prestazione, TipoProposta tipo, double quantitaOre) {
		this.prestazione = prestazione;
		this.tipo = tipo;
		this.quantitaOre = quantitaOre;
	}

	
	//GETTERS
	
	public double getQuantitaOre() {
		return quantitaOre;
	
	}
	
	public TipoProposta getTipo() {
		return tipo;	
	}

	public CategoriaFoglia getPrestazione() {
		return prestazione;
	}


	public Fruitore getAssociato() {
		return associato;
	}
	
	/**
	 * Metodo che associa il fruitore allo scambio di proposta accettato
	 * @param associato
	 */
	public void setFruitoreAssociato(Fruitore associato) {
		this.associato = associato;	
	}
	
	@Override
	public String toString() {
		return "PropostaScambio [prestazione " + tipo + ": "+ prestazione.getNome() + ", ore: " + quantitaOre + "]";
	}

}
