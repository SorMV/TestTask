package ru.kazan.express.testtask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kazan.express.testtask.zip.Zipper;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Autowired
    private Zipper zipper;

   @Override
   public void run(ApplicationArguments args) throws Exception {
       zipper.packFileToZip();
   }
}