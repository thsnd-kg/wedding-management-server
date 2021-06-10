package home.javaweb.report.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import home.javaweb.report.entity.ReportDate;

public interface ReportDateRepository extends JpaRepository<ReportDate, Long> {


	ReportDate findByDate(LocalDate date);
	
	@Query(value = "SELECT COUNT(report_date_id) FROM report_date_detail WHERE report_date_id = ?1", nativeQuery = true)
	int selectCountFeast(Long reportId);
	
	@Query(value = "SELECT SUM(total_bill + total_fine) "
				+ "FROM report_date_detail rpd INNER JOIN bill b on rpd.bill_id = b.id "
				+ "WHERE rpd.report_date_id = ?1", nativeQuery = true)
	Long calculateRevenue(Long reportId);

}
