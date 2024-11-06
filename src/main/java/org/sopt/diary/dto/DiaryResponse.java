package org.sopt.diary.dto;

public class DiaryResponse {
    private Long diaryId;

    public DiaryResponse() {}

    public DiaryResponse(Long diaryId) {
        this.diaryId = diaryId;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Long diaryId) {
        this.diaryId = diaryId;
    }
}