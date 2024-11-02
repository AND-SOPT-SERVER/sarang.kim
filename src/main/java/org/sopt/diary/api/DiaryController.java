package org.sopt.diary.api;

import org.sopt.diary.repository.DiaryDetailResponse;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;
    private static final int MAX_CONTENT_LENGTH = 30;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/diaries")
    ResponseEntity<DiaryResponse> post(@RequestBody DiaryRequest request) {
        ResponseEntity<DiaryResponse> validationError = validateContentLength(request.getContent());
        if (validationError != null) return validationError;

        Long createdId = diaryService.createDiary(request.getTitle(), request.getContent(), request.getCategory());
        return ResponseEntity.ok(new DiaryResponse(createdId));
    }

    @GetMapping("/diaries/list")
    ResponseEntity<List<DiaryListResponse>> getList(@RequestParam(required = false) String category,
                                                    @RequestParam(required = false) String orderBy) {
        return ResponseEntity.ok(diaryService.getRecentList(10));
    }

    @GetMapping("/diaries")
    ResponseEntity<DiaryDetailResponse> getDiary(@RequestBody DiaryRequest request) {
        if (request.getDiaryId() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(diaryService.getDiary(request.getDiaryId()));
    }

    @PatchMapping("/diaries")
    ResponseEntity<DiaryResponse> patch(@RequestBody DiaryRequest request) {
        ResponseEntity<DiaryResponse> validationError = validateContentLength(request.getContent());
        if (validationError != null) {
            return validationError;
        }

        diaryService.updateDiary(request.getDiaryId(), request.getTitle(), request.getContent(), request.getCategory());
        return ResponseEntity.ok(new DiaryResponse(request.getDiaryId()));
    }

    @DeleteMapping("/diaries")
    ResponseEntity<DiaryResponse> delete(@RequestBody DiaryRequest request) {
        diaryService.deleteDiary(request.getDiaryId());
        return ResponseEntity.ok(new DiaryResponse(request.getDiaryId()));
    }

    private ResponseEntity<DiaryResponse> validateContentLength(String content) {
        if (content != null && content.length() > MAX_CONTENT_LENGTH) {
            return ResponseEntity.badRequest().body(new DiaryResponse(null));
        }
        return null;
    }
}