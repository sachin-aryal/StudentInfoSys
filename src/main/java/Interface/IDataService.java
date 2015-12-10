package Interface;

import Model.Marks;
import Model.Student;

import java.util.List;
import java.util.Map;

/**
 * Created by saaryal on 12/3/15.
 */
public interface IDataService {
	 void display(Student student,Marks marks,Map<Student,Marks> allInfo);
	 void filter(Student student, Marks marks, String[] filePath, String whichFilter, String percentage);
	 void search(Student student, Marks marks, String[] filePath, String searchBy, String searchByVal);
	void sort(Student student, Marks marks, String[] filePath, String sortBy);
}
