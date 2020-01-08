package jgsu.clong;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class SplitFileTest {
    public static void main(String[] args) {
        splitExistFile();
    }

    public static void splitExistFile() {

        // 文档路径
        String path = "C:\\Users\\PlusTech\\Desktop\\pdf\\";
        // 待拆分文件名
        String pdfFileName = "统计插入测试文件.pdf";
        // 每个文件最大页数
        int filePageSize = 2;
        // 待拆分文件的总页数
        int totalPage;
        // 拆分后的文件数量
        int splitFileNum;
        int pageIndex = 1;
        PdfReader reader = null;
        try {
            String orignName = pdfFileName.split("\\.")[0];
            reader = new PdfReader(path + pdfFileName);
            totalPage = reader.getNumberOfPages();
            splitFileNum = totalPage % filePageSize == 0 ? totalPage / filePageSize : totalPage / filePageSize + 1;
            for (int i = 0; i < splitFileNum; i++) {
                String newFileName = path + orignName + "_" + (i + 1) + ".pdf";
                // 新建一个PDF文件
                Document document = null;
                PdfWriter writer = null;
                try {
                    document = new Document();
                    writer = PdfWriter.getInstance(document, new FileOutputStream(newFileName));
                    document.open();
                    PdfContentByte pdfContentByte = writer.getDirectContent();
                    for (int j = 0; j < filePageSize; j++) {
                        document.newPage();
                        pdfContentByte.addTemplate(writer.getImportedPage(reader, pageIndex), 0, 0);
                        pageIndex++;
                        if (pageIndex > totalPage)
                            break;

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    //这个地方要特别注意资源关闭的顺序
                    if (document != null)
                        document.close();

                    if (writer != null)
                        writer.close();

                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) reader.close();
        }

    }
}
