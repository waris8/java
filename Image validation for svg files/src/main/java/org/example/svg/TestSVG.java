package org.example.svg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

public class TestSVG {
    String filePath="";
    TestSVG(String filePath) throws Exception {
        this.filePath=filePath;
        createImage();
    }


    public void createImage() throws Exception{
        String svg_URI_input = new File("/Users/gulam.waris/ktest2.svg").toURI().toString();
        TranscoderInput input_svg_image = new TranscoderInput(svg_URI_input);
        //Step-2: Define OutputStream to PNG Image and attach to TranscoderOutput
        OutputStream png_ostream = new FileOutputStream(filePath);
        TranscoderOutput output_png_image = new TranscoderOutput(png_ostream);
        // Step-3: Create PNGTranscoder and define hints if required
        PNGTranscoder my_converter = new PNGTranscoder();
        // Step-4: Convert and Write output
        System.out.println("It will print");
        my_converter.transcode(input_svg_image, output_png_image);
        System.out.println("It will not print");
        png_ostream.flush();
        png_ostream.close();
    }

    public static void main(String[] args) throws Exception {
        TestSVG svg = new TestSVG("/Users/gulam.waris/ktest2.png");
    }
}
