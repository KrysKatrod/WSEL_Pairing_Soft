package Tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User extends Player {

	private double Score;
	private double Winrate;
	private double OppWin;
	private double OppOppWin;
	private List<String> OppIDList = new ArrayList<String>();
	
	public User() {
		// TODO Auto-generated constructor stub
		Score=0;
		OppWin=0;
		OppOppWin=0;
		Winrate=0;
	}

	public User (String Fname, String Lname, String id, String Nick, String country, String loc) {
		super(Fname, Lname, Nick, id, country, loc);
		// TODO Auto-generated constructor stub
		Score=0;
		OppWin=0;
		OppOppWin=0;
		Winrate=0;
		FullName=Fname+" "+Nick+" "+Lname;
	}
	
	public User (Player player) {
		this.setFirstName(player.getFirstName());
		this.setLastName(player.getLastName());
		this.setSurname(player.getSurname());
		this.setID(player.getID());
		this.setCountry(player.getCountry());
		this.setLocation(player.getLocation());
		this.setFullName(player.getFullName());
		Score=0;
		OppWin=0;
		OppOppWin=0;
		Winrate=0;
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
	@Override
	public String toString() {
		return "Player [FirstName=" + FirstName + ", LastName=" + LastName + ", Surname=" + Surname + ", ID=" + ID + ", Country=" + Country + ", Location=" + Location + "] [Score=" + Score + ", Winrate=" + Winrate + ", OppWin=" + OppWin + ", OppOppWin=" + OppOppWin
				+ ", OppIDList=" + OppIDList + "]";
	}

	//Setters
	public void setScore(double arg) {
		this.Score=arg;
	}
	public void setWinrate(double arg) {
		this.Winrate = arg;
	}
	public void setOppWin(double arg) {
		this.OppWin=arg;
	}
	public void setOppOppWin(double arg) {
		this.OppOppWin=arg;
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
	
	public void RefreshOppWin(int round, Map<String,User> players) {
		boolean hasbye=false;
		if(round>1) {
		for (int i = 0;i<round-1;i++) {
			if(!this.OppIDList.get(i).equals("BYE")) {
			this.OppWin=this.OppWin+players.get(this.OppIDList.get(i)).getWinrate();
			}
			else hasbye=true;
		}
		if(!hasbye) this.OppWin=this.OppWin/round;
		else this.OppWin=this.OppWin/(round-1);
		}
		else if(round==1) {
			if(!this.OppIDList.get(0).equals("BYE")) {
				this.OppWin=players.get(this.OppIDList.get(0)).getWinrate();
				}
		}
		this.OppWin=this.OppWin*1000000000;
		this.OppWin=Math.round(this.OppWin);
		this.OppWin=this.OppWin/1000000000;
	}
	
	public void RefreshOppOppWin(int round, Map<String,User> players) {
		boolean hasbye=false;
		if(round>1) {
			for (int i = 0;i<round-1;i++) {
			if(this.OppIDList.get(i)!="BYE") {
				this.OppOppWin=this.OppOppWin+players.get(this.OppIDList.get(i)).getOppWin();
			}
			else hasbye=true;
		}
		if(!hasbye) this.OppOppWin=this.OppOppWin/round;
		else this.OppOppWin=this.OppOppWin/(round-1);
		}
		else if(round==1) {
			if (!this.OppIDList.get(0).equals("BYE")) this.OppOppWin=players.get(this.OppIDList.get(0)).getOppWin();;
		}
		this.OppOppWin=this.OppOppWin*1000000000;
		this.OppOppWin=Math.round(this.OppOppWin);
		this.OppOppWin=this.OppOppWin/1000000000;
	}
}
