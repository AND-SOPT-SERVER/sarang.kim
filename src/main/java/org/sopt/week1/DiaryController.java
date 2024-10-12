package org.sopt.week1;

import java.util.List;

public class DiaryController {
    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();

    Status getStatus() {
        return status;
    }

    void boot() {
        this.status = Status.RUNNING;
    }

    void finish() {
        this.status = Status.FINISHED;
    }

    // APIS
    final List<Diary> getList() {
        return diaryService.getDiaryList();
    }

    final void post(final String body) {
        if (body.length() > 30) {
            throw new IllegalArgumentException("글자수 제한");
        }
        diaryService.writeDiary(body);
    }

    final void delete(final String id) {
        diaryService.deleteDiary(Long.parseLong(id));
    }

    final void patch(final String id, final String body) {
        if (body.length() > 30) {
            throw new IllegalArgumentException("글자수 제한");
        }
        diaryService.editDiary(Long.parseLong(id), body);
    }

    final List<Diary> getDeletedList() {
        return diaryService.getDeletedDiaryList();
    }

    final void restore(final String id) {
        diaryService.restoreDiary(Long.parseLong(id));
    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}