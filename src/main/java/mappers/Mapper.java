package mappers;

import java.util.HashMap;
import java.util.Map;

public class Mapper {

    private Map<String, String> bindEventsToOp = new HashMap<>();


    /**
     * Constructor
     */
    public Mapper() {
        bindEventsToOp.put("addBtnEvent","CRUD.CREATE");
        bindEventsToOp.put("searchBtnEvent","CRUD.SEARCH");
        bindEventsToOp.put("updateBtnEvent","CRUD.UPDATE");
        bindEventsToOp.put("deleteBtnEvent","CRUD.DELETE");
    }

    public Map<String, String> getBindEventsToOp() {
        return bindEventsToOp;
    }

    public void setBindEventsToOp(Map<String, String> bindEventsToOp) {
        this.bindEventsToOp = bindEventsToOp;
    }
}
