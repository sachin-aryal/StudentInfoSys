import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by saaryal on 12/3/15.
 */
public class Start {

	public static void main(String[] args) {

		System.out.println("Welcome to Student Infomation System");
		Scanner in = new Scanner(System.in);

		Start start = new Start();
		while (true){
			start.startMenu();
		}
	}
	public void startMenu(){
		System.out.println("Enter \n1.Display Data\n2.Search Data\n3.Filter Data\n4.Sort Data\n5.Other(Exit)");
		Scanner in = new Scanner(System.in);
		try {
			int val = in.nextInt();
			switch (val){
				case 1:
					System.out.println("Display Data");

					break;
				case 2:
					System.out.println("Search Data");
					break;
				case 3:
					System.out.println("Filter Data");
					break;
				case 4:
					System.out.println("Sort Data");
					break;
				default:System.exit(0);

			}
		}catch (InputMismatchException er){
			System.out.println("Input value should be a number");
			startMenu();
		}
	}
}
