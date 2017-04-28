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
    public Party getMyParty(); 
    public void setMyParty(Party party);
    public State getMyState(); 
    public void setMyState(State state);
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
    public Ethnicity getEthnicity();
    public void setEthnicity(Ethnicity  ethnicity);
    public Employment getEmployment(); 
    public void setEmployment(Employment employment); 
    public Education getEducation(); 
    public void setEducation(Education education); 
    public int getEarnings(); 
    public void setEarnings(int earnings); 
    public int getMaritalStatus(); 
    public void setMaritalStatus(int maritalStatus); 
    public int getMyChildren();
    public void setMyChildren(int children);
    public Gender getMyGender();
    public void setMyGender(Gender gender);
    public SexualOrientation getMySexualOrientation();
    public void setMySexualOrientation(SexualOrientation sexualOrientation);
    public Religion getMyReligion();
    public void setMyReligion(Religion religion);
    public boolean getPremium(); 
}
