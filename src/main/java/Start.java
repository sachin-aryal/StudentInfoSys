import Interface.IDataService;
import Model.Marks;
import Model.Student;
import ObjectFactory.ObjectFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by saaryal on 12/3/15.
 */
public class Start {

	ObjectFactory obj;

	public static void main(String[] args) {

		System.out.println("Welcome to Student Infomation System");
		Scanner in = new Scanner(System.in);

		Start start = new Start();
		while (true) {
			start.startMenu();
		}
	}

	public void startMenu() {
		ObjectFactory obj = new ObjectFactory();
		System.out.println("Enter \n1.Display Data\n2.Search Data\n3.Filter Data\n4.Sort Data\n5.Other(Exit)");
		Scanner in = new Scanner(System.in);
		IDataService data = obj.getDataObject();
		try {
			int val = in.nextInt();
			switch (val) {
				case 1:
					System.out.println("Display Data");
					subMenu("display");
					break;
				case 2:
					System.out.println("Search Data");
					subMenu("search");
					break;
				case 3:
					System.out.println("Filter Data");
					subMenu("filter");
					break;
				case 4:
					System.out.println("Sort Data");
					subMenu("sortMenu");
					break;
				default:
					System.exit(0);

			}
		} catch (InputMismatchException er) {
			System.out.println("Input value should be a number");
			startMenu();
		}
	}

	public void subMenu(String whichMenu) {
		String filePath[] = {"CSVData/student.csv","CSVData/student.csv"};
		obj = new ObjectFactory();
		IDataService data = obj.getDataObject();
		Student student = null;
		Marks marks = null;
		if (whichMenu.equals("display")) {
			data.display(student, marks, filePath);

		} else if (whichMenu.equals("search")) {

		} else if (whichMenu.equals("filter")) {

		} else {

		}

		}
	}


