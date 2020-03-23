import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath = "C:\\Users\\traeu\\Desktop\\www";
            List<File> fileList = new FileOutput().getFileList(filePath);
            for (int i = 0; i < fileList.size(); i++) {
                File file = fileList.get(i);
                new ImageOutput().writeImage(file, filePath + "\\modified");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
