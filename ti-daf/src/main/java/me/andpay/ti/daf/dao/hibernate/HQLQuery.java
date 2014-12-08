package me.andpay.ti.daf.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.Type;

/**
 * HQL查询类。
 * 
 * @author sea.bao
 */
public class HQLQuery {
    /**
     * select语句
     */
    private String selectClause;

    /**
     * from语句
     */
    private String fromClause;

    /**
     * 条件语句
     */
    private String whereClause;

    /**
     * Group语句
     */
    private String groupClause;

    /**
     * Order语句
     */
    private String orderClause;

    /**
     * 参数集
     */
    private List<Object> parameters = new ArrayList<Object>();

    public HQLQuery() {
    }

    public String getNonSelectClause() {
        StringBuffer clause = new StringBuffer();
        
        if (fromClause != null && fromClause.equals("") == false) {
            clause.append("from ");
            clause.append(fromClause);
            clause.append(" ");
        }

        if (whereClause != null && whereClause.equals("") == false) {
            clause.append("where ");
            clause.append(whereClause);
            clause.append(" ");
        }

        if (groupClause != null && groupClause.equals("") == false) {
            clause.append("group by ");
            clause.append(groupClause);
            clause.append(" ");
        }

        if (orderClause != null && orderClause.equals("") == false) {
            clause.append("order by ");
            clause.append(orderClause);
            clause.append(" ");
        }

        return clause.toString();
    }

    public String getClause() {
        StringBuffer clause = new StringBuffer();
        if (selectClause != null && selectClause.equals("") == false) {
            clause.append("select ");
            clause.append(selectClause);
            clause.append(" ");
        }

        clause.append(getNonSelectClause());
        
        return clause.toString();
    }

    public void addParameters(List<Object> parameters) {
        this.parameters.addAll(parameters);
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

    public void setParameters(List<Object> parameters, List<Type> types) {
        this.parameters = parameters;
    }

    public List<Object> getParameters() {
        return parameters;
    }
    
    public void addParameter(Object value) {
        this.parameters.add(value);
    }

    public Object[] getHQLArgs() {
        return parameters.toArray();
    }

    public void clearCommand() {
        selectClause = null;
        fromClause = null;
        whereClause = null;
        groupClause = null;
        orderClause = null;
        parameters.clear();
    }

    public String getFromClause() {
        return fromClause;
    }

    public String getGroupClause() {
        return groupClause;
    }

    public String getOrderClause() {
        return orderClause;
    }

    public String getSelectClause() {
        return selectClause;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setFromClause(String string) {
        fromClause = string;
    }

    public void setGroupClause(String string) {
        groupClause = string;
    }

    public void setOrderClause(String string) {
        orderClause = string;
    }

    public void setSelectClause(String string) {
        selectClause = string;
    }

    public void setWhereClause(String string) {
        whereClause = string;
    }

}