package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO dao;
	
	List<Blackout> blackouts;
	int bestPeople =0;
	List<Blackout> best;
	int toth = 0;
	int totPeople = 0;
	
	public Model() {
		dao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return dao.getNercList();
	}

	public List<Blackout> doWorstCaseAnalysis(Nerc nerc, int X, int Y) {
		blackouts = dao.getBlackout(nerc);
		List<Blackout> parziale = new ArrayList<>();
		best = null;
		
		cerca (parziale, 0, X, Y, blackouts);
		return best;
	}

	private void cerca(List<Blackout> parziale, int livello, int x, int y, List<Blackout> blackouts) {
		
		toth = calcolaTotH(parziale);
		totPeople = calcolaTotPeople(parziale);
		//casi terminali
		
		if(toth <= y) {
			if (totPeople > bestPeople) {
				best = new ArrayList<>(parziale);
				bestPeople = totPeople;
			}
		} else
			return;
		
		//caso intermedio
		
		for(Blackout b: blackouts) {
			if(annoOk(b, parziale, x) && !parziale.contains(b)) {
				parziale.add(b);
				cerca(parziale, livello+1, x, y, blackouts);
				parziale.remove(parziale.size()-1);
			} 
		}
		
		
	}

	private boolean annoOk(Blackout prova, List<Blackout> parziale, int x) {
		boolean flag = true;
		
		for(Blackout b: parziale) {
			if(Math.abs(b.getAnno()-prova.getAnno()) > x){
				flag = false;
			}
		}
		
		return flag;
	}

	private int calcolaTotPeople(List<Blackout> parziale) {
		int tot=0;
		
		for(Blackout b: parziale) {
			tot+= b.getCustomers();
		}
		
		return tot;
	}

	private int calcolaTotH(List<Blackout> parziale) {
		int tot =0;
		
		for(Blackout b: parziale) {
			tot+= b.getDurata();
		}
		
		return tot;
	}

	public int getToth(List<Blackout> parziale) {
		return calcolaTotH(parziale);
	}

	public int getTotPeople(List<Blackout> parziale) {
		return calcolaTotPeople(parziale);
	}
	
	

}
