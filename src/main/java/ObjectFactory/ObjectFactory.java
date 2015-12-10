package ObjectFactory;

import Interface.IDataService;
import Service.DataService;

/**
 * Created by saaryal on 12/3/15.
 */
public class ObjectFactory {

	public IDataService getDataObject(){
		return new DataService();
	}
}
