import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class MP {
    public static void main(String[] args) {
        String baseUrl = "https://hasanhuseyinvarol.com/hatim/sounds/";
        String fileExtension = ".mp3#t=0";
        int start = 1;  // Başlangıç numarası
        int end = 604;  // Bitiş numarası

        for (int i = start; i <= end; i++) {
            // Sayfa numarasını sıfırlarla doldur
            String formattedNumber = String.format("%03d", i); // 1 -> 001, 10 -> 010, 100 -> 100
            String fileUrl = baseUrl + formattedNumber + fileExtension; // Dinamik URL
            String fileName = "page_" + formattedNumber + ".mp3"; // Kaydedilecek dosya adı

            try {
                downloadFile(fileUrl, fileName);
                System.out.println("Dosya indirildi: " + fileName);
            } catch (IOException e) {
                System.err.println("Hata! Dosya indirilemedi: " + fileName + ". Hata: " + e.getMessage());
            }
        }
    }

    public static void downloadFile(String fileUrl, String fileName) throws IOException {
        // URL'nin #t=0 kısmını indirirken yok saymak için gerçek bağlantıyı alıyoruz
        String cleanedUrl = fileUrl.split("#")[0];
        try (BufferedInputStream in = new BufferedInputStream(new URL(cleanedUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        }
    }
}


