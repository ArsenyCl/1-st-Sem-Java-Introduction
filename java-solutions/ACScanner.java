import java.io.*;
import java.nio.charset.Charset;

public class ACScanner {
    private final Reader reader;

    private final int bufferSize = 4096;

    private char[] cbuf = new char[bufferSize];
    private StringBuilder newLine = new StringBuilder(0);
    private StringBuilder newWord = new StringBuilder(0);
    private StringBuilder newDigit = new StringBuilder(0);
    private StringBuilder newNext = new StringBuilder(0);

    private int cbufSize = 4096;

    private boolean isHasNextLine = true;

    private int lastIndex = 0;

    private boolean lastCharIsR = false;

    private int lineIterator = 0;


    public ACScanner(InputStream in) {
        reader = new InputStreamReader(in);
    }
    public ACScanner(Reader in) {
        reader = in;
    }
    public ACScanner(String in)  {
        reader = new StringReader(in);

    }
    public ACScanner(String path, Charset charset) throws IOException  {
            reader = new InputStreamReader(new FileInputStream(path), charset);
    }
    public String scanNewLine() throws IOException {
        newLine.setLength(0);
        while (true) {
            while (lastIndex < cbufSize && cbuf[0] != 0) {
                if (cbuf[lastIndex] == '\r') { //r
                    lastIndex++;
                    lastCharIsR =true;
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
            } else if (lastIndex >= cbufSize) {
                isHasNextLine = false;
                break;
            }
        }
        if (newLine.length() > 0) {
            return newLine.toString();
        } else {
            return null;
        }
    }
    public boolean hasNextLine() {
        return isHasNextLine;
    }

    public String nextDigit() throws IOException {
        newDigit.setLength(0);
        while(true) {
            while (lineIterator < cbufSize && cbuf[0] != 0) {
                if (Character.isDigit(cbuf[lineIterator]) || (cbuf[lineIterator] == '-' && newDigit.isEmpty())) {
                    newDigit.append(cbuf[lineIterator]);
                    lineIterator++;
                    while (lineIterator < cbufSize && Character.isDigit(cbuf[lineIterator])) {
                        newDigit.append(cbuf[lineIterator]);
                        lineIterator++;
                    }
                } else if (!newDigit.isEmpty()) {
                    return newDigit.toString();
                } else {
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
        if (!newDigit.isEmpty()) {
            return newDigit.toString();
        } else {
            return null;
        }
    }
    public String nextWord() throws IOException {
        newWord.setLength(0);
        while(true) {
            while (lineIterator < cbufSize && cbuf[0] != 0) {
                if ((Character.isLetter(cbuf[lineIterator]) || Character.getType(cbuf[lineIterator]) == Character.DASH_PUNCTUATION ||  cbuf[lineIterator] == 39)) {
                    newWord.append(cbuf[lineIterator]);
                    lineIterator++;
                    while (lineIterator < cbufSize && (Character.isLetter(cbuf[lineIterator]) || Character.getType(cbuf[lineIterator]) == Character.DASH_PUNCTUATION ||  cbuf[lineIterator] == 39)) {
                        newWord.append(cbuf[lineIterator]);
                        lineIterator++;
                    }
                } else if (!newWord.isEmpty()) {
                    return newWord.toString();
                } else {
                    lineIterator++;
                }
            }
            if (cbufSize == bufferSize) {
                cbufSize = reader.read(cbuf);
                lineIterator = 0;
            } else if (lineIterator >= cbufSize) {
                break;
            }
        }  if (!newWord.isEmpty()) {
            return newWord.toString();
        } else {
            return null;
        }
    }
    public String next() throws IOException {
        newNext.setLength(0);
        while(true) {
            while (lineIterator < cbufSize && cbuf[0] != 0) {
                if (!Character.isWhitespace(cbuf[lineIterator]) || (cbuf[lineIterator] == '-' && newNext.isEmpty())) {
                    newNext.append(cbuf[lineIterator]);
                    lineIterator++;
                    while (lineIterator < cbufSize && !Character.isWhitespace(cbuf[lineIterator])) {
                        newNext.append(cbuf[lineIterator]);
                        lineIterator++;
                    }
                } else if (!newNext.isEmpty()) {
                    return newNext.toString();
                } else {
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
        if (!newNext.isEmpty()) {
            return newNext.toString();
        } else {
            return null;
        }
    }
    public void close() throws IOException {
        reader.close();
    }
}

