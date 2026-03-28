package org.example.core.parser;

import org.example.core.CommandContext;
import org.example.errors.handlers.CommandNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class ArgsParser {

        // wallet transaction --out --name="asda" --value=120.2
        public CommandContext parse(String[] args) throws Exception {
            Map<String, String> map = new HashMap<>();

            for (int i = 0; i < args.length; i++) {
                String arg = args[i];

                if(!arg.startsWith("--")){
                  throw new CommandNotFoundException("Unknow command: " + arg);
                }


                String key = arg.substring(2);

                if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                    map.put(key, args[i + 1]);
                    i++;
                } else {
                    map.put(key, "true");
                }
            }

            return new CommandContext(map);
        }
}



