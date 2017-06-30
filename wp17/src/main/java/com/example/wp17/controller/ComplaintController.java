package com.example.wp17.controller;

import com.example.wp17.model.Complaint;
import com.example.wp17.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Imola on 6/30/2017.
 */

@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {
    @Autowired
    ComplaintService complaintService;

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint) {
        complaintService.addComplaint(complaint);
        //System.out.println(subForumService.readSubForums());
        return new ResponseEntity<>(complaint, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET)
    public ResponseEntity getComplaints() {
        //System.out.println(subForumService.readSubForums());
        ArrayList<Complaint> complaints= complaintService.readComplaints();
        return new ResponseEntity(complaints, HttpStatus.OK);
    }
}
