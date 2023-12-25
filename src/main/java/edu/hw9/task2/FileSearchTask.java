package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

class FileSearchTask extends RecursiveTask<List<File>> {
    private final File file;
    private final long size;
    private final String extension;

    FileSearchTask(File file, long size, String extension) {
        this.file = file;
        this.size = size;
        this.extension = extension;
    }

    @Override
    protected List<File> compute() {
        List<File> result = new ArrayList<>();

        if (file.isFile() && file.length() > size && file.getName().endsWith(extension)) {
            result.add(file);
        }

        if (file.isDirectory()) {
            List<FileSearchTask> subTasks = new ArrayList<>();
            File[] files = file.listFiles();

            if (files != null) {
                for (File subFile : files) {
                    FileSearchTask task = new FileSearchTask(subFile, size, extension);
                    task.fork();
                    subTasks.add(task);
                }
            }

            for (FileSearchTask subTask : subTasks) {
                result.addAll(subTask.join());
            }
        }

        return result;
    }
}
