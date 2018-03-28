package Tournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.toIntExact;


public class Tournament {
	
	protected List<User> players = new ArrayList<User>();
	protected List<Match> matchs = new ArrayList<Match>();
	protected User BYE;
	protected String TournamentName;
	protected String Date;
	protected String Location;
	protected int Season;
	protected int Episode;
	
	//Builder
	public Tournament() {
		this.TournamentName="Empty";
		this.Date="01/01/2000";
		this.Location="DTC";
		this.Season=0;
		this.Episode=0;
		this.BYE=new User ("BYE", "BYE", "BYE", "BYE", "BYE", "BYE");
		this.players = new ArrayList<User>();
	}
	public Tournament(String Nam,String Dat, String Loc, int Sson, int Ep) {
		this.TournamentName=Nam;
		this.Date=Dat;
		this.Location=Loc;
		this.Season=Sson;
		this.Episode=Ep;
		this.players = new ArrayList<User>();
		this.BYE=new User ("BYE", "BYE", "BYE", "BYE", "BYE", "BYE");
	}
	//Getters
	public List<User> getplayers() {
		return this.players;
	}
	public List<Match> getmatchs() {
		return this.matchs;
	}
	public String getName() {
		return this.TournamentName;
	}
	public String getDate() {
		return this.Date;
	}
	public String getLocation() {
		return this.Location;
	}
	public int getSeason() {
		return this.Season;
	}
	public int getEpisode() {
		return this.Episode;
	}
	public User getBYE() {
		return this.BYE;
	}
	//Setters
	public void setplayers(List<User> arg) {
		this.players = arg;
	}
	public void setmatchs(List<Match> arg) {
		this.matchs = arg;
	}
	public void setName(String arg) {
		this.TournamentName=arg;
	}
	public void setDate(String arg) {
		this.Date=arg;
	}
	public void setLocation(String arg) {
		this.Location=arg;
	}
	public void setSeason(int arg) {
		this.Season=arg;
	}
	public void setEpisode(int arg) {
		this.Episode=arg;
	}
	public void setBYE(User arg) {
		this.BYE=arg;
	}
	//Methods
	public void Addplayer(User player) {
		players.add(player);
	}
	public void Addround(Match match) {
		matchs.add(match);
	}
	public void SortByScore() {
		/*for(int i=0;i<this.players.size();i++) {
			double arg=this.players.get(i).getOppWin()*1000000000;
			arg=Math.round(arg);
			arg=arg/1000000000;
			this.players.get(i).setOppWin(arg);
		}
		for(int i=0;i<this.players.size();i++) {
			double arg=this.players.get(i).getOppOppWin()*1000000000;
			arg=Math.round(arg);
			arg=arg/1000000000;
			this.players.get(i).setOppOppWin(arg);
		}*/
		this.players.sort(Comparator.comparing(User::getScore).thenComparing(User::getOppWin).thenComparing(User::getOppOppWin).reversed());
	}
	public List<List<User>> GroupByScore(double rounds) {
		List<User> playerlist = this.players;
		List<List<User>> playermap = new ArrayList<>();
		for(double i=rounds;i>=0;i--) {
			List<User> temp = new ArrayList<>();
			for(int j=0;j<playerlist.size();j++) {
				if(playerlist.get(j).getScore()==i) {
					temp.add(playerlist.get(j));
				}
			}
			playermap.add(temp);
		}
		return playermap;
	}
	public List<Match> Matchmaking(List<User> arg, int round) {
		List<Match> matched_players=new ArrayList<>();
		List<Match> submatch= new ArrayList<>();
		List<User> seed_players = new ArrayList<>();
		List<User> tmp_list = new ArrayList<>();
		List<User> backup=new ArrayList<>();
		User bye = new User();
		boolean bye_check=false;
		for(int i=0;i<arg.size();i++) {
			seed_players.add(arg.get(i));
			backup.add(arg.get(i));
		}
		int number_of_players=seed_players.size();
		// BYE DETERMINATION
		int nboflast=1;
		if(number_of_players%2==1) {
			for(int i=seed_players.size()-1;i>=0;i--) {
				if(seed_players.get(seed_players.size()-1).getScore()==seed_players.get(i).getScore()) {
					nboflast++;
				}
			}
			int k=1;
			if(nboflast>1) k=toIntExact(Math.round(Math.random()*(nboflast-1)))+1;
			bye = seed_players.get(seed_players.size()-k);
			System.out.println("Number of last : "+nboflast);
			while(bye.getOppIDList().contains("BYE")) {
				k=toIntExact(Math.round(Math.random()*(nboflast-1)))+1;
				bye = seed_players.get(seed_players.size()-k);
			}
			System.out.println("Bye :"+bye.getFirstName()+" "+bye.getLastName());
			seed_players.remove(seed_players.get(seed_players.size()-k));
			backup.remove(backup.get(backup.size()-k));
			bye_check=true;
		}
		else bye_check=false;
		// SUBPAIRING CHECK
		int counter=0;
		for(int i=0;i<seed_players.size()-1;i++) {
			if(seed_players.get(i).getScore()!=seed_players.get(i+1).getScore()) {
				if(counter%2==1) {
					submatch.add(new Match(seed_players.get(i),seed_players.get(i+1),round));
					seed_players.remove(i);
					seed_players.remove(i);
				}
				counter=0;
			}
		}
		int a=0;
		int bias=1;
		boolean reset=false,start=true,hasproblem=false;
		while(reset||start) {
			matched_players=new ArrayList<>();
			if(reset) reset=false;
			start=false;
			hasproblem=false;
			tmp_list=new ArrayList<>();
			while(!seed_players.isEmpty()) {
				a=toIntExact(Math.round(Math.random()*(seed_players.size()-1)));
				tmp_list.add(seed_players.get(a));
				seed_players.remove(a);
			}
			for(int i=0;i<backup.size();i++) {
				seed_players.add(backup.get(i));
			}
			tmp_list.sort(Comparator.comparing(User::getScore));
			bias=1;
		while(!tmp_list.isEmpty()&&!hasproblem) {
			if(tmp_list.get(0).getOppIDList().contains(tmp_list.get(1).getID())) {
			while(tmp_list.get(0).getOppIDList().contains(tmp_list.get(bias).getID())&&tmp_list.size()>2&&bias<=tmp_list.size()) {
				bias++;
			}
			if(tmp_list.get(0).getScore()!=tmp_list.get(bias).getScore()||tmp_list.size()==2) {
				hasproblem=true;
				System.out.println("RESET ON");
			}
			else {
				matched_players.add(new Match(tmp_list.get(0),tmp_list.get(bias),round));
				tmp_list.remove(bias);
				tmp_list.remove(0);
			}
			bias=1;
			}
			else {
				matched_players.add(new Match(tmp_list.get(0),tmp_list.get(1),round));
				tmp_list.remove(1);
				tmp_list.remove(0);
			}
			if(!submatch.isEmpty()) {
				if(matched_players.get(matched_players.size()).getPlayer1().getScore()<submatch.get(0).getPlayer1().getScore()){
				matched_players.add(submatch.get(0));
				submatch.remove(0);
				}

			}
		}
		if(hasproblem) reset=true;
		//System.out.println("Reset : "+reset+" Hasproblem : "+hasproblem+" taille tmp_list : "+tmp_list.size()+" taille matched : "+matched_players.size());
		//try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}
		}
		Collections.reverse(matched_players);
		if(bye_check) matched_players.add(new Match(bye,this.getBYE(),round));
		return matched_players;
	}
	
