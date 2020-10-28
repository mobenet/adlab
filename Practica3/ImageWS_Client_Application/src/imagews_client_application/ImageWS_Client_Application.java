/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagews_client_application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.me.image.Image;

/**
 *
 * @author elchu
 */
public class ImageWS_Client_Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Menu de la practica 3");
        boolean exit = false;
        while (!exit) {
            System.out.println("1 - Registrar imagen");
            System.out.println("2 - Listar imagen");
            System.out.println("3 - Buscar imagen");
            System.out.println("4 - Modificar imagen");
            System.out.println("5 - Salir");
            Scanner sc = new Scanner(System.in);
            try  {            
                int in = sc.nextInt();
                sc.nextLine();
                switch (in) {

                    case 1: //registrar
                        Image tmp = new Image();
                        System.out.println("Titulo: ");
                        tmp.setTitle(sc.nextLine());
                        System.out.println("Autor: ");
                        tmp.setAuthor(sc.nextLine());
                        System.out.println("Drescripción: ");
                        tmp.setDescription(sc.nextLine());
                        System.out.println("Keywords (separadas por una coma y un espacio): ");
                        tmp.setKeywords(sc.nextLine());
                        System.out.println("Fecha de creación en formato yyyy/mm/dd: ");
                        tmp.setCreationDate(sc.nextLine());
                        int id = registrerImage(tmp);
                        System.out.println("Se ha añadido la imagen con id: "+id);//no se si enseñar id al user
                        break;

                    case 2://listar
                        ArrayList<Image> list = new ArrayList<>();
                        listImages().forEach(o -> {
                            if(o == null) System.out.println("nulo");
                            else list.add((Image) o);
                        });
                        list.forEach(i -> { 
                            System.out.println(ImageToString(i));
                        });
                        break;
                    case 3: //buscar
                        System.out.println("Introduce los por los que buscar con un punto al final:"
                                + " (titulo, fecha, autor, palabras clave)");

                        String aux;
                        Scanner p = new Scanner(System.in);
                        ArrayList<String> ar = new ArrayList<>();
                        Scanner sca = new Scanner(System.in);
                        while(true) {
                            String a = sca.nextLine();
                            if (a.equals(".")) break;
                            else ar.add(a);
                        }
                        //sca.close();
                        Iterator<String> it2 = ar.iterator();
                        it2 = ar.iterator();          
                        List<List<Object>> colores;
                        colores = new ArrayList<>();
                        while(it2.hasNext()) {
                            switch (it2.next()) {
                                case "titulo":
                                    System.out.println("Introduce el titulo de la imagen: ");
                                    aux = p.nextLine();
                                    colores.add(searchbyTitle(aux));
                                    break;

                                case "fecha":
                                    System.out.println("Introduce la fecha (yyy/mm/dd) de la imagen: ");
                                    aux = p.nextLine();
                                    colores.add(searchbyCreaDate(aux));
                                    break;

                                case "autor":
                                    System.out.println("Introduce el autor de la imagen: ");
                                    aux = p.nextLine();
                                    colores.add(searchbyAuthor(aux));
                                    break;

                                case "palabras clave":
                                    System.out.println("Introduce las palabras clave de la imagen: ");
                                    aux = p.nextLine();
                                    colores.add(searchbyKeywords(aux));
                                    break;

                                default: 
                                    System.out.println("Entrada incorrecta, vuelve a probar");
                                    System.out.println("");
                                    break; 
                            }
                        }
                        //p.close();
                        
                        HashMap<Integer, Image> map = new HashMap<>();
                        Iterator<List<Object>> iter = colores.iterator();

                        while(iter.hasNext()){//lista de listas
                            Iterator<Object> siter = iter.next().iterator();
                            while(siter.hasNext()){//lista de objectos
                            Object img = siter.next();
                            Image image = Image.class.cast(img);
                            map.put(image.getId(), (Image) img);
                            }
                        }
                        map.forEach((Integer k, Image v) -> {
                            
                            System.out.println("Título: " + v.getTitle());
                            System.out.println("Fecha creación: " + v.getCreationDate());
                            System.out.println("Autor: " + v.getAuthor());
                            System.out.println("Palabras clave: " + v.getKeywords());
                            System.out.println("Descripción: " + v.getDescription());
                            System.out.println("Fecha de grabado: " + v.getStorageDate());
                            System.out.println("Nombre del archivo: " + v.getFileName());
                            System.out.println("");
                        });
                        break;

                    case 4: 
                        //modificar
                        System.out.println("Te recomendamos que uses la aplicación web para modificar una imagen");
                        break;
                    case 5: //salir
                        exit = true;
                        break;
                    default: 
                        System.out.println("Entrada incorrecta, vuelve a probar");
                        System.out.println("");
                        break;
                }
            } 
            
            catch (InputMismatchException ime){
                    System.out.println("¡Cuidado! Solo puedes insertar números del 1 al 5. ");
            }
            //sc.close();
            
        }

    }
    

    private static String ImageToString(Image img) {
        return "Image{" + 
                "id=" + img.getId() 
                + ", title=" + img.getTitle() 
                + ", author=" + img.getAuthor()
                + ", description=" + img.getDescription() 
                + ", keywords=" + img.getKeywords() 
                + ", creationDate=" + img.getCreationDate()
                + ", storageDate=" + img.getStorageDate() 
                + ", fileName=" + img.getFileName() + '}';
    }

    private static java.util.List<java.lang.Object> listImages() {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.listImages();
    }

    private static int deleteImage(org.me.image.Image image) {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.deleteImage(image);
    }

    private static java.util.List<java.lang.Object> searchbyTitle(java.lang.String title) {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyTitle(title);
    }

    private static java.util.List<java.lang.Object> searchbyCreaDate(java.lang.String creaDate) {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyCreaDate(creaDate);
    }

    private static java.util.List<java.lang.Object> searchbyAuthor(java.lang.String author) {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyAuthor(author);
    }

    private static int registrerImage(org.me.image.Image image) {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.registrerImage(image);
    }

    private static java.util.List<java.lang.Object> searchbyKeywords(java.lang.String keywords) {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyKeywords(keywords);
    }
}
