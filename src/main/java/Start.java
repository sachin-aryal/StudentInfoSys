import Interface.IDataService;
import Model.Marks;
import Model.Student;
import ObjectFactory.ObjectFactory;
import Service.DataService;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by saaryal on 12/3/15.
 */
public class Start {

	ObjectFactory obj;
	Scanner intReader = new Scanner(System.in);
	Scanner doubleReader = new Scanner(System.in);
	Scanner stringReader = new Scanner(System.in);

	private final static String filePath[] = {"CSVData/student.csv","CSVData/marks.csv"};


	public static void main(String[] args) {

		System.out.println("Welcome to Student Information System");

		Start start = new Start();
		while (true) {
			start.startMenu();
		}
	}

	public void startMenu() {
		System.out.println("Enter \n1.Display Data\n2.Search Data\n3.Filter Data\n4.Sort Data\n5.Other(Exit)");
		try {
			int val = intReader.nextInt();
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
		obj = new ObjectFactory();
		IDataService data = obj.getDataObject();
		Student student = null;
		Marks marks = null;
		if (whichMenu.equals("display")) {
			Map<Student,Marks> allInfo = DataService.getData(student, marks, filePath, "all", null);
			data.display(student, marks,allInfo);
		} else if (whichMenu.equals("search")) {

			System.out.println("Search By\n1.Roll No\n2.Batch\n3.Name\n4.Semester");
			int val = intReader.nextInt();
			String searchBy="";
			switch (val) {
				case 1:
					searchBy="rollNo";
					break;
				case 2:
					searchBy="batch";
					break;
				case 3:
					searchBy="name";
					break;
				case 4:
					searchBy="semester";
					break;
				default:
					break;
			}
			if(searchBy!=""){
				System.out.println("Enter the "+searchBy);
				String searchByVal = stringReader.nextLine();
				data.search(student, marks, filePath, searchBy, searchByVal);
			}

			} else if (whichMenu.equals("filter")) {
			System.out.println("Enter \n1.Percentage less than\n2.Percentage greater than");
			int val = intReader.nextInt();
			String whichFilter="";
			switch (val) {
				case 1:
					whichFilter="lessPercentage";
					break;
				case 2:
					whichFilter="greaterPercentage";
					break;
				default:
					break;
			}
			if(whichFilter!=""){
				System.out.println("Enter the Percentage value");
				double percentage = doubleReader.nextDouble();
				data.filter(student,marks,filePath,whichFilter,String.valueOf(percentage));
			}

		} else {
			String sortBy="";
			System.out.println("Sort By:\n1.Name\n2.Roll No\n3.Address\n4.Batch\n5.Percentage");
			int val = intReader.nextInt();
			switch (val){
				case 1:
					sortBy="name";
					break;
				case 2:
					sortBy="rollNo";
					break;
				case 3:
					sortBy="address";
					break;
				case 4:
					sortBy="batch";
					break;
				case 5:
					sortBy="percentage";
					break;
				default:
					System.exit(0);
			}
			data.sort(student,marks,filePath,sortBy);

		}

		}
	}


