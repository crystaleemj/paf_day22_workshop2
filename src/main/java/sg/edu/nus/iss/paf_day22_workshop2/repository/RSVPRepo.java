package sg.edu.nus.iss.paf_day22_workshop2.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day22_workshop2.model.RSVP;

@Repository
public class RSVPRepo {
    
    @Autowired
    JdbcTemplate template;

    private String listAllRSVP = "select * from rsvps";
    private String listRSVPbyName = "select * from rsvps where full_name=? ";
    private String countTotalRSVP = "select count(*) from rsvps";

    public List<RSVP> listAllRSVP(){
        return template.query(listAllRSVP, BeanPropertyRowMapper.newInstance(RSVP.class));
    }

    public List<RSVP> listRSVPbyName(String name){
        return template.query(listRSVPbyName, BeanPropertyRowMapper.newInstance(RSVP.class), name);
    }

    public Integer countTotalRSVP(){
        List<RSVP> list = listAllRSVP();
        return list.size();
    }

    public Integer countTotalRSVPsql(){
        return template.queryForObject(countTotalRSVP, Integer.class);
    }


}