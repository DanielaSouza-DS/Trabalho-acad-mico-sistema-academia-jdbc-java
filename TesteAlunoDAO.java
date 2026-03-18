package dados;

import dados.Aluno;
import dados.AlunoDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TesteAlunoDAO {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        AlunoDAO dao = new AlunoDAO();

        int opcao;

        do {
            System.out.println("\n==== MENU ALUNO ====");
            System.out.println("1 - Inserir aluno");
            System.out.println("2 - Listar alunos");
            System.out.println("3 - Alterar aluno");
            System.out.println("4 - Deletar aluno");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar o buffer

            switch (opcao) {
                case 1:
                    Aluno novo = new Aluno();
                    System.out.print("Nome: ");
                    novo.setNome(sc.nextLine());

                    System.out.print("CPF: ");
                    novo.setCpf(sc.nextLine());

                    System.out.print("Data Nascimento (YYYY-MM-DD): ");
                    novo.setDataNascimento(sc.nextLine());

                    System.out.print("Email: ");
                    novo.setEmail(sc.nextLine());

                    System.out.print("Peso: ");
                    novo.setPeso(sc.nextDouble());

                    System.out.print("Altura: ");
                    novo.setAltura(sc.nextDouble());
                    sc.nextLine(); // limpar o buffer

                    dao.salvar(novo);
                    break;

                case 2:
                    List<Aluno> lista = dao.listar();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum aluno cadastrado.");
                    } else {
                        for (Aluno a : lista) {
                            System.out.println("\nID: " + a.getId());
                            System.out.println("Nome: " + a.getNome());
                            System.out.println("CPF: " + a.getCpf());
                            System.out.println("Nascimento: " + a.getDataNascimento());
                            System.out.println("Email: " + a.getEmail());
                            System.out.println("Peso: " + a.getPeso());
                            System.out.println("Altura: " + a.getAltura());
                        }
                    }
                    break;

                case 3:
                    System.out.print("ID do aluno que deseja alterar: ");
                    int idAlterar = sc.nextInt();
                    sc.nextLine(); // limpar o buffer

                    Aluno alterar = new Aluno();
                    System.out.print("Novo nome: ");
                    alterar.setNome(sc.nextLine());

                    System.out.print("Novo CPF: ");
                    alterar.setCpf(sc.nextLine());

                    System.out.print("Nova data nascimento (YYYY-MM-DD): ");
                    alterar.setDataNascimento(sc.nextLine());

                    System.out.print("Novo email: ");
                    alterar.setEmail(sc.nextLine());

                    System.out.print("Novo peso: ");
                    alterar.setPeso(sc.nextDouble());

                    System.out.print("Nova altura: ");
                    alterar.setAltura(sc.nextDouble());
                    sc.nextLine(); // limpar buffer

                    dao.alterar(idAlterar, alterar);
                    break;

                case 4:
                    System.out.print("ID do aluno que deseja deletar: ");
                    int idDeletar = sc.nextInt();
                    sc.nextLine(); // limpar o buffer
                    dao.deletar(idDeletar);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        sc.close();
    }
}
