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
public class DataSet {
    
    private String dataKey;  
    private HashMap<String, BaseUser> Users = new HashMap<>(); 
    
    // superSet Constructor 
    public DataSet(){ 
        RequestHandler rhUsers = new RequestHandler("testUser.txt", "testUNtoID.txt");
        Users = rhUsers.getUsers(); 
        }
    
     public void filterParty(ArrayList<Integer> parties){
         
        for(Map.Entry<String, BaseUser> user : Users.entrySet()){
            int count = 0;
            
            for(Integer party: parties){
                if (user.getValue().getMyParty() == party)
                    break; 
                else
                 count++;    
            }
            
            if(count==parties.size())
                Users.remove(user.getKey()); 
        }
    }  
     
    public void filterState(EnumSet<State> states){
        
        // do nothing
        if (states.isEmpty() || states.size()==50){
            return; 
        }
        
        if (states.size()<=25){
            
            for(Map.Entry<String, BaseUser> user : Users.entrySet()){
                int count = 0; 
                
                for(State state: states){
                    if (user.getValue().getMyState() == state)
                        break; 
                    else 
                        count++;    
                }
                
                if(count==states.size())
                Users.remove(user.getKey()); 
            }
        }
        
        else{
            
            EnumSet<State> negateStates = EnumSet.complementOf(states); 
            
            
            for(Map.Entry<String, BaseUser> user : Users.entrySet()){
                
                for(State state: negateStates){
                    
                    if (user.getValue().getMyState() == state){
                        Users.remove(user.getKey());  
                        break; 
                    }
                }
            
            }
        }
    }
    
    public void filterDistrict(ArrayList<Integer> districts){ 
        
        // do nothing 
        if (districts.isEmpty() || districts.size()==435){
            return;
        }
        
        if (districts.size()<=217){
            
            for(Map.Entry<String, BaseUser> user : Users.entrySet()){
                int count = 0; 
                
                for(Integer district: districts){
                    if (user.getValue().getMyDistrict() == district)
                        break; 
                    else 
                        count++;    
                }
                
                if(count==districts.size())
                Users.remove(user.getKey()); 
            }
        }
        
        else{
            int tempCount = 1; 
            int missingDistricts = 435 - districts.size(); 
            
            ArrayList<Integer> negateDistricts = new ArrayList<>(); 
            
            while (tempCount<=435 && negateDistricts.size()<missingDistricts){
                
                if(!districts.contains(tempCount)){
                    negateDistricts.add(tempCount); 
                    tempCount++;  
                }
             
            }
            
            for(Map.Entry<String, BaseUser> user : Users.entrySet()){
                
                for(Integer district: negateDistricts){
                    
                    if (user.getValue().getMyDistrict()== district){
                        Users.remove(user.getKey());  
                        break; 
                    }
                }
            
            }
        }
    }
    
    public void filterAge( int low, int high){
        // inclusions
        if (low <= high){
            for(Map.Entry<String, BaseUser> user: Users.entrySet()){
                int age = user.getValue().getMyAge(); 
                    if(!(age >= low && age <= high)){
                        Users.remove(user.getKey());
                    }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, BaseUser> user: Users.entrySet()){
                int age = user.getValue().getMyAge(); 
                    if(!(age >= low || age <= high)){
                        Users.remove(user.getKey());
                    }
            }
        }
    }
    
    // filter startDate? 
    
    public void filterComFeq( double low, double high){
        // inclusions
        if (low <= high){
            for(Map.Entry<String, BaseUser> user: Users.entrySet()){
                double freq = user.getValue().getComFreq(); 
                    if(!(freq >= low && freq <= high)){
                        Users.remove(user.getKey());
                    }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, BaseUser> user: Users.entrySet()){
                double freq = user.getValue().getComFreq(); 
                    if(!(freq >= low || freq <= high)){
                        Users.remove(user.getKey());
                    }
            }
        }
    }
    
    
    public void filterCom(ArrayList<String> IntityIDs){
        
        for(Map.Entry<String, BaseUser> user : Users.entrySet()){
            
            int count = 0;
            
            if(user.getValue().getComSize() == 0)
                Users.remove(user.getKey()); 
                
            else{
                for(String IntityID : IntityIDs){
                    if(user.getValue().getCom(IntityID)!=null)
                        break; 
                    else 
                        count++;
            }    
                if (count == IntityIDs.size())
                    Users.remove(user.getKey());
                
            }
        }
    }
    
    
    public void filterVoteFreq(double low, double high){
        // inclusions
        if (low <= high){
            for(Map.Entry<String, BaseUser> user: Users.entrySet()){
                double freq = user.getValue().getVoteFreq(); 
                if(!(freq >= low && freq <= high)){
                    Users.remove(user.getKey());
                }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, BaseUser> user: Users.entrySet()){
                double freq = user.getValue().getVoteFreq(); 
                if(!(freq >= low || freq <= high)){
                    Users.remove(user.getKey());
                }
            }
        }
    }
    
    //0 no, 1 yes, 2 NA 
    public void filterVote(HashMap<String, Integer> votes){
        
        for(Map.Entry<String, BaseUser> user : Users.entrySet()){
            
            int count = 0;
            
                for(Map.Entry<String, Integer> vote : votes.entrySet()){
                    if(user.getValue().getMyVote(vote.getKey()) == vote.getValue())
                        break; 
                    else 
                        count++;
                }  
                
                if (count == votes.size())
                    Users.remove(user.getKey());
                
        }
    }
        
