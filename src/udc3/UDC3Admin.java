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
public class UDC3Admin implements BaseAdmin{
    
    private String AdminID, AdminPswrd; 
    private DataSet superSet;  
    private HashMap<String, DataSet> DataSetHash = new HashMap<>();
    private HashMap<String, TempAdmin> AccessHash = new HashMap<>(); 
    
    @Override
    public DataSet getDataSet(String dataSetID) {
        for(Map.Entry<String, DataSet> dataSet: DataSetHash.entrySet()){
            if (dataSet.getKey().equals(dataSetID)){
                return dataSet.getValue(); 
            }
        }
        return null; 
    }
    public void populateUserSuperSet(){
        superSet = new UserDataSet(); 
    }
    
   public void populateRepSuperSet(){
       superSet = new RepDataSet(); 
   }
   
    @Override
    public DataSet createSubSet(DataSet superSet) {
        
        if(){
            superSet.filterAge(0, 0);
        }

        if(){
            superSet.filterChildren(0, 0);
        }
        
        if(){
            
            superSet.filterCom(IntityIDs);
        }
        
        if(){
            superSet.filterComFeq(0, 0);
        }
        
        if(){
            superSet.filterDistrict(districts);
        }
        
        if(){
            superSet.filterEarnings(0, 0);
        }
        
        if(){
            superSet.filterEducation(educations);
        }
        
        if(){
            superSet.filterEmployment(employments);
        }
        
        if(){
            superSet.filterEthnicity(ethnicities);
        }
        
        if(){
            superSet.filterFollow(IntityIDs);
        }
        
        if(){
            superSet.filterGender(genders);
        }
        
        if(){
            superSet.filterMaritalSatus(0);
        }
        
        if(){
            superSet.filterMember(groupIDs);
        }
        
        if(){
            superSet.filterParty(parties);
        }
        
        if(){
            superSet.filterPremium(true);
        }
        
        if(){
            superSet.filterReligion(religions);
        }
        
        if(){
            superSet.filterSexualOrientation(sexualOrientations);
        }
        
        if(){
            superSet.filterState(states);
        }
        
        if(){
            superSet.filterVote(votes);
        }
        
        if(){
            superSet.filterVoteFreq(0, 0);
        }
        
        return superSet; 
    }
    
    public RepDataSet createRepSubSet(){
        
        RepDataSet repSetCast = (RepDataSet)superSet; 
        
        if(){
            repSetCast.filterScore(0, 0);
        }
        
        if(){
            repSetCast.filterOfficialVote(votes);
        }
        
        if(){
            repSetCast.filterOfficialVoteFreq(0, 0);
        }
        
        return repSetCast; 
    }
    
    @Override
    public void deletDataSet(String dataSetID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifyLogin(String userID, String pswrd) {
        return this.AdminID.equals(userID) && this.AdminPswrd.equals(pswrd);
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
    
    
    
}
