/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udc3;

/**
 *
 * @author nathanielclayarnold
 */
public enum Gender {
    AGENDER,
    ANDROGYNE,
    ANDROGYNOUS,
    BIGENDER,
    CIS,
    CISGENDER,
    CIS_FEMALE,
    CIS_MALE,
    CIS_MAN,
    CIS_WOMAN,
    CISGENDER_FEMALE,
    CISGENDER_MALE,
    CISGENDER_MAN,
    CISGENDER_WOMAN,
    FEMALE_TO_MALE,
    FTM,
    GENDER_FLUID,
    GENDER_NONCONFORMING,
    GENDER_QUESTIONING,
    GENDER_VARIANT,
    GENDERQUEER,
    INTERSEX,
    MALE_TO_FEMALE,
    MTF,
    NEITHER,
    NEUTROIS,
    NON_BINARY,
    OTHER,
    PANGENDER,
    TRANS,
    TRANSASTERISK, 
    Trans_Female,
    TRANSASTERISK_FEMALE("trans* female"),
    TRANS_MALE,
    TRANSASTERISK_MALE("trans* male"),
    TRANS_MAN,
    TRANSASTERISK_MAN("trans* man"),
    TRANS_PERSON,
    TRANSASTERISK_PERSON("trans* person"),
    TRANS_WOMAN,
    TRANSASTERISK_WOMAN("trans* woman"),
    TRANSFEMININE,
    TRANSGENDER,
    TRANSGENDER_FEMALE,
    TRANSGENDER_MALE,
    TRANSGENDER_MAN,
    TRANSGENDER_PERSON,
    TRANSGENDER_WOMAN,
    TRANSMASCULINE,
    TRANSSEXUAL,
    TRANSSEXUAL_FEMALE,
    TRANSSEXUAL_MALE,
    TRANSSEXUAL_MAN,
    TRANSSEXUAL_PERSON,
    TRANSSEXUAL_WOMAN,
    TWO_SPIRIT; 

    private final String text; 
    
    private Gender(final String text){
      this.text = text;   
    }
    
    private Gender(){
      this.text = name().replaceAll("_", " ").toLowerCase(); 
    }
    
    @Override
    public String toString(){
    return text; 
}
}