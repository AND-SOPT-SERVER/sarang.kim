package org.sopt.diary.service;

import org.sopt.diary.dto.DiaryListResponse;
import org.sopt.diary.dto.DiaryDetailResponse;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public enum SortCriteria {
        LATEST,     // 최신순
        CONTENT_LENGTH  // 내용 글자수 순
    }

    public DiaryService(DiaryRepository diaryRepository, UserRepository userRepository) {
        this.diaryRepository = diaryRepository;
        this.userRepository = userRepository;
    }

    public Long createDiary(String title, String content, String category, Long userId, boolean isPublic) {
        // Validate title uniqueness
        if (diaryRepository.existsByTitle(title)) {
            throw new IllegalArgumentException("Title already exists");
        }

        // Validate category
        try {
            DiaryEntity.Category.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category. Must be one of: FOOD, SCHOOL, MOVIE, EXERCISE");
        }

        // Get user nickname
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        DiaryEntity newDiary = new DiaryEntity(title, content, category, userId, user.getNickname(), isPublic);
        DiaryEntity saved = diaryRepository.save(newDiary);
        return saved.getDiaryId();
    }

    public List<DiaryListResponse> getRecentList(String category, SortCriteria sortBy) {
        List<DiaryEntity> diaries;

        if (category != null) {
            try {
                DiaryEntity.Category cat = DiaryEntity.Category.valueOf(category.toUpperCase());
                if (sortBy == SortCriteria.LATEST) {
                    diaries = diaryRepository.findTop10ByCategoryOrderByCreatedAtDesc(cat);
                } else {
                    // 내용 글자수 순으로 정렬
                    diaries = diaryRepository.findTop10ByCategoryOrderByContentLengthDesc(cat);
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid category");
            }
        } else {
            if (sortBy == SortCriteria.LATEST) {
                diaries = diaryRepository.findTop10ByOrderByCreatedAtDesc();
            } else {
                // 내용 글자수 순으로 정렬
                diaries = diaryRepository.findTop10ByOrderByContentLengthDesc();
            }
        }

        return diaries.stream()
                .map(diary -> new DiaryListResponse(
                        diary.getDiaryId(),
                        diary.getTitle(),
                        diary.getUserNickname(),
                        diary.getCategory().toString(),
                        diary.isPublic()))
                .collect(Collectors.toList());
    }

    public void updateDiary(Long diaryId, String title, String content, String category,
                            boolean isPublic, Long userId) {
        DiaryEntity diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new NoSuchElementException("Diary not found"));

        // Validate ownership
        if (!diary.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Not authorized to modify this diary");
        }

        // Validate title uniqueness if changed
        if (!diary.getTitle().equals(title) && diaryRepository.existsByTitle(title)) {
            throw new IllegalArgumentException("Title already exists");
        }

        diary.setTitle(title);
        diary.setContent(content);
        diary.setCategory(DiaryEntity.Category.valueOf(category.toUpperCase()));
        diary.setPublic(isPublic);

        diaryRepository.save(diary);
    }

    public void deleteDiary(Long diaryId, Long userId) {
        DiaryEntity diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new NoSuchElementException("Diary not found"));

        if (!diary.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Not authorized to delete this diary");
        }

        diaryRepository.delete(diary);
    }
}