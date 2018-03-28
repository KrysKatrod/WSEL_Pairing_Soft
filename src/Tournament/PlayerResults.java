package Tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerResults {

		private double Score;
		private double Winrate;
		private double OppWin;
		private double OppOppWin;
		private List<String> OppIDList = new ArrayList<String>();
		
		//Builders
		public PlayerResults() {
			Score=0;
			Winrate=0;
			OppWin=0;
			OppOppWin=0;
		}
		public PlayerResults(double sc,double win,double owin, double oowin) {
			Score=sc;
			Winrate=win;
			OppWin=owin;
			OppOppWin=oowin;
		}
		//Getters
		public double getScore() {
			return Score;
		}
		public double getWinrate() {
			return Winrate;
		}
		public double getOppWin() {
			return OppWin;
		}
		public double getOppOppWin() {
			return OppOppWin;
		}
		public List<String> getOppIDList() {
			return OppIDList;
		}
		//Setters
		public void setScore(double arg) {
			this.Score = arg;
		}
		public void setWinrate(double arg) {
			this.Winrate = arg;
		}
		public void setOppWin(double arg) {
			this.OppWin = arg;
		}
		public void setOppOppWin(double arg) {
			this.OppOppWin = arg;
		}
		
		//Methods
		public void AddOpp(String opp) {
			OppIDList.add(opp);
		}
		
		public void Win(String opp) {
			this.setScore(this.Score+1);
			this.AddOpp(opp);
		}
		
		public void Lose(String opp) {
			this.AddOpp(opp);
		}
		
		public void RefreshWinrate(int round) {
			this.Winrate=this.Score/round;
			if(this.Winrate<(0.33)) {
				this.Winrate=(0.33);
			}
		}
		
		public void RefreshOppWin(int round, Map<String,PlayerResults> players) {
			boolean hasbye=false;
			if(round>1) {
			for (int i = 0;i<round;i++) {
				if(!this.OppIDList.get(i).equals("BYE")) {
				this.OppWin=this.OppWin+players.get(this.OppIDList.get(i)).getWinrate();
				}
				else hasbye=true;
			}
			if(!hasbye) this.OppWin=this.OppWin/round;
			else this.OppWin=this.OppWin/(round-1);
			}
			else if(round==1) {
				if(!hasbye) this.OppWin=players.get(this.OppIDList.get(0)).getWinrate();
			}
		}
		
		public void RefreshOppOppWin(int round, Map<String,PlayerResults> players) {
			boolean hasbye=false;
			if(round>1) {
				for (int i = 0;i<round;i++) {
				if(this.OppIDList.get(i)!="BYE") {
					this.OppOppWin=this.OppOppWin+players.get(this.OppIDList.get(i)).getOppWin();
				}
				else hasbye=true;
			}
			if(!hasbye) this.OppOppWin=this.OppOppWin/round;
			else this.OppOppWin=this.OppOppWin/(round-1);
			}
			else if(round==1) {
				if (!hasbye) this.OppOppWin=players.get(this.OppIDList.get(0)).getOppWin();;
			}
		}
		
}
