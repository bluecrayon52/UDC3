/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udc3;
import java.util.*; 
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
 
/**
 *
 * @author nathanielclayarnold
 */
public class User implements BaseUser {
   protected String firstName, lastName, email, userName, userID, pswrd;
   protected boolean isPremium; 
    
    // some ints represent string that are chosen from drop down boxes in GUI
   protected int district, earnings, maritalStatus, children; 
   
   protected State state; 
   protected Party party; 
   protected Gender gender; 
   protected Ethnicity ethnicity; 
   protected Employment employment; 
   protected Religion religion; 
   protected Education education; 
   protected SexualOrientation sexualOrientation; 
   
   
   protected LocalDate birthday; 
   protected LocalDate start; 
   
   protected HashMap<String, Integer> myVotes = new HashMap<>(); 
   protected HashMap<String, List<String>> myComments = new HashMap<>(); 
   
   protected List<String> following = new ArrayList<>(); 
   protected List<String> memberships = new ArrayList<>();  
   
   protected User(String firstName, String lastName, String email, 
                    String userName, String userID, String pswrd, Boolean prem) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.userID = userID;
        this.pswrd = pswrd; 
        isPremium = prem; 
        setStartDate();
    }

    @Override
    public boolean verifyLogin(String userID, String pswrd) {
        return this.userID.equals(userID) && this.pswrd.equals(pswrd);
    }

    @Override
    public String getID() {
        return userID; 
    }
    
    @Override
    public void setID(String userID) {
        this.userID = userID; 
    }
    
    @Override
    public String getPswrd() {
        return pswrd; 
    }

    @Override
    public void setPswrd(String pswrd) {
        this.pswrd = pswrd; 
    }
   @Override
    public Party getMyParty(){
        return party; 
    }
    
   @Override
    public void setMyParty(Party party){
        this.party = party; 
    }
    
   @Override
    public State getMyState(){
        return state; 
    }
    
   @Override
    public void setMyState(State state){
        this.state = state; 
    }
    
   @Override
    public int getMyDistrict(){
        return district; 
    }
    
   @Override
    public void setMyDistrict(int district){
        this.district = district; 
    }
    
   @Override
    public int getMyAge(){
        int age = 0; 
        LocalDate today = LocalDate.now(); 
        Period p = Period.between(birthday, today); 
        age = p.getYears();
        return age; 
    }
    
   @Override
    public void setMyAge(String birthday){
        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.birthday = LocalDate.parse(birthday, DATE_FORMAT); 
    }
    
    private void setStartDate(){
        start = LocalDate.now(); 
    }
    
    public LocalDate getStartDate(){
        return start; 
    }
    
   @Override
    public double getComFreq(){
        double freq = 0; 
        int comms = 0;
        for (Map.Entry<String, List<String>> comments : myComments.entrySet()){
            comms+= comments.getValue().size(); 
        }
        // time from start to now in months 
        int time = Period.between(getStartDate(), LocalDate.now()).getMonths(); 
        freq = comms/ time;   
        return freq;    
    }
    
   @Override
    public List<String> getCom(String intityID){
        for(Map.Entry<String, List<String>> comment: myComments.entrySet()){
            if(comment.getKey().equals(intityID)){
                return comment.getValue(); 
            }
        }
        return null; 
    }
    
   @Override
    public int getComSize(){
        return myComments.size(); 
    }
    
   @Override
    public void addComment(String intityID, String com){
        int count = 0; 
        for(Map.Entry<String, List<String>> comment : myComments.entrySet()){
            if(comment.getKey().equals(intityID)){
                comment.getValue().add(com); 
            }
            else count++; 
        }
        if(count == myComments.size()){
            List<String> coms = new ArrayList<>();  
            coms.add(com); 
            myComments.put(intityID, coms); 
        }
    }
    
   @Override
    public double getVoteFreq(){
        double freq = 0; 
        int votes = myVotes.size(); 
        int time = Period.between(getStartDate(), LocalDate.now()).getMonths();
        freq = votes/ time; 
        return freq;   
    }
    
   @Override
    public int getMyVote(String lawID){
        for(Map.Entry<String, Integer> vote: myVotes.entrySet()){
            if(vote.getKey().equals(lawID)){
                return vote.getValue(); 
            }
        }
        return 2;  // 0 yes, 1 No, 2 NA 
    }
    
   @Override
    public void setMyVote(String lawID, int vote){
        myVotes.put(lawID, vote); 
    }
    
   @Override
    public int getVotesSize(){
        return myVotes.size(); 
    }
    
   @Override
    public boolean doIFollow(String intityID){
        for(String law : following){
            if (law.equals(intityID)){
                return true; 
            }
        }
        return false; 
    }
    
   @Override
    public void follow(String intityID){
        following.add(intityID); 
    }
    
   @Override
    public int getFollowSize(){
        return following.size(); 
    }
    
   @Override
    public boolean amIMember(String groupID){
        for(String group : memberships){
            if(group.equals(groupID)){
                return true; 
            }
        }
        return false; 
    }
    
    public void joinGroup(String groupID){
        memberships.add(groupID); 
    }
    
   @Override
    public int getMemberSize(){
        return memberships.size(); 
    }
    
   @Override
    public Ethnicity getEthnicity(){
        return ethnicity; 
    }
    
   @Override
    public void setEthnicity(Ethnicity ethnicity){
        this.ethnicity = ethnicity; 
    }
    
   @Override
    public Employment getEmployment(){
        return employment; 
    }
    
   @Override
    public void setEmployment(Employment employment){
        this.employment = employment; 
    }
    
   @Override
    public Education getEducation(){
        return education; 
    }
    
   @Override
    public void setEducation(Education education){
        this.education = education; 
    }
    
   @Override
    public int getEarnings(){
        return earnings; 
    }
    
   @Override
    public void setEarnings(int earnings){
        this.earnings = earnings; 
    }
    
   @Override
    public int getMaritalStatus(){
        return maritalStatus; 
    }
    
   @Override
    public void setMaritalStatus(int maritalStatus){
        this.maritalStatus = maritalStatus; 
    }
    
   @Override
    public int getMyChildren(){
        return children; 
    }
    
   @Override
    public void setMyChildren(int children){
        this.children = children; 
    }
    
   @Override
    public Gender getMyGender(){
        return gender; 
    }
    
   @Override
    public void setMyGender(Gender gender){
        this.gender = gender; 
    }
    
   @Override
    public SexualOrientation getMySexualOrientation(){
        return sexualOrientation; 
    }
    
   @Override
    public void setMySexualOrientation(SexualOrientation sexualOrientation){
        this.sexualOrientation = sexualOrientation; 
    }
    
   @Override
    public Religion getMyReligion(){
        return religion; 
    }
    
   @Override
    public void setMyReligion(Religion religion){
        this.religion = religion; 
    }
    
    protected String getFirstName() {
        return firstName;
    }

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    protected String getLastName() {
        return lastName;
    }

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    protected String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected String getUserName() {
        return userName;
    }

    protected void setUserName(String userName) {
        this.userName = userName;
    }

    protected boolean sendMessage(String username){
        return false;
    }
    
   @Override
    public boolean getPremium(){
        return isPremium; 
    }
    
    protected String[] toArray(){
        
        return new String[]{"u", firstName, lastName, email, userName,
            userID, pswrd };
    }
}



