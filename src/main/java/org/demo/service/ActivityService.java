package org.demo.service;

import org.demo.model.Activity;
import org.demo.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    // Metode untuk membuat kegiatan baru (CREATE)
    public Activity createActivity(Activity activity) {
        // Logika validasi atau bisnis lainnya bisa ditambahkan di sini
        return activityRepository.save(activity);
    }

    // Metode untuk mendapatkan semua kegiatan (READ ALL)
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    // Metode untuk mendapatkan kegiatan berdasarkan ID (READ ONE)
    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    // Metode untuk memperbarui kegiatan (UPDATE)
    public Activity updateActivity(Long id, Activity activityDetails) {
        Optional<Activity> optionalActivity = activityRepository.findById(id);

        if (optionalActivity.isPresent()) {
            Activity existingActivity = optionalActivity.get();
            existingActivity.setTitle(activityDetails.getTitle());
            existingActivity.setDescription(activityDetails.getDescription());
            existingActivity.setActivityDateTime(activityDetails.getActivityDateTime());
            existingActivity.setLocation(activityDetails.getLocation());
            existingActivity.setSpeaker(activityDetails.getSpeaker());
            // --- UPDATE FIELD BARU imageUrl ---
            existingActivity.setImageUrl(activityDetails.getImageUrl());
            // Simpan perubahan ke database
            return activityRepository.save(existingActivity);
        } else {
            return null;
        }
    }

    // Metode untuk menghapus kegiatan (DELETE)
    public boolean deleteActivity(Long id) {
        if (activityRepository.existsById(id)) {
            activityRepository.deleteById(id);
            return true;
        }
        return false;
    }
}