package Tournament;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BDDScan {

    /*public static void main() {
        // -define .csv file in app
        String fileNameDefined = "Project_0/WSEL Pairing System/WSELBDD.csv";
        // -File class needed to turn stringName to actual file
        File file = new File(fileNameDefined);

        try{
            // -read from filePooped with Scanner class
            Scanner inputStream = new Scanner(file);
            // hashNext() loops line-by-line
            while(inputStream.hasNext()){
                //read single line, put in string
                String data = inputStream.next();
                System.out.println(data + "***");

            }
            // after loop, close scanner
            inputStream.close();


        }catch (FileNotFoundException e){

            e.printStackTrace();
        }

    }*/
	
	public static void main() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Project_0/WSEL Pairing System/WSELBDD.csv"));
        scanner.useDelimiter(";");
        while(scanner.hasNext()){
            System.out.println(scanner.next()+"|");
        }
        scanner.close();
	}
	
}
