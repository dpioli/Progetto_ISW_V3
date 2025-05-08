package applicazione;

import utenti.Fruitore;

public class Proposta {
	
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

	@Override
	public String toString() {
		return "PropostaScambio [prestazione " + tipo + ": "+ prestazione.getNome() + ", ore: " + quantitaOre + "]";
	}

}
