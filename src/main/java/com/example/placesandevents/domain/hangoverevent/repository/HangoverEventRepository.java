package com.example.placesandevents.domain.hangoverevent.repository;

import com.example.placesandevents.domain.hangoverevent.HangoverEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HangoverEventRepository extends JpaRepository<HangoverEvent, Long> {
}
