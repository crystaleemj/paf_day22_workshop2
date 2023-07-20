package sg.edu.nus.iss.paf_day22_workshop2.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.paf_day22_workshop2.model.RSVP;
import sg.edu.nus.iss.paf_day22_workshop2.repository.RSVPRepo;

@Service
public class RSVPSvc {
    
    @Autowired
    RSVPRepo repo;

    public List<RSVP> listAllRSVP(){
        return repo.listAllRSVP();
    }

    public List<RSVP> listRSVPbyName(String name){
        return repo.listRSVPbyName(name);
    }

    public JsonObject noRSVPFoundError(String name){
        JsonObjectBuilder object = Json.createObjectBuilder()
            .add("errmsg", "No RSVP record found with name: " + name);
            return object.build();
    }

    public Integer countTotalRSVP(){
        return repo.countTotalRSVP();
    }

    public Integer countTotalRSVPsql(){
        return repo.countTotalRSVPsql();
    }

    public JsonObject countRSVPJson(Integer count){
        JsonObjectBuilder object = Json.createObjectBuilder()
            .add("errmsg", "Total number of RSVP count: " + count);
            return object.build();
    }

    public Boolean addNewRSVP(RSVP rsvp){
        return repo.addNewRSVP(rsvp);
    }

    public Boolean updateRSVP(RSVP rsvp){
        return repo.updateRSVP(rsvp);
    }

    public RSVP listRSVPbyID(Integer id){
        return repo.listRSVPbyID(id);
    }
}
