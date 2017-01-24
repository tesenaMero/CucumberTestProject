import com.google.common.base.Joiner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.lang.StringUtils.EMPTY;

class CsvWriter {

    private static final String ROW_END = "\n";
    private static final String FIELD_SEPARATOR_COMA = "|";

    private final Writer writer;

    public CsvWriter(OutputStream outputStream, String encoding) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(outputStream, encoding));
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    void writeRow(CsvRow csvRow) {
        writeRow(csvRow.getRow());
    }

    void writeRow(List columns) {
        StringBuilder out = new StringBuilder(EMPTY);
        Joiner.on(FIELD_SEPARATOR_COMA).appendTo(out, columns).append(ROW_END);
        try {
            writer.write(out.toString());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    void close() throws IOException {
        closeQuietly(writer);
    }
}