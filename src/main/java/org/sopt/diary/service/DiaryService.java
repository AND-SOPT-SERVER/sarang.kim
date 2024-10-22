package org.sopt.diary.service;

import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public Long createDiary(String title, String content, String category) {
        DiaryEntity newDiary = new DiaryEntity(title, content, category);
        diaryRepository.save(newDiary);
        return newDiary.getId();
    }

    public Diary getDiary(Long id) {
        DiaryEntity diaryEntity = diaryRepository.findById(id).orElseThrow(() -> new RuntimeException("Diary not found"));
        return new Diary(diaryEntity.getId(), diaryEntity.getTitle(), diaryEntity.getContent(), diaryEntity.getCreatedAt());
    }

    public List<Diary> getList(int limit) {
        // 1) repository로부터 DiaryEntity를 가져옴
        List<DiaryEntity> diaryEntityList = diaryRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        // 2) DiaryEntity -> Diary로 변환
        final List<Diary> diaryList = new ArrayList<>();

        for (int i = 0; i < Math.min(limit, diaryEntityList.size()); i++) {
            DiaryEntity diaryEntity = diaryEntityList.get(i);
            // 모든 필드를 Diary 생성자에 전달
            diaryList.add(new Diary(diaryEntity.getId(), diaryEntity.getTitle(), diaryEntity.getContent(), diaryEntity.getCreatedAt()));
        }
        return diaryList;
    }

    public void updateDiary(Long id, String title, String content, String category) {
        DiaryEntity diaryEntity = diaryRepository.findById(id).orElseThrow(() -> new RuntimeException("Diary not found"));

        if (content.length() > 30) {
            throw new IllegalArgumentException("Content exceeds 30 characters");
        }

        diaryEntity.setTitle(title);
        diaryEntity.setContent(content);
        diaryEntity.setCategory(category);
        diaryRepository.save(diaryEntity);
    }

    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }

    public List<Diary> getRecentList(int limit) {
        List<DiaryEntity> diaryEntityList = diaryRepository.findTop10ByOrderByCreatedAtDesc();

        final List<Diary> diaryList = new ArrayList<>();
        for (DiaryEntity diaryEntity : diaryEntityList) {
            diaryList.add(new Diary(
                    diaryEntity.getId(),
                    diaryEntity.getTitle(),
                    diaryEntity.getContent(),
                    diaryEntity.getCreatedAt()
            ));
        }
        return diaryList;
    }

}
