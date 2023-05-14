package com.cap.fatrip.repository;

import com.cap.fatrip.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PlanRepository extends JpaRepository<PlanEntity, Long> {
	@Query("SELECT plan FROM PlanEntity plan LEFT JOIN PlanTagEntity rel ON plan = rel.plan WHERE rel.tag.name IN (:tag1)")
	List<PlanEntity> findPlanByTag1(@Param("tag1") String tag1);

	@Query("SELECT plan FROM PlanEntity plan LEFT JOIN PlanTagEntity rel ON plan = rel.plan WHERE rel.tag.name IN (:tag1, :tag2)")
	List<PlanEntity> findPlanByTag2(@Param("tag1") String tag1, @Param("tag2") String tag2);

	@Query("SELECT plan FROM PlanEntity plan LEFT JOIN PlanTagEntity rel ON plan = rel.plan WHERE rel.tag.name IN (:tag1, :tag2, :tag3, :tag4, :tag5, :tag6, :tag7, :tag8, :tag9, :tag10)")
	List<PlanEntity> findPlanByTag10(@Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4, @Param("tag5") String tag5,
									 @Param("tag6") String tag6, @Param("tag7") String tag7, @Param("tag8") String tag8, @Param("tag9") String tag9, @Param("tag10") String tag10);

//	@Query("SELECT distinct plan FROM PlanEntity plan LEFT JOIN PlanTagEntity rel ON plan = rel.plan WHERE rel.tag.name IN (:tag1)" +
//			"AND plan.title LIKE %:title% AND plan.planTagEntities.size>0")
//	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1);
//
//	@Query("SELECT distinct plan FROM PlanEntity plan LEFT JOIN PlanTagEntity rel ON plan = rel.plan WHERE rel.tag.name IN (:tag1, :tag2)" +
//			"And plan.title LIKE %:title% AND plan.planTagEntities.size>1")
//	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2);
//
//	@Query("SELECT distinct plan FROM PlanEntity plan LEFT JOIN PlanTagEntity rel ON plan = rel.plan WHERE rel.tag.name IN (:tag1, :tag2, :tag3)" +
//			"And plan.title LIKE %:title% AND plan.planTagEntities.size>2")
//	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3);
//
//	@Query("SELECT distinct plan FROM PlanEntity plan LEFT JOIN PlanTagEntity rel ON plan = rel.plan WHERE rel.tag.name IN (:tag1, :tag2, :tag3, :tag4)" +
//			"And plan.title LIKE %:title% AND plan.planTagEntities.size>3")
//	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4);
//
//	@Query("SELECT distinct plan FROM PlanEntity plan LEFT JOIN PlanTagEntity rel ON plan = rel.plan WHERE rel.tag.name IN (:tag1, :tag2, :tag3, :tag4, :tag5)" +
//			"And plan.title LIKE %:title% AND plan.planTagEntities.size>4")
//	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4, @Param("tag5") String tag5);
//
//	@Query("SELECT distinct plan FROM PlanEntity plan LEFT JOIN PlanTagEntity rel ON plan = rel.plan WHERE rel.tag.name IN (:tag1, :tag2, :tag3, :tag4, :tag5, :tag6)" +
//			"And plan.title LIKE %:title% AND plan.planTagEntities.size>5")
//	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4, @Param("tag5") String tag5,
//											@Param("tag6") String tag6);
//
//	@Query("SELECT distinct plan FROM PlanEntity plan LEFT JOIN PlanTagEntity rel ON plan = rel.plan WHERE rel.tag.name IN (:tag1, :tag2, :tag3, :tag4, :tag5, :tag6, :tag7)" +
//			"And plan.title LIKE %:title% AND plan.planTagEntities.size>6")
//	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4, @Param("tag5") String tag5,
//											@Param("tag6") String tag6, @Param("tag7") String tag7);
//
//	@Query("SELECT distinct plan FROM PlanEntity plan LEFT JOIN PlanTagEntity rel ON plan = rel.plan WHERE rel.tag.name IN (:tag1, :tag2, :tag3, :tag4, :tag5, :tag6, :tag7, :tag8)" +
//			"And plan.title LIKE %:title% AND plan.planTagEntities.size>7")
//	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4, @Param("tag5") String tag5,
//											@Param("tag6") String tag6, @Param("tag7") String tag7, @Param("tag8") String tag8);
//
//	@Query("SELECT distinct plan FROM PlanEntity plan LEFT JOIN PlanTagEntity rel ON plan = rel.plan WHERE rel.tag.name IN (:tag1, :tag2, :tag3, :tag4, :tag5, :tag6, :tag7, :tag8, :tag9)" +
//			"And plan.title LIKE %:title% AND plan.planTagEntities.size>8")
//	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4, @Param("tag5") String tag5,
//											@Param("tag6") String tag6, @Param("tag7") String tag7, @Param("tag8") String tag8, @Param("tag9") String tag9);
//
//	@Query("SELECT distinct plan FROM PlanEntity plan LEFT JOIN PlanTagEntity rel ON plan = rel.plan WHERE rel.tag.name IN (:tag1, :tag2, :tag3, :tag4, :tag5, :tag6, :tag7, :tag8, :tag9, :tag10)" +
//			"And plan.title LIKE %:title% AND plan.planTagEntities.size>9")
//	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4, @Param("tag5") String tag5,
//											@Param("tag6") String tag6, @Param("tag7") String tag7, @Param("tag8") String tag8, @Param("tag9") String tag9, @Param("tag10") String tag10);


	@Query("SELECT distinct plan FROM PlanEntity plan INNER JOIN FETCH PlanTagEntity rel ON plan = rel.plan WHERE plan.title LIKE %:title% And plan.planTagEntities.size>0" +
			"AND EXISTS (SELECT pt1 FROM PlanTagEntity pt1 WHERE pt1.plan = plan AND pt1.tag.name = :tag1)" +
			"ORDER BY plan.like DESC")
	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1);
	@Query("SELECT distinct plan FROM PlanEntity plan INNER JOIN FETCH PlanTagEntity rel ON plan = rel.plan WHERE plan.title LIKE %:title% And plan.planTagEntities.size>1" +
			"AND EXISTS (SELECT pt1 FROM PlanTagEntity pt1 WHERE pt1.plan = plan AND pt1.tag.name = :tag1)" +
			"AND EXISTS (SELECT pt2 FROM PlanTagEntity pt2 WHERE pt2.plan = plan AND pt2.tag.name = :tag2)" +
			"ORDER BY plan.like DESC")
	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2);
	@Query("SELECT distinct plan FROM PlanEntity plan INNER JOIN FETCH PlanTagEntity rel ON plan = rel.plan WHERE plan.title LIKE %:title% And plan.planTagEntities.size>1" +
			"AND EXISTS (SELECT pt1 FROM PlanTagEntity pt1 WHERE pt1.plan = plan AND pt1.tag.name = :tag1)" +
			"AND EXISTS (SELECT pt2 FROM PlanTagEntity pt2 WHERE pt2.plan = plan AND pt2.tag.name = :tag2)" +
			"AND EXISTS (SELECT pt3 FROM PlanTagEntity pt3 WHERE pt3.plan = plan AND pt3.tag.name = :tag3)" +
			"ORDER BY plan.like DESC")
	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3);
	@Query("SELECT distinct plan FROM PlanEntity plan INNER JOIN FETCH PlanTagEntity rel ON plan = rel.plan WHERE plan.title LIKE %:title% And plan.planTagEntities.size>1" +
			"AND EXISTS (SELECT pt1 FROM PlanTagEntity pt1 WHERE pt1.plan = plan AND pt1.tag.name = :tag1)" +
			"AND EXISTS (SELECT pt2 FROM PlanTagEntity pt2 WHERE pt2.plan = plan AND pt2.tag.name = :tag2)" +
			"AND EXISTS (SELECT pt3 FROM PlanTagEntity pt3 WHERE pt3.plan = plan AND pt3.tag.name = :tag3)" +
			"AND EXISTS (SELECT pt4 FROM PlanTagEntity pt4 WHERE pt4.plan = plan AND pt4.tag.name = :tag4)" +
			"ORDER BY plan.like DESC")
	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4);
	@Query("SELECT distinct plan FROM PlanEntity plan INNER JOIN FETCH PlanTagEntity rel ON plan = rel.plan WHERE plan.title LIKE %:title% And plan.planTagEntities.size>1" +
			"AND EXISTS (SELECT pt1 FROM PlanTagEntity pt1 WHERE pt1.plan = plan AND pt1.tag.name = :tag1)" +
			"AND EXISTS (SELECT pt2 FROM PlanTagEntity pt2 WHERE pt2.plan = plan AND pt2.tag.name = :tag2)" +
			"AND EXISTS (SELECT pt3 FROM PlanTagEntity pt3 WHERE pt3.plan = plan AND pt3.tag.name = :tag3)" +
			"AND EXISTS (SELECT pt4 FROM PlanTagEntity pt4 WHERE pt4.plan = plan AND pt4.tag.name = :tag4)" +
			"AND EXISTS (SELECT pt5 FROM PlanTagEntity pt5 WHERE pt5.plan = plan AND pt5.tag.name = :tag5)" +
			"ORDER BY plan.like DESC")
	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4, @Param("tag5") String tag5);
	@Query("SELECT distinct plan FROM PlanEntity plan INNER JOIN FETCH PlanTagEntity rel ON plan = rel.plan WHERE plan.title LIKE %:title% And plan.planTagEntities.size>1" +
			"AND EXISTS (SELECT pt1 FROM PlanTagEntity pt1 WHERE pt1.plan = plan AND pt1.tag.name = :tag1)" +
			"AND EXISTS (SELECT pt2 FROM PlanTagEntity pt2 WHERE pt2.plan = plan AND pt2.tag.name = :tag2)" +
			"AND EXISTS (SELECT pt3 FROM PlanTagEntity pt3 WHERE pt3.plan = plan AND pt3.tag.name = :tag3)" +
			"AND EXISTS (SELECT pt4 FROM PlanTagEntity pt4 WHERE pt4.plan = plan AND pt4.tag.name = :tag4)" +
			"AND EXISTS (SELECT pt5 FROM PlanTagEntity pt5 WHERE pt5.plan = plan AND pt5.tag.name = :tag5)" +
			"AND EXISTS (SELECT pt6 FROM PlanTagEntity pt6 WHERE pt6.plan = plan AND pt6.tag.name = :tag6)" +
			"ORDER BY plan.like DESC")
	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4, @Param("tag5") String tag5,
											@Param("tag6") String tag6);
	@Query("SELECT distinct plan FROM PlanEntity plan INNER JOIN FETCH PlanTagEntity rel ON plan = rel.plan WHERE plan.title LIKE %:title% And plan.planTagEntities.size>1" +
			"AND EXISTS (SELECT pt1 FROM PlanTagEntity pt1 WHERE pt1.plan = plan AND pt1.tag.name = :tag1)" +
			"AND EXISTS (SELECT pt2 FROM PlanTagEntity pt2 WHERE pt2.plan = plan AND pt2.tag.name = :tag2)" +
			"AND EXISTS (SELECT pt3 FROM PlanTagEntity pt3 WHERE pt3.plan = plan AND pt3.tag.name = :tag3)" +
			"AND EXISTS (SELECT pt4 FROM PlanTagEntity pt4 WHERE pt4.plan = plan AND pt4.tag.name = :tag4)" +
			"AND EXISTS (SELECT pt5 FROM PlanTagEntity pt5 WHERE pt5.plan = plan AND pt5.tag.name = :tag5)" +
			"AND EXISTS (SELECT pt6 FROM PlanTagEntity pt6 WHERE pt6.plan = plan AND pt6.tag.name = :tag6)" +
			"AND EXISTS (SELECT pt7 FROM PlanTagEntity pt7 WHERE pt7.plan = plan AND pt7.tag.name = :tag7)" +
			"ORDER BY plan.like DESC")
	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4, @Param("tag5") String tag5,
											@Param("tag6") String tag6, @Param("tag7") String tag7);
	@Query("SELECT distinct plan FROM PlanEntity plan INNER JOIN FETCH PlanTagEntity rel ON plan = rel.plan WHERE plan.title LIKE %:title% And plan.planTagEntities.size>1" +
			"AND EXISTS (SELECT pt1 FROM PlanTagEntity pt1 WHERE pt1.plan = plan AND pt1.tag.name = :tag1)" +
			"AND EXISTS (SELECT pt2 FROM PlanTagEntity pt2 WHERE pt2.plan = plan AND pt2.tag.name = :tag2)" +
			"AND EXISTS (SELECT pt3 FROM PlanTagEntity pt3 WHERE pt3.plan = plan AND pt3.tag.name = :tag3)" +
			"AND EXISTS (SELECT pt4 FROM PlanTagEntity pt4 WHERE pt4.plan = plan AND pt4.tag.name = :tag4)" +
			"AND EXISTS (SELECT pt5 FROM PlanTagEntity pt5 WHERE pt5.plan = plan AND pt5.tag.name = :tag5)" +
			"AND EXISTS (SELECT pt6 FROM PlanTagEntity pt6 WHERE pt6.plan = plan AND pt6.tag.name = :tag6)" +
			"AND EXISTS (SELECT pt7 FROM PlanTagEntity pt7 WHERE pt7.plan = plan AND pt7.tag.name = :tag7)" +
			"AND EXISTS (SELECT pt8 FROM PlanTagEntity pt8 WHERE pt8.plan = plan AND pt8.tag.name = :tag8)" +
			"ORDER BY plan.like DESC")
	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4, @Param("tag5") String tag5,
											@Param("tag6") String tag6, @Param("tag7") String tag7, @Param("tag8") String tag8);
	@Query("SELECT distinct plan FROM PlanEntity plan INNER JOIN FETCH PlanTagEntity rel ON plan = rel.plan WHERE plan.title LIKE %:title% And plan.planTagEntities.size>1" +
			"AND EXISTS (SELECT pt1 FROM PlanTagEntity pt1 WHERE pt1.plan = plan AND pt1.tag.name = :tag1)" +
			"AND EXISTS (SELECT pt2 FROM PlanTagEntity pt2 WHERE pt2.plan = plan AND pt2.tag.name = :tag2)" +
			"AND EXISTS (SELECT pt3 FROM PlanTagEntity pt3 WHERE pt3.plan = plan AND pt3.tag.name = :tag3)" +
			"AND EXISTS (SELECT pt4 FROM PlanTagEntity pt4 WHERE pt4.plan = plan AND pt4.tag.name = :tag4)" +
			"AND EXISTS (SELECT pt5 FROM PlanTagEntity pt5 WHERE pt5.plan = plan AND pt5.tag.name = :tag5)" +
			"AND EXISTS (SELECT pt6 FROM PlanTagEntity pt6 WHERE pt6.plan = plan AND pt6.tag.name = :tag6)" +
			"AND EXISTS (SELECT pt7 FROM PlanTagEntity pt7 WHERE pt7.plan = plan AND pt7.tag.name = :tag7)" +
			"AND EXISTS (SELECT pt8 FROM PlanTagEntity pt8 WHERE pt8.plan = plan AND pt8.tag.name = :tag8)" +
			"AND EXISTS (SELECT pt9 FROM PlanTagEntity pt9 WHERE pt9.plan = plan AND pt9.tag.name = :tag9)" +
			"ORDER BY plan.like DESC")
	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4, @Param("tag5") String tag5,
											@Param("tag6") String tag6, @Param("tag7") String tag7, @Param("tag8") String tag8, @Param("tag9") String tag9);
	@Query("SELECT distinct plan FROM PlanEntity plan INNER JOIN FETCH PlanTagEntity rel ON plan = rel.plan WHERE plan.title LIKE %:title% And plan.planTagEntities.size>1" +
			"AND EXISTS (SELECT pt1 FROM PlanTagEntity pt1 WHERE pt1.plan = plan AND pt1.tag.name = :tag1)" +
			"AND EXISTS (SELECT pt2 FROM PlanTagEntity pt2 WHERE pt2.plan = plan AND pt2.tag.name = :tag2)" +
			"AND EXISTS (SELECT pt3 FROM PlanTagEntity pt3 WHERE pt3.plan = plan AND pt3.tag.name = :tag3)" +
			"AND EXISTS (SELECT pt4 FROM PlanTagEntity pt4 WHERE pt4.plan = plan AND pt4.tag.name = :tag4)" +
			"AND EXISTS (SELECT pt5 FROM PlanTagEntity pt5 WHERE pt5.plan = plan AND pt5.tag.name = :tag5)" +
			"AND EXISTS (SELECT pt6 FROM PlanTagEntity pt6 WHERE pt6.plan = plan AND pt6.tag.name = :tag6)" +
			"AND EXISTS (SELECT pt7 FROM PlanTagEntity pt7 WHERE pt7.plan = plan AND pt7.tag.name = :tag7)" +
			"AND EXISTS (SELECT pt8 FROM PlanTagEntity pt8 WHERE pt8.plan = plan AND pt8.tag.name = :tag8)" +
			"AND EXISTS (SELECT pt9 FROM PlanTagEntity pt9 WHERE pt9.plan = plan AND pt9.tag.name = :tag9)" +
			"AND EXISTS (SELECT pt10 FROM PlanTagEntity pt10 WHERE pt10.plan = plan AND pt10.tag.name = :tag10)" +
			"ORDER BY plan.like DESC")
	List<PlanEntity> findPlanByTagsAndTitle(@Param("title") String title, @Param("tag1") String tag1, @Param("tag2") String tag2, @Param("tag3") String tag3, @Param("tag4") String tag4, @Param("tag5") String tag5,
											@Param("tag6") String tag6, @Param("tag7") String tag7, @Param("tag8") String tag8, @Param("tag9") String tag9, @Param("tag10") String tag10);
}
