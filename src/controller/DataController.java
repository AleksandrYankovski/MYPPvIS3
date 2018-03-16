package controller;

import model.BinaryTree;
import model.Randomiser;
import view.DekartSystem;
import view.MainFrame;

import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by Александр on 28.05.2017.
 */
public class DataController implements Runnable {
    private MainFrame mainFrame;
    private ArrayList<ArrayList<Long>> randomArrays;
    private Long kolMass;
    private Long sortTime;
    private DekartSystem dekartSystem;
    private DefaultTableModel tableModel;
    private int maxSortTime;
    private ArrayList<Long> arraySortTime;


    public DataController() {
        mainFrame = new MainFrame(this);
        tableModel = mainFrame.getTableModel();
        arraySortTime = new ArrayList<>();

    }

    public void start(Long kolMass, Integer maxKolElement) {
        maxSortTime = 0;
        randomArrays = new ArrayList<>();
        this.kolMass = kolMass;
    }

    @Override
    public void run() {
        tableModel.setRowCount(0);
        arraySortTime.clear();
        calculate();
    }

    private void calculate() {
        for (long curentKolElement = 2; curentKolElement < kolMass; curentKolElement++) {
            ArrayList<Long> arrayList=new Randomiser((long) curentKolElement).getArray();
            calculateTimeforSorting(arrayList);
            updateMainFrame(arrayList);
        }
    }

    private synchronized void calculateTimeforSorting(ArrayList<Long> arrayList) {
        int kolSteps = 100;
        Long[] varible = new Long[100];
        Long sum = new Long(0);

        for (int step = 0; step < kolSteps; step++) {
            varible[step] = new Long(0);
            varible[step] = new BinaryTree(arrayList).getTimeforSorting();
            sum += varible[step];
        }
        sortTime = sum / 100;
        arraySortTime.add(sortTime);
        if (sortTime > maxSortTime)
            maxSortTime = Math.toIntExact(sortTime);
    }


    private synchronized void updateMainFrame(ArrayList<Long> arrayList) {
        updateTable(arrayList);
        updateDekartSystem();
    }

    private synchronized void updateDekartSystem() {
        dekartSystem = mainFrame.getDekartSystem();

        dekartSystem.buildGraph(Math.toIntExact(kolMass), true, maxSortTime, arraySortTime);


        dekartSystem.repaint();

    }

    private synchronized void updateTable(ArrayList<Long> arrayList) {
        String[] strings = {String.valueOf(arrayList.size()), String.valueOf(sortTime)};
        tableModel.addRow(strings);


        tableModel.newRowsAdded(new TableModelEvent(tableModel));
    }

}

