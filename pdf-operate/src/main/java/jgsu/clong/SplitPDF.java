package jgsu.clong;


import com.spire.pdf.PdfDocument;

public class SplitPDF {

    public static void main(String[] args)

    {
        //加载PDF文档
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile("C:\\Users\\PlusTech\\Desktop\\pdf\\统计插入测试文件.pdf");
        //拆分为多个PDF文档
        doc.split("C:\\Users\\PlusTech\\Desktop\\pdf\\splitDocument-{0}.pdf", 0);
        doc.close();
    }
}