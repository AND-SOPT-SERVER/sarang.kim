package org.sopt.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final Map<Long, String> deletedStorage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();

    void save(final Diary diary) {
        //채번 과정
        final long id = numbering.addAndGet(1);
        //저장 과정
        storage.put(id, diary.getBody());
    }

    List<Diary> findAll() {
        // 1. diary 담을 자료구조
        final List<Diary> diaryList = new ArrayList<>();
        // 2. 저장된 모든 키를 가져와서 해당 키로 일기를 불러오기
        for (Long id : storage.keySet()) {
            String body = storage.get(id);
            // 2-1. 불러온 값을 구성한 자료구조로 이관
            diaryList.add(new Diary(id, body));
        }
        // 3. 불러온 자료구조를 응답
        return diaryList;
    }

    public Diary findById(final Long id) {
        String body = storage.get(id);
        return (body != null) ? new Diary(id, body) : null;
    }

    void deleteById(final Long id) {
        String body = storage.remove(id);
        deletedStorage.put(id, body);
    }

    void restoreById(final Long id) {
        String body = deletedStorage.remove(id); //삭제된 일기 복구
        storage.put(id, body);
    }

    void edit(final Long id, final String body) {
        storage.put(id, body);
    }

    List<Diary> findAllDeleted() {
        final List<Diary> deletedDiaryList = new ArrayList<>();
        for (Long id : deletedStorage.keySet()) {
            String body = deletedStorage.get(id);
            deletedDiaryList.add(new Diary(id, body));
        }
        return deletedDiaryList;
    }
}