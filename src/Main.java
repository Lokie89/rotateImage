import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<File> fileList = new FileOutput().getFileList("C:\\Users\\amoeba\\Desktop\\www");
            String outputPath = "C:\\Users\\amoeba\\Desktop\\www2";
            for (int i = 0; i < fileList.size(); i++) {
                File file = fileList.get(i);
                new ImageOutput().writeImage(file, outputPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
