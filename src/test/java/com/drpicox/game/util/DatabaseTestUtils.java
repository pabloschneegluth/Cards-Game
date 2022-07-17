package com.drpicox.game.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class DatabaseTestUtils {
    private final List<JpaRepository> repositories;

    public DatabaseTestUtils(List<JpaRepository> repositories) {
        this.repositories = repositories;
    }

    public void clear() {
        tryClearAllRepositories();

        // Repeat the process, so it can throw the error, if any
        repositories.forEach(r -> r.deleteAll());
    }

    private void tryClearAllRepositories() {
        var fail = new AtomicBoolean(true);
        var times = 0;
        while (times < repositories.size() && fail.get()) {
            fail.set(false);
            times += 1;
            repositories.forEach(r -> {
                try {
                    r.deleteAll();
                } catch (Throwable t) {
                    fail.set(true);
                }
            });
        }
    }
}