	public List<Match> MatchmakingFirstRound(List<User> arg) {
		List<Match> matched_players=new ArrayList<>();
		List<User>grouped_players=new ArrayList<>();
		for(int i=0;i<arg.size();i++) grouped_players.add(arg.get(i));
		User bye = new User();
		boolean bye_check;
		int number_of_players=grouped_players.size();
		if(number_of_players%2==1) {
			int byeID= toIntExact(Math.round(Math.random()*(grouped_players.size()-1)));
			bye = grouped_players.get(byeID);
			grouped_players.remove(byeID);
			bye_check=true;
		}
		else bye_check=false;
		while(!grouped_players.isEmpty()) {
			long index_a = Math.round(Math.random()*(grouped_players.size()-1));
			long index_b = Math.round(Math.random()*(grouped_players.size()-1));
			int a,b;
			a = toIntExact(index_a);
			b = toIntExact(index_b);
			//||map_results.get(grouped_players.get(score).get(a).getID()).getOppIDList().contains(grouped_players.get(score).get(b).getID())
			while(a==b) {
				index_b = Math.round(Math.random()*(grouped_players.size()-1));
				a = toIntExact(index_a);
				b = toIntExact(index_b);
			}
			matched_players.add(new Match(grouped_players.get(a),grouped_players.get(b),0));
			grouped_players.remove(a);
			if(a<b) {
				grouped_players.remove(b-1);
			}
			else grouped_players.remove(b);
		}
		if(bye_check) {
			matched_players.add(new Match(bye,this.getBYE(),0));
		}
		return matched_players;
	}
	
}
