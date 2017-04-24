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
public class Rep extends User{
    protected String  url, facebook, twitter, phoneNum;
    private HashMap<String, Integer> myOfclVotes = new HashMap<>();
    private double CScore; 
    
    public Rep(String firstName, String lastName, String email, String userName, String userID, 
               String password) {
        
        super(firstName, lastName, email, userName, userID, password);
    }
  
    public double getScore(){
        return CScore; 
    }
    
    public void setScore(double CScore){
        this.CScore = CScore; 
    }
    
    public double getOfclVoteFreq(){
        double freq = 0; 
        int votes = myOfclVotes.size(); 
        int time = 1; // all votes possible since first day in office 
        freq = votes/ time; 
        return freq;
    }
    
    public int getOfclVote(String lawID){
       for(Map.Entry<String, Integer> vote: myOfclVotes.entrySet()){
            if(vote.getKey().equals(lawID)){
                return vote.getValue(); 
            } 
        }
        return 2; 
    }
    
    public void setOfclVote(String lawID, int vote){
        myOfclVotes.put(lawID, vote); 
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFacebook(){
        return facebook; 
    }
    
    public void setFacebook(String facebook){
        this.facebook = facebook; 
    }
    
    public String getTwitter(){
        return twitter; 
    }
    
    public void setTwitter(String twitter){
        this.twitter = twitter; 
    }
    
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    @Override
    public String[] toArray(){
        return new String[]{"pr", firstName, lastName, email, userName,
            userID, pswrd, url, phoneNum};
    }
}

    

