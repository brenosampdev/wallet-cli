package org.example.core.middleware.core;

import java.util.HashMap;
import java.util.Map;

public class Context {
    public Map<String, Object> data = new HashMap<>();

    public Context(Map<String, Object> data){
        this.data.putAll(data);
    }
}
