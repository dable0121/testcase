package function.strategy;

import com.sun.nio.zipfs.ZipPath;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

public class Compressor {
    private final CompressionStrategy strategy;
    public Compressor(CompressionStrategy strategy) {
        this.strategy = strategy;
    }
    public void compress(Path inFile, File outFile) throws IOException {
        try (OutputStream outStream = new FileOutputStream(outFile)) {
            Files.copy(inFile, strategy.compress(outStream));
        }
    }

    public static void main(String[] args) {
        Compressor gzipCompressor = new Compressor(GZIPOutputStream::new);
//        gzipCompressor.compress(inFile, outFile);
        Compressor zipCompressor = new Compressor(ZipOutputStream::new);
//        zipCompressor.compress(inFile, outFile);
    }
}