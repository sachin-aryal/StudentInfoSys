package Interface;

import Model.Marks;
import Model.Student;

import java.util.List;
import java.util.Map;

/**
 * Created by saaryal on 12/3/15.
 */
public interface IDataService {
	 Map<Student, Marks> display(Student student,Marks marks,String filePath[]);
	 void filter();
	 void search();
	void sort();
}
