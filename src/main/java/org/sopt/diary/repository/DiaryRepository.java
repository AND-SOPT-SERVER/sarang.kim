package org.sopt.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    List<DiaryEntity> findTop10ByOrderByCreatedAtDesc();
    List<DiaryEntity> findTop10ByCategoryOrderByCreatedAtDesc(DiaryEntity.Category category);
    boolean existsByTitle(String title);

    @Query(value = "SELECT d FROM DiaryEntity d ORDER BY LENGTH(d.content) DESC LIMIT 10")
    List<DiaryEntity> findTop10ByOrderByContentLengthDesc();

    @Query(value = "SELECT d FROM DiaryEntity d WHERE d.category = ?1 ORDER BY LENGTH(d.content) DESC LIMIT 10")
    List<DiaryEntity> findTop10ByCategoryOrderByContentLengthDesc(DiaryEntity.Category category);
}
