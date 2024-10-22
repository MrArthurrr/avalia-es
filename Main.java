import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        int nota;
        String filme;
        String comentario;

        System.out.println("Diga o nome do filme: ");
        filme = myObj.nextLine();

        do {
            System.out.println("Diga sua nota para o filme (1 a 10): ");
            nota = myObj.nextInt();
            if (nota < 1 || nota > 10) {
                System.out.println("A nota está incorreta");
            }
        } while (nota < 1 || nota > 10);
        myObj.nextLine();

        System.out.println("Faça um comentário sobre o filme");
        comentario = myObj.nextLine();

        System.out.println("***********************");
        System.out.println("Filme: " + filme);
        System.out.println("Nota: " + nota);
        System.out.println("Comentário: " + comentario);
        System.out.println("***********************");
    }
}