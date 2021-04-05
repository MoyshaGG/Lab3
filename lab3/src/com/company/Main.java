package com.company;

import lab3.test.TestByConsol;
import lab3.test.TestFile;
import lab3.test.TestStoreObject;
import lab3.test.testApp;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        testApp app = new testApp();
        app.startApp();
        TestByConsol testConsole = new TestByConsol();
        testConsole.startAppConsole();
        TestFile.main();
        TestStoreObject storeObject = new TestStoreObject();
        storeObject.main();
    }
}
