package com.assessment.test.resources.v1;

import com.assessment.test.services.v1.api.GetPointsServices;
import com.assessment.test.model.v1.PointResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Named
@RestController
@Produces({"application/json", MediaType.APPLICATION_JSON})
@Consumes({"application/json", MediaType.APPLICATION_JSON})
public class Resource {
@Autowired private GetPointsServices getPointsServices;


    @GetMapping("/getPoints")
    public ResponseEntity<Object> getPoint() {
        try {
            PointResponse response = getPointsServices.getPoints();
            return new ResponseEntity<>(response,new HttpHeaders(), HttpStatus.OK);

        } catch (Exception c) {
            return new ResponseEntity<>(c.toString(),new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

}

