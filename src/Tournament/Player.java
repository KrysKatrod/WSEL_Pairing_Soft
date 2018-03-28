package Tournament;

public class Player {
	protected String FirstName;		// First Name of the player
	protected String LastName;		// Last Name of the player
	protected String Surname;			// Surname/nickname of the player
	protected String ID;					// ID of the player
	protected String Country;
	protected String Location;
	protected String FullName;

//Builders
public Player() { 					//Generic Builder
	
	FirstName = "John";
	LastName = "Doe";
	Surname = "Bob";
	ID = "NONE";
	Country = "NONE";
	Location = "NONE";
	FullName=FirstName+" "+Surname+" "+LastName;
}
public Player(String Fname, String Lname, String id, String Nick, String country, String loc) { //Specific builder
	FirstName = Fname;
	LastName = Lname;
	Surname = Nick;
	ID = id; 						//Use WSEL ID
	Country = country;
	Location = loc;
	FullName=Fname+" "+Nick+" "+Lname;
}

//Getters
public String getFirstName() {
	return FirstName;
}
public String getLastName() {
	return LastName;
}
public String getSurname() {
	return Surname;
}
public String getFullName() {
	return FullName;
}
public String getID() {
	return ID;
}
public String getCountry() {
	return Country;
}
public String getLocation() {
	return Location;
}

@Override
public String toString() {
	return "Player [FirstName=" + FirstName + ", LastName=" + LastName + ", Surname=" + Surname + ", ID=" + ID
			+ ", Country=" + Country + ", Location=" + Location + "]";
}
//Setters
public void setFirstName(String arg) {
	FirstName=arg;
}
public void setLastName(String arg) {
	LastName=arg;
}
public void setSurname(String arg) {
	Surname=arg;
}
public void setFullName(String arg) {
	FullName=arg;
}
public void setID(String arg) {
	ID=arg;
}
public void setCountry(String arg) {
	Country=arg;
}
public void setLocation(String arg) {
	Location=arg;
}

//Methods


}