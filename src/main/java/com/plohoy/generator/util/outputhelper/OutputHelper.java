package com.plohoy.generator.util.outputhelper;

import com.plohoy.generator.model.file.AbstractSourceFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class OutputHelper {

    public ResponseEntity<String> writeFiles(List<AbstractSourceFile> abstractSourceFiles) {
        for (AbstractSourceFile abstractSourceFile : abstractSourceFiles) {
            File directories = new File(abstractSourceFile.getPath());
            directories.mkdirs();

            File file = new File(abstractSourceFile.getPath() + abstractSourceFile.getFileName());

            try (OutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(abstractSourceFile
                        .getData()
                        .toString()
                        .getBytes()
                );

            } catch (IOException e) {
                return new ResponseEntity<>(
                        "IOException was thrown while file " + abstractSourceFile.getPath() + abstractSourceFile.getFileName() + " was written",
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        }
        return new ResponseEntity<>(
                "Files were written successfully!",
                HttpStatus.OK
        );
    }
}
