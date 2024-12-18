import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public final static void limpaecra() {
       System.out.print("\033[H\033[2J");
       System.out.flush();
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        ArrayList<String> filmes = new ArrayList<>();
        String titulo, diretor, comentario, resp;
        int data, avaliacao;
        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);

        try {
            File file = new File("avaliações.txt");
            if (file.exists()) {
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {
                    filmes.add(fileReader.nextLine());
                }
                fileReader.close();
            }
        } catch (IOException e) {
            System.out.println("Não deu pra carregar as avaliações");
        }

        do {
            limpaecra();
            System.out.println("**********Rotten Tomatoes**********");
            System.out.println(" 1. Adicionar avaliação");
            System.out.println(" 2. Alterar avaliação");
            System.out.println(" 3. Remover avaliação");
            System.out.println(" 4. Ver as avaliações");
            System.out.println(" 5. Sair");
            System.out.println("***********************************");
            resp = myObj.nextLine();

            if (resp.equals("1")) {
                System.out.println("Digite o título do filme: ");
                titulo = myObj.nextLine();
                System.out.println("Digite a data de lançamento: ");
                data = myObj.nextInt();
                myObj.nextLine();
                System.out.println("Digite o nome do diretor: ");
                diretor = myObj.nextLine();

                do {
                    System.out.println("Digite a avaliação (0/10): ");
                    avaliacao = myObj.nextInt();
                    if (avaliacao < 0 || avaliacao > 10) {
                        System.out.println("A avaliação deve estar entre 0 e 10 ");
                    }
                } while (avaliacao < 0 || avaliacao > 10);
                myObj.nextLine(); 

                System.out.println("Digite uma crítica: ");
                comentario = myObj.nextLine();

                filmes.add("Título: " + titulo + "; Data: " + data + "; Diretor: " + diretor + "; Avaliação: " + avaliacao + "/10; Comentário: " + comentario);
                Collections.sort(filmes);
                salvarAvaliacoes(filmes);
            }

            if (resp.equals("2")) {
                if(filmes.isEmpty()){
                    System.out.println("Não tem nada aqui");
                    System.out.println("............................... ");
                    System.out.println("Pressione ENTER para continuar");
                    myObj.nextLine();
                } else {
                    System.out.println("Digite o numero da crítica que deseja alterar: ");
                    int pos = myObj2.nextInt();
                    myObj2.nextLine();
                    System.out.println("Digite o novo título do filme: ");
                    titulo = myObj.nextLine();
                    System.out.println("Digite a nova data de lançamento: ");
                    data = myObj.nextInt();
                    myObj.nextLine();
                    System.out.println("Digite o novo nome do diretor: ");
                    diretor = myObj.nextLine();

                    do {
                        System.out.println("Digite a nova avaliação (0/10): ");
                        avaliacao = myObj.nextInt();
                        if (avaliacao < 0 || avaliacao > 10) {
                            System.out.println("A avaliação deve estar entre 0 e 10 ");
                        }
                    } while (avaliacao < 0 || avaliacao > 10);

                    myObj.nextLine();
                    System.out.println("Digite uma nova crítica: ");
                    comentario = myObj.nextLine();

                    filmes.set(pos - 1, "Título: " + titulo + "; Data: " + data + "; Diretor: " + diretor + "; Avaliação: " + avaliacao + "/10; Comentário: " + comentario);
                    Collections.sort(filmes);
                    salvarAvaliacoes(filmes);
                }
            }

            if (resp.equals("3")) {
                if (filmes.isEmpty()) {
                    System.out.println("Não tem nada aqui ");
                    System.out.println("............................... ");
                    System.out.println("Pressione ENTER para continuar");
                    myObj.nextLine();
                } else {
                    System.out.println("Diga o número da crítica para remover ");
                    int pos = myObj2.nextInt();
                    filmes.remove(pos - 1);
                    salvarAvaliacoes(filmes);
                }
            }

            if (resp.equals("4")) {
                if (filmes.isEmpty()) {
                    System.out.println("Não tem nada aqui ");
                } else {
                    System.out.println("**********AVALIAÇÕES**********\n");
                    for (int i = 0; i < filmes.size(); i++) {
                        System.out.println((i + 1) + ". " + filmes.get(i));
                    }
                    System.out.println("\n**********AVALIAÇÕES**********");
                }
                System.out.println("............................... ");
                System.out.println("Pressione ENTER para continuar ");
                myObj.nextLine();
            }

            if (resp.equals("5")) {
                System.out.println("Sucesso! :) ");
            }
        } while (!resp.equals("5"));
    }
    
    private static void salvarAvaliacoes(ArrayList<String> filmes) {
        try (FileWriter writer = new FileWriter("avaliações.txt")) {
            for (String filme : filmes) {
                writer.write(filme + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro");
        }
    }
}