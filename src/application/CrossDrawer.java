package application;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

public class CrossDrawer {

    private PdfDocument pdf;
    private final double y1 = 65;
    private final double y2 = 77;

    public CrossDrawer(PdfDocument pdf) {
        this.pdf = pdf;
    }

    public void drawCross(double x1) {
    	final double x2 = x1 + 52.3;
        PdfCanvas canvas = new PdfCanvas(pdf.getLastPage());
        canvas.setStrokeColor(ColorConstants.BLUE)
                .setLineWidth(1.5f)
                .moveTo(x1, y1)
                .lineTo(x2, y2)
                .moveTo(x2, y1)
                .lineTo(x1, y2)
                .stroke();
    }
}