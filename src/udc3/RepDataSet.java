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
public class RepDataSet extends DataSet{
    
    private String dataKey;
    private HashMap<String, Rep> Users = new HashMap<>();
    
    public RepDataSet(){
        RequestHandler rhReps = new RequestHandler("testReps.txt", "testRNtoID.txt");
        Users = rhReps.getReps(); 
        }
   
     public void filterScore(double low, double high){
         // inclusions
        if (low <= high){
            for(Map.Entry<String, Rep> rep: Users.entrySet()){
                double score = rep.getValue().getScore(); 
                    if(!(score >= low && score <= high)){
                        Users.remove(rep.getKey());
                    }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, Rep> rep: Users.entrySet()){
                double score = rep.getValue().getScore(); 
                    if(!(score >= low || score <= high)){
                        Users.remove(rep.getKey());
                    }
            }
        }
        
    }
    
    public void filterOfficialVoteFreq(double low, double high){
        // inclusions
        if (low <= high){
            for(Map.Entry<String, Rep> rep: Users.entrySet()){
                double freq = rep.getValue().getOfclVoteFreq(); 
                    if(!(freq >= low && freq <= high)){
                        Users.remove(rep.getKey());
                    }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, Rep> rep: Users.entrySet()){
                double freq = rep.getValue().getOfclVoteFreq(); 
                    if(!(freq >= low || freq <= high)){
                        Users.remove(rep.getKey());
                    }
            }
        }
        
    }
    
    public void filterOfficialVote(HashMap<String, Integer> votes){
        for(Map.Entry<String, Rep> rep : Users.entrySet()){
            
            int count = 0;
            
                for(Map.Entry<String, Integer> vote : votes.entrySet()){
                    if(rep.getValue().getOfclVote(vote.getKey()) == vote.getValue())
                        break; 
                    else 
                        count++;
                }  
                
                if (count == votes.size())
                    Users.remove(rep.getKey());
                
        }
    }
    
}
