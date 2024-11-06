package org.sopt.diary.dto;

public class DiaryListResponse {
    private Long diaryId;
    private String title;
    private String userNickname;
    private String category;
    private boolean isPublic;

    public DiaryListResponse() {}

    public DiaryListResponse(Long diaryId, String title) {
        this.diaryId = diaryId;
        this.title = title;
    }

    public DiaryListResponse(Long diaryId, String title, String userNickname, String category, boolean isPublic) {
        this.diaryId = diaryId;
        this.title = title;
        this.userNickname = userNickname;
        this.category = category;
        this.isPublic = isPublic;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Long diaryId) {
        this.diaryId = diaryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}