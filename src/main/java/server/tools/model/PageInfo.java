package server.tools.model;

/**
 * Java object PageInfo
 * 
 * Note: there are no setters or getters in this class
 * 
 * jakarta json will automatically convert to/from json
 * 
 * @author cook
 *
 */
public class PageInfo {
	// page number within whole table
    public Integer pageNum;
    // Number of rows in page (content length)
    public Integer pageSize;
    // total number of pages within whole table
    public Integer totalPages;
    
	public PageInfo() {
		super();
	}


	public PageInfo(Integer pageNum, Integer pageSize, Integer totalPages) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalPages = totalPages;
	}


	@Override
	public String toString() {
		return "PageInfo [pageNum=" + pageNum + ", pageSize=" + pageSize + "]";
	}


    
    
    
}
