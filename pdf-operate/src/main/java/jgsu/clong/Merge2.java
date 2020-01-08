package jgsu.clong;

import com.spire.pdf.*;
import java.io.*;

public class Merge2 {
    public static void main(String[] args) throws Exception {
        
        String outputFile = "C:\\Users\\PlusTech\\Desktop\\pdf\\all.pdf";
        FileInputStream stream1 = new FileInputStream(new File("C:\\Users\\PlusTech\\Desktop\\pdf\\splitDocument-0.pdf"));
        FileInputStream stream2 = new FileInputStream(new File("C:\\Users\\PlusTech\\Desktop\\pdf\\splitDocument-1.pdf"));
        FileInputStream stream3 = new FileInputStream(new File("C:\\Users\\PlusTech\\Desktop\\pdf\\splitDocument-2.pdf"));
        //加载PDF示例文档
        InputStream[] streams = new FileInputStream[]{stream1, stream2, stream3};

        //合并PDF文档
        PdfDocumentBase doc = PdfDocument.mergeFiles(streams);

        //保存文档
        doc.save(outputFile);
        doc.close();
    }
}