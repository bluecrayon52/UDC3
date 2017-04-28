/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udc3;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

/**
 *
 * @author nathanielclayarnold
 */
public abstract class DataSet {
    protected HashMap<String, User> users;

    public DataSet(){ 
        users = populate();
    }
    
    abstract protected HashMap populate(); 

    abstract public void filterParty(ArrayList<Party> parties);
    
    abstract public void filterState(EnumSet<State> states);
    
    abstract public void filterDistrict(ArrayList<Integer> districts);
    
    abstract public void filterAge( int low, int high); 
    
    abstract public void filterComFeq( double low, double high);
    
    abstract public void filterCom(ArrayList<String> IntityIDs);
    
    abstract public void filterVoteFreq(double low, double high);
    
    abstract public void filterVote(HashMap<String, Integer> votes); 
    
    abstract public void filterFollow(ArrayList<String> IntityIDs); 
    
    abstract public void filterMember(ArrayList<String> groupIDs);
    
    abstract public void filterEthnicity(ArrayList<Ethnicity> ethnicities);
    
    abstract public void filterEmployment(ArrayList<Employment> employments);
    
    abstract public void filterEducation(ArrayList<Education> educations);
    
    abstract public void filterEarnings(int low, int high);
    
    abstract public void filterMaritalSatus(ArrayList<Integer> MaritalStatuses);
    
    abstract public void filterChildren(int low, int high);
    
    abstract public void filterGender(ArrayList<Gender> genders);
    
    abstract public void filterSexualOrientation(ArrayList<SexualOrientation> sexualOrientations);
    
    abstract public void filterReligion(ArrayList<Religion> religions);
    
    abstract public void filterPremium(boolean cond);
}
