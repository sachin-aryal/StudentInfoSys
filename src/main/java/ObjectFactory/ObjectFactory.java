package ObjectFactory;

import Interface.IDataService;
import Model.Marks;
import Model.Student;
import Service.DataService;

/**
 * Created by saaryal on 12/3/15.
 */
public class ObjectFactory {

	public IDataService getDataObject(){
		return new DataService();
	}
	public Student getStudentObject(){return new Student();}
	public Marks getMarksObject(){return new Marks();}
}
