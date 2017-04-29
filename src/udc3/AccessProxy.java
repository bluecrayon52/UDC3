/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udc3;

import java.util.*;

/**
 *
 * @author nathanielclayarnold
 */
public class AccessProxy implements BaseAdmin{
    
    private UDC3Admin tempAdmin = new UDC3Admin(); 
            
    @Override
    public DataSet getDataSet(String dataSetID) {
       return tempAdmin.getDataSet(dataSetID); 
    }

    @Override
    public DataSet createSubSet(DataSet superSet) {
        return tempAdmin.createSubSet(superSet); 
    }

    @Override
    public boolean verifyLogin(String userID, String pswrd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPswrd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setID(String userID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPswrd(String pswrd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletDataSet(String dataSetID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
