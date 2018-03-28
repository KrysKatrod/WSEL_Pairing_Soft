package Tournament;

import java.util.Map;

public class Match {
		
		protected User J1;
		protected User J2;
		protected int round;
		protected String Result;
		
		//Builders
		public Match() {
			this.J1 = new User();
			this.J2 = new User();
			this.round = 0;
			this.Result = "PENDING";
		}
		
		public Match(User p1,User p2, int round)
		{
			this.J1 = p1;
			this.J2 = p2;
			this.round= round;
			this.Result = "PENDING";
		}
		//Getters
		public User getPlayer1() {
			return this.J1;
		}
		public User getPlayer2() {
			return this.J2;
		}
		public int getRound() {
			return this.round;
		}
		public String getResult() {
			return this.Result;
		}
		//Setters
		public void setPlayer1(User arg) {
			this.J1 = arg;
		}
		public void setPlayer2(User arg) {
			this.J2 = arg;
		}
		public void setRound(int arg) {
			this.round = arg;
		}
		//Methods
		public void setResults(Map<String,User> results_list,String arg) {
			if(this.J1.getID().equals("BYE")||this.J2.getID().equals("BYE")) {
				if(this.J1.getID().equals("BYE")) results_list.get(this.J2.getID()).Win(this.J1.getID());
				if(this.J2.getID().equals("BYE")) results_list.get(this.J1.getID()).Win(this.J2.getID());
			}
			else {
			switch(arg) {
			case("Left") :
				results_list.get(this.J1.getID()).Win(this.J2.getID());
				results_list.get(this.J2.getID()).Lose(this.J1.getID());
				break;
			case("Right") :
				results_list.get(this.J2.getID()).Win(this.J1.getID());
				results_list.get(this.J1.getID()).Lose(this.J2.getID());
				break;
			case("DRAW") :
				results_list.get(this.J2.getID()).Lose(this.J1.getID());
				results_list.get(this.J1.getID()).Lose(this.J2.getID());
				break;
			}
			}
			this.Result=arg;
			
		}
		
}
