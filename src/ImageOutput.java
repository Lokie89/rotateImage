import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageOutput {
    public BufferedImage getImage(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        return image;
    }

    public void writeImage(File file, String outputPath) throws IOException {
        isExistPath(outputPath);
        writeRotateImage(file, outputPath);
        writeSliceImage(file, outputPath);
    }

    private void writeRotateImage(File file, String outputPath) throws IOException {
        BufferedImage image = getImage(file);
        final double rads = Math.toRadians(new RandomNumberGenerator().getRandomNumber());
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads, 0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);

        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotatedImage);
        writeImage(getSliceImageRotated(rotatedImage), new File(outputPath + "\\rotate-" + file.getName()));
    }

    private BufferedImage getSliceImageRotated(BufferedImage rotatedImage) {
        final int h = rotatedImage.getHeight();
        final int startV = h / 3;
        final int endV = h * 2 / 3;

        final int w = rotatedImage.getWidth();
        final int startW = w / 3;
        final int endW = w * 2 / 3;
        final BufferedImage sliceImageRotated = rotatedImage.getSubimage(startW, startV, endW, endV);
        return sliceImageRotated;
    }

    private void writeSliceImage(File file, String outputPath) throws IOException {
        writeSliceVerticalImage(file, outputPath);
        writeSliceHorizonImage(file, outputPath);
    }

    private void writeSliceVerticalImage(File file, String outputPath) throws IOException {
        BufferedImage image = getImage(file);
        final int w = (int) Math.floor(image.getWidth()) / 2;
        final int h = (int) Math.floor(image.getHeight());
        final BufferedImage sliceImage = image.getSubimage(0, 0, w, h);
        writeImage(sliceImage, new File(outputPath + "\\sliceV1-" + file.getName()));

        final BufferedImage sliceImage2 = image.getSubimage(w, 0, w, h);
        writeImage(sliceImage2, new File(outputPath + "\\sliceV2-" + file.getName()));
    }

    private void writeSliceHorizonImage(File file, String outputPath) throws IOException {
        BufferedImage image = getImage(file);
        final int w = (int) Math.floor(image.getWidth());
        final int h = (int) Math.floor(image.getHeight()) / 2;
        final BufferedImage sliceImage = image.getSubimage(0, 0, w, h);
        writeImage(sliceImage, new File(outputPath + "\\sliceH1-" + file.getName()));

        final BufferedImage sliceImage2 = image.getSubimage(0, h, w, h);
        writeImage(sliceImage2, new File(outputPath + "\\sliceH2-" + file.getName()));
    }


    private void writeImage(BufferedImage image, File file) throws IOException {
        ImageIO.write(image, "JPG", file);
    }

    private void isExistPath(String outputPath) {
        File folder = new File(outputPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

}
