package application;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class ParagraphBuilder {

    private PdfFont font;
    private float fontSize;

    public ParagraphBuilder(PdfFont font, float fontSize) {
        this.font = font;
        this.fontSize = fontSize;
    }

    public Paragraph buildParagraph(Document document, String text, float x, float y, float width) {
        return new Paragraph(text)
                .setFont(font)
                .setFontColor(ColorConstants.BLACK, 1)
                .setFontSize(fontSize)
                .setFixedPosition(x, y, width);
    }
}
