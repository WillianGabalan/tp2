package opencv;

import com.googlecode.javacv.cpp.opencv_core.CvMat;
import java.util.Random;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSize;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCloneImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGet2D;
import static com.googlecode.javacv.cpp.opencv_core.cvGetMat;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvSet2D;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExtratorCaracteristicas2 {

    public static void main(String[] args) throws IOException {
    	//proximaImagem
        int  classePersonagem;
        String classePersonagemString = null;
        int red, green, blue;

        // Cabeçalho do arquivo Weka
	String exportacao = "@relation caracteristicas\n\n";
	exportacao += "@attribute cor_medio_red double\n";
	exportacao += "@attribute cor_medio_green double\n";
	exportacao += "@attribute cor_medio_blue double\n";
	exportacao += "@attribute classe {Abacaxi, Melancia, Banana}\n\n";
	exportacao += "@data\n";
        
        // Diretório onde estão armazenadas as imagens
        File diretorio = new File("src\\imagens");
        File[] arquivos = diretorio.listFiles();

        // Características do 
        //double cor_medio_red, cor_medio_green, cor_medio_blue,cont;
        int cor_medio_red, cor_medio_green, cor_medio_blue,cont;
        

        
        // Definição do vetor de características
        int[][] caracteristicas = new int[73][4];

        // Percorre todas as imagens do diretório
        for (int i = 0; i < arquivos.length; i++) {
        	cor_medio_red = 0;
        	cor_medio_green = 0;
        	cor_medio_blue = 0;
             // Carrega cada imagem do diretório
            IplImage imagemOriginal = cvLoadImage(diretorio.getAbsolutePath() + "\\" + arquivos[i].getName());
            CvSize tamanhoImagemOriginal = cvGetSize(imagemOriginal);
            
            // Imagem processada - tamanho, profundidade de cores e número de canais de cores
            IplImage imagemProcessada = cvCreateImage(tamanhoImagemOriginal, IPL_DEPTH_8U, 3);
            imagemProcessada = cvCloneImage(imagemOriginal);

            // Definição da classe - Abacaxi ou melancia
            if (arquivos[i].getName().charAt(0) == 'a') {
                classePersonagem = 0;
                classePersonagemString = "Abacaxi";
            } else if(arquivos[i].getName().charAt(0) == 'b') {
                classePersonagem = 1;
                classePersonagemString = "Banana";
            }
            else if(arquivos[i].getName().charAt(0) == 's') {
                classePersonagem = 2;
                classePersonagemString = "Melancia";
            }

            // Matriz multi-canal
            CvMat mtx = CvMat.createHeader(imagemProcessada.height(), imagemProcessada.width());
            CvScalar scalarImagemProcessada = new CvScalar();
            cvGetMat(imagemProcessada, mtx, null, 0);
            
            cont=0;Random random = new Random(); 
            // Varre a imagem pixel a pixel
            for (int altura = 0; altura < imagemProcessada.height(); altura=altura + 1 + random.nextInt(2)) {
            	    for (int largura = 0; largura < imagemProcessada.width(); largura=largura + 1 + random.nextInt(2)) {
                    
                    // Extração do RGB de cada pixel da imagem
                    CvScalar scalarExtraiRgb = cvGet2D(imagemProcessada, altura, largura);
                    blue = (int)(scalarExtraiRgb.val(0));
                    green = (int)(scalarExtraiRgb.val(1));
                    red = (int)(scalarExtraiRgb.val(2));

                    // cores m�dios                  
                    if (blue >= 25 && blue <= 225 && 
                            green >= 25 && green <= 225 && 
                            red >= 25 && red <= 225) {                       
                    	cor_medio_red = cor_medio_red + red;
                    	cor_medio_green =cor_medio_green + green;
                    	cor_medio_blue = cor_medio_blue + blue;
                    	cont++;
                    }

                }
            }
        	cor_medio_red = cor_medio_red/cont;
        	cor_medio_green =cor_medio_green/cont;
        	cor_medio_blue = cor_medio_blue/cont;
            cont=0;
            // Imagem processada de acordo com as características (alteração das cores)
            imagemProcessada = new IplImage(mtx);

            // Normaliza as características pelo número de pixels totais da imagem
            
            
            // Grava as características no vetor de características
            caracteristicas[i][0] = cor_medio_red;
            caracteristicas[i][1] = cor_medio_green;
            caracteristicas[i][2] = cor_medio_blue;
      

            System.out.println( classePersonagemString +" - cor_medio_red: " + caracteristicas[i][0] + " - cor_medio_green: " + caracteristicas[i][1] + " - cor_medio_blue: " + caracteristicas[i][2]);
            exportacao += caracteristicas[i][0] + "," + caracteristicas[i][1] + "," + caracteristicas[i][2] + "," + classePersonagemString + "\n";

            //cvShowImage("Imagem original", imagemOriginal);
            //cvShowImage("Imagem processada", imagemProcessada);
            //proximaImagem = cvWaitKey();
        }

        // Grava o arquivo ARFF no disco
        File arquivo = new File("caracteristicas.arff");
        FileOutputStream f = new FileOutputStream(arquivo);
        f.write(exportacao.getBytes());
        f.close();
    }
}