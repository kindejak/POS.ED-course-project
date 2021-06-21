package pos.app;

import java.awt.*;
import java.awt.print.*;

public class Print extends App implements Printable {

        // Attributes..
        private PrinterJob printerJob;
        private PageFormat pageFormat;
        private Paper paper;
        private String header;
        private String body;

        private final int MARGIN = 1;

        public void PrintReceipt(String header, String body) throws PrinterException {

            this.header = header;
            this.body = body;
            printerJob = PrinterJob.getPrinterJob();
            pageFormat = printerJob.defaultPage(); // Getting the page format

            paper = new Paper(); // Create a new paper


            // For receipt which will not be larger than 1000 points
            int width = 216;
            int height = 1000;

            paper.setImageableArea(MARGIN, MARGIN, width, height);
            pageFormat.setPaper(paper);

            pageFormat.setOrientation(PageFormat.PORTRAIT);
            printerJob.setPrintable(this, pageFormat);

            try {

                printerJob.print();

            } catch (PrinterException e) {
                throw new PrinterException("Printing Failed.");

            }

        }

        /**
         * Printing with Graphics..
         */
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {

            if (pageIndex > 0) {
                return NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.setColor(Color.black);
            int size = 12;
            int y= 0;
            g2d.setFont(new Font("Times New Roman", Font.BOLD, size));
            for (String line : header.split(System.lineSeparator())){
                y += size;
                g2d.drawString(line, 4, y); //align right
            }
            size = 10;
            g2d.setFont(new Font("Times New Roman", Font.PLAIN, size));
            for (String line : body.split(System.lineSeparator())){
                y += size;
                g2d.drawString(line, 216 - graphics.getFontMetrics().stringWidth(line), y); // align right
            }

            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            return PAGE_EXISTS;

        }


}
