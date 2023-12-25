package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectorySearchTask extends RecursiveTask<List<File>> {
    private final File directory;
    private static final int FILE_THRESHOLD = 1000;

    public DirectorySearchTask(File directory) {
        this.directory = directory;
    }

    @Override
    protected List<File> compute() {
        List<File> result = new ArrayList<>();

        if (directory.isDirectory()) {
            List<DirectorySearchTask> subTasks = new ArrayList<>();
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    DirectorySearchTask task = new DirectorySearchTask(file);
                    task.fork();
                    subTasks.add(task);
                }
            }

            for (DirectorySearchTask subTask : subTasks) {
                result.addAll(subTask.join());
            }

            if (directory.listFiles().length > FILE_THRESHOLD) {
                result.add(directory);
            }
        }

        return result;
    }
}
