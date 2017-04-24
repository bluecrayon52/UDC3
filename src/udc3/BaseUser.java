/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udc3;

import java.util.List;

/**
 *
 * @author nathanielclayarnold
 */
public interface BaseUser {
     
    public boolean verifyLogin(String userID, String pswrd); 
    public String getID(); 
    public String getPswrd(); 
    public void setID(String userID); 
    public void setPswrd(String pswrd);
    public int getMyAge();
    public int getMyParty(); 
    public void setMyParty(int party);
    public int getMyState(); 
    public void setMyState(int state);
    public int getMyDistrict();
    public void setMyDistrict(int district);
    public void setMyAge(String birthday);
    public double getComFreq(); 
    public List<String> getCom(String intityID);
    public int getComSize();
    public void addComment(String intityID, String com);
    public double getVoteFreq();
    public int getMyVote(String lawID);
    public void setMyVote(String lawID, int vote);
    public int getVotesSize();
    public boolean doIFollow(String intityID);
    public void follow(String intityID);
    public int getFollowSize();
    public boolean amIMember(String groupID); 
    public int getMemberSize();
    public int getEthnicity();
    public void setEthnicity(int ethnicity);
    public int getEmployment(); 
    public void setEmployment(int employment); 
    public int getEducation(); 
    public void setEducation(int education); 
    public int getEarnings(); 
    public void setEarnings(int earnings); 
    public int getMaritalStatus(); 
    public void setMaritalStatus(int maritalStatus); 
    public int getMyChildren();
    public void setMyChildren(int children);
    public int getMyGender();
    public void setMyGender(int gender);
    public int getMySexualOrientation();
    public void setMySexualOrientation(int sexualOrientation);
    public int getMyReligion();
    public void setMyReligion(int religion);
    public boolean getPremium(); 
    public void setPremium(); 
    
     
    
}
