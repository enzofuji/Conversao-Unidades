/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codes;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.InterfaceConverter;

/**
 *
 * @author silva
 */
public class LoaderConverter {

    private String pathToFolderString;
    private ArrayList<InterfaceConverter> loadedObject = new ArrayList<>();

    public LoaderConverter(String pathToFolderString) {
        this.pathToFolderString = pathToFolderString;
    }

    public InterfaceConverter[] getLoadedObject() {
        InterfaceConverter[] array = new InterfaceConverter[loadedObject.size()];
        return (InterfaceConverter[]) loadedObject.toArray(array);
    }

    /**
     * Responsável por carregar pela primeira vez todas as classes que estão disponíveis
     * e carregar no arrayList todos os Objects.
     */
    public void loader() {
        String packageString = "resources";
        File[] filesFromFolder = getFilesFromFolder();
        for (File classFile : filesFromFolder) {
            try {
                String classString = classFile.getName().replace(".java", "");
                Object o = Class.forName(packageString + "." + classString).getConstructor().newInstance();
                InterfaceConverter converter = (InterfaceConverter) o;
                addToLoaded(converter);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoaderConverter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                // Método não foi encontrado pois representa uma interface
            } catch (SecurityException ex) {
                Logger.getLogger(LoaderConverter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(LoaderConverter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(LoaderConverter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(LoaderConverter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(LoaderConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Coloca apenas um objeto por classe dentro do ArrayList,
     * evitando duplicatas.
     * @param obj Objeto a ser comparado.
     */
    private void addToLoaded(InterfaceConverter obj) {
        String classString = obj.getClass().getName();
        for (InterfaceConverter objDefault: this.loadedObject) {
            if (objDefault.getClass().getName().equals(classString)) {
                return;  // Já está dentro do arrayList
            }
        }
        // Do contrário, adiciona no ArrayList
        this.loadedObject.add(obj);
    }
    
    /**
     * Lê todos os arquivos .java dentro de uma pasta.
     * @return Array de arquivos de classes a serem carregados.
     */
    private File[] getFilesFromFolder() {
        File folder = new File(this.pathToFolderString);
        FilenameFilter filterName = generateFilenameFilter(".java");
        return folder.listFiles(filterName);
    }

    /**
     * Filtro de arquivos.
     * @param filterString Nome que deseja ser filtrado.
     * @return booleano indicando se o arquivo corresponde ao filtro.
     */
    private FilenameFilter generateFilenameFilter(String filterString) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String filter = filterString.toLowerCase();
                return filter.endsWith(".java");
            }
        };
    }
}