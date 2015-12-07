package ObjectFactory;

import Interface.Data;
import Service.DataService;

/**
 * Created by saaryal on 12/3/15.
 */
public class ObjectFactory {

	public Data getDataObject(){
		return new DataService();
	}
}
