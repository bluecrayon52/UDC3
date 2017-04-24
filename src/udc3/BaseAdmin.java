/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udc3;

/**
 *
 * @author nathanielclayarnold
 */
public interface BaseAdmin {
    
    public boolean verifyLogin(String userID, String pswrd); 
    public String getID(); 
    public String getPswrd(); 
    public void setID(String userID); 
    public void setPswrd(String pswrd);
    public DataSet getDataSet(String dataSetID); 
    public DataSet createSubSet(DataSet superSet); 
    public void deletDataSet(String dataSetID);  
}
