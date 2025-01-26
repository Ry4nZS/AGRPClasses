package programa;

import java.util.InputMismatchException;
import java.util.Scanner;

public class P2 {

	public static void main(String[] args) {
		System.out.println("---------------------------------------------------------------------");
		int main_option = 0;
		Scanner teclado = new Scanner(System.in);

		do {
			double xifi = 0, xl = 0, N = 0, variancia = 0, desviopadrao = 0, xix = 0, CV = 0;
			int t = 0, i = 0;
			boolean seg = false;

			double mBase[][] = null;

			System.out.println("Ola, este programa realizara os calculos de agrupamento em classes.\n"
					+ "A Seguir leia atentamente as instrucoes e preencha os campos corretamente");

			do {
				try {
					System.out.println("-> Digite o numero de classes (tem que ser um numero inteiro!) da amostra para realizar os calculos: ");
					t = teclado.nextInt();
					if (t > 0) {
						mBase = new double[t][5];
						seg = true;
					} else {
						t = 0;
						System.out.println("Digite um valor que não seja zero nem negativo");
					}
				} catch (InputMismatchException erro) {
					System.out.println("Ocorreu um erro, você deve ter digitado alguma letra ou numero real");
					teclado.nextLine();
				}
			} while (seg == false);

			System.out.println("Digite os dados do programa! (Se for um numero quebrado utilize o padrao brasileiro para inserir os decimais (Ex: 7,85))");
			for (i = 0; i < t; i++) {

				seg = false;
				do {
					try {
						System.out.println("-> Digite o limite INFERIOR  referente a classe " + (i+1) + ": " );
						mBase[i][0] = teclado.nextDouble();
						seg = true;
					} catch (InputMismatchException erro) {
						System.out.println("Ocorreu um erro, você deve ter digitado alguma letra tente denovo!");
						teclado.nextLine();
					}
				} while (seg == false);
				seg = false;
				do {
					try {
						System.out.println("-> Digite o limite SUPERIOR referente a classe " + (i+1) + ": " );
						mBase[i][1] = teclado.nextDouble();
						mBase[i][3] = 0.50 * (mBase[i][0] + mBase[i][1]);
						seg = true;
					} catch (InputMismatchException erro) {
						System.out.println("Ocorreu um erro, você deve ter digitado alguma letra tente denovo!");
						teclado.nextLine();
					}
				} while (seg == false);
				seg = false;
				do {
					try {
						do {
							System.out.println("-> Digite a frequencia individual absoluta referente a classe " + (i+1) + ": " );
							mBase[i][2] = teclado.nextInt();
							if (mBase[i][2] < 0)
								System.out.println("A frequencia não pode ser negativa!");
						} while (mBase[i][2] < 0);
						seg = true;
					} catch (InputMismatchException erro) {
						System.out.println("Ocorreu um erro, você deve ter digitado alguma letra ou numero real");
						teclado.nextLine();
					}
				} while (seg == false);
				N += mBase[i][2];
				mBase[i][4] = mBase[i][3] * mBase[i][2];
				xifi += mBase[i][4];

			}

			xl = num_arredondado(xifi / N); // Calculo da Media

			double mVariancia[][] = new double[t][3];

			for (i = 0; i < t; i++) {

				mVariancia[i][0] = mBase[i][3];
				mVariancia[i][1] = mBase[i][2];

			}

			for (i = 0; i < t; i++) {

				mVariancia[i][2] = ((mVariancia[i][0] - xl) * (mVariancia[i][0] - xl)) * mVariancia[i][1];
				xix += mVariancia[i][2];

			}

			variancia = num_arredondado(xix / (N - 1)); // Calculo da Variancia
			desviopadrao = num_arredondado(Math.sqrt(variancia)); // Calculo do Desvio-Padrao
			CV = num_arredondado((100 * desviopadrao) / xl); // Calculo do Coeficiente de Variacao (CV)

			System.out.println("\nMENU DE OPCOES:");
			System.out.println("1. Tabela da Media (X-Linha)");
			System.out.println("2. Tabela da Variancia (s^2)");
			System.out.println("3. Media (X-Linha)");
			System.out.println("4. Variancia (s^2)");
			System.out.println("5. Desvio-Padrao (s)");
			System.out.println("6. Coeficiente de Variacao (CV)");
			System.out.println("7. Valor de N (Soma de FI)");
			System.out.println("8. Encerrar a leitura de dados");
			System.out.print("\nEscolha uma opcao pelo numero: ");

			int opcao = 0;

			do {
				do {
					try {
						opcao = teclado.nextInt();
						seg = true;
					} catch (InputMismatchException erro) {
						System.out.println("\nOcorreu um erro, você deve ter digitado alguma letra ou numero invalido tente novamente!");
						teclado.nextLine();
					}
				} while (seg = false);
				switch (opcao) {
				case 1:
					System.out.println("\nOpcao Tabela da media");
					System.out.println(" LI   |  LS  |  PMI  | Frequencia Individual Absoluta | PMI * Fi |");
					for (i = 0; i < t; i++) {
						System.out.printf("%.2f | %.2f | %.2f | %.2f | %.2f\n", mBase[i][0], mBase[i][1], mBase[i][3],
								mBase[i][2], mBase[i][4]);
					}
					System.out.print("\nEscolha novamente uma opcao: ");
					break;
				case 2:
					System.out.println("\nOpção Tabela da Variancia");
					System.out.println("Ponto Medio | Frequencia Individual Absoluta | Variancia");
					for (i = 0; i < t; i++) {
						System.out.printf("%.2f | %.2f | %.2f\n", mVariancia[i][0], mVariancia[i][1], mVariancia[i][2]);
					}
					System.out.print("\nEscolha novamente uma opcao: ");
					break;
				case 3:
					System.out.println("\n\tOpcao Media (X-linha)");
//Media
					System.out.println("\n\tValor da Media: " + xl);
					System.out.print("\nEscolha novamente uma opcao: ");
					break;
				case 4:
					System.out.println("\n\tOpcao Variancia (s^2)");
//Variância
					System.out.println("\n\tValor da Variancia: " + variancia);
					System.out.print("\nEscolha novamente uma opcao: ");
					break;
				case 5:
					System.out.println("\n\tOpcao Desvio-Padrao (s)");
//Desvio-padrao
					System.out.println("\n\tValor do Desvio-Padrao: " + desviopadrao);
					System.out.print("\nEscolha novamente uma opcao: ");
					break;
				case 6:
					System.out.println("\n\tOpcao Coeficiente de Variacao (CV)");
//Coeficiente de Variação
					System.out.println("\n\tValor do Coeficiente de Variacao: " + CV + "%");
					System.out.print("\nEscolha novamente uma opcao: ");
					break;
				case 7:
					System.out.println("\n\tOpcao Valor de N");
					System.out.println("\n\tN: " + N);
					System.out.print("\nEscolha novamente uma opcao: ");
					break;
				case 8:
					System.out.println("\n\tSaindo da leitura de dados...");
					break;
				default:
					System.out.println("\n\tOpcao invalida! Tente novamente.");
					teclado.nextLine();
				}
			} while (opcao != 8);
			do {
				try {
					do {
					System.out.println("\n\tDeseja reiniciar o programa para um novo uso? \n\t1-Sim \n\t2-Nao ");
					main_option = teclado.nextInt();
					if (main_option != 1 && main_option != 2)
					System.out.println("Valor invalido, digite 1 para reiniciar o programa ou 2 para encerrar.");
					else if (main_option == 1) {
						System.out.println("\n\tReiniciando!!");
					}
					else {
						System.out.println("Programa encerrado!");
					}
					} while(main_option != 1 && main_option != 2); 
					seg = true;
				} catch (InputMismatchException erro) {
					System.out.println("Você digitou uma opção invalida tente novamente!");
					teclado.nextLine();
				}
			} while (seg == false);
		} while (main_option != 2);

		teclado.close();
	}

// Metodo de arredondamento:

	public static double num_arredondado(double num) {

		double n;

		if (num == 0) {
			return num;

		} else if (num < 0) {
			n = -num;

		} else {
			n = num;
		}

		double a = n - (int) n;
		double b = a * 100;
		double c = b - (int) b;
		int d = (int) (c * 10);
		double e = a - (c / 100);
		double arredonda;

		if (d >= 5) {
			arredonda = (int) n + e + 0.01;
			arredonda = Math.round(arredonda * 100.0) / 100.0; //

		} else {
			arredonda = (int) n + e;
		}

		if (num < 0) {
			arredonda = -arredonda;
		}
		return arredonda;
	}
}