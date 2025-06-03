package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class CrashDetector {
    private static final String SESSION_FILE = "app_running.flag";
    private static final String LOG_FILE = "erro_shutdown_anormal.log";

    public static void checkPreviousCrash() {
        File file = new File(SESSION_FILE);
        if (file.exists()) {
            String msg = "Foi detectado um encerramento anormal do sistema na última execução!\n"
                       + "Um registro de erro foi gerado e pode ser encontrado em " + LOG_FILE + ".\n"
                       + "Por favor, verifique se houve perda de dados ou inconsistências.";
            JOptionPane.showMessageDialog(null, msg, "Aviso de Falha", JOptionPane.ERROR_MESSAGE);

            gerarLogErro();
        }

        try { file.createNewFile(); } catch (Exception e) {}
    }

    private static void gerarLogErro() {
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            fw.write("[" + now.format(formatter) + "] Encerramento anormal detectado.\n");
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "Erro ao gravar no arquivo de log: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void markCleanShutdown() {
        File file = new File(SESSION_FILE);
        if (file.exists()) file.delete();
    }
}