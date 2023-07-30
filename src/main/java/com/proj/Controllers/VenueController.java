package com.proj.Controllers;

import com.proj.Models.Venue;
import com.proj.Services.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping
    public ResponseEntity<Venue> createVenue(@RequestBody Venue venue) {
        Venue createdVenue = venueService.createVenue(venue);
        if (createdVenue != null) {
            return new ResponseEntity<>(createdVenue, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable int id) {
        Venue venue = venueService.getVenueById(id);
        if (venue != null) {
            return new ResponseEntity<>(venue, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venue> updateVenue(@PathVariable int id, @RequestBody Venue venue) {
        Venue updatedVenue = venueService.updateVenue(id, venue);
        if (updatedVenue != null) {
            return new ResponseEntity<>(updatedVenue, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable int id) {
        venueService.deleteVenue(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
