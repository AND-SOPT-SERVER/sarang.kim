package org.sopt.week1;

import java.util.List;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

    void writeDiary(final String body) {
        final Diary diary = new Diary(null, body);
        diaryRepository.save(diary);
    }

    List<Diary> getDiaryList() {
        return diaryRepository.findAll();
    }

    public void deleteDiary(final Long id) {
        validateDiaryExists(id);
        diaryRepository.deleteById(id);
    }

    public void editDiary(final Long id, final String body) {
        validateDiaryExists(id);
        diaryRepository.edit(id, body);
    }

    public void restoreDiary(final Long id) {
        Diary diary = diaryRepository.findById(id);
        if (diary == null) {
            diaryRepository.restoreById(id); //복구
        } else {
            throw new IllegalArgumentException("이미 존재하는 일기에요.");
        }
    }

    public List<Diary> getDeletedDiaryList() {
        return diaryRepository.findAllDeleted(); //삭제된 일기 목록 반환
    }

    private void validateDiaryExists(final Long id) {
        Diary diary = diaryRepository.findById(id);
        if (diary == null) {
            throw new IllegalArgumentException("아이디 " + id + " 존재하지 않음");
        }
    }

}