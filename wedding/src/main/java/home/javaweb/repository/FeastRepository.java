package home.javaweb.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import home.javaweb.entity.FeastEntity;
import home.javaweb.entity.LobbyEntity;
import home.javaweb.entity.ShiftEntity;

public interface FeastRepository extends JpaRepository<FeastEntity, Long> {
	
	FeastEntity findByShift_IdAndLobby_IdAndDateOfOrganization (Long id_shift, Long id_lobby, LocalDate date);
         
}