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
public class UserDataSet extends DataSet{
    
    @Override
    protected HashMap populate(){ 
        // retrieve users from a database 
        return null;  
    }
   
    @Override
     public void filterParty(ArrayList<Party> parties){
         
         for(Map.Entry<String, User> user : users.entrySet()){
            int count = 0;
            
            for(Party party: parties){
                if (((User)user.getValue()).getMyParty() == party)
                    break; 
                else
                 count++;    
            }
            
            if(count==parties.size())
                users.remove(user.getKey()); 
        }
    }  
     
    @Override
    public void filterState(EnumSet<State> states){
        
        // do nothing
        if (states.isEmpty() || states.size()==50){
            return; 
        }
        
        if (states.size()<=25){
            
            for(Map.Entry<String, User> user : users.entrySet()){
                int count = 0; 
                
                for(State state: states){
                    if (user.getValue().getMyState() == state)
                        break; 
                    else 
                        count++;    
                }
                
                if(count==states.size())
                users.remove(user.getKey()); 
            }
        }
        
        else{
            
            EnumSet<State> negateStates = EnumSet.complementOf(states); 
            
            
            for(Map.Entry<String, User> user : users.entrySet()){
                
                for(State state: negateStates){
                    
                    if (user.getValue().getMyState() == state){
                        users.remove(user.getKey());  
                        break; 
                    }
                }
            
            }
        }
    }
    
