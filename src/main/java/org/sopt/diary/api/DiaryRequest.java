package org.sopt.diary.api;

//DTO: 데이터를 계층 간, 특히 클라이언트와 서버사이에서 주고받기 위한 전용 객체

public class DiaryRequest {
    private Long diaryId;
    private String title;
    private String content;
    private String category;

    public DiaryRequest() {}

    public DiaryRequest(Long id, String title, String content) {
        this.diaryId = id;
        this.title = title;
        this.content = content;
        this.category = null;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
