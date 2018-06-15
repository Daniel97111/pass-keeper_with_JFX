package apk.model;

import java.util.*;
import java.util.stream.Collectors;

public class PasswordSafe {

    private HashMap<Integer, PasswordEntry> entries = new HashMap<>();
    private Integer nextId = 0;

    public PasswordSafe() {
    }

    public PasswordSafe(Collection<PasswordEntry> passwordEntries) {
        passwordEntries.forEach(pe -> entries.put(pe.getId(), pe));
        nextId = entries.values().stream()
                .mapToInt(PasswordEntry::getId)
                .max()
                .orElse(0) + 1;
    }

    public Collection<PasswordEntry> all() {
        return entries.values();
    }

    public PasswordEntry addEntries(String service, String login, String password) {
        Integer id = nextId++;
        PasswordEntry passwordEntry = new PasswordEntry(id, service, login, password);
        entries.put(passwordEntry.getId(), passwordEntry);
        return passwordEntry;
    }

    public void removeEntries(Integer id) {
        entries.remove(id);
    }


    public String show(String service) {
        for (PasswordEntry passwordEntry : entries.values()) {
            if (passwordEntry.getService().equalsIgnoreCase(service)) {
                return "L: " + passwordEntry.getLogin() + "\nP: " + passwordEntry.getPassword() + "\nID: " + passwordEntry.getId();
            }
        }
        return null;// dodać exception !!!!!!!!!!!!!!!!!!!!
    }


    public boolean exists(String service, String login) {
        return entries.values().stream()
                .anyMatch(e -> e.getService().equals(service) && e.getLogin().equals(login));
    }

    public boolean existsId(Integer id) {
        return entries.values().stream()
                .anyMatch(e -> e.getId().equals(id));
    }

}