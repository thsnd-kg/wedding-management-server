package home.javaweb.report.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import home.javaweb.report.dto.CountFoodDTO;
import home.javaweb.report.dto.CountLobbyDTO;
import home.javaweb.report.dto.CountServiceDTO;
import home.javaweb.report.entity.ReportDate;

@Repository
public interface ReportDateRepository extends JpaRepository<ReportDate, Long> {


	ReportDate findByDate(LocalDate date);
	
	@Query(value = "SELECT COUNT(report_date_id) FROM report_date_detail WHERE report_date_id = ?1", nativeQuery = true)
	int selectCountFeast(Long reportId);
	
	@Query(value = "SELECT SUM(total_bill + total_fine) "
				+ "FROM report_date_detail rpd INNER JOIN bill b on rpd.bill_id = b.id "
				+ "WHERE rpd.report_date_id = ?1", nativeQuery = true)
	Long calculateRevenue(Long reportId);

	@Query(value = "SELECT rd "
				+ "FROM ReportDate rd "
				+ "WHERE MONTH(rd.date) < ?1")
	List<ReportDate> findAll(int currentMonth);

	@Query(value = "SELECT rd "
			+ "FROM ReportDate rd "
			+ "WHERE MONTH(rd.date) = ?1")
	List<ReportDate> findByMonth(int month);
	
	@Query(value = "SELECT rd "
			+ "FROM ReportDate rd "
			+ "WHERE MONTH(rd.date) = ?1 AND YEAR(rd.date) = ?2 ")
	List<ReportDate> findByMonthAndYear(int month, int year); 
	
	@Query(value = "SELECT rd "
			+ "FROM ReportDate rd "
			+ "WHERE MONTH(rd.date) = ?1 AND YEAR(rd.date) = ?2 ")
	List<ReportDate> findAllBillByDate(LocalDate date);

	@Query(value = "SELECT lob.name as lobbyName, COUNT(f.lobby_id) as count "
			+ "FROM bill b join feast f on b.feast_id = f.id join lobby lob on f.lobby_id = lob.id "
			+ "WHERE date_part('year',b.date_of_payment) = ?2 AND date_part('month',b.date_of_payment) = ?1 "
			+ "GROUP BY lob.name "
			+ "ORDER BY COUNT(f.lobby_id) DESC ", nativeQuery = true)
	List<CountLobbyDTO> selectCountLobby(int month, int year);

	@Query(value = "SELECT s.name as serviceName, SUM(fs.count) as count "
			+ "FROM bill b join feast f on b.feast_id = f.id join feast_service fs on f.id = fs.feast_id join service s on fs.service_id = s.id "
			+ "WHERE date_part('year',b.date_of_payment) = ?2 AND date_part('month',b.date_of_payment) = ?1 "
			+ "GROUP BY s.name "
			+ "ORDER BY SUM(fs.count) DESC ",nativeQuery = true)
	List<CountServiceDTO> selectCountService(int month, int year);

	@Query(value = "SELECT fc.name as category, food.name as foodName, SUM(tf.food_id) as count "
			+ "FROM bill b JOIN feast f ON b.feast_id = f.id JOIN feast_table ft ON f.id = ft.feast_id "
			+ "	 JOIN table_food tf ON ft.id = tf.feast_table_id JOIN food ON food.id = tf.food_id "
			+ "     JOIN food_category fc ON fc.id = food.food_category_id "
			+ "WHERE date_part('year',b.date_of_payment) = ?2 AND date_part('month',b.date_of_payment) = ?1 "
			+ "GROUP BY fc.name, food.name "
			+ "ORDER BY SUM(tf.count)" , nativeQuery = true)
	List<CountFoodDTO> selectCountFood(int month, int year);
}