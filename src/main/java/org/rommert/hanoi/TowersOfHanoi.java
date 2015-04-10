package org.rommert.hanoi;

import java.util.ArrayList;
import java.util.List;

/**
 * 3 Towers, containing Disks that all vary in size. In the starting position, all Disks are on the same Tower, ordered
 * from big (on the bottom) to small (on the top).
 */
public class TowersOfHanoi {

    private int nrOfMoves = 0;
    private List<Tower> towers = new ArrayList<>();

    public TowersOfHanoi(int nrOfDisks) {
        Integer[] startingDisks = new Integer[nrOfDisks];
        for (int index=0, disk=nrOfDisks;disk>=1;index++, disk--) {
            startingDisks[index]=disk;
        }

        towers.add(new Tower("A", startingDisks));
        towers.add(new Tower("B"));
        towers.add(new Tower("C"));
    }


    /**
     * move the given disk, which should be on top of the given from column, from that column to the given to column
     */
    public void moveDisk(int disk, Tower from, Tower to) {
        from.removeDisk(disk);
        to.addDisk(disk);

        nrOfMoves++;
        System.out.println(String.format("[%s] Move disk %s from tower %s to tower %s. Results in %s",
                nrOfMoves, disk, from.getName(), to.getName(), this.toString()));
    }

    /**
     * move the given disk and every disk on top of that disk from the given from column to the given to column
     */
    public void movePile(int disk, Tower from, Tower to) {
        // moving a pile containing only the smallest disk is simple: just move the disk
        if (disk == 1) {
            moveDisk(disk, from, to);
            return;
        }

        // moving a pile that contains more then a single Disk requires the rest of the pile to be stashed somewhere
        // while we move the bottom Disk
        Tower stash = determineStashColumn(from, to);
        movePile(disk-1, from, stash);
        moveDisk(disk, from, to);
        movePile(disk-1, stash, to);
    }

    /**
     * Find the Tower that is neither the given from Tower, nor the given to Tower
     * @param from
     * @param to
     * @return the remaining Tower
     */
    private Tower determineStashColumn(Tower from, Tower to) {
        return towers.stream().filter(tower->!(tower.equals(from) || tower.equals(to))).findFirst().get();
    }

    @Override
    public String toString() {
        return towers.toString();
    }


    /**
     * Move all the Disks from the starting Tower, one by one, such that in the end the starting pile of Disks is completely
     * moved from the starting Tower to another Tower. In the process, a bigger Disk may never rest upon a smaller disk.
     *
     */
    public void solve() {
        // find column with all the disks on it
        Tower startingTower = towers.stream().filter(column->!column.isEmpty()).findFirst().get();

        // move those disks to another column (we don't really care which one)
        Tower toTower = towers.stream().filter(column->!column.equals(startingTower)).findFirst().get();

        movePile(startingTower.size(), startingTower, toTower);
        System.out.println("Done!");
    }
}
