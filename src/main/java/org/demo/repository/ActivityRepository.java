package org.demo.repository;


import org.demo.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository // Menandai interface ini sebagai komponen repository Spring
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    // JpaRepository menyediakan metode CRUD dasar:
    // save(), findById(), findAll(), deleteById(), dll.

    // Anda juga bisa menambahkan metode kustom jika diperlukan, contoh:
    List<Activity> findByLocation(String location); // Mencari kegiatan berdasarkan lokasi
    List<Activity> findByActivityDateTimeAfter(LocalDateTime dateTime); // Mencari kegiatan setelah waktu tertentu
}