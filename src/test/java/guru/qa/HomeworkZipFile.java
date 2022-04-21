package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HomeworkZipFile {

        @Test
        void pdfParseZipTest() throws Exception {
            ZipFile zipFile = new ZipFile("src/test/resources/files/test_files.zip");
            ZipEntry zipPdfEntry = zipFile.getEntry("invoice-19.04.2022.pdf");
            InputStream inputStream = zipFile.getInputStream(zipPdfEntry);
            PDF pdf = new PDF(inputStream);
            assertThat(pdf.text).contains("Счет № МШ-0000269 от 19.04.2022");
        }

        @Test
        void csvParseZipTest() throws Exception {
            ZipFile zipFile = new ZipFile("src/test/resources/files/test_files.zip");
            ZipEntry zipCsvEntry = zipFile.getEntry("file.csv");
            try (InputStream inputStream = zipFile.getInputStream(zipCsvEntry);
                 CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
                List<String[]> content = reader.readAll();
                assertThat(content.get(0)).contains(
                        "Series_reference",
                        "Period",
                        "Data_value",
                        "Suppressed",
                        "STATUS",
                        "UNITS",
                        "Magnitude",
                        "Subject",
                        "Group",
                        "Series_title_1",
                        "Series_title_2",
                        "Series_title_3",
                        "Series_title_4",
                        "Series_title_5");
            }
        }

        @Test
        void xslxParseZipTest() throws Exception {
            ZipFile zipFile = new ZipFile("src/test/resources/files/test_files.zip");
            ZipEntry zipXslxEntry = zipFile.getEntry("prajs_ot_1204.xls");
            InputStream inputStream = zipFile.getInputStream(zipXslxEntry);
            XLS xls = new XLS(inputStream);
            assertThat(xls.excel
                    .getSheetAt(0)
                    .getRow(10)
                    .getCell(1)
                    .getStringCellValue()).contains("Ромашка");
        }
    }
