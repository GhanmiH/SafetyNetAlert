package com.api.SafetyNetAlerts.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.SafetyNetAlerts.model.FireStation;
import com.api.SafetyNetAlerts.model.FloodList;
import com.api.SafetyNetAlerts.model.FloodPeople;
import com.api.SafetyNetAlerts.model.Person;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloodService {

    @Autowired
    FireStationService firestationService;
    @Autowired
    PersonService personService;
    @Autowired
    MedicalRecordService medicalRecordService;

    private Logger logger = LogManager.getLogger(FloodService.class);

   
	public List getPeopleWhenFloodFromStationNumber(List<String> stationNumbers) {

        List<FloodList> floodLists = new ArrayList<>();
        for (String stationNumber : stationNumbers
        ) {
            Iterable<FireStation> firestations = firestationService.getFirestationsFromStationNumber(stationNumber);
            for (FireStation firestation : firestations
            ) {
                List<FloodPeople> floodPeopleList = new ArrayList<>();
                FloodList floodList = new FloodList();
                Iterable<Person> persons = personService.getPersonFromAddress(firestation.getAddress());
                for (Person person : persons
                ) {
                    int age = personService.getAge(person.getLastName(), person.getFirstName());
					List allergies = medicalRecordService.getMedicalRecordFromLastNameAndFirstName(person.getLastName(), person.getFirstName()).getAllergies();
					List medication = medicalRecordService.getMedicalRecordFromLastNameAndFirstName(person.getLastName(), person.getFirstName()).getMedications();

                    FloodPeople floodPeople = new FloodPeople();
                    floodPeople.setLastName(person.getLastName());
                    floodPeople.setFirstName(person.getFirstName());
                    floodPeople.setAge(age);
                    floodPeople.setPhone(person.getPhone());
                    floodPeople.setAllergies(allergies);
                    floodPeople.setMedications(medication);

                    floodPeopleList.add(floodPeople);
                }
                floodList.setAddress(firestation.getAddress());
                floodList.setStation(stationNumber);
                floodList.setFloodPeopleList(floodPeopleList);
                floodLists.add(floodList);
            }
        }
            logger.info("réponse en retour à la requête sur le endpoint /flood/stations avec le paramètre stationNumber: " + stationNumbers);
            return floodLists;

    }
}
