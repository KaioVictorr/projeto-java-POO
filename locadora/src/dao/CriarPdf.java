package dao;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTable;

import com.lowagie.text.Anchor;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class CriarPdf {
	
	public void criarPfd(JTable jTable,String nomeDoArquivo) throws DocumentException, IOException {
		//cria um documento
		Document document = new Document(PageSize.A4);
				
		try {
			//cria o pdf
            PdfWriter.getInstance(document, new FileOutputStream(nomeDoArquivo));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
		
		//abre o pdf para escrever
		document.open();
				
                     
        // Cria a tabelaPdf
        PdfPTable table = new PdfPTable(jTable.getColumnCount()); 
          
 
        PdfPCell columnHeader;
        // celula de Cabeçalho da tabela
        
        // Pegamos os nomes das colunas da JTable e colocamos na pdfTable
        
        for (int column = 0; column < jTable.getColumnCount(); column++) {                 
            columnHeader = new PdfPCell(new Phrase(jTable.getColumnName(column)));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
        }
        
        //Define 1 linha como cabeçalho
        table.setHeaderRows(1);
        
        //Preenche as linhas da tabelaPdf                 
        for (int row = 0; row < jTable.getRowCount(); row++) {                
            for (int column = 0; column < jTable.getColumnCount(); column++) { 
                table.addCell(jTable.getValueAt(row, column).toString());
            }
        } 
        
        //Adiciona a tabela no documento        
        document.add(table);
        
        document.close();
        
        File arquivo = new File(nomeDoArquivo);
        if (arquivo.exists()) {
            Desktop.getDesktop().open(arquivo); // Abre no visualizador padrão
        }
		
	}
}
