package com.nio.test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by frinder_liu on 2016/4/13.
 */
public class NioTest {


   @Test
    public void fileAsyncCallback() {
        String pathStr = "D:\\LEARN\\html\\test.txt";
        Path path = Paths.get(pathStr);
        try {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
            final ByteBuffer buffer = ByteBuffer.allocate(100_000);
            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println(result);
                    buffer.flip();
                    System.out.println(Charset.defaultCharset().decode(buffer));
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    System.out.println(exc.getMessage());
                }
            });
            System.out.println("over...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    @Test
    public void fileAsync() {
        String pathStr = "D:\\LEARN\\html\\netty.pdf";
        Path path = Paths.get(pathStr);
        try {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
            ByteBuffer buffer = ByteBuffer.allocate(1000);
            Future<Integer> result = channel.read(buffer, 0);
            while (!result.isDone()) {
                System.out.println("do something other!");
            }
            Integer i = result.get();
            System.out.println(i);
            buffer.flip();
            System.out.println(Charset.defaultCharset().decode(buffer));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //    @Test
    public void fileChannel() {
        String pathStr = "D:\\LEARN\\html\\test.txt";
        Path path = Paths.get(pathStr);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
            System.out.println(channel.size());
            channel.read(buffer, channel.size() - 56);
            buffer.flip();
            System.out.println(Charset.defaultCharset().decode(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // @Test
    public void fileWatcher() {
        String pathStr = "D:\\LEARN\\html";
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path path = FileSystems.getDefault().getPath(pathStr);
            WatchKey key = path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
            WatchKey temp;
            while (true) {
                temp = watcher.take();
                for (WatchEvent<?> event : temp.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        System.out.println("changed...");
                    }
                }
                key.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // @Test
    public void fileNewRW() {
        String pathStr = "D:\\LEARN\\html\\test.txt";
        Path path = Paths.get(pathStr);
        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String line : lines) {
                System.out.println(line);
            }

            byte[] bytes = Files.readAllBytes(path);
            for (byte b : bytes) {
                System.out.print(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // @Test
    public void fileOldRW() {
        String pathStr = "D:\\LEARN\\html\\test.txt";
        Path path = Paths.get(pathStr);
        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (
                BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)
        ) {
            writer.write("hello world...");
            writer.newLine();
            writer.write("hello jack...");
            writer.newLine();
            writer.write("hello lucy...");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (
                BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /*
       -----------------------------------------------------------------------------------------
     */


    // @Test
    public void fileCURD() {
        String pathStr = "D:\\LEARN\\html\\test.txt";
        Path path = Paths.get(pathStr);
        try {
            if (Files.notExists(path)) {
                Path file = Files.createFile(path);
                System.out.println(file);
            } else {
                System.out.println("file already exists!");
            }
            // Files.delete(path);
            //Path target = Paths.get("D:\\LEARN\\html\\test1.txt");
            // Files.copy(path, target);
            // Files.move(path, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /*
       -----------------------------------------------------------------------------------------
    */


    //  @Test
    public void pathVisitor() {
        String pathStr = "D:\\LEARN\\html";
        Path path = Paths.get(pathStr);
        System.out.println(path.toString());
        System.out.println(path.getFileName());
        System.out.println(path.startsWith("D:\\LEARN"));
        /**
         * 查找当前目前下所有以 .html 结尾的文件
         */
        try (
                DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.html")
        ) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("**********************************************");
        System.out.println("****************华丽的分割了*******************");
        System.out.println("**********************************************");

        /**
         * 查找当前目录及子目录下所有以 .html 结尾的文件
         */
        try {
            Files.walkFileTree(path, new FindHtmlVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class FindHtmlVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toString().endsWith(".html")) {
                System.out.println(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }


    /*
       -----------------------------------------------------------------------------------------
     */


    //@Test
    public void pathInfo() {
        String pathStr = "D:\\LEARN\\html";
        Path path = Paths.get(pathStr);
        // 也可以这样获得path
        // path = FileSystems.getDefault().getPath(pathStr);
        System.out.println(path.getFileName());
        System.out.println(path.getNameCount());
        System.out.println(path.getParent());
        System.out.println(path.getRoot());
        System.out.println(path.subpath(1, 2));
        try {
            System.out.println(Files.getLastModifiedTime(path));
            System.out.println(Files.size(path));
            System.out.println(Files.isSymbolicLink(path));
            System.out.println(Files.isDirectory(path));
            System.out.println(Files.readAttributes(path, "*"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
