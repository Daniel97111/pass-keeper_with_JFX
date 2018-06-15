package apk.controlPanel;

import apk.model.PasswordEntry;
import apk.model.PasswordSafe;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileSafeController {

    public void saveToFile(List<PasswordEntry> passwordEntries, boolean append) {
        Gson gson = new Gson();

        List<String> jsons = passwordEntries.stream()
                .map(gson::toJson)
                .collect(Collectors.toList());
        File file = new File("src/main/resources/pass/new-pass-keeper-file.pwm");

        try {
            FileUtils.writeLines(file, jsons, append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PasswordSafe init(String readFile) {

        Collection<PasswordEntry> passwordEntries = readFromFile(readFile);

        return new PasswordSafe(passwordEntries);
    }

    private Collection<PasswordEntry> readFromFile(String readFile) {

        Gson gson = new Gson();

        File file = new File(readFile);
        try {
            return FileUtils.readLines(file, "UTF-8").stream()
                    .map(s -> gson.fromJson(s, PasswordEntry.class))
                    .collect(Collectors.toSet());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }
}
