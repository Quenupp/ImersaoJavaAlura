import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    

    public void cria(InputStream inputStream, String nomeArquivo, String rating) throws Exception {

        // leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        imagemOriginal= resizeImage(imagemOriginal, 600,1000);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        var fonte = new Font("Impact", Font.ITALIC, largura/10);
        graphics.setColor(Color.GREEN.darker());
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        graphics.drawString("Nota: " + rating ,200, 1125);

        // escrever a nova imagem em um arquivo
        File local = new File("imagens/" + nomeArquivo);
        ImageIO.write(novaImagem, "png", local);

    }

    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }   

}

