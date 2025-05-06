package applicazione;

import java.util.ArrayList;

public class GestoreScambi {
	
	private ArrayList<PropostaScambio> proposte;
	
	public GestoreScambi() {
		this.proposte = new ArrayList<>();
	}
	
	public void aggiungiProposta(PropostaScambio proposta) {
		proposte.add(proposta);
	}
	
	public ArrayList<PropostaScambio> getTutteLeProposte() {
		return new ArrayList<>(proposte);
	}

}