package org.example.infrastructure;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class JsonStorage {
  private final Gson gson;
  private static JsonStorage instance = null;

  private final Path defaultPath = Path.of(System.getProperty("user.home"), ".wallet"); 

  private JsonStorage() {
    this.gson = new Gson();
  }

  public static synchronized JsonStorage getInstance(){
    if(instance == null){
      instance = new JsonStorage();
    }

    return instance;
  }

  private Path getPath(Path path){
    return this.defaultPath.resolve(path);
  }  
  
  
  public <T> Optional<T> read(StorageCollection collection, Class<T> clazz) throws IOException {
    return read(collection.relativePath(), clazz);
  }

  public <T> Optional<T> read(StorageCollection collection, Type type) throws IOException {
    return read(collection.relativePath(), type);
  }

  public void write(StorageCollection collection, Object value) throws IOException {
    write(collection.relativePath(), value);
  }

  public <T> Optional<T> read(Path path, Class<T> clazz) throws IOException {
    if (!Files.exists(this.getPath(path))) {
      return Optional.empty();
    }

    String jsonContent = Files.readString(this.getPath(path));

    if (jsonContent == null || jsonContent.isBlank()) {
      return Optional.empty();
    }

    return Optional.ofNullable(gson.fromJson(jsonContent, clazz));
  }

  public <T> Optional<T> read(Path path, Type type) throws IOException {
    if (!Files.exists(this.getPath(path))) {
      return Optional.empty();
    }

    String jsonContent = Files.readString(this.getPath(path));

    if (jsonContent == null || jsonContent.isBlank()) {
      return Optional.empty();
    }

    return Optional.ofNullable(gson.fromJson(jsonContent, type));
  }

  public void write(Path path, Object value) throws IOException {
    Path parent = this.getPath(path).getParent();
    if (parent != null) {
      Files.createDirectories(parent);
    }

    String jsonContent = gson.toJson(value);

    Files.writeString(
        this.getPath(path),
        jsonContent,
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING,
        StandardOpenOption.WRITE
    );
  }

}
