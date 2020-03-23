import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileOutput {
    public List<File> getFileList(String url) throws IOException {
        Path path = Paths.get(url);
        List<File> fileList = new ArrayList<>();
        Stream<Path> stream = Files.list(path);
        stream.forEach(p -> fileList.add(new File(url +"\\"+ p.getFileName())));
        return fileList;
    }
}
