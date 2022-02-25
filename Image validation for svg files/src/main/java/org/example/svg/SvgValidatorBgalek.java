package org.example.svg;


import com.github.bgalek.security.svg.SvgSecurityValidator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SvgValidatorBgalek {
    public static void main(String[] args) throws Exception{
        byte[] array = Files.readAllBytes(Paths.get("/Users/gulam.waris/sc1.png"));
        SvgSecurityValidator validator = new SvgSecurityValidator();
        System.out.println(validator.validate(array).hasViolations());
    }
}
//xssevilsvg