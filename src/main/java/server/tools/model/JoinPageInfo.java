package server.tools.model;

/**
 * Java object JoinPageInfo
 * 
 * Note: there are no setters or getters in this class
 * 
 * jakarta json will automatically convert to/from json
 * 
 * @author cook
 *
 */
public class JoinPageInfo {
	// sql join type
    public String joinType;
    //
    public PageInfo pageInfo;
    
	public JoinPageInfo() {
		super();
	}

	@Override
	public String toString() {
		return "JoinPageInfo [joinType=" + joinType + ", pageInfo=" + pageInfo + "]";
	}
    
    
    
}
