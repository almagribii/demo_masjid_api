package org.demo.controller;


import org.demo.model.Activity;
import org.demo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Menandai kelas ini sebagai REST Controller
@RequestMapping("/api/activities") // Base URL untuk semua endpoint di controller ini
public class ActivityController {

    private final ActivityService activityService;

    @Autowired // Injeksi dependensi ActivityService
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    // Endpoint untuk membuat kegiatan baru (POST)
    // URL: POST /api/activities
    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        Activity createdActivity = activityService.createActivity(activity);
        return new ResponseEntity<>(createdActivity, HttpStatus.CREATED); // Mengembalikan 201 Created
    }

    // Endpoint untuk mendapatkan semua kegiatan (GET ALL)
    // URL: GET /api/activities
    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityService.getAllActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK); // Mengembalikan 200 OK
    }

    // Endpoint untuk mendapatkan kegiatan berdasarkan ID (GET ONE)
    // URL: GET /api/activities/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        Optional<Activity> activity = activityService.getActivityById(id);
        return activity.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Mengembalikan 404 Not Found jika tidak ada
    }

    // Endpoint untuk memperbarui kegiatan (PUT)
    // URL: PUT /api/activities/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody Activity activityDetails) {
        Activity updatedActivity = activityService.updateActivity(id, activityDetails);
        if (updatedActivity != null) {
            return new ResponseEntity<>(updatedActivity, HttpStatus.OK); // Mengembalikan 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Mengembalikan 404 Not Found
        }
    }

    // Endpoint untuk menghapus kegiatan (DELETE)
    // URL: DELETE /api/activities/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteActivity(@PathVariable Long id) {
        boolean deleted = activityService.deleteActivity(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Mengembalikan 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Mengembalikan 404 Not Found
        }
    }
}