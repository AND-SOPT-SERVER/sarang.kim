package org.sopt.diary.api;

import org.sopt.diary.repository.DiaryListItemResponse;

import java.util.List;

public class DiaryListResponse {
    private final List<DiaryListItemResponse> diaryList;

    public DiaryListResponse(List<DiaryListItemResponse> diaryList) {
        this.diaryList = diaryList;
    }

    public List<DiaryListItemResponse> getDiaryList() {
        return diaryList;
    }
}
