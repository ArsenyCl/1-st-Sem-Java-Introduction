package md2html;


import java.io.*;
import java.nio.charset.Charset;

public class SpecialScanner {
    private final Reader reader;
    private final int bufferSize = 65536;
    private char[] cbuf = new char[bufferSize];
    private int cbufSize;
    private boolean lastCharIsR = false;
    private boolean lastCharIsN = false;
    public  boolean skipLines = false;
    private StringBuilder newNext = new StringBuilder(0);
    private int lineIterator = 0;

    private SpecialScanner(Reader input) throws IOException {
        this.reader = input;
        cbufSize = reader.read(cbuf);
    }

    public SpecialScanner(InputStream in) throws IOException {
        this(new InputStreamReader(in));
    }
    public SpecialScanner(File file, Charset charset) throws IOException {
        this(new InputStreamReader(new FileInputStream(file), charset));
    }
    private boolean antiCopyPaste(String parametor) {
        if (parametor.equals("Sp")) {
            return (cbuf[lineIterator] == '*' || cbuf[lineIterator] == '-' || cbuf[lineIterator] == '_'
                    || cbuf[lineIterator] == '`' || cbuf[lineIterator] == '#' || cbuf[lineIterator] == '<'
                    || cbuf[lineIterator] == '>' || cbuf[lineIterator] == '&' || cbuf[lineIterator] == '\\'|| cbuf[lineIterator] == '+');
        } else if(parametor.equals("newLine")) {
            return cbuf[lineIterator] == '\r' || cbuf[lineIterator] == '\n';
        } else {
            return false;
        }
    }
    public String next() throws IOException {
        return newNext.toString();
    }
    public boolean hasNext() throws IOException {
        newNext.setLength(0);
        boolean special = false;
        boolean symb = false;
        boolean newLineCount = false;
        boolean firstSym = true;
        char symChar = '0';
        skipLines= false;
        lastCharIsN = false;
        lastCharIsR = false;
        while (true) {
            while (lineIterator < cbufSize) {
                if (!newLineCount && !special && !antiCopyPaste("Sp") && !antiCopyPaste("newLine")) {
                    symb = true;
                    while (lineIterator < cbufSize && !antiCopyPaste("Sp") && !skipLines && !antiCopyPaste("newLine")) {
                        newNext.append(cbuf[lineIterator]);
                        lineIterator++;
                    }
                } else if (!newLineCount && !symb && (symChar == cbuf[lineIterator] || firstSym) && !antiCopyPaste("newLine")
                        && antiCopyPaste("Sp") ) {
                    special = true;
                    while (firstSym ||(lineIterator < cbufSize && (symChar == cbuf[lineIterator] || symChar == '\\' || (symChar == '#' && cbuf[lineIterator] == ' '))
                           && !antiCopyPaste("newLine"))) {
                        firstSym = false;
                        symChar = cbuf[lineIterator];
                        newNext.append(cbuf[lineIterator]);
                        lineIterator++;
                    }
                } else if (!symb && !special &&  antiCopyPaste("newLine")) {
                    newLineCount = true;
                    newNext = new StringBuilder(String.valueOf('\n'));
                    while (lineIterator < cbufSize && antiCopyPaste("newLine")) {
                        skipLines = (skipLines || foundDoubleLine(cbuf[lineIterator]));
                        lineIterator++;
                    }
                } else if (!newNext.isEmpty()) {
                    return true;
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
    private boolean foundDoubleLine(char symbol) {
        if (lastCharIsN && (symbol == '\n' ||symbol == '\r')) {
            lastCharIsN = false;
            lastCharIsR = false;
            return true;
        } else if(lastCharIsR && symbol == '\r') {
            lastCharIsR = false;
            return true;
        } else if (symbol == '\n') {
            lastCharIsN = true;
            return false;
        } else if (symbol == '\r') {
            lastCharIsR = true;
            return false;
        }
        lastCharIsN = false;
        lastCharIsR = false;
        return false;
    }
    public void close() throws IOException {
        reader.close();
    }
}