package com.designpattern.srp;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Journal {
    private final List<String> entries = new ArrayList<>();

    public void addEntry(String events) {
        entries.add(events);
    }

    public String removeEntry(int indexOfEvent) {
        return entries.remove(indexOfEvent);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }
}

class Persistence { //Instead of having logic to persist Journal to file system in Journal class we are utilizing Persistence class
    public void saveToFile(Journal journal, String fileName) throws FileNotFoundException {
        try (PrintStream ps = new PrintStream(fileName)) {
            ps.println(journal);
        }
    }
}

public class JournalSRP {
    public static void main(String[] args) throws Exception {
        Journal journal = new Journal();
        journal.addEntry("1: Great Fun Today");
        journal.addEntry("2: Happy Learning");
        System.out.println(journal);
        Persistence persistence = new Persistence();
        String filename = "C:\\Files\\Codes\\file.txt";
        persistence.saveToFile(journal, filename);
        Runtime.getRuntime().exec("notepad.exe " + filename);
    }
}
