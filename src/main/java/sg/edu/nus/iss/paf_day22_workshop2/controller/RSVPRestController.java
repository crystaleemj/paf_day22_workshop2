package sg.edu.nus.iss.paf_day22_workshop2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf_day22_workshop2.model.RSVP;
import sg.edu.nus.iss.paf_day22_workshop2.service.RSVPSvc;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping(path = "/api")
public class RSVPRestController {

    @Autowired
    RSVPSvc svc;
    
    
    @GetMapping(path = "/rsvps", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RSVP>> listAllRSVP() {
        
        return new ResponseEntity<List<RSVP>>(svc.listAllRSVP(), HttpStatus.OK);
    }

    @GetMapping(path = "/rsvp")
    public ResponseEntity<?> listRSVPbyNamEntity(@RequestParam String name) {

        List<RSVP> list = new ArrayList<>();

        if (list.isEmpty()) {
            return new ResponseEntity<>(svc.noRSVPFoundError(name).toString(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(svc.listRSVPbyName(name), HttpStatus.OK);
    }

    @GetMapping(path = "/rsvps/count")
    public ResponseEntity<String> countTotalRSVP() {
        Integer count = svc.countTotalRSVPsql();
        return new ResponseEntity<String>(svc.countRSVPJson(count).toString(), HttpStatus.CREATED);
    }
    
    @PostMapping(path = "/rsvp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewRSVP(RSVP rsvp) {
        // method to check if rsvp exists
        // method to post the rsvp to db
        if (!svc.addNewRSVP(rsvp)) {
            return new ResponseEntity<>("Error adding RSVP", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully added RSVP", HttpStatus.CREATED);
    }
    
    
    
}
