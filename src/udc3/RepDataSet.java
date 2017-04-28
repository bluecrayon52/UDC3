/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udc3;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nathanielclayarnold
 */
public class RepDataSet extends UserDataSet{
    
    @Override 
    protected HashMap populate(){
        // retrieve users from a database
        return null; 
    }
    
     public void filterScore(double low, double high){
         // inclusions
        if (low <= high){
            for(Map.Entry<String, User> rep: users.entrySet()){
                Rep temp = (Rep)rep.getValue();
                double score = temp.getScore(); 
                    if(!(score >= low && score <= high)){
                        users.remove(rep.getKey());
                    }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, User> rep: users.entrySet()){
                Rep temp = (Rep)rep.getValue(); 
                double score = temp.getScore(); 
                    if(!(score >= low || score <= high)){
                        users.remove(rep.getKey());
                    }
            }
        }
        
    }
    
    public void filterOfficialVoteFreq(double low, double high){
        // inclusions
        if (low <= high){
            for(Map.Entry<String, User> rep: users.entrySet()){
                Rep temp = (Rep)rep.getValue(); 
                double freq = temp.getOfclVoteFreq(); 
                    if(!(freq >= low && freq <= high)){
                        users.remove(rep.getKey());
                    }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, User> rep: users.entrySet()){
                Rep temp = (Rep)rep.getValue(); 
                double freq = temp.getOfclVoteFreq(); 
                    if(!(freq >= low || freq <= high)){
                        users.remove(rep.getKey());
                    }
            }
        }
        
    }
    
    public void filterOfficialVote(HashMap<String, Integer> votes){
        for(Map.Entry<String, User> rep : users.entrySet()){
            Rep temp = (Rep)rep.getValue();
            int count = 0;
            
                for(Map.Entry<String, Integer> vote : votes.entrySet()){
                    
                    if(temp.getOfclVote(vote.getKey()) == vote.getValue())
                        break; 
                    else 
                        count++;
                }  
                
                if (count == votes.size())
                    users.remove(rep.getKey());
                
        }
    }
    
}