    public void filterFollow(ArrayList<String> IntityIDs){
        
        for(Map.Entry<String, BaseUser> user : Users.entrySet()){
            
            int count = 0;
            
            if(user.getValue().getFollowSize() == 0)
                Users.remove(user.getKey()); 
                
            else{
                for(String IntityID : IntityIDs){
                    if(user.getValue().doIFollow(IntityID)==true)
                        break; 
                    else 
                        count++;
            }    
                if (count == IntityIDs.size())
                    Users.remove(user.getKey());
                
            }
        }
    }
    
    public void filterMember(ArrayList<String> groupIDs){
        
        for(Map.Entry<String, BaseUser> user : Users.entrySet()){
            
            int count = 0;
            
            if(user.getValue().getMemberSize()== 0)
                Users.remove(user.getKey()); 
                
            else{
                for(String groupID : groupIDs){
                    if(user.getValue().amIMember(groupID)==true)
                        break; 
                    else 
                        count++;
            }    
                if (count == groupIDs.size())
                    Users.remove(user.getKey());
                
            }
        }
    }
    
    public void filterEthnicity(ArrayList<Integer> ethnicities){
        for(Map.Entry<String, BaseUser> user : Users.entrySet()){
            int count = 0;
            
            for(Integer ethnicity: ethnicities){
                if (user.getValue().getEthnicity()== ethnicity)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==ethnicities.size())
                Users.remove(user.getKey()); 
        }
    }
    
    public void filterEmployment(ArrayList<Integer> employments){
        for(Map.Entry<String, BaseUser> user : Users.entrySet()){
            int count = 0;
            
            for(Integer employment: employments){
                if (user.getValue().getEmployment()== employment)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==employments.size())
                Users.remove(user.getKey()); 
        }
    }
    
    public void filterEducation(ArrayList<Integer> educations){
        for(Map.Entry<String, BaseUser> user : Users.entrySet()){
            int count = 0;
            
            for(Integer education: educations){
                if (user.getValue().getEducation()== education)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==educations.size())
                Users.remove(user.getKey()); 
        }
    }
    
    public void filterEarnings(int low, int high){
        // inclusions
        if (low <= high){
            for(Map.Entry<String, BaseUser> user: Users.entrySet()){
                int earnings = user.getValue().getEarnings(); 
                    if(!(earnings >= low && earnings <= high)){
                        Users.remove(user.getKey());
                    }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, BaseUser> user: Users.entrySet()){
                int earnings = user.getValue().getEarnings(); 
                    if(!(earnings >= low || earnings <= high)){
                        Users.remove(user.getKey());
                    }
            }
        }
        
    }
    
    // 0 single, 1 engaged, 2 married, 3 seperated, 4 divorced  
    public void filterMaritalSatus(ArrayList<Integer> MaritalStatuses){
        for(Map.Entry<String, BaseUser> user : Users.entrySet()){
            int count = 0;
            
            for(Integer status: MaritalStatuses){
                if (user.getValue().getMaritalStatus()== status)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==MaritalStatuses.size())
                Users.remove(user.getKey()); 
        }
    }
    
    public void filterChildren(int low, int high){
        // inclusions
        if (low <= high){
            for(Map.Entry<String, BaseUser> user: Users.entrySet()){
                int children = user.getValue().getMyChildren(); 
                    if(!(children >= low && children <= high)){
                        Users.remove(user.getKey());
                    }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, BaseUser> user: Users.entrySet()){
                int children = user.getValue().getMyChildren(); 
                    if(!(children >= low || children <= high)){
                        Users.remove(user.getKey());
                    }
            }
        }
        
    }
    
    public void filterGender(ArrayList<Integer> genders){
        for(Map.Entry<String, BaseUser> user : Users.entrySet()){
            int count = 0;
            
            for(Integer gender: genders){
                if (user.getValue().getMyGender()== gender)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==genders.size())
                Users.remove(user.getKey()); 
        }
        
    }
    
    public void filterSexualOrientation(ArrayList<Integer> sexualOrientations){
        for(Map.Entry<String, BaseUser> user : Users.entrySet()){
            int count = 0;
            
            for(Integer sexualOrientation: sexualOrientations){
                if (user.getValue().getMySexualOrientation()== sexualOrientation)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==sexualOrientations.size())
                Users.remove(user.getKey()); 
        }
        
    }
    
    public void filterReligion(ArrayList<Integer> religions){
        for(Map.Entry<String, BaseUser> user : Users.entrySet()){
            int count = 0;
            
            for(Integer religion: religions){
                if (user.getValue().getMyReligion()== religion)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==religions.size())
                Users.remove(user.getKey()); 
        }
        
    }
    
    public void filterPremium(boolean cond){
        for(Map.Entry<String, BaseUser> user : Users.entrySet()){
            if(user.getValue().getPremium()!=cond){
                Users.remove(user.getKey()); 
            }   
        
        }
    }
    
    public void save(){
        
    }
    
    public void delete(){
        
    } 
}
