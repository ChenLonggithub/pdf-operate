package jgsu.clong;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.*;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {
/*    public static void main(String[] args) {
        String s = splitFile("C:\\Users\\PlusTech\\Desktop\\pdf\\统计插入测试文件.pdf", 1, 33);

        System.out.println(s);


    }*/

    public static void main(String[] args) {
        try {
            Main.splitPDF(new FileInputStream("C:\\Users\\PlusTech\\Desktop\\pdf\\统计插入测试文件.pdf"),
                    new FileOutputStream("C:\\output1.pdf"), 1, 12);
            Main.splitPDF(new FileInputStream("C:\\Users\\PlusTech\\Desktop\\pdf\\统计插入测试文件.pdf"),
                    new FileOutputStream("C:\\output2.pdf"), 13, 20);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author viralpatel.net
     *
     * @param inputStream Input PDF file
     * @param outputStream Output PDF file
     * @param fromPage start page from input PDF file
     * @param toPage end page from input PDF file
     */
    public static void splitPDF(InputStream inputStream,
                                OutputStream outputStream, int fromPage, int toPage) {
        Document document = new Document();
        try {
            PdfReader inputPDF = new PdfReader(inputStream);

            int totalPages = inputPDF.getNumberOfPages();

            //make fromPage equals to toPage if it is greater
            if(fromPage > toPage ) {
                fromPage = toPage;
            }
            if(toPage > totalPages) {
                toPage = totalPages;
            }

            // Create a writer for the outputstream
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF data
            PdfImportedPage page;

            while(fromPage <= toPage) {
                document.newPage();
                page = writer.getImportedPage(inputPDF, fromPage);
                cb.addTemplate(page, 0, 0);
                fromPage++;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen())
                document.close();
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public static String splitFile(String pdfFile,Integer from,Integer end){

        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(pdfFile);
            int n = reader.getNumberOfPages();
            if(end==0){
                end = n;
            }
            List<String> savepaths = new ArrayList<String>();
            int a = pdfFile.lastIndexOf(".pdf");
            String staticpath = pdfFile.substring(0, a);
            String savepath = staticpath+ "_from_"+from+"_to_"+end+"_.pdf";
            savepaths.add(savepath);
            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(savepaths.get(0)));
            document.open();
            for(int j=from; j<=end; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();
            return new File(savepath).getName();
        } catch (IOException e) {
            return null;
        } catch(DocumentException e) {
            return null;
        }
    }
}
