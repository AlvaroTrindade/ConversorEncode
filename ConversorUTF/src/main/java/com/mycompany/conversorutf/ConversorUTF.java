/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.conversorutf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 *
 * @author FamiliaTrindade
 */
public class ConversorUTF {

    public static String conversor(File diretorio) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        File[] list = diretorio.listFiles();

        for (File fl : list) {

            if (!fl.isDirectory()) {

                String n = fl.getName();
                String caminhoPadrao = fl.getAbsolutePath();
                String diretorioOud = fl.getCanonicalPath();
                String diretorioNew = fl.getCanonicalPath();

                if (n.contains(".xhtml") || n.contains(".java")) {

                    if (n.contains(".xhtml")) {
                        diretorioOud = diretorioOud.replace(".xhtml", ".xhtml.oud");
                        diretorioNew = diretorioNew.replace(".xhtml", ".xhtml.new");

                    } else {
                        diretorioOud = diretorioOud.replace(".java", ".java.oud");
                        diretorioNew = diretorioNew.replace(".java", ".java.new");

                    }
                    // Renomeia arquivo padrão para .Oud
                    File filePadrao = new File(caminhoPadrao);
                    filePadrao.renameTo(new File(diretorioOud));

                    //Fluxo de Entrada com Arquivo
                    FileInputStream arq = new FileInputStream(diretorioOud);
                    InputStreamReader inputArquivo = new InputStreamReader(arq, "ISO-8859-1");
                    BufferedReader buffRead = new BufferedReader(inputArquivo);

                    // Fluxo de Saída com novo Arquivo
                    FileOutputStream novoArq = new FileOutputStream(diretorioNew);
                    BufferedWriter buffWrite = new BufferedWriter(new OutputStreamWriter(novoArq, "UTF-8"));

                    String linha = buffRead.readLine();
                    while (linha != null) {
                        buffWrite.write(linha);
                        buffWrite.newLine();
                        linha = buffRead.readLine();
                    }
                    buffRead.close();
                    buffWrite.close();

               // Deleta arquivo antigo e renomeia o novo (new) 
                    File arquivoOud = new File(diretorioOud);
                    arquivoOud.delete();

                   File arquivoNew = new File(diretorioNew);
                   arquivoNew.renameTo(new File(caminhoPadrao));

                }
            } else {
                conversor(fl);

            }
        }

        return ("Encode convertido");

    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Conversor ISO-8859-1 para UTF-8 (Arquivos: .xhtml e .java)\n");
        System.out.print("Informe o diretório: ");
        String caminho = teclado.nextLine();

        File diretorio = new File(caminho);
        System.out.print(conversor(diretorio));
    }
}
