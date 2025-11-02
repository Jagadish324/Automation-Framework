package com.arc.helper;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFReader {
    public static String getPDFTextContent(String pdfPath) {
        File file = new File(pdfPath);
        try (PDDocument document = Loader.loadPDF(file)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            System.out.println(text);
            return text;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
