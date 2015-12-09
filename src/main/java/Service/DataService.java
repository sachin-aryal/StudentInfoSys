package Service;

import Interface.IDataService;
import Model.Marks;
import Model.Student;
import ObjectFactory.ObjectFactory;
import au.com.bytecode.opencsv.CSVReader;

import java.io.*;
import java.util.*;

/**
 * Created by saaryal on 12/3/15.
 */
public class DataService implements IDataService {

	public Map<Student, Marks> display(Student student,Marks marks,String filePath[]) {
		Map<Student,Marks> allInfo = new HashMap<Student, Marks>();

		ObjectFactory obj = new ObjectFactory();

		CSVReader reader = null;
		CSVReader reader1 = null;
		try {
			reader = new CSVReader(new FileReader(filePath[0]));
			reader1 = new CSVReader(new FileReader(filePath[1]));

			while (true) {
				String[] partOne = reader.readNext();
				String[] partTwo = reader1.readNext();

				if (partOne == null || partTwo == null)
					break;

				student = obj.getStudentObject();
				student.setRollNo(Integer.parseInt(partOne[0]));
				student.setBatch(Integer.parseInt(partOne[1]));
				student.setName(partOne[2] + " " + partOne[3]);
				student.setAddress(partOne[4]);

				marks = obj.getMarksObject();
				marks.setSemester(partTwo[1]);
				marks.setPercentage(Double.parseDouble(partTwo[2]));
				marks.setStatus(Boolean.parseBoolean(partTwo[3]));

				allInfo.put(student,marks);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}



		return allInfo;
	}

	public void filter() {

	}

	public void search() {

	}

	public void sort() {

	}
}
