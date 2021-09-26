
  
package com.api.SafetyNetAlerts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;




@Data
@Entity
@Table(name = "MedicalRecord")
public class MedicalRecord {

    private static final Logger logger = LogManager.getLogger(MedicalRecord.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
   
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
   @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthdate; 
    
    @ElementCollection
    private List<Allergies> allergies;
    @ElementCollection
    private List<Medications> medications;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public List<Allergies> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<Allergies> allergies) {
		this.allergies = allergies;
	}
	public List<Medications> getMedications() {
		return medications;
	}
	public void setMedications(List<Medications> medications) {
		this.medications = medications;
	}
	
	public MedicalRecord() {
	}

	public MedicalRecord(Long id, String lastName, String firstName, LocalDate birthdate, List<Allergies> allergies,List<Medications> medications) {
		
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthdate = birthdate;
		this.allergies = allergies;
		this.medications = medications;
	}
	@Override
	public String toString() {
		return "MedicalRecord [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", birthdate="
				+ birthdate + ", allergies=" + allergies + ", medications=" + medications + "]";
	}
}
