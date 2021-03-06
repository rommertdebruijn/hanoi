package org.rommert.hanoi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TowersOfHanoiRenderer {

    public static final String SEPARATOR = "   ";
    private int nrOfDisks;

    public TowersOfHanoiRenderer(int nrOfDisks) {
        this.nrOfDisks = nrOfDisks;
    }

    public String render(List<Tower> towers) {
        Map<Integer, List<Integer>> positionToDisks = mapDisksToPositionInTower(towers);

        // render that map!
        StringBuilder toString = new StringBuilder();
        for (int height=nrOfDisks;height>0;height--) { // for each position in the tower (high to low)
            for (Integer disk : positionToDisks.get(height)) { // render any disks that are in that position
                toString.append(renderDisk(disk));
                toString.append(SEPARATOR);
            }
            toString.append("\n");
        }
        return toString.toString();
    }

    private Map<Integer, List<Integer>> mapDisksToPositionInTower(List<Tower> towers) {
        Map<Integer, List<Integer>> positionToDisks = new HashMap<>();
        for (int height=1;height<=nrOfDisks;height++) { // for each position in the tower (low to high)
            List<Integer> disksInPosition = new ArrayList<>(); // ... keep track of any disks that are in this position
            for (Tower tower : towers) {
                if (tower.size() >= height) { // if tower goes (at least) this high
                    disksInPosition.add(tower.getDisk(height - 1)); // ... then store the disk that is in this position
                } else {
                    disksInPosition.add(0); // else store an empty disk
                }
            }
            positionToDisks.put(height, disksInPosition); // store position->the list of disks that are in that position
        }
        return positionToDisks;
    }

    private String renderDisk(Integer disk) {
        int nrOfSpaces = nrOfDisks - disk;
        StringBuilder b = new StringBuilder("^"); // center of the tower
        for (int i=0;i<disk;i++) { // render the disk...
            b.insert(0, "=").append("=");
        }
        for (int i=0;i<nrOfSpaces;i++) { //... then pad with whitespace
            b.insert(0, " ").append(" ");
        }
        return b.toString();
    }
}
