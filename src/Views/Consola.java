package Views;

public class Consola {

    public static void gotoxy(int x, int y){
        System.out.print("\033[" + y + ";" + x + "H");
    }

    public static void cls(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Error al limpiar la consola");
        }
    }

    public static void dibujar_rectangulo(int ancho, int alto, int posX, int posY){
        gotoxy(posX, posY);
        System.out.print("┌");
        for (int i = 0; i < ancho; i++) {
            System.out.print("─");
        }
        System.out.print("┐");

        for (int i = 0; i < alto; i++) {
            gotoxy(posX, posY+i+1);
            System.out.print("│");
            for (int j = 0; j<ancho; j++){
                System.out.print(" ");
            }
            System.out.print("│");
        }

        gotoxy(posX, posY+alto+1);
        System.out.print("└");
        for (int i = 0; i < ancho; i++) {
            System.out.print("─");
        }
        System.out.print("┘");
    }

    public static void change_color(int text_color, int bg_color){
        System.out.print("\033[38;5;" + text_color + "m");
        System.out.print("\033[48;5;" + bg_color + "m");
    }

    public static void reset_color(){
        System.out.print("\033[0m");
    }

    public static void dibujar_boton(int ancho, int alto, int posX, int posY, String texto){
        dibujar_rectangulo(ancho, alto, posX, posY);
        int texto_len = texto.length();
        int ancho_sobra = ancho - texto_len;
        int sobra_inicial = ancho_sobra/2;

        int alto_sobra = alto - 1;
        int sobra_inicial_alto = alto_sobra/2;

        gotoxy(posX + sobra_inicial + 1, posY + sobra_inicial_alto + 1);
        System.out.print(texto);
    }

}
