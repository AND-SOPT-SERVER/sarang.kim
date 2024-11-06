package org.sopt.diary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DiaryRequest {
    private Long diaryId;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 1, max = 10, message = "Title must be between 1 and 10 characters")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    @Size(min = 1, max = 30, message = "Content must be between 1 and 30 characters")
    private String content;

    private String category;
    private boolean isPublic;

    public Long getDiaryId() {
        return diaryId;
    }

    public @NotBlank(message = "Title cannot be blank") @Size(min = 1, max = 10, message = "Title must be between 1 and 10 characters") String getTitle() {
        return title;
    }

    public @NotBlank(message = "Content cannot be blank") @Size(min = 1, max = 30, message = "Content must be between 1 and 30 characters") String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public boolean isPublic() { return isPublic; }
    public void setPublic(boolean isPublic) { this.isPublic = isPublic; }
}