    @Override
    public void filterDistrict(ArrayList<Integer> districts){ 
        
        // do nothing 
        if (districts.isEmpty() || districts.size()==435){
            return;
        }
        
        if (districts.size()<=217){
            
            for(Map.Entry<String, User> user : users.entrySet()){
                int count = 0; 
                
                for(Integer district: districts){
                    if (user.getValue().getMyDistrict() == district)
                        break; 
                    else 
                        count++;    
                }
                
                if(count==districts.size())
                users.remove(user.getKey()); 
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
            
            for(Map.Entry<String, User> user : users.entrySet()){
                
                for(Integer district: negateDistricts){
                    
                    if (user.getValue().getMyDistrict()== district){
                        users.remove(user.getKey());  
                        break; 
                    }
                }
            
            }
        }
    }
    
    @Override
    public void filterAge( int low, int high){
        // inclusions
        if (low <= high){
            for(Map.Entry<String, User> user: users.entrySet()){
                int age = user.getValue().getMyAge(); 
                    if(!(age >= low && age <= high)){
                        users.remove(user.getKey());
                    }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, User> user: users.entrySet()){
                int age = user.getValue().getMyAge(); 
                    if(!(age >= low || age <= high)){
                        users.remove(user.getKey());
                    }
            }
        }
    }
    
    @Override
    public void filterComFeq( double low, double high){
        // inclusions
        if (low <= high){
            for(Map.Entry<String, User> user: users.entrySet()){
                double freq = user.getValue().getComFreq(); 
                    if(!(freq >= low && freq <= high)){
                        users.remove(user.getKey());
                    }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, User> user: users.entrySet()){
                double freq = user.getValue().getComFreq(); 
                    if(!(freq >= low || freq <= high)){
                        users.remove(user.getKey());
                    }
            }
        }
    }
    
    
    @Override
    public void filterCom(ArrayList<String> IntityIDs){
        
        for(Map.Entry<String, User> user : users.entrySet()){
            
            int count = 0;
            
            if(user.getValue().getComSize() == 0)
                users.remove(user.getKey()); 
                
            else{
                for(String IntityID : IntityIDs){
                    if(user.getValue().getCom(IntityID)!=null)
                        break; 
                    else 
                        count++;
            }    
                if (count == IntityIDs.size())
                    users.remove(user.getKey());
                
            }
        }
    }
    
    
    @Override
    public void filterVoteFreq(double low, double high){
        // inclusions
        if (low <= high){
            for(Map.Entry<String, User> user: users.entrySet()){
                double freq = user.getValue().getVoteFreq(); 
                if(!(freq >= low && freq <= high)){
                    users.remove(user.getKey());
                }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, User> user: users.entrySet()){
                double freq = user.getValue().getVoteFreq(); 
                if(!(freq >= low || freq <= high)){
                    users.remove(user.getKey());
                }
            }
        }
    }
    
    //0 no, 1 yes, 2 NA 
    @Override
    public void filterVote(HashMap<String, Integer> votes){
        
        for(Map.Entry<String, User> user : users.entrySet()){
            
            int count = 0;
            
                for(Map.Entry<String, Integer> vote : votes.entrySet()){
                    if(user.getValue().getMyVote(vote.getKey()) == vote.getValue())
                        break; 
                    else 
                        count++;
                }  
                
                if (count == votes.size())
                    users.remove(user.getKey());
                
        }
    }
        
    @Override
    public void filterFollow(ArrayList<String> IntityIDs){
        
        for(Map.Entry<String, User> user : users.entrySet()){
            
            int count = 0;
            
            if(user.getValue().getFollowSize() == 0)
                users.remove(user.getKey()); 
                
            else{
                for(String IntityID : IntityIDs){
                    if(user.getValue().doIFollow(IntityID)==true)
                        break; 
                    else 
                        count++;
            }    
                if (count == IntityIDs.size())
                    users.remove(user.getKey());
                
            }
        }
    }
    
    @Override
    public void filterMember(ArrayList<String> groupIDs){
        
        for(Map.Entry<String, User> user : users.entrySet()){
            
            int count = 0;
            
            if(user.getValue().getMemberSize()== 0)
                users.remove(user.getKey()); 
                
            else{
                for(String groupID : groupIDs){
                    if(user.getValue().amIMember(groupID)==true)
                        break; 
                    else 
                        count++;
            }    
                if (count == groupIDs.size())
                    users.remove(user.getKey());
                
            }
        }
    }
    
    @Override
    public void filterEthnicity(ArrayList<Ethnicity> ethnicities){
        for(Map.Entry<String, User> user : users.entrySet()){
            int count = 0;
            
            for(Ethnicity ethnicity: ethnicities){
                if (user.getValue().getEthnicity()== ethnicity)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==ethnicities.size())
                users.remove(user.getKey()); 
        }
    }
    
    @Override
    public void filterEmployment(ArrayList<Employment> employments){
        for(Map.Entry<String, User> user : users.entrySet()){
            int count = 0;
            
            for(Employment employment: employments){
                if (user.getValue().getEmployment()== employment)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==employments.size())
                users.remove(user.getKey()); 
        }
    }
    
    @Override
    public void filterEducation(ArrayList<Education> educations){
        for(Map.Entry<String, User> user : users.entrySet()){
            int count = 0;
            
            for(Education education: educations){
                if (user.getValue().getEducation()== education)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==educations.size())
                users.remove(user.getKey()); 
        }
    }
    
    @Override
    public void filterEarnings(int low, int high){
        // inclusions
        if (low <= high){
            for(Map.Entry<String, User> user: users.entrySet()){
                int earnings = user.getValue().getEarnings(); 
                    if(!(earnings >= low && earnings <= high)){
                        users.remove(user.getKey());
                    }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, User> user: users.entrySet()){
                int earnings = user.getValue().getEarnings(); 
                    if(!(earnings >= low || earnings <= high)){
                        users.remove(user.getKey());
                    }
            }
        }
        
    }
    
    // 0 single, 1 engaged, 2 married, 3 seperated, 4 divorced  
    @Override
    public void filterMaritalSatus(ArrayList<Integer> MaritalStatuses){
        for(Map.Entry<String, User> user : users.entrySet()){
            int count = 0;
            
            for(Integer status: MaritalStatuses){
                if (user.getValue().getMaritalStatus()== status)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==MaritalStatuses.size())
                users.remove(user.getKey()); 
        }
    }
    
    @Override
    public void filterChildren(int low, int high){
        // inclusions
        if (low <= high){
            for(Map.Entry<String, User> user: users.entrySet()){
                int children = user.getValue().getMyChildren(); 
                    if(!(children >= low && children <= high)){
                        users.remove(user.getKey());
                    }
            }
        }
        
        // exclusions 
        else{
            for(Map.Entry<String, User> user: users.entrySet()){
                int children = user.getValue().getMyChildren(); 
                    if(!(children >= low || children <= high)){
                        users.remove(user.getKey());
                    }
            }
        }
        
    }
    
    @Override
    public void filterGender(ArrayList<Gender> genders){
        for(Map.Entry<String, User> user : users.entrySet()){
            int count = 0;
            
            for(Gender gender: genders){
                if (user.getValue().getMyGender()== gender)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==genders.size())
                users.remove(user.getKey()); 
        }
        
    }
    
    @Override
    public void filterSexualOrientation(ArrayList<SexualOrientation> sexualOrientations){
        for(Map.Entry<String, User> user : users.entrySet()){
            int count = 0;
            
            for(SexualOrientation sexualOrientation: sexualOrientations){
                if (user.getValue().getMySexualOrientation()== sexualOrientation)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==sexualOrientations.size())
                users.remove(user.getKey()); 
        }
        
    }
    
    @Override
    public void filterReligion(ArrayList<Religion> religions){
        for(Map.Entry<String, User> user : users.entrySet()){
            int count = 0;
            
            for(Religion religion: religions){
                if (user.getValue().getMyReligion()== religion)
                    break; 
                else; 
                    count++;    
            }
            
            if(count==religions.size())
                users.remove(user.getKey()); 
        }
        
    }
    
    @Override
    public void filterPremium(boolean cond){
        for(Map.Entry<String, User> user : users.entrySet()){
            if(user.getValue().getPremium()!=cond){
                users.remove(user.getKey()); 
            }   
        
        }
    }
    
    public void save(){
        
    }
    
    public void delete(){
        
    } 
}
