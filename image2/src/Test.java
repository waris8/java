import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Test {

//    private static final Tika TIKA = new Tika();
//    public static boolean isImageMimeType(File src) {
//        try (FileInputStream fis = new FileInputStream(src)) {
//            String mime = TIKA.detect(fis, src.getName());
//            return mime.contains("/")
//                    && mime.split("/")[0].equalsIgnoreCase("image");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void saveAsjpeg() throws Exception {

        // Create a JPEG transcoder
        JPEGTranscoder t = new JPEGTranscoder();

        // Set the transcoding hints.
        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));

        // Create the transcoder input.
        String svgURI = new File("/Users/gulam.waris/xssevilsvg.svg").toURI().toString();
        TranscoderInput input = new TranscoderInput(svgURI);

        // Create the transcoder output.
        OutputStream ostream = new FileOutputStream("/Users/gulam.waris/ttt.jpeg");
        TranscoderOutput output = new TranscoderOutput(ostream);

        // Save the image.
        t.transcode(input, output);

        // Flush and close the stream.
        ostream.flush();
        ostream.close();
        System.exit(0);
    }
    public static void main(String[] args) throws Exception{
        File file = new File("/Users/gulam.waris/xssevilsvg.svg");
//        System.out.println(isImageMimeType(file));
        saveAsjpeg();
//        try {
//            //image is InputStream
//            InputStream is = new FileInputStream(file);
//            byte[] byteArray = IOUtils.toByteArray(is);
//            ImageFormat mimeType = Imaging.guessFormat(byteArray);
//            if (mimeType == ImageFormats.JPEG) {
//                System.out.println(mimeType);
//                System.out.println(true);
//            } else {
//                // handle image of different format. Ex: PNG
//                System.out.println(mimeType);
//                System.out.println(false);
//            }
//        } catch (ImageReadException e) {
//            //not an image
//        }
    }
}
