package applicazione;

import utenti.Fruitore;

public class PropostaScambio {
	
	private Fruitore mittente;
	private Fruitore destinatario;
	private CategoriaFoglia categoriaOfferta;
	private CategoriaFoglia categoriaRichiesta;
	private double quantitaOfferta; 
	private double quantitaRichiesta;
	private StatoProposta stato;
	
	public PropostaScambio(Fruitore mittente, Fruitore destinatario, 
			CategoriaFoglia categoriaOfferta, CategoriaFoglia categoriaRichiesta, 
			double quantitaOfferta, double quantitaRichiesta) {
		
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.categoriaOfferta = categoriaOfferta;
		this.categoriaRichiesta = categoriaRichiesta;
		this.quantitaOfferta = quantitaOfferta;
		this.quantitaRichiesta = quantitaRichiesta;
		this.stato = stato;
	}

	
	//GETTERS
	
	public Fruitore getMittente() {
		return mittente;
	}

	public Fruitore getDestinatario() {
		return destinatario;
	}

	public CategoriaFoglia getCategoriaOfferta() {
		return categoriaOfferta;
	}

	public CategoriaFoglia getCategoriaRichiesta() {
		return categoriaRichiesta;
	}

	public double getQuantitaOfferta() {
		return quantitaOfferta;
	}

	public double getQuantitaRichiesta() {
		return quantitaRichiesta;
	}

	public StatoProposta getStato() {
		return stato;
	}


	@Override
	public String toString() {
		return "PropostaScambio [mittente=" + mittente + ", destinatario=" + destinatario + ", categoriaOfferta="
				+ categoriaOfferta + ", categoriaRichiesta=" + categoriaRichiesta + ", quantitaOfferta="
				+ quantitaOfferta + ", quantitaRichiesta=" + quantitaRichiesta + ", stato=" + stato + "]";
	}

}