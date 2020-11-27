/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codes;

import Converts.InterfaceConverter;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import Converts.InterfaceConverter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author silva
 */
public class Controller {
    
    private Thread watcherThread;
    private LoaderConverter loaderConverter;
    private List<InterfaceConverter> filtredList;
    private String pathToFolderString;
    
    public Controller(String pathToFolderString) {
        this.pathToFolderString = pathToFolderString;
        this.loaderConverter = new LoaderConverter(pathToFolderString);
        
        // Monitora pelo sistema de arquivos em outra thread
        FileWatcher watcher = FileWatcher.getInstance(pathToFolderString);
        this.watcherThread = new Thread(watcher);
        this.watcherThread.start();
        this.loaderConverter.loader();
    }
    
    public List<InterfaceConverter> getFiltredList() {
        return filtredList;
    }
    
    public DefaultComboBoxModel generateComboBoxModel() {
        Object[] items = loaderConverter.getLoadedObject();
        DefaultComboBoxModel model = new DefaultComboBoxModel(items);
        return model;
    }
    
    public DefaultComboBoxModel generateCobBoxModel(String filter) {
        this.filtredList = new ArrayList<>();
        for (InterfaceConverter itemObject : loaderConverter.getLoadedObject()) {
            if (itemObject.getCategory().equals(filter)) {
                // Pertence a mesma categoria desejada
                filtredList.add(itemObject);
            }
        }
        return new DefaultComboBoxModel(filtredList.toArray());
    }
    
    public Double convert(double value, InterfaceConverter inputConverter, InterfaceConverter expectedConverter) {
        return expectedConverter.convert(inputConverter.toBase(value));
    }
}
