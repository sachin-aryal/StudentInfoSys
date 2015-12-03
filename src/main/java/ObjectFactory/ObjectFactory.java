package ObjectFactory;

import Interface.Data;
import Interface.Filter;
import Interface.Search;
import Interface.Sort;
import Service.DataService;
import Service.FilterService;
import Service.SearchService;
import Service.SortService;

/**
 * Created by saaryal on 12/3/15.
 */
public class ObjectFactory {
	public Filter getFilterObject(){
		return new FilterService();
	}
	public Search getSearchObject(){
		return new SearchService();
	}
	public Sort getSortObject(){
		return new SortService();
	}
	public Data getDataObject(){
		return new DataService();
	}
}
