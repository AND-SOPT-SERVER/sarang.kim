package org.sopt.diary.service;

import org.sopt.diary.api.DiaryListResponse;
import org.sopt.diary.repository.DiaryDetailResponse;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public Long createDiary(String title, String content, String category) {
        DiaryEntity newDiary = new DiaryEntity(title, content, category);
        DiaryEntity saved = diaryRepository.save(newDiary);
        return saved.getId();
    }

    public void updateDiary(Long id, String title, String content, String category) {
        DiaryEntity diaryEntity = diaryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Diary not found"));

        diaryEntity.setTitle(title);
        diaryEntity.setContent(content);
        diaryEntity.setCategory(category);
        diaryRepository.save(diaryEntity);
    }

    public void deleteDiary(Long id) {
        DiaryEntity diaryEntity = diaryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Diary not found"));
        diaryRepository.delete(diaryEntity);
    }

    public List<DiaryListResponse> getRecentList(int limit) {
        List<DiaryEntity> diaryEntityList = diaryRepository.findTop10ByOrderByCreatedAtDesc();
        return diaryEntityList.stream()
                .map(diary -> new DiaryListResponse(diary.getId(), diary.getTitle()))
                .collect(Collectors.toList());
    }

    public DiaryDetailResponse getDiary(Long id) {
        DiaryEntity diaryEntity = diaryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Diary not found"));

        return new DiaryDetailResponse(
                diaryEntity.getId(),
                diaryEntity.getTitle(),
                diaryEntity.getContent(),
                diaryEntity.getCreatedAt()
        );
    }
}