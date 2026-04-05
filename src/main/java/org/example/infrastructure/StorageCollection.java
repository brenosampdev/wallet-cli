package org.example.infrastructure;

import java.nio.file.Path;

public enum StorageCollection {
    TRANSACTIONS("transactions/data.json"),
    USER("user/data.json"),
    GOALS("goals/data.json"),
    CATEGORIES("categories/data.json");

    private final Path relativePath;

    StorageCollection(String relativePath) {
        this.relativePath = Path.of(relativePath);
    }

    public Path relativePath() {
        return relativePath;
    }
}
