package server.tools.model;

import java.util.List;

/**
 * Page of data modeled as database rows of an object with type T
 * 
 * @author cook
 * @param <T> Type of object in list of rows, in the page content
 */
public class PageData<T>  {
	// page sizing info
	public PageInfo pageInfo;
    // Page content is a list of rows from database. Each row is an object
    // of type T
    public List<T> content;

    public PageData() {
    }
    
    public PageData(PageInfo pageInfo, List<T> content) {
        this.pageInfo = pageInfo;
        this.content = content;
    }

	@Override
	public String toString() {
		return "PageData [pageInfo=" + pageInfo + "]";
	}
   
    
}
