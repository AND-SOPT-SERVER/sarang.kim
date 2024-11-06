package org.sopt.diary.api;

import jakarta.validation.Valid;
import org.sopt.diary.dto.DiaryListResponse;
import org.sopt.diary.dto.DiaryRequest;
import org.sopt.diary.dto.DiaryResponse;
import org.sopt.diary.dto.DiaryDetailResponse;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/diaries")
    ResponseEntity<DiaryResponse> post(
            @RequestBody @Valid DiaryRequest request,
            @RequestHeader("X-USER-ID") Long userId) {
        Long createdId = diaryService.createDiary(
                request.getTitle(),
                request.getContent(),
                request.getCategory(),
                userId,
                request.isPublic()
        );
        return ResponseEntity.ok(new DiaryResponse(createdId));
    }

    @GetMapping("/diaries/list")
    public ResponseEntity<List<DiaryListResponse>> getDiaryList(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "LATEST") DiaryService.SortCriteria sortBy) {

        List<DiaryListResponse> diaries = diaryService.getRecentList(category, sortBy);
        return ResponseEntity.ok(diaries);
    }

    @PatchMapping("/diaries/{diaryId}")
    ResponseEntity<DiaryResponse> patch(
            @PathVariable Long diaryId,
            @RequestBody @Valid DiaryRequest request,
            @RequestHeader("X-USER-ID") Long userId) {
        diaryService.updateDiary(
                diaryId,
                request.getTitle(),
                request.getContent(),
                request.getCategory(),
                request.isPublic(),
                userId
        );
        return ResponseEntity.ok(new DiaryResponse(diaryId));
    }

    @DeleteMapping("/diaries/{diaryId}")
    ResponseEntity<Void> delete(
            @PathVariable Long diaryId,
            @RequestHeader("X-USER-ID") Long userId) {
        diaryService.deleteDiary(diaryId, userId);
        return ResponseEntity.ok().build();
    }
}