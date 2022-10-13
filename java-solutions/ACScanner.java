import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ACScanner {
    private final Reader reader;
    private final int bufferSize = 8192;
    private char[] cbuf = new char[bufferSize];
    private int cbufSize;
    private int lastIndex = 0;
    private boolean lastCharIsR = false;
    private StringBuilder newDigit = new StringBuilder(0);
    private StringBuilder newWord = new StringBuilder(0);

    private StringBuilder newNext = new StringBuilder(0);

    private int lineIterator = 0;
    public int hasFoundNewLines = 0;

    private ACScanner(Reader input) throws IOException {
        this.reader = input;
        cbufSize = reader.read(cbuf);
    }

    public ACScanner(InputStream in) throws IOException {
        this(new InputStreamReader(in));
    }

    public ACScanner(String in) throws IOException {
        this(new StringReader(in));
    }

    public ACScanner(File file, Charset charset) throws IOException {
        this(new InputStreamReader(new FileInputStream(file), charset));
    }

    public String scanNewLine() throws IOException {
        StringBuilder newLine = new StringBuilder(0);
        while (true) {
            while (lastIndex < cbufSize) {
                if (cbuf[lastIndex] == '\r') { //r
                    lastIndex++;
                    lastCharIsR = true;
                    return newLine.toString();
                } else if (cbuf[lastIndex] == '\n') { //n
                    if (!lastCharIsR) {
                        lastIndex++;
                        return newLine.toString();
                    }
                } else {
                    newLine.append(cbuf[lastIndex]);
                }
                lastIndex++;
            }
            if (cbufSize == bufferSize) {
                cbufSize = reader.read(cbuf);
                lastIndex = 0;
            } else if(!newLine.isEmpty()){
                return newLine.toString();
            }
        }
    }

    public String nextDigit() throws IOException {
        return newDigit.toString();
    }

    private boolean antiCopyPaste(String parametor) {
        if (parametor.equals("digit")) {
            return Character.isDigit(cbuf[lineIterator]) || cbuf[lineIterator] == '-';
        } else if (parametor.equals("word")) {
            return Character.isLetter(cbuf[lineIterator]) || Character.getType(cbuf[lineIterator]) == Character.DASH_PUNCTUATION || cbuf[lineIterator] == 39;
        } else if (parametor.equals("next")) {
            return !Character.isWhitespace(cbuf[lineIterator]);
        } else {
            return false;
        }
    }

    public boolean hasNextDigit() throws IOException {
        newDigit.setLength(0);
        hasFoundNewLines = 0;
        while (true) {
            while (lineIterator < cbufSize) {
                if (antiCopyPaste("digit")) {
                    while (lineIterator < cbufSize && antiCopyPaste("digit")) {
                        newDigit.append(cbuf[lineIterator]);
                        lineIterator++;
                    }
                } else if (!newDigit.isEmpty()) {
                    return true;
                } else {
                    if (privateFoundNewLine(cbuf[lineIterator])) {
                        hasFoundNewLines++;
                    }
                    lineIterator++;
                }
            }
            if (cbufSize == bufferSize) {
                lineIterator = 0;
                cbufSize = reader.read(cbuf);
            } else {
                break;
            }
        }
        if (!newDigit.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    public String nextWord() throws IOException {
        return newWord.toString();
    }

    public boolean hasNextWord() throws IOException {
        newWord.setLength(0);
        hasFoundNewLines = 0;
        while (true) {
            while (lineIterator < cbufSize) {
                if (antiCopyPaste("word")) {
                    while (lineIterator < cbufSize && antiCopyPaste("word")) {
                        newWord.append(cbuf[lineIterator]);
                        lineIterator++;
                    }
                } else if (!newWord.isEmpty()) {
                    return true;
                } else {
                    if (privateFoundNewLine(cbuf[lineIterator])) {
                        hasFoundNewLines++;
                    }
                    lineIterator++;
                }
            }
            if (cbufSize == bufferSize) {
                cbufSize = reader.read(cbuf);
                lineIterator = 0;
            } else if (lineIterator >= cbufSize) {
                break;
            }
        }
        if (!newWord.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public String next() throws IOException {
        return newNext.toString();
    }
    public boolean hasNext() throws IOException {
        newNext.setLength(0);
        hasFoundNewLines = 0;
        while (true) {
            while (lineIterator < cbufSize) {
                if (antiCopyPaste("next")) {
                    while (lineIterator < cbufSize && antiCopyPaste("next")) {
                        newNext.append(cbuf[lineIterator]);
                        lineIterator++;
                    }
                } else if (!newNext.isEmpty()) {
                    return true;
                } else {
                    if (privateFoundNewLine(cbuf[lineIterator])) {
                        hasFoundNewLines++;
                    }
                    lineIterator++;
                }
            }
            if (cbufSize == bufferSize) {
                cbufSize = reader.read(cbuf);
                lineIterator = 0;
            } else {
                break;
            }
        }
        if (!newNext.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    private boolean privateFoundNewLine(char symbol) {
        if (symbol == '\r') {
            lastCharIsR = true;
            return true;
        } else if (symbol == '\n') {
            if (!lastCharIsR) {
                return true;
            } else {
                lastCharIsR = false;
                return false;
            }
        } else {
            return false;
        }
    }
    public void close() throws IOException {
        reader.close();
    }
}