package org.sopt.diary.api;

import org.sopt.diary.repository.DiaryListItemResponse;
import org.sopt.diary.repository.DiaryDetailResponse;
import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* 클라이언트 요청 처리, 서비스 계층 호출 */

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/diaries")
    ResponseEntity<?> post(@RequestBody DiaryRequest request) { //@RequestBody는 클라이언트가 보낸 JSON 데이터를 객체(여기서는 DiaryRequest)로 변환
        if (request.getContent().length() > 30) {
            return ResponseEntity.badRequest().body("30자 넘어요");
        }

        Long diaryId = diaryService.createDiary(request.getTitle(), request.getContent(), request.getCategory());
        return ResponseEntity.ok(Map.of("diaryId", diaryId));
    }

    @GetMapping("/diaries/list")
    ResponseEntity<DiaryListResponse> getList(@RequestParam(required = false) String category,
                                              @RequestParam(required = false) String orderBy) {
        List<Diary> diaryList = diaryService.getRecentList(10);
        List<DiaryListItemResponse> diaryResponseList = new ArrayList<>();

        for (Diary diary : diaryList) {
            diaryResponseList.add(new DiaryListItemResponse(diary.getId(), diary.getTitle()));
        }

        return ResponseEntity.ok(new DiaryListResponse(diaryResponseList));
    }

    @GetMapping("/diaries")
    ResponseEntity<DiaryDetailResponse> getDiary(@RequestBody DiaryRequest request) {
        Diary diary = diaryService.getDiary(request.getDiaryId());
        return ResponseEntity.ok(new DiaryDetailResponse(
                diary.getId(),
                diary.getTitle(),
                diary.getContent(),
                diary.getCreatedAt()
        ));
    }

    @PatchMapping("/diaries")
    ResponseEntity<?> patch(@RequestBody DiaryRequest request) {
        try {
            diaryService.updateDiary(request.getDiaryId(), request.getTitle(), request.getContent(), request.getCategory());
            return ResponseEntity.ok(Map.of("diaryId", request.getDiaryId()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/diaries")
    ResponseEntity<?> delete(@RequestBody DiaryRequest request) {
        diaryService.deleteDiary(request.getDiaryId());
        return ResponseEntity.ok(Map.of("diaryId", request.getDiaryId()));
    }

}
