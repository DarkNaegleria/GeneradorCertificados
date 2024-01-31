package application;

import java.io.IOException;
import java.net.URL;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFontFactory.EmbeddingStrategy;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

public class PDFGenerator {
	
    public PDFGenerator(Controller controller) {
        this.controller = controller;
        setupDestination();
    }
	
    private Controller controller;
    private String DEST;
	private final String TEMPLATE = "/src/resources/Plantilla.png";
	//private final String TEMPLATE = "/resources/Plantilla.png";
	private static final String ArialBlack = "/src/resources/font/ARIALBD.TTF";
	//private static final String ArialBlack = "./src/resources/font/ARIALBD.TTF";
  
    private void setupDestination() {
        DEST = controller.getDirectorySelected() + controller.getFileName() + ".pdf";
    }
    
    private void addParagraph(ParagraphBuilder paragraphBuilder, Document document, String text, float x, float y, float width) {
        Paragraph paragraph = paragraphBuilder.buildParagraph(document, text, x, y, width);
        document.add(paragraph);
    }
    
	public void makePDF () throws IOException {
		
		PdfWriter writer = new PdfWriter(DEST);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf, PageSize.A4.rotate());
		document.setMargins(0, 0, 0, 0);
		PdfFont arialBlack = PdfFontFactory.createFont(ArialBlack, EmbeddingStrategy.PREFER_EMBEDDED);
		URL imageUrl = getClass().getResource(TEMPLATE);
		Image template = new Image(ImageDataFactory.create(imageUrl));
		float width = PageSize.A4.rotate().getWidth();
		float height = PageSize.A4.rotate().getHeight();
		float xForTicketCode = (controller.getTicketType().equals("BOLETA:")) ? 493 : 503;
		template.scaleAbsolute(width, height);
		template.setFixedPosition(0, 0);

		document.add(template);

		ParagraphBuilder paragraphBuilder = new ParagraphBuilder(arialBlack, 13.5f);
		
        addParagraph(paragraphBuilder, document, controller.getCertificateNumber(), 702, 420.3f, 120);
        addParagraph(paragraphBuilder, document, controller.getDueDateFormated(), 243, 374.4f, 400);
        addParagraph(paragraphBuilder, document, controller.getTicketType(), 425, 374.4f, 400);
        addParagraph(paragraphBuilder, document, controller.getTicketCode(), xForTicketCode, 374.4f, 400);
        addParagraph(paragraphBuilder, document, controller.getAddress(), 150, 277.4f, 600);
        addParagraph(paragraphBuilder, document, controller.getOwner(), 243, 259.7f, 600);
        addParagraph(paragraphBuilder, document, controller.getBusinessType(), 107, 241.75f, 600);
        addParagraph(paragraphBuilder, document, controller.getBusinessName(), 178, 224.5f, 600);
        addParagraph(paragraphBuilder, document, controller.getIssueDateFormated(), 618, 198.2f, 400);

		switch (controller.getSanitaryLevel()) {
	    case "Excelente":
	        CrossDrawer crossDrawer1 = new CrossDrawer(pdf);
	        crossDrawer1.drawCross(166);
	        break;
	    case "Bueno":
	        CrossDrawer crossDrawer2 = new CrossDrawer(pdf);
	        crossDrawer2.drawCross(329.9);
	        break;
	    case "Regular":
	        CrossDrawer crossDrawer3 = new CrossDrawer(pdf);
	        crossDrawer3.drawCross(526.4);
	        break;
	    case "Deficiente":
	        CrossDrawer crossDrawer4 = new CrossDrawer(pdf);
	        crossDrawer4.drawCross(690.5);
	        break;
		}
		document.close();
	}
}

