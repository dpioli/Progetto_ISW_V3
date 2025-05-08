package applicazione;

public class PropostaScambio {
	
	private Proposta richiesta;
	private Proposta offerta; 
	private StatoProposta stato;
	
	/**
	 * Costruttore dell'oggetto costituito dalla proposta del richiedente e l'offerta che gli viene proposta.
	 * Inizialmente lo stato è sempre impostato IN_ATTESA, finché il fruitore stesso la accetta o la rifiuta.
	 * @param richiesta
	 * @param offerta
	 */
	public PropostaScambio(Proposta richiesta, Proposta offerta) {
		this.richiesta = richiesta;
		this.offerta = offerta;
		this.stato = StatoProposta.IN_ATTESA;
	}

	public StatoProposta getStato() {
		return stato;
	}

	public void setStato(StatoProposta stato) {
		this.stato = stato;
	}

	@Override
	public String toString() {
		return "PropostaScambio \nrichiesta: " + richiesta.toString() + "\nofferta: " + offerta.toString();
	}

}