package Tournament;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StatMatch extends Match {
		
	protected StringProperty tab = new SimpleStringProperty();
	protected String p1name;
	protected String p2name;
	protected double p1score;
	protected double p2score;
	protected StringProperty outcome = new SimpleStringProperty();
	
	public StatMatch() {
		tab.set("0");
		p1name="";
		p2name="";
		p1score=0;
		p2score=0;
		outcome.set("[PENDING]");
	}
	
	public StatMatch(User p1,User p2, int round,String argtable, String rslt) {
		super(p1,p2,round);
		p1name=this.J1.getFirstName()+" "+this.J1.getLastName();
		p2name=this.J2.getFirstName()+" "+this.J2.getLastName();
		p1score=this.J1.getScore();
		p2score=this.J2.getScore();
		tab.set(argtable);
		outcome.set(rslt);
	}
	
	public String getP1name() {
		return p1name;
	}
	public void setP1name(String p1name) {
		this.p1name = p1name;
	}
	public String getP2name() {
		return p2name;
	}
	public void setP2name(String p2name) {
		this.p2name = p2name;
	}
	public double getP1score() {
		return p1score;
	}
	public void setP1score(double p1score) {
		this.p1score = p1score;
	}
	public double getP2score() {
		return p2score;
	}
	public void setP2score(double p2score) {
		this.p2score = p2score;
	}
	public String gettab() {
		return tab.get();
	}
	public void settab(String tab) {
		this.tab.set(tab);
	}
	public StringProperty tabProperty() {
		return tab;
	}
	public String getoutcome() {
		return outcome.get();
	}
	public void setoutcome(String outcome) {
		this.outcome.set(outcome);
	}
	public StringProperty outcomeProperty() {
		return outcome;
	}

	@Override
	public String toString() {
		return "StatMatch [table=" + tab + ", p1name=" + p1name + ", p2name=" + p2name + ", p1score=" + p1score
				+ ", p2score=" + p2score + ", Result=" + outcome + "]";
	}
	
}
