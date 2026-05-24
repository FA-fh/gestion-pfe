package com.jee.gestionpfe.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;

import com.jee.gestionpfe.entities.StagePfe;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PdfReportService {

    private final StagePfeService stagePfeService;

    public byte[] generateStageReport(Long stageId) {
        StagePfe stage = stagePfeService.findById(stageId);

        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDType1Font titleFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
            PDType1Font textFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                float y = 780;

                y = writeLine(content, "Rapport de Stage PFE", titleFont, 18, 50, y);
                y -= 20;

                y = writeLine(content, "Sujet : " + safe(stage.getSujet()), textFont, 12, 50, y);
                y = writeLine(content, "Annee : " + safe(stage.getAnnee()), textFont, 12, 50, y);
                y = writeLine(content, "Date debut : " + safe(stage.getDateDebut()), textFont, 12, 50, y);
                y = writeLine(content, "Date fin : " + safe(stage.getDateFin()), textFont, 12, 50, y);
                y -= 10;

                if (stage.getEtudiant() != null) {
                    y = writeLine(content, "Etudiant : " + safe(stage.getEtudiant().getNom()) + " "
                            + safe(stage.getEtudiant().getPrenom()), textFont, 12, 50, y);
                    y = writeLine(content, "CNE : " + safe(stage.getEtudiant().getCne()), textFont, 12, 50, y);
                    y = writeLine(content, "Email : " + safe(stage.getEtudiant().getEmail()), textFont, 12, 50, y);

                    if (stage.getEtudiant().getFiliere() != null) {
                        y = writeLine(content, "Filiere : " + safe(stage.getEtudiant().getFiliere().getIntitule()),
                                textFont, 12, 50, y);
                    }
                }

                y -= 10;

                if (stage.getEntreprise() != null) {
                    y = writeLine(content, "Entreprise : " + safe(stage.getEntreprise().getNom()), textFont, 12, 50, y);
                    y = writeLine(content, "Ville : " + safe(stage.getEntreprise().getVille()), textFont, 12, 50, y);
                    y = writeLine(content, "Email : " + safe(stage.getEntreprise().getEmail()), textFont, 12, 50, y);
                }

                y -= 10;

                if (stage.getEncadrantAcademique() != null) {
                    y = writeLine(content, "Encadrant academique : "
                            + safe(stage.getEncadrantAcademique().getNom()) + " "
                            + safe(stage.getEncadrantAcademique().getPrenom()), textFont, 12, 50, y);
                }

                if (stage.getEncadrantEntreprise() != null) {
                    y = writeLine(content, "Encadrant entreprise : "
                            + safe(stage.getEncadrantEntreprise().getNom()) + " "
                            + safe(stage.getEncadrantEntreprise().getPrenom()), textFont, 12, 50, y);
                }

                y -= 15;

                y = writeBlock(content, "Description", stage.getDescription(), titleFont, textFont, 50, y);
                y = writeBlock(content, "Objectifs", stage.getObjectifs(), titleFont, textFont, 50, y);
                y = writeBlock(content, "Solution", stage.getSolution(), titleFont, textFont, 50, y);
                y = writeBlock(content, "Demarche", stage.getDemarche(), titleFont, textFont, 50, y);
                writeBlock(content, "Outils", stage.getOutils(), titleFont, textFont, 50, y);
            }

            document.save(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la generation du PDF", e);
        }
    }

    private float writeBlock(PDPageContentStream content, String title, String value,
                             PDType1Font titleFont, PDType1Font textFont, float x, float y) throws IOException {
        y = writeLine(content, title + " :", titleFont, 12, x, y);
        y = writeLine(content, safe(value), textFont, 11, x, y);
        return y - 10;
    }

    private float writeLine(PDPageContentStream content, String text, PDType1Font font,
                            int fontSize, float x, float y) throws IOException {
        content.beginText();
        content.setFont(font, fontSize);
        content.newLineAtOffset(x, y);
        content.showText(clean(text));
        content.endText();
        return y - 18;
    }

    private String safe(Object value) {
        return value == null ? "" : value.toString();
    }

    private String clean(String value) {
        return value == null ? "" : value.replace("\n", " ").replace("\r", " ");
    }
}