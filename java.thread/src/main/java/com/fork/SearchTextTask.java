package com.fork;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by frinder_liu on 2016/4/21.
 */
public class SearchTextTask {

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        Path path = Paths.get("D:\\apps\\javaconf");
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        long result = forkJoinPool.invoke(new DirSearchTask(path, "jdbc"));
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
    }

}


class FileSearchTask extends RecursiveTask<Long> {

    private final Path path;
    private final String searchText;

    public FileSearchTask(Path path, String searchText) {
        this.path = path;
        this.searchText = searchText;
    }

    @Override
    protected Long compute() {
        long count = new FileSearch().occurrencesCount();
        System.out.println(getClass().getName() + " : " + Thread.currentThread().getName() + " : " + count);
        return count;
    }

    class FileSearch {

        String[] wordsIn(String line) {
            return line.trim().split("(\\s|\\p{Punct})+");
        }

        public Long occurrencesCount() {
            long count = 0;
            try {
                List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                for (String line : lines) {
                    for (String word : wordsIn(line)) {
                        if (searchText.equals(word)) {
                            count = count + 1;
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            return count;
        }

    }
}


class DirSearchTask extends RecursiveTask<Long> {

    private final Path path;
    private final String searchText;

    public DirSearchTask(Path path, String searchText) {
        this.path = path;
        this.searchText = searchText;
    }

    @Override
    protected Long compute() {
        long result = 0;
        try {
            List<Path> paths = new ArrayList<>();
            Files.walkFileTree(path, new FileVisitor(paths));
            List<RecursiveTask<Long>> tasks = new LinkedList<>();
            RecursiveTask<Long> task;
            for (Path path : paths) {
                task = new FileSearchTask(path, searchText);
                tasks.add(task);
                task.fork();
            }
            for (RecursiveTask<Long> tak : tasks) {
                result += tak.join();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(getClass().getName() + " : " + Thread.currentThread().getName() + " : " + result);
        return result;
    }


    class FileVisitor extends SimpleFileVisitor<Path> {

        private final List<Path> paths;

        public FileVisitor(List<Path> paths) {
            this.paths = paths;
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            if (path.toFile().isFile()) {
                paths.add(path);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}