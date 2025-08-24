package com.criptografia;

import com.criptografia.criptografia.AssinaturaDigital;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Responsavel> responsaveis = new ArrayList<>();
        ArrayList<Boletim> boletins = new ArrayList<>();

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\nMenu:");
            System.out.println("1 - Criar Boletim");
            System.out.println("2 - Enviar chave para um responsável");
            System.out.println("3 - Assinar boletim digitalmente");
            System.out.println("4 - Cadastrar responsável");
            System.out.println("5 - Decriptografia Boletim");
            System.out.println("6 - Verificar assiatura Digital do Boletim");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Entrada inválida.");
                continue;
            }

            switch (opcao) {
                case 1:
                    try {
                        System.out.println("Digite o conteúdo do boletim:");
                        String conteudoBoletim = scanner.nextLine();

                        Boletim boletim = new Boletim();
                        boletim.setConteudo(conteudoBoletim);
                        boletins.add(boletim);
                        System.out.println("Boletim criado com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro ao criar boletim: " + e.getMessage());
                    }
                    break;
                case 2:
                    if (responsaveis.isEmpty() || boletins.isEmpty()) {
                        System.out.println("Cadastre pelo menos um responsável e um boletim antes.");
                        break;
                    }
                    System.out.println("Escolha o responsável do aluno (digite o número do responsável):");
                    for (int i = 0; i < responsaveis.size(); i++) {
                        System.out.println(i + " - Responsável " + responsaveis.get(i).getNome());
                    }
                    int indiceResponsavel = -1;
                    try {
                        indiceResponsavel = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Entrada inválida.");
                        break;
                    }
                    if (indiceResponsavel < 0 || indiceResponsavel >= responsaveis.size()) {
                        System.out.println("Índice de responsável inválido.");
                        break;
                    }
                    System.out.println("Escolha o boletim correspondente:");
                    for (int i = 0; i < boletins.size(); i++) {
                        System.out.println(i + " - Boletim " + (i + 1));
                    }
                    int indiceBoletim = -1;
                    try {
                        indiceBoletim = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Entrada inválida.");
                        break;
                    }
                    if (indiceBoletim < 0 || indiceBoletim >= boletins.size()) {
                        System.out.println("Índice de boletim inválido.");
                        break;
                    }
                    Responsavel responsavel = responsaveis.get(indiceResponsavel);
                    Boletim boletim = boletins.get(indiceBoletim);
                    try {
                        boletim.enviarChavePublicaParaResponsavel(responsavel);
                        System.out.println("Chave pública enviada para o responsável " + responsavel.getNome());
                    } catch (Exception e) {
                        System.out.println("Erro ao enviar chave pública: " + e.getMessage());
                    }
                    break;
                case 3:
                    if (boletins.isEmpty()) {
                        System.out.println("Nenhum boletim cadastrado.");
                        break;
                    }
                    System.out.println("Escolha o boletim para assinar digitalmente:");
                    for (int i = 0; i < boletins.size(); i++) {
                        System.out.println(i + " - Boletim " + (i + 1));
                    }
                    int indiceBoletimAssinar = -1;
                    try {
                        indiceBoletimAssinar = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Entrada inválida.");
                        break;
                    }
                    if (indiceBoletimAssinar < 0 || indiceBoletimAssinar >= boletins.size()) {
                        System.out.println("Índice de boletim inválido.");
                        break;
                    }
                    Boletim boletimAssinar = boletins.get(indiceBoletimAssinar);
                    try {
                        String assinatura = boletimAssinar.assinarDigitalmente();
                        System.out.println("Boletim assinado digitalmente. Assinatura: " + assinatura);
                    } catch (Exception e) {
                        System.out.println("Erro ao assinar digitalmente: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Digite o nome do responsável:");
                    String nomeResponsavel = scanner.nextLine();
                    Responsavel novoResponsavel = new Responsavel();
                    novoResponsavel.setNome(nomeResponsavel);
                    responsaveis.add(novoResponsavel);
                    System.out.println("Responsável " + nomeResponsavel + " cadastrado com sucesso.");
                    break;
                case 5:
                    if (responsaveis.isEmpty() || boletins.isEmpty()) {
                        System.out.println("Cadastre pelo menos um responsável e um boletim antes.");
                        break;
                    }
                    System.out.println("Escolha o responsável do aluno (digite o número do responsável):");
                    for (int i = 0; i < responsaveis.size(); i++) {
                        System.out.println(i + " - Responsável " + responsaveis.get(i).getNome());
                    }
                    indiceResponsavel = -1;
                    try {
                        indiceResponsavel = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Entrada inválida.");
                        break;
                    }
                    if (indiceResponsavel < 0 || indiceResponsavel >= responsaveis.size()) {
                        System.out.println("Índice de responsável inválido.");
                        break;
                    }
                    System.out.println("Escolha o boletim correspondente:");
                    for (int i = 0; i < boletins.size(); i++) {
                        System.out.println(i + " - Boletim " + (i + 1));
                    }
                    indiceBoletim = -1;
                    try {
                        indiceBoletim = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Entrada inválida.");
                        break;
                    }
                    if (indiceBoletim < 0 || indiceBoletim >= boletins.size()) {
                        System.out.println("Índice de boletim inválido.");
                        break;
                    }
                    responsavel = responsaveis.get(indiceResponsavel);
                    boletim = boletins.get(indiceBoletim);

                    try {
                        System.out.println(responsavel.decriptaBoletim(boletim));
                    } catch (Exception e) {
                        System.out.println("Erro ao decriptografar Conteudo"  + e.getMessage());
                    }
                    break;
                case 6:
                    if (boletins.isEmpty()) {
                        System.out.println("Nenhum boletim cadastrado.");
                        break;
                    }
                    System.out.println("Escolha o boletim para verificar a assinatura digital:");
                    for (int i = 0; i < boletins.size(); i++) {
                        System.out.println(i + " - Boletim " + (i + 1));
                    }
                    int indiceBoletimVerificar = -1;
                    try {
                        indiceBoletimVerificar = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Entrada inválida.");
                        break;
                    }
                    if (indiceBoletimVerificar < 0 || indiceBoletimVerificar >= boletins.size()) {
                        System.out.println("Índice de boletim inválido.");
                        break;
                    }
                    Boletim boletimVerificar = boletins.get(indiceBoletimVerificar);
                    try {
                        boolean isValida = AssinaturaDigital.verificaSHA256RSA(boletimVerificar.getConteudo(), boletimVerificar.getAssinaturaDigital(), boletimVerificar.getChaveAssinaturaDigital());
                        if (isValida) {
                            System.out.println("A assinatura digital é válida.");
                        } else {
                            System.out.println("A assinatura digital é inválida.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao verificar assinatura digital: " + e.getMessage());
                    }
                    break;
            }
        }
        scanner.close();
    }
}