package org.example.docx;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class InstrTextExtractor {
    public static void main(String[] args) {
        String filePath = "./abc.docx"; // Path to your DOCX file

        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    CTR ctr = run.getCTR();

                    // Check directly for instrText elements
                    List<CTText> instrTextList = ctr.getInstrTextList();
                    if (instrTextList != null && !instrTextList.isEmpty()) {
                        for (CTText instrText : instrTextList) {
                            System.out.println("Instruction Text: " + instrText.getStringValue());
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to process the DOCX file: " + e.getMessage());
        }
    }
}