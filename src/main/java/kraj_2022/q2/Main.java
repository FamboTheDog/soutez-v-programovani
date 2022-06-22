package kraj_2022.q2;

import java.io.File;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.Objects;
import java.util.Stack;

public class Main {

    private static final Charset US_ASCII = StandardCharsets.US_ASCII;
    private static Stack<File> files = new Stack<>();

    public static void main(String[] args) {
        File file = new File(args[0]);
        traverseDirs(file);
        while (!files.isEmpty()) {
            File currentFile = files.pop();
            String currentFileName = currentFile.getName();
            String ascii = toAscii(currentFile.getName());
            System.out.println(currentFile.getPath() + " -> " + ascii);
        }
    }

    private static void traverseDirs(File start) {
        for (File it : Objects.requireNonNull(start.listFiles())) {
            files.add(it);
            if (it.isDirectory()) {
                traverseDirs(it);
            }
        }
    }

    private static String toAscii(final String input) {
        final CharsetEncoder charsetEncoder = US_ASCII.newEncoder();
        final char[] decomposed = Normalizer.normalize(input, Normalizer.Form.NFKD).toCharArray();
        final StringBuilder sb = new StringBuilder(decomposed.length);

        for (int i = 0; i < decomposed.length; ) {
            final int codePoint = Character.codePointAt(decomposed, i);
            final int charCount = Character.charCount(codePoint);

            if(charsetEncoder.canEncode(CharBuffer.wrap(decomposed, i, charCount))) {
                sb.append(decomposed, i, charCount);
            }

            i += charCount;
        }
        return sb.toString();
    }

}
